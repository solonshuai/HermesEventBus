package com.zxy.mytsfqxproject.activity

import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import kotlinx.android.synthetic.main.activity_advice.*
import kotlinx.android.synthetic.main.top_view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 使用幫助
 */
class AdviceActivity : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_advice

    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "建议"
        tv_commit.setOnClickListener {
            RetrofitManager.service.feedback(et_advice.text.toString()).enqueue(object : Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
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
                        showToast(errmsg)
                    }
                }
            })
        }
    }

    override fun start() {
    }
}