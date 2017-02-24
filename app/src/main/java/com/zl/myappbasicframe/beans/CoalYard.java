package com.zl.myappbasicframe.beans;

import java.util.List;

/**
 * 电厂ID
 * 电厂名称
 *
 *  煤场ID
 *  煤场名称
 *  煤场描述
 *  煤场存煤总数
 *  煤场有效面积
 *  设备类型
 *  底图url
 *  基础宽度
 *  基础高度
 *  对象数量
 *
 *  对象数据：
 *  （对象ID，对象名称，
 *  对象类型（1：煤堆:2：斗轮机:99：其他）
 *  对象x1，对象y1，对象x2，对象y2，对象h，
 *  对象描述1名称，对象描述1数据，
 *  对象描述2名称，对象描述2，
 *  对象描述3名称，对象描述3数据，
 *  对象描述4名称，对象描述4数据，
 *  对象描述5名称，对象描述5数据，
 *  对象描述6名称，对象描述6数据，
 *  对象描述7名称， 对象描述7数据，
 *  对象描述8名称，对象描述8数据，
 *  对象描述9名称，对象描述9数据，
 *  对象描述10名称，对象描述10数据，
 *  视频监控url地址）
 *
 * Created by Ray on 2017-01-18.
 */

public class CoalYard {
    /**
     * factoryid : 1
     * factoryname : 大同二电
     * coalyardid : 1
     * coalyardname : 二煤场
     * coalstoragedesc : 二煤场长250米，宽60米，建设于2009年，于2011年启用，储煤能力30万吨
     * coalstorage : 88
     * coalyardsize : 96800
     * devicetype : 1
     * basepicurl : http://www.google.com
     * basewide : 800
     * basehigh : 600
     * objectquantity : 22
     * data : [{"id":"1","name":"北煤堆","type":"1","x1":"1","y1":"1","x2":"2","y2":"2","x3":"3","y3":"3","x4":"4","y4":"4","h":"30","descname1":"name1","descvalue1":"value1","descname2":"name2","descvalue2":"value2","descname3":"name3","descvalue3":"value3","descname4":"name4","descvalue4":"value4","descname5":"name5","descvalue5":"value5","descname6":"name6","descvalue6":"value6","descname7":"name7","descvalue7":"value7","descname8":"name8","descvalue8":"value8","descname9":"name9","descvalue9":"value9","descname10":"name10","descvalue10":"value10","videourl":"http: //www.google.com"},{"id":"2","name":"南煤堆","type":"2","x1":"5","y1":"5","x2":"6","y2":"6","x3":"7","y3":"7","x4":"8","y4":"8","h":"30","descname1":"name1","descvalue1":"value1","descname2":"name2","descvalue2":"value2","descname3":"name3","descvalue3":"value3","descname4":"name4","descvalue4":"value4","descname5":"name5","descvalue5":"value5","descname6":"name6","descvalue6":"value6","descname7":"name7","descvalue7":"value7","descname8":"name8","descvalue8":"value8","descname9":"name9","descvalue9":"value9","descname10":"name10","descvalue10":"value10","videourl":"http: //www.google.com"}]
     */

    private String factoryid;
    private String factoryname;
    private String coalyardid;
    private String coalyardname;
    private int coalstorage;
    private String coalstoragedesc;
    private int coalyardsize;
    private int devicetype;
    private String basepicurl;
    private int basewide;
    private int basehigh;
    private int objectquantity;
    private List<DataBean> data;

    public String getFactoryid() {
        return factoryid;
    }

    public void setFactoryid(String factoryid) {
        this.factoryid = factoryid;
    }

    public String getFactoryname() {
        return factoryname;
    }

    public void setFactoryname(String factoryname) {
        this.factoryname = factoryname;
    }

    public String getCoalyardid() {
        return coalyardid;
    }

    public void setCoalyardid(String coalyardid) {
        this.coalyardid = coalyardid;
    }

    public String getCoalyardname() {
        return coalyardname;
    }

    public void setCoalyardname(String coalyardname) {
        this.coalyardname = coalyardname;
    }

    public int getCoalstorage() {
        return coalstorage;
    }

    public void setCoalstorage(int coalstorage) {
        this.coalstorage = coalstorage;
    }

    public String getCoalstoragedesc() {
        return coalstoragedesc;
    }

    public void setCoalstoragedesc(String coalstoragedesc) {
        this.coalstoragedesc = coalstoragedesc;
    }

    public int getCoalyardsize() {
        return coalyardsize;
    }

    public void setCoalyardsize(int coalyardsize) {
        this.coalyardsize = coalyardsize;
    }

    public int getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(int devicetype) {
        this.devicetype = devicetype;
    }

    public String getBasepicurl() {
        return basepicurl;
    }

    public void setBasepicurl(String basepicurl) {
        this.basepicurl = basepicurl;
    }

    public int getBasewide() {
        return basewide;
    }

    public void setBasewide(int basewide) {
        this.basewide = basewide;
    }

    public int getBasehigh() {
        return basehigh;
    }

    public void setBasehigh(int basehigh) {
        this.basehigh = basehigh;
    }

    public int getObjectquantity() {
        return objectquantity;
    }

