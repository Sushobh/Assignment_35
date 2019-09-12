package org.sushobh.exampleapp.Dagger.Modules

import dagger.Module
import dagger.Provides
import org.sushobh.exampleapp.Extras.MobileInfo
import org.sushobh.exampleapp.MyApplication
import javax.inject.Singleton

@Module
class AppModule{


    @Provides
    @Singleton
    fun provideMobileInfo() : MobileInfo {
        return MobileInfo(MyApplication.myApplication)
    }

}