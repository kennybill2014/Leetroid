package joey.com.leetroid;

import java.util.ArrayList;
import java.util.Collections;

// :syntax on
// :colorscheme desert
// :let html_use_css=0
// :TOhtml

public class ProblemsContainer {

    private ArrayList<Problem> mProblems = new ArrayList<Problem>();
    private ArrayList<String> mProblemTexts = new ArrayList<String>();

    private static ProblemsContainer sInstance;

    public ProblemsContainer() {

    }

    public static ProblemsContainer getInstance() {
        if (sInstance == null) {
            sInstance = new ProblemsContainer();
        }
        return sInstance;
    }

    public static void releaseInstance() {
        sInstance = null;
    }

    public void addProblem(Problem problem) {
        if (mProblems != null) {
            mProblems.add(problem);
        }
    }

    public int getSize() {
        if (mProblems != null) {
            return mProblems.size();
        }
        return 0;
    }

    public Problem getProblem(int index) {
        if (mProblems != null && index < mProblems.size()) {
            return mProblems.get(index);
        }
        return null;
    }

    public ArrayList<Problem> getProblems() {
        return mProblems;
    }

    public void addProblemText(String text) {
        if (mProblemTexts != null) {
            mProblemTexts.add(text);
        }
    }

    public String getProblemText(int position) {
        if (mProblemTexts != null && position < mProblemTexts.size()) {
            return mProblemTexts.get(position);
        }
        return null;
    }

    public void refreshProblemList() {
        for (int i = mProblems.size() - 1; i >= 1; i--) {
            if (mProblems.get(i).mIsStared && !mProblems.get(i - 1).mIsStared) {
                Collections.swap(mProblems, i, i - 1);
            }
        }
    }

}
