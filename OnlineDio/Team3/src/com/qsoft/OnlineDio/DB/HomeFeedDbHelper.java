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
public class HomeFeedDbHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME= "onlinedio.db";
    private static final int DATABASE_VERSION=1;

    public static final String HOME_FEED_TABLE_NAME="home_feed";

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


    // Database creation sql statement
    public static final String DATABASE_CREATE = "create table "
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


    public HomeFeedDbHelper(Context context) {
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
        db.execSQL("DROP TABLE IF EXISTS " + HOME_FEED_TABLE_NAME);
        onCreate(db);
    }
}
