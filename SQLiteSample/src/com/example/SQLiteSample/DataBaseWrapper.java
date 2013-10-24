package com.example.SQLiteSample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 10/22/13
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataBaseWrapper extends SQLiteOpenHelper
{
    public static final String STUDENTS = "Students";
    public static final String STUDENT_ID = "id";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_AGE = "age";

    private static final String DATABASE_NAME = "Students";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " + STUDENTS + "(" + STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + STUDENT_NAME + " TEXT NOT NULL, " + STUDENT_AGE + " INTEGER NOT NULL); ";

    public DataBaseWrapper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS" + STUDENTS);
        onCreate(db);
    }
}
