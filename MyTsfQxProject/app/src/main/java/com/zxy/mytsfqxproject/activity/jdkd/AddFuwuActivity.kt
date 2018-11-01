package com.zxy.mytsfqxproject.activity.jdkd

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.View.TabLayoutHelper
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.base.BaseFragmentAdapter
import com.zxy.mytsfqxproject.fragment.childfragment.FuWuFragment
import com.zxy.mytsfqxproject.fragment.childfragment.PeiJianFragment
import com.zxy.mytsfqxproject.mvp.entity.FuwuBean
import com.zxy.mytsfqxproject.mvp.entity.PeiJianBean
import com.zxy.mytsfqxproject.recyclerView.OnClickLister
import kotlinx.android.synthetic.main.activity_addfuwu.*
import kotlinx.android.synthetic.main.top_view.*

class AddFuwuActivity : BaseActivity(), View.OnClickListener {
    override fun layoutId(): Int = R.layout.activity_addfuwu
    private val tabList = ArrayList<String>()
    private val fragments = ArrayList<Fragment>()
    private var page = 0
    private var mFuWuFragment: FuWuFragment? = null
    private var mPeiJianFragment: PeiJianFragment? = null
    private var mPeiJianBean = ArrayList<PeiJianBean.ResultBean.DataBean>()
    private var mFuwuBean = ArrayList<FuwuBean.ResultBean.DataBean>()
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        page = intent.getIntExtra("page", 0)
        iv_left.setImageResource(R.mipmap.img_back)
        tv_header_title.text = "添加服务"
        tv_right.text = "完成"
        iv_left.setOnClickListener(this)
        tv_right.setOnClickListener(this)
        tabList.add("服务")
        tabList.add("配件")
        mFuWuFragment = FuWuFragment()
        mPeiJianFragment = PeiJianFragment()
        fragments.add(mFuWuFragment!!)
        fragments.add(mPeiJianFragment!!)
        mViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, fragments, tabList)
        mTabLayout.setupWithViewPager(mViewPager)
        TabLayoutHelper.setUpIndicatorWidth(mTabLayout)
        mViewPager.currentItem = page
        mPeiJianFragment!!.setListner(object : OnClickLister<PeiJianBean.ResultBean.DataBean> {
            override fun onItemAddClick(item: PeiJianBean.ResultBean.DataBean, position: Int) {

                mPeiJianBean.add(item)
//                showToast(item.material_name)
            }

            override fun onItemRemoveClick(item: PeiJianBean.ResultBean.DataBean, position: Int) {
                mPeiJianBean.remove(item)
//                showToast(item.material_name)
            }
        })
        mFuWuFragment!!.setListner(object : OnClickLister<FuwuBean.ResultBean.DataBean> {
            override fun onItemAddClick(item: FuwuBean.ResultBean.DataBean, position: Int) {
                mFuwuBean.add(item)
//                showToast(item.project_price)
            }

            override fun onItemRemoveClick(item: FuwuBean.ResultBean.DataBean, position: Int) {
//                showToast(item.project_price)
                mFuwuBean.remove(item)
            }
        })
    }

    override fun start() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_right -> {
                val intent = Intent(this@AddFuwuActivity, JDKDActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelableArrayList("mPeiJianBean", mPeiJianBean)
                bundle.putParcelableArrayList("mFuwuBean", mFuwuBean)
                intent.putExtras(bundle)
                setResult(300, intent)
                finish()
            }
        }
    }

}
