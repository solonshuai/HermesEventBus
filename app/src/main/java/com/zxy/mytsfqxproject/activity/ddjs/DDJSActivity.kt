package com.zxy.mytsfqxproject.activity.ddjs

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.activity.fuwu.FwOfferActivity
import com.zxy.mytsfqxproject.adapter.RankAdapter
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.ConfigurationInfor
import com.zxy.mytsfqxproject.mvp.entity.RankFuddBean
import com.zxy.mytsfqxproject.recyclerView.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_ddjs.*
import kotlinx.android.synthetic.main.top_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 订单结算
 */
class DDJSActivity : BaseActivity(), View.OnClickListener, Callback<RankFuddBean>, OnRecyclerItemClickListener<RankFuddBean.ResultBean.DataBean> {
    override fun layoutId(): Int = R.layout.activity_ddjs
    private lateinit var mRankAdapter: RankAdapter
    var pamrms = HashMap<String, Any>()
    private var last_page = 0
    private var current_page = 1
    private var isLoadmore = false
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        mLayoutStatusView = multipleStatusView
        iv_left.setImageResource(R.mipmap.img_back)
        tv_header_title.text = "订单结算"
        iv_left.setOnClickListener { finish() }
        tv_search.setOnClickListener(this)
        mRankAdapter = RankAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mRankAdapter
        mRankAdapter.onRecyclerItemClickListener = this
        mSmartRefreshLayout.refreshHeader = ClassicsHeader(this).setSpinnerStyle(SpinnerStyle.Scale)
        mSmartRefreshLayout.refreshFooter = ClassicsFooter(this).setSpinnerStyle(SpinnerStyle.Scale)
        mSmartRefreshLayout.setOnRefreshListener { refreshlayout ->
            current_page = 1
            isLoadmore = false
            mSmartRefreshLayout.isEnableLoadmore = true
            start()
            refreshlayout.finishRefresh(1500)
        }

        mSmartRefreshLayout.setOnLoadmoreListener { refreshlayout ->
            if (current_page >= last_page) {
                mSmartRefreshLayout.finishLoadmore()
                showToast("没有更多了")
            } else {
                isLoadmore = true
                current_page += 1
                start()
                refreshlayout.finishLoadmore(1500)
            }
        }
    }

    override fun start() {
        pamrms.clear()
        RetrofitManager.service.tsfDefault().enqueue(object : Callback<ConfigurationInfor> {
            override fun onFailure(call: Call<ConfigurationInfor>, t: Throwable) {
            }

            override fun onResponse(call: Call<ConfigurationInfor>, response: Response<ConfigurationInfor>) {
                if (response.body()!!.code == 200) {
                    pamrms["repair_state"] = response.body()!!.result.clear_status.state
                    pamrms["keyword"] = et_search.text.toString()
                    RetrofitManager.service.apprepairList(Tools.getRequestBody(pamrms)).enqueue(this@DDJSActivity)
                }
            }
        })

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_search -> {
                start()
            }
        }
    }

    override fun onFailure(call: Call<RankFuddBean>, t: Throwable) {
    }

    override fun onResponse(call: Call<RankFuddBean>, response: Response<RankFuddBean>) {
        if (response.body()!!.code == 200) {
            if (response.body()!!.result.data.size > 0) {
                current_page = response.body()!!.result.current_page
                last_page = response.body()!!.result.last_page
                if (isLoadmore) {
                    mRankAdapter.addDataList(response.body()!!.result.data)
                } else {
                    mRankAdapter.setDataList(response.body()!!.result.data)
                }
            } else {
                mLayoutStatusView!!.showEmpty()
            }
        } else {
            mLayoutStatusView!!.showError()
        }
    }

    override fun onItemClick(v: View, item: RankFuddBean.ResultBean.DataBean, position: Int) {
        val intent = Intent(this, FwOfferActivity::class.java)
        intent.putExtra("repair_id", "" + item.repair_id)
        intent.putExtra("client_id", item.client_id)
        startActivity(intent)
    }
}