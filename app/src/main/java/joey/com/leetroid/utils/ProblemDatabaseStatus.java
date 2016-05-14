package joey.com.leetroid.utils;

import android.provider.BaseColumns;

public final class ProblemDatabaseStatus {

    public ProblemDatabaseStatus() {

    }

    public static abstract class ProblemStatusEntry implements BaseColumns {

        public static final String TABLE_NAME = "problemstatus";
        public static final String COLUMN_NAME_INDEX = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_STATUS = "status";

    }

}
