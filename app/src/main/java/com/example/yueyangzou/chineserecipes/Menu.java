package com.example.yueyangzou.chineserecipes;

import java.util.UUID;

/**
 * Created by yueyangzou on 16/8/22.
 */
public class Menu {
    private String mName;
    private String mDescription;
    private UUID id;

    public Menu() {
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
}
