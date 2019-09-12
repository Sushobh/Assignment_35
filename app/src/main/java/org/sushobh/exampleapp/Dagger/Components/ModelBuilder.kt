package org.sushobh.exampleapp.Dagger.Components

import dagger.Component
import org.sushobh.exampleapp.Base.BaseModel
import org.sushobh.exampleapp.Dagger.Modules.RetrofitModule
import org.sushobh.exampleapp.Dagger.Modules.RoomDatabaseModule
import org.sushobh.exampleapp.Models.MainModel
import javax.inject.Singleton


@Singleton
@Component(modules = [RetrofitModule::class,RoomDatabaseModule::class])
interface ModelBuilder {
    fun inject (mainModel: MainModel)
}