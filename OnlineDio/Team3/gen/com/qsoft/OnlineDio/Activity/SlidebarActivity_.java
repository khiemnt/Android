//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package com.qsoft.OnlineDio.Activity;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.googlecode.androidannotations.api.SdkVersionHelper;
import com.qsoft.OnlineDio.R.layout;

public final class SlidebarActivity_
    extends SlidebarActivity
{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.slidebar);
    }

    private void init_(Bundle savedInstanceState) {
        accountManager = ((AccountManager) this.getSystemService(Context.ACCOUNT_SERVICE));
    }

    private void afterSetContentView_() {
        ivProfile = ((ImageView) findViewById(com.qsoft.OnlineDio.R.id.sidebar_ivProfile));
        rlLeftDrawer = ((RelativeLayout) findViewById(com.qsoft.OnlineDio.R.id.left_drawer));
        lvOption = ((ListView) findViewById(com.qsoft.OnlineDio.R.id.slidebar_listOption));
        mDrawerLayout = ((DrawerLayout) findViewById(com.qsoft.OnlineDio.R.id.drawer_layout));
        {
            View view = findViewById(com.qsoft.OnlineDio.R.id.sidebar_ivProfile);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        SlidebarActivity_.this.onClick(view);
                    }

                }
                );
            }
        }
        {
            AdapterView<?> view = ((AdapterView<?> ) findViewById(com.qsoft.OnlineDio.R.id.slidebar_listOption));
            if (view!= null) {
                view.setOnItemClickListener(new OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        slideBarItemClickListener(position);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (((SdkVersionHelper.getSdkInt()< 5)&&(keyCode == KeyEvent.KEYCODE_BACK))&&(event.getRepeatCount() == 0)) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    public static SlidebarActivity_.IntentBuilder_ intent(Context context) {
        return new SlidebarActivity_.IntentBuilder_(context);
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, SlidebarActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public SlidebarActivity_.IntentBuilder_ flags(int flags) {
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
