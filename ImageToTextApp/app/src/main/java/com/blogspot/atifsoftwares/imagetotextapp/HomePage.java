package com.blogspot.atifsoftwares.imagetotextapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class HomePage extends AppCompatActivity {
    DatabaseReference databaseActivity;
    DatabaseReference databaseSetting;
    DatabaseReference databaseSetting1;
    DatabaseReference data;
    DatabaseReference percentage;




    double total;
    double goal;
    double goal1;
    TextView setGoalAmt;
    ProgressBar pb;
    int counter = 0;
    TextView value;
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        databaseActivity = FirebaseDatabase.getInstance().getReference("activities");
        databaseSetting = FirebaseDatabase.getInstance().getReference("GoalAmount/value");
        databaseSetting1 = FirebaseDatabase.getInstance().getReference("GoalAmount/value");
        data = FirebaseDatabase.getInstance().getReference();
        percentage =  FirebaseDatabase.getInstance().getReference("percentage/value");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView totalSpent = (TextView) findViewById(R.id.totalSpent);
        TextView spent = (TextView) findViewById(R.id.totalSpent);

        FloatingActionButton addBtn = findViewById(R.id.fab);
        FloatingActionButton historyBtn = findViewById(R.id.historyBtn);

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, history.class));
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, AddOption.class));
            }
        });

        databaseActivity.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Activities activity = postSnapshot.getValue(Activities.class);
                    double  sum = Double.parseDouble(activity.totalAmt);
                    total += sum;
                    Log.d(TAG, "Total: " + activity.totalAmt);

                }


                goal = total;
                totalSpent.setText("$"+String.format("%.2f", total));


                int percentage =  calculate(total);
                prog(percentage,total );

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());

            }


        });

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                setGoalAmt = findViewById(R.id.setGoalAmt);
                goal1= Double.valueOf(dataSnapshot.getValue().toString());
                setGoalAmt.setText("$"+dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        databaseSetting.addValueEventListener(postListener);

        percentage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                pb=findViewById(R.id.progressBar);
              pb.setProgress(Integer.valueOf(dataSnapshot.getValue().toString()));

             // pb.setProgress(Integer.valueOf("90"));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });






     //    double i =  goal;
        // int percentage =  calculate("50","100");
     //   int percentage =  calculate(spent.getText().toString(),setGoalAmt.getText().toString());
     //  prog(percentage);
    }

    //actionbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //handle actionbar item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.settings){
            startActivity(new Intent(HomePage.this, setting.class));
            Intent intent = new Intent(HomePage.this, setting.class);

            intent.putExtra("goalAmt",setGoalAmt.getText().toString());
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public int calculate(final double total){

        final double[] result = {0.0};

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                goal1= Double.valueOf(dataSnapshot.getValue().toString());
                setGoalAmt.setText("$"+dataSnapshot.getValue().toString());
                    int i = Integer.valueOf(dataSnapshot.getValue().toString());
                    final int z = i;
                pb = findViewById(R.id.progressBar);
                value.setText(""+(total/i)*100);

//                final Timer t = new Timer();
//                TimerTask tt = new TimerTask() {
//                    @Override
//                    public void run()
//                    {
//                        counter++;
//                        pb.setProgress(counter);
//
//                        if(counter ==10) {
//                            t.cancel();
//                        }
//                    }
//                };
//
//                t.schedule(tt,0,100);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        databaseSetting1.addValueEventListener(postListener);




        return (int) result[0];



    }
    public void prog(int percent,final double total){

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value=findViewById(R.id.percent);
                pb = findViewById(R.id.progressBar);
                // Get Post object and use the values to update the UI
                goal1= Double.valueOf(dataSnapshot.getValue().toString());
                setGoalAmt.setText("$"+dataSnapshot.getValue().toString());
                final int i = Integer.valueOf(dataSnapshot.getValue().toString());
                final int z = i;
                pb = findViewById(R.id.progressBar);
                value.setText(String.format("%.0f", (total/i)*100)+"% reached this so this month");
                data.child("percentage").child("value").setValue(String.format("%.0f", (total/i)*100));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        databaseSetting1.addValueEventListener(postListener);





        pb = findViewById(R.id.progressBar);
        final int converToInt =  percent;
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run()
            {
                counter++;
                pb.setProgress(counter);

//                if(counter == 50)
//                    t.cancel();
            }
        };

//        t.schedule(tt,0,100);
       value=findViewById(R.id.percent);
        //value.setText(converToInt+"% spent so far " );

    }

}
