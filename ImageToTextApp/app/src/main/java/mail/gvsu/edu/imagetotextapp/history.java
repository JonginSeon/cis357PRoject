package mail.gvsu.edu.imagetotextapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import mail.gvsu.edu.imagetotextapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class history extends AppCompatActivity {
    ListView listViewActivity ;
    DatabaseReference databaseActivity;

    List<Activities> activitiesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseActivity= FirebaseDatabase.getInstance().getReference("activities");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activitiesList = new ArrayList<>();
        listViewActivity = (ListView) findViewById(R.id.listviewAct);

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseActivity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                activitiesList.clear();
                for(DataSnapshot activitySnapshot :dataSnapshot.getChildren()){
                    Activities act =activitySnapshot.getValue(Activities.class);
                    activitiesList.add(act);
                }
                ActivitiesList adapter = new ActivitiesList(history.this, activitiesList);
                listViewActivity.setAdapter((adapter));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
