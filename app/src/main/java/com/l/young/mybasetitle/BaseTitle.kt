package iot.chinamobile.model.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.l.young.mybasetitle.R


/**
 * Created by luke on 2017/1/18.
 */
class BaseTitle : RelativeLayout {
    private var isCanback: Boolean = false
    private var isSetting: Boolean = false
    private var isTextSetting: Boolean = false
    private var title: String? = null
    private var textSetting: String? = null
    private var settingRes: Int = 0
    private var backRes: Int = 0
    private var contentColor: Int = 0
    private var backColor: Int = 0
    private var titleSize: Int = 0
    private var tvTitle: TextView? = null
    private var tvSetting: TextView? = null
    private var titleView: TitleView? = null
    private val rl_root: RelativeLayout? = null
    private lateinit var mContext: Context
    internal lateinit var backBtn: ImageView
    internal lateinit var setting: ImageView

    constructor(context: Context) : super(context, null) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mContext = context
        LayoutInflater.from(context).inflate(R.layout.title, this)
        val ta = context.obtainStyledAttributes(attrs, R.styleable.BaseTitle, 0, 0)
        try {
            title = ta.getString(R.styleable.BaseTitle_title_text)
            textSetting = ta.getString(R.styleable.BaseTitle_setting_text)
            isCanback = ta.getBoolean(R.styleable.BaseTitle_isCanBack, false)
            isSetting = ta.getBoolean(R.styleable.BaseTitle_isSetting, false)
            isTextSetting = ta.getBoolean(R.styleable.BaseTitle_isTextSetting, false)
            settingRes = ta.getResourceId(R.styleable.BaseTitle_setting_res, 0)
            backRes = ta.getResourceId(R.styleable.BaseTitle_back_res, R.mipmap.back)
            backColor = ta.getResourceId(R.styleable.BaseTitle_title_background, R.color.colorAccent)
            titleSize = ta.getDimensionPixelSize(R.styleable.BaseTitle_title_size, sp2px(context, 18))
            contentColor = ta.getResourceId(R.styleable.BaseTitle_title_content_color, R.color.c333333)
            setUpView()
        } finally {
            ta.recycle()
        }

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs) {}

    @SuppressLint("WrongConstant")
    private fun setUpView() {
        tvTitle = this.findViewById(R.id.title_tv_content)
        tvTitle!!.text = title
        tvTitle!!.textSize = px2sp(mContext, titleSize.toFloat()).toFloat()
        tvTitle!!.setTextColor(context.resources.getColor(contentColor))
        tvSetting = this.findViewById(R.id.title_tv_setting)
        tvSetting!!.text = textSetting
        tvSetting!!.visibility = if (isTextSetting) View.VISIBLE else View.GONE
        tvSetting!!.setTextColor(context.resources.getColor(contentColor))
        backBtn = findViewById(R.id.title_iv_back)
        backBtn.visibility = if (isCanback) View.VISIBLE else View.INVISIBLE

        backBtn.setOnClickListener {
            // ((Activity) getContext()).finish();
            if (titleView != null) {
                titleView!!.doBack()
            }
            //(mContext as Activity).finish()
        }

        if (settingRes != 0) {
            val moreImgView = findViewById<ImageView>(R.id.title_iv_setting)
            moreImgView.visibility = View.INVISIBLE
        }
        backBtn.setImageResource(backRes)
        setting = findViewById<ImageView>(R.id.title_iv_setting)
        setting.visibility = if (isSetting) View.VISIBLE else View.GONE

        setting.setImageResource(settingRes)

        setting.setOnClickListener {
            titleView?.doSetting()
        }

        tvSetting!!.setOnClickListener {
            titleView?.doSetting()
        }

        this.setBackgroundResource(backColor)
    }


    private fun sp2px(context: Context, spValue: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue.toFloat(), context.resources.displayMetrics).toInt()
    }

    /**
     * 设置title接口
     * @param view
     */
    fun setTitleView(view: TitleView) {
        this.titleView = view
    }

    fun setTitleView(back: () -> Unit?, setting: () -> Unit?) {
        this.titleView = object : TitleView {
            override fun doSetting() {
                setting?.invoke()
            }

            override fun doBack() {
                back?.invoke()
            }
        }
    }

    fun doBackAndSetting(back: () -> Unit, setting: () -> Unit) {
        this.titleView = object : TitleView {
            override fun doSetting() {
                setting()
            }

            override fun doBack() {
                back()
            }
        }
    }

    fun doBack(back: () -> Unit) {
        this.titleView = object : TitleView {
            override fun doSetting() {

            }

            override fun doBack() {
                back()
            }
        }
    }

    fun doSetting(setting: () -> Unit) {
        this.titleView = object : TitleView {
            override fun doSetting() {
                setting()
            }

            override fun doBack() {
            }
        }
    }

    @SuppressLint("WrongConstant")
            /**
     * 返回键是否可见
     * @param visible
     */
    fun setBackVisible(visible: Boolean) {
        backBtn.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    @SuppressLint("WrongConstant")
            /**
     * setting字样是否可见
     * @param visible
     */
    open fun setSettingVisible(visible: Boolean) {
        setting?.visibility = if (visible) View.VISIBLE else View.INVISIBLE
        tvSetting?.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    @SuppressLint("WrongConstant")
    fun settingTextVisible() {
        tvSetting?.visibility = View.VISIBLE
        setting?.visibility = View.GONE
    }

    @SuppressLint("WrongConstant")
    fun settingResVisible() {
        tvSetting?.visibility = View.GONE
        setting?.visibility = View.VISIBLE

    }

    /**
     * title内容
     * @param title
     */
    fun setTitleText(title: String) {
        tvTitle!!.text = title
    }

    fun setSettingText(setting: String) {
        tvSetting?.text = setting
    }

    companion object {

        fun px2sp(context: Context, pxValue: Float): Int {
            val fontScale = context.resources.displayMetrics.scaledDensity
            return (pxValue / fontScale + 0.5f).toInt()
        }
    }

}
