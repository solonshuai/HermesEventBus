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
                    for (i in 0 until project_data.size) {
                        val addFuwuBean = AddFuwuBean.ResultBean(project_data[i].rg_id, project_data[i].goods_id, project_data[i].goods_type
                                , project_data[i].goods_name, project_data[i].goods_code, project_data[i].goods_num, project_data[i].goods_saleprice, project_data[i].goods_amount,
                                project_data[i].builder_user, project_data[i].builder_uid, project_data[i].is_card, project_data[i].goods_oe, project_data[i].goods_unit, project_data[i].goods_price,
                                project_data[i].goods_discount, project_data[i].total_amount, project_data[i].sale_uid, project_data[i].sale_user, project_data[i].remark)
                        mAddFuwuBean.add(addFuwuBean)
                        val price = project_data[i].goods_num * Tools.strByInt(project_data[i].goods_saleprice)
                        totalMonet += price
                    }
                    for (i in 0 until material_data.size) {
                        val addFuwuBean = AddFuwuBean.ResultBean(material_data[i].rg_id, material_data[i].goods_id, material_data[i].goods_type
                                , material_data[i].goods_name, material_data[i].goods_code, material_data[i].goods_num, material_data[i].goods_saleprice, material_data[i].goods_amount,
                                material_data[i].builder_user, material_data[i].builder_uid, material_data[i].is_card, material_data[i].goods_oe, material_data[i].goods_unit, material_data[i].goods_price,
                                material_data[i].goods_discount, material_data[i].total_amount, material_data[i].sale_uid, material_data[i].sale_user, material_data[i].remark, material_data[i].received_num)
                        mAddFuwuBean.add(addFuwuBean)
                        totalMonet += Tools.strByInt(material_data[i].goods_price)
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
                        if (repair_state == Tools.strByInt(string[i].toString())) {
                            tv_offer.text = "结算"
                            return
                        } else {
                            tv_offer.text = "已结账"
                            return
                        }
                    }
                } else {
                    showToast(errmsg)
                }
            }
        })
    }

    private fun settlement() {
        if (repair_state != 0) {
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