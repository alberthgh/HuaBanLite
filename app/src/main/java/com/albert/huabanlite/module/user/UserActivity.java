package com.albert.huabanlite.module.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.albert.huabanlite.R;
import com.albert.huabanlite.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by alberthuang on 2017/3/20.
 */

public class UserActivity extends BaseActivity {


    public static final String USER_ID = "USER_ID";
    public static final String USER_TITLE = "USER_TITLE";
    @BindView(R.id.user_toolbar)
    Toolbar userToolbar;
    @BindView(R.id.user_appbar)
    AppBarLayout userAppbar;
    @BindView(R.id.user_framelayout)
    FrameLayout userFramelayout;
    @BindView(R.id.user_layout)
    RelativeLayout userLayout;


    private String userId, title;

    public static void launch(Activity activity, String userId, String title) {
        Intent intent = new Intent(activity, UserActivity.class);
        intent.putExtra(USER_ID, userId);
        intent.putExtra(USER_TITLE, title);
        activity.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_simple;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        setActionBar(userToolbar);

        userId = getIntent().getStringExtra(USER_ID);
        if (userId != null) {
            getFragmentManager().beginTransaction().replace(R.id.user_framelayout, UserBoardsFragment.newInstance(userId)).commit();
        }

        title = getIntent().getStringExtra(USER_TITLE);
        if (title != null) {
            setTitle(title + "的个人主页");
        }
    }

}
