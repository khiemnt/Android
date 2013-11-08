package com.qsoft.OnlineDio.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 11/4/13
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class DbHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME= "onlinedio.db";
    private static final int DATABASE_VERSION=8;

    public static final String HOME_FEED_TABLE_NAME="home_feed";
    public static final String PROFILE_TABLE_NAME="profile";

    public static final String HOME_FEED_COL_ID="id";
    public static final String HOME_FEED_COL_USER_ID="user_id";
    public static final String HOME_FEED_COL_TITLE="title";
    public static final String HOME_FEED_COL_THUMBNAIL="thumbnail";
    public static final String HOME_FEED_COL_DESCRIPTION="description";
    public static final String HOME_FEED_COL_SOUND_PATH="sound_path";
    public static final String HOME_FEED_COL_DURATION="duration";
    public static final String HOME_FEED_COL_Played="played";
    public static final String HOME_FEED_COL_CREATED_AT="created_at";
    public static final String HOME_FEED_COL_UPDATE_AT="updated_at";
    public static final String HOME_FEED_COL_LIKES="likes";
    public static final String HOME_FEED_COL_VIEWED="viewed";
    public static final String HOME_FEED_COL_COMMENTS="comments";
    public static final String HOME_FEED_COL_USERNAME="username";
    public static final String HOME_FEED_COL_DISPLAY_NAME="display_name";
    public static final String HOME_FEED_COL_AVATAR="avatar";

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
    public static final String HOME_FEED_DATABASE_CREATE = "create table "
            + HOME_FEED_TABLE_NAME + "(" +
            HOME_FEED_COL_ID + " integer  primary key autoincrement," +
            HOME_FEED_COL_USER_ID + " integer," +
            HOME_FEED_COL_TITLE + " text," +
            HOME_FEED_COL_THUMBNAIL + " text," +
            HOME_FEED_COL_DESCRIPTION + " text," +
            HOME_FEED_COL_SOUND_PATH + " text," +
            HOME_FEED_COL_DURATION + " text," +
            HOME_FEED_COL_Played + " boolean," +
            HOME_FEED_COL_CREATED_AT + " text," +
            HOME_FEED_COL_UPDATE_AT + " text," +
            HOME_FEED_COL_LIKES + " integer," +
            HOME_FEED_COL_VIEWED + " integer," +
            HOME_FEED_COL_COMMENTS + " integer," +
            HOME_FEED_COL_USERNAME + " text," +
            HOME_FEED_COL_DISPLAY_NAME + " text," +
            HOME_FEED_COL_AVATAR + " text" +
            ");";

    // Database creation sql statement
    public static final String PROFILE_DATABASE_CREATE = "create table "
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
            PROFILE_COL_COUNTRY_ID + " text," +
            PROFILE_COL_STORAGE_PLAN_ID + " integer," +
            PROFILE_COL_DESCRIPTION + " text," +
            PROFILE_COL_CREATE_AT + " text," +
            PROFILE_COL_UPDATE_AT + " text," +
            PROFILE_COL_SOUNDS + "  integer," +
            PROFILE_COL_FAVORITES + "  integer," +
            PROFILE_COL_LIKES + "  integer," +
            PROFILE_COL_FOLLOWINGS + "  integer," +
            PROFILE_COL_AUDIENCES + "  integer" +
            ");";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(HOME_FEED_DATABASE_CREATE);
        database.execSQL(PROFILE_DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + HOME_FEED_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PROFILE_TABLE_NAME);
        onCreate(db);
    }
}
