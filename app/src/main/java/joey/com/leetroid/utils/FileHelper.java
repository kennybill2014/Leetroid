package joey.com.leetroid.utils;


import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import joey.com.leetroid.ProblemsContainer;
import joey.com.leetroid.R;

public class FileHelper {

    private Context mContext;

    public FileHelper(Context context) {
        mContext = context;
    }

    public void startRead(final int rawId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Resources res = mContext.getResources();
                BufferedReader input = new BufferedReader(new InputStreamReader(
                        res.openRawResource(rawId)));
                StringBuffer sb = new StringBuffer();
                try {
                    String line;
                    while ((line = input.readLine()) != null) {
                        sb.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (sb.length() > 0) {
                    ProblemsContainer.getInstance().addProblemText(sb.toString());
                }
            }
        }).start();
    }

}
