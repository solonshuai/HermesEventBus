package com.zxy.mytsfqxproject.activity

import android.Manifest
import android.content.Intent
import android.support.v4.content.res.ResourcesCompat
import android.text.TextUtils
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.zxy.mytsfqxproject.MainActivity
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.db.SPUtil
import com.zxy.mytsfqxproject.http.UrlConstant
import kotlinx.android.synthetic.main.activity_splash.*
import me.weyye.hipermission.HiPermission
import me.weyye.hipermission.PermissionCallback
import me.weyye.hipermission.PermissionItem


/**
 * Created by xuhao on 2017/12/5.
 * desc: 启动页
 */

class SplashActivity : BaseActivity() {

    private var alphaAnimation: AlphaAnimation? = null
    override fun layoutId(): Int = R.layout.activity_splash
    override fun initView() {
        //渐变展示启动屏
        alphaAnimation = AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 2000
        alphaAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                redirectTo()
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationStart(animation: Animation) {}

        })
        checkPermission()
    }

    fun redirectTo() {
        if (TextUtils.isEmpty(SPUtil.getData(UrlConstant.token, "").toString())) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        finish()
    }

    override fun start() {
    }

    private fun checkPermission() {
        val permissonItems = ArrayList<PermissionItem>()
        permissonItems.add(PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储", R.drawable.permission_ic_storage))
        permissonItems.add(PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.drawable.permission_ic_location))
        permissonItems.add(PermissionItem(Manifest.permission.READ_CONTACTS, "通讯录", R.drawable.permission_ic_contacts))
        HiPermission.create(this)
                .title("亲爱的上帝")
                .permissions(permissonItems)
                .filterColor(ResourcesCompat.getColor(resources, R.color.colorPrimary, theme))
                .msg("为了使用更流畅，请开启这些权限吧！")
                .style(R.style.PermissionBlueStyle)
                .checkMutiPermission(object : PermissionCallback {
                    override fun onFinish() {
                        if (alphaAnimation != null) {
                            iv_web_icon.startAnimation(alphaAnimation)
                        }
                    }

                    override fun onDeny(permission: String?, position: Int) {
                    }

                    override fun onGuarantee(permission: String?, position: Int) {
                    }

                    override fun onClose() {
                        showToast("用户关闭权限申请")
                    }
                })
    }
}