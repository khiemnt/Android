//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package com.qsoft.OnlineDio.Activity;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.qsoft.OnlineDio.R.id;
import com.qsoft.OnlineDio.R.layout;

public final class LoginActivity_
    extends LoginActivity
{

    private Handler handler_ = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.login_layout);
    }

    private void init_(Bundle savedInstanceState) {
        mAccountManager = ((AccountManager) this.getSystemService(Context.ACCOUNT_SERVICE));
    }

    private void afterSetContentView_() {
        btLogin = ((Button) findViewById(id.login_btLogin));
        etEmail = ((EditText) findViewById(id.etEmail));
        tvResetPas = ((TextView) findViewById(id.tvResetPass));
        ibtDelPass = ((ImageButton) findViewById(id.ibtDelPass));
        ibtDelEmail = ((ImageButton) findViewById(id.ibtDelEmail));
        btBack = ((Button) findViewById(id.login_btBack));
        etPass = ((EditText) findViewById(id.etPass));
        {
            View view = findViewById(id.login_btBack);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        LoginActivity_.this.onClickListener(view);
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.login_btLogin);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        LoginActivity_.this.onClickListener(view);
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.tvResetPass);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        LoginActivity_.this.onClickListener(view);
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.ibtDelEmail);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        LoginActivity_.this.onClickListener(view);
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.ibtDelPass);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        LoginActivity_.this.onClickListener(view);
                    }

                }
                );
            }
        }
        {
            final TextView view = ((TextView) findViewById(id.etEmail));
            if (view!= null) {
                view.addTextChangedListener(new TextWatcher() {


                    @Override
                    public void afterTextChanged(Editable s) {
                        LoginActivity_.this.afterTextChanged(s);
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        LoginActivity_.this.onTextChangedOnTextView(s, start, before, count);
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        LoginActivity_.this.beforeTextChangedOnTextView(s, start, count, after);
                    }

                }
                );
            }
        }
        {
            final TextView view = ((TextView) findViewById(id.etPass));
            if (view!= null) {
                view.addTextChangedListener(new TextWatcher() {


                    @Override
                    public void afterTextChanged(Editable s) {
                        LoginActivity_.this.afterTextChanged(s);
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        LoginActivity_.this.onTextChangedOnTextView(s, start, before, count);
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        LoginActivity_.this.beforeTextChangedOnTextView(s, start, count, after);
                    }

                }
                );
            }
        }
        afterViews();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        afterSetContentView_();
    }

    public static LoginActivity_.IntentBuilder_ intent(Context context) {
        return new LoginActivity_.IntentBuilder_(context);
    }

    @Override
    public void resultLogin(final Intent intent) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    LoginActivity_.super.resultLogin(intent);
                } catch (RuntimeException e) {
                    Log.e("LoginActivity_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void getInfoUser(final String email, final String pass, final String accountType) {
        BackgroundExecutor.execute(new Runnable() {


            @Override
            public void run() {
                try {
                    LoginActivity_.super.getInfoUser(email, pass, accountType);
                } catch (RuntimeException e) {
                    Log.e("LoginActivity_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, LoginActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public LoginActivity_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

        public void startForResult(int requestCode) {
            if (context_ instanceof Activity) {
                ((Activity) context_).startActivityForResult(intent_, requestCode);
            } else {
                context_.startActivity(intent_);
            }
        }

    }

}