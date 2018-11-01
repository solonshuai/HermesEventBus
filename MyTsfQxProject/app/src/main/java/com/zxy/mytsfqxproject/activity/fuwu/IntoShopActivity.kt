package com.zxy.mytsfqxproject.activity.fuwu

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.adapter.IntoShopAdapter
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.IntoShopBean
import kotlinx.android.synthetic.main.activity_into_shop.*
import kotlinx.android.synthetic.main.top_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 该车的进店 消费历史
 */
class IntoShopActivity : BaseActivity(), Callback<IntoShopBean> {
    override fun layoutId(): Int = R.layout.activity_into_shop
    private lateinit var mIntoShopAdapter: IntoShopAdapter
    var car_id: Int = 0
    var pamrms = HashMap<String, Any>()
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        mLayoutStatusView = multipleStatusView
        iv_left.setImageResource(R.mipmap.img_back)
        tv_header_title.text = "进店历史"
        iv_left.setOnClickListener { finish() }
        mIntoShopAdapter = IntoShopAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager
        recyclerView.adapter = mIntoShopAdapter
        car_id = intent.getIntExtra("car_id", 0)
    }

    override fun start() {
        pamrms["car_id"] = car_id
        RetrofitManager.service.carHistory(Tools.getRequestBody(pamrms)).enqueue(this)
    }

    override fun onFailure(call: Call<IntoShopBean>, t: Throwable) {
    }

    override fun onResponse(call: Call<IntoShopBean>, response: Response<IntoShopBean>) {
        if (response.body()!!.code == 200) {
            if (response.body()!!.result.repair_list.size > 0) {
                mIntoShopAdapter.setDataList(response.body()!!.result.repair_list)
            } else {
                mLayoutStatusView!!.showEmpty()
            }
            tv_now_num.text = "" + response.body()!!.result.now_year.total_num
            tv_now_money.text = "￥" + response.body()!!.result.now_year.total_amount
            tv_last_num.text = "" + response.body()!!.result.total_history.repair_count
            tv_last_money.text = "￥" + response.body()!!.result.total_history.repair_amount
        } else {
            mLayoutStatusView!!.showError()
        }
    }
}