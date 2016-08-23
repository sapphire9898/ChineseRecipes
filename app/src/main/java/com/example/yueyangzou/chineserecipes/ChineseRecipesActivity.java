package com.example.yueyangzou.chineserecipes;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;


public class ChineseRecipesActivity extends SingleFragmentActivity {
    private static final String EXTRA_MENU_ID = "com.example.yueyang.android.chineserecipes.menu_id";

    public static Intent newIntent(Context packageContext, UUID menu_id) {
        Intent intent = new Intent(packageContext, ChineseRecipesActivity.class);
        intent.putExtra(EXTRA_MENU_ID, menu_id);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        UUID menuId = (UUID) getIntent().getSerializableExtra(EXTRA_MENU_ID);
        return ChineseRecipesFragment.newInstance(menuId);
    }
}
