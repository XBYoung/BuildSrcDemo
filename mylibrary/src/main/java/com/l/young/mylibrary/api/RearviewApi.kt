package com.l.young.mylibrary.api


import android.database.Observable
import iot.chinamobile.rearview.helper.CustomConverterFactory
import iot.chinamobile.rearview.helper.NullOnEmptyConverterFactory
import iot.chinamobile.rearview.model.BASE_URL
import iot.chinamobile.rearview.model.bean.*
import okhttp3.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.security.cert.X509Certificate
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

/**
 * Created by Administrator on 2018/2/24.
 */
interface RearviewApi {
    @POST("/api/v0/sms-verifiction-codes")
    fun sendRegistSMS(@Body body: SMSCodeRequest): Call<ResponseBody>

    @POST("/api/v0/verify-sms-code")
    fun verifyCode(@Body body: VerifyCode): Call<ResponseBody>

    @POST("/api/v0/users/sms-login")
    fun loginBySMS(@Body body: LoginSMSRequest): Call<UserBean>

    @POST("/api/v0/users/reset-password")
    fun resetPass(@Header("token") token: String, @Body body: ResetPassRequest): Call<ResponseBody>

    @POST("/api/v0/users/pwd-login")
    fun loginByPWD(@Body body: LoginPWDRequest): Call<UserBean>


    @POST("/api/v0/users/logout")
    fun logout(@Header("token") token: String, @Body body: NullRequest): Call<ResponseBody>

    @POST("/api/v0/users/{uuid}")
    fun modifyUserInfo(@Path("uuid") uuid: String, @Body body: ModifyUserRequest): Call<ModifyUserResult>

    @Multipart
    @POST("/zuul/api/v0/images")
    fun upLoadImag(@Part("imageUploadType") parma: RequestBody, @Part file: MultipartBody.Part): Call<UploadImgResult>

    @POST("/api/v0/users/modify-password")
    fun modifyPass(@Body body: ModifyPassRequest): Call<ResponseBody>

    /**
     * 用户的后视镜
     */
    @GET("/api/v0/users/{uuid}/bind")
    fun getUserTerminals(@Path("uuid") uuid: String, @QueryMap map: MutableMap<String, Any>): Call<BindTerminalResullt>

    @GET("/api/v0/users/{uuid}")
    fun getUserInfo(@Path("uuid") uuid: String, @QueryMap map: MutableMap<String, Any>): Call<UserBean>

    /**
     * 验证需要的后视镜
     */
    @GET("/api/v0/terminals")
    fun getTheTerminals(@QueryMap map: MutableMap<String, Any>): Call<TheTerminalResult>

    @POST("/api/v0/users/{uuid}/terminals/{sn}")
    fun bindTerminal(@Path("uuid") uuid: String, @Path("sn") sn: String, @Body body: NullRequest): Call<ResponseBody>

    /**
     * 获取品牌列表
     */
    @GET("/api/v0/vehicles/brands")
    fun getCarList(@QueryMap map1: MutableMap<String, Any>): Call<CarListResult>

    /**
     * 品牌子型号
     */
    @GET("/api/v0/vehicles/brands/{uuid}/models")
    fun getBrandModels(@Path("uuid") uuid: String, @QueryMap map: MutableMap<String, Any>): Call<GetVehicleModelsResponse>

    /**
     * 绑定车辆到终端
     */
    @POST("/api/v0/terminals/{sn}/vehicles")
    fun bindCarToTerminal(@Path("sn") sn: String, @Body body: BindCarToTerminalRequest): Call<NoBodyEntity>

    @POST("/api/v0/users/{uuid}/terminals/{sn}/delete")
    fun deleteTerminal(@Path("uuid") uuid: String, @Path("sn") sn: String, @Body body: NullRequest): Call<ResponseBody>

    /**
     * 发送导航
     */
    @POST("/api/v0/terminals/{sn}/navigation")
    fun sendNavigation(@Path("sn") sn: String, @Body body: SendNavigationRequest): Call<ResponseBody>

    @GET("/api/v0/terminals/{sn}/realtime")
    fun getTerminalRealTime(@Path("sn") sn: String, @QueryMap map1: MutableMap<String, Any>): Call<TerminalRealTimeResult>

    @GET("/api/v0/terminals/{tuuid}/trackstatistics")
    fun getTrackStatistics(@Path("tuuid") sn: String, @QueryMap map: MutableMap<String, Any>): Call<TrackStatics>

    @GET("/api/v0/trackstatistics/{uuid}/tracks")
    fun getTrackAndEvents(@Path("uuid") uuid: String, @QueryMap map: MutableMap<String, Any>): Call<TrackAndEvents>

    @GET("access_token")
    fun getTokenForWXlogin(@QueryMap map: MutableMap<String, Any>): io.reactivex.Observable<WXTokenResult>

    /* @GET("access_token")
     fun getTokenForWXlogin(@QueryMap map: MutableMap<String, Any>): Call<WXTokenResult>*/
    @POST("/api/v0/wechat-login")
    fun loginByWX(@Body body: WXloginRequest): Call<UserBean>

