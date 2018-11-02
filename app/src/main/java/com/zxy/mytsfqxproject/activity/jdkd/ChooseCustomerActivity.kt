package com.zxy.mytsfqxproject.activity.jdkd

import android.content.Intent
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.activity.CustomerEditorActivity
import com.zxy.mytsfqxproject.adapter.ChooseCustomerAdapter
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.ChooseCustomer
import com.zxy.mytsfqxproject.recyclerView.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_choose_customer.*
import kotlinx.android.synthetic.main.top_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ChooseCustomerActivity : BaseActivity(), Callback<ChooseCustomer>, View.OnClickListener, OnRecyclerItemClickListener<ChooseCustomer.ResultBean.DataBean> {
    override fun layoutId(): Int = R.layout.activity_choose_customer
    private lateinit var mChooseCustomerAdapter: ChooseCustomerAdapter
    var pageIndex = 0
    val totalPages = 0
    private var pamrms = HashMap<String, Any>()
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        mLayoutStatusView = multipleStatusView
        iv_left.setImageResource(R.mipmap.img_back)
        tv_header_title.text = "选择客户"
        iv_right.setImageResource(R.mipmap.img_add)
        mChooseCustomerAdapter = ChooseCustomerAdapter(this)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mRecyclerView.adapter = mChooseCustomerAdapter
        mChooseCustomerAdapter.onRecyclerItemClickListener = this
        mSmartRefreshLayout.refreshHeader = ClassicsHeader(this).setSpinnerStyle(SpinnerStyle.Scale)
        mSmartRefreshLayout.refreshFooter = ClassicsFooter(this).setSpinnerStyle(SpinnerStyle.Scale)
        mSmartRefreshLayout.isEnableLoadmore = false
        mSmartRefreshLayout.setOnRefreshListener { refreshlayout ->
            mSmartRefreshLayout.isEnableLoadmore = true
            refreshlayout.finishRefresh(1000)
            start()
        }
        mSmartRefreshLayout.setOnLoadmoreListener { refreshlayout ->
            if (pageIndex >= totalPages) {
                mSmartRefreshLayout.finishLoadmore()
                showToast("没有更多了")
            } else {
                pageIndex += 1
                refreshlayout.finishLoadmore(1000)
                start()
            }
        }
        iv_left.setOnClickListener(this)
        tv_search.setOnClickListener(this)
        iv_right.setOnClickListener(this)
    }

    override fun start() {
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_search -> {
                start()
            }
            R.id.iv_right -> {
                startActivity(Intent(this, CustomerEditorActivity::class.java))
            }
            R.id.iv_left -> {
                val intent = Intent(this, JDKDActivity::class.java)
                intent.putExtra("customer_phone", "")
                setResult(100, intent)
                finish()
            }
        }
    }

    override fun onItemClick(v: View, item: ChooseCustomer.ResultBean.DataBean, position: Int) {
        val intent = Intent(this, JDKDActivity::class.java)
        intent.putExtra("customer_phone", item)
        setResult(100, intent)
        finish()
    }

    override fun onFailure(call: Call<ChooseCustomer>, t: Throwable) {

    }

    override fun onResume() {
        super.onResume()
        pamrms["key"] = et_search.text.toString()
        RetrofitManager.service.getChooseCustomer(Tools.getRequestBody(pamrms)).enqueue(this)
    }

    override fun onResponse(call: Call<ChooseCustomer>, response: Response<ChooseCustomer>) {
        if (response.body()!!.code == 200 && response.body()!!.result != null) {
            if (response.body()!!.result.data == null && response.body()!!.result.data.size <= 0) {
                mLayoutStatusView!!.showEmpty()
            } else {
                mChooseCustomerAdapter.setDataList(response.body()!!.result.data)
            }
        } else {
            mLayoutStatusView!!.showError()
        }
    }
}