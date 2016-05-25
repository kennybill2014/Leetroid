package joey.com.leetroid;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import joey.com.leetroid.ui.MainListContainerFragment;
import joey.com.leetroid.ui.WebContainerFragment;
import joey.com.leetroid.utils.FileHelper;
import joey.com.leetroid.utils.ProblemListHelper;

public class MainActivity extends FragmentActivity {

    public static final String TAB_MAIN_TAG = "main";
    public static final String TAB_WEB_TAG = "web";
    private TabHost.TabSpec mMainTab;
    private TabHost.TabSpec mWebTab;
    private FragmentTabHost mFragmentTabHost;
    private ProblemListHelper mProblemListHelper;
    private SplashManager mSplashManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashManager = new SplashManager(this);
        mSplashManager.begin();
        setContentView(R.layout.activity_main);
        initProblems();
        initView();
    }

    private void initProblems() {
        mProblemListHelper = new ProblemListHelper(this.getApplicationContext());
        mProblemListHelper.startBuild();
    }

    private void storeProblemStatus() {
        mProblemListHelper.storeStatus();
    }

    /**
     * init views in MainActivity
     */
    private void initView() {
        mFragmentTabHost = (FragmentTabHost) findViewById(R.id.tab_host);
        mFragmentTabHost.setup(this, getSupportFragmentManager(), R.id.frame_container);

        mMainTab = getIndicator(MainActivity.this, mFragmentTabHost.newTabSpec(TAB_MAIN_TAG), "#363636",
                "Solution", R.mipmap.problem);
        mWebTab = getIndicator(MainActivity.this, mFragmentTabHost.newTabSpec(TAB_WEB_TAG), "#363636",
                "Discussion", R.mipmap.discussion);

        mFragmentTabHost.addTab(mMainTab, MainListContainerFragment.class, null);
        mFragmentTabHost.addTab(mWebTab, WebContainerFragment.class, null);
        mFragmentTabHost.getTabWidget().setDividerDrawable(null); // Remove the divider
        mFragmentTabHost.getCurrentTabView().setBackgroundColor(Color.parseColor("#1E1E1E"));
        mFragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (tabId) {
                    case "main":
                        mFragmentTabHost.getTabWidget().getChildTabViewAt(0)
                                .setBackgroundColor(Color.parseColor("#1E1E1E"));
                        mFragmentTabHost.getTabWidget().getChildTabViewAt(1)
                                .setBackgroundColor(Color.parseColor("#363636"));
                        break;
                    case "web":
                        mFragmentTabHost.getTabWidget().getChildTabViewAt(1)
                                .setBackgroundColor(Color.parseColor("#1E1E1E"));
                        mFragmentTabHost.getTabWidget().getChildTabViewAt(0)
                                .setBackgroundColor(Color.parseColor("#363636"));
                        break;
                }
            }
        });
    }

    private TabHost.TabSpec getIndicator(Context context, TabHost.TabSpec spec,
                                         String color, String str, int genResIcon) {
        View v = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        TextView tabText = (TextView) v.findViewById(R.id.tab_text);
        ImageView tabImage = (ImageView) v.findViewById(R.id.tab_image);

        v.setBackgroundColor(Color.parseColor(color));
        tabText.setText(str);
        tabText.setTextColor(Color.WHITE);
        tabImage.setBackgroundResource(genResIcon);

        return spec.setIndicator(v);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        storeProblemStatus();
        ProblemsContainer.releaseInstance();
    }

}
