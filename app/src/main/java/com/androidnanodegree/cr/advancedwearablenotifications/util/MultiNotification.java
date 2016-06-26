package com.androidnanodegree.cr.advancedwearablenotifications.util;

import android.app.Notification;
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

public class MultiNotification {
    /**
     * A numeric value that identifies the notification that we'll be sending.
     * This value needs to be unique within this app, but it doesn't need to be
     * unique system-wide.
     */
    public static final int NOTIFICATION_ID = 1;

    /**
     * For notification to be grouped we need to give them a new notification ID. Therefore we need
     * to create a new notification ID
     */
    public static final int SECOND_NOTIFICATION_ID = 2;

    /**
     * For the summary notifications on the phone an extra ID is needed
     */
    public static final int THIRD_NOTIFICATION_ID = 3;

    /**
     * A group key must be created to group our notifications together
     */
    final static String GROUP_KEY_EMAILS = "group_key_emails";

    /**
     * A member variable to store the context given by the constructor of this class
     */
    private final Context mContext;

    public MultiNotification(Context context) {
        mContext = context;
    }

    public void show() {
        /**
         * Create an intent that will be fired when the user clicks the notification.
         * The intent needs to be packaged into a {@link PendingIntent} so that the
         * notification service can fire it on our behalf.
         */
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);

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
        builder.setSmallIcon(R.drawable.ic_mail);

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
        builder.setContentTitle(
                mContext.getString(R.string.multi_notification_new_mail) + " " +
                        mContext.getString(R.string.multi_notification_mail_sender_1)
        );
        builder.setContentText(mContext.getString(R.string.multi_notification_mail_subject_1));
        builder.setSubText(mContext.getString(R.string.multi_notification_sub_text));

        /**
         * Here we set the group our notification belong to
         */
        builder.setGroup(GROUP_KEY_EMAILS);

        /**
         * Send the notification. This will immediately display the notification icon in the
         * notification bar.
         * Use the NotificationManagerCompat for support on older versions
         */
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(mContext.getApplicationContext());
        notificationManager.notify(NOTIFICATION_ID, builder.build());

        /**
         * Now we create our second notification to be grouped with the first notification
         */
        Notification notification2 = new NotificationCompat.Builder(mContext)
                .setContentTitle(
                        mContext.getString(R.string.multi_notification_new_mail) + " " +
                                mContext.getString(R.string.multi_notification_mail_sender_2)
                )
                .setContentText(mContext.getString(R.string.multi_notification_mail_subject_2))
                .setSmallIcon(R.drawable.ic_mail)
                .setGroup(GROUP_KEY_EMAILS)
                .build();

        /**
         * Notify the NotificationManagerCompat that we have a new notification
         */
        notificationManager.notify(SECOND_NOTIFICATION_ID, notification2);

        /**
         * It's important that we still provide a summary notification that appears on handheld
         * devices. So in addition to adding each unique notification to the same stack group, also add
         * a summary notification and call setGroupSummary() on the summary notification.
         * <p/>
         * This notification does not appear in your stack of notifications on the wearable, but it
         * appears as the only notification on the handheld device.
         * <p/>
         * We start by creating an InboxStyle to show in our summery notification
         */
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
                .addLine(
                        mContext.getString(R.string.multi_notification_mail_sender_1) +
                                mContext.getString(R.string.multi_notification_mail_subject_1)
                )
                .addLine(
                        mContext.getString(R.string.multi_notification_mail_sender_2) + " " +
                                mContext.getString(R.string.multi_notification_mail_subject_2)
                )
                .setBigContentTitle(mContext.getString(R.string.multi_notification_title))
                .setSummaryText(mContext.getString(R.string.multi_notification_mail_summary_text));

        /**
         * Here we create the summary notification
         */
        Notification summaryNotification = new NotificationCompat.Builder(mContext)
                .setContentTitle(mContext.getString(R.string.multi_notification_title))
                .setSmallIcon(R.drawable.ic_mail)
                .setLargeIcon(BitmapFactory.decodeResource(
                        mContext.getResources(), R.mipmap.ic_launcher)
                )
                .setStyle(inboxStyle)
                .setGroup(GROUP_KEY_EMAILS)
                .setGroupSummary(true)
                .build();

        /**
         * Finally we need to notify the NotificationManagerCompat that we have a summary notification
         */
        notificationManager.notify(THIRD_NOTIFICATION_ID, summaryNotification);
    }
}
