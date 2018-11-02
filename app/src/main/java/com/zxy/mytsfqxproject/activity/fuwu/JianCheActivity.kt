package com.zxy.mytsfqxproject.activity.fuwu

import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.FwDetailBean
import kotlinx.android.synthetic.main.activity_jian_che.*
import kotlinx.android.synthetic.main.top_view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 检车
 */
class JianCheActivity : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_jian_che
    var mFwDetailBean: FwDetailBean.ResultBean.VerifyCarDetailBean? = null
    var pamrms = HashMap<String, Any>()
    var car_id = 0
    var repair_id = ""
    val car_number = ""
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        mFwDetailBean = intent.getSerializableExtra("mFwDetailBean") as FwDetailBean.ResultBean.VerifyCarDetailBean?
        tv_header_title.text = intent.getStringExtra("car_number")
        tv_car_miles.text = "" + intent.getIntExtra("miles", 0) + "KM"
        car_id = intent.getIntExtra("car_id", 0)
        repair_id = intent.getStringExtra("repair_id")
        et_qimian.setText(mFwDetailBean!!.lacquer)
        tv_kongtiao.setText(mFwDetailBean!!.conditioner)
        et_engine.setText(mFwDetailBean!!.engine)
        et_dipan.setText(mFwDetailBean!!.chassis)
        et_lushi.setText(mFwDetailBean!!.road_test)
        et_trouble_light.setText(mFwDetailBean!!.light)
        et_light.setText(mFwDetailBean!!.lamplight)
        et_note.setText(mFwDetailBean!!.remark)
        tv_commit.setOnClickListener { jieDai() }
    }

    override fun start() {

    }

    fun jieDai() {
        pamrms["car_id"] = car_id
        pamrms["repair_id"] = repair_id
        pamrms["lacquer"] = et_qimian.text
        pamrms["conditioner"] = tv_kongtiao.text
        pamrms["engine"] = et_engine.text
        pamrms["chassis"] = et_dipan.text
        pamrms["road_test"] = et_lushi.text
        pamrms["light"] = et_trouble_light.text
        pamrms["lamplight"] = et_light.text
        pamrms["remark"] = et_note.text
        RetrofitManager.service.saveVerifyCarDetail(Tools.getRequestBody(pamrms)).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val sss = response.body()!!.toString()
                val jobj = JSONObject(sss)
                val errmsg = jobj.get("errmsg").toString()
                val code = jobj.get("code")
                if (code == 200) {
                    showToast(errmsg)
                    finish()
                } else {
                    showToast(errmsg)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            }
        })
    }
}