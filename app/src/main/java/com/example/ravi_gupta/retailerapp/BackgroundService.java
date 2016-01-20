package com.example.ravi_gupta.retailerapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Ravi-Gupta on 1/20/2016.
 */
public class BackgroundService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    OrderCollection orderCollection;
    /**
     *  This method is called to start the service from main Activity
     * @param intent {Intent}
     * @param flags {int}
     * @param startId {int}
     * @return int
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        orderCollection = new OrderCollection();
        return START_STICKY;
    }

    /**
     * This method is used to kill service or destroy service
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
