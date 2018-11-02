package com.zxy.mytsfqxproject.activity

import android.content.Intent
import android.view.View
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.ActivityManager
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.db.SPUtil
import kotlinx.android.synthetic.main.activity_set.*
import kotlinx.android.synthetic.main.top_view.*

class SetActivity : BaseActivity(), View.OnClickListener {
    override fun layoutId(): Int = R.layout.activity_set

    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "设置"
        layout_about.setOnClickListener(this)
        tv_getSatr.setOnClickListener(this)
        tv_changPwd.setOnClickListener(this)
        bt_loginOut.setOnClickListener(this)
    }

    override fun start() {
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.layout_about -> {

            }
            R.id.tv_getSatr -> {

            }
            R.id.tv_changPwd -> {
                startActivity(Intent(this, ChangePwdActivity::class.java))
                finish()
            }
            R.id.bt_loginOut -> {
                SPUtil.clearData()
                ActivityManager.finishAll()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}