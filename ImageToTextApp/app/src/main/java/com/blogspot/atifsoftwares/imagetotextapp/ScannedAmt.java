package com.blogspot.atifsoftwares.imagetotextapp;

public class ScannedAmt {

    String activityId;
    String totalAmt;


    public ScannedAmt(){


    }


    public ScannedAmt(String activityId, String totalAmt){
        this.activityId=activityId;
        this.totalAmt=totalAmt;

    }
    public String getActivityId(){
        return activityId;
    }
    public String getTotalAmt(){
        return totalAmt;
    }

}
