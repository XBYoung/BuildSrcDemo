package iot.chinamobile.rearview.service

import android.os.CountDownTimer
import android.os.Handler
import com.l.young.mylibrary.EasyApp
import com.l.young.mylibrary.R

/**
 * Created by luke on 2016/11/9.
 */
class CodeTimer(millisInFuture: Long, countDownInterval: Long, private val mHandler: Handler?) : CountDownTimer(millisInFuture, countDownInterval) {

    override fun onTick(millisUntilFinished: Long) {
        if (mHandler != null) {
            val message = mHandler.obtainMessage()
            message.what = IN_RUNNING
            message.obj = (millisUntilFinished / 1000).toString() + "s"
            mHandler.sendMessage(message)
            //   mHandler.obtainMessage(IN_RUNNING, (millisUntilFinished / 1000) + "秒后重发").sendToTarget();
        }
    }

    override fun onFinish() {
        if (mHandler != null) {
            val message = mHandler.obtainMessage()
            message.what = END_RUNNING
            message.obj = EasyApp.getContext().getString(R.string.send_code)
            mHandler.sendMessage(message)
            //    mHandler.obtainMessage(END_RUNNING, "获取验证码").sendToTarget();
        }
    }

    companion object {
        val IN_RUNNING = 1001
        var END_RUNNING = 1002
    }
}
