package org.sushobh.exampleapp

import android.app.Application
import org.sushobh.exampleapp.Dagger.Components.DaggerModelBuilder
import org.sushobh.exampleapp.Dagger.Components.DaggerViewModelBuilder

class MyApplication : Application() {

    var modelComponent = DaggerViewModelBuilder.builder().build()


    override fun onCreate() {
        myApplication = this;
        super.onCreate()
    }

    companion object {
        lateinit var myApplication: MyApplication
    }

}