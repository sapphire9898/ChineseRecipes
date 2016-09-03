package com.example.yueyangzou.chineserecipes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

/**
 * Created by yueyangzou on 16/9/1.
 */
public class MenuWebPageActivity extends SingleFragmentActivity{

    public static Intent newIntent(Context context, Uri MenuPageUri) {
        Intent i = new Intent(context, MenuWebPageActivity.class);
        i.setData(MenuPageUri);
        return i;
    }

    @Override
    protected Fragment createFragment() {
        return MenuPageFragment.newInstance(getIntent().getData());
    }
}
