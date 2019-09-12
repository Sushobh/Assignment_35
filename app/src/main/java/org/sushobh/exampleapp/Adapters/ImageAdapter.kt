package org.sushobh.exampleapp.Adapters

import android.widget.LinearLayout
import android.view.ViewGroup

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso
import org.sushobh.exampleapp.R
import org.sushobh.exampleapp.Room.Entity.ClothItem
import androidx.viewpager.widget.ViewPager





class ImageAdapter : PagerAdapter {


     var context: Context
     var mLayoutInflater : LayoutInflater
     var imageList : List<ClothItem>

    constructor(context: Context,imageList : List<ClothItem>) : super(){
        this.context = context
        mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        this.imageList = imageList
    }

    override fun getCount(): Int {
        Log.i("returningCount",""+imageList.size)
        return imageList.size
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ImageView
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = mLayoutInflater.inflate(org.sushobh.exampleapp.R.layout.pager_item, container, false)
        as ImageView
        Picasso.with(context).load(Uri.parse(imageList[position].fileUri))
            .fit().centerCrop()
            .into(itemView)
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    override fun destroyItem(container: View, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }
}