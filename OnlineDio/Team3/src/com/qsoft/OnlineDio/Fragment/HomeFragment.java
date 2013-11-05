package com.qsoft.OnlineDio.Fragment;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.qsoft.OnlineDio.Activity.SlidebarActivity;
import com.qsoft.OnlineDio.Adapter.FeedsAdapterCustom;
import com.qsoft.OnlineDio.DB.HomeFeedContract;
import com.qsoft.OnlineDio.Model.Feed;
import com.qsoft.OnlineDio.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment
{
    private Button btNavigate;
    private ListView home_lvDetail;

    public static String token;
    private Account mConnectAccount;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.home_layout, null);
        Bundle bundle = getArguments();
        token = bundle.getString("token2");
        mConnectAccount = bundle.getParcelable("connectAccount");
        setUpUI(view);
        setUpListenerController();
        if (mConnectAccount == null)
        {
            return null;
        }

        Bundle bundle1 = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_FORCE, true);
        bundle1.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true); // Performing a sync no matter if it's off
        bundle1.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true); // Performing a sync no matter if it's off
        ContentResolver.requestSync(mConnectAccount, HomeFeedContract.AUTHORITY, bundle);
        ArrayList<Feed> feedList = readFromContentProvider();
        if (feedList != null && feedList.size() > 0)
        {
            setUpDataToHomeListView(getActivity(), feedList);
        }

        return view;
    }

    private ArrayList<Feed> readFromContentProvider()
    {
        Cursor curFeeds = getActivity().getContentResolver().query(HomeFeedContract.CONTENT_URI, null, null, null, null);
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

    private void setUpDataToHomeListView(Context context, ArrayList<Feed> feeds)
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
//    private class Connection extends AsyncTask<String,Void,ArrayList<Feed>>
//    {
//
//
//        @Override
//        protected ArrayList<Feed> doInBackground(String... param)
//        {
//             feedsTemp = null;
//            try
//            {
//                ParseServerAccessor parseServerAccessor=new ParseServerAccessor();
//                feedsTemp=  parseServerAccessor.getListFeeds(param[0]) ;
//
//            }
//            catch (Exception e)
//            {
//                if( e.getMessage().equals("cannot access my apis")){
//                    //Todo: token expire->call login
//                }
//
//            }
//            return feedsTemp;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<Feed> feeds)
//        {
//            super.onPostExecute(feeds);
//            if(feeds!=null && feeds.size()>0){
//                setUpDataToHomeListView(getActivity(), feeds);
//                setUpListenerController();
//            }
//
//        }


    // }

}

