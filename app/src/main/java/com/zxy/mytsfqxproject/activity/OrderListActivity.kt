package com.zxy.mytsfqxproject.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.adapter.OrderListAdapter
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.inter.OnItemWheelView
import com.zxy.mytsfqxproject.mvp.entity.OrdeListBean
import com.zxy.mytsfqxproject.mvp.entity.OrdeNumBean
import kotlinx.android.synthetic.main.activity_order_list.*
import kotlinx.android.synthetic.main.dialog_wheel_time.view.*
import kotlinx.android.synthetic.main.top_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 日历- 预约单
 */
class OrderListActivity : BaseActivity(), View.OnClickListener, Callback<OrdeListBean>, OnItemWheelView,
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnMonthChangeListener {
    override fun layoutId(): Int = R.layout.activity_order_list
    private lateinit var mOrderListAdapter: OrderListAdapter
    var pamrms = HashMap<String, Any>()
    val calenderMap = java.util.HashMap<String, Calendar>()
    var chooseDay = ""
    @SuppressLint("SetTextI18n")
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "预约单"
        iv_right.setImageResource(R.mipmap.img_add)
        iv_right.setOnClickListener(this)
        mOrderListAdapter = OrderListAdapter(this)
        mOrderListAdapter.setListener(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mOrderListAdapter
        calendarView.setFixMode()
        calendarView.setOnCalendarSelectListener(this)
        getNum()
        tv_header_title2.text = ""+calendarView.year + "年" + calendarView.curMonth + "月份"
        calendarView.setOnMonthChangeListener(this)
    }

    override fun start() {
    }

    fun getNum() {
        val year = calendarView.curYear
        val month = calendarView.curMonth
        val day = Tools.getMonthOfDay(year, month)
        pamrms["start_date"] = Tools.intByStr(year) + "-" + Tools.intByStr(month) + "-01"
        pamrms["end_date"] = Tools.intByStr(year) + "-" + Tools.intByStr(month) + "-" + Tools.intByStr(day)
        RetrofitManager.service.pandect(Tools.getRequestBody(pamrms)).enqueue(object : Callback<OrdeNumBean> {
            override fun onFailure(call: Call<OrdeNumBean>, t: Throwable) {
            }

            override fun onResponse(call: Call<OrdeNumBean>, response: Response<OrdeNumBean>) {
                if (response.body()!!.code == 200) {
                    if (response.body()!!.result.size <= 0) {
                        return
                    }
                    response.body()!!.result.forEach {
                        calenderMap.put(getSchemeCalendar(it.days, Tools.intByStr(it.count)).toString(),
                                getSchemeCalendar(it.days, Tools.intByStr(it.count)))
                    }
                    calendarView.setSchemeDate(calenderMap)
                }
            }
        })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_right -> {
                startActivity(Intent(this@OrderListActivity, NewAddOrderActivity::class.java))
            }
        }
    }


    override fun onFailure(call: Call<OrdeListBean>, t: Throwable) {
    }

    override fun onResponse(call: Call<OrdeListBean>, response: Response<OrdeListBean>) {
        if (response.body()!!.code == 200) {
            mOrderListAdapter.setDataList(response.body()!!.result.data)
            getNum()
        }
    }

    /**
     * adapter 点击取消
     */
    override fun onItemClick(str: String) {
        pamrms["day"] = chooseDay
        RetrofitManager.service.byDayBespeak(Tools.getRequestBody(pamrms)).enqueue(this@OrderListActivity)
    }

    override fun onCalendarSelect(calendar: Calendar, isClick: Boolean) {
        val year = calendar.year
        val month = calendar.month
        val days = calendar.day
        pamrms.clear()
        chooseDay = Tools.intByStr(year) + "-" + Tools.intByStr(month) + "-" + Tools.intByStr(days)
        pamrms["day"] = chooseDay
        RetrofitManager.service.byDayBespeak(Tools.getRequestBody(pamrms)).enqueue(this@OrderListActivity)
    }

    override fun onCalendarOutOfRange(calendar: Calendar?) {
    }

    private fun getSchemeCalendar(days: String, text: String): Calendar {
        val calendar = Calendar()
        calendar.year = Tools.strByInt(Tools.indexString(days)[0])
        calendar.month = Tools.strByInt(Tools.indexString(days)[1])
        calendar.day = Tools.strByInt(Tools.indexString(days)[2])
        calendar.schemeColor = resources.getColor(R.color.colorPrimary)
        calendar.scheme = text
        return calendar
    }

    @SuppressLint("SetTextI18n")
    override fun onMonthChange(year: Int, month: Int) {
        tv_header_title2.text = ""+year+"年" + month + "月份"
    }
}