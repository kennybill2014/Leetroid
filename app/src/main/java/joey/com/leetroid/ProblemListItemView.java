package joey.com.leetroid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import joey.com.leetroid.ui.MainListFragment.ProblemListAdapter;
import joey.com.leetroid.utils.ProblemDataBaseHelper;


public class ProblemListItemView extends RelativeLayout implements View.OnClickListener {

    private boolean isStared;

    private RelativeLayout mImageWrapper;

    private ImageView mImageView;

    private TextView mTitleView;

    private TextView mDifficultyView;

    private Problem mProblem;

    private ProblemListAdapter mAdapter;

    private ProblemDataBaseHelper mProblemDatabaseHelper;

    public ProblemListItemView(Context context) {
        super(context);
        //init(context);
    }

    public ProblemListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //init(context);
    }

    public ProblemListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //init(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        mImageWrapper = (RelativeLayout) findViewById(R.id.problem_region_star_wrapper);
        mImageView = (ImageView) findViewById(R.id.problem_region_star);
        mTitleView = (TextView) findViewById(R.id.problem_region_title);
        mDifficultyView = (TextView) findViewById(R.id.problem_region_difficulty);
        mImageView.setOnClickListener(this);
        mImageWrapper.setOnClickListener(this);
        //setOnClickListener(this);
    }

    public void setProblemAttributes(Problem problem, ProblemListAdapter adapter) {
        mAdapter = adapter;
        mProblem = problem;
        mTitleView.setText(problem.mTitle);
        mDifficultyView.setText(problem.mDifficulty);
        isStared = problem.mIsStared;
        if (isStared) {
            mImageView.setBackgroundResource(R.mipmap.starred);
        } else {
            mImageView.setBackgroundResource(R.mipmap.unstarred);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == mImageView || v == mImageWrapper) {
            if (mProblem.mIsStared) {
                mProblem.mIsStared = false;
                mImageView.setBackgroundResource(R.mipmap.unstarred);
                ProblemsContainer.getInstance().refreshProblemList();
                mAdapter.notifyDataSetChanged();
            } else {
                mProblem.mIsStared = true;
                mImageView.setBackgroundResource(R.mipmap.starred);
                ProblemsContainer.getInstance().refreshProblemList();
                mAdapter.notifyDataSetChanged();
            }
        }
    }

}
