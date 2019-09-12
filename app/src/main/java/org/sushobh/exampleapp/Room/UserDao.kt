package org.sushobh.exampleapp.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Observable
import org.sushobh.exampleapp.Room.Entity.ClothItem
import org.sushobh.exampleapp.Room.Entity.FavoriteCombination
import java.util.*
import kotlin.collections.ArrayList


@Dao
interface ClothItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClothItem(clothItem: ClothItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFav(favoriteCombination: FavoriteCombination)


    @Query("select *from FavoriteCombination")
    fun getAllFavs() : Observable<List<FavoriteCombination>>

    @Query("select *from ClothItem")
    fun getAllClothItems() : Observable<List<ClothItem>>

    @Query("update FavoriteCombination set isFavorite = (1 - isFavorite) where shirtFileUri = :shirtUri and pantFileUri = :pantUri")
    fun updateFav(shirtUri: String, pantUri: String)

    @Query("select isFavorite from FavoriteCombination where shirtFileUri = :shirtUri and pantFileUri = :pantUri")
    fun isFav(shirtUri: String, pantUri: String) : Boolean
    @Query("select * from FavoriteCombination where shirtFileUri = :shirtUri and pantFileUri = :pantUri limit 1")
    abstract fun getFav(shirtUri: String, pantUri: String) : FavoriteCombination

}