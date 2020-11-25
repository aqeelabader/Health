package com.aqeel.abaderhealth;

public class Udetail {
    String Userid;
    String Fullname;
    String height;
    String weight;
    String calorie;
    String Theight;
    String Tweight;
    String Tcalorie;

    public Udetail() {
        //do not remove
    }

    public Udetail(String userid, String fullname, String height, String weight, String calorie, String theight, String tweight, String tcalorie) {
        Userid = userid;
        Fullname = fullname;
        this.height = height;
        this.weight = weight;
        this.calorie = calorie;
        Theight = theight;
        Tweight = tweight;
        Tcalorie = tcalorie;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getTheight() {
        return Theight;
    }

    public void setTheight(String theight) {
        Theight = theight;
    }

    public String getTweight() {
        return Tweight;
    }

    public void setTweight(String tweight) {
        Tweight = tweight;
    }

    public String getTcalorie() {
        return Tcalorie;
    }

    public void setTcalorie(String tcalorie) {
        Tcalorie = tcalorie;
    }


    public String ToString(){
        return "Full Name : " + Fullname +"\n"+"Height : "+height+"\n"+"Weight : "+weight+"\n"+"Calorie : "+calorie+"\n"+"Target Height : "+Theight+"\n"+"Target Weight : "+Tweight+"\n"+"Target Calorie : "+Tcalorie;
    }
}
