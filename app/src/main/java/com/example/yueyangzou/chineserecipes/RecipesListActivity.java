package com.example.yueyangzou.chineserecipes;

import android.support.v4.app.Fragment;

/**
 * Created by yueyangzou on 16/8/22.
 */
public class RecipesListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new RecipesListFragment();
    }
}
