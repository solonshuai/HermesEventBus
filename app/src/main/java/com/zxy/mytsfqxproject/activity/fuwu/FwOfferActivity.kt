package com.zxy.mytsfqxproject.activity.fuwu

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.adapter.AddFuwuAdapter
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.AddFuwuBean
import com.zxy.mytsfqxproject.mvp.entity.RepairInfosBean
import kotlinx.android.synthetic.main.activity_fw_offer.*
import kotlinx.android.synthetic.main.top_view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.set

/**
 * 报价单
 */
class FwOfferActivity : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_fw_offer
    private lateinit var mAddFuwuAdapter: AddFuwuAdapter
    var pamrms = HashMap<String, Any>()
    var repair_id = ""
    var client_id = 0
    var totalMonet = 0
    var repair_state = 0
    var issettlement = false
    private var mAddFuwuBean = ArrayList<AddFuwuBean.ResultBean>()
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        mLayoutStatusView = multipleStatusView
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "报价单"
        mAddFuwuAdapter = AddFuwuAdapter(this)
        mAddFuwuAdapter.setOffer(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager
        mRecyclerView.adapter = mAddFuwuAdapter
        repair_id = intent.getStringExtra("repair_id")
        client_id = intent.getIntExtra("client_id", 0)
        tv_offer.setOnClickListener { settlement() }
    }

    override fun start() {
        pamrms["repair_id"] = repair_id
        RetrofitManager.service.repairInfo(Tools.getRequestBody(pamrms)).enqueue(object : Callback<RepairInfosBean> {
            override fun onFailure(call: Call<RepairInfosBean>, t: Throwable) {
            }

            override fun onResponse(call: Call<RepairInfosBean>, response: Response<RepairInfosBean>) {
                if (response.body()!!.code == 200) {
                    repair_state = response.body()!!.result.repair_info.repair_state
                    mLayoutStatusView!!.showContent()
                    val project_data = response.body()!!.result.project_data
                    val material_data = response.body()!!.result.material_data
                    project_data.forEach {
                        val addFuwuBean = AddFuwuBean.ResultBean(it.rg_id, it.goods_id, it.goods_type
                                , it.goods_name, it.goods_code, it.goods_num, it.goods_saleprice, it.goods_amount,
                                it.builder_user, it.builder_uid, it.is_card, it.goods_oe, it.goods_unit, it.goods_price,
                                it.goods_discount, it.total_amount, it.sale_uid, it.sale_user, it.remark)
                        mAddFuwuBean.add(addFuwuBean)
                        val price = it.goods_num * Tools.strByInt(it.goods_saleprice)
                        totalMonet += price
                    }
                    material_data.forEach {
                        val addFuwuBean = AddFuwuBean.ResultBean(it.rg_id, it.goods_id, it.goods_type
                                , it.goods_name, it.goods_code, it.goods_num, it.goods_saleprice, it.goods_amount,
                                it.builder_user, it.builder_uid, it.is_card, it.goods_oe, it.goods_unit, it.goods_price,
                                it.goods_discount, it.total_amount, it.sale_uid, it.sale_user, it.remark, it.received_num)
                        mAddFuwuBean.add(addFuwuBean)
                        totalMonet += Tools.strByInt(it.goods_price)
                    }
                }
                mAddFuwuAdapter.setDataList(mAddFuwuBean)
                tv_total_money.text = "￥" + totalMonet
                getstate()
            }
        })
    }

    fun getstate() {
        RetrofitManager.service.yesProjectuUpdate().enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val sss = response.body()!!.toString()
                val jobj = JSONObject(sss)
                val errmsg = jobj.optString("errmsg")
                val code = jobj.optInt("code")
                if (code == 200) {
                    val string = jobj.getJSONArray("result")
                    for (i in 0 until string.length()) {
                        if (repair_state != Tools.strByInt(string[i].toString())) {
                            issettlement = true
                        }
                    }
                    if (issettlement) {
                        tv_offer.text = "已结账"
                    }
                } else {
                    showToast(errmsg)
                }
            }
        })
    }

    private fun settlement() {
        if (issettlement) {
            return
        }
        pamrms.clear()
        pamrms["repair_id"] = repair_id
        pamrms["type"] = "app"
        RetrofitManager.service.clearRepair(pamrms).enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val sss = response.body()!!.toString()
                val jobj = JSONObject(sss)
                val errmsg = jobj.optString("errmsg")
                val code = jobj.getInt("code")
                if (code == 200) {
                    val intent = Intent(this@FwOfferActivity, SettlementActivity::class.java)
                    intent.putExtra("client_id", client_id)
                    intent.putExtra("repair_id", repair_id)
                    startActivity(intent)
                } else {
                    showToast(errmsg)
                }
            }
        })
    }
}