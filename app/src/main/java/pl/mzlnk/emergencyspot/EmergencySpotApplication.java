package pl.mzlnk.emergencyspot;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import pl.mzlnk.emergencyspot.service.network.NetworkService;
import pl.mzlnk.emergencyspot.service.user.UserService;

public class EmergencySpotApplication extends Application {

    public static EmergencySpotApplication app;

    public NetworkService networkService;
    public UserService userService;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        createContext();
    }

    @SuppressLint("MissingPermission")
    private void createContext() {
        this.networkService = NetworkService.init(this);
        this.userService = UserService.init();
    }

}
