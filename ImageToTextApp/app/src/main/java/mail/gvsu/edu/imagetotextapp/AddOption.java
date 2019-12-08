package mail.gvsu.edu.imagetotextapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mail.gvsu.edu.imagetotextapp.R;

public class AddOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_option);
        Button typeBtn = (Button) findViewById(R.id.typeBtn);
        Button scanBtn = (Button) findViewById(R.id.scanBtn);


        typeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddOption.this, typePage.class));
            }
        });


        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddOption.this, MainActivity.class));
            }
        });


    }
}