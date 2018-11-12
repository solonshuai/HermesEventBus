package com.zxy.mytsfqxproject

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.View.CustomPopDialog2
import com.zxy.mytsfqxproject.View.TabEntity
import com.zxy.mytsfqxproject.activity.CustomerManager
import com.zxy.mytsfqxproject.activity.OrderListActivity
import com.zxy.mytsfqxproject.activity.SetActivity
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.fragment.HomeFragment
import com.zxy.mytsfqxproject.fragment.MyFragment
import com.zxy.mytsfqxproject.http.DownLoadService
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.inter.OnChooseCameraListener
import com.zxy.mytsfqxproject.mvp.entity.AppSetBean
import com.zxy.mytsfqxproject.mvp.entity.MessageEvent
import com.zxy.mytsfqxproject.rongIM.ConversationListAdapterEx
import io.rong.imkit.RongContext
import io.rong.imkit.fragment.ConversationListFragment
import io.rong.imlib.model.Conversation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.top_view.*
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : BaseActivity(), OnChooseCameraListener, Callback<AppSetBean> {
    private val mTitles = arrayOf("首页", "消息", "我的")
    private var mExitTime: Long = 0
    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_news_normal, R.mipmap.ic_my_normal)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_news_select, R.mipmap.ic_my_selecte)

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mHomeFragment: HomeFragment? = null
    private var mMyFragment: MyFragment? = null
    var fragment: Fragment? = null
    //默认为0
    private var mIndex = 0
    private var downloadUrl: String? = null

    override fun layoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        tab_layout.currentTab = mIndex
        switchFragment(mIndex)
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
    }

    //初始化底部菜单
    private fun initTab() {

        (0 until mTitles.size)
                .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        //为Tab赋值
        tab_layout.setTabData(mTabEntities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                //切换Fragment
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        showTitle(position)
        when (position) {
            0 // 首页
            -> mHomeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment.getInstance(mTitles[position]).let {
                mHomeFragment = it
                transaction.add(R.id.fl_container, it, "钛师傅的新门店")
            }
            1 -> if (fragment == null) {
                fragment = initConversationList()
                transaction.add(R.id.fl_container, fragment, "消息")
            } else {
                transaction.show(fragment)
            }
            2  //热门
            -> mMyFragment?.let {
                transaction.show(it)
            } ?: MyFragment.getInstance(mTitles[position]).let {
                mMyFragment = it
                transaction.add(R.id.fl_container, it, "我的")
            }
        }

        mIndex = position
        tab_layout.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }

    private fun showTitle(position: Int) {
        when (position) {
            0 -> {
                iv_left.visibility = View.VISIBLE
                iv_right.visibility = View.VISIBLE
                iv_left.setImageResource(R.mipmap.img_customer)
                iv_right.setImageResource(R.mipmap.img_order)
                tv_header_title.text = "钛师傅的新门店"
                iv_right.setOnClickListener {
                    startActivity(Intent(this@MainActivity, OrderListActivity::class.java))
                }
                iv_left.setOnClickListener {
                    startActivity(Intent(this@MainActivity, CustomerManager::class.java))
                }
            }
            1 -> {
                iv_left.visibility = View.INVISIBLE
                iv_right.visibility = View.INVISIBLE
//                iv_right.setImageResource(R.mipmap.img_add)
                tv_header_title.text = "消息"
            }
            2 -> {
                iv_left.visibility = View.INVISIBLE
                iv_right.setImageResource(R.mipmap.img_set)
                iv_right.visibility = View.VISIBLE
                tv_header_title.text = "我的"
                iv_right.setOnClickListener {
                    startActivity(Intent(this@MainActivity, SetActivity::class.java))
                }
            }
        }
    }

    private fun initConversationList(): Fragment {
        val listFragment = ConversationListFragment()
        listFragment.setAdapter(ConversationListAdapterEx(RongContext.getInstance()))
        val uri: Uri = Uri.parse("rong://" + this.applicationInfo.packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "true")//公共服务号
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "true")//订阅号
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                .build()
        listFragment.uri = uri
        return listFragment
    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        if (null != fragment) {
            transaction.hide(fragment)
        }
        mMyFragment?.let { transaction.hide(it) }
    }


    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tab_layout != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }

    override fun initView() {
        RetrofitManager.service.appset().enqueue(this)
    }

    //暂时用这个借口对升级掉用
    override fun fromCamera() {
        //稍后更新
    }

    override fun fromAlbum() {
        check8_0Permis()
    }

    fun showDialog(ismodify: Boolean) {
        val dialog = CustomPopDialog2(this).Builder(this)
        dialog.imags = null
        dialog.listener = this
        dialog.isCanceled = !ismodify
        dialog.create().show()
    }

    override fun onFailure(call: Call<AppSetBean>, t: Throwable) {
    }

    override fun onResponse(call: Call<AppSetBean>, response: Response<AppSetBean>) {
        if (response.body()!!.code == 200) {
            downloadUrl = response.body()!!.result.android.versionInfo!!.getUrl
            val versionNum = response.body()!!.result.android.versionInfo!!.lastVersion.replace(".", "")
            val currentNum = Tools.getVersionName(this@MainActivity).replace(".", "")
            if (versionNum > currentNum) {
                showDialog(response.body()!!.result.android.versionInfo!!.isIsForce)
            }
        }
    }

    fun check8_0Permis() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val haveInstallPermission = this.packageManager.canRequestPackageInstalls()
            if (!haveInstallPermission) {
                alert.setTitle("安装权限")
                alert.setMessage("需要打开允许来自此来源，请去设置中开启此权限")
                alert.setDialogOut(false)
                alert.setCancelable(false)
                alert.setPositiveButton("确定") { _, _ -> startInstallPermissionSettingActivity() }
                alert.setNegativeButton(null, null)
                alert.show()
                return
            }
        } else {
            if (TextUtils.isEmpty(downloadUrl)) {
                return
            }
            download(this.downloadUrl!!)
        }
    }

    private fun download(downloadUrl: String) {
        mProgressDialog!!.setMessage("正在下载中.....")
        mProgressDialog!!.setCanceledOnTouchOutside(false)
        mProgressDialog!!.setCancelable(false)
        mProgressDialog!!.show()
        val updataService = Intent(this@MainActivity, DownLoadService::class.java)
        updataService.putExtra("downloadurl", downloadUrl)
        startService(updataService)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun startInstallPermissionSettingActivity() {
        val packageURI = Uri.parse("package:$packageName")
        //注意这个是8.0新API
        val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI)
        startActivityForResult(intent, 10086)
    }

    override fun start() {
        EventBus.getDefault().post(MessageEvent("RongImConnect"))
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 10086) {
            download(this.downloadUrl!!)
        }
    }
}
