package com.zxy.mytsfqxproject.activity

import android.text.TextUtils
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import kotlinx.android.synthetic.main.activity_change_pwd.*
import kotlinx.android.synthetic.main.top_view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePwdActivity : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_change_pwd

    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "修改密码"
        tv_commit.setOnClickListener {
            changepwd()
        }
    }

    fun changepwd() {
        if (TextUtils.isEmpty(et_pwd.text.toString())) {
            showToast("请输入旧密码")
            return
        }
        if (TextUtils.isEmpty(et_pwd1.text.toString())) {
            showToast("请确认密码")
            return
        }
        if (TextUtils.isEmpty(et_pwd2.text.toString())) {
            showToast("请输入新密码")
            return
        }
        if (et_pwd2.text.toString() != et_pwd1.text.toString()) {
            showToast("两次密码不一致哦")
            return
        }
        RetrofitManager.service.appAlterLoginPwd(et_pwd.text.toString(), et_pwd1.text.toString()).enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val sss = response.body()!!.toString()
                val jobj = JSONObject(sss)
                val errmsg = jobj.optString("errmsg")
                val code = jobj.optInt("code")
                if (code == 200) {
                    showToast(errmsg)
                    finish()
                } else {
                    showToast(errmsg)
                }
            }
        })
    }

    override fun start() {
    }
}