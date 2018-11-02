package com.zxy.mytsfqxproject.activity.fuwu

import android.content.Intent
import android.support.v4.app.Fragment
import android.view.View
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.base.BaseFragmentAdapter
import com.zxy.mytsfqxproject.fragment.childfragment.RankFragment
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.ConfigurationInfor
import kotlinx.android.synthetic.main.activity_fwdd.*
import kotlinx.android.synthetic.main.top_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 服务订单
 */
class FWDDActivity : BaseActivity(), View.OnClickListener {
    override fun layoutId(): Int = R.layout.activity_fwdd
    /**
     * 存放 tab 标题
     */
    private val mTabTitleList = ArrayList<String>()

    private val mFragmentList = ArrayList<Fragment>()
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        tv_header_title.text = "服务订单"
        iv_left.setOnClickListener(this)
        iv_right.setImageResource(R.mipmap.img_light_search)
        iv_right.setOnClickListener(this)
        mLayoutStatusView = multipleStatusView
    }

    override fun start() {
        mLayoutStatusView!!.showLoading()
        RetrofitManager.service.tsfDefault().enqueue(object : Callback<ConfigurationInfor> {
            override fun onFailure(call: Call<ConfigurationInfor>, t: Throwable) {
            }

            override fun onResponse(call: Call<ConfigurationInfor>, response: Response<ConfigurationInfor>) {
                if (response.body()!!.code == 200) {
                    setTabInfo(response.body()!!)
                } else {
                    multipleStatusView.showNoNetwork()
                }
                mLayoutStatusView!!.showContent()
            }
        })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.iv_right -> {
                startActivity(Intent(this@FWDDActivity, FwddSearchActivity::class.java))
            }
        }
    }

    fun setTabInfo(tabInfo: ConfigurationInfor) {
        tabInfo.result.repair_state.mapTo(mTabTitleList) { it.content }
        tabInfo.result.repair_state.mapTo(mFragmentList) { RankFragment.getInstance(it.state) }
        mViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragmentList, mTabTitleList)
        mTabLayout.setupWithViewPager(mViewPager)
    }
}