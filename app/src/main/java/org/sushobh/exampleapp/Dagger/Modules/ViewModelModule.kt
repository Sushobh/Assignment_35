package org.sushobh.exampleapp.Dagger.Modules

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import org.sushobh.exampleapp.Base.BaseActivity
import org.sushobh.exampleapp.Base.BaseFragment
import org.sushobh.exampleapp.MainActivity
import org.sushobh.exampleapp.ViewModels.MainViewModel


@Module
class ViewModelModule(var baseActivity: BaseActivity?,val baseFragment: BaseFragment?) {



    @Provides
    fun provideMainViewModel (): MainViewModel {
        val viewModel =  ViewModelProviders.of(baseActivity as MainActivity).get(MainViewModel::class.java)
        viewModel.presenter = (baseActivity as MainActivity).mainPresenter;
        return viewModel
    }



}