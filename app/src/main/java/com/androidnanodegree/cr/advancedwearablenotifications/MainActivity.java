package com.androidnanodegree.cr.advancedwearablenotifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.androidnanodegree.cr.advancedwearablenotifications.util.StandardNotification;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void CreateStandardNotification(View view){
        StandardNotification standardNotification = new StandardNotification(this);
        standardNotification.show();
    }
}
