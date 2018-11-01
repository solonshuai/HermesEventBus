package com.zxy.mytsfqxproject.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.adapter.CustomerManngerAdapter
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.ChooseCustomer
import com.zxy.mytsfqxproject.recyclerView.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_customer_manager.*
import kotlinx.android.synthetic.main.top_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 客户管理
 */
class CustomerManager : BaseActivity(), OnRecyclerItemClickListener<ChooseCustomer.ResultBean.DataBean>, Callback<ChooseCustomer>, View.OnClickListener {
    override fun layoutId(): Int = R.layout.activity_customer_manager
    private lateinit var mCustomerManngerAdapter: CustomerManngerAdapter
    var pageIndex = 0
    val totalPages = 0
    private var pamrms = HashMap<String, Any>()
    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> {
                    pamrms["key"] = et_search.text
                    RetrofitManager.service.applists(Tools.getRequestBody(pamrms)).enqueue(this@CustomerManager)
                }
            }
        }
    }
    override fun initView() {
        mLayoutStatusView = mMultipleStatusView
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "客户管理"
        iv_right.setImageResource(R.mipmap.img_customer)
        mCustomerManngerAdapter = CustomerManngerAdapter(this)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mRecyclerView.adapter = mCustomerManngerAdapter
        mCustomerManngerAdapter.onRecyclerItemClickListener = this
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
        bt_addCustomer.setOnClickListener(this)
        tv_search.setOnClickListener(this)
        iv_right.setOnClickListener(this)
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
    }

    override fun onItemClick(v: View, item: ChooseCustomer.ResultBean.DataBean, position: Int) {
        val intent = Intent(this, CustomerDataActivity::class.java)
        intent.putExtra("client_id", item.client_id)
        startActivity(intent)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_addCustomer -> {
                val intent = Intent(this, CustomerEditorActivity::class.java)
                intent.putExtra("isEditor", false)
                startActivity(intent)
            }
            R.id.tv_search -> {
                start()
            }
            R.id.iv_right -> {
                val intent = Intent(this, PhoneAddressBookActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun start() {
        mHandler.sendEmptyMessageDelayed(0,500)
    }

    override fun onFailure(call: Call<ChooseCustomer>, t: Throwable) {
        mLayoutStatusView!!.showError()
    }

    override fun onResponse(call: Call<ChooseCustomer>, response: Response<ChooseCustomer>) {
        if (response.body()!!.code == 200 && response.body()!!.result != null) {
            mCustomerManngerAdapter.setDataList(response.body()!!.result.data)
        } else {
            mLayoutStatusView!!.showEmpty()
        }
    }
}