package com.qsoft.OnlineDio.Fragment;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.qsoft.OnlineDio.Activity.SlidebarActivity;
import com.qsoft.OnlineDio.Adapter.FeedsAdapterCustom;
import com.qsoft.OnlineDio.Authenticate.AccountGeneral;
import com.qsoft.OnlineDio.DB.GenericContract;
import com.qsoft.OnlineDio.Model.Feed;
import com.qsoft.OnlineDio.R;

import java.io.IOException;
import java.util.ArrayList;

public class HomeFragment extends Fragment
{
    private Button btNavigate;
    private static ListView home_lvDetail;
    String token;
    public static String newToken;
    private Account mConnectAccount;
    private AccountManager accountManager;


    public static Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            String aResponse = msg.getData().getString("message");

            if ((null != aResponse)) {
              //  Toast.makeText(getActivity(),"fa",Toast.LENGTH_SHORT).show();
                ArrayList<Feed> feedList = readFromContentProvider();
                if (feedList != null && feedList.size() > 0)
                {

                    setUpDataToHomeListView(SlidebarActivity.context, feedList);
                }
            }
            else
            {
               // Toast.makeText(getActivity(),"345",Toast.LENGTH_SHORT).show();
            }

        }
    };
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.home_layout, null);
        Bundle bundle = getArguments();
         token = bundle.getString("token2");
        mConnectAccount = bundle.getParcelable("connectAccount");
        setUpUI(view);
        setUpListenerController();
//        if (mConnectAccount == null)
//        {
//            return null;
//        }
//        try
//        {
//            new Connection().execute(token);
//        }
//        catch (Exception ex)
//        {
//            ex.getMessage();
//        }


        // Create Inner Thread Class
        Thread background = new Thread(new Runnable() {
            // After call for background.start this run method call
            public void run() {
                try {
                    String isSync="ok";

                    threadMsg(isSync);

                } catch (Throwable t) {
                    // just end the background thread
                    Log.i("Animation", "Thread  exception " + t);
                }
            }

            private void threadMsg(String msg) {

                if (!msg.equals(null) && !msg.equals("")) {

                    refreshTokenExpires(mConnectAccount,token);
                    Bundle bundle1 = new Bundle();
                    bundle1.putBoolean(ContentResolver.SYNC_EXTRAS_FORCE, true);
                    bundle1.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true); // Performing a sync no matter if it's off
                    bundle1.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true); // Performing a sync no matter if it's off
                    ContentResolver.requestSync(mConnectAccount, GenericContract.AUTHORITY, bundle1);

                }
            }

            // Define the Handler that receives messages from the thread and update the progress


        });
        // Start Thread
        background.start();  //After call start method thread called run Method


        return view;
    }


    private static ArrayList<Feed> readFromContentProvider()
    {

        Cursor curFeeds = SlidebarActivity.context.getContentResolver().query(GenericContract.HOME_FEED_CONTENT_URI, null, null, null, null);
        ArrayList<Feed> feeds = new ArrayList<Feed>();
        if (curFeeds != null)
        {
            while (curFeeds.moveToNext())
            {
                feeds.add(Feed.fromCursor(curFeeds));
            }
            curFeeds.close();
        }
        return feeds;
    }

    private void setUpListenerController()
    {
        btNavigate.setOnClickListener(onClickListener);
        home_lvDetail.setOnItemClickListener(onItemClickListener);
    }

    private static void setUpDataToHomeListView(Context context, ArrayList<Feed> feeds)
    {
        home_lvDetail.setAdapter(new FeedsAdapterCustom(context, R.layout.home_items_layout, feeds));
    }

    private ListView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            doShowProgram();
        }
    };
    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.btNavigate:
                    showMenu();
                    break;
            }
        }
    };

    private void showMenu()
    {
        ((SlidebarActivity) getActivity()).setOpenListOption();
    }

    private void doShowProgram()
    {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.slidebar_homeFragment, new ProgramFragment(), "ProgramFragment");
        ft.addToBackStack("ProgramFragment");
        ft.commit();
    }

    private void setUpUI(View view)
    {
        btNavigate = (Button) view.findViewById(R.id.btNavigate);
        home_lvDetail = (ListView) view.findViewById(R.id.home_lvDetail);
    }

    private class Connection extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... param)
        {
            refreshTokenExpires(mConnectAccount, param[0]);
            Bundle bundle1 = new Bundle();
            bundle1.putBoolean(ContentResolver.SYNC_EXTRAS_FORCE, true);
            bundle1.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true); // Performing a sync no matter if it's off
            bundle1.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true); // Performing a sync no matter if it's off
            ContentResolver.requestSync(mConnectAccount, GenericContract.AUTHORITY, bundle1);

            return null;
        }

        @Override
        protected void onPostExecute(Void sync)
        {
            super.onPostExecute(sync);
            ArrayList<Feed> feedList = readFromContentProvider();
            if (feedList != null && feedList.size() > 0)
            {
                setUpDataToHomeListView(getActivity(), feedList);
            }
        }

        @Override
        protected void onCancelled(Void aVoid)
        {
            super.onCancelled(aVoid);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        protected void onCancelled()
        {
            super.onCancelled();    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        protected void onProgressUpdate(Void... values)
        {
            super.onProgressUpdate(values);

        }
    }

    private void refreshTokenExpires(Account mConnectAccount, String token)
    {

        accountManager = AccountManager.get(getActivity());

        accountManager.invalidateAuthToken(AccountGeneral.ACCOUNT_TYPE, token);
        try
        {
            String newToken = accountManager.getAuthToken(mConnectAccount, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS, true, null, null).getResult().getString(AccountManager.KEY_AUTHTOKEN);
        }

        catch (OperationCanceledException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (AuthenticatorException e)
        {
            e.printStackTrace();
        }

    }
}

