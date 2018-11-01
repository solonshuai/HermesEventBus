package com.zxy.mytsfqxproject.mvp.Model

import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.CarDetailBean
import io.reactivex.Observable
import okhttp3.RequestBody

class JdKdModel {

    fun getCarDetail(route: RequestBody): Observable<CarDetailBean> {
        return RetrofitManager.service.getClientDetail(route)
    }
}