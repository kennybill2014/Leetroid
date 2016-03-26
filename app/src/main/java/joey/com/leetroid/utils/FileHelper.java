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

    private Resources mResouces;

    private String mPackageName;

    public String[] mFileNames = {"two_sum", "two_sum_ii",
            "two_sum_iii", "valid_palindrome",
            "implement_strstr", "reverse_words_in_a_string",
            "reverse_words_in_a_string_ii", "string_to_integer",
            "valid_number", "further_thoughts",
            "longest_substring_without_repeating_characters", "longest_substring_with_at_most_two_distinct_characters",
            "missing_ranges", "longest_palindromic_substring",
            "one_edit_distance", "read_n_characters_given_read4",
            "read_n_characters_given_read4_call_multiple_times", "reverse_integer",
            "plus_one", "palindrome_number",
            "merge_two_sorted_lists", "add_two_numbers",
            "swap_nodes_in_pairs", "merge_k_sorted_linked_lists"};

    public FileHelper(Context context) {
        mContext = context;
        mResouces = mContext.getResources();
        mPackageName = mContext.getPackageName();
    }

    public void startRead() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Resources res = mContext.getResources();
                BufferedReader input;
                for (int i = 0; i < 1; i++) { // 应该是mFileNames.length
                    input = new BufferedReader(new InputStreamReader(res.openRawResource(mResouces.getIdentifier(mFileNames[i], "raw", mPackageName))));
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
                        ProblemsContainer.getInstance().addProblemText(sb.toString());
                        sb = null;
                    }
                }
            }
        }).start();
    }

}
