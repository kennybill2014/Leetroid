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

    private Resources mResources;

    private String mPackageName;

    public FileHelper(Context context) {
        mContext = context;
        mResources = mContext.getResources();
        mPackageName = mContext.getPackageName();
    }

    public void startRead(final FileReadListener fileReadListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader input;
                for (int i = 0; i < Datas.mFileNames.length; i++) {
                    input = new BufferedReader(new InputStreamReader(mResources.openRawResource(mResources.getIdentifier(Datas.mFileNames[i], "raw", mPackageName))));
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
                        ProblemsContainer.getInstance().getProblem(i).mFileText = sb.toString();
                        sb = null;
                    }
                }
                fileReadListener.onComplete();
            }
        }).start();
    }

    public interface FileReadListener {
        public void onComplete();
    }
}
