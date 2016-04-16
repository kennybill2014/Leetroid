package joey.com.leetroid;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchFrameActivity extends Activity {

    private ListView mSuggestionListView;

    private SuggestionAdapter mSuggestionAdapter;

    private EditText mEditText;

    private ArrayList<String> mSuggestions = new ArrayList<String> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mEditText = (EditText) findViewById(R.id.search_region_edit);
        mEditText.addTextChangedListener(new TextInputListener());
        mSuggestionAdapter = new SuggestionAdapter();
        mSuggestionListView = (ListView) findViewById(R.id.suggestion_listview);
        mSuggestionListView.setAdapter(mSuggestionAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public class TextInputListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            System.out.println("beforeTextChanged: " + s + " start " + start + " count " + count + " after " + after);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            System.out.println("onTextChanged: " + s + " start " + start + " count " + count + " before " + before);
            searchProblems(s.toString());
            mSuggestionAdapter.notifyDataSetChanged();
        }

        @Override
        public void afterTextChanged(Editable s) {
            System.out.println("afterTextChanged: " + s);
        }

    }

    public class SuggestionAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mSuggestions.size();
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
                View view = LayoutInflater.from(SearchFrameActivity.this).inflate(R.layout.suggestion_list_item, null);
                convertView = view;
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) view.findViewById(R.id.suggestion_id);
                view.setTag(viewHolder);
            }
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            (viewHolder.textView).setText(mSuggestions.get(position));
            return convertView;
        }
    }

    public class ViewHolder {
        public TextView textView;
    }

    private void searchProblems(String input) {
        ArrayList<Problem> problems = ProblemsContainer.getInstance().getProblems();
        mSuggestions.clear();
        for (int i = 0; i < problems.size(); i++) {
            if (problems.get(i).mTitle.toLowerCase().contains(input.toLowerCase())) {
                mSuggestions.add(problems.get(i).mTitle);
            }
        }
    }

}
