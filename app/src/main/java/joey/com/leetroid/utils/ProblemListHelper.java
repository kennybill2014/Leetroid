package joey.com.leetroid.utils;

import android.content.Context;

import joey.com.leetroid.Problem;
import joey.com.leetroid.ProblemsContainer;

public class ProblemListHelper {

    private Context mContext;

    public ProblemListHelper(Context context) {
        mContext = context;
    }

    public void startBuild() {
        for (int i = 0; i < 1; i++) {
            Problem problem = new Problem(false, "Two Sum", "Easy");
            ProblemsContainer.getInstance().addProblem(problem);
        }
    }
}
