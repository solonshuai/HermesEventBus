package com.zxy.mytsfqxproject.activity

import android.content.Intent
import com.bumptech.glide.Glide
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.View.GlideCircleTransform
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.StoreInfoBean
import kotlinx.android.synthetic.main.activity_certification.*
import kotlinx.android.synthetic.main.top_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 认证
 */
class CertificationActivity : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_certification

    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "个人信息"
        tv_right.text = "编辑"
        tv_right.setOnClickListener {
            startActivity(Intent(this@CertificationActivity, EditInfoActivity::class.java))
        }
    }

    override fun start() {
        RetrofitManager.service.storeInfo().enqueue(object : Callback<StoreInfoBean> {
            override fun onFailure(call: Call<StoreInfoBean>, t: Throwable) {
            }

            override fun onResponse(call: Call<StoreInfoBean>, response: Response<StoreInfoBean>) {
                if (response.body()!!.code == 200) {
                    val storeInfoBean = response.body()!!.result
                    tv_store_name.text = storeInfoBean.company_name
                    tv_store_phone.text = storeInfoBean.store_phone
                    tv_store_add.text = storeInfoBean.store_address
                }
            }
        })
        RetrofitManager.service.findStaffInfo().enqueue(object : Callback<StoreInfoBean> {
            override fun onFailure(call: Call<StoreInfoBean>, t: Throwable) {
            }

            override fun onResponse(call: Call<StoreInfoBean>, response: Response<StoreInfoBean>) {
                if (response.body()!!.code == 200) {
                    val storeInfoBean = response.body()!!.result
                    Glide.with(this@CertificationActivity).load(storeInfoBean.photo).centerCrop().transform(GlideCircleTransform(this@CertificationActivity)).into(iv_icon)
                    tv_name.text = storeInfoBean.username
                    tv_sex.text = storeInfoBean.sex
                    tv_phone.text = storeInfoBean.phone
                    tv_birthday.text = storeInfoBean.birth_day
                    tv_weixin.text = storeInfoBean.we_chat
                    tv_type.text = storeInfoBean.type_work
                }
            }
        })
    }
}