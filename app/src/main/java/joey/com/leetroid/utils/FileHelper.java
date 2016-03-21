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

    public int[] mFileIds = {R.raw.two_sum};

    public FileHelper(Context context) {
        mContext = context;
    }

    public void startRead(final int rawId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Resources res = mContext.getResources();
                BufferedReader input;
                for (int i = 0; i < mFileIds.length; i++) {
                    input = new BufferedReader(new InputStreamReader(res.openRawResource(mFileIds[i])));
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
                        sb = null;
                    }
                }
            }
        }).start();
    }

}
