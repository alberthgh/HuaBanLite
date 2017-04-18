package com.albert.huabanlite.module.main;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.widget.FrameLayout;
import android.widget.Toolbar;

import com.albert.huabanlite.Entity.event.SwitchTypeEvent;
import com.albert.huabanlite.R;
import com.albert.huabanlite.base.BaseActivity;
import com.albert.huabanlite.listener.OnTypeChangeListener;
import com.albert.huabanlite.module.type.TypeFragment;
import com.albert.huabanlite.unit.RxBus;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements OnTypeChangeListener {

    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.container_with_refresh)
    FrameLayout containerWithRefresh;
    @BindView(R.id.main_coordinatorLayout)
    CoordinatorLayout mainCoordinatorLayout;
    @BindView(R.id.main_bmb)
    BoomMenuButton mainBmb;


    private String[] titleArray;
    private String[] typeArray;
    private int[] iconResource = new int[]{R.drawable.huaban_placeholder, R.drawable.icon_home,
            R.drawable.icon_beauty, R.drawable.icon_anim,
            R.drawable.icon_travel, R.drawable.icon_pet};

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        titleArray = getResources().getStringArray(R.array.title_recommend_main);//显示的文字
        typeArray = getResources().getStringArray(R.array.type_recommend_main);//查询的关键字

        setActionBar(toolbarMain);
        setTitle(titleArray[0]);
        setBoomMenu();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        TypeFragment typeFragment = TypeFragment.newInstance();
        typeFragment.setOnTypeChangeListener(this);
        transaction.replace(R.id.container_with_refresh, typeFragment);
        transaction.commit();
    }

    private void setBoomMenu() {
        for (int i = 0; i < mainBmb.getPiecePlaceEnum().pieceNumber(); i++) {
            final int temp = i;
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(iconResource[i])
                    .normalText(titleArray[temp])
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            RxBus.getDefault().post(new SwitchTypeEvent(typeArray[temp], titleArray[temp]));
                        }
                    });
            mainBmb.addBuilder(builder);
        }
    }

    @Override
    public void onTypeChange(String type){
        setTitle(type);
    }
}
