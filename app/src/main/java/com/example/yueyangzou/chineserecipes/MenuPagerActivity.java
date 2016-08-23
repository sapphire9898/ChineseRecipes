package com.example.yueyangzou.chineserecipes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

/**
 * Created by yueyangzou on 16/8/22.
 */
public class MenuPagerActivity extends FragmentActivity {
    private static final String EXTRA_MENU_ID = "com.example.yueyangzou.android.chineserecipe.menu_id";
    private ViewPager mViewPager;
    private List<Menu> mMenuList;

    public static Intent newIntent(Context packageContext, UUID menuId) {
        Intent intent = new Intent(packageContext, MenuPagerActivity.class);
        intent.putExtra(EXTRA_MENU_ID, menuId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pager);
        UUID menuId = (UUID) getIntent().getSerializableExtra(EXTRA_MENU_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_menu_pager_view_pager);
        mMenuList = MenuLab.get(this).getMenus();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Menu menu = mMenuList.get(position);
                return ChineseRecipesFragment.newInstance(menu.getId());
            }

            @Override
            public int getCount() {
                return mMenuList.size();
            }
        });
        for (int i = 0; i < mMenuList.size(); i++) {
            if (mMenuList.get(i).getId().equals(menuId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }





    }

}
