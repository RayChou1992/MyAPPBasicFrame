package com.zl.myappbasicframe.utils;

/**
 * Created by thinkpad on 2016-04-19.
 */
public class Constant {


    /**
     * 这里传经纬度，获取地址
     */
    public final static String URL_GET_ADDRESS = "/transit/inMine";
    /**
     * 发车登记
     */
    public final static String URL_FACHEDENGJI = "/transit/mineDepartCar";
    /**
     * 添加终点地址
     */
    public final static String URL_ENDADDRESS = "/transit/mineDepart";
    /**
     * 可选择地址
     */
    public final static String URL_CHOSE_ADDRESS = "/transit/mineInfrastructure";
    /**
     * 新建地址
     */
    public final static String URL_NEW_ADDRESS = "/transit/mineSaveIstct";
    /**
     * 搜索匹配地址
     */
    public final static String URL_SEARCH_ADDRESS = "/transit/searchIstct";
    /**
     * 发车登记修改
     */
    public final static String URL_FACHEDENGJI_XIUGAI = "/transit/mineCarInfo";
    /**
     * 订单获取列表
     */
    public final static String URL_DINGDAN_LIST = "/order/orderList";
    /**
     * 基础设施查找
     */
    public final static String URL_ADDRESSFIND_INDENT = "/transit/searchIstct";

    /**
     * 订单发布
     */
    public final static String URL_PUBLISH_INDENT = "/order/saveOrder";
    /**
     * 抢单/接单
     */
    public final static String URL_QIANGDAN_JIEDAN = "/order/orderDetail";
    /**
     * 获取煤山的列表
     */
    public final static String URL_GET_MEISHANLIST = "/unloadCoal/locationHome";
    /**
     * 保存车辆卸煤记录
     */
    public final static String URL_SAVE_XIEMEI = "/unloadCoal/saveYard";
    /**
     * 车辆当天相应煤场卸煤信息
     */
    public final static String URL_XIEMEIMENGSSAGELIST = "/unloadCoal/getYardRecordList";
    /**
     * 保存煤场信息
     */
    public final static String URL_SAVECAOLYARD = "/unloadCoal/saveCoalYard";
    /**
     * 查询煤场下 子元素
     */
    public final static String URL_SELECT_CHILD = "/unloadCoal/getSubCoal";
    /**
     * 卸煤直接定位场地(最具体的位置)
     */
    public final static String URL_ADDRESS_UNLOAD = "/unloadCoal/unloadLocation";

    //所有在途汽车
    public final static String URL_GET_ALL_CAR = "/transit/getAllTransitCar";
    //所有在途汽车
    public final static String URL_GET_ALL_CHANNEL = "/transit/getAllChannnel";
    //车路径
    public final static String URL_GET__CAR_ALL_GIS = "/transit/getCarDatail?carNumber=";
    //40秒间的路径点
    public final static String POINTS = "/qiczt/getjingwd";
    //BASE_URL
    public final static String STATUS = "/qiczt/fupaiz";
    //异常拍照
    public final static String SINGLE_PHOTO = "/qiczt/getfuwpic";
    //所有异常照片2
    public final static String ALL_PHOTO = "/transit/getCarPic?carNumber=";

    /**
     * 检查更新
     **/
    public final static String URL_UPDATE = "/qiczt/appupdate";
//            + "/app/car_online/gengxinqczt";//旧地址

    public final static String URL_REGISTER_ONE = "/sendSMS?telphone=";
    /**
     * 验证码发送后 点击下一步  验证用户
     */
    public final static String URL_REGISTER_TWO = "/checkRegister?telphone=";
    /**
     * 注册
     */
    public final static String URL_REGISTER_THREE = "/register";

    /**
     * 登录
     */
    public final static String URL_REGISTER_LOGIN = "/login";

    /**
     * 个人信息设置
     */
    public final static String URL_PERSONAL_SETTING = "/information";
    /**
     * 修改或找回密码
     */
    public final static String URL_MODIFY_PWD = "/modifyPwd";
    /**
     * 获取用户信息
     */
    public final static String URL_GET_INFO = "/getInformation?telphone=";
    /**
     * 获取各煤场的卸车统计
     */
    public final static String URL_GET_COAL_STATISTICS = "/unloadCoal/unloadCoalManage";
    /**
     * 获取各煤场的卸车统计明细
     */
    public final static String URL_GET_COAL_STATISTICS_DETAIL = "/unloadCoal/unloadCarDetail";
    /**
     * 获取各煤场的库存统计
     */
    public final static String URL_GET_STOCK = "/unloadCoal/stockManage";
    /**
     * 获取驻矿的发货统计
     */
    public final static String URL_GET_MINE_STATISTICS= "/transit/mineStatis";
    /**
     * 获取矿发统计明细
     */
    public final static String URL_GET_MINE_STATISTICS_DETAIL= "/transit/mineStatisDetail";

}
