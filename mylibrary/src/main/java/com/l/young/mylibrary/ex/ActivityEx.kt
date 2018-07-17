package com.l.young.mylibrary.ex

import android.app.Activity
import android.widget.Toast
import com.l.young.mylibrary.EasyApp

fun  Activity.showToast(msg:Any?){
    Toast.makeText(this,msg.toString(),Toast.LENGTH_SHORT).show()
}

 fun Activity.showToast(msg:Any?,gravity: Int){
    val toast  = Toast(this)
    toast.setText(msg.toString())
    toast.duration = Toast.LENGTH_SHORT
    toast.setGravity(gravity,0,0)
    toast.show()
}
fun  Activity.showLongToast(msg:Any?){
    Toast.makeText(this,msg.toString(),Toast.LENGTH_SHORT).show()
}

 fun Activity.showLongToast(msg:Any?,gravity: Int){
    val toast  = Toast(this)
    toast.setText(msg.toString())
    toast.duration = Toast.LENGTH_SHORT
    toast.setGravity(gravity,0,0)
    toast.show()
}