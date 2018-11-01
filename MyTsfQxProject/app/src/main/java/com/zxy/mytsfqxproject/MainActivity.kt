package com.zxy.mytsfqxproject

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import android.view.View
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.View.TabEntity
import com.zxy.mytsfqxproject.activity.CustomerManager
import com.zxy.mytsfqxproject.activity.OrderListActivity
import com.zxy.mytsfqxproject.activity.SetActivity
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.fragment.HomeFragment
import com.zxy.mytsfqxproject.fragment.MyFragment
import com.zxy.mytsfqxproject.mvp.entity.MessageEvent
import com.zxy.mytsfqxproject.rongIM.ConversationListAdapterEx
import io.rong.imkit.RongContext
import io.rong.imkit.fragment.ConversationListFragment
import io.rong.imlib.model.Conversation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.top_view.*
import org.greenrobot.eventbus.EventBus
import java.util.*

class MainActivity : BaseActivity() {
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


    override fun layoutId(): Int {
        return R.layout.activity_main
    }

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

}
