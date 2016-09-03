package com.example.yueyangzou.chineserecipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by yueyangzou on 16/8/22.
 */
public class ChineseRecipesFragment extends Fragment {

    public static final String ARG_MENU_ID = "menu_id";

    private MenuRecipe mMenuRecipe;
    private TextView mTextView;
    private TextView mContentView;
    private Button mButton;

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
        mMenuRecipe = MenuLab.get(getActivity()).getMenu(menuId);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        mTextView = (TextView) v.findViewById(R.id.fragment_menu_title);
        mContentView = (TextView) v.findViewById(R.id.fragment_menu_content);

        mTextView.setText(mMenuRecipe.getName());
        mContentView.setText(mMenuRecipe.getDescription());

        mButton = (Button) v.findViewById(R.id.web_search_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = MenuWebPageActivity.newIntent(getActivity(), mMenuRecipe.getMenuUri());
//                startActivity(i);

                 Intent i = new Intent(Intent.ACTION_VIEW, mMenuRecipe.getMenuUri());
                 startActivity(i);
            }
        });



        return v;
    }
}
