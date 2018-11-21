package com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.home.view.HomeScreen;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.signup.view.SignupActivity;


public class Utils {
    public static void startNewActivity(Activity activity, Class<HomeScreen> activityClass) {
        Intent intent = new Intent(activity, activityClass);
        activity.startActivity(intent);
    }

    public static void startNewActivitys(Activity activity, Class<SignupActivity> activityClass) {
        Intent intent = new Intent(activity, activityClass);
        activity.startActivity(intent);
    }

    public static void ShowToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
