package com.neo.notification_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "MainActivity";
    Intent intent;
//    Intent intent2;

    public static Dialog dialog;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    // the fields
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolBar;

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
        intent.removeExtra("Title");
        intent.removeExtra("Body");


//        dialog.dismiss();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "onPostResume: starts");


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String token = instanceIdResult.getToken();
                Log.d(TAG, "onSuccess: token is: " + token);

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("token");

                reference.child(token).setValue(token);
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolBar = findViewById(R.id.nav_icon);


        // tells system that we want to use the custom toolbar as our action bar

        setSupportActionBar(toolBar);

        // Hides or show Items of navigation drawer.
        Menu menu = navigationView.getMenu();
//        menu.findItem(R.id.nav_logout).setVisible(false);


        // brings the navigation view to seem to be on top of the layout so that it can be clicked
        navigationView.bringToFront();
//         tells app that through our actionbar toogle we want to bring and hide the navigationView
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//         makes the menu items in the navigationView clickable
        navigationView.setNavigationItemSelectedListener(this);

//         selects the navigation home icon by default
        navigationView.setCheckedItem(R.id.nav_home);


        intent = getIntent();

        // checks if the title key and body key gotten from the firemessaging class does not have null values
        if ((intent.getStringExtra("Title") != null) && (intent.getStringExtra("Body") != null)) {
            Log.d(TAG, "notifier: " + intent.getStringExtra("Title"));
            Log.d(TAG, "notifier: " + intent.getStringExtra("Body"));
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog);
            TextView title = (TextView) dialog.findViewById(R.id.notificationTitle);
            TextView content = (TextView) dialog.findViewById(R.id.notificationBody);
            Button close = (Button) dialog.findViewById(R.id.closeButton);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            title.setText(intent.getStringExtra("Title"));
            content.setText(intent.getStringExtra("Body"));
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
        intent.removeExtra("Title");
        intent.removeExtra("Body");



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                Toast.makeText(this, "you pressed the nav_home icon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Intent  loginIntent= new Intent(getApplicationContext(), LoginActivity.class);
                loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(loginIntent);
                finish();
                break;
            case R.id.nav_pending_tasks:
                Intent pendingTasksIntent = new Intent(MainActivity.this, PendingTasks.class);
                startActivity(pendingTasksIntent);
                break;
            case R.id.nav_completed_tasks:
                Intent completedTasksIntent = new Intent(MainActivity.this, CompletedTasks.class);
                startActivity(completedTasksIntent);
                break;
            case R.id.nav_task_details:
                Intent taskDetailsIntent = new Intent(MainActivity.this, TaskDetails.class);
                startActivity(taskDetailsIntent);
                break;
        }
        //closes the drawer when any item is clicked
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

}
