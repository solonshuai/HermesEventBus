package com.zxy.mytsfqxproject.activity.fuwu

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.View.ShowWheelView
import com.zxy.mytsfqxproject.adapter.FwTaskAdapter
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.AddFuwuBean
import com.zxy.mytsfqxproject.mvp.entity.ChooseTechBean
import com.zxy.mytsfqxproject.recyclerView.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_fw_task.*
import kotlinx.android.synthetic.main.top_view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.set

/**
 * 服务订单派工
 */
class FwddTaskingActivity : BaseActivity(), Callback<AddFuwuBean>, OnRecyclerItemClickListener<AddFuwuBean.ResultBean> {
    override fun layoutId(): Int = R.layout.activity_fw_task
    private lateinit var mFwTaskAdapter: FwTaskAdapter
    var pamrms = HashMap<String, Any>()
    private var mAddFuwuBean = ArrayList<AddFuwuBean.ResultBean>()
    private val mTechList = ArrayList<String>()
    private val mapsTech = HashMap<String, Int>()
    var techName = ""
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        mLayoutStatusView = multipleStatusView
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_right.text = "批量选择"
        tv_header_title.text = intent.getStringExtra("car_number")
        mFwTaskAdapter = FwTaskAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager
        recyclerView.adapter = mFwTaskAdapter
        mFwTaskAdapter.onRecyclerItemClickListener = this
        tv_right.setOnClickListener { gotoNext() }
        tv_paigong.setOnClickListener { repairAssign() }
    }

    override fun start() {
        pamrms["repair_id"] = intent.getStringExtra("repair_id")
        RetrofitManager.service.getGoodsList(Tools.getRequestBody(pamrms)).enqueue(this)
    }

    override fun onResume() {
        super.onResume()
        RetrofitManager.service.getChooseTech().enqueue(object : Callback<ChooseTechBean> {
            override fun onFailure(call: Call<ChooseTechBean>, t: Throwable) {
            }

            override fun onResponse(call: Call<ChooseTechBean>, response: Response<ChooseTechBean>) {
                if (response.body()!!.code == 200) {
                    if (response.body()!!.result.data.size > 0) {
                        for (i in 0 until response.body()!!.result.data.size) {
                            mTechList.add(response.body()!!.result.data[i].username)
                            mapsTech[response.body()!!.result.data[i].username] = response.body()!!.result.data[i].staff_id
                        }
                    } else {
                        mLayoutStatusView!!.showEmpty()
                    }
                } else {
                    showToast(response.body()!!.errmsg)
                }
            }
        })
    }

    override fun onFailure(call: Call<AddFuwuBean>, t: Throwable) {
    }

    override fun onResponse(call: Call<AddFuwuBean>, response: Response<AddFuwuBean>) {
        if (response.body()!!.code == 200) {
            if (response.body()!!.result.isEmpty()) {
                mLayoutStatusView!!.showEmpty()
            } else {
                mAddFuwuBean.clear()
                mAddFuwuBean = response.body()!!.result as ArrayList<AddFuwuBean.ResultBean>
                mFwTaskAdapter.setDataList(response.body()!!.result)
            }
        } else {
            showToast(response.body()!!.errmsg)
        }
    }

    override fun onItemClick(v: View, item: AddFuwuBean.ResultBean, position: Int) {

        ShowWheelView.show(this@FwddTaskingActivity, mTechList) { str ->
            techName = str
            if (position <= mAddFuwuBean.size) {
                mAddFuwuBean[position].builder_user = techName
                mAddFuwuBean[position].builder_uid = mapsTech[techName] as Int
            }
            mFwTaskAdapter.setDataList(mAddFuwuBean)
        }
    }

    private fun gotoNext() {
        ShowWheelView.show(this@FwddTaskingActivity, mTechList) { str ->
            techName = str
            for (i in 0 until mAddFuwuBean.size) {
                mAddFuwuBean[i].builder_user = techName
                mAddFuwuBean[i].builder_uid = mapsTech[techName] as Int
            }
            mFwTaskAdapter.setDataList(mAddFuwuBean)
        }
    }

    private fun repairAssign() {
        pamrms["repair_id"] = intent.getStringExtra("repair_id")
        pamrms["goods_list"] = Gson().toJson(mAddFuwuBean)
        RetrofitManager.service.repairAssign(Tools.getRequestBody(pamrms)).enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (!Tools.isJson(response.body()!!.toString())) {
                    return
                }
                val sss = response.body()!!.toString()
                val jobj = JSONObject(sss)
                val errmsg = jobj.optString("errmsg")
                val code = jobj.getInt("code")
                if (code == 200) {
                    showToast(errmsg)
                    finish()
                } else {
                    showToast(errmsg)
                }
            }
        })
    }
}