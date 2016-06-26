package com.androidnanodegree.cr.advancedwearablenotifications;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.androidnanodegree.cr.advancedwearablenotifications.util.BigStyleNotification;
import com.androidnanodegree.cr.advancedwearablenotifications.util.ExtraActionNotification;
import com.androidnanodegree.cr.advancedwearablenotifications.util.PagerNotification;
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
    public void createStandardNotification(View view) {
        StandardNotification standardNotification = new StandardNotification(this);
        standardNotification.show();
    }

    /**
     * Launch the Big Style Notification
     */
    public void createBigTextStyleNotificaton(View view) {
        BigStyleNotification bigStyleNotification = new BigStyleNotification(this);
        bigStyleNotification.show();
    }

    /**
     * Launch the Extra Action Notification
     */
    public void createExtraActionNotification(View view) {
        ExtraActionNotification extraActionNotification = new ExtraActionNotification(this);
        extraActionNotification.show();
    }

    /**
     * Launch the Simple Extended Wearable Notification
     */
    public void createSimpleExtendedWearableNotification(View view) {
        SimpleExtendedWearableNotification simpleExtendedWearableNotification =
                new SimpleExtendedWearableNotification(this);
        simpleExtendedWearableNotification.show();
    }

    /**
     * Launch the Pager Notification
     */
    public void createPagerNotification(View view) {
        PagerNotification pagerNotification =
                new PagerNotification(this);
        pagerNotification.show();
    }
}
