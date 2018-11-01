package com.zxy.mytsfqxproject.activity.jdkd

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.View.PinyinComparator
import com.zxy.mytsfqxproject.View.SideBar
import com.zxy.mytsfqxproject.View.TitleItemDecoration
import com.zxy.mytsfqxproject.adapter.CarBrandDetailAdapter
import com.zxy.mytsfqxproject.adapter.CarSortAdapter
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.CarBean
import com.zxy.mytsfqxproject.mvp.entity.SortModel
import com.zxy.mytsfqxproject.recyclerView.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_car_brand.*
import kotlinx.android.synthetic.main.top_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


/**
 * 车辆品牌列表
 */
class CarBrandActivity : BaseActivity(), Callback<CarBean>, View.OnClickListener, SideBar.OnTouchingLetterChangedListener, OnRecyclerItemClickListener<SortModel> {
    override fun layoutId(): Int = R.layout.activity_car_brand
    private var manager: LinearLayoutManager? = null
    private var pamrms = HashMap<String, Any>()
    /**
     * 根据拼音来排列RecyclerView里面的数据类
     */
    private var mComparator: PinyinComparator? = null
    private var mDateList: List<SortModel>? = null
    private var mDecoration: TitleItemDecoration? = null
    private var mSortModel: SortModel? = null
    private lateinit var mCarSortAdapter: CarSortAdapter
    private lateinit var mCarBrandDetailAdapter: CarBrandDetailAdapter
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener(this)
        tv_header_title.text = "选择车辆品牌"
        mComparator = PinyinComparator()
        mCarSortAdapter = CarSortAdapter(this)
        mCarBrandDetailAdapter = CarBrandDetailAdapter(this)
        manager = LinearLayoutManager(this)
        mRecyclerView_detail.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        mRecyclerView_detail.adapter = mCarBrandDetailAdapter
        mRecyclerView.layoutManager = manager
        mRecyclerView.adapter = mCarSortAdapter
        sb_list.setOnTouchingLetterChangedListener(this)
        mCarSortAdapter.onRecyclerItemClickListener = this
        mCarBrandDetailAdapter.onRecyclerItemClickListener = OnRecyclerItemClickListener<CarBean.ResultBean> { v, item, position ->
            val intent = Intent(this@CarBrandActivity, JDKDActivity::class.java)
            intent.putExtra("car_detail", item)
            intent.putExtra("car", mSortModel)
            setResult(200, intent)
            finish()
        }
        //禁止手势滑动
        drawer_layout .setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }


    override fun start() {
        RetrofitManager.service.appCarBrandList().enqueue(object : Callback<CarBean> {
            override fun onFailure(call: Call<CarBean>, t: Throwable) {
            }

            override fun onResponse(call: Call<CarBean>, response: Response<CarBean>) {
                if (response.body()!!.result != null && response.body()!!.result.size > 0) {
                    mDateList = filledData(response.body()!!)
                    mDecoration = TitleItemDecoration(this@CarBrandActivity, mDateList)
                    mRecyclerView.addItemDecoration(mDecoration)
//            mRecyclerView.addItemDecoration(DividerItemDecoration(this@CarBrandActivity, DividerItemDecoration.VERTICAL))
                    mCarSortAdapter.setDataList(mDateList)
                } else {

                }
            }
        })
    }

    override fun onFailure(call: Call<CarBean>, t: Throwable) {
    }

    override fun onResponse(call: Call<CarBean>, response: Response<CarBean>) {
        if (response.body()!!.result != null && response.body()!!.result.size > 0) {
            mCarBrandDetailAdapter.setDataList(response.body()!!.result)
            drawer_layout.openDrawer(Gravity.RIGHT)
            //打开手势滑动
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }

    /**
     * 为RecyclerView填充数据
     *
     * @param date
     * @return
     */
    private fun filledData(date: CarBean): List<SortModel> {
        val mSortList = ArrayList<SortModel>()
        for (i in date.result.indices) {
            val sortModel = SortModel()
            sortModel.name = date.result[i].brand_name
            sortModel.imgs = date.result[i].brand_logo
            sortModel.brand_id = date.result[i].brand_id
            val sortString = date.result[i].brand_category
            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]".toRegex())) {
                sortModel.letters = sortString.toUpperCase()
            } else {
                sortModel.letters = "#"
            }
            mSortList.add(sortModel)
        }
        return mSortList
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_left -> {
                finish()
            }
        }
    }

    override fun onTouchingLetterChanged(letter: String) {
        //该字母首次出现的位置
        val position = mCarSortAdapter.getPositionForSection(letter[0].toInt())
        if (position != -1) {
            manager!!.scrollToPositionWithOffset(position, 0)
        }
    }

    @SuppressLint("RtlHardcoded")
    override fun onItemClick(v: View, item: SortModel, position: Int) {
        mSortModel = item
        pamrms["brand_pid"] = item.brand_id
        tv_car_title.text = item.name
        RetrofitManager.service.CarBranddetails(Tools.getRequestBody(pamrms)).enqueue(this)
    }
}