package com.example.shubham.recipedisplaytask;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shubham on 19-05-2018.
 */

public class DisplayFragment extends Fragment {

    ArrayList<RecipeResponse.Recipe> recipeArrayList;
    RecyclerView RV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.recipe_display,container,false);


        recipeArrayList=new ArrayList<>();
        RV=(RecyclerView) v.findViewById(R.id.list);

        final RecipeAdapter rAdapter= new RecipeAdapter(getActivity(), recipeArrayList, new RecipeAdapter.RecipeClickListener() {
            @Override
            public void OnRecipeClick(int pos, View v) {


                String ingredients="";
                for(int j=0;j<recipeArrayList.get(pos).getIngredient_data().size();j++) {
                    ingredients=ingredients+ "\n - "+recipeArrayList.get(pos).getIngredient_data().get(j).getIngredient_name();
                }

//                Intent i=new Intent(getActivity(),IngredientDisplay.class);
//                i.putExtra("recipe",ingredients);
//                startActivity(i);

                Bundle arg=new Bundle();
                arg.putString("recipe",ingredients);

                IngredientDisplay ingredientFragment= new IngredientDisplay();
                ingredientFragment.setArguments(arg);
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame,ingredientFragment).commit();

            }
        });
        RV.setAdapter(rAdapter);
        RV.setLayoutManager(new LinearLayoutManager(getActivity()));
        RV.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        RV.setItemAnimator(new DefaultItemAnimator());

        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://www.speechify.in/internship/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<RecipeResponse> call=apiInterface.getRecipe();

        call.enqueue(new Callback<RecipeResponse>() {

            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if(response.body()!=null){
                    for(int i=0;i<response.body().recipe_data.size();i++){
                        recipeArrayList.add(response.body().recipe_data.get(i));
                        rAdapter.notifyItemInserted(i);

                    }

                }
                else
                    Toast.makeText(getActivity(), "Not successful", Toast.LENGTH_SHORT).show();

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Connection Error ", Toast.LENGTH_SHORT).show();
            }
        });



        return v;
    }


}
