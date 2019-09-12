package org.sushobh.exampleapp.Base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import java.util.*


abstract class BaseActivity : AppCompatActivity() {


    abstract fun inject()
    abstract fun getViewModel() : BaseViewModel<*>
    lateinit var baseViewModel: BaseViewModel<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        baseViewModel = getViewModel()
    }


    fun displayOptionAlertDialog( title : String ,  options : Array<String>) : Single<Int>{

        return Single.create(SingleOnSubscribe<Int> {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(title)
            builder.setItems(options, DialogInterface.OnClickListener { dialog, which ->
                it.onSuccess(which)
            })
            builder.show()
        })

    }

    override fun onResume() {
        baseViewModel.onResume()
        super.onResume()
    }

    override fun onStart() {
        baseViewModel.onStart()
        super.onStart()
    }
}