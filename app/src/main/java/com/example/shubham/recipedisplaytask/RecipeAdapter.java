package com.example.shubham.recipedisplaytask;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by shubham on 19-05-2018.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {


    Context mContext;
    ArrayList<RecipeResponse.Recipe> mRecipes;
    RecipeClickListener rListner;

    public RecipeAdapter(Context context, ArrayList<RecipeResponse.Recipe> list, RecipeClickListener r){
        mContext=context;
        mRecipes=list;
        rListner=r;
    }


    public interface RecipeClickListener{
         void OnRecipeClick(int pos,View v);
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(mContext).inflate(R.layout.recipe_list_item,parent,false);
        return new RecipeViewHolder(v,rListner);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

        holder.recipeName.setText(mRecipes.get(position).getName().toString());

    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView recipeName;
        RecipeClickListener recipeListner;

        public RecipeViewHolder(View itemView,RecipeClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            recipeName=(TextView) itemView.findViewById(R.id.recipe_name);
            recipeListner=listener;
        }

        @Override
        public void onClick(View view) {

            int position= getAdapterPosition();
            recipeListner.OnRecipeClick(position,view);
            
        }
    }
}
