package com.l.young.mylibrary.helper

import android.content.Context
import android.content.SharedPreferences
import iot.chinamobile.rearview.model.*
import iot.chinamobile.rearview.model.bean.User
import iot.chinamobile.rearview.model.bean.UserBean
import iot.chinamobile.rearview.util.DecUtils
import iot.chinamobile.rearview.util.LogUtils
import java.util.*

/**
 * Created by Administrator on 2018/2/13.
 */
class ShareHelper private constructor(context: Context) {
    val sp: SharedPreferences by lazy {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
    }
    lateinit var editer: SharedPreferences.Editor

    init {
        println(context)
    }

    companion object {
        private var instance: ShareHelper? = null
        fun getInstance(context: Context): ShareHelper {
            synchronized(ShareHelper::class.java) {
                if (instance == null) {
                    instance = ShareHelper(context)
                }
                return instance!!
            }
        }
    }


    inline fun <reified T> String.sava(value: T) {
        editer = sp.edit()
        value?.let {
            when (it) {
                is String -> editer.putString(this, getBase64(it))
                is Boolean -> editer.putString(getBase64(this), getBase64(it.toString()))
                is Long -> editer.putString(getBase64(this), getBase64(it.toString()))
                is Float -> editer.putString(getBase64(this), getBase64(it.toString()))
                is Int -> editer.putString(getBase64(this), getBase64(it.toString()))
                else -> {
                    throw IllegalAccessException("value = $value can't be used!")
                }
            }
            editer.commit()
        }

    }

    inline fun getFromBase64(key: String): String {
        return DecUtils.getFromBASE64(key)!!
    }

    inline fun getBase64(key: String): String {
        return DecUtils.getBase64(key)!!
    }

    inline fun <reified T> String.getValue(): T {
        when (T::class.java) {
            String::class.java -> return getFromBase64(sp.getString(this, "")) as T
            Int::class.java -> return getFromBase64(sp.getString(getFromBase64(this), "")).toInt() as T
            Long::class.java -> return getFromBase64(sp.getString(getFromBase64(this), "")).toLong() as T
            Float::class.java -> return getFromBase64(sp.getString(getFromBase64(this), "")).toFloat() as T
            Boolean::class.java -> return getFromBase64(sp.getString(getFromBase64(this), "")).toBoolean() as T
            else -> {
                throw IllegalAccessError("type = ${T::class.java} can't be used!")
            }
        }
    }

    /**
     * const val ADDRESS = "address"
    const val BIRTHDAY = "birthday"
    const val DRIVINGLICENSE = "drivingLicense"
    const val EMAIL = "email"
    const val GENDER = "gender"
    const val IDCARD = "idCard"
    const val IDTYPE = "idType"
    const val MARRIAGE =  "marriage"
    const val MOBILE =  "mobile"
    const val NICKNAME =  "nickName"
    const val STATUSTYPE = "statusType"
    const val USERHEADIMAGES = "userHeadImages"
    const val USERUUID = "userUUID"
    const val FIRSTLOGIN =  "firstLogin"
    const val TOKEN = "token"
     */
    open fun saveUser(user: User) {
        ADDRESS.sava(user.address)
        BIRTHDAY.sava(user.birthday)
        DRIVINGLICENSE.sava(user.drivingLicense)
        EMAIL.sava(user.email)
        GENDER.sava(user.gender)
        IDCARD.sava(user.idCard)
        IDTYPE.sava(user.idType)
        MARRIAGE.sava(user.marriage)
        MOBILE.sava(user.mobile)
        NICKNAME.sava(user.nickName)
        STATUSTYPE.sava(user.statusType)
        USERHEADIMAGES.sava(user.userHeadImages[0])
        USERUUID.sava(user.userUUID)
    }

    fun saveUserAfterLogin(user: UserBean) {
        MOBILE.sava(user.user.mobile)
        USERUUID.sava(user.user.userUUID)
        TOKEN.sava(user.token)


    }

    fun clearAfterLogOut() {
        MOBILE.sava("")
        USERUUID.sava("")
        TOKEN.sava("")
    }

    fun saveCurrentAccount(value: String) {
        MOBILE.sava(value)
        LogUtils.d(TAG, "saveAccount = $value")


    }

    fun saveCurrentUUid(value: String) {
        USERUUID.sava(value)
    }

    fun getCurrentUUid(): String {
        return USERUUID.getValue<String>()
    }

    fun saveCurrentToken(value: String) {
        TOKEN.sava(value)
    }

    fun getCurrentToken(): String {
        return TOKEN.getValue<String>()
    }

    fun getCurrentAccount(): String {
        return MOBILE.getValue<String>().apply {
//            LogUtils.d(TAG, "getValue = $this")
        }
    }
    /*open fun getUser(): User {
        val mutableMap = sp.all

    }*/


}