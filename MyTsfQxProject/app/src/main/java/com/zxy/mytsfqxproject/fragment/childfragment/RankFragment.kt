package com.zxy.mytsfqxproject.fragment.childfragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.activity.fuwu.FwDetailActivity
import com.zxy.mytsfqxproject.adapter.RankAdapter
import com.zxy.mytsfqxproject.base.BaseFragment
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.RankFuddBean
import com.zxy.mytsfqxproject.recyclerView.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_fuwu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 服务订单fragment
 */
class RankFragment : BaseFragment(), Callback<RankFuddBean>, OnRecyclerItemClickListener<RankFuddBean.ResultBean.DataBean> {
    private var state = 0
    private lateinit var mRankAdapter: RankAdapter
    var pamrms = HashMap<String, Any>()
    private var last_page = 0
    private var current_page = 1
    private var isLoadmore = false

    companion object {
        fun getInstance(state: Int): RankFragment {
            val fragment = RankFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.state = state
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_fuwu

    override fun initView(view: View) {
        mLayoutStatusView = multipleStatusView
        mRankAdapter = RankAdapter(activity)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mRankAdapter
        mRankAdapter.onRecyclerItemClickListener = this
        lazyLoad()
        mSmartRefreshLayout.refreshHeader = ClassicsHeader(this.context).setSpinnerStyle(SpinnerStyle.Scale)
        mSmartRefreshLayout.refreshFooter = ClassicsFooter(this.context).setSpinnerStyle(SpinnerStyle.Scale)
        mSmartRefreshLayout.setOnRefreshListener { refreshlayout ->
            current_page = 1
            isLoadmore = false
            mSmartRefreshLayout.isEnableLoadmore = true
            lazyLoad()
            refreshlayout.finishRefresh(1500)
        }

        mSmartRefreshLayout.setOnLoadmoreListener { refreshlayout ->
            if (current_page >= last_page) {
                mSmartRefreshLayout.finishLoadmore()
                showToast("没有更多了")
            } else {
                isLoadmore = true
                current_page += 1
                lazyLoad()
                refreshlayout.finishLoadmore(1500)
            }
        }
    }

    override fun lazyLoad() {
        mLayoutStatusView!!.showLoading()
        pamrms.clear()
        pamrms["repair_state"] = state
        pamrms["page"] = current_page
        RetrofitManager.service.apprepairList(Tools.getRequestBody(pamrms)).enqueue(this)
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
                mLayoutStatusView!!.showContent()
            } else {
                mLayoutStatusView!!.showEmpty()
            }
        } else {
            mLayoutStatusView!!.showError()
        }
    }

    override fun onItemClick(v: View, item: RankFuddBean.ResultBean.DataBean, position: Int) {
        val intent = Intent(activity, FwDetailActivity::class.java)
        intent.putExtra("item", item)
        startActivity(intent)
    }
}
