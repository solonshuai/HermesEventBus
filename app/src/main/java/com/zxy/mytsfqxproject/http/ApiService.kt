package com.zxy.mytsfqxproject.http

import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.mvp.entity.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("Login/sendSmsCode")
    fun yanSmsCode(@Body route: RequestBody): Call<JsonObject>

    @POST("login/findLoginPwd")
    fun findLoginPwd(@Body route: RequestBody): Call<JsonObject>

    @POST("Login/login")
    fun login(@Body route: RequestBody): Call<Result<UserBean>>

    @POST("Appmenu/getMenu")
    fun getMenu(@Body route: RequestBody): Call<MenuBean>

    //获取当前车牌的所有信息
    @POST("Communal/getClientDetail")
    fun getClientDetail(@Body route: RequestBody): Observable<CarDetailBean>

    //获取客户列表
    @POST("Client/applists")
    fun getChooseCustomer(@Body route: RequestBody): Call<ChooseCustomer>

    @POST("Client/addClient")
    fun addClient(@Body route: RequestBody): Call<JsonObject>

    @POST("Client/editClient")
    fun editClient(@Body route: RequestBody): Call<JsonObject>

    @POST("Client/applists")//客户管理
    fun applists(@Body route: RequestBody): Call<ChooseCustomer>

    @POST("Communal/searchProjectOrMaterial")//添加服务
    fun searchProjectOrMaterial(@Body route: RequestBody): Call<FuwuBean>

    @POST("Communal/searchProjectOrMaterial")
    fun searchProjectPeijian(@Body route: RequestBody): Call<PeiJianBean>

    @POST("Communal/searchProjectOrMaterial")
    fun searchProjectPeijian(): Call<PeiJianBean>

    @POST("Communal/appCarBrandList")
    fun appCarBrandList(): Call<CarBean>

    @POST("Communal/appCarBranddetails")
    fun CarBranddetails(@Body route: RequestBody): Call<CarBean>

    @POST("Client/clientDetail")
    fun clientDetail(@Body route: RequestBody): Call<clientDetail>

    @POST("Car/appCarDetails")
    fun CarDetail(@Body route: RequestBody): Call<CarListDetailBean>

    @POST("Car/relieveClient")//车辆详情的解绑
    fun relieveClient(@Body route: RequestBody): Call<JsonObject>

    @POST("Member/cardList")//选择卡种
    fun cardList(): Call<CarLostBean>

    @POST("Staff/appLists")//提成员工
    fun appLists(): Call<CommissionBean>

    @POST("BusinessPara/queryBusinessPara")//获取支付方式
    fun queryBusinessPara(@Body route: RequestBody): Call<PayTypeBean>

    @POST("Member/appCreateMember")//开卡
    fun appCreateMember(@Body route: RequestBody): Call<JsonObject>

    @POST("Member/getCardDetails")//开卡
    fun getCardDetails(@Body route: RequestBody): Call<CardDetailBean>

    @POST("Communal/insuranceList")//选择保险公司
    fun insuranceList(): Call<InsuranceBean>

    @Multipart
    @POST("Car/appSaveCar")//保存新增车辆
    fun appSaveCar(@PartMap params: HashMap<String, RequestBody>, @Part imgList: MultipartBody.Part): Call<JsonObject>

    @POST("Car/appSaveCar")//保存新增车辆
    fun appSaveCar2(@Body route: RequestBody): Call<JsonObject>

    @POST("repair/appRepair")//服务开单-接待
    fun appRepair(@Body route: RequestBody): Call<JsonObject>

    @POST("Bespeak/pandect")//日历订单数量
    fun pandect(@Body route: RequestBody): Call<OrdeNumBean>

    @POST("Bespeak/byDayBespeak")//日历订单列表
    fun byDayBespeak(@Body route: RequestBody): Call<OrdeListBean>

    @POST("Car/lists")//日历 新建订单列表
    fun lists(@Body route: RequestBody): Call<NewAddOrderBean>

    @POST("Bespeak/bespeakServiceList")//新建预约单的服务类别
    fun bespeakServiceList(): Call<BespeakServiceBean>

    @POST("Bespeak/createBespeak")//新建预约单  -保存
    fun createBespeak(@Body route: RequestBody): Call<JsonObject>

    @POST("login/tsfDefault")//app 配置信息
    fun tsfDefault(): Call<ConfigurationInfor>

    @POST("Repair/apprepairList")//服务订单
    fun apprepairList(@Body route: RequestBody): Call<RankFuddBean>

    @POST("repair/appRepairFlow")//服务订单 -详情
    fun appRepairFlow(@Body route: RequestBody): Call<FwDetailBean>

    @POST("repair/yesProjectuUpdate")//服务订单 -状态
    fun yesProjectuUpdate(): Call<JsonObject>

    @POST("repair/carHistory")//车辆的进店历史
    fun carHistory(@Body route: RequestBody): Call<IntoShopBean>

    @POST("Repair/repairInfo")//查看接车详情 /报价单
    fun repairInfo(@Body route: RequestBody): Call<RepairInfosBean>

    @POST("repair/verifyCarDetail")//查看检车详情
    fun verifyCarDetail(@Body route: RequestBody): Call<JianCheDetailBean>

    @POST("repair/saveVerifyCarDetail")//检车 提交
    fun saveVerifyCarDetail(@Body route: RequestBody): Call<JsonObject>

    @POST("repair/getGoodsList")//检车 增加服务 /派工
    fun getGoodsList(@Body route: RequestBody): Call<AddFuwuBean>

    @FormUrlEncoded
    @POST("repair/saveGoodsList")//检车 保存服务
    fun saveGoodsList(@FieldMap params: HashMap<String, Any>): Call<JsonObject>

    @POST("Staff/lists")//技师派工选择
    fun getChooseTech(): Call<ChooseTechBean>

    @POST("repair/repairAssign")//技师派工
    fun repairAssign(@Body route: RequestBody): Call<JsonObject>

    @FormUrlEncoded
    @POST("Repair/clearRepair")//结算
    fun clearRepair(@FieldMap params: HashMap<String, Any>): Call<JsonObject>

    @FormUrlEncoded
    @POST("Member/clientMember")//结算页面 获取会员卡
    fun clientMember(@FieldMap params: HashMap<String, Any>): Call<ClientCardBean>

    @FormUrlEncoded
    @POST("repair/getRepairClearInfo")//结算页面 获取详情
    fun getRepairClearInfo(@FieldMap params: HashMap<String, Any>): Call<RepairClearInfoBean>

    @FormUrlEncoded
    @POST("Receipt/receiptRepair")//结算页面 结算
    fun receiptRepair(@FieldMap params: HashMap<String, Any>): Call<JsonObject>

    @POST("repair/storeRepairList")//过程管理
    fun storeRepairList(): Call<GcglBean>

    @POST("Store/storeInfo")//门店信息 Staff/findStaffInfo
    fun storeInfo(): Call<StoreInfoBean>

    @POST("Staff/findStaffInfo")
    fun findStaffInfo(): Call<StoreInfoBean>

    @FormUrlEncoded
    @POST("Store/appUpdateStore")//编辑门店信息保存
    fun appUpdateStore(@FieldMap params: HashMap<String, Any>): Call<JsonObject>


    @Multipart
    @POST("Staff/appsaveStaff")//个人信息保存
    fun appSaveStaf(@PartMap params: HashMap<String, RequestBody>, @Part photo: MultipartBody.Part): Call<JsonObject>

    @POST("staff/staffInfo")//个人中心
    fun staffInfo(): Call<StaffInfoBean>

    @FormUrlEncoded
    @POST("store/feedback")//建议反馈
    fun feedback(@Field("content") content: String): Call<JsonObject>

    @FormUrlEncoded
    @POST("Bespeak/cancelBespeak")//取消订单
    fun cancelBespeak(@Field("bespeak_id") bespeak_id: Int): Call<JsonObject>

    @FormUrlEncoded
    @POST("staff/appAlterLoginPwd")//修改密码
    fun appAlterLoginPwd(@Field("old_pwd") old_pwd: String, @Field("new_pwd") new_pwd: String): Call<JsonObject>

    @POST("client/gatherInfo")//电话号码
    fun gatherInfo(@Body route: RequestBody): Call<JsonObject>
}