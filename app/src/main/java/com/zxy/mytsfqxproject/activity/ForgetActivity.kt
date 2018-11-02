package com.zxy.mytsfqxproject.activity

import android.text.TextUtils
import android.view.View
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import kotlinx.android.synthetic.main.activity_forget.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ForgetActivity : BaseActivity(), View.OnClickListener {
    private var pamrms = HashMap<String, Any>()

    override fun layoutId(): Int = R.layout.activity_forget

    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        bt_login.setOnClickListener(this)
        tv_getCode.setOnClickListener(this)
    }

    override fun start() {
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_getCode -> {
                pamrms["phone"] = et_name.text.toString()
                pamrms["type"] = "find"
                pamrms["verify_code"] = et_code.text.toString()
                RetrofitManager.service.yanSmsCode(Tools.getRequestBody(pamrms)).enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        showToast(getString(R.string.http_error))
                    }

                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        val sss = response.body()!!.toString()
                        val jobj = JSONObject(sss)
                        val errmsg = jobj.get("errmsg").toString()
                        val code = jobj.get("code")
                        if (code == 200) {
                            showToast(errmsg)
                        } else {
                            showToast(errmsg)
                        }
                    }
                })
            }
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
                if (et_pwd.text.toString() != et_pwd2.text.toString()) {
                    showToast("两次密码不一致")
                    return
                }
                pamrms.clear()
                pamrms["phone"] = et_name.text.toString()
                pamrms["sms_code"] = et_code.text.toString()
                pamrms["pwd"] = et_pwd.text.toString()
                pamrms["confirm_pwd"] = et_pwd2.text.toString()
                RetrofitManager.service.findLoginPwd(Tools.getRequestBody(pamrms)).enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        showToast(getString(R.string.http_error))
                    }

                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        val sss = response.body()!!.toString()
                        val jobj = JSONObject(sss)
                        val errmsg = jobj.get("errmsg").toString()
                        val code = jobj.get("code")
                        if (code == 200) {
                            showToast(errmsg)
                            finish()
                        } else {
                            showToast(getString(R.string.http_error))
                        }
                    }
                })
            }

        }
    }

}