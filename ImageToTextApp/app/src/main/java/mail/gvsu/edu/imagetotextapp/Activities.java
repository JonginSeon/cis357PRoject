package mail.gvsu.edu.imagetotextapp;

public class Activities {

    String activityId;
    String totalAmt;
    String date;
    String title;
    public Activities(){
    }


    public Activities(String activityId, String totalAmt, String date, String title){
        this.activityId=activityId;
        this.totalAmt=totalAmt;
        this.date=date;
        this.title=title;
    }
    public String getActivityId(){
        return activityId;
    }
    public String getTotalAmt(){
        return totalAmt;
    }
    public String getTitle(){
        return title;
    }


    public  String getDate(){
        return date;
    }
}
