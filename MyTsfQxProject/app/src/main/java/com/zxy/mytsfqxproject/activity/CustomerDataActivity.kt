package com.zxy.mytsfqxproject.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.adapter.CarListAdapter
import com.zxy.mytsfqxproject.adapter.CardListAdapter
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.clientDetail
import com.zxy.mytsfqxproject.recyclerView.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_customer_data.*
import kotlinx.android.synthetic.main.top_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 客户资料
 */
class CustomerDataActivity : BaseActivity(), View.OnClickListener, Callback<clientDetail> {
    override fun layoutId(): Int = R.layout.activity_customer_data
    var item: clientDetail.ResultBean? = null
    var pamrms = HashMap<String, Any>()
    private lateinit var mCarListAdapter: CarListAdapter
    private lateinit var mCardListAdapter: CardListAdapter
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        tv_header_title.text = "客户资料"
        tv_right.text = "编辑"
        iv_left.setOnClickListener(this)
        tv_right.setOnClickListener(this)
        tv_kaika.setOnClickListener(this)
        tv_cardetail.setOnClickListener(this)
        pamrms["client_id"] = intent.getIntExtra("client_id", 0)
        mCarListAdapter = CarListAdapter(this)
        mCardListAdapter = CardListAdapter(this)
        rv_carlist.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        rv_carlist.adapter = mCarListAdapter
        rv_cardList.layoutManager = LinearLayoutManager(this)
        rv_cardList.adapter = mCardListAdapter
        initListner()
    }

    fun initListner() {
        mCarListAdapter.onRecyclerItemClickListener = OnRecyclerItemClickListener<clientDetail.ResultBean.CarListBean> { v, item, position ->
            val intent = Intent(this@CustomerDataActivity, CarDetailActivity::class.java)
            intent.putExtra("car_id", item.id)
            startActivity(intent)
        }
        mCardListAdapter.onRecyclerItemClickListener = object : OnRecyclerItemClickListener<clientDetail.ResultBean.CardListBean> {
            override fun onItemClick(v: View, item: clientDetail.ResultBean.CardListBean, position: Int) {
                val intent = Intent(this@CustomerDataActivity, CardDetailsActivity::class.java)
                intent.putExtra("mem_id", item.mem_id)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        RetrofitManager.service.clientDetail(Tools.getRequestBody(pamrms)).enqueue(this)
    }

    override fun start() {
    }

    override fun onFailure(call: Call<clientDetail>, t: Throwable) {
    }

    override fun onResponse(call: Call<clientDetail>, response: Response<clientDetail>) {
        if (response.body()!!.result != null) {
            setclientDetail(response.body()!!)
            mCarListAdapter.setDataList(response.body()!!.result.car_list)
            mCardListAdapter.setDataList(response.body()!!.result.card_list)
        }
    }

    fun setclientDetail(mclientDetail: clientDetail) {
        item = mclientDetail.result
        tv_name.text = mclientDetail.result.username
        if (mclientDetail.result.sex == "男") {
            iv_sex.setImageResource(R.mipmap.img_nan)
        } else {
            iv_sex.setImageResource(R.mipmap.img_nv)
        }
        when (mclientDetail.result.client_grade) {
            10 -> iv_level.setImageResource(R.mipmap.img_level)
            20 -> iv_level.setImageResource(R.mipmap.img_level_b)
            30 -> iv_level.setImageResource(R.mipmap.img_level_c)
        }
        tv_phone.text = mclientDetail.result.phone
        tv_bir.text = mclientDetail.result.birthday
        tv_dizhi.text = mclientDetail.result.address
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_right -> {
                val intent = Intent(this, CustomerEditorActivity::class.java)
                intent.putExtra("item", item)
                intent.putExtra("isEditor", true)
                startActivity(intent)
            }
            R.id.tv_kaika -> {
                val intent = Intent(this, OpenCardActivity::class.java)
                intent.putExtra("client_id", item!!.client_id)
                startActivity(intent)
            }
            R.id.tv_cardetail -> {
                val intent = Intent(this, AddCarActivity::class.java)
                intent.putExtra("client_id", item!!.client_id)
                startActivity(intent)
            }
        }
    }
}