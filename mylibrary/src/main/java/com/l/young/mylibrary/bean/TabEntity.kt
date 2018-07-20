package com.l.young.mylibrary.bean

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * Created by idea on 2016/5/16.
 */
class TabEntity(var title: String, var selectedIcon: Int, var unSelectedIcon: Int) : CustomTabEntity {

    override fun getTabTitle(): String {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }
}

