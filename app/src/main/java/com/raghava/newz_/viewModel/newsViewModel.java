package com.raghava.newz_.viewModel;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.raghava.newz_.MainActivity;
import com.raghava.newz_.R;
import com.raghava.newz_.models.API.NewsArticlesInteface;
import com.raghava.newz_.models.New_model;
import com.raghava.newz_.models.News_Articles;
import com.raghava.newz_.views.newsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class newsViewModel {
    private String Genre;
    private ArrayList<News_Articles> data;
    private Context context;
    private ProgressBar FullPB;

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public newsViewModel(Context context, ArrayList<News_Articles> data, ProgressBar FullPB) {
        this.data = data != null ? data : new ArrayList<>();
        this.context = context;
        this.FullPB=FullPB;
    }

    public void loadNextPage(int current_page,boolean is_lastPage,boolean is_loading,String Genre){
        if (is_loading || is_lastPage) {
            return;
        }

        getArticlesByGenre(Genre,current_page);
    }

    public void getArticlesByGenre(String Genre,int current_page) {

        if (Genre == null) {
            Toast.makeText(context, "Genre is null", Toast.LENGTH_SHORT).show();
            return;
        }

        FullPB.setVisibility(View.VISIBLE);
        data.clear();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();

            String baseURL = "https://newsapi.org/";
            String GenreURL = "https://newsapi.org/v2/top-headlines?category=" + Genre + "&apikey=2af1eb341e6f43e6839e842e5255f80f";
            String UrlForAllArticles = "https://newsapi.org/v2/top-headlines?excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apikey=2af1eb341e6f43e6839e842e5255f80f";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build();
            NewsArticlesInteface api = retrofit.create(NewsArticlesInteface.class);

            Call<New_model> call;
            if (Genre.equals("All")) {
                call = api.getAllArticles(UrlForAllArticles,current_page);
            } else {
                call = api.getArticlesByGenre(GenreURL,current_page);
            }
            call.enqueue(new Callback<New_model>() {
                @Override
                public void onResponse(Call<New_model> call, Response<New_model> response) {
                    FullPB.setVisibility(View.GONE);

                    System.out.println("*********************"+ "Response Code: " + response.code());

                    if(response.isSuccessful()){
                        New_model newsModel =response.body();

                        if(newsModel !=null ){
                            data.clear();

                            FullPB.setVisibility(View.GONE);
                            ArrayList<News_Articles> articles = newsModel.getData();
//                            Toast.makeText(context, "#articles: "+articles.size(), Toast.LENGTH_SHORT).show();

                            if(articles != null){
                                for (int i = 0; i < articles.size(); i++) {
                                    data.add(new News_Articles(articles.get(i).getTitle(),
                                            articles.get(i).getDescription(), articles.get(i).getUrlToImage(),
                                            articles.get(i).getUrl(), articles.get(i).getContent()));
                                }
                            }
                        }
                        else{
                            Toast.makeText(context, "No Data : ", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(context, "Unsuccessful request, status Code : "+ response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<New_model> call, Throwable t) {

                    Log.e("Retrofit", "Error: " + t.getMessage(), t);
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                    if (!Genre.equals("All"))
                        Toast.makeText(context, "Failed to fetch " + Genre + " Articles.", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(context, "Failed to fetch articles-failure", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(context, "NO Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }
}
