package com.example.yueyangzou.chineserecipes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by yueyangzou on 16/8/22.
 */
public class ChineseRecipesFragment extends Fragment {

    public static final String ARG_MENU_ID = "menu_id";

    private Menu mMenu;
    private TextView mTextView;
    private TextView mContentView;

    public static ChineseRecipesFragment newInstance(UUID menuId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_MENU_ID, menuId);

        ChineseRecipesFragment fragment = new ChineseRecipesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID menuId = (UUID) getArguments().getSerializable(ARG_MENU_ID);
        mMenu = MenuLab.get(getActivity()).getMenu(menuId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        mTextView = (TextView) v.findViewById(R.id.fragment_menu_title);
        mContentView = (TextView) v.findViewById(R.id.fragment_menu_content);

        mTextView.setText(mMenu.getName());
        mContentView.setText(mMenu.getDescription());



        return v;
    }
}
