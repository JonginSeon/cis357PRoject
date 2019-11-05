package com.blogspot.atifsoftwares.imagetotextapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class HomePage extends AppCompatActivity {
    DatabaseReference databaseActivity;
    double total;

    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        databaseActivity = FirebaseDatabase.getInstance().getReference("activities");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView totalSpent = (TextView) findViewById(R.id.totalSpent);

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

                totalSpent.setText(String.format("%.2f", total));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());

            }


        });


    }


}
