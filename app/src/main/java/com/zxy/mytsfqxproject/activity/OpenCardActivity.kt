package com.zxy.mytsfqxproject.activity

import android.text.TextUtils
import android.view.View
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.View.ShowWheelView
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.CarLostBean
import com.zxy.mytsfqxproject.mvp.entity.CommissionBean
import com.zxy.mytsfqxproject.mvp.entity.PayTypeBean
import kotlinx.android.synthetic.main.activity_open_card.*
import kotlinx.android.synthetic.main.top_view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.set

/**
 * 开卡
 */
class OpenCardActivity : BaseActivity(), View.OnClickListener {
    override fun layoutId(): Int = R.layout.activity_open_card
    var pamrms = HashMap<String, Any>()
    private val mCarList = ArrayList<String>()
    var mCarLostBean = ArrayList<CarLostBean.ResultBean>()
    var type = ""
    private val mCommissionList = ArrayList<String>()
    var mCommissionBean = ArrayList<CommissionBean.ResultBean>()
    var commission = ""
    private val mPayTypeList = ArrayList<String>()
    var mPayTypeBean = ArrayList<PayTypeBean.ResultBean.DataBean>()
    var payType = ""
    var client_id: Int = 0
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        tv_header_title.text = "开卡"
        iv_left.setOnClickListener { finish() }
        bt_kaika.setOnClickListener(this)
        tv_type.setOnClickListener(this)
        tv_ticheng_id.setOnClickListener(this)
        tv_pay_type.setOnClickListener(this)
        client_id = intent.getIntExtra("client_id", 0)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_kaika -> {
                pamrms.clear()
                pamrms["client_id"] = client_id
                if (TextUtils.isEmpty(et_recharge_amount.text)) {
                    showToast("请填充值金额")
                    return
                }
                if (TextUtils.isEmpty(et_giver_money.text)) {
                    showToast("请填赠送金额")
                    return
                }
                pamrms["recharge_money"] = et_recharge_amount.text
                pamrms["donate_money"] = et_giver_money.text
                if (type != "" && type != null) {
                    for (i in 0 until mCarLostBean.size) {
                        if (type == mCarLostBean[i].card_title) {
                            pamrms["card_id"] = mCarLostBean[i].card_id
                            break
                        }
                    }
                }
                if (TextUtils.isEmpty(tv_ticheng_id.text)) {
                    showToast("请选择提成员工")
                    return
                }
                for (i in 0 until mCommissionBean.size) {
                    if (commission == mCommissionBean[i].username) {
                        pamrms["rake_staff_id"] = mCommissionBean[i].staff_id
                    }
                }
                if (TextUtils.isEmpty(tv_pay_type.text)) {
                    showToast("请选择支付方式")
                    return
                }
                for (i in 0 until mPayTypeBean.size) {
                    if (payType == mPayTypeBean[i].title) {
                        pamrms["pay_type"] = mPayTypeBean[i].id
                        break
                    }
                }
                RetrofitManager.service.appCreateMember(Tools.getRequestBody(pamrms)).enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        val sss = response.body()!!.toString()
                        val jobj = JSONObject(sss)
                        val errmsg = jobj.get("errmsg").toString()
                        val code = jobj.get("code")
                        if (code == 200) {
                            showToast(errmsg)
                        } else {
                            showToast(getString(R.string.http_error))
                        }
                    }
                })
            }
            R.id.tv_type -> {
                RetrofitManager.service.cardList().enqueue(object : Callback<CarLostBean> {
                    override fun onFailure(call: Call<CarLostBean>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<CarLostBean>, response: Response<CarLostBean>) {
                        response.body()!!.result.forEach {
                            mCarList.add(it.card_title)
                        }
                        mCarLostBean = response.body()!!.result as ArrayList<CarLostBean.ResultBean>
                        ShowWheelView.show(this@OpenCardActivity, mCarList) { str ->
                            type = str
                            tv_type.text = str
                        }
                    }
                })
            }
            R.id.tv_ticheng_id -> {
                RetrofitManager.service.appLists().enqueue(object : Callback<CommissionBean> {
                    override fun onFailure(call: Call<CommissionBean>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<CommissionBean>, response: Response<CommissionBean>) {
                        response.body()!!.result.forEach {
                            mCommissionList.add(it.username)
                        }
                        mCommissionBean = response.body()!!.result as ArrayList<CommissionBean.ResultBean>
                        ShowWheelView.show(this@OpenCardActivity, mCommissionList) { str ->
                            commission = str
                            tv_ticheng_id.text = str
                        }
                    }
                })

            }
            R.id.tv_pay_type -> {
                pamrms["type"] = 30
                pamrms["business"] = "recharge"
                RetrofitManager.service.queryBusinessPara(Tools.getRequestBody(pamrms)).enqueue(object : Callback<PayTypeBean> {
                    override fun onFailure(call: Call<PayTypeBean>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<PayTypeBean>, response: Response<PayTypeBean>) {
                        response.body()!!.result.data.forEach {
                            mPayTypeList.add(it.title)
                        }
                        mPayTypeBean = response.body()!!.result.data as ArrayList<PayTypeBean.ResultBean.DataBean>
                        ShowWheelView.show(this@OpenCardActivity, mPayTypeList) { str ->
                            payType = str
                            tv_pay_type.text = str
                        }
                    }
                })
            }
        }
    }

    override fun start() {

    }

}