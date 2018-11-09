package com.zxy.mytsfqxproject.activity.jdkd

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.text.TextUtils
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import com.etop.PLDemo.EtScanPlateActivity
import com.etop.utils.*
import com.etop.vincode.EtVinScanActivity
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.KeyboardUtil
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.View.ShowWheelView
import com.zxy.mytsfqxproject.View.ShowWheelViewTime
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.Presenter.JdkDPresenter
import com.zxy.mytsfqxproject.mvp.contract.JdKdContract
import com.zxy.mytsfqxproject.mvp.entity.*
import kotlinx.android.synthetic.main.activity_jdkd.*
import kotlinx.android.synthetic.main.top_view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Method
import java.util.*
import kotlin.collections.HashMap


/**
 * 接单开单
 */
class JDKDActivity : BaseActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener, View.OnFocusChangeListener, JdKdContract.View {
    private val mJdkDPresenter by lazy { JdkDPresenter() }
    var pamrms = HashMap<String, Any>()
    private val youBiaoList = ArrayList<String>().apply {
        add("空")
        add("少于1/4")
        add("1/4")
        add("1/2")
        add("3/4")
        add("满")
    }
    var mChooseCustomeritem: ChooseCustomer.ResultBean.DataBean? = null
    var mCarBeanResultBean: CarBean.ResultBean? = null
    var mSortModel: SortModel? = null //品牌
    var client_id: String? = null//客户的Id
    var car_id: String = ""
    var sex: String = "男"
    var send_sex: String = "男"
    var isExists = false//客户是否存在
    private var mPeiJianBean = ArrayList<PeiJianBean.ResultBean.DataBean>()
    private var mFuwuBean = ArrayList<FuwuBean.ResultBean.DataBean>()
    override fun layoutId(): Int = R.layout.activity_jdkd
    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> {//添加接单开单的服务项目
                    if (mFuwuBean.size > 0) {
                        var fuwuText = " ，"
                        mFuwuBean.forEach {
                            fuwuText += it.project_title
                        }
                        tv_fuwu.text = fuwuText
                    } else {
                        tv_fuwu.text = ""
                    }
                }
                1 -> {
                    if (mPeiJianBean.size > 0) {
                        var peiText = " "
                        mPeiJianBean.forEach {
                            peiText += it.material_name
                        }
                        tv_peijian.text = peiText
                    } else {
                        tv_peijian.text = ""
                    }
                }
            }
        }
    }

    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        mJdkDPresenter.attachView(this)
        mProgressDialog = ProgressDialog(this)
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "接待开单"
        iv_chepai_scanning.setOnClickListener(this)
        iv_chejia_scanning.setOnClickListener(this)
        tv_chexing.setOnClickListener(this)
        iv_phone_scanning.setOnClickListener(this)
        tv_youbiao.setOnClickListener(this)
        tv_jiaoche_time.setOnClickListener(this)
        tv_setting.setOnClickListener(this)
        tv_fuwu.setOnClickListener(this)
        tv_peijian.setOnClickListener(this)
        rg_choose.setOnCheckedChangeListener(this)
        rg_songxiuren.setOnCheckedChangeListener(this)
        bt_jiedai.setOnClickListener(this)
        et_chepai_num.onFocusChangeListener = this
        try {
            val cls = EditText::class.java
            val setSoftInputShownOnFocus: Method
            setSoftInputShownOnFocus = cls.getMethod("setShowSoftInputOnFocus", Boolean::class.javaPrimitiveType)
            setSoftInputShownOnFocus.isAccessible = true
            setSoftInputShownOnFocus.invoke(et_chepai_num, false)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        et_chepai_num.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (TextUtils.isEmpty(et_chepai_num.text)) {
                    KeyboardUtil(this@JDKDActivity, this@JDKDActivity, et_chepai_num).showChinese()
                } else {
                    KeyboardUtil(this@JDKDActivity, this@JDKDActivity, et_chepai_num).showNumber()
                }
                return false
            }
        })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_chepai_scanning -> {
                //1.设置授权文件的名字
                PlateUserIdUtil.setUserId("1A055DE0BF6B06BEBCDC")
                val intent = Intent(this@JDKDActivity, EtScanPlateActivity::class.java)
                //2.配置保存图像信息
                val config = PlateInfoConfig()
                intent.putExtra(PlateUserIdUtil.INTENT_PLATE_CONFIG, config)
                startActivityForResult(intent, 400)
            }
            R.id.iv_chejia_scanning -> {
                //1.设置授权名称
                VinUserIdUtil.setUserId("1A055DE0BF6B06BEBCDC")
                try {
                    //写入授权文件
                    VinStreamEmpowerFileUtil.copyDataBase(this)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                val intent = Intent(this@JDKDActivity, EtVinScanActivity::class.java)
                val config = VinInfoConfig()
                //是否保存图像，默认不保存
                //config.setIsSaveImage(true);
                //设置图像保存路径(格式为"/alpha/VinCode/")默认路径
                //config.setStrSaveImagePath("/etop666/");
                intent.putExtra(VinUserIdUtil.INTENT_VIN_CONFIG, config)
                startActivityForResult(intent, 500)
            }
            R.id.tv_chexing -> {
                val intent = Intent(this, CarBrandActivity::class.java)
                startActivityForResult(intent, 200)
            }
            R.id.iv_phone_scanning -> {
                val intent = Intent(this, ChooseCustomerActivity::class.java)
                startActivityForResult(intent, 100)
            }
            R.id.tv_youbiao -> {
                ShowWheelView.show(this, youBiaoList) { tv_youbiao.text = it }
            }
            R.id.tv_jiaoche_time -> {
                ShowWheelViewTime.show(this, true, true, true, true, false, "yyyy-MM-dd HH:mm") { tv_jiaoche_time.text = it }
            }
            R.id.tv_setting -> {

            }
            R.id.tv_fuwu -> {
                val intent = Intent(this, AddFuwuActivity::class.java)
                intent.putExtra("page", 0)
                startActivityForResult(intent, 300)
            }
            R.id.tv_peijian -> {
                val intent = Intent(this, AddFuwuActivity::class.java)
                intent.putExtra("page", 1)
                startActivityForResult(intent, 301)
            }
            R.id.bt_jiedai -> {
                pamrms.clear()
                if (TextUtils.isEmpty(client_id)) {
                    if (TextUtils.isEmpty(tv_kehu_name.text)) {
                        showToast("请输入客户名称")
                        return
                    }
                    if (TextUtils.isEmpty(et_phone.text)) {
                        showToast("请输入客户电话号码")
                        return
                    }
                    pamrms["username"] = tv_kehu_name.text
                    pamrms["phone"] = et_phone.text
                    pamrms["sex"] = sex
                    pamrms["client_type"] = 10
                    pamrms["birthday"] = "2018-12-25"
                } else {
                    pamrms["client_id"] = client_id!!
                }
                if (car_id == "") {
                    if (TextUtils.isEmpty(et_chepai_num.text)) {
                        showToast("请输入车牌号码")
                        return
                    }
                    if (TextUtils.isEmpty(et_chepai_num.text)) {
                        showToast("请输入车架号码")
                        return
                    }
                    pamrms["car_number"] = et_chepai_num.text
                    pamrms["car_vin"] = et_chejia_num.text
                    if (mSortModel == null && mCarBeanResultBean == null) {
                        showToast("请选择车辆品牌")
                        return
                    }
                    pamrms["car_brand_id"] = mSortModel!!.brand_id
                    pamrms["car_type_id"] = mCarBeanResultBean!!.brand_id
                } else {
                    pamrms["car_id"] = car_id
                }
                pamrms["send_name"] = et_songxiuren_name.text
                pamrms["send_phone"] = et_songxiuren_num.text
                pamrms["send_sex"] = send_sex
                pamrms["fuel"] = tv_youbiao.text
                pamrms["miles"] = tv_licheng_num.text
                pamrms["expect_complete_time"] = tv_jiaoche_time.text
                pamrms["fault"] = et_fault.text
                pamrms["send_remark"] = et_beizhu.text
                if (mFuwuBean.size > 0) {
                    pamrms["project_list"] = Gson().toJson(mFuwuBean)
                }
                if (mPeiJianBean.size > 0) {
                    pamrms["material_list"] = Gson().toJson(mPeiJianBean)
                }
                RetrofitManager.service.appRepair(Tools.getRequestBody(pamrms)).enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
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
                })
            }
        }
    }

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        if (rb_nan1.id == checkedId) {
            sex = "男"
        }
        if (rb_nv1.id == checkedId) {
            sex = "女"
        }
        if (rb_nan2.id == checkedId) {
            send_sex = "男"
        }
        if (rb_nv2.id == checkedId) {
            send_sex = "女"
        }
    }

    /**
     * 查询这个车牌号码是否有
     */
    override fun onFocusChange(v: View, hasFocus: Boolean) {
        if (hasFocus) {//此处为得到焦点时的处理内容
            et_chejia_num.isFocusable = true
        } else {
            if (TextUtils.isEmpty(et_chepai_num.text.toString())) return
            pamrms.clear()
            pamrms["car_number"] = et_chepai_num.text.toString()
            mJdkDPresenter.requestCarData(Tools.getRequestBody(pamrms))
        }
    }

    override fun start() {
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun setCarDetail(carDetailBean: CarDetailBean, isFocusable: Boolean) {
        isExists = isFocusable
        if (!isExists) return
        car_id = "" + carDetailBean.result.car_id
        et_chejia_num.setText(carDetailBean.result.car_vin)
        et_chejia_num.isFocusable = isFocusable
        tv_chexing.text = carDetailBean.result.car_brand_name + "  " + carDetailBean.result.car_series_name
        tv_kehu_name.setText(carDetailBean.result.username)
        tv_kehu_name.isFocusable = isFocusable
        et_songxiuren_name.setText(carDetailBean.result.username)
        et_songxiuren_name.isFocusable = isFocusable
        et_phone.setText(carDetailBean.result.phone)
        et_phone.isFocusable = isFocusable
        et_songxiuren_num.setText(carDetailBean.result.phone)
        et_songxiuren_num.isFocusable = isFocusable
        if (carDetailBean.result.sex == "男") {
            rb_nan1.isChecked = true
            rb_nan2.isChecked = true
        } else {
            rb_nv1.isChecked = true
            rb_nv2.isChecked = true
        }
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mJdkDPresenter.detachView()
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) return
        when (resultCode) {
            100 -> {
                mChooseCustomeritem = data.getParcelableExtra("customer_phone")
                if (mChooseCustomeritem == null) return
                tv_kehu_name.setText(mChooseCustomeritem!!.username)
                et_phone.setText(mChooseCustomeritem!!.phone)
                et_songxiuren_name.setText(mChooseCustomeritem!!.username)
                et_songxiuren_num.setText(mChooseCustomeritem!!.phone)
                client_id = mChooseCustomeritem!!.client_id.toString()
            }
            200 -> {
                mCarBeanResultBean = data.getParcelableExtra("car_detail")
                mSortModel = data.getParcelableExtra("car")
                if (mCarBeanResultBean == null) return
                tv_chexing.text = mSortModel!!.name + "/" + mCarBeanResultBean!!.brand_name
            }
        }
        if (requestCode == 400) {
            var plateInfo = ""
            val listResult = data.getStringArrayListExtra("listResult")
            for (i in listResult.indices) {
                plateInfo += listResult[i] + "\r\n"
            }
            //显示识别结果
            showToast(plateInfo)
            et_chepai_num.setText(listResult[0])
        } else if (requestCode == 500) {
            val recogResult = data.getStringExtra("recogResult")
            et_chejia_num.setText(recogResult)
        } else if (requestCode == 300) {
            mFuwuBean.clear()
            val bundle = data.extras ?: return
            mFuwuBean = bundle.getParcelableArrayList("mFuwuBean")
            mHandler.sendEmptyMessage(0)
        } else if (requestCode == 301) {
            mPeiJianBean.clear()
            val bundle = data.extras ?: return
            mPeiJianBean = bundle.getParcelableArrayList("mPeiJianBean")
            mHandler.sendEmptyMessage(1)
        }
    }
}