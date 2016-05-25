package joey.com.leetroid.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;

import joey.com.leetroid.utils.ProblemDatabaseStatus.ProblemStatusEntry;

public class ProblemDataBaseHelper {

    private DatabaseHelper mDatabaseHelper;

    private Context mContext;

    public ProblemDataBaseHelper(Context context) {
        mContext = context;
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public boolean isDatabaseExists() {
        File dbFile = mContext.getDatabasePath(DatabaseHelper.DATABASE_NAME);
        return dbFile.exists();
    }

    public void putStatusDatabase(int id, String title, int status) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProblemStatusEntry.COLUMN_NAME_INDEX, id);
        values.put(ProblemStatusEntry.COLUMN_NAME_TITLE, title);
        values.put(ProblemStatusEntry.COLUMN_NAME_STATUS, status);

        long newRowId;
        newRowId = db.insert(ProblemStatusEntry.TABLE_NAME, null, values);
    }

    public int getStatusDatabase(int id) {
        int status;
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();

        String[] projection = { ProblemStatusEntry.COLUMN_NAME_STATUS };

        String selection = "SELECT " + ProblemStatusEntry.COLUMN_NAME_STATUS
                + " FROM " + ProblemStatusEntry.TABLE_NAME + " WHERE "
                + ProblemStatusEntry.COLUMN_NAME_INDEX + " = " + id;
        Cursor c = db.rawQuery(selection, null);
        c.moveToFirst();
        status = c.getInt(c.getColumnIndexOrThrow(ProblemStatusEntry.COLUMN_NAME_STATUS));
        c.close();

        return status;
    }

    public void updateStatusDatabase(int id, String title, int status) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProblemStatusEntry.COLUMN_NAME_INDEX, id);
        values.put(ProblemStatusEntry.COLUMN_NAME_TITLE, title);
        values.put(ProblemStatusEntry.COLUMN_NAME_STATUS, status);

        String clause = ProblemStatusEntry.COLUMN_NAME_INDEX + " = " + id;
        db.update(ProblemStatusEntry.TABLE_NAME, values, clause, null);
    }

}
