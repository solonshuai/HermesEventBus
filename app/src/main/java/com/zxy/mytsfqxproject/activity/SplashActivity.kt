package com.zxy.mytsfqxproject.activity

import android.Manifest
import android.content.Intent
import android.text.TextUtils
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.zxy.mytsfqxproject.MainActivity
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.db.SPUtil
import com.zxy.mytsfqxproject.http.UrlConstant
import kotlinx.android.synthetic.main.activity_splash.*
import pub.devrel.easypermissions.EasyPermissions


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
        val perms = arrayOf(Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CONTACTS)
        EasyPermissions.requestPermissions(this, "钛师傅需要以下权限，请允许", 0, *perms)

    }
    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (requestCode == 0) {
            if (perms.isNotEmpty()) {
                if (perms.contains(Manifest.permission.READ_PHONE_STATE)
                        && perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                &&perms.contains(Manifest.permission.READ_CONTACTS)) {
                    if (alphaAnimation != null) {
                        iv_web_icon.startAnimation(alphaAnimation)
                    }
                }
            }
        }
    }
}