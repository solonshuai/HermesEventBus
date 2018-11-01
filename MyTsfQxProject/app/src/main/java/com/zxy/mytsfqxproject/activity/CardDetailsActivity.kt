package com.zxy.mytsfqxproject.activity

import android.view.View
import android.widget.TextView
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.CardDetailBean
import kotlinx.android.synthetic.main.activity_card_detail.*
import kotlinx.android.synthetic.main.top_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 会员卡详情
 */
class CardDetailsActivity : BaseActivity(), View.OnClickListener, Callback<CardDetailBean> {
    override fun layoutId(): Int = R.layout.activity_card_detail
    var pamrms = HashMap<String, Any>()

    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener(this)
        tv_header_title.text = "会员卡详情"
        pamrms["mem_id"] = intent.getIntExtra("mem_id", 0)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_left -> {
                finish()
            }
        }
    }

    override fun start() {
        RetrofitManager.service.getCardDetails(Tools.getRequestBody(pamrms)).enqueue(this)
    }

    override fun onFailure(call: Call<CardDetailBean>, t: Throwable) {
    }

    override fun onResponse(call: Call<CardDetailBean>, response: Response<CardDetailBean>) {
        if (response.body()!!.code == 200) {
            val cardDetailBean = response.body()!!.result
            tv_card_num.text="卡号"+cardDetailBean.card_number
            tv_card_type.text="卡名"+cardDetailBean.card_title
            tv_money.text="￥"+cardDetailBean.money
            tv_name.text=cardDetailBean.username
            tv_phone.text=cardDetailBean.phone
            tv_car_time.text=Tools.getTime(cardDetailBean.create_time,"yyyy-MM-dd HH:mm:ss")
            if(cardDetailBean.project_list.size>0){
//                val tv = TextView(mContext) layout=item_card_wufu
//                tv.setText(item.getGoods_name().get(i))
//                tv.setBackgroundResource(R.drawable.shape_gary)
//                tv.setPadding(5, 0, 5, 0)
//                linear.addView(tv)
//                val tv1 = TextView(mContext)
//                tv1.text = " "
//                linear.addView(tv1)
            }
        }
    }
}