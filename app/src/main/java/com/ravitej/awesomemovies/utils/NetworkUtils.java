package com.ravitej.awesomemovies.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

    public static ConnectionModel getConnectionStats(Application application) {
        ConnectivityManager cm = (ConnectivityManager) application.getApplicationContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null &&
            activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            switch (activeNetwork.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    return new ConnectionModel(ConnectionType.WIFI, true);
                case ConnectivityManager.TYPE_MOBILE:
                    return new ConnectionModel(ConnectionType.MOBILE_TYPE, true);
            }
        }
        return new ConnectionModel(ConnectionType.NO_CONNECTION, false);
    }
}
