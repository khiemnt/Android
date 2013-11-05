package com.qsoft.OnlineDio.DB;

import android.net.Uri;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 11/5/13
 * Time: 10:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class ProfileContract
{
    public static final String CONTENT_ITEM_TYPE="vnd.android.cursor.item/vnd.onlinedio.profile";
    public static final String CONTENT_TYPE_DIR="vnd.android.cursor.dir/vnd.onlinedio.profile";

    public static final String AUTHORITY="com.qsoft.onlinedio.profile.provider";
    public static final Uri CONTENT_URI= Uri.parse("content://"+AUTHORITY+"/profiles");
}
