package com.albert.huabanlite.module.board;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.albert.huabanlite.R;
import com.albert.huabanlite.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by alberthuang on 2017/3/16.
 */

public class BoardDetailActivity extends BaseActivity {


    protected static final String BOARD_ID = "BOARD_ID";
    protected static final String BOARD_TITLE = "BOARD_TITLE";


    @BindView(R.id.board_toolbar)
    Toolbar toolbar;
    @BindView(R.id.board_framelayout)
    FrameLayout boardFramelayout;
    @BindView(R.id.board_layout)
    RelativeLayout boardLayout;

    private String boardId, boardTitle;

    public static void launch(Activity activity, String boardId, String boardTitle) {
        Intent intent = new Intent(activity, BoardDetailActivity.class);
        intent.putExtra(BOARD_ID, boardId);
        intent.putExtra(BOARD_TITLE, boardTitle);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_board_detail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setActionBar(toolbar);

        boardTitle = getIntent().getStringExtra(BOARD_TITLE);
        if (boardTitle != null) {
            setTitle(boardTitle);
        }

        boardId = getIntent().getStringExtra(BOARD_ID);
        if (boardId != null) {
            getFragmentManager().beginTransaction().replace(R.id.board_framelayout, BoardDetailFragment.newInstance(boardId)).commit();
        }
    }

}
