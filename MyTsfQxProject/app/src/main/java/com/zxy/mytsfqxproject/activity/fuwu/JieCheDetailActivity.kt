package com.zxy.mytsfqxproject.activity.fuwu

import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.RepairInfosBean
import kotlinx.android.synthetic.main.activity_jieche_detail.*
import kotlinx.android.synthetic.main.top_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 查看接车详情
 */
class JieCheDetailActivity : BaseActivity(), Callback<RepairInfosBean> {
    override fun layoutId(): Int = R.layout.activity_jieche_detail
    var pamrms = HashMap<String, Any>()
    var repair_id = 0
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        repair_id = intent.getIntExtra("repair_id", 0)
    }

    override fun start() {
        pamrms["repair_id"] = repair_id
        RetrofitManager.service.repairInfo(Tools.getRequestBody(pamrms)).enqueue(this)
    }

    override fun onFailure(call: Call<RepairInfosBean>, t: Throwable) {

    }

    override fun onResponse(call: Call<RepairInfosBean>, response: Response<RepairInfosBean>) {
        if (response.body()!!.code == 200) {
            val result = response.body()!!.result.repair_info
            tv_header_title.text = result.car_number
            tv_name.text = result.send_name
            tv_phone.text = result.send_phone
            tv_time.text = Tools.getTime(result.create_time, "yyyy-MM-dd HH:mm:ss")
            tv_mileage.text = "" + result.miles + "KM"
            tv_youbiao.text = result.fuel
            tv_times.text = Tools.getTime(result.expect_complete_time, "yyyy-MM-dd HH:mm:ss")
            tv_beizhu.text = result.fault
        }
    }
}