package android.com.parsetest;

import android.app.Application;

import io.cloudboost.CloudApp;

/**
 * Created by Duke on 5/12/2016.
 */
public class CustomApplicationClass extends Application {

    String CLIENT_KEY = "94a9bcdb-103d-4ab6-9ca5-7cfc6534eef8";
    String APP_ID = "opepvvbhhwho";
    String MASTER_KEY = "76f768e7-e58e-4acf-9023-871ef3e95c76";

    @Override
    public void onCreate() {
        super.onCreate();
        CloudApp.init(APP_ID, CLIENT_KEY);

    }
}
