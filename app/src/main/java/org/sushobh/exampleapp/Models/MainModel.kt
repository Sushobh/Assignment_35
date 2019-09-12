package org.sushobh.exampleapp.Models

import androidx.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.sushobh.exampleapp.Base.BaseModel
import org.sushobh.exampleapp.Dagger.Components.DaggerModelBuilder

import org.sushobh.exampleapp.Dagger.Modules.RetrofitModule
import org.sushobh.exampleapp.Dagger.Modules.RoomDatabaseModule
import org.sushobh.exampleapp.Room.AppDatabase
import org.sushobh.exampleapp.Room.Entity.ClothItem
import org.sushobh.exampleapp.Room.Entity.FavoriteCombination


import retrofit2.Retrofit
import java.util.*

import javax.inject.Inject

class MainModel : BaseModel {


    var someNumber = UUID.randomUUID()


    constructor() {
        DaggerModelBuilder.builder().retrofitModule(RetrofitModule(BASE_URL)).build().inject(this)
        userDao = appDatabase.clothItemDao()
    }

    fun addClothItem(clothItem: ClothItem){
        userDao.insertClothItem(clothItem)
    }

    fun getClothingItems() : Observable<List<ClothItem>>{
        return userDao.getAllClothItems()
    }


    fun getFavs() : Observable<List<FavoriteCombination>>{
        return userDao.getAllFavs()
    }

    fun updateFav( shirtUri : String,  pantUri : String) {
        userDao.updateFav(shirtUri,pantUri)
    }

    fun contains( shirtUri : String,  pantUri : String) : Boolean {
       return userDao.getFav(shirtUri,pantUri) != null
    }

    fun isFav( shirtUri : String,  pantUri : String) : Boolean {
        return userDao.isFav(shirtUri,pantUri)
    }

    fun addFav(favoriteCombination: FavoriteCombination) {
          userDao.insertFav(favoriteCombination)
    }

}