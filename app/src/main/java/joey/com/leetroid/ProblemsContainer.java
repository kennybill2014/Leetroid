package joey.com.leetroid;

import java.util.ArrayList;

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
        if (mProblems != null) {
            return mProblems.get(index);
        }
        return null;
    }

}
