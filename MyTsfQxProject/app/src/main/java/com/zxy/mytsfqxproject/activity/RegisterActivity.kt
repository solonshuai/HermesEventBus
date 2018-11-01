package com.zxy.mytsfqxproject.activity

import android.text.TextUtils
import android.view.View
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), View.OnClickListener {
    override fun layoutId(): Int = R.layout.activity_register

    override fun initView() {
        bt_login.setOnClickListener(this)
    }

    override fun start() {
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_login -> {
                if (TextUtils.isEmpty(et_name.text.toString())) {
                    showToast("请输入手机号码")
                    return
                }
                if (TextUtils.isEmpty(et_code.text.toString())) {
                    showToast("请输入验证码")
                    return
                }
                if (TextUtils.isEmpty(et_pwd.text.toString())) {
                    showToast("请输入密码")
                    return
                }
                if (TextUtils.isEmpty(et_pwd2.text.toString())) {
                    showToast("请输入密码")
                    return
                }
                if(et_pwd.text.toString() != et_pwd2.text.toString()){
                    showToast("两次密码不一致")
                    return
                }

            }

        }
    }
}