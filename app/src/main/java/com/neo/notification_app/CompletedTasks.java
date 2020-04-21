package com.neo.notification_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class CompletedTasks extends AppCompatActivity implements RecyclerViewClickInterface {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    Toolbar mToolbar;
    ArrayList<TasksList> tasklist;
    Intent mIntent;
    private static final String TAG = "CompletedTasks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_tasks);

        mToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Completed Tasks");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tasklist = new ArrayList<>();

        tasklist.add(new TasksList("Task 1", "The hottest place in Nigeria"));
        tasklist.add(new TasksList("Task 2", "The city with the finest girls"));
        tasklist.add(new TasksList("Task 3", "The city blessed with party freaks"));
        tasklist.add(new TasksList("Abuja", "The city some cool places"));
        tasklist.add(new TasksList("HolyFamily", "Not your ordinary hostel"));
        tasklist.add(new TasksList("Unizik", "The school with the highest stress ratio"));
        tasklist.add(new TasksList("SiliconValley", "The place that I want to be at"));
        tasklist.add(new TasksList("Rio de Janeiro", "The city with the beach lifestyle"));
        tasklist.add(new TasksList("New York", "The city with the most organised police force"));
        tasklist.add(new TasksList("Brooklyn", "The gang infested hood"));
        tasklist.add(new TasksList("Miami", "The city blessed with the highest number of rich kids"));
        tasklist.add(new TasksList("Las Vega", "The place with the highest number of casinos"));
        tasklist.add(new TasksList("Amawbia", "The place that is very close to my villa"));
        tasklist.add(new TasksList("BinaryHills", "The place to go to improve on my computer skills"));
        tasklist.add(new TasksList("DeveloperNation", "The place to get more info on anything computer related"));
        tasklist.add(new TasksList("Iba housing Estate", "The place where my boy is situated"));
        tasklist.add(new TasksList("Festac", "The place that I will soon invade with my shine"));
        tasklist.add(new TasksList("BananaIsland", "The place where the bigboy of lag reside\n\n\n\n\n\n\n\n\n\n\n\ntgtththththhthtjjtjjdddddddddddddddddddddd"));

        mRecyclerView = findViewById(R.id.CompletedRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mAdapter = new MyCustomAdapter(this, tasklist, this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onClick(int position) {

        Toast.makeText(this, "the pos is : " + (position), Toast.LENGTH_LONG).show();
        Log.d(TAG, "onClick: " + tasklist.get(position).getTaskName());
        mIntent = new Intent(this, CompletedTaskDetails.class);
        mIntent.putExtra("Task name", tasklist.get(position).getTaskName())
                .putExtra("Task Details", tasklist.get(position).getTaskDetails());
        startActivity(mIntent);

    }
}
