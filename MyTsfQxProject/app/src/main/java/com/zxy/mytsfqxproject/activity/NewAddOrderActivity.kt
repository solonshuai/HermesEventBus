package com.zxy.mytsfqxproject.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.adapter.NewAddOrderAdapter
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.NewAddOrderBean
import com.zxy.mytsfqxproject.recyclerView.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_new_addorder.*
import kotlinx.android.synthetic.main.top_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 新建预约单
 */
class NewAddOrderActivity : BaseActivity(), View.OnClickListener, Callback<NewAddOrderBean>, OnRecyclerItemClickListener<NewAddOrderBean.ResultBean.DataBean> {
    override fun layoutId(): Int = R.layout.activity_new_addorder
    private lateinit var mNewAddOrderAdapter: NewAddOrderAdapter
    var pamrms = HashMap<String, Any>()

    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        tv_header_title.text = "新建预约单"
        tv_right.text = "添加新车"
        iv_left.setOnClickListener(this)
        tv_right.setOnClickListener(this)
        mNewAddOrderAdapter = NewAddOrderAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mNewAddOrderAdapter
        tv_search.setOnClickListener(this)
        mNewAddOrderAdapter.onRecyclerItemClickListener = this
    }

    override fun start() {
        pamrms["key"] = et_search.text
        RetrofitManager.service.lists(Tools.getRequestBody(pamrms)).enqueue(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_right -> {
                startActivity(Intent(this@NewAddOrderActivity, NewOrdeAddActivity::class.java))
            }
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_search -> {
                start()
            }
        }
    }

    override fun onFailure(call: Call<NewAddOrderBean>, t: Throwable) {
    }

    override fun onResponse(call: Call<NewAddOrderBean>, response: Response<NewAddOrderBean>) {
        if (response.body()!!.code == 200) {
            mNewAddOrderAdapter.setDataList(response.body()!!.result.data)
        }
    }

    override fun onItemClick(v: View, item: NewAddOrderBean.ResultBean.DataBean, position: Int) {
        val intent = Intent(this@NewAddOrderActivity, NewOrdeListItemActivity::class.java)
        intent.putExtra("newAdd", false)
        intent.putExtra("item", item)
        startActivity(intent)

    }
}
