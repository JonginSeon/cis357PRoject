package com.blogspot.atifsoftwares.imagetotextapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.NumberUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class typePage extends AppCompatActivity {

    EditText totalText;
    EditText dateText;
    Button finishBtn;

    DatabaseReference databaseActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_page);


        databaseActivity = FirebaseDatabase.getInstance().getReference("activities");
         totalText = (EditText) findViewById(R.id.totalText);
         dateText = (EditText) findViewById(R.id.dateText);
         finishBtn = (Button) findViewById(R.id.finishBtn);


        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("message");
//
//                myRef.setValue("Hello, World!");
               addActivityManually();
               startActivity(new Intent(typePage.this, HomePage.class));
            }
        });

    }
    private void addActivityManually(){

        String totalAmt = totalText.getText().toString();
        String date = dateText.getText().toString();

        if(!totalAmt.matches("") &&!date.matches("")) {

            String id = databaseActivity.push().getKey();
            Activities activity = new Activities(id,totalAmt,date);
            databaseActivity.child(id).setValue(activity);
          Toast.makeText(this, "Activity is successfully added",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this, "You should complete Total or Date",Toast.LENGTH_LONG).show();
        }
    }


}
