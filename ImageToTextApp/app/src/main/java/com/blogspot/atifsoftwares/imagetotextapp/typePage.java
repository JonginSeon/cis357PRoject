package com.blogspot.atifsoftwares.imagetotextapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class typePage extends AppCompatActivity {


     TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    EditText totalText;
    EditText dateText;
    Button finishBtn;
    EditText titleText;

    DatabaseReference databaseActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_page);


        databaseActivity = FirebaseDatabase.getInstance().getReference("activities");
         totalText = (EditText) findViewById(R.id.totalText);
//         dateText = (EditText) findViewById(R.id.dateText);
         finishBtn = (Button) findViewById(R.id.finishBtn);
        titleText = (EditText) findViewById(R.id.titleText);
        mDisplayDate = (TextView) findViewById(R.id.dateView);

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addActivityManually();
               startActivity(new Intent(typePage.this, HomePage.class));
            }
        });
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal  = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        typePage.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

        }
    });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month+1;
                String date = month+"/"+dayOfMonth+"/"+year;
                mDisplayDate.setText(date);
            }
        };
    }
    private void addActivityManually(){

        String totalAmt = totalText.getText().toString();

        String title = titleText.getText().toString();
        if(!totalAmt.matches("") &&!mDisplayDate.getText().toString().matches("")) {

            String id = databaseActivity.push().getKey();
            Activities activity = new Activities(id,totalAmt,mDisplayDate.getText().toString(),title);
            databaseActivity.child(id).setValue(activity);
            Toast.makeText(this, "Activity is successfully added",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this, "You should complete Total or Date",Toast.LENGTH_LONG).show();

        }
    }


}
