package com.neo.notification_app;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import java.util.Map;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static final String TAG = "Token";

    public static String NOTIFICATION_ID = "NOTIFICATION_ID";
//    private static final String NOTIFICATION_CHANNEL_ID = "Channel";


    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "from: " + remoteMessage.getFrom());

        if(remoteMessage.getData().size() > 0){

        }
        Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        if(remoteMessage.getNotification() != null){
            Log.d(TAG, "Message Notification Title: " + remoteMessage.getData().get("title"));

            Log.d(TAG, "Message Notification Body: " + remoteMessage.getData().get("content"));
        }

        createNotificationChannel();

        if(remoteMessage != null){
            sendNotification(remoteMessage);
        }



    }



    // method only creates a notififcation channel for android 8 and 9 since it is needed.
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//
//            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_ID, importance);
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//
//            notificationManager.createNotificationChannel(channel);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                NotificationChannel channel = new NotificationChannel(NOTIFICATION_ID, "Notification", notificationManager.IMPORTANCE_HIGH);
                channel.setDescription("The App");
                channel.enableLights(true);
                channel.setLightColor(Color.BLUE);
                channel.setVibrationPattern(new long[]{500,500,500,500});


                notificationManager.createNotificationChannel(channel);
            }

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


    // method must be overridden in order to recieve notification when the app is killed
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.d(TAG, "onTaskRemoved: starts");
    }

    private void sendNotification(RemoteMessage messageBody){

        Map<String, String> data = messageBody.getData();
        String title = data.get("title");
        String content = data.get("content");



        Intent intent = new Intent(this, MainActivity.class);
        // pases the content and title of the content and title tag of the json data recieved to a body and title.
        intent.putExtra("Body", messageBody.getData().get("content"));
        intent.putExtra("Title", messageBody.getData().get("title"));
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK|intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(title)
                .setContentText(content)
                .setContentInfo("info")
                .setChannelId(NOTIFICATION_ID)
                .setContentIntent(pendingIntent);


        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(), notificationBuilder.build());






//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("Body", messageBody.getNotification().getBody());
//        intent.putExtra("Title", messageBody.getNotification().getTitle());
//        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
//
//
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
//        notificationBuilder.setChannelId(CHANNEL_ID);
//        notificationBuilder.setSmallIcon(R.drawable.ic_stat_name);
//        notificationBuilder.setContentTitle(messageBody.getNotification().getTitle());
//        notificationBuilder.setContentText(messageBody.getNotification().getBody());
//        notificationBuilder.setAutoCancel(true);
//        notificationBuilder.setContentIntent(pendingIntent);

//        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0, notificationBuilder.build());

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
