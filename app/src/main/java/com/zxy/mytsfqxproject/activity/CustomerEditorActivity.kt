package com.zxy.mytsfqxproject.activity

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.View
import android.widget.RadioGroup
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.View.ShowCity
import com.zxy.mytsfqxproject.View.ShowWheelView
import com.zxy.mytsfqxproject.View.ShowWheelViewTime
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.inter.OnItemWheelView
import com.zxy.mytsfqxproject.mvp.entity.JsonBean
import com.zxy.mytsfqxproject.mvp.entity.clientDetail
import kotlinx.android.synthetic.main.activity_customer_editor.*
import kotlinx.android.synthetic.main.top_view.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * 客户的编辑和新增
 */
class CustomerEditorActivity : BaseActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener, Callback<JsonObject> {
    override fun layoutId(): Int = R.layout.activity_customer_editor
    private val lavel = ArrayList<String>().apply {
        add("A")
        add("B")
        add("C")
    }
    private var options1Items = ArrayList<JsonBean>()
    private val options2Items = ArrayList<ArrayList<String>>()
    private val options3Items = ArrayList<ArrayList<ArrayList<String>>>()
    //是为编辑  否为添加
    var isEditor: Boolean = false
    private var sex: String ="男"
    private var type: String = "10"
    private var pamrms = HashMap<String, Any>()
    var item: clientDetail.ResultBean? = null
    private var thread: Thread? = null
    private var isLoaded = false
    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> if (thread == null) {
                    thread = Thread(Runnable {
                        // 子线程中解析省市区数据
                        initJsonData()
                    })
                    thread!!.start()
                }
                1 -> {
                    isLoaded = true
                }
                2 -> {
                    isLoaded = false
                }

            }
        }
    }

    override fun initView() {
        isEditor = intent.getBooleanExtra("isEditor", false)
        iv_left.setImageResource(R.mipmap.img_back)
        tv_right.text = "保存"
        if (isEditor) {
            tv_header_title.text = "修改客户"
            item = intent.getSerializableExtra("item") as clientDetail.ResultBean
        } else {
            tv_header_title.text = "新增客户"
        }
        tv_right.setOnClickListener(this)
        rg_sex.setOnCheckedChangeListener(this)
        rg_type.setOnCheckedChangeListener(this)
        et_address.setOnClickListener(this)
        et_customer_level.setOnClickListener(this)
        et_birthday.setOnClickListener(this)
        if (item != null) {
            tv_kehu_name.setText(item!!.username.toString())
            et_phone.setText(item!!.phone)
            et_birthday.text = item!!.birthday
            if (item!!.client_grade == 10) {
                et_customer_level.text = "A"
            } else if (item!!.client_grade == 20) {
                et_customer_level.text = "B"
            } else if (item!!.client_grade == 30) {
                et_customer_level.text = "C"
            }
            et_address.text = item!!.birthday
            if (item!!.sex == "男") {
                rb_nan1.isChecked = true
            } else {
                rb_nv1.isChecked = true
            }
            if (item!!.client_grade == 10) {
                rb_geren.isChecked = true
            } else {
                rb_danwei.isChecked = true
            }

        }
        mHandler.sendEmptyMessage(0)
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
    }

    override fun start() {
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_right -> {
                if (sex == null && sex == "") {
                    showToast("请选择性别")
                    return
                }
                if (type == null && type == "") {
                    showToast("请选择类别")
                    return
                }
                if (TextUtils.isEmpty(et_phone.text)) {
                    showToast("请输入手机号码")
                    return
                }
                if (TextUtils.isEmpty(et_customer_level.text)) {
                    showToast("请输入类别")
                    return
                }
                if (TextUtils.isEmpty(et_birthday.text)) {
                    showToast("请选择生日")
                    return
                }
                if (TextUtils.isEmpty(et_address.text)) {
                    showToast("请选择地址")
                    return
                }
                pamrms["username"] = tv_kehu_name.text
                pamrms["phone"] = et_phone.text
                pamrms["sex"] = sex
                pamrms["client_type"] = type
                pamrms["birthday"] = et_birthday.text
                pamrms["client_from"] = "10"
                pamrms["address"] = et_address.text
                if (et_customer_level.text == "A") {
                    pamrms["client_grade"] = 10
                } else if (et_customer_level.text == "B") {
                    pamrms["client_grade"] = 20
                } else {
                    pamrms["client_grade"] = 30
                }
                if (isEditor) {
                    pamrms["client_id"] = item!!.client_id
                    RetrofitManager.service.editClient(Tools.getRequestBody(pamrms)).enqueue(this)
                } else {
                    RetrofitManager.service.addClient(Tools.getRequestBody(pamrms)).enqueue(this)
                }
            }
            R.id.et_address -> {
                if (isLoaded) {
                    ShowCity.show(this, options1Items, options2Items, options3Items, object : OnItemWheelView {
                        override fun onItemClick(str: String) {
                            et_address.text = str
                        }
                    })
                } else {
                    showToast("加载失败")
                }
            }
            R.id.et_customer_level -> {
                ShowWheelView.show(this, lavel) { et_customer_level.text = it }
            }
            R.id.et_birthday -> {
                ShowWheelViewTime.show(this, true, true, true, false, false, "yyyy-MM-dd") { et_birthday.text = it }
            }
        }
    }

    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
        showToast(getString(R.string.http_error))
    }

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

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        if (rb_nan1.id == checkedId) {
            sex = "男"
        }
        if (rb_nv1.id == checkedId) {
            sex = "女"
        }
        if (rb_geren.id == checkedId) {
            type = "10"
        }
        if (rb_danwei.id == checkedId) {
            type = "20"
        }
    }

    /**
     * 解析本地城市数据
     */
    private fun initJsonData() {
        val JsonData = Tools.getJson(this, "province.json")//获取assets目录下的json文件数据
        val jsonBean = parseData(JsonData)//用Gson 转成实体

        options1Items = jsonBean
        for (i in jsonBean.indices) {//遍历省份
            val CityList = ArrayList<String>()//该省的城市列表（第二级）
            val Province_AreaList = ArrayList<ArrayList<String>>()//该省的所有地区列表（第三极）

            for (c in 0 until jsonBean[i].cityList.size) {//遍历该省份的所有城市
                val CityName = jsonBean[i].cityList[c].name
                CityList.add(CityName)//添加城市
                val City_AreaList = ArrayList<String>()//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean[i].cityList[c].area == null || jsonBean[i].cityList[c].area.size === 0) {
                    City_AreaList.add("")
                } else {
                    City_AreaList.addAll(jsonBean[i].cityList[c].area)
                }
                Province_AreaList.add(City_AreaList)//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList)

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList)
        }
        mHandler.sendEmptyMessage(1)
    }

    fun parseData(result: String): ArrayList<JsonBean> {//Gson 解析
        val detail = ArrayList<JsonBean>()
        try {
            val data = JSONArray(result)
            val gson = Gson()
            for (i in 0 until data.length()) {
                val entity = gson.fromJson<JsonBean>(data.optJSONObject(i).toString(), JsonBean::class.java)
                detail.add(entity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mHandler.sendEmptyMessage(2)
        }

        return detail
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacksAndMessages(null)
    }
}