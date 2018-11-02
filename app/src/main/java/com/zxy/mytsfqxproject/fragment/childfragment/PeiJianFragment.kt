package com.zxy.mytsfqxproject.fragment.childfragment

import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.adapter.FuwuPeijianAdapter
import com.zxy.mytsfqxproject.base.BaseFragment
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.PeiJianBean
import com.zxy.mytsfqxproject.recyclerView.OnClickLister
import kotlinx.android.synthetic.main.fragment_peijian.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PeiJianFragment : BaseFragment(), Callback<PeiJianBean>, View.OnClickListener {
    private lateinit var mFuwuPeijianAdapter: FuwuPeijianAdapter
    private var pamrms = HashMap<String, Any>()
    var OnClickLister: OnClickLister<PeiJianBean.ResultBean.DataBean>? = null

    fun setListner(OnClickLister: OnClickLister<PeiJianBean.ResultBean.DataBean>) {
        this.OnClickLister = OnClickLister
    }

    override fun getLayoutId(): Int = R.layout.fragment_peijian

    override fun initView(view: View) {
        mLayoutStatusView = multipleStatusView
        mFuwuPeijianAdapter = FuwuPeijianAdapter(activity)
        mFuwuPeijianAdapter.setLister(OnClickLister)
        mRecyclerView.adapter = mFuwuPeijianAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        SmartRefreshLayout.refreshHeader = ClassicsHeader(this.activity).setSpinnerStyle(SpinnerStyle.Scale)
        SmartRefreshLayout.refreshFooter = ClassicsFooter(this.activity).setSpinnerStyle(SpinnerStyle.Scale)
        tv_search.setOnClickListener(this)
        starLoad()
    }

    fun starLoad() {
        if (TextUtils.isEmpty(et_search.text)) {
            pamrms.clear()
            RetrofitManager.service.searchProjectPeijian().enqueue(this)
        } else {
            pamrms["keyword"] = et_search.text
            RetrofitManager.service.searchProjectPeijian(Tools.getRequestBody(pamrms)).enqueue(this)
        }
    }

    override fun lazyLoad() {
    }

    override fun onFailure(call: Call<PeiJianBean>, t: Throwable) {
        mLayoutStatusView!!.showError()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_search -> {
                pamrms["keyword"] = et_search.text
                starLoad()
            }
        }
    }

    override fun onResponse(call: Call<PeiJianBean>, response: Response<PeiJianBean>) {
        if (response.body()!!.code == 200 && response.body()!!.result != null) {
            if (response.body()!!.result.data != null && response.body()!!.result.data.size > 0) {
                mFuwuPeijianAdapter.setDataList(response.body()!!.result.data)
            } else {
                mLayoutStatusView!!.showEmpty()
            }
        } else {
            mLayoutStatusView!!.showError()
        }
    }

}