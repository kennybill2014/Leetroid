package joey.com.leetroid.utils;

import android.content.Context;

import java.io.File;

import joey.com.leetroid.Problem;
import joey.com.leetroid.ProblemsContainer;

public class ProblemListHelper {

    private Context mContext;

    private FileHelper mFileHelper;

    public ProblemListHelper(Context context) {
        mContext = context;
        mFileHelper = new FileHelper(context);
    }

    public void startBuild() {
        if (Datas.mDifficulties.length == Datas.mFileNames.length
                && Datas.mFileNames.length == Datas.mProblemNames.length) {
            for (int i = 0; i < Datas.mFileNames.length; i++) {
                Problem problem = new Problem(i, false, Datas.mProblemNames[i], Datas.mDifficulties[i], Datas.mFileNames[i]);
                ProblemsContainer.getInstance().addProblem(problem);
            }
            mFileHelper.startRead();
        }
    }
}
