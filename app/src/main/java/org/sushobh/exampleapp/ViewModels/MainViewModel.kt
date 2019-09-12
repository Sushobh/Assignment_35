package org.sushobh.exampleapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import org.sushobh.exampleapp.Base.BaseViewModel
import org.sushobh.exampleapp.Dagger.Components.DaggerViewModelBuilder
import org.sushobh.exampleapp.Dagger.Modules.ModelModule
import org.sushobh.exampleapp.Extras.MobileInfo
import org.sushobh.exampleapp.Models.MainModel
import org.sushobh.exampleapp.Presenter.MainPresenter
import org.sushobh.exampleapp.Room.Entity.ClothItem
import org.sushobh.exampleapp.Room.Entity.FavoriteCombination
import org.sushobh.exampleapp.Room.Entity.IMAGE_TYPE_PANT
import org.sushobh.exampleapp.Room.Entity.IMAGE_TYPE_SHIRT
import javax.inject.Inject

class MainViewModel  : BaseViewModel<MainPresenter>() {



    var shirtItemsLiveData = MutableLiveData<List<ClothItem>>()
    var pantItemsLiveData = MutableLiveData<List<ClothItem>>()
    var currentPagerPosition = MutableLiveData<Pair<Int,Int>>()
    var favIconLiveData = MutableLiveData<Boolean>()

    override fun inject() {
        DaggerViewModelBuilder.builder()
            .modelModule(ModelModule()).build().inject(this)
    }


    override fun init() {
        presenter.setTitle("Main Activity")
        disposables.add(mainModel.getClothingItems()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                if(it != null){
                    val groupedItems =  it.groupBy {
                        it.imageType
                    }

                    shirtItemsLiveData.value = groupedItems[IMAGE_TYPE_SHIRT]
                    pantItemsLiveData.value = groupedItems[IMAGE_TYPE_PANT]
                }
                else {
                    shirtItemsLiveData.value = ArrayList()
                    pantItemsLiveData.value = ArrayList()
                }

             }))
    }

    fun clickedOnAddShirt() {
        presenter.pickImage("Select a shirt")?.subscribe(Consumer {
              if(it != null){
                  mainModel.addClothItem(ClothItem(it.toString(), IMAGE_TYPE_SHIRT))
              }
        })?.let { disposables.add(it) }
    }

    fun clickedOnAddPant() {
        presenter.pickImage("Select a Pant")?.subscribe(Consumer {
            if(it != null){
                mainModel.addClothItem(ClothItem(it.toString(), IMAGE_TYPE_PANT))
            }
        })?.let { disposables.add(it) }
    }

    fun clickedOnFavorite(shirtPosition : Int,pantPosition : Int) {

        if( mainModel.contains(shirtItemsLiveData.value!![shirtPosition].fileUri,
                pantItemsLiveData.value!![pantPosition].fileUri)){
            mainModel.updateFav(shirtItemsLiveData.value!![shirtPosition].fileUri,
                pantItemsLiveData.value!![pantPosition].fileUri)
            favIconLiveData.value = mainModel.isFav(shirtItemsLiveData.value!![shirtPosition].fileUri,
                pantItemsLiveData.value!![pantPosition].fileUri)
         }
        else {
            mainModel.addFav(FavoriteCombination(
                shirtItemsLiveData.value!![shirtPosition].fileUri,
                pantItemsLiveData.value!![pantPosition].fileUri,true)
            )
            favIconLiveData.value = mainModel.isFav(shirtItemsLiveData.value!![shirtPosition].fileUri,
                pantItemsLiveData.value!![pantPosition].fileUri)
        }


    }

    fun clickedOnShuffle() {
         currentPagerPosition.value =  Pair(
             (0..shirtItemsLiveData.value!!.size-1).random()
             ,
             (0..pantItemsLiveData.value!!.size-1).random()
         )
    }

    fun clothItemSelectionChanaged(shirtPostion: Int, pantPosition: Int) {
           favIconLiveData.value = mainModel.isFav(shirtItemsLiveData.value!![shirtPostion].fileUri,
               pantItemsLiveData.value!![pantPosition].fileUri)
    }


}