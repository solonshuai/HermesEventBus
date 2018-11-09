package com.zxy.mytsfqxproject.activity.fuwu

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.View.ShowWheelView
import com.zxy.mytsfqxproject.adapter.AddPayAdapter
import com.zxy.mytsfqxproject.adapter.ClientCardAdapter
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.inter.OnItemClickListener
import com.zxy.mytsfqxproject.mvp.entity.AddPaymentView
import com.zxy.mytsfqxproject.mvp.entity.ClientCardBean
import com.zxy.mytsfqxproject.mvp.entity.PayTypeBean
import com.zxy.mytsfqxproject.mvp.entity.RepairClearInfoBean
import kotlinx.android.synthetic.main.activity_settlment.*
import kotlinx.android.synthetic.main.top_view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 结账页面
 */
class SettlementActivity : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_settlment
    var repair_id = ""
    var payType = ""
    var client_id = 0
    var clear_id = 0 //结算ID
    var pamrms = HashMap<String, Any>()
    private var setShowValue = HashMap<String, Any>()
    private lateinit var clientCardAdapter: ClientCardAdapter
    private lateinit var addPayAdapter: AddPayAdapter
    var clientCar = ArrayList<ClientCardBean.ResultBean>()
    var addPaylist = ArrayList<AddPaymentView.ResultBean>()
    var result = AddPaymentView.ResultBean(null, 0)
    private val mPayTypeList = ArrayList<String>()
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "结账"
        repair_id = intent.getStringExtra("repair_id")
        client_id = intent.getIntExtra("client_id", 0)
        clientCardAdapter = ClientCardAdapter(this)
        recyclerView_card.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager
        recyclerView_card.adapter = clientCardAdapter
        tv_add.setOnClickListener { addView() }
        tv_offer.setOnClickListener { pay() }
        tv_choose_type.setOnClickListener { ShowPayType() }
        addPayAdapter = AddPayAdapter(this)
        recyclerView_type.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager
        recyclerView_type.adapter = addPayAdapter
        clientCardAdapter.setListener(object : OnItemClickListener<ClientCardBean.ResultBean> {
            override fun onItemClick(str: String, mDatas: MutableList<ClientCardBean.ResultBean>) {
                clientCar = mDatas as ArrayList<ClientCardBean.ResultBean>
                clientCardAdapter.setDataList(clientCar)
            }
        })
    }

    override fun start() {
        pamrms["client_id"] = client_id
        RetrofitManager.service.clientMember(pamrms).enqueue(object : Callback<ClientCardBean> {
            override fun onFailure(call: Call<ClientCardBean>, t: Throwable) {
            }

            override fun onResponse(call: Call<ClientCardBean>, response: Response<ClientCardBean>) {
                if (response.body()!!.code == 200) {
                    clientCar = response.body()!!.result as ArrayList<ClientCardBean.ResultBean>
                    clientCardAdapter.setDataList(clientCar)
                }
            }
        })
        pamrms.clear()
        pamrms["repair_id"] = repair_id
        RetrofitManager.service.getRepairClearInfo(pamrms).enqueue(object : Callback<RepairClearInfoBean> {
            override fun onFailure(call: Call<RepairClearInfoBean>, t: Throwable) {
            }

            override fun onResponse(call: Call<RepairClearInfoBean>, response: Response<RepairClearInfoBean>) {
                if (response.body()!!.code == 200) {
                    val repairInfo = response.body()!!.result.repair_info
                    tv_car_name.text = repairInfo.car_number
                    tv_total.text = "￥" + repairInfo.total_amount
                    tv_total_moneys.text = "￥" + repairInfo.total_amount
                    tv_discount.text = "￥" + repairInfo.total_discount
                    clear_id = response.body()!!.result.clear_info.clear_id

                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        pamrms["type"] = 30
        RetrofitManager.service.queryBusinessPara(Tools.getRequestBody(pamrms)).enqueue(object : Callback<PayTypeBean> {
            override fun onFailure(call: Call<PayTypeBean>, t: Throwable) {
            }

            override fun onResponse(call: Call<PayTypeBean>, response: Response<PayTypeBean>) {
                response.body()!!.result.data.forEach {
                    mPayTypeList.add(it.title)
                    setShowValue[it.title] = it.id
                }
                addPayAdapter.setShowTitle(mPayTypeList)
                addPayAdapter.setShowValue(setShowValue)
            }
        })
    }

    private fun addView() {
        val result = AddPaymentView.ResultBean()
        addPayAdapter.addDataList(result)
    }

    private fun ShowPayType() {
        ShowWheelView.show(this, mPayTypeList) { str ->
            tv_choose_type.text = str
            val results = AddPaymentView.ResultBean(str, setShowValue[str] as Int)
            result = results
        }
    }

    private fun pay() {
        addPaylist.clear()
        pamrms.clear()
        result.amount = et_choose_money.text.toString()
        addPaylist = addPayAdapter.datas as ArrayList<AddPaymentView.ResultBean>
        addPaylist.add(result)
        pamrms["clear_id"] = clear_id
        if (clientCar.size > 0) {
            clientCar.forEach {
                if (it.isCheck) {
                    pamrms["card_id"] = it.mem_id
                } else {
                    showToast("请选择一张会员卡哦")
                    return
                }
            }
        } else {
            pamrms["card_id"] = 0
        }
        pamrms["comment"] = et_beizhu.text
        val Paylist = ArrayList<AddPaymentView.ResultBean>()
        if (addPaylist.size > 0) {
            addPaylist.forEach {
                if (it.pay_title != null && it.amount != null) {
                    Paylist.add(it)
                }
            }
            pamrms["receipt_data"] = Gson().toJson(Paylist)
        }
        RetrofitManager.service.receiptRepair(pamrms).enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val sss = response.body()!!.toString()
                val jobj = JSONObject(sss)
                val errmsg = jobj.get("errmsg").toString()
                val code = jobj.get("code")
                if (code == 200) {
                    finish()
                } else {
                    showToast(errmsg)
                }
            }
        })
    }
}

