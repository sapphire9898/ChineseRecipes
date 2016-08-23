package com.example.yueyangzou.chineserecipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yueyangzou on 16/8/22.
 */
public class RecipesListFragment extends Fragment {

    private RecyclerView mMenuRecyclerView;
    private MenuAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes_list, container, false);
        mMenuRecyclerView = (RecyclerView) view.findViewById(R.id.menu_recycler_view);
        mMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return view;
    }

    private void updateUI() {
        MenuLab menuLab  = MenuLab.get(getActivity());
        List<Menu> menus = menuLab.getMenus();

        mAdapter = new MenuAdapter(menus);
        mMenuRecyclerView.setAdapter(mAdapter);
    }




    private class MenuHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitleTextView;
        private Menu mMenu;
        //public TextView mContentTextView;

        public MenuHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView)itemView;
        }
        public void bindMenu(Menu menu) {
            mMenu = menu;
        }

        @Override
        public void onClick(View v) {
            Intent intent = MenuPagerActivity.newIntent(getActivity(), mMenu.getId());
            startActivity(intent);
        }


    }

    private class MenuAdapter extends RecyclerView.Adapter<MenuHolder> {
        private List<Menu> mMenuList;

        @Override
        public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new MenuHolder(view);
        }

        public MenuAdapter(List<Menu> menuList) {
            mMenuList = menuList;
        }

        @Override
        public void onBindViewHolder(MenuHolder holder, int position) {
            Menu menu = mMenuList.get(position);
            holder.bindMenu(menu);
            holder.mTitleTextView.setText(menu.getName());

        }
        @Override
        public int getItemCount() {
            return mMenuList.size();
        }
    }


}
