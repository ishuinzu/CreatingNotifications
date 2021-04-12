package com.ishuinzu.creatingnotifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {
    private Button btnShowNotification;
    private static final String CHANNEL_ID = "personal_notifications";
    private static final int NOTIFICATION_ID = 100;
    private String title = "Title";
    private String message = "A dummy notification message.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowNotification = findViewById(R.id.btnShowNotification);

        btnShowNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show Notification
                showNotification();
            }
        });
    }

    private void showNotification() {
        //Create Notification Channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Personal Notifications";
            String description = "This includes all the personal notification for the application.";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_ID,
                    name,
                    importance
            );
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        //Creating Pending Intent
        Intent intent = new Intent(MainActivity.this, ShowNotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("TITLE", title);
        intent.putExtra("MESSAGE", message);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                MainActivity.this,
                0,
                intent,
                PendingIntent.FLAG_ONE_SHOT
        );

        //Creating Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_android);
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }
}