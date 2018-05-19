package com.example.shubham.recipedisplaytask;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class IngredientDisplay extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.recipe_ingredients,container,false);

        String str=getArguments().get("recipe").toString();

        TextView ingredientText = (TextView) v.findViewById(R.id.textView2);

        ingredientText.setText(str);
        return v;
    }
}
