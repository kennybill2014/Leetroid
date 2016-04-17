package joey.com.leetroid;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.TextView;

public class ProblemActivity extends Activity {

    private TextView mText;

    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);

        Intent intent = getIntent();
        String fileText = intent.getStringExtra("filetext");

        mTitle = (TextView) findViewById(R.id.problem_title_text);
        mText = (TextView) findViewById(R.id.problem_text);
        if (!TextUtils.isEmpty(fileText)) {
            Spanned spannedCode = Html.fromHtml(fileText);
            mText.setText(spannedCode);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
