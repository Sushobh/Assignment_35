package org.sushobh.exampleapp.Base

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(){


    abstract fun inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

}