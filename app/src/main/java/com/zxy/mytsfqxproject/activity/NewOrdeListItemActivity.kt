package com.zxy.mytsfqxproject.activity

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.View.ShowWheelViewTime
import com.zxy.mytsfqxproject.adapter.BespeakServiceListAdapter
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.BespeakServiceBean
import com.zxy.mytsfqxproject.mvp.entity.CarListDetailBean
import com.zxy.mytsfqxproject.mvp.entity.NewAddOrderBean
import com.zxy.mytsfqxproject.recyclerView.OnClickLister
import kotlinx.android.synthetic.main.activity_new_order_item.*
import kotlinx.android.synthetic.main.top_view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 新建预约单的列表item
 */
class NewOrdeListItemActivity : BaseActivity(), View.OnClickListener {
    override fun layoutId(): Int = R.layout.activity_new_order_item
    var mNewAddOrderBeanitem: NewAddOrderBean.ResultBean.DataBean? = null
    private var mBespeakServiceBean = ArrayList<BespeakServiceBean.ResultBean>()
    private lateinit var mBespeakServiceListAdapter: BespeakServiceListAdapter
    var pamrms = HashMap<String, Any>()
    var isNewAdd = false
    var carID = ""
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        isNewAdd = intent.getBooleanExtra("newAdd", false)
        iv_left.setImageResource(R.mipmap.img_back)
        tv_header_title.text = "新建预约单"
        tv_right.text = "保存"
        iv_left.setOnClickListener(this)
        tv_right.setOnClickListener(this)
        if (isNewAdd) {
            carID = intent.getStringExtra("car_id")
        } else {
            mNewAddOrderBeanitem = intent.getSerializableExtra("item") as NewAddOrderBean.ResultBean.DataBean
            tv_username.text = mNewAddOrderBeanitem!!.username
            tv_car_num.text = mNewAddOrderBeanitem!!.car_number
            Glide.with(this).load(mNewAddOrderBeanitem!!.brand_logo).into(iv_car_icon)
            tv_phone.text = mNewAddOrderBeanitem!!.phone
            carID = mNewAddOrderBeanitem!!.car_number
        }
        tv_yuyue_time.setOnClickListener(this)
        tv_fuwuname.setOnClickListener(this)
        tv_sure.setOnClickListener(this)
        mBespeakServiceListAdapter = BespeakServiceListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mBespeakServiceListAdapter
        mBespeakServiceListAdapter.setLister(object : OnClickLister<BespeakServiceBean.ResultBean> {
            override fun onItemAddClick(item: BespeakServiceBean.ResultBean, position: Int) {
                mBespeakServiceBean.add(item)
            }

            override fun onItemRemoveClick(item: BespeakServiceBean.ResultBean, position: Int) {
                mBespeakServiceBean.remove(item)
            }
        })
    }

    override fun start() {
        if (isNewAdd) {
            pamrms.clear()
            pamrms["car_id"] = carID
            RetrofitManager.service.CarDetail(Tools.getRequestBody(pamrms)).enqueue(object : Callback<CarListDetailBean> {
                override fun onFailure(call: Call<CarListDetailBean>, t: Throwable) {
                }

                override fun onResponse(call: Call<CarListDetailBean>, response: Response<CarListDetailBean>) {
                    if (response.body()!!.code == 200) {
                        val result = response.body()!!.result
                        tv_username.text = result.username
                        tv_car_num.text = result.car_number
                        Glide.with(this@NewOrdeListItemActivity).load(result.brand_logo).into(iv_car_icon)
                        tv_phone.text = result.phone
                    }
                }
            })
        }
        RetrofitManager.service.bespeakServiceList().enqueue(object : Callback<BespeakServiceBean> {
            override fun onFailure(call: Call<BespeakServiceBean>, t: Throwable) {
            }

            override fun onResponse(call: Call<BespeakServiceBean>, response: Response<BespeakServiceBean>) {
                if (response.body()!!.code == 200) {
                    mBespeakServiceListAdapter.setDataList(response.body()!!.result)
                }
            }
        })
    }

    @SuppressLint("RtlHardcoded")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_right -> {
                pamrms.clear()
                pamrms["id"] = carID
                pamrms["phone"] = tv_phone.text
                if (mBespeakServiceBean.size <= 0) {
                    showToast("请选择服务类别")
                    return
                }
                var id = ""
                for (i in 0 until mBespeakServiceBean.size) {
                    id = if (i > 0) {
                        id + "," + mBespeakServiceBean[i].id
                    } else {
                        id + "" + mBespeakServiceBean[i].id
                    }
                }
                pamrms["serve_id"] = id
                if (TextUtils.isEmpty(tv_yuyue_time.text)) {
                    showToast("请选择到店时间")
                    return
                }
                pamrms["bespeak_time"] = tv_yuyue_time.text
                RetrofitManager.service.createBespeak(Tools.getRequestBody(pamrms)).enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        val sss = response.body()!!.toString()
                        val jobj = JSONObject(sss)
                        val errmsg = jobj.optString("errmsg")
                        val code = jobj.getInt("code")
                        if (code == 200) {
                            showToast(errmsg)
                            finish()
                        } else {
                            showToast(errmsg)
                        }
                    }
                })
            }
            R.id.tv_fuwuname -> {
                drawer_layout.openDrawer(Gravity.RIGHT)
            }
            R.id.tv_yuyue_time -> {
                ShowWheelViewTime.show(this, true, true, true, true, false, "yyyy-MM-dd HH:mm")
                { tv_yuyue_time.text = it }
            }
            R.id.tv_sure -> {
                var str = ""
                if (mBespeakServiceBean.size > 0) {
                    for (i in 0 until mBespeakServiceBean.size) {
                        str = str + " " + mBespeakServiceBean[i].title
                    }
                    tv_fuwuname.text = str
                }
                drawer_layout.closeDrawer(Gravity.RIGHT)
            }
        }
    }
}