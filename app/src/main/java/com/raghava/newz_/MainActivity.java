package com.raghava.newz_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.raghava.newz_.models.API.NewsArticlesInteface;
import com.raghava.newz_.models.GenreModel;
import com.raghava.newz_.models.New_model;
import com.raghava.newz_.models.News_Articles;
import com.raghava.newz_.views.GenreAdapter;
import com.raghava.newz_.views.PaginationScrollListener;
import com.raghava.newz_.views.newsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

//    news API key : 2af1eb341e6f43e6839e842e5255f80f

    newsAdapter news_adapter;
    RecyclerView rvNews,rvGenre;
    ProgressBar progressBarLoadArticle,FullPB;
    Button retryBtn;

    private boolean isLoading=false;
    private boolean isLastPage=false;
    private static final int START_PAGE_NO=1;

    private int current_page_no=START_PAGE_NO;

    List<News_Articles> data=new ArrayList<>();
    List<GenreModel> GenreModels=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_views();

        // Genre Adapter
        fillGenreContent();
        GenreAdapter genreAdapter=new GenreAdapter(MainActivity.this,GenreModels);
        LinearLayoutManager linearLayoutManagerGenre=new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL,false);

        rvGenre.setLayoutManager(linearLayoutManagerGenre);
        rvGenre.setAdapter(genreAdapter);

        // News Adapter
        news_adapter=new newsAdapter(MainActivity.this,data);
        LinearLayoutManager linearLayoutManagerNews=new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL,false);


        rvNews.setLayoutManager(linearLayoutManagerNews);
        rvNews.setAdapter(news_adapter);

        rvNews.addOnScrollListener(new PaginationScrollListener(linearLayoutManagerNews) {
            @Override
            protected void loadNextArticles() {
                isLoading=true;
                current_page_no = current_page_no+1;
                loadNextPage();
            }

            @Override
            public boolean isLoading() {
                return isLastPage;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                load1Page();
            }
        });

    }

    public void init_views(){
        rvNews=findViewById(R.id.recyclerViewNews);
        rvGenre=findViewById(R.id.recyclerViewGenres);
        progressBarLoadArticle=findViewById(R.id.progress_circular);
        retryBtn=findViewById(R.id.retryBtn);
        FullPB=findViewById(R.id.FullPB);
    }

    private void loadNextPage(){

    }

    private void fillGenreContent(){

//        business entertainment general health science sports technology

        GenreModel business=new GenreModel("business","https://images.unsplash.com/photo-1434626881859-194d67b2b86f?q=80&w=2074&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        GenreModel entertainment=new GenreModel("entertainment","https://images.unsplash.com/photo-1470229722913-7c0e2dbbafd3?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        GenreModel general=new GenreModel("general","https://images.unsplash.com/photo-1494059980473-813e73ee784b?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        GenreModel health=new GenreModel("health","https://images.unsplash.com/photo-1532938911079-1b06ac7ceec7?q=80&w=1932&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        GenreModel science=new GenreModel("science","https://images.unsplash.com/photo-1507413245164-6160d8298b31?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        GenreModel sports=new GenreModel("sports","https://images.unsplash.com/photo-1579952363873-27f3bade9f55?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        GenreModel technology=new GenreModel("technology","https://plus.unsplash.com/premium_photo-1661963212517-830bbb7d76fc?q=80&w=1986&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");

        GenreModels.add(business);
        GenreModels.add(entertainment);
        GenreModels.add(general);
        GenreModels.add(health);
        GenreModels.add(science);
        GenreModels.add(sports);
        GenreModels.add(technology);
    }

    private void getArticlesByGenre(String Genre){
        FullPB.setVisibility(View.VISIBLE);
        data.clear();
        String baseURL="https://newsapi.org/";
        String GenreURL = "https://newsapi.org/v2/top-headlines?category=" + Genre + "&apikey=2af1eb341e6f43e6839e842e5255f80f";
        String UrlForAllArticles= "https://newsapi.org/v2/top-headlines?excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apikey=2af1eb341e6f43e6839e842e5255f80f";
        Retrofit retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).build();
        NewsArticlesInteface api =retrofit.create(NewsArticlesInteface.class);
        Call<News_Articles> call;
        if(Genre.equals("All")){
            call = api.getAllArticles(UrlForAllArticles);
        }
        else{
            call= api.getArticlesByGenre(GenreURL);
        }
        call.enqueue(new Callback<News_Articles>() {
            @Override
            public void onResponse(Call<News_Articles> call, Response<News_Articles> response) {
//                News_Articles data = response.body();
                FullPB.setVisibility(View.GONE);



            }

            @Override
            public void onFailure(Call<News_Articles> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to fetch "+ Genre + " Articles.", Toast.LENGTH_SHORT).show();
            }
        });
    }


}