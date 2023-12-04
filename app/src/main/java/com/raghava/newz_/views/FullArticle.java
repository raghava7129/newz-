package com.raghava.newz_.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.raghava.newz_.R;

public class FullArticle extends AppCompatActivity {

    private String title,description,imageurl,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_article);

        title=getIntent().getStringExtra("title");
        description=getIntent().getStringExtra("description");
        imageurl=getIntent().getStringExtra("urlToImage");
        url=getIntent().getStringExtra("url");

    }
}