package com.blogspot.atifsoftwares.imagetotextapp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class setting extends AppCompatActivity {
     EditText setGoalText;
     DatabaseReference databaseActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setGoalText = findViewById(R.id.goalSpendingText);
        databaseActivity = FirebaseDatabase.getInstance().getReference();

        Intent intent =getIntent();
        String goalAmt = intent.getStringExtra("goalAmt");

        setGoalText.setText(goalAmt);




        Button saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftKeyBoard();
                if(setGoalText.getText().toString().equals("")){

                    Toast.makeText(setting.this, "Type your goal setting", Toast.LENGTH_LONG).show();

                }
                else
                    {
                        int goalAmt = Integer.parseInt(setGoalText.getText().toString());
                        startActivity(new Intent(setting.this, HomePage.class));


                        databaseActivity.child("GoalAmount").child("value").setValue(goalAmt);

                }
            }
        });
        setGoalText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                setGoalText.setText("");
                return false;
            }
        });

    }
    private void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if(imm.isAcceptingText()) { // verify if the soft keyboard is open
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


}
