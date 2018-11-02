package com.zxy.mytsfqxproject.activity

import android.content.Intent
import android.text.TextUtils
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.StoreInfoBean
import kotlinx.android.synthetic.main.activity_change_info.*
import kotlinx.android.synthetic.main.top_view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 个人中心 修改门店信息
 */
class ChangeInfoActivity : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_change_info
    var pamrms = HashMap<String, Any>()
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "编辑门店信息"
        tv_choose_map.setOnClickListener { chooseMap() }
        tv_commit.setOnClickListener { Commit() }
    }

    override fun start() {
        RetrofitManager.service.storeInfo().enqueue(object : Callback<StoreInfoBean> {
            override fun onFailure(call: Call<StoreInfoBean>, t: Throwable) {
            }

            override fun onResponse(call: Call<StoreInfoBean>, response: Response<StoreInfoBean>) {
                if (response.body()!!.code == 200) {
                    val storeInfoBean = response.body()!!.result
                    et_name.setText(storeInfoBean.bank_name)
                    et_phone.setText(storeInfoBean.store_phone)
                    et_address.setText(storeInfoBean.store_address)
                    et_kaihu.setText(storeInfoBean.bank_name)
                    et_num.setText(storeInfoBean.bank_account)
                }
            }
        })
    }

    private fun chooseMap() {
        val intent = Intent(this@ChangeInfoActivity, MapActivity::class.java)
        startActivityForResult(intent, 100)
    }

    private fun Commit() {
        if (TextUtils.isEmpty(et_name.text)) {
            showToast("请输入门店名称")
            return
        }
        if (TextUtils.isEmpty(et_phone.text)) {
            showToast("请输入门店电话号码")
            return
        }
        if (TextUtils.isEmpty(et_address.text)) {
            showToast("请输入门店地址")
            return
        }
        pamrms["company_name"] = et_name.text
        pamrms["store_phone"] = et_phone.text
        pamrms["store_address"] = et_address.text
        pamrms["bank_name"] = et_kaihu.text
        pamrms["bank_account"] = et_num.text
        RetrofitManager.service.appUpdateStore(pamrms).enqueue(object : Callback<JsonObject> {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data == null) {
            return
        }
        if (requestCode == 100) {
            val address = data.getStringExtra("currentAddress")
            et_address.setText(address)

        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}