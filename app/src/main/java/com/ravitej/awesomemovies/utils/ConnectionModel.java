package com.ravitej.awesomemovies.utils;

public class ConnectionModel {

    private ConnectionType type;
    private boolean isConnected;

    public ConnectionModel(ConnectionType type, boolean isConnected) {
        this.type = type;
        this.isConnected = isConnected;
    }

    public ConnectionType getType() {
        return type;
    }

    public boolean getIsConnected() {
        return isConnected;
    }
}
