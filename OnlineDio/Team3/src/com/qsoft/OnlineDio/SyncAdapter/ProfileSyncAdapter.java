package com.qsoft.OnlineDio.SyncAdapter;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.*;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import com.qsoft.OnlineDio.Authenticate.AccountGeneral;
import com.qsoft.OnlineDio.DB.HomeFeedContract;
import com.qsoft.OnlineDio.Model.Feed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 11/5/13
 * Time: 11:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class ProfileSyncAdapter  extends AbstractThreadedSyncAdapter
{
    private static final String TAG = "ProfileSyncAdapter";

    private final AccountManager accountManager;

    public ProfileSyncAdapter(Context context, boolean autoInitialize)
    {
        super(context, autoInitialize);
        accountManager = AccountManager.get(context);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult)
    {
        try
        {
            String authToken = accountManager.blockingGetAuthToken(account, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS, true);

            //get Feeds from server
            ParseServerAccessor parseServerAccessor = new ParseServerAccessor();
            List<Feed> remoteFeeds=parseServerAccessor.getListFeeds(authToken) ;

            //get Feed from local
            ArrayList<Feed> localFeeds=new ArrayList<Feed>();
            Cursor cursorFeeds=provider.query(HomeFeedContract.CONTENT_URI,null,null,null,null);
            if(cursorFeeds!=null){
                while(cursorFeeds.moveToNext()){
                    localFeeds.add(Feed.fromCursor(cursorFeeds));
                }
                cursorFeeds.close();
            }
            //local co ,server ko co
            ArrayList<Feed> showToRemote=new ArrayList<Feed>();
            for(Feed localFeed:localFeeds){
                if(!remoteFeeds.contains(localFeed)){
                    showToRemote.add(localFeed);
                }
            }
            //server co ,local ko co
            ArrayList<Feed>showToLocal=new ArrayList<Feed>();
            for(Feed remoteFeed:remoteFeeds){
                if(!localFeeds.contains(remoteFeed)){
                    showToLocal.add(remoteFeed);
                }
            }

            //update lai local
            if(showToLocal.size()==0){
                Log.d("home feed", TAG + "> No server changes to update local database");
            } else{
                Log.d("home feed", TAG + "> Updating local database with remote changes");

                //updating local feed
                int i=0;
                ContentValues showToLocalValues[]=new ContentValues[showToLocal.size()];
                for(Feed localFeed:showToLocal){
                    showToLocalValues[i++]=localFeed.getContentValues();
                }
                provider.bulkInsert(HomeFeedContract.CONTENT_URI, showToLocalValues);
            }

            Log.d("home feed", TAG + "> Finished.");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
