package joey.com.leetroid.utils;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;

import joey.com.leetroid.Problem;
import joey.com.leetroid.ProblemsContainer;

public class ProblemListHelper implements FileHelper.FileReadListener {

    private Context mContext;

    private FileHelper mFileHelper;

    private ProblemDataBaseHelper mProblemDataBaseHelper;

    public ProblemListHelper(Context context) {
        mContext = context;
        mFileHelper = new FileHelper(context);
        mProblemDataBaseHelper = new ProblemDataBaseHelper(context);
    }

    public void startBuild() {
        if (Datas.mDifficulties.length == Datas.mFileNames.length
                && Datas.mFileNames.length == Datas.mProblemNames.length) {
            boolean isStarred;
            if (mProblemDataBaseHelper.isDatabaseExists()) {
                for (int i = 0; i < Datas.mFileNames.length; i++) {
                    isStarred = mProblemDataBaseHelper.getStatusDatabase(i) == 1;
                    Problem problem = new Problem(i, isStarred, Datas.mProblemNames[i], Datas.mDifficulties[i], Datas.mFileNames[i]);
                    ProblemsContainer.getInstance().addProblem(problem);
                }
            } else {
                for (int i = 0; i < Datas.mFileNames.length; i++) {
                    isStarred = false;
                    mProblemDataBaseHelper.putStatusDatabase(i, Datas.mProblemNames[i], 0);
                    Problem problem = new Problem(i, isStarred, Datas.mProblemNames[i], Datas.mDifficulties[i], Datas.mFileNames[i]);
                    ProblemsContainer.getInstance().addProblem(problem);
                }
            }
            mFileHelper.startRead(this);
        }
    }

    public void storeStatus() {
        if (mProblemDataBaseHelper.isDatabaseExists()) {
            ArrayList<Problem> problems = ProblemsContainer.getInstance().getProblems();
            for (int i = 0; i < problems.size(); i++) {
                Problem problem = problems.get(i);
                mProblemDataBaseHelper.updateStatusDatabase(problem.mIndex, problem.mTitle, problem.mIsStared ? 1 : 0);
            }
        }
    }

    @Override
    public void onComplete() {
        ProblemsContainer.getInstance().refreshProblemList();
    }
}
