package org.sushobh.exampleapp.Dagger.Components



import dagger.Component
import org.sushobh.exampleapp.Dagger.Modules.AppModule
import org.sushobh.exampleapp.Dagger.Modules.ModelModule
import org.sushobh.exampleapp.ViewModels.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ModelModule::class, AppModule::class))
interface ViewModelBuilder {
    fun inject(mainViewModel: MainViewModel)
}
