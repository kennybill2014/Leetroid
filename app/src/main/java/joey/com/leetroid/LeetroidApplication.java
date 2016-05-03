package joey.com.leetroid;

import android.app.Application;

public class LeetroidApplication extends Application {

    private SplashManager mSplashManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mSplashManager = new SplashManager(this);
        mSplashManager.begin();
    }

}
