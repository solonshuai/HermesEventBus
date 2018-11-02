package com.zxy.mytsfqxproject.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.LocationSource
import com.amap.api.maps.UiSettings
import com.amap.api.maps.model.CameraPosition
import com.amap.api.maps.model.LatLng
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.core.PoiItem
import com.amap.api.services.geocoder.GeocodeResult
import com.amap.api.services.geocoder.GeocodeSearch
import com.amap.api.services.geocoder.RegeocodeQuery
import com.amap.api.services.geocoder.RegeocodeResult
import com.amap.api.services.poisearch.PoiResult
import com.amap.api.services.poisearch.PoiSearch
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.adapter.MapSearchAdapter
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.mvp.entity.LocationBean
import com.zxy.mytsfqxproject.recyclerView.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.top_view.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * 地址选择
 */
class MapActivity : BaseActivity(), LocationSource, AMapLocationListener, PoiSearch.OnPoiSearchListener, OnRecyclerItemClickListener<LocationBean>, GeocodeSearch.OnGeocodeSearchListener {
    override fun layoutId(): Int = R.layout.activity_map
    var aMap: AMap? = null
    //声明AMapLocationClient类对象，定位发起端
    private var mLocationClient: AMapLocationClient? = null
    //声明mLocationOption对象，定位参数
    var mLocationOption: AMapLocationClientOption? = null
    //声明mListener对象，定位监听器
    private var mListener: LocationSource.OnLocationChangedListener? = null
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private var isFirstLoc = true
    private var city = "成都市"
    //当前地址
    private var currentAddress = ""
    private lateinit var mMapSearchAdapter: MapSearchAdapter
    val datas = ArrayList<LocationBean>()
    //UiSettings 类用于操控这些控件
    private var mUiSettings: UiSettings? = null
    //根据经纬度获取地址
    private var geocoderSearch: GeocodeSearch? = null
    private var latitude = 0.0
    private var longitude = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        map.onCreate(savedInstanceState)
        if (aMap == null) {
            aMap = map.map
            //设置显示定位按钮 并且可以点击
            mUiSettings = aMap!!.uiSettings
            aMap!!.setLocationSource(this)//设置了定位的监听
            // 是否显示定位按钮
            mUiSettings!!.isMyLocationButtonEnabled = true
            //是否显示 + — 按钮
            mUiSettings!!.isZoomControlsEnabled = true
            //显示定位层并且可以触发定位,默认是flase
            aMap!!.isMyLocationEnabled = false
            //地图状态改变的方法
            aMap!!.setOnCameraChangeListener(object : AMap.OnCameraChangeListener {
                override fun onCameraChangeFinish(p0: CameraPosition?) {
                    latitude = p0!!.target.latitude
                    longitude = p0.target.longitude
                    showToast(p0.target.toString())
                }

                override fun onCameraChange(p0: CameraPosition?) {
                }
            })
        }
    }

    override fun initView() {
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
        iv_left.setImageResource(R.mipmap.img_back)
        tv_header_title.text = "选择位置"
        tv_right.text = "确认"
        tv_right.setOnClickListener {
            // 第一个参数表示一个Latlng(经纬度)，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
            val query = RegeocodeQuery(LatLonPoint(latitude, longitude), 20f, GeocodeSearch.GPS)
            geocoderSearch!!.getFromLocationAsyn(query)
        }
        mMapSearchAdapter = MapSearchAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager
        recyclerView.adapter = mMapSearchAdapter
        mMapSearchAdapter.onRecyclerItemClickListener = this
        // 地址逆解析
        geocoderSearch = GeocodeSearch(applicationContext)
        geocoderSearch!!.setOnGeocodeSearchListener(this)
        //初始化定位
        mLocationClient = AMapLocationClient(applicationContext)
        //设置定位回调监听
        mLocationClient!!.setLocationListener(this)
        //初始化定位参数
        mLocationOption = AMapLocationClientOption()
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption!!.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption!!.isNeedAddress = true
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption!!.interval = 2000
        //给定位客户端对象设置定位参数
        mLocationClient!!.setLocationOption(mLocationOption)
        //启动定位
        mLocationClient!!.startLocation()
        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.toString() != null && s.toString() != "") {
                    val query = PoiSearch.Query(s.toString().trim(), "", city)// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
                    query.pageSize = 10// 设置每页最多返回多少条poiitem
                    query.pageNum = 0// 设置查第一页
                    val poiSearch = PoiSearch(this@MapActivity, query)
                    poiSearch.setOnPoiSearchListener(this@MapActivity)
                    poiSearch.searchPOIAsyn()
                } else {
                    datas.clear()
                    mMapSearchAdapter.setDataList(datas)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }

    override fun start() {
    }

    override fun onItemClick(v: View, item: LocationBean, position: Int) {
        //将地图移动到定位点
        aMap!!.moveCamera(CameraUpdateFactory.changeLatLng(LatLng(item.latitude, item.longitude)))
        datas.clear()
        mMapSearchAdapter.setDataList(datas)
    }

    override fun onPoiItemSearched(p0: PoiItem, p1: Int) {
    }

    override fun onPoiSearched(poiResult: PoiResult, errcode: Int) {
        if (errcode == 1000) {
            datas.clear()
            val pois = poiResult.pois
            for (i in 0 until pois.size) {
                val locationBean = LocationBean()
                locationBean.add = pois[i].title
                locationBean.snippet = pois[i].snippet
                locationBean.latitude = pois[i].latLonPoint.latitude
                locationBean.longitude = pois[i].latLonPoint.longitude
                datas.add(locationBean)
            }
            mMapSearchAdapter.setDataList(datas)
        }
    }

    override fun deactivate() {
        mListener = null
    }

    override fun activate(onLocationChangedListener: LocationSource.OnLocationChangedListener) {
        mListener = onLocationChangedListener
    }

    override fun onLocationChanged(aMapLocation: AMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.errorCode == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.locationType//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.latitude//获取纬度
                aMapLocation.longitude//获取经度
                aMapLocation.accuracy//获取精度信息
                val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val date = Date(aMapLocation.time)
                df.format(date)//定位时间
                aMapLocation.address//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.country//国家信息
                aMapLocation.province//省信息
                aMapLocation.city//城市信息
                aMapLocation.district//城区信息
                aMapLocation.street//街道信息
                aMapLocation.streetNum//街道门牌号信息
                aMapLocation.cityCode//城市编码
                aMapLocation.adCode//地区编码
                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    isFirstLoc = false
                    //添加图钉
//                    aMap!!.addMarker(getMarkerOptions(amapLocation))
//                    markerOptions.position(LatLng(aMapLocation.latitude, aMapLocation.longitude))
//                    markerOptions.visible(true)
//                    val bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.img_red_yuan))
//                    markerOptions.icon(bitmapDescriptor)
//                    markerOptions.isGps = true
//                    aMap!!.addMarker(markerOptions)
                    //获取定位信息
                    val buffer = StringBuffer()
                    buffer.append(aMapLocation.country + ""
                            + aMapLocation.province + ""
                            + aMapLocation.city + ""
                            + aMapLocation.province + ""
                            + aMapLocation.district + ""
                            + aMapLocation.street + ""
                            + aMapLocation.streetNum)

                    showToast(buffer.toString())
                    currentAddress = buffer.toString()
                    city = aMapLocation.city
                    latitude = aMapLocation.latitude
                    longitude = aMapLocation.longitude
                    //设置缩放级别
                    aMap!!.moveCamera(CameraUpdateFactory.zoomTo(17f))
                    //将地图移动到定位点
                    aMap!!.moveCamera(CameraUpdateFactory.changeLatLng(LatLng(aMapLocation.latitude, aMapLocation.longitude)))
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener!!.onLocationChanged(aMapLocation)
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                showToast("" + aMapLocation.errorCode + "\n" + aMapLocation.errorInfo)
            }
        }
    }

    //根据经纬度获取地址
    override fun onRegeocodeSearched(result: RegeocodeResult?, rCode: Int) {
        currentAddress = result!!.regeocodeAddress.formatAddress
        val intent = Intent(this@MapActivity, ChangeInfoActivity::class.java)
        intent.putExtra("currentAddress", currentAddress)
        setResult(100, intent)
        finish()
    }

    override fun onGeocodeSearched(p0: GeocodeResult?, p1: Int) {
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
        mLocationClient!!.stopLocation()//停止定位
        mLocationClient!!.onDestroy()//销毁定位客户端。
    }

    override fun onDestroy() {
        super.onDestroy()
        map.onDestroy()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        map.onSaveInstanceState(outState)
    }
}