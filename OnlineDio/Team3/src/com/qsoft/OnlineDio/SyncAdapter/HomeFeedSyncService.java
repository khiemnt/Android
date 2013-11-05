package com.qsoft.OnlineDio.SyncAdapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 11/4/13
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomeFeedSyncService  extends Service
{
    private static final Object sSyncAdapterLock=new Object();
    private static HomeFeedSyncAdapter syncAdapter=null;

    @Override
    public void onCreate()
    {
       synchronized (sSyncAdapterLock){
           if(syncAdapter==null){
               syncAdapter=new HomeFeedSyncAdapter(getApplicationContext(),true);
           }
       }
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return syncAdapter.getSyncAdapterBinder();
    }
}
