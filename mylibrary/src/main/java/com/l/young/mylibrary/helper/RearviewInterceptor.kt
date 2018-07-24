import android.util.Log

import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

/**
 * Created by Administrator on 2018/2/8.
 */
class RearviewInterceptor {
    companion object {
        const val TAG = "API_"

    }
    class LogInterceptor : Interceptor {
        override fun intercept(chian: Interceptor.Chain?): Response {
            //add header
            val builder = chian?.request()?.newBuilder()
            builder?.apply {
                addHeader("Content-Type", "application/json")
                addHeader("Accept", "application/json")
                addHeader("msgId", UUID.randomUUID().toString())
                addHeader("from", "MOBILE")
            }
            val request = builder?.build()
            val url = request?.url()
            val connection = chian?.connection()
            val headers = request?.headers()
            Log.d(TAG, "method = ${request?.method()} body = ${request?.log()}" +
                    "\r\nurl = $url " +
                    "\r\nconnection = $connection" +
                    "\r\nheaders = $headers")
            val response = chian?.proceed(request)
            val peekBody = response?.peekBody(1024 * 1024)
            val headers1 = response?.headers()
            response?.log()
            Log.d(TAG, "peeekBody = $peekBody  headers1 = $headers1")
            return response!!
        }


    }

    class LogInterceptorWithToken : Interceptor {
        override fun intercept(chian: Interceptor.Chain?): Response {
            //add header
            val builder = chian?.request()?.newBuilder()
            builder?.apply {
                addHeader("Content-Type", "application/json")
                addHeader("Accept", "application/json")
                addHeader("msgId", UUID.randomUUID().toString())
                addHeader("from", "MOBILE")
            }
/*
           val token = ShareHelper.getInstance(RearviewApplication.getmContext()).getCurrentToken()
            LogUtils.d("token", "token = " + token)
            builder?.addHeader("token", token)
*/

            val request = builder?.build()
            val url = request?.url()
            val connection = chian?.connection()
            val headers = request?.headers()
            Log.d(TAG, "method = ${request?.method()} body = ${request?.log()}" +
                    "\r\nurl = $url " +
                    "\r\nconnection = $connection" +
                    "\r\nheaders = $headers")
            val response = chian?.proceed(request)
            response?.log()
            return response!!
        }
    }

    class LogInterceptorFile : Interceptor {
        override fun intercept(chian: Interceptor.Chain?): Response {
            //add header
            val builder = chian?.request()?.newBuilder()
            builder?.apply {
                addHeader("Content-Type", "application/json")
                addHeader("Accept", "application/json")
                addHeader("msgId", UUID.randomUUID().toString())
                addHeader("from", "MOBILE")
            }
            val token = ShareHelper.getInstance(RearviewApplication.getmContext()).getCurrentToken()
            builder?.addHeader("token", token)
            val request = builder?.build()
            val url = request?.url()
            val connection = chian?.connection()
            val headers = request?.headers()
            Log.d(TAG, "method = ${request?.method()} body = ${request?.log()}" +
                    "\r\nurl = $url " +
                    "\r\nconnection = $connection" +
                    "\r\nheaders = $headers")
            val response = chian?.proceed(request)
            response?.log()
            return response!!

        }
    }
}