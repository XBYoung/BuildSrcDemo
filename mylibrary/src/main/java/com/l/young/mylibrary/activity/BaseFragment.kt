package com.l.young.mylibrary.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by idea on 2016/11/30.
 */
abstract class BaseFragment : Fragment(), ResInterface, LoadingInterface {
    private var contentView: View? = null


    fun applyTheme() {

    }

    override fun showLoading(title: String, body: String, cancelble: Boolean) {

    }

    override fun hideLoading() {

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (contentView == null) {
            contentView = inflater.inflate(getLayoutId(), null)
        }
        return contentView
    }
}
