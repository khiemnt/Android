package com.qsoft.OnlineDio.Activity;

import android.accounts.*;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.googlecode.androidannotations.annotations.*;
import com.qsoft.OnlineDio.Authenticate.AccountGeneral;
import com.qsoft.OnlineDio.R;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 10/14/13
 * Time: 10:01 AM
 * To change this template use File | Settings | File Templates.
 */
@EActivity(R.layout.first_launch_layout)
public class FirstLaunchActivity extends AccountAuthenticatorActivity
{
    @ViewById(R.id.launch_btLogin)
    protected Button launch_btLogin;

    @SystemService
    AccountManager mAccountManager;

    private Account mConnectedAccount;
    public final static String AUTHEN_TOKEN = "authen_token";
    public final static String ACCOUNT_CONNECTED = "account_connected";


    @Click(R.id.launch_btLogin)
    protected void btLoginClick()
    {
        getTokenForAccountCreateIfNeeded(AccountGeneral.ACCOUNT_TYPE, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS);
    }


    private void getTokenForAccountCreateIfNeeded(String accountType, String authTokenType)
    {
        final AccountManagerFuture<Bundle> future = mAccountManager.getAuthTokenByFeatures(accountType, authTokenType, null, this, null, null,
                new AccountManagerCallback<Bundle>()
                {
                    @Override
                    public void run(AccountManagerFuture<Bundle> future)
                    {
                        Bundle bnd = null;
                        try
                        {
                            bnd = future.getResult();
                            String authToken = bnd.getString(AccountManager.KEY_AUTHTOKEN);
                            showMessage(((authToken != null) ? "SUCCESS!\ntoken: " + authToken : "FAIL"));
                            if (authToken != null)
                            {
                                String accountName = bnd.getString(AccountManager.KEY_ACCOUNT_NAME);
                                mConnectedAccount = new Account(accountName, AccountGeneral.ACCOUNT_TYPE);
                                String user_id = mAccountManager.getUserData(mConnectedAccount, LoginActivity.USER_ID);

                                Intent intent = new Intent(FirstLaunchActivity.this, SlidebarActivity_.class);
                                intent.putExtra(AUTHEN_TOKEN, authToken);
                                intent.putExtra(ACCOUNT_CONNECTED, mConnectedAccount);
                                intent.putExtra(LoginActivity.USER_ID, user_id);
                                startActivity(intent);

                            }
                            Log.d("udinic", "GetTokenForAccount Bundle is " + bnd);
                            finish();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            showMessage(e.getMessage());
                        }
                    }
                }
                , null);
    }

    @UiThread()
    protected void showMessage(final String msg)
    {
        if (TextUtils.isEmpty(msg))
        {
            return;
        }
        else
        {
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

}