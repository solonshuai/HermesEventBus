package com.zxy.mytsfqxproject.activity.fuwu

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.activity.jdkd.AddFuwuActivity
import com.zxy.mytsfqxproject.adapter.AddFuwuAdapter
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.*
import com.zxy.mytsfqxproject.recyclerView.OnClickLister
import kotlinx.android.synthetic.main.activity_add_fuwu.*
import kotlinx.android.synthetic.main.top_view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.set

class FwAddActivity : BaseActivity(), Callback<AddFuwuBean>, View.OnClickListener {
    override fun layoutId(): Int = R.layout.activity_add_fuwu
    private lateinit var mAddFuwuAdapter: AddFuwuAdapter
    var pamrms = HashMap<String, Any>()
    var repair_id = ""
    private var mAddFuwuBean = ArrayList<AddFuwuBean.ResultBean>()
    private var mPeiJianBean = ArrayList<PeiJianBean.ResultBean.DataBean>()
    private var mFuwuBean = ArrayList<FuwuBean.ResultBean.DataBean>()
    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> {
                    if (mPeiJianBean.size > 0) {
                        for (i in 0 until mPeiJianBean.size) {
                            for (j in 0 until mAddFuwuBean.size) {
                                if (mPeiJianBean[i].stock_id == mAddFuwuBean[j].goods_id && mPeiJianBean[i].material_name == mAddFuwuBean[j].goods_name && mPeiJianBean[i].material_code == mAddFuwuBean[j].goods_code) {
                                    mAddFuwuBean.removeAt(j)
                                }
                            }
                            val addFuwuBean = AddFuwuBean.ResultBean(0, mPeiJianBean[i].stock_id, 20, mPeiJianBean[i].material_name, mPeiJianBean[i].material_code, mPeiJianBean[i].material_num, mPeiJianBean[i].sale_price, Tools.intByStr(mPeiJianBean[i].material_amount), 0)
                            mAddFuwuBean.add(addFuwuBean)
                        }
                    }
                    if (mFuwuBean.size > 0) {
                        for (i in 0 until mFuwuBean.size) {
                            val addFuwuBean = AddFuwuBean.ResultBean(0, mFuwuBean[i].project_id, 10, mFuwuBean[i].project_title, mFuwuBean[i].project_code, 1, mFuwuBean[i].project_price, mFuwuBean[i].project_price, 0)
                            mAddFuwuBean.add(addFuwuBean)
                        }
                    }
                    mAddFuwuAdapter.setDataList(mAddFuwuBean)
                    mLayoutStatusView!!.showContent()
                }
            }
        }
    }

    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        mLayoutStatusView = multipleStatusView
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "添加服务"
        mAddFuwuAdapter = AddFuwuAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager
        recyclerView.adapter = mAddFuwuAdapter
        repair_id = intent.getStringExtra("repair_id")
        tv_add.setOnClickListener(this)
        tv_save.setOnClickListener(this)
        mAddFuwuAdapter.setOnClickLister(object : OnClickLister<AddFuwuBean.ResultBean> {
            override fun onItemAddClick(item: AddFuwuBean.ResultBean?, position: Int) {
                for (i in 0 until mAddFuwuBean.size) {
                    if (mAddFuwuBean[i].goods_id == item!!.goods_id && mAddFuwuBean[i].rg_id == item.rg_id) {
                        mAddFuwuBean[i].goods_amount = item.goods_amount
                        mAddFuwuBean[i].goods_num = item.goods_num
                        return
                    }
                }
            }

            override fun onItemRemoveClick(item: AddFuwuBean.ResultBean?, position: Int) {
                mAddFuwuBean.remove(item)
                mAddFuwuAdapter.setDataList(mAddFuwuBean)
            }
        })
    }

    override fun start() {
        pamrms["repair_id"] = repair_id
        RetrofitManager.service.getGoodsList(Tools.getRequestBody(pamrms)).enqueue(this)
    }

    override fun onFailure(call: Call<AddFuwuBean>, t: Throwable) {
    }

    override fun onResponse(call: Call<AddFuwuBean>, response: Response<AddFuwuBean>) {
        if (response.body()!!.code == 200) {
            if (response.body()!!.result.size > 0) {
                mAddFuwuBean = response.body()!!.result as ArrayList<AddFuwuBean.ResultBean>
                mAddFuwuAdapter.setDataList(response.body()!!.result)
            } else {
                mLayoutStatusView!!.showEmpty()
            }
        } else {
            mLayoutStatusView!!.showError()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_add -> {
                val intent = Intent(this, AddFuwuActivity::class.java)
                intent.putExtra("page", 0)
                startActivityForResult(intent, 300)
            }
            R.id.tv_save -> {
                val goodProjectBean = ArrayList<SaveGoodProjectBean>()
                val goodMaterialBean = ArrayList<SaveGoodMaterialBean>()
                for (i in 0 until mAddFuwuBean.size) {
                    if (mAddFuwuBean[i].goods_type == 10) {
                        val saveGoodBean = SaveGoodProjectBean(mAddFuwuBean[i].rg_id, mAddFuwuBean[i].goods_id, Tools.strByInt(mAddFuwuBean[i].goods_saleprice), mAddFuwuBean[i].goods_num, Tools.strByInt(mAddFuwuBean[i].goods_amount))
                        goodProjectBean.add(saveGoodBean)
                    }
                    if (mAddFuwuBean[i].goods_type == 20) {
                        val saveGoodMaterialBean = SaveGoodMaterialBean(mAddFuwuBean[i].rg_id, mAddFuwuBean[i].goods_id, Tools.strByInt(mAddFuwuBean[i].goods_saleprice), mAddFuwuBean[i].goods_num, Tools.strByInt(mAddFuwuBean[i].goods_amount))
                        goodMaterialBean.add(saveGoodMaterialBean)
                    }
                }
                pamrms["repair_id"] = repair_id
                pamrms["project_list"] = Gson().toJson(goodProjectBean)
                pamrms["material_list"] = Gson().toJson(goodMaterialBean)
                RetrofitManager.service.saveGoodsList(pamrms).enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        val sss = response.body()!!.toString()
                        val jobj = JSONObject(sss)
                        val errmsg = jobj.get("errmsg").toString()
                        val code = jobj.get("code")
                        if (code == 200) {
                            finish()
                        } else {
                            showToast(errmsg)
                        }
                    }
                })
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data == null) {
            return
        }
        when (resultCode) {
            300 -> {
                mPeiJianBean.clear()
                mFuwuBean.clear()
                val bundle = data.extras ?: return
                mPeiJianBean = bundle.getParcelableArrayList("mPeiJianBean")
                mFuwuBean = bundle.getParcelableArrayList("mFuwuBean")
                mHandler.sendEmptyMessage(0)
            }
        }
    }
}

