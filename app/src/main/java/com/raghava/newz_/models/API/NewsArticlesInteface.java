package com.raghava.newz_.models.API;

import com.raghava.newz_.models.New_model;
import com.raghava.newz_.models.News_Articles;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface NewsArticlesInteface {

    @GET
    Call<New_model> getAllArticles(@Url String url,
                                   @Query("page") int page
    );

    @GET
    Call<New_model> getArticlesByGenre(@Url String url,@Query("page") int page);

}
