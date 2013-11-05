package com.qsoft.OnlineDio.DB;

import android.net.Uri;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 11/4/13
 * Time: 11:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class HomeFeedContract
{
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.onlinedio.homefeed";
    public static final String CONTENT_TYPE_DIR = "vnd.android.cursor.dir/vnd.onlinedio.homefeed";

    public static final String AUTHORITY = "com.qsoft.onlinedio.homefeed.provider";
    // content://<authority>/<path to type>
    public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/homefeeds");
}
