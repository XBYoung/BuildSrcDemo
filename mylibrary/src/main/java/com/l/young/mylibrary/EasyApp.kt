package com.l.young.mylibrary

import android.content.Context

object EasyApp {
   private lateinit var context:Context
    fun init(context: Context){
        this.context = context
    }
    fun getContext():Context{
        context?.let {
            return it
        }?:throw NullPointerException("未初始化EasyApp")
    }
}