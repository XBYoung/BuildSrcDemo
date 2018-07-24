package com.l.young.easyApp

import android.app.Application
import com.l.young.mylibrary.EasyApp

class MyApplication:Application(){
    override fun onCreate() {
        super.onCreate()
        EasyApp.init(this)
    }
}
