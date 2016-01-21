package com.example.ravi_gupta.retailerapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

/**
 * Created by Ravi-Gupta on 1/20/2016.
 */
public class BackgroundService extends Service {

    MainActivity mainActivity;

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
        EventBus.getDefault().registerSticky(this);
        EventBus.getDefault().register(this);
        return START_STICKY;
    }

    @Subscriber ( tag = Constants.SEND_MAIN_ACTIVITY_INSTANCE)
    private void takeMainActivityInstance(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        orderCollection = new OrderCollection(mainActivity);
    }

    /**
     * This method is used to kill service or destroy service
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
