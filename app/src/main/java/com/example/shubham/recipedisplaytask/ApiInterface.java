package com.example.shubham.recipedisplaytask;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by shubham on 19-05-2018.
 */

public interface ApiInterface {

    @GET("android_task.php")
    Call<RecipeResponse> getRecipe();
}
