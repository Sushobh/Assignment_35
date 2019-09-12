package org.sushobh.exampleapp.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import org.sushobh.exampleapp.Room.Entity.ClothItem
import org.sushobh.exampleapp.Room.Entity.FavoriteCombination


@Database(entities = arrayOf(ClothItem::class,FavoriteCombination::class) , version =  1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clothItemDao() : ClothItemDao
}