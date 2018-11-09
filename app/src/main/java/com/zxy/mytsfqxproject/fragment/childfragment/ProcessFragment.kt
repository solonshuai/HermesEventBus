package com.zxy.mytsfqxproject.fragment.childfragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.activity.fuwu.FwddTaskingActivity
import com.zxy.mytsfqxproject.adapter.ProcessAdapter
import com.zxy.mytsfqxproject.base.BaseFragment
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.GcglBean
import com.zxy.mytsfqxproject.recyclerView.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_fuwu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 过程管理
 */
class ProcessFragment : BaseFragment(), Callback<GcglBean>, OnRecyclerItemClickListener<GcglBean.ResultBean> {
    override fun getLayoutId(): Int = R.layout.fragment_fuwu
    var pamrms = HashMap<String, Any>()
    private lateinit var mProcessAdapter: ProcessAdapter
    private var isassgin = ""
    private val mList = ArrayList<GcglBean.ResultBean>()
    private val mList1 = ArrayList<GcglBean.ResultBean>()

    companion object {
        fun getInstance(isassgin: String): ProcessFragment {
            val fragment = ProcessFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.isassgin = isassgin
            return fragment
        }
    }

    override fun initView(view: View) {
        mLayoutStatusView = multipleStatusView
        mProcessAdapter = ProcessAdapter(activity)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mProcessAdapter
        mProcessAdapter.onRecyclerItemClickListener = this
        RetrofitManager.service.storeRepairList().enqueue(this)
    }

    override fun lazyLoad() {
    }

    override fun onFailure(call: Call<GcglBean>, t: Throwable) {
    }

    override fun onResponse(call: Call<GcglBean>, response: Response<GcglBean>) {
        mList.clear()
        mList1.clear()
        if (response.body()!!.code == 200) {
            if (response.body()!!.result.size > 0) {
                response.body()!!.result.forEach {
                    if (isassgin == "0" && it.assgin == "not") {
                        mList.add(it)
                    } else {
                        mList1.add(it)
                    }
                }
                if (isassgin == "0") {
                    mProcessAdapter.setDataList(mList)
                } else {
                    mProcessAdapter.setDataList(mList1)
                }
            } else {
                mLayoutStatusView!!.showEmpty()
            }
        } else {
            mLayoutStatusView!!.showError()
        }
    }

    override fun onItemClick(v: View, item: GcglBean.ResultBean, position: Int) {
        if (isassgin == "0") {
            val intent = Intent(activity, FwddTaskingActivity::class.java)
            intent.putExtra("repair_id", Tools.intByStr(item.repair_id))
            intent.putExtra("car_number", item.car_number)
            startActivity(intent)
        }
    }
}