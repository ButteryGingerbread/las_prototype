// DBHelper.java
package com.example.libraryadminstrationsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBName = "borrow.db";

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table borrow(StudentID TEXT primary key, BookID TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("CREATE TABLE temp_borrow AS SELECT * FROM borrow");

        db.execSQL("DROP TABLE IF EXISTS borrow");

        db.execSQL("CREATE TABLE borrow ("
                + "StudentID TEXT PRIMARY KEY, "
                + "BookID TEXT)");

        db.execSQL("INSERT INTO borrow (StudentID, BookID) SELECT StudentID, BookID FROM temp_borrow");

        db.execSQL("DROP TABLE IF EXISTS temp_borrow");
    }

    public boolean insertData(String studentID, String bookID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("StudentID", studentID);
        contentValues.put("BookID", bookID );
        long result = db.insert("borrow", null, contentValues);
        if (result == -1) return false;
        else return true;
    }
}
