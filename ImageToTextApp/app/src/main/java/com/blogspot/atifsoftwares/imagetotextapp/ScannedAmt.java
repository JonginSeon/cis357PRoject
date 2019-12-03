package com.blogspot.atifsoftwares.imagetotextapp;

public class ScannedAmt {

    String activityId;
    String totalAmt;
    String title;
    String date;


    public ScannedAmt(){


    }


    public ScannedAmt(String activityId, String totalAmt,String title,String date){
        this.activityId=activityId;
        this.totalAmt=totalAmt;
        this.title=title;
        this.date=date;
    }
    public String getActivityId(){
        return activityId;
    }
    public String getTotalAmt(){
        return totalAmt;
    }
    public String getTitle(){ return title; }
    public String getDate(){ return date; }


}
