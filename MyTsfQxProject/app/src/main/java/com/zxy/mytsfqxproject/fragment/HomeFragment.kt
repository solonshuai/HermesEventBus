package com.zxy.mytsfqxproject.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.ActivityManager
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.View.GridDividerItemDecoration
import com.zxy.mytsfqxproject.activity.LoginActivity
import com.zxy.mytsfqxproject.activity.ddjs.DDJSActivity
import com.zxy.mytsfqxproject.activity.fuwu.FWDDActivity
import com.zxy.mytsfqxproject.activity.gcgl.GCGLActivity
import com.zxy.mytsfqxproject.activity.jdkd.JDKDActivity
import com.zxy.mytsfqxproject.adapter.MenuAdapter
import com.zxy.mytsfqxproject.base.BaseFragment
import com.zxy.mytsfqxproject.db.SPUtil
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.MenuBean
import com.zxy.mytsfqxproject.recyclerView.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class HomeFragment : BaseFragment(), OnRecyclerItemClickListener<MenuBean.ResultBean>, Callback<MenuBean> {
    private var mTitle: String? = null
    //    private val imgs = ArrayList<String>().apply {
//        add("http://ww3.sinaimg.cn/large/610dc034jw1fasakfvqe1j20u00mhgn2.jpg")
//        add("http://h.hiphotos.baidu.com/image/pic/item/09fa513d269759eea254ddbabefb43166d22dfbf.jpg")
//        add("http://f.hiphotos.baidu.com/image/pic/item/8694a4c27d1ed21bfb91c832a66eddc450da3f89.jpg")
//    }
    private lateinit var menuAdapter: MenuAdapter
    private var pamrms = HashMap<String, Any>()

    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    @SuppressLint("ResourceAsColor")
    override fun initView(view: View) {
//        mBanner.setImages(imgs)//设置图片源
//        mBanner.setImageLoader(object : ImageLoader() {
//            override fun displayImage(context: Context, path: Any, imageView: ImageView) {
//                Glide.with(activity).load(path as String)
//                        .into(imageView)
//            }
//        })
//        mBanner.start()
        menuAdapter = MenuAdapter((this.activity as Context?)!!)
        menuAdapter.onRecyclerItemClickListener = this
        mRecyclerView.layoutManager = GridLayoutManager(this.activity!!, 3) as RecyclerView.LayoutManager?
        mRecyclerView.addItemDecoration(GridDividerItemDecoration(context))
        mRecyclerView.adapter = menuAdapter
        mSmartRefreshLayout.refreshHeader = ClassicsHeader(this.activity).setSpinnerStyle(SpinnerStyle.Scale)
        mSmartRefreshLayout.refreshFooter = ClassicsFooter(this.activity).setSpinnerStyle(SpinnerStyle.Scale)
        mSmartRefreshLayout.isEnableLoadmore = false
        lazyLoad()
        mSmartRefreshLayout.setOnRefreshListener { refreshlayout ->
            lazyLoad()
            refreshlayout.finishRefresh(1500)
        }
    }

    override fun lazyLoad() {
        pamrms["class"] = "home"
        RetrofitManager.service.getMenu(Tools.getRequestBody(pamrms)).enqueue(this)
    }

    override fun onFailure(call: Call<MenuBean>, t: Throwable) {
        showToast("请重新登录")
        SPUtil.clearData()
        ActivityManager.finishAll()
        startActivity(Intent(activity, LoginActivity::class.java))
    }

    override fun onResponse(call: Call<MenuBean>, response: Response<MenuBean>) {
        if (response.body()!!.code == 200) {
            menuAdapter.setDataList(response.body()!!.result)
        } else if (response.body()!!.code == 402) {
            ActivityManager.finishAll()
            startActivity(Intent(activity, LoginActivity::class.java))
        } else {
            showToast(response.body()!!.errmsg)
        }
    }

    override fun onItemClick(v: View, item: MenuBean.ResultBean, position: Int) {
        when (item.alias) {
            "JDKD" -> {
                startActivity(Intent(activity, JDKDActivity::class.java))
            }
            "FWDD" -> {
                startActivity(Intent(activity, FWDDActivity::class.java))
            }
            "GCGL" -> {
                startActivity(Intent(activity, GCGLActivity::class.java))
            }
            "DDJS" -> {
                startActivity(Intent(activity, DDJSActivity::class.java))
            }
        }
    }

}