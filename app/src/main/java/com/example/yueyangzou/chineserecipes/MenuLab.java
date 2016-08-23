package com.example.yueyangzou.chineserecipes;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by yueyangzou on 16/8/22.
 */
public class MenuLab {
    private static MenuLab sMenuLab;
    private List<Menu> mMenus;

    public static MenuLab get(Context context) {
        if (sMenuLab == null) {
            sMenuLab = new MenuLab(context);
        }
        return sMenuLab;
    }
    private MenuLab(Context context) {
        mMenus = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Menu menu = new Menu();
            menu.setName("Menu #" + i);
            menu.setDescription("This is the first description");
            mMenus.add(menu);
        }
     }

    public List<Menu> getMenus() {
        return mMenus;
    }

    public Menu getMenu(UUID id) {
        for (Menu menu : mMenus) {
            if (menu.getId().equals(id)) {
                return menu;
            }
        }
        return null;
    }
}
