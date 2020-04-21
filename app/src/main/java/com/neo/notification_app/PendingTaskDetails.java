package com.neo.notification_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class PendingTaskDetails extends AppCompatActivity {

    Bundle arguments;
    private static final String TAG = "PendingTaskDetails";
    TextView taskTitle;
    TextView taskInfo;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        taskTitle = findViewById(R.id.taskTitle);
        taskInfo = findViewById(R.id.taskDetails);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Task Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arguments = getIntent().getExtras();

        if (arguments != null) {
            String taskName = arguments.getString("Task name");
            String taskDetails = arguments.getString("Task Details");
            if (taskName != null) {
                Log.d(TAG, "onCreate: the task name is " + taskName);
                taskTitle.setText(taskName);
            }
            if (taskDetails != null) {
                Log.d(TAG, "onCreate: the task Details is " + taskDetails);
                taskInfo.setText(taskDetails);
            }
        }

    }
}
