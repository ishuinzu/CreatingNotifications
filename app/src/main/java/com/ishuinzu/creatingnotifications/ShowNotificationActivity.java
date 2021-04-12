package com.ishuinzu.creatingnotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShowNotificationActivity extends AppCompatActivity {
    private TextView txtNotificationTitle, txtNotificationMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notification);

        txtNotificationTitle = findViewById(R.id.txtNotificationTitle);
        txtNotificationMessage = findViewById(R.id.txtNotificationMessage);

        txtNotificationTitle.setText(getIntent().getExtras().getString("TITLE"));
        txtNotificationMessage.setText(getIntent().getExtras().getString("MESSAGE"));
    }
}