package com.example.shubham.recipedisplaytask;

import java.util.ArrayList;

/**
 * Created by shubham on 19-05-2018.
 */

public class RecipeResponse {

    public ArrayList<Recipe> recipe_data;

    public class Recipe{

        String id;
        String name;
        ArrayList<Ingredient> ingredient_data;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<Ingredient> getIngredient_data() {
            return ingredient_data;
        }

        public void setIngredient_data(ArrayList<Ingredient> ingredient_data) {
            this.ingredient_data = ingredient_data;
        }
    }

    public class Ingredient{
        String ingredient_id;
        String ingredient_name;

        public String getIngredient_id() {
            return ingredient_id;
        }

        public String getIngredient_name() {
            return ingredient_name;
        }

        public void setIngredient_id(String ingredient_id) {
            this.ingredient_id = ingredient_id;
        }

        public void setIngredient_name(String ingredient_name) {
            this.ingredient_name = ingredient_name;
        }
    }
}
