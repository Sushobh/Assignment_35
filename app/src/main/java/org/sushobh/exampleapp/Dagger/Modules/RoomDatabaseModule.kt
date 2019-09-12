package org.sushobh.exampleapp.Dagger.Modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import org.sushobh.exampleapp.MyApplication
import org.sushobh.exampleapp.Room.AppDatabase

import javax.inject.Singleton


@Module
class RoomDatabaseModule {


    @Singleton
    @Provides
    public fun provideRoomDatabase () : AppDatabase {
        return Room.databaseBuilder(MyApplication.myApplication,AppDatabase::class.java,"mydatabase")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

}