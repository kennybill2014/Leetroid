package joey.com.leetroid;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import joey.com.leetroid.ui.MainListContainerFragment;
import joey.com.leetroid.ui.WebContainerFragment;

public class MainActivity extends FragmentActivity {

    public static final String TAB_ONE_TAG = "tab1";
    public static final String TAB_TWO_TAG = "tab2";
    private FragmentTabHost mFragmentTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initProblems();
        initView();
    }

    private void initProblems() {
        for (int i=0; i< 10; i++) {
            Problem problem = new Problem(false, "Two Sum", "Easy");
            ProblemsContainer.getInstance().addProblem(problem);
        }
    }

    private void initView() {
        System.out.println("Init Views in MainActivity");
        //mFragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mFragmentTabHost = (FragmentTabHost) findViewById(R.id.tab_host);
        mFragmentTabHost.setup(this, getSupportFragmentManager(), R.id.frame_container);

        mFragmentTabHost.addTab(getIndicator(MainActivity.this, mFragmentTabHost.newTabSpec(TAB_ONE_TAG), "#10000000",
                "MainList", R.mipmap.ic_launcher), MainListContainerFragment.class, null);
        mFragmentTabHost.addTab(getIndicator(MainActivity.this, mFragmentTabHost.newTabSpec(TAB_TWO_TAG), "#10000000",
                "Web", R.mipmap.ic_launcher), WebContainerFragment.class, null);
    }

    private TabHost.TabSpec getIndicator(Context context, TabHost.TabSpec spec,
                                         String color, String str, int genResIcon) {
        View v = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        TextView tabText = (TextView) v.findViewById(R.id.tab_text);
        ImageView tabImage = (ImageView) v.findViewById(R.id.tab_image);

        v.setBackgroundColor(Color.parseColor(color));
        tabText.setText(str);
        tabImage.setBackgroundResource(genResIcon);

        return spec.setIndicator(v);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProblemsContainer.releaseInstance();
    }
}
