package com.l.young.easyApp

import android.os.Bundle
import com.flyco.tablayout.listener.CustomTabEntity
import com.l.young.easyApp.fragments.HomeFragment
import com.l.young.mylibrary.activity.BaseActivity
import com.l.young.mylibrary.activity.BaseFragment
import com.l.young.mylibrary.bean.TabEntity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : BaseActivity() {
    internal var mFragmentList = ArrayList<BaseFragment>()
    internal var mTabEntities = ArrayList<CustomTabEntity>()
    //Fragments
    internal var clsArray = arrayOf<Class<out BaseFragment>>(
            HomeFragment::class.java,
            HomeFragment::class.java,
            HomeFragment::class.java)
    //标题
    lateinit internal var titles: Array<String>
    //未选中图标
    lateinit internal var normals: IntArray
    //选中图标
    lateinit internal var selectedDrawable: IntArray
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTab()

    }
    private fun initTab(){
        titles = arrayOf("tab1", "tab2","tab3")
        normals = intArrayOf(R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher)
        selectedDrawable = intArrayOf(R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round)
        mFragmentList = addFragmentList(R.id.fl_content, clsArray )
        showFragment(mFragmentList, 0)
        for (i in titles.indices) {
            mTabEntities.add(TabEntity(titles[i], selectedDrawable[i], normals[i]))
        }

        main_Group.setTabData(mTabEntities)
    }
}
