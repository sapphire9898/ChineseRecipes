package com.example.yueyangzou.chineserecipes;

import android.net.Uri;

import java.util.UUID;

/**
 * Created by yueyangzou on 16/8/22.
 */
public class MenuRecipe {
    private String mName;
    private String mDescription;
    private String mShort;
    private String mUrl;

    public String getShort() {
        return mShort;
    }

    public void setShort(String aShort) {
        mShort = aShort;
    }

    private UUID id;

    public MenuRecipe() {
        this.id = UUID.randomUUID();
    }
    public String getName() {
        return mName;
    }


    public void setName(String name) {
        mName = name;
    }

    public UUID getId() {
        return id;
    }


    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Uri getMenuUri() {
        return Uri.parse("http://www.google.com/search?q=" + mName + "restaurant" + "oq=" + mName + "restaurant" + "&sourceid=chrome-mobile&ie=UTF-8");

    }
}
