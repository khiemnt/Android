package com.qsoft.OnlineDio.Activity;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.googlecode.androidannotations.annotations.*;
import com.qsoft.OnlineDio.Authenticate.AccountGeneral;
import com.qsoft.OnlineDio.Custom.ArrayAdapterSidebarListOption;
import com.qsoft.OnlineDio.Fragment.HomeFragment;
import com.qsoft.OnlineDio.R;
import com.qsoft.OnlineDio.Util.STATUS;

/**
 * User: Dell 3360
 * Date: 10/20/13
 * Time: 8:39 AM
 */
@EActivity(R.layout.slidebar)
public class SlidebarActivity extends FragmentActivity
{
    @ViewById(R.id.drawer_layout)
    protected DrawerLayout mDrawerLayout;

    @ViewById(R.id.slidebar_listOption)
    protected ListView lvOption;

    @ViewById(R.id.sidebar_ivProfile)
    protected ImageView ivProfile;

    @ViewById(R.id.left_drawer)
    protected RelativeLayout rlLeftDrawer;

    @SystemService
    AccountManager accountManager;

    final String[] listOptionName = {"Home", "Favorite", "Following", "Audience", "Genres", "Setting", "Help Center", "Sign Out"};
    private static final int HOME = 0;
    private static final int FAVORITE = 1;
    private static final int FOLLOWING = 2;
    private static final int AUDIENCE = 3;
    private static final int GENRES = 4;
    private static final int SETTING = 5;
    private static final int HELP_CENTER = 6;
    private static final int SIGN_OUT = 7;

    private ActionBarDrawerToggle mDrawerToggle;

    public HomeFragment homeFragment;
    private String token;
    private Account mConnectAccount;
    private String userId;

    public static Context context;

    @AfterViews
    void afterViews()
    {
        Bundle b = getIntent().getExtras();
        token = b.getString(FirstLaunchActivity.AUTHEN_TOKEN);
        userId = b.getString(LoginActivity.USER_ID);
        mConnectAccount = b.getParcelable(FirstLaunchActivity.ACCOUNT_CONNECTED);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        context = this;

        setUpDataListOption(this);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */)
        {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        showHomeFragment();

    }

    private void showHomeFragment()
    {
        Bundle b2 = new Bundle();
        b2.putString(FirstLaunchActivity.AUTHEN_TOKEN, token);
        b2.putParcelable(FirstLaunchActivity.ACCOUNT_CONNECTED, mConnectAccount);
        homeFragment = new HomeFragment();
        homeFragment.setArguments(b2);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.slidebar_homeFragment, homeFragment);
        ft.commit();
    }

    @ItemClick(R.id.slidebar_listOption)
    void slideBarItemClickListener(int index)
    {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (index)
        {
            case HOME:
                Bundle b2 = new Bundle();
                b2.putString(FirstLaunchActivity.AUTHEN_TOKEN, token);
                b2.putParcelable(FirstLaunchActivity.ACCOUNT_CONNECTED, mConnectAccount);
                homeFragment = new HomeFragment();
                homeFragment.setArguments(b2);
                ft.replace(R.id.slidebar_homeFragment, homeFragment, "HomeFragment");
                ft.addToBackStack("HomeFragment");
                ft.commit();
                break;
            case SIGN_OUT:
                accountManager.removeAccount(mConnectAccount, null, null);
                final Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra(LoginActivity.ARG_ACCOUNT_TYPE, AccountGeneral.ACCOUNT_TYPE);
                intent.putExtra(LoginActivity.ARG_AUTH_TYPE, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS);
                intent.putExtra(LoginActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);
                intent.putExtra(STATUS.LOGIN.toString(), STATUS.LOGIN_AGAIN.toString());
                final Bundle bundle = new Bundle();
                bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                startActivity(intent);
                break;
        }
        setCloseListOption();
    }

    @Click(R.id.sidebar_ivProfile)
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.sidebar_ivProfile:
                showProfile();
                setCloseListOption();
                break;
        }

    }


    private void showProfile()
    {
        Intent intent = new Intent(this, ProfileActivity_.class);
        intent.putExtra(FirstLaunchActivity.AUTHEN_TOKEN, token);
        intent.putExtra(LoginActivity.USER_ID, userId);
        intent.putExtra(FirstLaunchActivity.ACCOUNT_CONNECTED, mConnectAccount);
        startActivity(intent);
    }

    private void setUpDataListOption(Context context)
    {
        lvOption.setAdapter(new ArrayAdapterSidebarListOption(context, R.layout.slidebar_listoptions, listOptionName));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.slidebar_profileFragment);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    public void setOpenListOption()
    {
        mDrawerLayout.openDrawer(rlLeftDrawer);
    }

    public void setCloseListOption()
    {
        mDrawerLayout.closeDrawer(rlLeftDrawer);
    }
}
