package org.sushobh.exampleapp.Base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import org.sushobh.exampleapp.Dagger.Components.DaggerViewModelBuilder
import org.sushobh.exampleapp.Dagger.Modules.ModelModule
import org.sushobh.exampleapp.Extras.MobileInfo
import org.sushobh.exampleapp.Models.MainModel
import javax.inject.Inject

abstract class BaseViewModel<T : BasePresenter> : ViewModel {

    abstract fun init()
    abstract fun inject()

    protected var disposables : ArrayList<Disposable> = ArrayList()


   @Inject
   lateinit var mainModel : MainModel

    @Inject
   lateinit var mobileInfo: MobileInfo

    open fun onResume() {

    }

    open fun onStart() {

    }


    override fun onCleared() {
        disposables.forEach(Disposable::dispose)
        super.onCleared()
    }

    lateinit var presenter : T


    constructor(){
        inject()
    }


}