package pl.mzlnk.emergencyspot;

import android.app.Application;

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
    }

}
