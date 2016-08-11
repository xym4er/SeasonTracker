package com.ychornyi.seasontracker.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MainReceiver extends BroadcastReceiver {
    public MainReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent intent0 = new Intent(context, MainService.class);
            context.startService(intent0);
        }
    }
}
