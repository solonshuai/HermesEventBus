package com.zxy.mytsfqxproject.activity

import android.annotation.SuppressLint
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.ConfigurationInfor
import kotlinx.android.synthetic.main.activity_help.*
import kotlinx.android.synthetic.main.top_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HelpActivity : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_help

    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "帮助"
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun start() {
        rc_webview.settings.javaScriptEnabled = true
        rc_webview.webViewClient = WebViewClient()
        rc_webview.webChromeClient = WebChromeClient()
        RetrofitManager.service.tsfDefault().enqueue(object : Callback<ConfigurationInfor> {
            override fun onFailure(call: Call<ConfigurationInfor>, t: Throwable) {
            }

            override fun onResponse(call: Call<ConfigurationInfor>, response: Response<ConfigurationInfor>) {
                if (response.body()!!.code == 200) {
                    rc_webview.loadUrl(response.body()!!.result.help)
                }
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KEYCODE_BACK && rc_webview.canGoBack()) {
            rc_webview.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}