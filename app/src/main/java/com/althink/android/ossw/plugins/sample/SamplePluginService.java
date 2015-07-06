package com.althink.android.ossw.plugins.sample;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by krzysiek on 08/06/15.
 */
public class SamplePluginService extends Service {

    private final static String TAG = SamplePluginService.class.getSimpleName();

    private final Messenger mMessenger = new Messenger(new OperationHandler());

    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        //Log.d(TAG, "onBind");

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String value = sharedPref.getString(SamplePluginSettingsActivity.STRING_PARAM_VALUE, "test");
        updateParamValue(SamplePluginProperty.STRING_PARAM, value);

        timer = new Timer();
        timer.schedule(new ChangeParamValueTimer(), 0, 500);

        return mMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        //Log.d(TAG, "onUnbind");
        timer.cancel();
        timer = null;

        return super.onUnbind(intent);
    }

    //tells activity to run on ui thread
    class ChangeParamValueTimer extends TimerTask {

        @Override
        public void run() {

            int intVal = getParamIntValue(SamplePluginProperty.INT_PARAM);
            intVal += 1;
            if (intVal > 999) {
                intVal = 0;
            }
            updateParamValue(SamplePluginProperty.INT_PARAM, intVal);

            float floatVal = getParamFloatValue(SamplePluginProperty.FLOAT_PARAM);
            floatVal += 0.025;
            if (floatVal > 99.99) {
                floatVal = 0;
            }
            updateParamValue(SamplePluginProperty.FLOAT_PARAM, floatVal);
        }
    }

    private class OperationHandler extends Handler {

        public OperationHandler() {
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (SamplePluginFunction.resolveById(msg.what)) {
                case INCREASE_MOD_PARAM: {
                    int value = getParamIntValue(SamplePluginProperty.MOD_PARAM);

                    value++;

                    updateParamValue(SamplePluginProperty.MOD_PARAM, value);
                    break;
                }
                case DECREASE_MOD_PARAM: {
                    int value = getParamIntValue(SamplePluginProperty.MOD_PARAM);

                    if (value > 0) {
                        value--;
                    }

                    updateParamValue(SamplePluginProperty.MOD_PARAM, value);
                    break;
                }
                default:
                    // do nothing
                    return;
            }
        }
    }

    private float getParamFloatValue(SamplePluginProperty property) {
        Cursor query = getContentResolver().query(SamplePluginContentProvider.PROPERTY_VALUES_URI, new String[]{property.getName()}, null, null, null);
        query.moveToNext();
        float value = query.getFloat(0);
        query.close();
        return value;
    }

    private int getParamIntValue(SamplePluginProperty property) {
        Cursor query = getContentResolver().query(SamplePluginContentProvider.PROPERTY_VALUES_URI, new String[]{property.getName()}, null, null, null);
        query.moveToNext();
        int value = query.getInt(0);
        query.close();
        return value;
    }

    private String getParamStringValue(SamplePluginProperty property) {
        Cursor query = getContentResolver().query(SamplePluginContentProvider.PROPERTY_VALUES_URI, new String[]{property.getName()}, null, null, null);
        query.moveToNext();
        String value = query.getString(0);
        query.close();
        return value;
    }

    private void updateParamValue(SamplePluginProperty property, float value) {
        ContentValues values = new ContentValues();
        values.put(property.getName(), value);
        getContentResolver().update(SamplePluginContentProvider.PROPERTY_VALUES_URI, values, null, null);
    }

    private void updateParamValue(SamplePluginProperty property, int value) {
        ContentValues values = new ContentValues();
        values.put(property.getName(), value);
        getContentResolver().update(SamplePluginContentProvider.PROPERTY_VALUES_URI, values, null, null);
    }

    private void updateParamValue(SamplePluginProperty property, String value) {
        ContentValues values = new ContentValues();
        values.put(property.getName(), value);
        getContentResolver().update(SamplePluginContentProvider.PROPERTY_VALUES_URI, values, null, null);
    }
}