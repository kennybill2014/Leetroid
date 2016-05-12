package joey.com.leetroid;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
        mRootView.setBackgroundColor(Color.parseColor("#FF038CC3"));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.topMargin = (int) ((float)(mContext.getApplicationContext().getResources().getDisplayMetrics().heightPixels) * 0.382);
        TextView textView = new TextView(mContext);
        textView.setText("Leetroid");
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 42);
        textView.setTypeface(null, Typeface.BOLD);
        mRootView.addView(textView, layoutParams);

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
            }, 3000);
        }
    }

}
