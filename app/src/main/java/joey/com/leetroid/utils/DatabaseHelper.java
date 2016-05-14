package joey.com.leetroid.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import joey.com.leetroid.utils.ProblemDatabaseStatus.ProblemStatusEntry;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";

    private static final String INT_TYPE = " INTEGER";

    public static final String DATABASE_NAME = "ProblemStatus.db";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProblemStatusEntry.TABLE_NAME + "(" + ProblemStatusEntry._ID + " INTEGER PRIMARY KEY," +
                    ProblemStatusEntry.COLUMN_NAME_INDEX + INT_TYPE + COMMA_SEP +
                    ProblemStatusEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    ProblemStatusEntry.COLUMN_NAME_STATUS + INT_TYPE + ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProblemStatusEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}
