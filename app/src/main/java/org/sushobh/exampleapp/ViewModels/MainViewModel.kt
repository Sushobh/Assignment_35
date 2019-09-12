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



    var shirtItemsLiveData : MutableLiveData<List<ClothItem>> = MutableLiveData<List<ClothItem>>()
    var pantItemsLiveData : MutableLiveData<List<ClothItem>> = MutableLiveData<List<ClothItem>>()
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
        if(shirtItemsLiveData.value != null && pantItemsLiveData.value != null){
            if(!(shirtPosition>=0 && pantPosition >=0)){
                return
            }
            val shirtImageUri = shirtItemsLiveData.value!![shirtPosition].fileUri
            val pantImageUri = pantItemsLiveData.value!![pantPosition].fileUri
            if( mainModel.contains(shirtImageUri,
                    pantImageUri)){
                mainModel.updateFav(shirtImageUri,
                    pantImageUri)
                favIconLiveData.value = mainModel.isFav(shirtImageUri,
                    pantImageUri)
            }
            else {
                mainModel.addFav(FavoriteCombination(
                    shirtImageUri,
                    pantImageUri,true)
                )
                favIconLiveData.value = mainModel.isFav(shirtImageUri,
                    pantImageUri)
            }
        }

    }

    fun clickedOnShuffle() {
        if(shirtItemsLiveData.value != null && pantItemsLiveData.value != null){
            val shirtsCount = if(shirtItemsLiveData.value!!.size == 0) {
                0
            }
            else {
                shirtItemsLiveData.value!!.size -1
            }
            val pantsCount = if(pantItemsLiveData.value!!.size == 0) {
                0
            }
            else {
                pantItemsLiveData.value!!.size -1
            }

            currentPagerPosition.value =  Pair(
                (0..shirtsCount).random()
                ,
                (0..pantsCount).random()
            )
        }

    }

    fun clothItemSelectionChanaged(shirtPostion: Int, pantPosition: Int) {
           favIconLiveData.value = mainModel.isFav(shirtItemsLiveData.value!![shirtPostion].fileUri,
               pantItemsLiveData.value!![pantPosition].fileUri)
    }


}