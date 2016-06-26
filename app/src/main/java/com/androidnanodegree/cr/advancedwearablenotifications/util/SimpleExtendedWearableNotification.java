package com.androidnanodegree.cr.advancedwearablenotifications.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.androidnanodegree.cr.advancedwearablenotifications.R;

/**
 * Most of the code is from the Basic Notification Sample provided by the samples of SDK 23
 * Original code can be found at: https://github.com/googlesamples/android-BasicNotifications
 */

public class SimpleExtendedWearableNotification {
    /**
     * A numeric value that identifies the notification that we'll be sending.
     * This value needs to be unique within this app, but it doesn't need to be
     * unique system-wide.
     */
    public static final int NOTIFICATION_ID = 2;

    /**
     * A member variable to store the context given by the constructor of this class
     */
    private Context mContext;

    public SimpleExtendedWearableNotification(Context context) {
        mContext = context;
    }

    public void show() {
        /**
         * Create an intent that will be fired when the user clicks the notification.
         * The intent needs to be packaged into a {@link android.app.PendingIntent} so that the
         * notification service can fire it on our behalf.
         */
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);

        /**
         * Build an intent for an action to view a map that will only be shown on the phone
         */
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse(
                "geo:0,0?q=" + mContext.getString(R.string.notification_location)
        );
        mapIntent.setData(geoUri);
        PendingIntent mapPendingIntent =
                PendingIntent.getActivity(mContext, 0, mapIntent, 0);

        /**
         * Use NotificationCompat.Builder to set up our notification.
         */
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);

        /**
         * Set the icon that will appear in the notification bar. This icon also appears
         * in the lower right hand corner of the notification itself.
         *
         * Important note: although you can use any drawable as the small icon, Android
         * design guidelines state that the icon should be simple and monochrome. Full-color
         * bitmaps or busy images don't render well on smaller screens and can end up
         * confusing the user.
         */
        builder.setSmallIcon(R.drawable.ic_stat_notification);

        // Set the intent that will fire when the user taps the notification.
        builder.setContentIntent(pendingIntent);

        // Set the notification to auto-cancel. This means that the notification will disappear
        // after the user taps it, rather than remaining until it's explicitly dismissed.
        builder.setAutoCancel(true);


        /**
         * Build the notification's appearance.
         * Set the large icon, which appears on the left of the notification. In this
         * sample we'll set the large icon to be the same as our app icon. The app icon is a
         * reasonable default if you don't have anything more compelling to use as an icon.
         */
        builder.setLargeIcon(BitmapFactory.decodeResource(
                mContext.getResources(), R.mipmap.ic_launcher)
        );

        /**
         * Set the text of the notification. This sample sets the three most commonly used
         * text areas:
         * 1. The content title, which appears in large type at the top of the notification
         * 2. The content text, which appears in smaller text below the title
         * 3. The subtext, which appears under the text on newer devices. Devices running
         *    versions of Android prior to 4.2 will ignore this field, so don't use it for
         *    anything vital!
         */
        builder.setContentTitle(mContext.getString(
                R.string.simple_extended_wearable_notification_title)
        );
        builder.setContentText(mContext.getString(
                R.string.simple_extended_wearable_notification_text)
        );
        builder.setSubText(mContext.getString(
                R.string.simple_extended_wearable_notification_sub_text)
        );

        /**
         * Add the action of the notification.
         * This action will be shown in the notification and can be directly be executed by the user
         */
        builder.addAction(
                R.drawable.ic_map_notification,
                mContext.getString(R.string.extra_action_notification_action_text),
                mapPendingIntent
        );

        /**
         *  Build a navigation Intent so that we can add to the action
         */

        Uri navigationUri = Uri.parse(
                "google.navigation:q=" +
                        mContext.getString(R.string.notification_location) +
                        "&mode=d"
        );
        Intent navigationIntent = new Intent(Intent.ACTION_VIEW);
        navigationIntent.setPackage("com.google.android.apps.maps");
        navigationIntent.setData(navigationUri);
        PendingIntent navigationPendingIntent =
                PendingIntent.getActivity(mContext, 0, navigationIntent, 0);

        /**
         * Build the action so we can add it to the NotificationCompat.builder
         */
        NotificationCompat.Action navigationAction =
                new NotificationCompat.Action.Builder(R.drawable.ic_directions,
                        mContext.getString(
                                R.string.simple_extended_wearable_notification_text
                        ), navigationPendingIntent)
                        .build();

        /**
         * Add the action to the WearableExtender so the action will only be shown
         * on the wearable for easy navigation
         */
        builder.extend(new NotificationCompat.WearableExtender().addAction(navigationAction));

        /**
         * Send the notification. This will immediately display the notification icon in the
         * notification bar.
         * Use the NotificationManagerCompat for support on older versions
         */
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(mContext.getApplicationContext());
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}