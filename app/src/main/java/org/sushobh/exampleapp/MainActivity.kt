package org.sushobh.exampleapp

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import org.sushobh.exampleapp.Adapters.ImageAdapter
import org.sushobh.exampleapp.Base.BaseActivity
import org.sushobh.exampleapp.Base.BaseViewModel
import org.sushobh.exampleapp.Dagger.Components.DaggerActivityBuilder
import org.sushobh.exampleapp.Dagger.Modules.PresenterModule
import org.sushobh.exampleapp.Dagger.Modules.ViewModelModule
import org.sushobh.exampleapp.Presenter.MainPresenter
import org.sushobh.exampleapp.Room.Entity.ClothItem
import org.sushobh.exampleapp.ViewModels.MainViewModel
import java.lang.Exception
import javax.inject.Inject

class MainActivity : BaseActivity() {


    @Inject
    lateinit var mainPresenter : MainPresenter

    @Inject
    lateinit var mainViewModel: MainViewModel

    var shirtItems : ArrayList<ClothItem> = ArrayList()
    var pantItems  : ArrayList<ClothItem> = ArrayList()



   lateinit var shirtImagePagerAdapter : ImageAdapter
   lateinit var pantImagePagerAdapter : ImageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.init()

        shirtImagePagerAdapter =  ImageAdapter(this,shirtItems)
        pantImagePagerAdapter = ImageAdapter(this,pantItems)
        guide1.adapter = shirtImagePagerAdapter
        guide2.adapter = pantImagePagerAdapter
        addClickListeners()
        addViewPageChangeListeners()
        observeData()
    }

    private fun addClickListeners() {
        add_shirt.setOnClickListener({
            mainViewModel.clickedOnAddShirt()
        })


        add_pant.setOnClickListener({
            mainViewModel.clickedOnAddPant()
        })

        favorite.setOnClickListener({
            mainViewModel.clickedOnFavorite(guide1.currentItem,guide2.currentItem)
        })

        shuffle.setOnClickListener({
            mainViewModel.clickedOnShuffle()
        })
    }

    private fun observeData() {
        mainViewModel.shirtItemsLiveData.observe(this, Observer {
            if(it != null){
                shirtItems.clear()
                shirtItems.addAll(it)
                shirtImagePagerAdapter.notifyDataSetChanged()
            }

        })

        mainViewModel.pantItemsLiveData.observe(this, Observer {
           if(it != null){
               pantItems.clear()
               pantItems.addAll(it)
               pantImagePagerAdapter.notifyDataSetChanged()
           }
        })

        mainViewModel.favIconLiveData.observe(this, Observer {
            if(it != null){
                if(it){
                    favorite.setImageResource(R.drawable.ic_favorite_black_24dp)
                }
                else {
                    favorite.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                }

            }
        })

        mainViewModel.currentPagerPosition.observe(this, Observer {
            if(it != null){
                try {
                    guide1.currentItem = it.first
                    guide2.currentItem = it.second
                }catch (e : Exception){
                    e.printStackTrace()
                }
            }
        })
    }

    private fun addViewPageChangeListeners() {
        guide1.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                 mainViewModel.clothItemSelectionChanaged(position,guide2.currentItem)
            }

        })

        guide2.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                mainViewModel.clothItemSelectionChanaged(guide1.currentItem,position)
            }

        })

    }

    override fun inject() {
        DaggerActivityBuilder.builder().viewModelModule(ViewModelModule(this,null))
            .presenterModule(PresenterModule(this,null))
            .build().inject(this)
    }



    override fun getViewModel(): BaseViewModel<*> {
        return mainViewModel
    }





}
