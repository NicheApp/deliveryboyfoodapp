package com.mj.deliveryboyapp;
public class Myhomedata {

    private String restadd;
    private String custadd;
    private String pickuptime;
    private String delivtime;

    public Myhomedata(String restadd, String custadd, String pickuptime, String delivtime) {
        this.restadd = restadd;
        this.custadd = custadd;
        this.pickuptime = pickuptime;
        this.delivtime = delivtime;
    }

    public String getRestadd() {
        return restadd;
    }

    public void setRestadd(String restadd) {
        this.restadd = restadd;
    }

    public String getCustadd() {
        return custadd;
    }

    public void setCustadd(String custadd) {
        this.custadd = custadd;
    }

    public String getPickuptime() {
        return pickuptime;
    }

    public void setPickuptime(String pickuptime) {
        this.pickuptime = pickuptime;
    }

    public String getDelivtime() {
        return delivtime;
    }

    public void setDelivtime(String delivtime) {
        this.delivtime = delivtime;
    }
}
