package com.qsoft.OnlineDio.Activity;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.googlecode.androidannotations.annotations.*;
import com.qsoft.OnlineDio.Authenticate.AccountGeneral;
import com.qsoft.OnlineDio.Authenticate.User;
import com.qsoft.OnlineDio.R;
import com.qsoft.OnlineDio.Util.STATUS;
import com.qsoft.OnlineDio.Validate.Constant;
import com.qsoft.OnlineDio.Validate.EmailFormatValidator;
import com.qsoft.OnlineDio.Validate.NetworkUtil;

import static com.qsoft.OnlineDio.Authenticate.AccountGeneral.sServerAuthenticate;

@EActivity(R.layout.login_layout)
public class LoginActivity extends AccountAuthenticatorActivity
{
    public static String USER_ID = "user_id";
    public final static String ARG_ACCOUNT_TYPE = "ACCOUNT_TYPE";
    public final static String ARG_AUTH_TYPE = "AUTH_TYPE";
    public final static String ARG_ACCOUNT_NAME = "ACCOUNT_NAME";
    public final static String ARG_IS_ADDING_NEW_ACCOUNT = "IS_ADDING_ACCOUNT";
    public final static String PARAM_USER_PASS = "USER_PASS";
    public static final String KEY_ERROR_MESSAGE = "ERR_MSG";

    EmailFormatValidator emailFormatValidator = new EmailFormatValidator();

    @ViewById(R.id.login_btLogin)
    Button btLogin;

    @ViewById(R.id.login_btBack)
    Button btBack;

    @ViewById(R.id.tvResetPass)
    TextView tvResetPas;

    @ViewById(R.id.etEmail)
    EditText etEmail;

    @ViewById(R.id.etPass)
    EditText etPass;

    @ViewById(R.id.ibtDelEmail)
    ImageButton ibtDelEmail;

    @ViewById(R.id.ibtDelPass)
    ImageButton ibtDelPass;

    @SystemService
    AccountManager mAccountManager;

    private String email;
    private String pass;

    private String mAuthTokenType;


    private final String TAG = this.getClass().getSimpleName();
    private String checkLogin;


    @AfterViews
    void afterViews()
    {
        mAuthTokenType = getIntent().getStringExtra(ARG_AUTH_TYPE);
        checkLogin = getIntent().getStringExtra(STATUS.LOGIN.toString());
        if (mAuthTokenType == null)
        {
            mAuthTokenType = AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS;
        }
        getEmailAndPassword();

    }

    private void getEmailAndPassword()
    {
        email = etEmail.getText().toString();
        pass = etPass.getText().toString();
        if (!email.equals(" ") && !pass.equals(" "))
        {
            btLogin.setEnabled(true);
            btLogin.setClickable(true);
        }
    }

