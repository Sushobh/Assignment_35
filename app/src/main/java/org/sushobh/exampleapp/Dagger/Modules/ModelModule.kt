package org.sushobh.exampleapp.Dagger.Modules

import dagger.Module
import dagger.Provides
import org.sushobh.exampleapp.Models.MainModel
import javax.inject.Singleton


@Module
class ModelModule {

    @Provides
    @Singleton
    fun provideMainModel() : MainModel{
        return MainModel()
    }

}