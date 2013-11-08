package com.qsoft.OnlineDio.DB;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 11/4/13
 * Time: 11:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class GenericContentProvider extends ContentProvider
{
    public static final UriMatcher URI_MATCHER = buildUriMatcher();
    public static final String HOME_FEED_PATH = "homefeeds";
    public static final String PROFILE_PATH = "profiles";

    public static final int HOME_FEED_PATH_TOKEN = 100;
    public static final int PROFILE_PATH_TOKEN = 101;


    public static final String HOME_FEED_PATH_FOR_ID = "homefeeds/*";
    public static final String PROFILE_PATH_FOR_ID = "profiles/*";

    public static final int HOME_FEED_PATH_FOR_ID_TOKEN = 200;
    public static final int PROFILE_PATH_FOR_ID_TOKEN = 201;

    // Uri Matcher for the content provider
    private static UriMatcher buildUriMatcher()
    {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = GenericContract.AUTHORITY;
        //home feed
        matcher.addURI(authority, HOME_FEED_PATH, HOME_FEED_PATH_TOKEN);
        matcher.addURI(authority, HOME_FEED_PATH_FOR_ID, HOME_FEED_PATH_FOR_ID_TOKEN);
        //profile
        matcher.addURI(authority, PROFILE_PATH, PROFILE_PATH_TOKEN);
        matcher.addURI(authority, PROFILE_PATH_FOR_ID, PROFILE_PATH_FOR_ID_TOKEN);
        return matcher;
    }

    // Content Provider stuff

    private DbHelper dbHelper;

    @Override
    public boolean onCreate()
    {
        Context ctx = getContext();
        dbHelper = new DbHelper(ctx);
        return true;
    }

    @Override
    public String getType(Uri uri)
    {
        final int match = URI_MATCHER.match(uri);
        switch (match)
        {
            case HOME_FEED_PATH_TOKEN:
                return GenericContract.HOME_FEED_CONTENT_TYPE_DIR;
            case HOME_FEED_PATH_FOR_ID_TOKEN:
                return GenericContract.HOME_FEED_CONTENT_ITEM_TYPE;
            case PROFILE_PATH_TOKEN:
                return GenericContract.PROFILE_CONTENT_TYPE_DIR;
            case PROFILE_PATH_FOR_ID_TOKEN:
                return GenericContract.PROFILE_CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("URI " + uri + " is not supported.");
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        final int match = URI_MATCHER.match(uri);
        switch (match)
        {
            // retrieve home feed shows list
            case HOME_FEED_PATH_TOKEN:
            {
                SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
                builder.setTables(DbHelper.HOME_FEED_TABLE_NAME);
                return builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
            }
            case HOME_FEED_PATH_FOR_ID_TOKEN:
            {
                int homeFeedId = (int) ContentUris.parseId(uri);
                SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
                builder.setTables(DbHelper.HOME_FEED_TABLE_NAME);
                builder.appendWhere(DbHelper.HOME_FEED_COL_ID + "=" + homeFeedId);
                return builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
            }
            case PROFILE_PATH_TOKEN:
            {
                SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
                builder.setTables(DbHelper.PROFILE_TABLE_NAME);
                return builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
            }
            case PROFILE_PATH_FOR_ID_TOKEN:
            {
                int profileId = (int) ContentUris.parseId(uri);
                SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
                builder.setTables(DbHelper.PROFILE_TABLE_NAME);
                builder.appendWhere(DbHelper.PROFILE_COL_ID + "=" + profileId);
                return builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
            }
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int token = URI_MATCHER.match(uri);
        switch (token)
        {
            case HOME_FEED_PATH_TOKEN:
            {
                long id = db.insert(DbHelper.HOME_FEED_TABLE_NAME, null, values);
                if (id != -1)
                {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return GenericContract.HOME_FEED_CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();
            }
            case PROFILE_PATH_TOKEN:
            {
                long id=db.insert(DbHelper.PROFILE_TABLE_NAME,null,values);
                if(id!=-1){
                    getContext().getContentResolver().notifyChange(uri,null);
                }
                return GenericContract.PROFILE_CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build() ;
            }
            default:
            {
                throw new UnsupportedOperationException("URI: " + uri + " not supported.");
            }
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int token = URI_MATCHER.match(uri);
        int rowsDeleted = -1;
        switch (token)
        {
            case (HOME_FEED_PATH_TOKEN):
                rowsDeleted = db.delete(DbHelper.HOME_FEED_TABLE_NAME, selection, selectionArgs);
                break;
            case (HOME_FEED_PATH_FOR_ID_TOKEN):   {
                String HomeFeedIdWhereClause = DbHelper.HOME_FEED_COL_ID + "=" + uri.getLastPathSegment();
                if (!TextUtils.isEmpty(selection))
                {
                    HomeFeedIdWhereClause += " AND " + selection;
                }
                rowsDeleted = db.delete(DbHelper.HOME_FEED_TABLE_NAME, HomeFeedIdWhereClause, selectionArgs);
                break;
            }
            case (PROFILE_PATH_TOKEN):
                rowsDeleted = db.delete(DbHelper.PROFILE_TABLE_NAME, selection, selectionArgs);
                break;
            case (PROFILE_PATH_FOR_ID_TOKEN):
            {
                String ProfileIdWhereClause = DbHelper.PROFILE_COL_ID + "=" + uri.getLastPathSegment();
                if (!TextUtils.isEmpty(selection))
                {
                    ProfileIdWhereClause += " AND " + selection;
                }
                rowsDeleted = db.delete(DbHelper.PROFILE_TABLE_NAME, ProfileIdWhereClause, selectionArgs);
                break;
            }
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        // Notifying the changes, if there are any
        if (rowsDeleted != -1)
        {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int token = URI_MATCHER.match(uri);
        int rowsUpdate= -1;
        switch (token)
        {
            case (HOME_FEED_PATH_TOKEN):
                rowsUpdate = db.update(DbHelper.HOME_FEED_TABLE_NAME,values, selection, selectionArgs);
                break;
            case (PROFILE_PATH_TOKEN):
                rowsUpdate = db.update(DbHelper.PROFILE_TABLE_NAME,values, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        // Notifying the changes, if there are any
        if (rowsUpdate != -1)
        {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdate;
    }
}