    public void setObjectquantity(int objectquantity) {
        this.objectquantity = objectquantity;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 北煤堆
         * type : 1
         * x1 : 1
         * y1 : 1
         * x2 : 2
         * y2 : 2
         * x3 : 3
         * y3 : 3
         * x4 : 4
         * y4 : 4
         * h : 30
         * descname1 : name1
         * descvalue1 : value1
         * descname2 : name2
         * descvalue2 : value2
         * descname3 : name3
         * descvalue3 : value3
         * descname4 : name4
         * descvalue4 : value4
         * descname5 : name5
         * descvalue5 : value5
         * descname6 : name6
         * descvalue6 : value6
         * descname7 : name7
         * descvalue7 : value7
         * descname8 : name8
         * descvalue8 : value8
         * descname9 : name9
         * descvalue9 : value9
         * descname10 : name10
         * descvalue10 : value10
         * videourl : http: //www.google.com
         */

        private String id;
        private String name;
        private String type;
        private String x1;
        private String y1;
        private String x2;
        private String y2;
        private String x3;
        private String y3;
        private String x4;
        private String y4;
        private String h;
        private String descname1;
        private String descvalue1;
        private String descname2;
        private String descvalue2;
        private String descname3;
        private String descvalue3;
        private String descname4;
        private String descvalue4;
        private String descname5;
        private String descvalue5;
        private String descname6;
        private String descvalue6;
        private String descname7;
        private String descvalue7;
        private String descname8;
        private String descvalue8;
        private String descname9;
        private String descvalue9;
        private String descname10;
        private String descvalue10;
        private String videourl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getX1() {
            return x1;
        }

        public void setX1(String x1) {
            this.x1 = x1;
        }

        public String getY1() {
            return y1;
        }

        public void setY1(String y1) {
            this.y1 = y1;
        }

        public String getX2() {
            return x2;
        }

        public void setX2(String x2) {
            this.x2 = x2;
        }

        public String getY2() {
            return y2;
        }

        public void setY2(String y2) {
            this.y2 = y2;
        }

        public String getX3() {
            return x3;
        }

        public void setX3(String x3) {
            this.x3 = x3;
        }

        public String getY3() {
            return y3;
        }

        public void setY3(String y3) {
            this.y3 = y3;
        }

        public String getX4() {
            return x4;
        }

        public void setX4(String x4) {
            this.x4 = x4;
        }

        public String getY4() {
            return y4;
        }

        public void setY4(String y4) {
            this.y4 = y4;
        }

        public String getH() {
            return h;
        }

        public void setH(String h) {
            this.h = h;
        }

        public String getDescname1() {
            return descname1;
        }

        public void setDescname1(String descname1) {
            this.descname1 = descname1;
        }

        public String getDescvalue1() {
            return descvalue1;
        }

        public void setDescvalue1(String descvalue1) {
            this.descvalue1 = descvalue1;
        }

        public String getDescname2() {
            return descname2;
        }

        public void setDescname2(String descname2) {
            this.descname2 = descname2;
        }

        public String getDescvalue2() {
            return descvalue2;
        }

        public void setDescvalue2(String descvalue2) {
            this.descvalue2 = descvalue2;
        }

        public String getDescname3() {
            return descname3;
        }

        public void setDescname3(String descname3) {
            this.descname3 = descname3;
        }

        public String getDescvalue3() {
            return descvalue3;
        }

        public void setDescvalue3(String descvalue3) {
            this.descvalue3 = descvalue3;
        }

        public String getDescname4() {
            return descname4;
        }

        public void setDescname4(String descname4) {
            this.descname4 = descname4;
        }

        public String getDescvalue4() {
            return descvalue4;
        }

        public void setDescvalue4(String descvalue4) {
            this.descvalue4 = descvalue4;
        }

        public String getDescname5() {
            return descname5;
        }

        public void setDescname5(String descname5) {
            this.descname5 = descname5;
        }

        public String getDescvalue5() {
            return descvalue5;
        }

        public void setDescvalue5(String descvalue5) {
            this.descvalue5 = descvalue5;
        }

        public String getDescname6() {
            return descname6;
        }

        public void setDescname6(String descname6) {
            this.descname6 = descname6;
        }

        public String getDescvalue6() {
            return descvalue6;
        }

        public void setDescvalue6(String descvalue6) {
            this.descvalue6 = descvalue6;
        }

        public String getDescname7() {
            return descname7;
        }

        public void setDescname7(String descname7) {
            this.descname7 = descname7;
        }

        public String getDescvalue7() {
            return descvalue7;
        }

        public void setDescvalue7(String descvalue7) {
            this.descvalue7 = descvalue7;
        }

        public String getDescname8() {
            return descname8;
        }

        public void setDescname8(String descname8) {
            this.descname8 = descname8;
        }

        public String getDescvalue8() {
            return descvalue8;
        }

        public void setDescvalue8(String descvalue8) {
            this.descvalue8 = descvalue8;
        }

        public String getDescname9() {
            return descname9;
        }

        public void setDescname9(String descname9) {
            this.descname9 = descname9;
        }

        public String getDescvalue9() {
            return descvalue9;
        }

        public void setDescvalue9(String descvalue9) {
            this.descvalue9 = descvalue9;
        }

        public String getDescname10() {
            return descname10;
        }

        public void setDescname10(String descname10) {
            this.descname10 = descname10;
        }

        public String getDescvalue10() {
            return descvalue10;
        }

        public void setDescvalue10(String descvalue10) {
            this.descvalue10 = descvalue10;
        }

        public String getVideourl() {
            return videourl;
        }

        public void setVideourl(String videourl) {
            this.videourl = videourl;
        }
    }
}
