package com.zxy.mytsfqxproject.activity.fuwu

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Message
import android.view.View
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.View.CustomDialog
import com.zxy.mytsfqxproject.activity.CarDetailActivity
import com.zxy.mytsfqxproject.activity.CustomerDataActivity
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.FwDetailBean
import com.zxy.mytsfqxproject.mvp.entity.RankFuddBean
import kotlinx.android.synthetic.main.activity_fw_detail.*
import kotlinx.android.synthetic.main.top_view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 服务订单--详情
 */
class FwDetailActivity : BaseActivity(), Callback<FwDetailBean>, View.OnClickListener {
    override fun layoutId(): Int = R.layout.activity_fw_detail
    var item: RankFuddBean.ResultBean.DataBean? = null
    var pamrms = HashMap<String, Any>()
    var phone: String = ""
    var fwDetailBean: FwDetailBean.ResultBean? = null
    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> {

                }
            }
        }
    }

    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_right.setImageResource(R.mipmap.img_phone)
        item = intent.getSerializableExtra("item") as RankFuddBean.ResultBean.DataBean
        tv_header_title.text = item!!.car_number
        iv_left.setOnClickListener { finish() }
        layout_customer.setOnClickListener(this)
        layout_car.setOnClickListener(this)
        layout_history.setOnClickListener(this)
        iv_right.setOnClickListener(this)
        tv_chakan.setOnClickListener(this)
        tv_jian_scr.setOnClickListener(this)
        tv_jianche.setOnClickListener(this)
        tv_fuwu_add.setOnClickListener(this)
        tv_paigong.setOnClickListener(this)
        tv_jiezhang.setOnClickListener(this)
    }

    override fun start() {
    }

    override fun onResume() {
        super.onResume()
        pamrms["repair_id"] = item!!.repair_id
        RetrofitManager.service.appRepairFlow(Tools.getRequestBody(pamrms)).enqueue(this)
    }

    override fun onFailure(call: Call<FwDetailBean>, t: Throwable) {
        showToast(t.toString())
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.layout_customer -> {
                val intent = Intent(this, CustomerDataActivity::class.java)
                intent.putExtra("client_id", item!!.client_id)
                startActivity(intent)
            }
            R.id.layout_car -> {
                val intent = Intent(this, CarDetailActivity::class.java)
                intent.putExtra("car_id", item!!.car_id)
                startActivity(intent)
            }
            R.id.layout_history -> {
                val intent = Intent(this, IntoShopActivity::class.java)
                intent.putExtra("car_id", item!!.car_id)
                startActivity(intent)
            }
            R.id.iv_right -> {
                AlertMsg(phone)
            }
            R.id.tv_chakan -> {
                val intent = Intent(this, JieCheDetailActivity::class.java)
                intent.putExtra("repair_id", item!!.repair_id)
                startActivity(intent)
            }
            R.id.tv_jian_scr -> {
                val intent = Intent(this, JianCheDetailActivity::class.java)
                intent.putExtra("repair_id", item!!.repair_id)
                intent.putExtra("car_id", item!!.car_id)
                startActivity(intent)
            }
            R.id.tv_jianche -> {
                val intent = Intent(this, JianCheActivity::class.java)
                intent.putExtra("mFwDetailBean", fwDetailBean!!.verify_car_detail)
                intent.putExtra("car_id", fwDetailBean!!.repair_info.car_id)
                intent.putExtra("repair_id", fwDetailBean!!.repair_id)
                intent.putExtra("car_number", fwDetailBean!!.repair_info.car_number)
                intent.putExtra("miles", fwDetailBean!!.repair_info.miles)
                startActivity(intent)
            }
            R.id.tv_fuwu_add -> {
                val intent = Intent(this, FwAddActivity::class.java)
                intent.putExtra("repair_id", fwDetailBean!!.repair_id)
                startActivity(intent)
            }
            R.id.tv_paigong -> {
                val intent = Intent(this, FwddTaskingActivity::class.java)
                intent.putExtra("repair_id", fwDetailBean!!.repair_id)
                intent.putExtra("car_number", fwDetailBean!!.repair_info.car_number)
                startActivity(intent)
            }
            R.id.tv_jiezhang -> {
                if (fwDetailBean!!.repair_state == 60) {
                    val intent = Intent(this, SettlementActivity::class.java)
                    intent.putExtra("client_id", fwDetailBean!!.repair_info.client_id)
                    intent.putExtra("repair_id", fwDetailBean!!.repair_id)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, FwOfferActivity::class.java)
                    intent.putExtra("repair_id", fwDetailBean!!.repair_id)
                    intent.putExtra("client_id", fwDetailBean!!.repair_info.client_id)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onResponse(call: Call<FwDetailBean>, response: Response<FwDetailBean>) {
        if (response.body()!!.code == 200) {
            val repair_state = response.body()!!.result.repair_state
            fwDetailBean = response.body()!!.result
            phone = fwDetailBean!!.repair_info.phone
            tv_beizhu.text = "嘱咐：" + fwDetailBean!!.repair_info.remark
            tv_time.text = fwDetailBean!!.repair_info.create_time
            var goods = ""
            try {
                if (fwDetailBean!!.goods_info.goodsNameList != null && fwDetailBean!!.goods_info.goodsNameList.size > 0) {
                    for (i in 0 until fwDetailBean!!.goods_info.goodsNameList.size) {
                        goods += fwDetailBean!!.goods_info.goodsNameList[i]
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            tv_fuwu.text = goods
            tv_fuwu_money.text = "￥" + fwDetailBean!!.goods_info.goodsAmount
            tv_paigong_num.text = "已派：" + fwDetailBean!!.goods_assign.goodsYesAssign + "\n" + "待派：" + fwDetailBean!!.goods_assign.goodsNotAssign
            tv_shou.text = "￥" + fwDetailBean!!.goods_info.goodsAmount
            tv_shishou.text = "￥" + fwDetailBean!!.total_amount
            var car_verdict = ""
            if (fwDetailBean!!.verify_car.qc_user == null) return
            for (i in 0 until fwDetailBean!!.verify_car.verify_car_verdict.size) {
                car_verdict += fwDetailBean!!.verify_car.verify_car_verdict[i] + "; "
            }
            tv_jian_scr.text = car_verdict
            RetrofitManager.service.yesProjectuUpdate().enqueue(object : Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                }

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    val sss = response.body()!!.toString()
                    val jobj = JSONObject(sss)
                    val errmsg = jobj.optString("errmsg")
                    val code = jobj.optInt("code")
                    if (code == 200) {
                        val string = jobj.getJSONArray("result")
                        for (i in 0 until string.length()) {
                            if (repair_state == string[i]) {
                                iv_jiezhang.setImageResource(R.mipmap.img_red_yuan)
                                iv_tiche.setImageResource(R.mipmap.img_red_yuan)
                                tv_jiezhang.setBackgroundResource(R.mipmap.img_bg_red)
                            }
                        }
                    } else {
                        showToast(errmsg)
                    }
                }
            })
        }
    }

    private fun AlertMsg(msg: String) {
        val alert = CustomDialog.Builder(this)
        alert.setTitle("拨打电话")
        alert.setMessage(msg)
        alert.setishidd(false)
        alert.setDialogOut(false)
        alert.setPositiveButton("确定") { _, _ ->
            //            Intent intent = new Intent(Intent.ACTION_CALL)  需要动态申请权限
            val intent = Intent(Intent.ACTION_DIAL)
            val data = Uri.parse("tel:" + msg)
            intent.data = data
            startActivity(intent)
        }
        alert.setNegativeButton("取消", DialogInterface.OnClickListener { dialogInterface, i ->
        })
        alert.show()
    }
}