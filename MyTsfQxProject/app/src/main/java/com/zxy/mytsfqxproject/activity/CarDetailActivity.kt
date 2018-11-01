package com.zxy.mytsfqxproject.activity

import android.annotation.SuppressLint
import android.content.Context
import android.text.ClipboardManager
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.CarListDetailBean
import kotlinx.android.synthetic.main.activity_car_detail.*
import kotlinx.android.synthetic.main.top_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * 车辆详情 页面
 */
class CarDetailActivity : BaseActivity(), View.OnClickListener, Callback<CarListDetailBean> {
    override fun layoutId(): Int = R.layout.activity_car_detail
    var pamrms = HashMap<String, Any>()
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener(this)
        iv_jiebang.setOnClickListener(this)
        tv_copy.setOnClickListener(this)
        tv_header_title.text = "车辆详情"
        pamrms["car_id"] = intent.getIntExtra("car_id", 0)
    }

    override fun start() {
        RetrofitManager.service.CarDetail(Tools.getRequestBody(pamrms)).enqueue(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.iv_jiebang -> {
                onJieBang()
                finish()
            }
            R.id.tv_copy ->{
                onClickCopy()
            }
        }
    }

    fun onClickCopy() {
        if(TextUtils.isEmpty(tv_car_chejia_num.text)){
            showToast("内容为空")
            return
        }
        val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        // 将文本内容放到系统剪贴板里。
        cm.text = tv_car_chejia_num.text
        showToast("复制成功")
    }
    override fun onFailure(call: Call<CarListDetailBean>, t: Throwable) {
    }

    @SuppressLint("SetTextI18n")
    override fun onResponse(call: Call<CarListDetailBean>, response: Response<CarListDetailBean>) {
        if (response.body()!!.code == 200) {
            val result = response.body()!!.result
            Glide.with(this).load(result.car_photo).into(iv_car)
            tv_car_num.text = result.car_number
            tv_car_chejia_num.text = result.car_vin
            tv_time.text = result.create_date
            Glide.with(this).load(result.brand_logo).into(iv_car_icon)
            tv_car_name.text = result.car_brand_name
            tv_price.text = result.buy_money + "万"
            tv_engine_num.text = result.car_eno
            tv_insurance.text = Tools.getTime(result.insurance_over_time,"yyyy-MM-dd HH:mm:ss")
            tv_name.text = result.username
            if (result.sex == "男") {
                iv_sex.setImageResource(R.mipmap.img_nan)
            } else {
                iv_sex.setImageResource(R.mipmap.img_nv)
            }
            if (result.client_grade == 10) {
                iv_level.setImageResource(R.mipmap.img_level)
            } else if (result.client_grade == 20) {
                iv_level.setImageResource(R.mipmap.img_level_b)
            } else if (result.client_grade == 30) {
                iv_level.setImageResource(R.mipmap.img_level_c)
            }
            tv_phone.text = result.phone
        }
    }

    fun onJieBang() {
        pamrms["id"] = intent.getIntExtra("car_id",0)
        RetrofitManager.service.relieveClient(Tools.getRequestBody(pamrms)).enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

            }
        })
    }
}
