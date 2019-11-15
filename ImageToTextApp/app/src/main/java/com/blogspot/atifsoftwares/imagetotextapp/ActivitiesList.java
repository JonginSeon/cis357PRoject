package com.blogspot.atifsoftwares.imagetotextapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ActivitiesList extends ArrayAdapter<Activities> {


    private Activity context;
    private List<Activities> actList;


    public ActivitiesList (Activity context, List<Activities>  actList){
      super(context,R.layout.list_layout,actList);
        this.context = context;
        this.actList = actList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout,null , true);

        TextView name = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView total = (TextView) listViewItem.findViewById(R.id.textViewTotal);

        Activities activities = actList.get(position);

        name.setText(activities.getActivityId());
        total.setText(activities.getTotalAmt());
        return listViewItem;

    }
}
