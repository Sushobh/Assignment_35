package org.sushobh.exampleapp.Base

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.mlsdev.rximagepicker.RxImagePicker
import com.mlsdev.rximagepicker.Sources
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import java.lang.Exception
import java.util.*
import java.util.concurrent.Callable

open class BasePresenter(var baseActivity: BaseActivity?)  {



    fun setTitle(title : String){
        runActivityMethod(object  : ActivityMethod{
            override fun execute() {
                baseActivity?.supportActionBar?.title = title;
            }
        })
    }

    fun showToast(message : String){
        Toast.makeText(baseActivity,message,Toast.LENGTH_SHORT).show()
    }

    fun showFullScreen(){
        baseActivity?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        baseActivity?.getWindow()?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    fun showBackArrow(){
        baseActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    interface ActivityMethod {
        fun execute();
    }

    fun runActivityMethod(activityMethod: ActivityMethod){
        activityMethod.execute()
    }

    fun pickImage(chooserTitle : String): Observable<Uri>? {
      return  baseActivity?.displayOptionAlertDialog(chooserTitle, arrayOf("Camera","Gallery"))
            ?.flatMapObservable {
                if(it == 0){
                    RxImagePicker.with(baseActivity!!.supportFragmentManager).requestImage(Sources.CAMERA)
                } else {
                    RxImagePicker.with(baseActivity!!.supportFragmentManager).requestImage(Sources.GALLERY)
                }
            }
    }

    fun closeKeyboard(){
        val view = baseActivity?.getCurrentFocus()
        if (view != null) {
            val imm = baseActivity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
        }
    }

}