package com.zxy.mytsfqxproject.activity

import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.RadioGroup
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import com.yanzhenjie.album.Album
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.View.GlideCircleTransform
import com.zxy.mytsfqxproject.View.PhotoPopupWindow
import com.zxy.mytsfqxproject.View.ShowWheelViewTime
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.inter.OnChooseCameraListener
import com.zxy.mytsfqxproject.mvp.entity.StoreInfoBean
import kotlinx.android.synthetic.main.activity_edit_info.*
import kotlinx.android.synthetic.main.top_view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

/**
 * 个人中心的编辑
 */
class EditInfoActivity : BaseActivity(), OnChooseCameraListener, RadioGroup.OnCheckedChangeListener {
    override fun layoutId(): Int = R.layout.activity_edit_info
    var imgUri = ""
    var pamrms = HashMap<String, RequestBody>()
    var storeInfoBean: StoreInfoBean.ResultBean? = null
    var sex: String = "男"
    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "编辑信息"
        layout_icon.setOnClickListener {
            PhotoPopupWindow(this@EditInfoActivity, iv_icon, this)
        }
        et_birthday.setOnClickListener { it ->
            ShowWheelViewTime.show(this, true, true, true, false, false, "yyyy-MM-dd") { et_birthday.text = it }
        }
        tv_commit.setOnClickListener {
            save()
        }
        rg_choose.setOnCheckedChangeListener(this)
    }

    override fun start() {
        RetrofitManager.service.findStaffInfo().enqueue(object : Callback<StoreInfoBean> {
            override fun onFailure(call: Call<StoreInfoBean>, t: Throwable) {
            }

            override fun onResponse(call: Call<StoreInfoBean>, response: Response<StoreInfoBean>) {
                if (response.body()!!.code == 200) {
                    storeInfoBean = response.body()!!.result
                    Glide.with(this@EditInfoActivity).load(storeInfoBean!!.photo).centerCrop()
                            .error(R.mipmap.img_yuan_bg).transform(GlideCircleTransform(this@EditInfoActivity)).into(iv_icon)
                    et_name.setText(storeInfoBean!!.username)
                    et_phone.setText(storeInfoBean!!.phone)
                    et_birthday.text = storeInfoBean!!.birth_day
                    et_weixin.setText(storeInfoBean!!.we_chat)
                    et_type.setText(storeInfoBean!!.type_work)
                    if (storeInfoBean!!.sex == "男") {
                        rb_nan.isChecked = true
                    } else {
                        rb_nv.isChecked = true
                    }
                }
            }
        })
    }

    override fun fromCamera() {
        Album.camera(this)
                .start(110)
    }

    override fun fromAlbum() {
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
        when (requestCode) {
            110 -> {
                if (resultCode == RESULT_OK) {
                    val imageList = Album.parseResult(data)
                    imgUri = imageList[0]
                    Tools.getSmallBitmap(imageList[0])
                    refreshImage(imgUri)
                }
            }
            111 -> {
                if (resultCode == RESULT_OK) {
                    val imageList = Album.parseResult(data)
                    imgUri = imageList[0]
                    refreshImage(imgUri)
                }
            }
        }
    }

    fun refreshImage(imgUri: String) {
        Glide.with(this@EditInfoActivity).load(File(imgUri)).centerCrop()
                .error(R.mipmap.img_yuan_bg).transform(GlideCircleTransform(this@EditInfoActivity)).into(iv_icon)

    }

    fun save() {
        pamrms["staff_id"] = RequestBody.create(null, ("" + storeInfoBean!!.staff_id))
        pamrms["department_id"] = RequestBody.create(null, storeInfoBean!!.department_id)
        pamrms["phone"] = RequestBody.create(null, et_phone.text.toString())
        pamrms["username"] = RequestBody.create(null, et_name.text.toString())
        pamrms["sex"] = RequestBody.create(null, sex)
        pamrms["birth_day"] = RequestBody.create(null, et_birthday.text.toString())
        pamrms["we_chat"] = RequestBody.create(null, et_weixin.text.toString())
        pamrms["type_work"] = RequestBody.create(null, et_type.text.toString())
        if (imgUri == "") {
            showToast("请选择头像图片")
            return
        }
        val file = File(imgUri)
        val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val part = MultipartBody.Part.createFormData("photo", file.name, requestBody)
        RetrofitManager.service.appSaveStaf(pamrms, part).enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                showToast(t.toString())
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

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        if (rb_nan.id == checkedId) {
            sex = "男"
        }
        if (rb_nv.id == checkedId) {
            sex = "女"
        }
    }

}