    @TextChange({R.id.etEmail, R.id.etPass})
    public void onTextChangedOnTextView(CharSequence s, int start, int before,
                                        int count)
    {
        if (!etEmail.getText().toString().isEmpty())
        {
            ibtDelEmail.setVisibility(View.VISIBLE);
            ibtDelPass.setVisibility(View.INVISIBLE);
        }
        if (!etPass.getText().toString().isEmpty())
        {
            ibtDelPass.setVisibility(View.VISIBLE);
            ibtDelEmail.setVisibility(View.INVISIBLE);
        }

        if (!etEmail.getText().toString().isEmpty() && !etPass.getText().toString().isEmpty())
        {
            btLogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_bt_active));
        }
        else
        {
            btLogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_bt_normal));
        }
    }

    @BeforeTextChange({R.id.etEmail, R.id.etPass})
    public void beforeTextChangedOnTextView(CharSequence s, int start, int count,
                                            int after)
    {
    }

    @AfterTextChange({R.id.etEmail, R.id.etPass})
    public void afterTextChanged(Editable s)
    {
        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus && !etEmail.getText().toString().isEmpty())
                {
                    ibtDelEmail.setVisibility(View.VISIBLE);
                }
                else
                {
                    ibtDelEmail.setVisibility(View.INVISIBLE);
                }
            }
        });
        etPass.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus && !etPass.getText().toString().isEmpty())
                {
                    ibtDelPass.setVisibility(View.VISIBLE);
                }
                else
                {
                    ibtDelPass.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Click({R.id.login_btBack, R.id.login_btLogin, R.id.tvResetPass, R.id.ibtDelEmail, R.id.ibtDelPass})
    public void onClickListener(View view)
    {
        switch (view.getId())
        {
            case R.id.login_btBack:
                Intent intentBack = new Intent(LoginActivity.this, FirstLaunchActivity.class);
                startActivity(intentBack);
                break;
            case R.id.login_btLogin:
                if (!checkNetwork())
                {
                    break;
                }
                else
                {
                    checkLogin();
                }
                break;
            case R.id.tvResetPass:
                resetPassWord();
                break;
            case R.id.ibtDelEmail:
                etEmail.setText("");
                break;
            case R.id.ibtDelPass:
                etPass.setText("");
                break;
        }
    }


    private boolean checkNetwork()
    {
        boolean result = true;
        String status = NetworkUtil.getConnectivityStatusString(this);
        if (status.equals(Constant.NOT_CONNECTED_TO_INTERNET.getValue()))
        {
            result = false;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(Constant.TITLE_MESSAGE.getValue());
            builder.setMessage(Constant.MESSAGE_CONNECTION_INTERNET.getValue());
            builder.setNegativeButton(R.string.OK, new OkOnClickListener());
            builder.show();
        }
        return result;
    }

    private void resetPassWord()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Forgot Password");
        LayoutInflater inflater = LoginActivity.this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.login_reset_password_layout, null))
                .setPositiveButton(R.string.reset, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                    }
                });
        builder.setView(inflater.inflate(R.layout.login_reset_password_layout, null))
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                    }
                });
        builder.show();
    }


    private void checkLogin()
    {
        email = etEmail.getText().toString();
        pass = etPass.getText().toString();
        String accountType = getIntent().getStringExtra(ARG_ACCOUNT_TYPE);
        getInfoUser(email, pass, accountType);

    }

    @Background
    public void getInfoUser(String email, String pass, String accountType)
    {
        Log.d("udinic", TAG + "> Started authenticating");

        String authtoken = null;
        Bundle data = new Bundle();
        try
        {
            if ((emailFormatValidator.validate(email)) == false)
            {
                data.putString(KEY_ERROR_MESSAGE, Constant.EMAIL_INVALID.getValue());
            }
            else
            {
                User user = sServerAuthenticate.userSignIn(email, pass, mAuthTokenType);
                if (user != null)
                {
                    authtoken = user.getAccess_token();
                }
                data.putString(AccountManager.KEY_ACCOUNT_NAME, email);
                data.putString(AccountManager.KEY_ACCOUNT_TYPE, accountType);
                data.putString(AccountManager.KEY_AUTHTOKEN, authtoken);
                data.putString(AccountManager.KEY_CALLER_UID, user.getUser_id());
                data.putString(PARAM_USER_PASS, pass);
            }
        }
        catch (Exception e)
        {
            data.putString(KEY_ERROR_MESSAGE, e.getMessage());
        }

        Intent intent = new Intent();
        intent.putExtras(data);
        resultLogin(intent);
    }

    @UiThread
    public void resultLogin(Intent intent)
    {
        if (intent.hasExtra(KEY_ERROR_MESSAGE))
        {
            Toast.makeText(getBaseContext(), intent.getStringExtra(KEY_ERROR_MESSAGE), Toast.LENGTH_SHORT).show();
        }
        else
        {
            finishLogin(intent);
        }
    }

    private final class OkOnClickListener implements
            DialogInterface.OnClickListener
    {
        public void onClick(DialogInterface dialog, int which)
        {
            etEmail.setText(Constant.EMAIL.getValue());
            etPass.setText(Constant.PASSWORD.getValue());
        }

    }

    private void finishLogin(Intent intent)
    {
        Log.d("udinic", TAG + "> finishLogin");

        String accountName = intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        String accountPassword = intent.getStringExtra(PARAM_USER_PASS);
        String user_id = intent.getStringExtra(AccountManager.KEY_CALLER_UID);
        final Account account = new Account(accountName, intent.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE));
        String authtoken = null;
        if (getIntent().getBooleanExtra(ARG_IS_ADDING_NEW_ACCOUNT, false))
        {
            Log.d("udinic", TAG + "> finishLogin > addAccountExplicitly");
            authtoken = intent.getStringExtra(AccountManager.KEY_AUTHTOKEN);
            String authtokenType = mAuthTokenType;

            // Creating the account on the device and setting the auth token we got
            // (Not setting the auth token will cause another call to the server to authenticate the user)
            mAccountManager.addAccountExplicitly(account, accountPassword, null);
            mAccountManager.setAuthToken(account, authtokenType, authtoken);
            mAccountManager.setUserData(account, USER_ID, user_id);
        }
        else
        {
            Log.d("udinic", TAG + "> finishLogin > setPassword");
            mAccountManager.setPassword(account, accountPassword);
        }
        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        if (checkLogin.equals(STATUS.LOGIN_AGAIN.toString()))
        {
            Intent i = new Intent(LoginActivity.this, SlidebarActivity.class);
            i.putExtra(FirstLaunchActivity.AUTHEN_TOKEN, authtoken);
            i.putExtra(LoginActivity.USER_ID, user_id);
            i.putExtra(FirstLaunchActivity.ACCOUNT_CONNECTED, account);
            startActivity(i);
        }
        finish();
    }
}
