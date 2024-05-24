package com.example.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class mySMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        processSms(context, intent);
    }

    private void processSms(Context context, Intent intent) {
        Bundle myBundle = intent.getExtras();
        String message = "";
        String body = "";
        String address  = "";
        if (myBundle != null){
            Object[] mySMS = (Object[]) myBundle.get("pdus");
            for (int i = 0; i < mySMS.length; i++)
            {
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) mySMS[i]);
                body += sms.getMessageBody();
                address = sms.getOriginatingAddress();
            }

            message = "Có 1 tin nhắn từ " + address + "\n" + " " + body + " vừa gửi đến";
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}