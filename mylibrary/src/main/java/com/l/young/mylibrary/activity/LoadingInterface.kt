package com.l.young.mylibrary.activity

interface LoadingInterface {
    fun showLoading(title:String = "",body:String,cancelble:Boolean)
    fun hideLoading()
}