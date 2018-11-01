package com.zxy.mytsfqxproject.activity

import android.app.ProgressDialog
import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.zxy.mytsfqxproject.MainActivity
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.db.SPUtil
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.http.UrlConstant
import com.zxy.mytsfqxproject.mvp.entity.MessageEvent
import com.zxy.mytsfqxproject.mvp.entity.UserBean
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LoginActivity : BaseActivity(), View.OnClickListener {
    private var pamrms = HashMap<String, Any>()

    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        mProgressDialog = ProgressDialog(this)
        bt_login.setOnClickListener(this)
        tv_register.setOnClickListener(this)
        tv_forget.setOnClickListener(this)
    }

    override fun start() {}
    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_login -> {
                if (TextUtils.isEmpty(et_name.text.toString())) {
                    showToast("请输入手机号码")
                    return
                }
                if (TextUtils.isEmpty(et_pwd.text.toString())) {
                    showToast("请输入密码")
                    return
                }
                mProgressDialog!!.setMessage("登录中......")
                mProgressDialog!!.show()
                pamrms["phone"] = et_name.text.toString()
                pamrms["pwd"] = et_pwd.text.toString()
                RetrofitManager.service.login(Tools.getRequestBody(pamrms)).enqueue(object : Callback<UserBean> {
                    override fun onFailure(call: Call<UserBean>, t: Throwable) {
                        mProgressDialog!!.dismiss()
                        showToast(getString(R.string.http_error))
                    }

                    override fun onResponse(call: Call<UserBean>, response: Response<UserBean>) {
                        mProgressDialog!!.dismiss()
                        showToast(response.body()!!.errmsg)
                        if (response.body()!!.code == 200) {
                            val userBean = response.body()!!.result
                            SPUtil.putData(UrlConstant.token, userBean.acctoken)
                            SPUtil.putData(UrlConstant.userName, userBean.username)
                            SPUtil.putData(UrlConstant.userImg, userBean.photo)
                            SPUtil.putData(UrlConstant.rongCloud, userBean.rongCloud)
                            SPUtil.putData(UrlConstant.userId, userBean.staff_id)
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            this@LoginActivity.finish()
                        }
                    }
                })
            }
            R.id.tv_register -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_forget -> {
                val intent = Intent(this, ForgetActivity::class.java)
                startActivity(intent)
            }
        }
    }
}