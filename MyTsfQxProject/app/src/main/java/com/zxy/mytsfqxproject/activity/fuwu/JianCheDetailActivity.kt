package com.zxy.mytsfqxproject.activity.fuwu

import android.view.View
import com.bumptech.glide.Glide
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.JianCheDetailBean
import kotlinx.android.synthetic.main.activity_jianche.*
import kotlinx.android.synthetic.main.top_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 检车详情
 */
class JianCheDetailActivity : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_jianche
    var repair_id = 0
    var car_id: Int = 0
    var pamrms = HashMap<String, Any>()
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        tv_header_title.text = "检车详情"
        iv_left.setOnClickListener { finish() }
        repair_id = intent.getIntExtra("repair_id",0)
        car_id = intent.getIntExtra("car_id", 0)
    }

    override fun start() {
        pamrms["repair_id"] = repair_id
        pamrms["car_id"] = car_id
        RetrofitManager.service.verifyCarDetail(Tools.getRequestBody(pamrms)).enqueue(object : Callback<JianCheDetailBean> {
            override fun onFailure(call: Call<JianCheDetailBean>, t: Throwable) {
            }

            override fun onResponse(call: Call<JianCheDetailBean>, response: Response<JianCheDetailBean>) {
                if (response.body()!!.code == 200) {
                    val result = response.body()!!.result
                    tv_car_name.text = result.repair.car_number
                    Glide.with(this@JianCheDetailActivity).load(result.repair.brand_logo).into(iv_car_icon)
                    tv_time.text = result.repair.create_time
                    tv_licheng.text = "" + result.repair.miles + "KM"
                    tv_jianche.text = result.repair.fault
                    var index = 0
                    if (result.verify.lacquer == null) {
                        layout_lacquer.visibility = View.GONE
                    } else {
                        tv_lacquer.text = result.verify.lacquer
                        index += 1
                    }
                    if (result.verify.conditioner == null) {
                        layout_conditioner.visibility = View.GONE
                    } else {
                        tv_conditioner.text = result.verify.conditioner
                        index += 1
                    }
                    if (result.verify.remark == null ) {
                        tv_remark.visibility = View.GONE
                    } else {
                        tv_remark.text = result.verify.remark
                        index += 1
                    }
                    if (result.verify.engine == null) {
                        layout_engine.visibility = View.GONE
                    } else {
                        tv_engine.text = result.verify.engine
                        index += 1
                    }
                    if (result.verify.chassis == null) {
                        layout_chassis.visibility = View.GONE
                    } else {
                        tv_chassis.text = result.verify.chassis
                        index += 1
                    }
                    if (result.verify.light == null) {
                        layout_light.visibility = View.GONE
                    } else {
                        tv_light.text = result.verify.light
                        index += 1
                    }
                    if (result.verify.road_test == null) {
                        layout_road_test.visibility = View.GONE
                    } else {
                        tv_road_test.text = result.verify.road_test
                        index += 1
                    }
                    if (result.verify.lamplight == null) {
                        layout_lamplight.visibility = View.GONE
                    } else {
                        tv_lamplight.text = result.verify.lamplight
                        index += 1
                    }
                    tv_jianche_num.text = "共发现" + index + "项问题"
                }
            }
        })
    }
}