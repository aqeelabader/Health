package com.aqeel.abaderhealth;

public class Mealseaten {
    String usersid;
    String mealname;
    String mealcals;

    public Mealseaten(String usersid, String mealname, String mealcals) {
        this.usersid = usersid;
        this.mealname = mealname;
        this.mealcals = mealcals;
    }

    public Mealseaten() {
    }

    public String getUsersid() {
        return usersid;
    }

    public void setUsersid(String usersid) {
        this.usersid = usersid;
    }

    public String getMealname() {
        return mealname;
    }

    public void setMealname(String mealname) {
        this.mealname = mealname;
    }

    public String getMealcals() {
        return mealcals;
    }

    public void setMealcals(String mealcals) {
        this.mealcals = mealcals;
    }

    public String mealString(){
        return "Meal : "+mealname+"\n"+"Meal calories : "+mealcals;
    }
}
