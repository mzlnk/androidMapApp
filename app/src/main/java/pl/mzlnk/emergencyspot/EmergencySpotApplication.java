package pl.mzlnk.emergencyspot;

import android.app.Application;

public class EmergencySpotApplication extends Application {

    public static EmergencySpotApplication app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
    }

}
