package com.zxy.mytsfqxproject.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.zhjk.admin.mylibrary.MultipleStatusView
import com.zxy.mytsfqxproject.Utils.ActivityManager

abstract class BaseActivity : AppCompatActivity(){
    /**
     * 多种状态的 View 的切换
     */
    var mLayoutStatusView: MultipleStatusView? = null
    var mProgressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initView()
        initListener()
        start()
        closeKeyboard()
        ActivityManager.addActivity(this)
    }

    private fun initListener() {
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        start()
    }


    /**
     *  加载布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * 开始请求
     */
    abstract fun start()

    override fun onDestroy() {
        ActivityManager.removeActivity(this)
        super.onDestroy()
    }

    //关闭软件盘
    fun closeKeyboard() {
        val view = window.peekDecorView()
        if (view != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showToast(string: String) {
        Toast.makeText(this.applicationContext, string, Toast.LENGTH_SHORT).show()
    }
}