package com.neo.notification_app;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static final String TAG = "Token";
    private static final String CHANNEL_ID = "My_channel";
    public static final int NOTIFICATION_ID = 1;

    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "from: " + remoteMessage.getFrom());

        if(remoteMessage.getData().size() > 0){
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        if(remoteMessage.getNotification() != null){
            Log.d(TAG, "Message Notification Title: " + remoteMessage.getNotification().getTitle());

            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

//        createNotificationChannel();

        sendNotification(remoteMessage);


    }


    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, importance);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);

        }
    }



    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
//        Log.d(TAG, "Refreshed token: " + token);

        Log.i(TAG, "onNewToken: = " + token);

        FirebaseInstanceId.getInstance().getInstanceId().
                addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(!task.isSuccessful()){
                            Log.w(TAG, "getInstanceid Failed", task.getException());
                            return;
                        }

                        // get instance ID token
                        String token = task.getResult().getToken();


                        //log and send toast messages
                        Log.d("onComplete: ", token);
                    }
                });


        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token){

    }

    private void sendNotification(RemoteMessage messageBody){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

//
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setChannelId(CHANNEL_ID);
        notificationBuilder.setSmallIcon(R.drawable.ic_stat_name);
        notificationBuilder.setContentTitle(messageBody.getNotification().getTitle());
        notificationBuilder.setContentText(messageBody.getNotification().getBody());
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());

//        NotificationCompat.Builder notificationBuilder= new NotificationCompat.Builder(this, CHANNEL_ID);
//        notificationBuilder.setSmallIcon(R.drawable.ic_stat_name);
//        notificationBuilder.setContentTitle(messageBody.getNotification().getTitle());
//        notificationBuilder.setContentText(messageBody.getNotification().getBody());
//        notificationBuilder.setAutoCancel(true);
//        notificationBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//        NotificationManagerCompat notificationManager= NotificationManagerCompat.from(this);
//
//        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }



}
