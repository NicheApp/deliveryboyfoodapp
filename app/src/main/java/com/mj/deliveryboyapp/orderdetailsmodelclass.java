package com.mj.deliveryboyapp;

import java.math.BigDecimal;

public class orderdetailsmodelclass {
   private String orderid,name,mobile,email,add,res,price,type,dltime,pitime,resadd,resnum,resid,lat,lon;

   private int deliverying;
    public  orderdetailsmodelclass(String orderid, String name, String mobile, String email, String add,String lat,String lon,String res, String price,String type, String dltime, String pitime, int deliverying,String resadd,String resnum,String resid)
    {
this.orderid=orderid;
this.name=name;
this.mobile=mobile;
this.email=email;
this.add=add;
this.lat=lat;
this.lon=lon;
this.res=res;
this.price=price;
this.type=type;
this.dltime=dltime;
this.pitime=pitime;
this.resadd=resadd;
this.resnum=resnum;
this.resid=resid;

    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDltime() {
        return dltime;
    }

    public void setDltime(String dltime) {
        this.dltime = dltime;
    }

    public String getPitime() {
        return pitime;
    }

    public void setPitime(String pitime) {
        this.pitime = pitime;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getResadd() {
        return resadd;
    }

    public void setResadd(String resadd) {
        this.resadd = resadd;
    }

    public String getResnum() {
        return resnum;
    }

    public void setResnum(String resnum) {
        this.resnum = resnum;
    }

    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid;
    }

    public int getDeliverying() {
        return deliverying;
    }

    public void setDeliverying(int deliverying) {
        this.deliverying = deliverying;
    }
}
