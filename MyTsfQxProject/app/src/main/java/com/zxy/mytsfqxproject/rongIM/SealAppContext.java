package com.zxy.mytsfqxproject.rongIM;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

public class SealAppContext implements RongIM.UserInfoProvider {
    private static SealAppContext mRongCloudInstance;
    private Handler mWorkHandler;

    private SealAppContext(Context Context) {
        initListener();
        HandlerThread mWorkThread = new HandlerThread("getUserInfoManager");
        mWorkThread.start();
        mWorkHandler = new Handler(mWorkThread.getLooper());
    }

    /**
     * 初始化 RongCloud.
     *
     * @param context 上下文。
     */
    public static void init(Context context) {
        if (mRongCloudInstance == null) {
            synchronized (SealAppContext.class) {
                if (mRongCloudInstance == null) {
                    mRongCloudInstance = new SealAppContext(context);
                }
            }
        }
    }

    /**
     * init 后就能设置的监听
     */
    private void initListener() {
        RongIM.setUserInfoProvider(this, true);
        RongIM.getInstance().setMessageAttachedUserInfo(true);
        RongIM.getInstance().enableNewComingMessageIcon(true);//显示新消息提醒
        RongIM.getInstance().enableUnreadMessageIcon(true);//显示未读消息数目
    }

    /**
     * 1 是取自己缓存
     * 2 是网络请求
     *
     * @param
     * @return
     */
    @Override
    public UserInfo getUserInfo(final String id) {
        mWorkHandler.post(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(id)) {
                    getUserInfoByHttp(id);
                }
            }
        });
        return null;
    }

    private void getUserInfoByHttp(String partyId) {
//        UserInfo userInfo = new UserInfo(partyId, name, Uri.parse(headImageUrl));
//        RongIM.getInstance().refreshUserInfoCache(userInfo);
    }


}
