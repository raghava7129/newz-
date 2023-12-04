package com.raghava.newz_.views;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.raghava.newz_.R;
import com.raghava.newz_.models.News_Articles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class newsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private ArrayList<News_Articles> data;

    private static final int item=0;
    private static final int loading=1;

    private boolean isLoading=false;
    private boolean errorPage=false;


    public newsAdapter(Context context, ArrayList<News_Articles> data) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        MyViewHolder vh=null;

        switch (viewType){
            case item :
                View view1 =inflater.inflate(R.layout.news_child,parent,false);
                vh = new MyViewHolder(view1);
                break;

            case loading:
                View view2 = inflater.inflate(R.layout.loading_news_item,parent,false);
                vh = new MyViewHolder(view2);
                break;
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        News_Articles article = data.get(position);

        switch(getItemViewType(position)){
            case item:
                final MyViewHolder myViewHolder = (MyViewHolder) holder;
                String imageURL= article.getUrlToImage();
                myViewHolder.title.setText(article.getTitle());
                myViewHolder.description.setText(article.getDescription());

                Picasso.get().load(imageURL).placeholder(R.drawable.ic_launcher_background)
                        .into(myViewHolder.urlToImage);


                myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=new Intent(context,FullArticle.class);
                        i.putExtra("title",article.getTitle());
                        i.putExtra("description",article.getDescription());
                        i.putExtra("urlToImage",article.getUrlToImage());
                        i.putExtra("url",article.getUrl());
                        context.startActivity(i);
                    }
                });

                break;

            case loading :
               final loadHandling handling = (loadHandling) holder;
                handling.progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView urlToImage;
        private TextView title;
        private TextView description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            urlToImage = itemView.findViewById(R.id.newsImage);
            title = itemView.findViewById(R.id.newsTitle);
            description = itemView.findViewById(R.id.newsSubTitle);
        }
    }

    private class loadHandling extends RecyclerView.ViewHolder{
        private ProgressBar progressBar;
        
        public loadHandling(View v){
            super(v);
            progressBar= v.findViewById(R.id.progress_circular);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position <= data.size()-1 && isLoading)? loading:item;
    }

}
