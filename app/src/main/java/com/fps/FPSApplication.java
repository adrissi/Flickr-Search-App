package com.fps;

import android.app.Application;
import roboguice.RoboGuice;

/**
 * Created by anissou on 16-01-09.
 */
public class FPSApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();    //To change body of overridden methods use File | Settings | File Templates.
        RoboGuice.setBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE, RoboGuice.newDefaultRoboModule(this), new FPSModule());

    }
}
