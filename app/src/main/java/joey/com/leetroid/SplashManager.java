package joey.com.leetroid;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.HandlerThread;
import android.sax.RootElement;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class SplashManager {

    private Context mContext;

    private HandlerThread mSplashThread;

    private Handler mSplashHandler;

    private RelativeLayout mRootView;

    public SplashManager(Context context) {
        mContext = context;
        mSplashThread = new HandlerThread("splash");
        mSplashThread.start();
        mSplashHandler = new Handler(mSplashThread.getLooper());
        mRootView = new RelativeLayout(context);
    }

    public void begin() {
        mSplashHandler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                addSplashViewPage();
            }
        });
    }

    private void addSplashViewPage() {
        mRootView.setBackgroundColor(Color.parseColor("#F0FFF0"));

        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams windowManagerParams = new WindowManager.LayoutParams();
        windowManagerParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        windowManagerParams.format = PixelFormat.RGBA_8888;
        windowManagerParams.gravity = Gravity.CENTER;
        windowManagerParams.x = 0;
        windowManagerParams.y = 0;
        windowManagerParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowManagerParams.height = WindowManager.LayoutParams.MATCH_PARENT;

        windowManager.addView(mRootView, windowManagerParams);
        removeSplashViewPage();
    }

    private void removeSplashViewPage() {
        if (mSplashHandler != null) {
            mSplashHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    WindowManager windowManager = (WindowManager) mContext
                            .getSystemService(Context.WINDOW_SERVICE);
                    windowManager.removeViewImmediate(mRootView);

                    mSplashThread.quit();
                    mSplashThread = null;
                    mRootView = null;
                }
            }, 2000);
        }
    }

}
