package com.zxy.mytsfqxproject.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import com.etop.PLDemo.EtScanPlateActivity
import com.etop.utils.*
import com.etop.vincode.EtVinScanActivity
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.KeyboardUtil
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.View.ShowWheelView
import com.zxy.mytsfqxproject.View.ShowWheelViewTime
import com.zxy.mytsfqxproject.activity.jdkd.CarBrandActivity
import com.zxy.mytsfqxproject.activity.jdkd.ChooseCustomerActivity
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.CarBean
import com.zxy.mytsfqxproject.mvp.entity.ChooseCustomer
import com.zxy.mytsfqxproject.mvp.entity.InsuranceBean
import com.zxy.mytsfqxproject.mvp.entity.SortModel
import kotlinx.android.synthetic.main.activity_new_orde_add.*
import kotlinx.android.synthetic.main.top_view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Method

/**
 * 日历 订单新添加车辆
 */
class NewOrdeAddActivity : BaseActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    override fun layoutId(): Int = R.layout.activity_new_orde_add
    var mChooseCustomeritem: ChooseCustomer.ResultBean.DataBean? = null
    var mCarBeanResultBean: CarBean.ResultBean? = null
    var mSortModel: SortModel? = null //品牌
    private val mInsuranceList = ArrayList<String>()
    var mInsuranceBean = ArrayList<InsuranceBean.ResultBean>()
    var pamrms = HashMap<String, Any>()
    var type = 10
    var sex: String="男"
    private val lavel = java.util.ArrayList<String>().apply {
        add("A")
        add("B")
        add("C")
    }
    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> {
                    if (mChooseCustomeritem == null) return
                    if (mChooseCustomeritem!!.client_grade == 10) {
                        tv_user_level.text = "A"
                    } else if (mChooseCustomeritem!!.client_grade == 20) {
                        tv_user_level.text = "B"
                    } else {
                        tv_user_level.text = "C"
                    }
                    et_phone.setText(mChooseCustomeritem!!.phone)
                    et_songxiuren_name.setText(mChooseCustomeritem!!.username)
                    if (mChooseCustomeritem!!.sex == "男") {
                        rb_nan.isChecked = true
                    } else {
                        rb_nv.isChecked = true
                    }
                    if (mChooseCustomeritem!!.client_code == "个人") {
                        rb_nan2.isChecked = true
                    } else {
                        rb_nv2.isChecked = true
                    }
                    tv_user_level.isClickable = false
                    et_phone.isFocusable = false
                    et_songxiuren_name.isFocusable = false
                    rb_nan.isClickable = false
                    rb_nv.isClickable = false
                    rb_nan2.isClickable = false
                    rb_nv2.isClickable = false
                }
            }
        }
    }

    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "添加车辆"
        tv_right.text = "保存"
        tv_right.setOnClickListener(this)
        iv_car_scan.setOnClickListener(this)
        iv_carjia_scan.setOnClickListener(this)
        tv_pinpai_num.setOnClickListener(this)
        tv_baoxian_num.setOnClickListener(this)
        tv_daoqi_num.setOnClickListener(this)
        et_nianshen_num.setOnClickListener(this)
        rg_songxiuren.setOnCheckedChangeListener(this)
        rg_songxiuren2.setOnCheckedChangeListener(this)
        iv_phone_scanning.setOnClickListener(this)
        try {
            val cls = EditText::class.java
            val setSoftInputShownOnFocus: Method
            setSoftInputShownOnFocus = cls.getMethod("setShowSoftInputOnFocus", Boolean::class.javaPrimitiveType)
            setSoftInputShownOnFocus.isAccessible = true
            setSoftInputShownOnFocus.invoke(et_car_num, false)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        et_car_num.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (TextUtils.isEmpty(et_car_num.text)) {
                    KeyboardUtil(this@NewOrdeAddActivity, this@NewOrdeAddActivity, et_car_num).showChinese()
                } else {
                    KeyboardUtil(this@NewOrdeAddActivity, this@NewOrdeAddActivity, et_car_num).showNumber()
                }
                return false
            }
        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.iv_phone_scanning -> {
                val intent = Intent(this, ChooseCustomerActivity::class.java)
                startActivityForResult(intent, 100)
            }
            R.id.tv_user_level -> {
                ShowWheelView.show(this, lavel) { tv_user_level.text = it }
            }
            R.id.iv_car_scan -> {
                //1.设置授权文件的名字
                PlateUserIdUtil.setUserId("1A055DE0BF6B06BEBCDC")
                val intent = Intent(this@NewOrdeAddActivity, EtScanPlateActivity::class.java)
                //2.配置保存图像信息
                val config = PlateInfoConfig()
                intent.putExtra(PlateUserIdUtil.INTENT_PLATE_CONFIG, config)
                startActivityForResult(intent, 400)
            }
            R.id.iv_carjia_scan -> {
                //1.设置授权名称
                VinUserIdUtil.setUserId("1A055DE0BF6B06BEBCDC")
                try {
                    //写入授权文件
                    VinStreamEmpowerFileUtil.copyDataBase(this)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                val intent = Intent(this@NewOrdeAddActivity, EtVinScanActivity::class.java)
                val config = VinInfoConfig()
                //是否保存图像，默认不保存
                //config.setIsSaveImage(true);
                //设置图像保存路径(格式为"/alpha/VinCode/")默认路径
                //config.setStrSaveImagePath("/etop666/");
                intent.putExtra(VinUserIdUtil.INTENT_VIN_CONFIG, config)
                startActivityForResult(intent, 500)
            }
            R.id.tv_right -> {
                if (TextUtils.isEmpty(et_car_num.text)) {
                    showToast("请填写车牌号码")
                    return
                }
                pamrms["car_number"] = et_car_num.text
                if (TextUtils.isEmpty(et_carjia_num.text)) {
                    showToast("请填写车架号码")
                    return
                }
                pamrms["car_vin"] = et_carjia_num.text
                if (TextUtils.isEmpty(tv_pinpai_num.text)) {
                    showToast("请选车车辆品牌")
                    return
                }
                pamrms["car_series_id"] = mCarBeanResultBean!!.brand_id
                if (TextUtils.isEmpty(et_engine_num.text)) {
                    showToast("请填写发动号")
                    return
                }
                pamrms["car_eno"] = et_engine_num.text
                if (TextUtils.isEmpty(tv_baoxian_num.text)) {
                    showToast("请选择保险公司")
                    return
                }
                for (i in 0 until mInsuranceList.size) {
                    if (tv_baoxian_num.text == mInsuranceBean[i].name) {
                        pamrms["insurance_id"] = mInsuranceBean[i].id
                    }
                }
                if (TextUtils.isEmpty(tv_daoqi_num.text)) {
                    showToast("请选择保险到期日期")
                    return
                }
                pamrms["insurance_over_time"] = tv_daoqi_num.text
                if (TextUtils.isEmpty(et_nianshen_num.text)) {
                    showToast("请选择年审日期")
                    return
                }
                pamrms["next_annual_time"] = et_nianshen_num.text
                pamrms["username"] = et_songxiuren_name.text
                pamrms["sex"] = sex
                pamrms["client_grade"] = tv_user_level.text
                pamrms["phone"] = et_phone.text
                pamrms["client_type"] = type
                RetrofitManager.service.appSaveCar2(Tools.getRequestBody(pamrms)).enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        val sss = response.body()!!.toString()
                        val jobj = JSONObject(sss)
                        val errmsg = jobj.optString("errmsg")
                        val code = jobj.getInt("code")
                        if (code == 200) {
                            val result = JSONObject(jobj.optString("result"))
                            val car_id = result.optString("car_id")
                            showToast(errmsg)
                            val intent = Intent(this@NewOrdeAddActivity, NewOrdeListItemActivity::class.java)
                            intent.putExtra("car_id", car_id)
                            intent.putExtra("newAdd", true)
                            startActivity(intent)
                        } else {
                            showToast(errmsg)
                        }
                    }
                })

            }
            R.id.tv_pinpai_num -> {
                val intent = Intent(this, CarBrandActivity::class.java)
                startActivityForResult(intent, 200)
            }
            R.id.tv_baoxian_num -> {
                RetrofitManager.service.insuranceList().enqueue(object : Callback<InsuranceBean> {
                    override fun onFailure(call: Call<InsuranceBean>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<InsuranceBean>, response: Response<InsuranceBean>) {
                        for (i in 0 until response.body()!!.result.size) {
                            mInsuranceList.add(response.body()!!.result[i].name)
                        }
                        mInsuranceBean = response.body()!!.result as ArrayList<InsuranceBean.ResultBean>
                        ShowWheelView.show(this@NewOrdeAddActivity, mInsuranceList) { str ->
                            tv_baoxian_num.text = str
                        }
                    }
                })
            }
            R.id.tv_daoqi_num -> {
                ShowWheelViewTime.show(this, true, true, true, false, false, "yyyy-MM-dd") { tv_daoqi_num.text = it }
            }
            R.id.et_nianshen_num -> {
                ShowWheelViewTime.show(this, true, true, true, false, false, "yyyy-MM-dd") { et_nianshen_num.text = it }

            }
        }
    }

    override fun start() {
    }

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        if (rb_nan.id == checkedId) {
            sex = "男"
        }
        if (rb_nv.id == checkedId) {
            sex = "女"
        }
        if (rb_nan2.id == checkedId) {
            type = 10
        }
        if (rb_nv2.id == checkedId) {
            type = 20
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data == null) return
        when (resultCode) {
            100 -> {
                mChooseCustomeritem = data.getParcelableExtra("customer_phone")
                mHandler.sendEmptyMessage(0)
            }
            200 -> {
                mCarBeanResultBean = data.getParcelableExtra("car_detail")
                mSortModel = data.getParcelableExtra("car")
                if (mCarBeanResultBean == null) return
                tv_pinpai_num.text = mSortModel!!.name + "/" + mCarBeanResultBean!!.brand_name
            }

        }
        if (requestCode == 400) {
            var plateInfo = ""
            val listResult = data.getStringArrayListExtra("listResult")

            for (i in listResult.indices) {
                plateInfo += listResult[i] + "\r\n"
            }
            //显示识别结果
            showToast(plateInfo)
            et_car_num.setText(listResult[0])
        } else if (requestCode == 500) {
            val recogResult = data.getStringExtra("recogResult")
            et_carjia_num.setText(recogResult)
        }
    }
}