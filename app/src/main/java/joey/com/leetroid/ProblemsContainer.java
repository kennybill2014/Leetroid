package joey.com.leetroid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

// How to make syntax highlighting content ?
// (1) vim *.cpp
// (2) :syntax on
// (3) :colorscheme elflord
// (4) :let html_use_css=0
// (5) :TOhtml
// (6) Remove title, header and meta
// (7) Copy the content to *.txt

public class ProblemsContainer {

    private ArrayList<Problem> mProblems = new ArrayList<Problem>();

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

    /**
     * Refresh problem list
     * Sort algorithm used here, first compare the star status, then the problem index.
     */
    public void refreshProblemList() {
        Collections.sort(mProblems, new Comparator<Problem>() {
            @Override
            public int compare(Problem lhs, Problem rhs) {
                if (lhs.mIsStared) {
                    if (rhs.mIsStared) {
                        return lhs.mIndex - rhs.mIndex;
                    } else {
                        return -1;
                    }
                } else {
                    if (rhs.mIsStared) {
                        return 1;
                    } else {
                        return lhs.mIndex - rhs.mIndex;
                    }
                }
            }
        });
    }

}
