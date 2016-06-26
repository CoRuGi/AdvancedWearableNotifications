package com.androidnanodegree.cr.advancedwearablenotifications;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.androidnanodegree.cr.advancedwearablenotifications.util.BigStyleNotification;
import com.androidnanodegree.cr.advancedwearablenotifications.util.ExtraActionNotification;
import com.androidnanodegree.cr.advancedwearablenotifications.util.SimpleExtendedWearableNotification;
import com.androidnanodegree.cr.advancedwearablenotifications.util.StandardNotification;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Launch the Standard Notification
     */
    public void CreateStandardNotification(View view) {
        StandardNotification standardNotification = new StandardNotification(this);
        standardNotification.show();
    }

    /**
     * Launch the Extra Action Notification
     */
    public void CreateBigTextStyleNotificaton(View view) {
        BigStyleNotification bigStyleNotification = new BigStyleNotification(this);
        bigStyleNotification.show();
    }

    /**
     * Launch the Extra Action Notification
     */
    public void CreateExtraActionNotification(View view) {
        ExtraActionNotification extraActionNotification = new ExtraActionNotification(this);
        extraActionNotification.show();
    }

    /**
     * Launch the Simple Extended Wearable Notification
     */
    public void CreateSimpleExtendedWearableNotification(View view) {
        SimpleExtendedWearableNotification simpleExtendedWearableNotification =
                new SimpleExtendedWearableNotification(this);
        simpleExtendedWearableNotification.show();
    }
}
