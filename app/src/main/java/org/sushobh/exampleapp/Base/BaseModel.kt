package org.sushobh.exampleapp.Base

import org.sushobh.exampleapp.Dagger.Components.DaggerModelBuilder
import org.sushobh.exampleapp.Dagger.Modules.RetrofitModule
import org.sushobh.exampleapp.Room.AppDatabase
import org.sushobh.exampleapp.Room.ClothItemDao

import retrofit2.Retrofit
import javax.inject.Inject

abstract class BaseModel {


    @Inject
    lateinit var retrofit : Retrofit;

    @Inject
    lateinit var appDatabase: AppDatabase

    lateinit var  userDao : ClothItemDao



    public final var BASE_URL = "http://google.com"

}