package com.example.yueyangzou.chineserecipes;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * Created by yueyangzou on 16/8/22.
 */




public class MenuLab {

    private List<String> nameList = new ArrayList<>(Arrays.asList("Mapo Tofu",
            "Green Onion Cakes",
            "Chinese Lion's Head soup",
            "Kung Pao Chicken",
            "Pork Dumplings",
            "Chinese Steamed Buns",
            "Wonton Soup"));

    private List<String> flavorList = new ArrayList<>(Arrays.asList("Spicy", "Crispy", "Savory", "Spicy", "Savory", "Savory", "Savory"));

    private List<String> descriptionList = new ArrayList<>(Arrays.asList("Make sauce: Stir together broth, bean paste, soy sauce, and kosher salt. Set aside.",
            "Use a fork to mix flour and boiling water in a large bowl. Knead dough into a ball. \n Cover bowl with plastic wrap; let dough rest for 30 to 60 minutes. \nEvenly divide dough into 16 pieces. Roll each piece into a 1/4 inch thick circle.\n Brush each circle with oil, season with salt and pepper, and sprinkle with about 1 teaspoon of green onions. \nRoll up, cigar style, and pinch open ends together to form a circle. \nRoll each circle flat to 1/4 inch. Heat 2 teaspoons oil in a large skillet. \nFry cakes until golden brown, about 2 minutes on each side." ,
            "Stir-fry pork : Heat a wok or large heavy skillet over high heat until hot and add 1 1/2 tablespoons corn oil, swirling to coat. Add pork and stir-fry, breaking up lumps and adding remaining 1/2 tablespoon corn oil if meat sticks, until no longer pink. Add garlic and ginger and stir-fry over moderate heat until very fragrant, about 2 minutes" ,
            "Finish stir-fry:\n",
            "Stir reserved sauce, then add to pork and bring to a simmer. Drain tofu in a large sieve and slide into sauce, stirring gently.",
            "Stir cornstarch mixture and add to stir-fry. Bring to a boil, stirring gently, and cook until thickened and glossy, about 15 seconds.\n",
            "Turn off heat and sprinkle with sesame oil, Sichuan-peppercorn powder to taste, and 2 tablespoons scallion. Stir once or twice, then serve sprinkled with remaining tablespoon scallion."));


    private static MenuLab sMenuLab;
    private List<MenuRecipe> mMenuRecipes;

    public static MenuLab get(Context context) {
        if (sMenuLab == null) {
            sMenuLab = new MenuLab(context);
        }
        return sMenuLab;
    }
    private MenuLab(Context context) {
        mMenuRecipes = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            MenuRecipe menuRecipe = new MenuRecipe();
            //
            menuRecipe.setName(nameList.get(i));
            menuRecipe.setDescription(descriptionList.get(i));
            menuRecipe.setShort(flavorList.get(i));
            mMenuRecipes.add(menuRecipe);
        }
        Collections.sort(mMenuRecipes, new RecipesComparator());
    }

    private class RecipesComparator implements Comparator<MenuRecipe> {
        public int compare(MenuRecipe a, MenuRecipe b) {
            return a.getName().compareTo(b.getName());
        }
    }


    public List<MenuRecipe> getMenuRecipes() {
        return mMenuRecipes;
    }

    public MenuRecipe getMenu(UUID id) {
        for (MenuRecipe menuRecipe : mMenuRecipes) {
            if (menuRecipe.getId().equals(id)) {
                return menuRecipe;
            }
        }
        return null;
    }

    public List<MenuRecipe> getMenuRecipesByFlavor(String flaCode, boolean flavor) {
        List<MenuRecipe> list = new ArrayList<>();

        if (flavor) {
            for (MenuRecipe menuRecipe : getMenuRecipes()) {
                if (menuRecipe.getShort().equals(flaCode)) {
                    list.add(menuRecipe);
                }
            }
        }
        else {
            // according to the name select the name.
            for (MenuRecipe menuRecipe : getMenuRecipes()) {
                if (menuRecipe.getName().toLowerCase().contains(flaCode.toLowerCase())) {
                    list.add(menuRecipe);
                }
            }
        }
        Collections.sort(list, new RecipesComparator());
        return list;
    }

}
