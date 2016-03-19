package joey.com.leetroid;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

public class ProblemActivity extends Activity {

    private TextView mText;

    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);

        Intent intent = getIntent();
        int fileIndex = intent.getIntExtra("fileindex", 0);

        mTitle = (TextView) findViewById(R.id.problem_title_text);

        mText = (TextView) findViewById(R.id.problem_text);
        Spanned spannedCode = Html.fromHtml(ProblemsContainer.getInstance().getProblemText(fileIndex));
        mText.setText(spannedCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
