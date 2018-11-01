package com.zxy.mytsfqxproject.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.zxy.mytsfqxproject.activity.AdviceActivity
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.View.CustomPopDialog2
import com.zxy.mytsfqxproject.View.GlideCircleTransform
import com.zxy.mytsfqxproject.activity.CertificationActivity
import com.zxy.mytsfqxproject.activity.ChangeInfoActivity
import com.zxy.mytsfqxproject.activity.HelpActivity
import com.zxy.mytsfqxproject.base.BaseFragment
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.StaffInfoBean
import kotlinx.android.synthetic.main.fragment_my.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyFragment : BaseFragment(), View.OnClickListener {
    private var mTitle: String? = null
    var staffInfoBean: StaffInfoBean.ResultBean? = null

    companion object {
        fun getInstance(title: String): MyFragment {
            val fragment = MyFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_my
    }

    override fun initView(view: View) {
        layout_help.setOnClickListener(this)
        layout_fankui.setOnClickListener(this)
        layout_share.setOnClickListener(this)
        layout_chat.setOnClickListener(this)
        tv_stata.setOnClickListener(this)
        RetrofitManager.service.staffInfo().enqueue(object : Callback<StaffInfoBean> {
            override fun onFailure(call: Call<StaffInfoBean>, t: Throwable) {
            }

            override fun onResponse(call: Call<StaffInfoBean>, response: Response<StaffInfoBean>) {
                if (response.body()!!.code == 200) {
                    staffInfoBean = response.body()!!.result
                    Glide.with(this@MyFragment).load(staffInfoBean!!.photo).centerCrop().transform(GlideCircleTransform(activity)).into(iv_icon)
                    tv_username.text = staffInfoBean!!.username
                    tv_suoming.text = staffInfoBean!!.company_name
                }
            }
        })
    }

    override fun lazyLoad() {
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.layout_chat -> {
                if (staffInfoBean!!.wx_qrcode != null) {
                    showPopupWindow(staffInfoBean!!.wx_qrcode)
                }
            }
            R.id.layout_fankui -> {
                val intent = Intent(activity, HelpActivity::class.java)
                startActivity(intent)
            }
            R.id.layout_help -> {
                startActivity(Intent(activity, ChangeInfoActivity::class.java))
            }
            R.id.layout_share -> {
                startActivity(Intent(activity, AdviceActivity::class.java))
            }
            R.id.tv_stata -> {
                startActivity(Intent(activity, CertificationActivity::class.java))
            }
        }
    }

    private fun showPopupWindow(img: String) {
        val dialog = CustomPopDialog2.Builder(activity)
        dialog.imags = img
        dialog.create().show()
    }
}