    @POST("/api/v0/mobile-exist-bind")
    fun bindWxByHadAccount(@Body body: WXBindbyHadAccountRequest): Call<UserBean>

    @POST("/api/v0/mobile-register-bind")
    fun binWxByNewAccount(@Body body: WXBindbyNewAccountRequest): Call<UserBean>

    @POST("/api/v0/users/{uuid}/feedback")
    fun feedback(@Path("uuid") uuid: String, @Body body: FeedbackRequest): Call<ResponseBody>

    @GET("/v0/resources")
    fun getVideo_Album(@QueryMap param:MutableMap<String,Any?>): Call<VedioAndAlbumResult>

    /**
     * 获取终端状态
     */
    @GET("/v0/device-status")
    fun getConnectedStatus():Call<ConnectedStatusResult>

    @GET("/api/v0/terminals/{tuuid}/videos")
    fun getVideo_terminal(@Path("tuuid") tuuid: String, @QueryMap map: MutableMap<String, Any>): Call<Video_Terminal>

    /**
     * 开始直播请求
     */
    @POST("/api/v0/terminals/{tuuid}/monitor-start")
    fun startReal(@Path("tuuid") tuuid: String, @Body body: RealTimeRequest): Call<StartTerminalMonitorResponse>

    /**
     * 停止直播请求
     */
    @POST("/api/v0/terminals/{tuuid}/monitor-stop")
    fun stopReal(@Path("tuuid") tuuid: String, @Body body: NullRequest): Call<ResponseBody>

    @GET("/api/v0/updates/mobileapp-packages/check")
    fun checkUpdate(@QueryMap map: MutableMap<String, Any>): Call<UpdateResult>

    /**
     * 下载文件
     */
    @Streaming
    @GET
    fun downFile(@Url url: String): Call<ResponseBody>

    /**
    /zuul/api/v0/images,
    /zuul/api/v0/terminals/import,
    /zuul/api/v0/organizations/{ uuid }/terminals/import,
    /zuul/api/v0/updates/mobileapp-packages/upload,
    /zuul/api/v0/updates/terminal-packages/upload,
    /zuul/api/v0/messages/users/upload
     */
    /**
     * 构造请求实例
     */
    class ApiFactory {
        constructor()

        /**
         * Https 信任所有证书
         */
        fun httpsClient(): OkHttpClient.Builder {
            val client = OkHttpClient()
            val builder = client.newBuilder()
            builder.hostnameVerifier({ _, _ -> true })
            var trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(java.security.cert.CertificateException::class)
                override fun checkClientTrusted(
                        x509Certificates: Array<java.security.cert.X509Certificate>,
                        s: String) {
                }

                @Throws(java.security.cert.CertificateException::class)
                override fun checkServerTrusted(
                        x509Certificates: Array<java.security.cert.X509Certificate>,
                        s: String) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            })


            try {
                //构造自己的SSLContext
                val sc = SSLContext.getInstance("TLS")
                sc.init(null, trustAllCerts, java.security.SecureRandom())
                builder.sslSocketFactory(sc.socketFactory)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            builder.protocols(Collections.singletonList(Protocol.HTTP_1_1))
                    .build()

            return builder

        }

        /**
         * 默认的带基本配置带BaseURL
         */
        private inline fun init(builder: OkHttpClient.Builder, mutableUrl: String? = null): RearviewApi {
            builder.readTimeout(15, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
            val retrofit = Retrofit.Builder().baseUrl(mutableUrl ?: BASE_URL)
                    .addConverterFactory(NullOnEmptyConverterFactory())
                    .addConverterFactory(CustomConverterFactory.create()) //post 加密
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(builder.build())
                    .build()
            return retrofit.create(RearviewApi::class.java)
        }

        /**
         * 初始化未加密服务
         */
        private inline fun initNoDec(builder: OkHttpClient.Builder, mutableUrl: String? = null): RearviewApi {
            builder.readTimeout(15, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
            val retrofit = Retrofit.Builder().baseUrl(mutableUrl ?: BASE_URL)
                    // .addConverterFactory(CustomConverterFactory.create()) //post 加密
                    .addConverterFactory(NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(builder.build())
                    .build()
            return retrofit.create(RearviewApi::class.java)
        }

        /**
         * 无token
         */
        fun createApi(): RearviewApi {
            val builder = httpsClient()
                    .addInterceptor(RearviewInterceptor.LogInterceptor())
            return init(builder)
        }

        /**
         *带token
         */
        fun createApiWithToken(): RearviewApi {
            val builder = httpsClient()
                    .addInterceptor(RearviewInterceptor.LogInterceptorWithToken())
            return init(builder)
        }

        /**
         * 图片/文件
         */
        fun createApiUpFile(): RearviewApi {
            val builder = httpsClient()
                    .addInterceptor(RearviewInterceptor.LogInterceptorFile())
            return init(builder)
        }

        /**
         * 从终端获取数据
         */
        fun createRear(mutableUrl: String): RearviewApi {
            val builder = httpsClient()
                    .addInterceptor(RearviewInterceptor.LogInterceptor())
            return initNoDec(builder, mutableUrl)
        }


    }
}