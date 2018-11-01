package com.zxy.mytsfqxproject

import android.app.ActivityManager
import android.content.Context
import android.net.Uri
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import android.text.TextUtils
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.View.DisplayManager
import com.zxy.mytsfqxproject.db.SPUtil
import com.zxy.mytsfqxproject.http.UrlConstant
import com.zxy.mytsfqxproject.mvp.entity.MessageEvent
import com.zxy.mytsfqxproject.rongIM.SealAppContext
import io.rong.imkit.RongIM
import io.rong.imlib.RongIMClient
import io.rong.imlib.model.UserInfo
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlin.properties.Delegates


/**
 * 单列的Application
 */
class MyApplication : MultiDexApplication() {

    companion object {
        var context: Context by Delegates.notNull()
            private set
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
        DisplayManager.init(this)
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        EventBus.getDefault().register(this)
        SPUtil.getInstance(this)
        if (applicationInfo.processName == getCurProcessName(applicationContext)) {
            RongIM.init(this)
        }
        SealAppContext.init(this)
        RongIM.setConnectionStatusListener(MyConnectionStatusListener())
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun Event(messageEvent: MessageEvent) {
        when (messageEvent.message) {
            "RongImConnect" -> RongImConnect(SPUtil.getData(UrlConstant.rongCloud, null).toString())
        }

    }

    private fun RongImConnect(token: String) {
        if (TextUtils.isEmpty(token)) {
            return
        }
        RongIM.connect(token, object : RongIMClient.ConnectCallback() {
            override fun onTokenIncorrect() {
                Tools.logger("过期")
                SPUtil.putData(UrlConstant.rongCloud, null)
            }

            override fun onSuccess(s: String) {
                Tools.logger("onSuccess")
                val userId = SPUtil.getData(UrlConstant.userId, 0)
                val userName = SPUtil.getData(UrlConstant.userName, "")
                val headImageUrl = SPUtil.getData(UrlConstant.userImg, "")
                if (TextUtils.isEmpty(headImageUrl)) {
                    return
                }
                RongIM.getInstance().setCurrentUserInfo(UserInfo("" + userId, userName, Uri.parse(headImageUrl)))
            }

            override fun onError(errorCode: RongIMClient.ErrorCode) {
                SPUtil.putData(UrlConstant.rongCloud, null)
                Tools.logger("onError===$errorCode")
            }
        })
    }

    private inner class MyConnectionStatusListener : RongIMClient.ConnectionStatusListener {

        override fun onChanged(connectionStatus: RongIMClient.ConnectionStatusListener.ConnectionStatus) {
            when (connectionStatus) {
                RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED//连接成功。
                -> {
                }
                RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED//断开连接。
                -> {
                }
                RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTING//连接中。
                -> {
                }
                RongIMClient.ConnectionStatusListener.ConnectionStatus.NETWORK_UNAVAILABLE//网络不可用。
                -> {
                }
                RongIMClient.ConnectionStatusListener.ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT//用户账户在其他设备登录，本机会被踢掉线
                -> {
//                    SharePrefenceUtils.putData(Constant.Im_token, null)
//                    SharePrefenceUtils.putData(Constant.token, null)
//                    EventBus.getDefault().post(MessageEvent("OffLine"))
                }
            }
        }
    }


    fun getCurProcessName(context: Context): String? {
        val pid = android.os.Process.myPid()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (appProcess in activityManager.runningAppProcesses) {
            if (appProcess.pid == pid) {
                return appProcess.processName
            }
        }
        return null
    }
}