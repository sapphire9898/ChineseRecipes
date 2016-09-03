package com.example.yueyangzou.chineserecipes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yueyangzou on 16/8/22.
 */
public class RecipesListFragment extends Fragment {

    private RecyclerView mMenuRecyclerView;
    private MenuAdapter mAdapter;

    private CharSequence flavors[] = new CharSequence[]{"All", "Spicy", "Savory", "Crispy", "Sweet"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes_list, container, false);
        mMenuRecyclerView = (RecyclerView) view.findViewById(R.id.menu_recycler_view);
        mMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI("All", true);

        return view;
    }

    private void updateUI(String flaCode, boolean flavor) {
        MenuLab menuLab  = MenuLab.get(getActivity());
        List<MenuRecipe> menuRecipes = new ArrayList<>();

        if (flavor) {
            if (flaCode.equals("All")) {
                menuRecipes = menuLab.getMenuRecipes();
            }
            else {
                menuRecipes = menuLab.getMenuRecipesByFlavor(flaCode, true);
            }
        }
        else {
            menuRecipes = menuLab.getMenuRecipesByFlavor(flaCode, false);
        }

        mAdapter = new MenuAdapter(menuRecipes);
        mMenuRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_recipes_list, menu);


        MenuItem searchItem = menu.findItem(R.id.menu_item_search);

        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //update items according to the string.
                // the name should include the query. (strStr.)
                updateUI(query, false);
                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                updateUI(newText, false);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_selection :

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose one flavor");
                builder.setItems(flavors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0 :
                                updateUI("All", true);
                                break;
                            case 1 :
                                updateUI("Spicy", true);
                                //choose spicy ones. and then sort.
                                //updateUI();
                                break;
                            case 2 :
                                updateUI("Savory", true);
                                // choose savory ones, and then sort.
                                break;
                            case 3 :
                                updateUI("Crispy",true);
                                // crispy.
                                break;
                            case 4 :
                                // sweet'
                                updateUI("Sweet",true);
                                break;
                            default:break;
                        }
                    }
                });
                builder.show();

                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }



    private class MenuHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mFlavorTextView;
        private MenuRecipe mMenuRecipe;
        //public TextView mContentTextView;

        public MenuHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView)itemView.findViewById(R.id.list_item_menu_title_text_view);
            mFlavorTextView = (TextView)itemView.findViewById(R.id.list_item_menu_flavor_text_view);
        }
        public void bindMenu(MenuRecipe menuRecipe) {
            mMenuRecipe = menuRecipe;
            mTitleTextView.setText(menuRecipe.getName());
            mFlavorTextView.setText(menuRecipe.getShort());
        }

        @Override
        public void onClick(View v) {
            Intent intent = MenuPagerActivity.newIntent(getActivity(), mMenuRecipe.getId());
            startActivity(intent);
        }

    }

    private class MenuAdapter extends RecyclerView.Adapter<MenuHolder> {
        private List<MenuRecipe> mMenuRecipeList;

        @Override
        public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_menu, parent, false);
            return new MenuHolder(view);
        }

        public MenuAdapter(List<MenuRecipe> menuRecipeList) {
            mMenuRecipeList = menuRecipeList;
        }

        @Override
        public void onBindViewHolder(MenuHolder holder, int position) {
            MenuRecipe menuRecipe = mMenuRecipeList.get(position);
            holder.bindMenu(menuRecipe);

        }
        @Override
        public int getItemCount() {
            return mMenuRecipeList.size();
        }
    }


}
