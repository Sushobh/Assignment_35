package org.sushobh.exampleapp.Dagger.Modules

import dagger.Module
import dagger.Provides
import org.sushobh.exampleapp.Base.BaseActivity
import org.sushobh.exampleapp.Base.BaseFragment
import org.sushobh.exampleapp.Base.BasePresenter
import org.sushobh.exampleapp.MainActivity
import org.sushobh.exampleapp.Presenter.MainPresenter


@Module
class PresenterModule(var baseActivity: BaseActivity?,var baseFragment: BaseFragment?) {

    @Provides
    fun basePresenter() : BasePresenter {
        return BasePresenter(baseActivity)
    }

    @Provides
    fun mainPresenter() : MainPresenter {
        return MainPresenter(baseActivity as MainActivity)
    }




}

