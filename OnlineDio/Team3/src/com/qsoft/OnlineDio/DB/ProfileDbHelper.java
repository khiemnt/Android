package com.qsoft.OnlineDio.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 11/5/13
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class ProfileDbHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME= "onlinedio.db";
    private static final int DATABASE_VERSION=1;

    public static final String PROFILE_TABLE_NAME="profile";

    public static final String PROFILE_COL_ID="id";
    public static final String PROFILE_COL_FACEBOOK_ID="facebook_id";
    public static final String PROFILE_COL_USERNAME="username";
    public static final String PROFILE_COL_PASSWORD="password";
    public static final String PROFILE_COL_AVATAR="avatar";
    public static final String PROFILE_COL_COVER_IMAGE="cover_image";
    public static final String PROFILE_COL_DISPLAY_NAME="display_name";
    public static final String PROFILE_COL_FULL_NAME="full_name";
    public static final String PROFILE_COL_PHONE="phone";
    public static final String PROFILE_COL_BIRTHDAY="birthday";
    public static final String PROFILE_COL_GENDER="gender";
    public static final String PROFILE_COL_COUNTRY_ID="country_id";
    public static final String PROFILE_COL_STORAGE_PLAN_ID="storage_plan_id";
    public static final String PROFILE_COL_DESCRIPTION="description";
    public static final String PROFILE_COL_CREATE_AT="created_at";
    public static final String PROFILE_COL_UPDATE_AT="updated_at";
    public static final String PROFILE_COL_SOUNDS="sounds";
    public static final String PROFILE_COL_FAVORITES="favorites";
    public static final String PROFILE_COL_LIKES="likes";
    public static final String PROFILE_COL_FOLLOWINGS="followings";
    public static final String PROFILE_COL_AUDIENCES="audiences";


    // Database creation sql statement
    public static final String DATABASE_CREATE = "create table "
            + PROFILE_TABLE_NAME + "(" +
            PROFILE_COL_ID + " integer  primary key autoincrement," +
            PROFILE_COL_FACEBOOK_ID + " integer," +
            PROFILE_COL_USERNAME + " text," +
            PROFILE_COL_PASSWORD + " text," +
            PROFILE_COL_AVATAR + " text," +
            PROFILE_COL_COVER_IMAGE + " text," +
            PROFILE_COL_DISPLAY_NAME + " text," +
            PROFILE_COL_FULL_NAME + " boolean," +
            PROFILE_COL_PHONE + " text," +
            PROFILE_COL_BIRTHDAY + " text," +
            PROFILE_COL_GENDER + " integer," +
            PROFILE_COL_COUNTRY_ID + " integer," +
            PROFILE_COL_STORAGE_PLAN_ID + " integer," +
            PROFILE_COL_DESCRIPTION + " text," +
            PROFILE_COL_CREATE_AT + " text," +
            PROFILE_COL_UPDATE_AT + " text" +
            PROFILE_COL_SOUNDS + "  integer," +
            PROFILE_COL_FAVORITES + "  integer," +
            PROFILE_COL_LIKES + "  integer," +
            PROFILE_COL_FOLLOWINGS + "  integer," +
            PROFILE_COL_AUDIENCES + "  integer" +
            ");";


    public ProfileDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(HomeFeedDbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + PROFILE_TABLE_NAME);
        onCreate(db);
    }
}
