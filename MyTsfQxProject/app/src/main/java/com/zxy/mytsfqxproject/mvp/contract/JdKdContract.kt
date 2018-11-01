package com.zxy.mytsfqxproject.mvp.contract

import com.hazz.kotlinmvp.base.IBaseView
import com.hazz.kotlinmvp.base.IPresenter
import com.zxy.mytsfqxproject.mvp.entity.CarDetailBean
import okhttp3.RequestBody

interface JdKdContract {
    interface View : IBaseView {
        fun setCarDetail(carDetailBean: CarDetailBean,isFocusable:Boolean)
    }

    interface Presenter : IPresenter<View> {
        /**
         * 获取车牌的详情信息
         */
        fun requestCarData(route: RequestBody)
    }
}