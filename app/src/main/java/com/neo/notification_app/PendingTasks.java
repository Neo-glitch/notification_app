package com.neo.notification_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class PendingTasks extends AppCompatActivity implements RecyclerViewClickInterface {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    ArrayList<TasksList> tasklist;
    private static final String TAG = "PendingTasks";
    Intent mIntent;
    Toolbar toolBar;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_tasks);


        toolBar = findViewById(R.id.toolbar);

        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Pending Tasks");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tasklist = new ArrayList<>();
        tasklist.add(new TasksList("Lagos", "The hottest place in Nigeria"));
        tasklist.add(new TasksList("Enugu", "The city with the finest girls"));
        tasklist.add(new TasksList("Awka", "The city blessed with party freaks"));
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
        tasklist.add(new TasksList("BananaIsland", "The place where the bigboy of lag reside"));


        mRecyclerView = findViewById(R.id.pendingRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mAdapter = new MyCustomAdapter(this, tasklist, this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(this, "the pos is : " + (position), Toast.LENGTH_LONG).show();
        Log.d(TAG, "onClick: " + tasklist.get(position).getTaskName());
        mIntent = new Intent(this, PendingTaskDetails.class);
        mIntent.putExtra("Task name", tasklist.get(position).getTaskName())
                .putExtra("Task Details", tasklist.get(position).getTaskDetails());
        startActivity(mIntent);


    }

}
