package joey.com.leetroid.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import joey.com.leetroid.R;

public class WebContainerFragment extends BaseContainerFragment {

    private boolean isViewInited = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.container_framelayout, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!isViewInited) { // replace ?
            isViewInited = true;
            replaceFragment(new WebFragment(), false);
        }
    }

}
