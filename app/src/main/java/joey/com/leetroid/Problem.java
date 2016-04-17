package joey.com.leetroid;


public class Problem {

    public boolean mIsStared;

    public int mIndex;
    public String mTitle;
    public String mDifficulty;
    public String mFilename;
    public String mFileText;

    public Problem(int index, boolean isStarred, String title, String difficulty, String fileName) {
        mIndex = index;
        mIsStared = isStarred;
        mTitle = title;
        mDifficulty = difficulty;
        mFilename = fileName;
    }

}
