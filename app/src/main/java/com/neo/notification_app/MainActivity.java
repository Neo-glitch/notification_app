package com.neo.notification_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity{

    public static final String TAG = "MainActivity";
    Intent intent;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        intent.removeExtra("Title");
        intent.removeExtra("Body");

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
            }
        });


        intent = getIntent();

        // checks if the title key and body key gotten from the firemessaging class does not have null values
        if((intent.getStringExtra("Title")!= null) && (intent.getStringExtra("Body")!= null)){
            Log.d(TAG, "notifier: " + intent.getStringExtra("Title"));
            Log.d(TAG, "notifier: " + intent.getStringExtra("Body"));
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog);
            TextView title = (TextView)dialog.findViewById(R.id.username);
            TextView content= (TextView)dialog.findViewById(R.id.phoneNumber);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            title.setText(intent.getStringExtra("Title"));
            content.setText(intent.getStringExtra("Body"));
            dialog.show();
        }
        intent.removeExtra("Title");
        intent.removeExtra("Body");

    }

}
