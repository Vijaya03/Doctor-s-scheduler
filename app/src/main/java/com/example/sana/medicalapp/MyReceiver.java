package com.example.sana.medicalapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by SANA on 12-04-2018.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String sms= "Your turn is about to come. Please be ready. Thank You";
        String phoneNo;
        Bundle extrasBundle = intent.getExtras();
        phoneNo=extrasBundle.getString("phoneNo");
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, sms, null, null);
            Toast.makeText(context, "SMS Sent to " + phoneNo,Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(context,"SMS faild, please try again later!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
