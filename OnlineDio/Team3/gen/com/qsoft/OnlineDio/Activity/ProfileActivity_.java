//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package com.qsoft.OnlineDio.Activity;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.qsoft.OnlineDio.R.id;
import com.qsoft.OnlineDio.R.layout;

public final class ProfileActivity_
    extends ProfileActivity
{

    private Handler handler_ = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.profile_layout);
    }

    private void init_(Bundle savedInstanceState) {
    }

    private void afterSetContentView_() {
        pr_ibDeletePhone = ((ImageButton) findViewById(id.pr_ibDeletePhone));
        pr_edDescription = ((EditText) findViewById(id.pr_edDesciprtion));
        pr_edFullName = ((EditText) findViewById(id.pr_edFullName));
        btTakePicture = ((Button) findViewById(id.dg_btTakePicture));
        btCancel = ((Button) findViewById(id.pr_btCancel));
        pr_ivCoverImage = ((ImageView) findViewById(id.pr_ivCoverImage));
        pr_edBirthday = ((EditText) findViewById(id.pr_edBirthday));
        pr_ibDeleteFullName = ((ImageButton) findViewById(id.pr_ibDeleteFullName));
        pr_edDisplayName = ((EditText) findViewById(id.pr_edDisplayName));
        pr_btSave = ((Button) findViewById(id.pr_btSave));
        btChoosePicture = ((Button) findViewById(id.dg_btChoosePicture));
        btGenderSelectRight = ((Button) findViewById(id.pr_btnSelectRight_check));
        pr_rlBackGround = ((RelativeLayout) findViewById(id.pr_rlBackGround));
        pr_imgAvatar = ((ImageView) findViewById(id.pr_imgAvatar));
        pr_edCountry = ((EditText) findViewById(id.pr_edCountry));
        btGenderSelectLeft = ((Button) findViewById(id.pr_btnSelectLeft_check));
        pr_btCancel = ((Button) findViewById(id.pr_btCancel));
        pr_edPhone = ((EditText) findViewById(id.pr_edPhone));
        {
            View view = findViewById(id.pr_imgAvatar);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        ProfileActivity_.this.onClickListener(view);
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.pr_rlBackGround);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        ProfileActivity_.this.onClickListener(view);
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.pr_ibDeleteFullName);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        ProfileActivity_.this.onClickListener(view);
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.pr_ibDeletePhone);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        ProfileActivity_.this.onClickListener(view);
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.pr_edBirthday);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        ProfileActivity_.this.onClickListener(view);
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.pr_edCountry);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        ProfileActivity_.this.onClickListener(view);
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.pr_btnSelectLeft_check);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        ProfileActivity_.this.onClickListener(view);
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.pr_btnSelectRight_check);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        ProfileActivity_.this.onClickListener(view);
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.pr_btCancel);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        ProfileActivity_.this.onClickListener(view);
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.pr_btSave);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        ProfileActivity_.this.onClickListener(view);
                    }

                }
                );
            }
        }
        {
            final TextView view = ((TextView) findViewById(id.pr_edFullName));
            if (view!= null) {
                view.addTextChangedListener(new TextWatcher() {


                    @Override
                    public void afterTextChanged(Editable s) {
                        ProfileActivity_.this.afterTextChangedOnTextViews(s);
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                }
                );
            }
        }
        {
            final TextView view = ((TextView) findViewById(id.pr_edPhone));
            if (view!= null) {
                view.addTextChangedListener(new TextWatcher() {


                    @Override
                    public void afterTextChanged(Editable s) {
                        ProfileActivity_.this.afterTextChangedOnTextViews(s);
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
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

    public static ProfileActivity_.IntentBuilder_ intent(Context context) {
        return new ProfileActivity_.IntentBuilder_(context);
    }

    @Override
    public void getResult() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    ProfileActivity_.super.getResult();
                } catch (RuntimeException e) {
                    Log.e("ProfileActivity_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void connectToProfileServer(final String token, final String _status) {
        BackgroundExecutor.execute(new Runnable() {


            @Override
            public void run() {
                try {
                    ProfileActivity_.super.connectToProfileServer(token, _status);
                } catch (RuntimeException e) {
                    Log.e("ProfileActivity_", "A runtime exception was thrown while executing code in a runnable", e);
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
            intent_ = new Intent(context, ProfileActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public ProfileActivity_.IntentBuilder_ flags(int flags) {
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
