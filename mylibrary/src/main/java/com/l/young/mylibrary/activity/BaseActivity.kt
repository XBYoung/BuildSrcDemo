package com.l.young.mylibrary.activity

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.afollestad.materialdialogs.MaterialDialog
import com.l.young.mylibrary.utils.ActivityManagerUtils
import java.io.Serializable
import java.util.ArrayList


/**
 * Created by idea on 2016/11/30.
 */
abstract class BaseActivity : AppCompatActivity(), LoadingInterface {

    private lateinit var loadDialog: MaterialDialog
    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    val version: String?
        get() {
            try {
                val manager = this.packageManager
                val info = manager.getPackageInfo(this.packageName, 0)
                return info.versionName
            } catch (e: Exception) {

                return null
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyTheme()
        ActivityManagerUtils.instance().addActivity(this)
        initView()
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        changeScreenOrientation()
        //  IntentFilter filter = new IntentFilter("com.iot.monitoring.jpush.dialog");
        super.onResume()
    }

    fun changeScreenOrientation() {
        if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    override fun onPause() {

        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    /**
     * 检查是否有语音录制权限
     *
     * @return
     */
    fun checkRecordVoicePermisson(): Boolean {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
            return false
        } else {
            return true
        }
    }

    /**
     * 检查是否有读写权限
     *
     * @return
     */
    fun checkReadWritePermisson(): Boolean {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            return false
        } else {
            return true
        }
    }


    /*************************************BaseInterface ContextInterface */

    /**
     * example
     * requestWindowFeature(Window.FEATURE_NO_TITLE);
     * getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
     */

    fun applyTheme() {}


    fun initView() {}


    /*************************************FragmentInterface */


    fun <T : BaseFragment> addFragment(frameLayoutId: Int, cls: Class<T>): T? {
        var t: T? = null
        val mFragmentManager = supportFragmentManager
        val mTransaction = mFragmentManager.beginTransaction()
        try {
            t = cls.newInstance()
            if (!t!!.isAdded) {
                mTransaction.add(frameLayoutId, t, cls.name).hide(t)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mTransaction.commit()
        return t
    }

    fun <T : BaseFragment> addFragmentList(frameLayoutId: Int, clsArray: Array<Class<T>>): ArrayList<T> {
        val mFragmentList = ArrayList<T>()
        val mFragmentManager = supportFragmentManager
        val mTransaction = mFragmentManager.beginTransaction()

        for (i in clsArray.indices) {
            var t: T? = null
            try {
                t = clsArray[i].newInstance()
                if (!t!!.isAdded) {
                    mTransaction.add(frameLayoutId, t, clsArray[i].name).hide(t)
                }
                mFragmentList.add(t)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        mTransaction.commit()
        return mFragmentList
    }

    fun <T : BaseFragment> showFragment(fragmentList: ArrayList<T>, showPosition: Int) {
        val mFragmentManager = supportFragmentManager
        val mTransaction = mFragmentManager.beginTransaction()
        for (i in fragmentList.indices) {
            if (i == showPosition) {
                Log.d("MainActivity", "showFragment position is$showPosition")
                mTransaction.show(fragmentList[showPosition])
            } else {
                mTransaction.hide(fragmentList[i])
                Log.d("MainActivity", "hideFragment position is$i")
            }
        }
        mTransaction.commit()
    }

    fun <T : BaseFragment> hideFragment(fragmentList: ArrayList<T>, hidePosition: Int) {
        val mFragmentManager = supportFragmentManager
        val mTransaction = mFragmentManager.beginTransaction()
        mTransaction.hide(fragmentList[hidePosition])
        mTransaction.commit()
    }

    fun <T : BaseFragment> replaceFragment(frameLayoutId: Int, cls: Class<T>) {
        val mFragmentManager = supportFragmentManager
        val mTransaction = mFragmentManager.beginTransaction()

        var fragment: T? = null
        try {
            fragment = cls.newInstance()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        if (fragment != null) {
            mTransaction.replace(frameLayoutId, fragment)
        }
        mTransaction.commit()
    }

    /**
     * 获取当前显示Fragment
     *
     * @param mFragmentList
     * @return
     */
    private fun <T : BaseFragment> getVisibleFragment(mFragmentList: ArrayList<T>): T? {
        for (fragment in mFragmentList) {
            if (fragment != null && fragment.isVisible)
                return fragment
        }
        return null
    }

    fun isShowFragment(list: ArrayList<BaseFragment>, fragmentCls: Class<*>): Boolean {
        for (fragment in list) {
            if (fragment != null && fragment.isVisible && fragment.javaClass == fragmentCls)
                return true
        }
        return false
    }

    override fun showLoading(title: String, body: String, cancelable: Boolean) {
        loadDialog = MaterialDialog.Builder(this).title(title).content(body).cancelable(cancelable).build()
        loadDialog.show()
    }

    override fun hideLoading() {
        loadDialog?.dismiss()
    }



    fun getIntent(cls: Class<out BaseActivity>): Intent {
        return Intent(this, cls)
    }

    /**
     * @param intent
     * @param key
     * @param value
     * @hide
     */
    fun putExtra(intent: Intent, key: String, value: Any) {
        if (value is String) {
            intent.putExtra(key, value)
        } else if (value is Int) {
            intent.putExtra(key, value)
        } else if (value is Boolean) {
            intent.putExtra(key, value)
        } else if (value is Serializable) {
            intent.putExtra(key, value)
        }
    }

    /**
     * @param intent
     * @param value
     * @hide
     */
    fun putExtra(intent: Intent, value: Any) {
        if (value is String) {
            intent.putExtra("stringValue", value)
        } else if (value is Int) {
            intent.putExtra("intValue", value)
        } else if (value is Boolean) {
            intent.putExtra("booleanValue", value)
        } else if (value is Serializable) {
            intent.putExtra("serializableValue", value)
        }
    }

    /**
     * @param cls
     * @param data
     * @hide
     */
    private fun startActivityWithData(cls: Class<out BaseActivity>, data: Any) {
        val intent = getIntent(cls)
        putExtra(intent, data)
        startActivity(intent)
    }

    companion object {
        private val TAG = "TSPAPP_TAG"
    }

}
