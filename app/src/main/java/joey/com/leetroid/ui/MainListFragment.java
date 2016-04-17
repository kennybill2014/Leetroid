package joey.com.leetroid.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import joey.com.leetroid.Problem;
import joey.com.leetroid.ProblemActivity;
import joey.com.leetroid.ProblemListItemView;
import joey.com.leetroid.ProblemsContainer;
import joey.com.leetroid.R;
import joey.com.leetroid.SearchFrameActivity;

public class MainListFragment extends Fragment {

    private View mRootView;
    private ImageView mImageView;
    private ListView mUniversalSetList;
    private ProblemListAdapter mAdapter;
    private ArrayList<Problem> mUniversalProblems = new ArrayList<Problem>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("onCreateView in FirstFragment");
        if (mRootView == null) {
            View view = inflater.inflate(R.layout.main_list_fragment, container, false);
            initViews(view);
            mRootView = view;
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        System.out.println("onActivityCreated in FirstFragment");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initViews(View view) {
        mAdapter = new ProblemListAdapter(getActivity());
        mImageView = (ImageView) view.findViewById(R.id.search_icon);
        mImageView.setOnClickListener(new SearchClickListener());
        mUniversalSetList = (ListView) view.findViewById(R.id.problem_region_universal_list);
        mUniversalSetList.setAdapter(mAdapter);
        mUniversalSetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Problem problem = ProblemsContainer.getInstance().getProblem(position);
                Intent intent = new Intent(MainListFragment.this.getActivity(), ProblemActivity.class);
                intent.putExtra("filetext", problem.mFileText);
                startActivity(intent);
            }
        });
    }

    public class ProblemListAdapter extends BaseAdapter {

        private Context mContext;

        public ProblemListAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            System.out.println("Get count ");
            return ProblemsContainer.getInstance().getSize();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.problem_list_item, null);
            }
            Problem problem = ProblemsContainer.getInstance().getProblem(position);
            System.out.println("Position " + position + " Title " + problem.mTitle);
            ((ProblemListItemView) convertView).setProblemAttributes(problem, mAdapter);
            return convertView;
        }
    }

    public class SearchClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), SearchFrameActivity.class);
            startActivity(intent);
        }
    }
}
