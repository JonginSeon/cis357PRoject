package com.blogspot.atifsoftwares.imagetotextapp;

public class Activities {

    String activityId;
    String totalAmt;
    String date;

    public Activities(){


    }


    public Activities(String activityId, String totalAmt, String date){
        this.activityId=activityId;
        this.totalAmt=totalAmt;
        this.date=date;
    }
    public String getActivityId(){
        return activityId;
    }
    public String getTotalAmt(){
        return totalAmt;
    }

    public  String getDate(){
        return date;
    }
}
