package com.qsoft.OnlineDio.Model;

import android.content.ContentValues;
import android.database.Cursor;
import com.qsoft.OnlineDio.DB.HomeFeedDbHelper;
import com.qsoft.OnlineDio.Util.ConvertDate;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Dell 3360
 * Date: 10/20/13
 * Time: 8:39 AM
 */
public class Feed implements Serializable
{
    private int id;
    private int user_id;
    private String title;
    private String thumbnail;
    private String description;
    private String sound_path;
    private String duration;
    private boolean played;
    private String created_at;
    private String updated_at;
    private int likes;
    private int viewed;
    private int comments;
    private String username;
    private String display_name;
    private String avatar;

    public Feed()
    {
    }

    public Feed(String title, String username, int likes, int comments, String created_at)
    {
        this.title = title;
        this.username = username;
        this.likes = likes;
        this.comments = comments;
        this.created_at = created_at;
    }

    public Feed( int user_id, String title, String thumbnail, String description, String sound_path, String duration, boolean played, String created_at, String updated_at, int likes, int viewed, int comments, String username, String display_name, String avatar)
    {
        this.user_id = user_id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.description = description;
        this.sound_path = sound_path;
        this.duration = duration;
        this.played = played;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.likes = likes;
        this.viewed = viewed;
        this.comments = comments;
        this.username = username;
        this.display_name = display_name;
        this.avatar = avatar;
    }

    public static Feed fromCursor(Cursor cursorFeeds)
    {
        int user_id=cursorFeeds.getInt(cursorFeeds.getColumnIndex(HomeFeedDbHelper.HOME_FEED_COL_USER_ID));
        String title=cursorFeeds.getString(cursorFeeds.getColumnIndex(HomeFeedDbHelper.HOME_FEED_COL_TITLE));
        String thumbnail=cursorFeeds.getString(cursorFeeds.getColumnIndex(HomeFeedDbHelper.HOME_FEED_COL_THUMBNAIL));
        String description=cursorFeeds.getString(cursorFeeds.getColumnIndex(HomeFeedDbHelper.HOME_FEED_COL_DESCRIPTION));
        String sound_path=cursorFeeds.getString(cursorFeeds.getColumnIndex(HomeFeedDbHelper.HOME_FEED_COL_SOUND_PATH));
        String duration=cursorFeeds.getString(cursorFeeds.getColumnIndex(HomeFeedDbHelper.HOME_FEED_COL_DURATION));
        Boolean played=cursorFeeds.getInt(cursorFeeds.getColumnIndex(HomeFeedDbHelper.HOME_FEED_COL_Played))>0;

        String created_at=cursorFeeds.getString(cursorFeeds.getColumnIndex(HomeFeedDbHelper.HOME_FEED_COL_CREATED_AT));
        long days = 0;
        if(!created_at.equals(" ")){
            days =ConvertDate.getDays(ConvertDate.convertToDate(created_at),new Date());
        }

        String _created_at=days+" days ago";

        String updated_at=cursorFeeds.getString(cursorFeeds.getColumnIndex(HomeFeedDbHelper.HOME_FEED_COL_UPDATE_AT));
        int likes=cursorFeeds.getInt(cursorFeeds.getColumnIndex(HomeFeedDbHelper.HOME_FEED_COL_LIKES));
        int viewed=cursorFeeds.getInt(cursorFeeds.getColumnIndex(HomeFeedDbHelper.HOME_FEED_COL_VIEWED));
        int comments=cursorFeeds.getInt(cursorFeeds.getColumnIndex(HomeFeedDbHelper.HOME_FEED_COL_COMMENTS));
        String username=cursorFeeds.getString(cursorFeeds.getColumnIndex(HomeFeedDbHelper.HOME_FEED_COL_USERNAME));
        String display_name=cursorFeeds.getString(cursorFeeds.getColumnIndex(HomeFeedDbHelper.HOME_FEED_COL_DISPLAY_NAME));
        String avatar=cursorFeeds.getString(cursorFeeds.getColumnIndex(HomeFeedDbHelper.HOME_FEED_COL_AVATAR));
        return new Feed(user_id,title,thumbnail,description,sound_path,duration,played,_created_at,updated_at,likes,viewed,comments,username,display_name,avatar);
    }
    /**
     * Convenient method to get the objects data members in ContentValues object.
     * This will be useful for Content Provider operations,
     * which use ContentValues object to represent the data.
     *
     * @return
     */
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(HomeFeedDbHelper.HOME_FEED_COL_USER_ID, user_id);
        values.put(HomeFeedDbHelper.HOME_FEED_COL_TITLE, title);
        values.put(HomeFeedDbHelper.HOME_FEED_COL_THUMBNAIL, thumbnail);
        values.put(HomeFeedDbHelper.HOME_FEED_COL_DESCRIPTION, description);
        values.put(HomeFeedDbHelper.HOME_FEED_COL_SOUND_PATH, sound_path);
        values.put(HomeFeedDbHelper.HOME_FEED_COL_DURATION, duration);
        values.put(HomeFeedDbHelper.HOME_FEED_COL_Played, played);
        values.put(HomeFeedDbHelper.HOME_FEED_COL_CREATED_AT, created_at);
        values.put(HomeFeedDbHelper.HOME_FEED_COL_UPDATE_AT, updated_at);
        values.put(HomeFeedDbHelper.HOME_FEED_COL_LIKES, likes);
        values.put(HomeFeedDbHelper.HOME_FEED_COL_VIEWED, viewed);
        values.put(HomeFeedDbHelper.HOME_FEED_COL_COMMENTS, comments);
        values.put(HomeFeedDbHelper.HOME_FEED_COL_USERNAME, username);
        values.put(HomeFeedDbHelper.HOME_FEED_COL_DISPLAY_NAME, display_name);
        values.put(HomeFeedDbHelper.HOME_FEED_COL_AVATAR, avatar);
        return values;
    }

    //setter && getter

    public String getTitle()
    {
        return title;
    }

    public int getComments()
    {
        return comments;
    }


    public String getUsername()
    {
        return username;
    }


    public int getLikes()
    {
        return likes;
    }


    public String getCreated_at()
    {
        return created_at;
    }

    public static void main(String[] args)
    {
        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try
        {
            Date date1 = formatter.parse("Tue Nov 05 10:04:22 GMT+07:00 2013") ;
            System.out.println(date1);
        }
        catch (ParseException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
