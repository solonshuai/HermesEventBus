package com.zxy.mytsfqxproject.activity

import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import com.bumptech.glide.Glide
import com.etop.PLDemo.EtScanPlateActivity
import com.etop.utils.*
import com.etop.vincode.EtVinScanActivity
import com.google.gson.JsonObject
import com.yanzhenjie.album.Album
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.KeyboardUtil
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.View.GlideCircleTransform
import com.zxy.mytsfqxproject.View.PhotoPopupWindow
import com.zxy.mytsfqxproject.View.ShowWheelView
import com.zxy.mytsfqxproject.View.ShowWheelViewTime
import com.zxy.mytsfqxproject.activity.jdkd.CarBrandActivity
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.inter.OnChooseCameraListener
import com.zxy.mytsfqxproject.mvp.entity.CarBean
import com.zxy.mytsfqxproject.mvp.entity.InsuranceBean
import com.zxy.mytsfqxproject.mvp.entity.SortModel
import kotlinx.android.synthetic.main.activity_add_car.*
import kotlinx.android.synthetic.main.top_view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.lang.reflect.Method

/**
 * 添加车辆
 */
class AddCarActivity : BaseActivity(), View.OnClickListener, OnChooseCameraListener {
    override fun layoutId(): Int = R.layout.activity_add_car
    var mCarBeanResultBean: CarBean.ResultBean? = null
    var mSortModel: SortModel? = null //品牌
    private val mInsuranceList = ArrayList<String>()
    var mInsuranceBean = ArrayList<InsuranceBean.ResultBean>()
    var pamrms = HashMap<String, RequestBody>()
    var imgUri: String? = null
    var client_id: Int = 0
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "添加车辆"
        iv_car_scan.setOnClickListener(this)
        iv_carjia_scan.setOnClickListener(this)
        bt_baocun.setOnClickListener(this)
        tv_pinpai_num.setOnClickListener(this)
        tv_baoxian_num.setOnClickListener(this)
        tv_daoqi_num.setOnClickListener(this)
        et_nianshen_num.setOnClickListener(this)
        iv_photo.setOnClickListener(this)
        client_id = intent.getIntExtra("client_id", 0)
        try {
            val cls = EditText::class.java
            val setSoftInputShownOnFocus: Method
            setSoftInputShownOnFocus = cls.getMethod("setShowSoftInputOnFocus", Boolean::class.javaPrimitiveType)
            setSoftInputShownOnFocus.isAccessible = true
            setSoftInputShownOnFocus.invoke(et_car_num, false)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        et_car_num.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(TextUtils.isEmpty(et_car_num.text)){
                    KeyboardUtil(this@AddCarActivity, this@AddCarActivity, et_car_num).showChinese()
                }else{
                    KeyboardUtil(this@AddCarActivity, this@AddCarActivity, et_car_num).showNumber()
                }
                return false
            }
        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.iv_car_scan -> {
                //1.设置授权文件的名字
                PlateUserIdUtil.setUserId("1A055DE0BF6B06BEBCDC")
                val intent = Intent(this@AddCarActivity, EtScanPlateActivity::class.java)
                //2.配置保存图像信息
                val config = PlateInfoConfig()
                intent.putExtra(PlateUserIdUtil.INTENT_PLATE_CONFIG, config)
                startActivityForResult(intent, 400)
            }
            R.id.iv_carjia_scan -> {
                //1.设置授权名称
                VinUserIdUtil.setUserId("1A055DE0BF6B06BEBCDC")
                try {
                    //写入授权文件
                    VinStreamEmpowerFileUtil.copyDataBase(this)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                val intent = Intent(this@AddCarActivity, EtVinScanActivity::class.java)
                val config = VinInfoConfig()
                //是否保存图像，默认不保存
                //config.setIsSaveImage(true);
                //设置图像保存路径(格式为"/alpha/VinCode/")默认路径
                //config.setStrSaveImagePath("/etop666/");
                intent.putExtra(VinUserIdUtil.INTENT_VIN_CONFIG, config)
                startActivityForResult(intent, 500)
            }
            R.id.bt_baocun -> {
                if (TextUtils.isEmpty(et_car_num.text)) {
                    showToast("请填写车牌号码")
                    return
                }
                pamrms["car_number"] = RequestBody.create(null, et_car_num.text.toString())
                if (TextUtils.isEmpty(et_carjia_num.text)) {
                    showToast("请填写车架号码")
                    return
                }
                pamrms["car_vin"] = RequestBody.create(null, et_carjia_num.text.toString())
                if (TextUtils.isEmpty(tv_pinpai_num.text)) {
                    showToast("请选车车辆品牌")
                    return
                }
                pamrms["car_series_id"] = RequestBody.create(null, "" + mCarBeanResultBean!!.brand_id)
                if (TextUtils.isEmpty(et_engine_num.text)) {
                    showToast("请填写发动号")
                    return
                }
                pamrms["car_eno"] = RequestBody.create(null, et_engine_num.text.toString())
                if (TextUtils.isEmpty(tv_baoxian_num.text)) {
                    showToast("请选择保险公司")
                    return
                }
                for (i in 0 until mInsuranceList.size) {
                    if (tv_baoxian_num.text == mInsuranceBean[i].name) {
                        pamrms["insurance_id"] = RequestBody.create(null, "" + mInsuranceBean[i].id)
                    }
                }
                if (TextUtils.isEmpty(tv_daoqi_num.text)) {
                    showToast("请选择保险到期日期")
                    return
                }
                pamrms["insurance_over_time"] = RequestBody.create(null, tv_daoqi_num.text.toString())
                if (TextUtils.isEmpty(et_nianshen_num.text)) {
                    showToast("请选择年审日期")
                    return
                }
                pamrms["next_annual_time"] = RequestBody.create(null, et_nianshen_num.text.toString())
                pamrms["car_miles"] = RequestBody.create(null, et_gongli_num.text.toString())
                pamrms["client_id"] = RequestBody.create(null, "" + client_id)
                if (imgUri == null && imgUri == "") {
                    showToast("请添加图片")
                    return
                }
                val file = File(imgUri)
                val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                val part = MultipartBody.Part.createFormData("car_photo", file.name, requestBody)
                RetrofitManager.service.appSaveCar(pamrms, part).enqueue(object : Callback<JsonObject> {
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
                            showToast(getString(R.string.http_error))
                        }
                    }
                })
            }
            R.id.tv_pinpai_num -> {
                val intent = Intent(this, CarBrandActivity::class.java)
                startActivityForResult(intent, 200)
            }
            R.id.tv_baoxian_num -> {
                RetrofitManager.service.insuranceList().enqueue(object : Callback<InsuranceBean> {
                    override fun onFailure(call: Call<InsuranceBean>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<InsuranceBean>, response: Response<InsuranceBean>) {
                        for (i in 0 until response.body()!!.result.size) {
                            mInsuranceList.add(response.body()!!.result[i].name)
                        }
                        mInsuranceBean = response.body()!!.result as ArrayList<InsuranceBean.ResultBean>
                        ShowWheelView.show(this@AddCarActivity, mInsuranceList) { str ->
                            tv_baoxian_num.text = str
                        }
                    }
                })
            }
            R.id.tv_daoqi_num -> {
                ShowWheelViewTime.show(this, true, true, true, false, false, "yyyy-MM-dd") { tv_daoqi_num.text = it }
            }
            R.id.et_nianshen_num -> {
                ShowWheelViewTime.show(this, true, true, true, false, false, "yyyy-MM-dd") { et_nianshen_num.text = it }

            }
            R.id.iv_photo -> {
                PhotoPopupWindow(this@AddCarActivity, iv_photo, this)
            }
        }
    }

    override fun start() {
    }

    override fun fromCamera() {
        Album.camera(this)
                .start(110)
    }

    override fun fromAlbum() {
//        Album.album(this)
//                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary)) // Toolbar color.
//                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)) // StatusBar color.
//                .navigationBarColor(ActivityCompat.getColor(this, R.color.colorAccent)) // NavigationBar color.
//                .selectCount(100) // select count.
//                .columnCount(2) // span count.
//                .camera(true) // has fromCamera function.
//                .checkedList(mImageList) // The picture has been selected for anti-election.
//                .start(102)
        Album.albumRadio(this)
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(this, R.color.colorAccent)) // NavigationBar color.
                .columnCount(2) // span count.
                .camera(true) // has fromCamera function.
                .start(111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data == null) return
        when (resultCode) {
            200 -> {
                mCarBeanResultBean = data.getParcelableExtra("car_detail")
                mSortModel = data.getParcelableExtra("car")
                if (mCarBeanResultBean == null) return
                tv_pinpai_num.text = mSortModel!!.name + "/" + mCarBeanResultBean!!.brand_name
            }
            110 -> {
                if (resultCode == RESULT_OK) {
                    val imageList = Album.parseResult(data)
                    imgUri = imageList[0]
                    Tools.getSmallBitmap(imageList[0])
                    refreshImage(imgUri!!)
                }
            }
            111 -> {
                if (resultCode == RESULT_OK) {
                    val imageList = Album.parseResult(data)
                    imgUri = imageList[0]
                    refreshImage(imgUri!!)
                }
            }
        }
        if (requestCode == 400) {
            var plateInfo = ""
            if (data != null) {
                val listResult = data.getStringArrayListExtra("listResult")

                for (i in listResult.indices) {
                    plateInfo += listResult[i] + "\r\n"
                }
                //显示识别结果
                showToast(plateInfo)
                et_car_num.setText(listResult[0])
            } else {
                showToast("识别失败")
            }
        } else {
            val recogResult = data.getStringExtra("recogResult")
            et_carjia_num.setText(recogResult)
        }
    }

    fun refreshImage(imgUri: String) {
        // 将路径转成图片对象
        Glide.with(this@AddCarActivity).load(File(imgUri)).centerCrop()
                .error(R.mipmap.img_yuan_bg).transform(GlideCircleTransform(this@AddCarActivity)).into(iv_photo)
    }
}