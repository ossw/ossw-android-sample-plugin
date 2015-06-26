package com.althink.android.ossw.plugins.sample;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;

/**
 * Created by krzysiek on 08/06/15.
 */
public class SamplePluginService extends Service {

    private final static String TAG = SamplePluginService.class.getSimpleName();

    private final Messenger mMessenger = new Messenger(new OperationHandler());

    @Override
    public IBinder onBind(Intent intent) {

        Log.d(TAG, "onBind");
        return mMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    private class OperationHandler extends Handler {

        public OperationHandler() {
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int action;
            int code;

            switch (SamplePluginFunction.resolveById(msg.what)) {
                case INCREASE_MOD_PARAM:
                    break;
                case DECREASE_MOD_PARAM:
                    break;
                default:
                    // do nothing
                    return;
            }
        }
    }
}