package com.zxy.mytsfqxproject.mvp.Presenter

import com.hazz.kotlinmvp.base.BasePresenter
import com.zxy.mytsfqxproject.mvp.Model.JdKdModel
import com.zxy.mytsfqxproject.mvp.contract.JdKdContract
import com.zxy.mytsfqxproject.mvp.entity.CarDetailBean
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody

class JdkDPresenter : BasePresenter<JdKdContract.View>(), JdKdContract.Presenter {
    private var mCarDetailBean: CarDetailBean? = null
    private val mJdKdModel: JdKdModel by lazy {
        JdKdModel()
    }

    override fun requestCarData(route: RequestBody) {
        if (isViewAttached) {
            val disposable = mJdKdModel.getCarDetail(route)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<CarDetailBean> {
                        override fun onComplete() {
                            if (mCarDetailBean!!.result.car_number != null && mCarDetailBean!!.result.car_number != "") {
                                mRootView!!.setCarDetail(mCarDetailBean!!, true)
                            } else {
                                mRootView!!.setCarDetail(mCarDetailBean!!, false)
                            }
                        }

                        override fun onSubscribe(d: Disposable) {
                            addSubscription(d)
                        }

                        override fun onNext(t: CarDetailBean) {
                            mCarDetailBean = t
                        }

                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                        }
                    })
        }
    }
}