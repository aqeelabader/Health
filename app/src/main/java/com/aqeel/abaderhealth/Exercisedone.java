package com.aqeel.abaderhealth;

public class Exercisedone {
    String usersid;
    String exerciseType;
    String caloriesburned;
    String weightchanged;

    public Exercisedone(String usersid, String exerciseType, String caloriesburned, String weightchanged) {
        this.usersid = usersid;
        this.exerciseType = exerciseType;
        this.caloriesburned = caloriesburned;
        this.weightchanged = weightchanged;
    }

    public Exercisedone() {
        //dont remove
    }

    public String getUsersid() {
        return usersid;
    }

    public void setUsersid(String usersid) {
        this.usersid = usersid;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getCaloriesburned() {
        return caloriesburned;
    }

    public void setCaloriesburned(String caloriesburned) {
        this.caloriesburned = caloriesburned;
    }

    public String getWeightchanged() {
        return weightchanged;
    }

    public void setWeightchanged(String weightchanged) {
        this.weightchanged = weightchanged;
    }

    public String ToAString(){
        return "Exercise Type : "+exerciseType+"\n"+"Calories Burned : "+caloriesburned+"\n"+"weight change : "+weightchanged;
    }
}
