package com.zxy.mytsfqxproject.fragment.childfragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.adapter.FuWuAdapter
import com.zxy.mytsfqxproject.base.BaseFragment
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.FuwuBean
import com.zxy.mytsfqxproject.recyclerView.OnClickLister
import com.zxy.mytsfqxproject.recyclerView.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_fuwu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FuWuFragment : BaseFragment(), Callback<FuwuBean>{
    private lateinit var mFuWuAdapter: FuWuAdapter
    private var pamrms = HashMap<String, Any>()
    var OnClickLister: OnClickLister<FuwuBean.ResultBean.DataBean>? = null

    fun setListner(OnClickLister: OnClickLister<FuwuBean.ResultBean.DataBean>) {
        this.OnClickLister = OnClickLister
    }

    override fun getLayoutId(): Int = R.layout.fragment_fuwu

    override fun initView(view: View) {
        mLayoutStatusView = multipleStatusView
        mFuWuAdapter = FuWuAdapter(activity)
        mFuWuAdapter.setLister(OnClickLister)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mFuWuAdapter
        starLoad()
    }

    fun starLoad() {
        pamrms.clear()
        pamrms["type"] = "project"
        RetrofitManager.service.searchProjectOrMaterial(Tools.getRequestBody(pamrms)).enqueue(this)

    }

    override fun lazyLoad() {
    }

    override fun onFailure(call: Call<FuwuBean>, t: Throwable) {
        mLayoutStatusView!!.showError()
    }

    override fun onResponse(call: Call<FuwuBean>, response: Response<FuwuBean>) {
        if (response.body()!!.code == 200 && response.body()!!.result != null) {
            if (response.body()!!.result.data != null && response.body()!!.result.data.size > 0) {
                mFuWuAdapter.setDataList(response.body()!!.result.data)
            } else {
                mLayoutStatusView!!.showEmpty()
            }
        } else {
            mLayoutStatusView!!.showError()
        }
    }
}