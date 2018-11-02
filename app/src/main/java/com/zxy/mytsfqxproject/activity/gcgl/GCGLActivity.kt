package com.zxy.mytsfqxproject.activity.gcgl

import android.support.v4.app.Fragment
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.base.BaseFragmentAdapter
import com.zxy.mytsfqxproject.fragment.childfragment.ProcessFragment
import kotlinx.android.synthetic.main.activity_gcgl.*
import kotlinx.android.synthetic.main.top_view.*

/**
 * 过程管理
 */
class GCGLActivity : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_gcgl
    private val mTabTitleList = ArrayList<String>()
    private val mFragmentList = ArrayList<Fragment>()

    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "过程管理"
    }

    override fun start() {
        mTabTitleList.add("待派工")
        mTabTitleList.add("已派工")
        (0 until mTabTitleList.size).mapTo(mFragmentList) { ProcessFragment.getInstance(it.toString()) }
        mViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragmentList, mTabTitleList)
        mTabLayout.setupWithViewPager(mViewPager)
    }
}