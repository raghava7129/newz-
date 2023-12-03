package com.raghava.newz_.models.API;

import com.raghava.newz_.models.News_Articles;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NewsArticlesInteface {

    @GET
    Call<News_Articles> getAllArticles(@Url String url);

    @GET
    Call<News_Articles> getArticlesByGenre(@Url String url);

}
