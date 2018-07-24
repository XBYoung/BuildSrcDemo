package iot.chinamobile.rearview.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder


/**
 * Created by luke on 2016/11/9.
 */
class TimerService : Service() {


    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onStart(intent: Intent, startId: Int) {
        super.onStart(intent, startId)
        codeTimer = CodeTimer(60000, 1000, mHandler)
        codeTimer!!.start()
    }

    companion object {
        private var mHandler: Handler? = null
        private var codeTimer: CodeTimer? = null

        fun setmHandler(handler: Handler) {
            mHandler = handler
        }
    }
}
