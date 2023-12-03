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

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.MyViewHolder>{

    private Context context;
    private List<News_Articles> data;

    private static final int item=0;
    private static final int loading=1;

    private boolean isLoading=false;
    private boolean errorPage=false;


    public newsAdapter(Context context, List<News_Articles> data) {
        this.data = data;
        this.context=context;
    }

    public List<News_Articles> getData() {
        return data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        MyViewHolder vh=null;

        switch (viewType){
            case item :
                View view1 =inflater.inflate(R.layout.news_child,parent,false);
                vh = new MyViewHolder(view1);
                break;

            case loading:
                View view2 = inflater.inflate(R.layout.error_page,parent,false);
                vh = new MyViewHolder(view2);
                break;
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        News_Articles article = data.get(position);

        switch(getItemViewType(position)){
            case item:
                final MyViewHolder myViewHolder = (MyViewHolder) holder;
                String imageURL= article.getUrlToImage();
                myViewHolder.Title.setText(article.getTitle());
                myViewHolder.SubTitle.setText(article.getDescription());

                Picasso.get().load(imageURL).placeholder(R.drawable.ic_launcher_background)
                        .into(myViewHolder.newsImageView);


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=new Intent(context,FullArticle.class);
                        i.putExtra("title",article.getTitle());
                        i.putExtra("description",article.getDescription());
                        i.putExtra("imageurl",article.getUrlToImage());
                        i.putExtra("url",article.getUrl());
                    }
                });

                break;

            case loading :
                errorHandling handling = null;
                handling.progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView newsImageView;
        private TextView Title;
        private TextView SubTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImageView = itemView.findViewById(R.id.newsImage);
            Title = itemView.findViewById(R.id.newsTitle);
            SubTitle = itemView.findViewById(R.id.newsSubTitle);
        }
    }

    private class errorHandling extends RecyclerView.ViewHolder{
        private ProgressBar progressBar;
        
        public errorHandling(View v){
            super(v);
            progressBar= v.findViewById(R.id.progress_circular);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position==data.size()-1 && isLoading)? loading:item;
    }

    public void add_article(News_Articles na){
        data.add(na);
        notifyItemInserted(data.size()-1);
    }

    public void addAll_articles(List<News_Articles> data){
        for(News_Articles na:data){
            add_article(na);
        }
    }

    public void addLoadingItem(){
        isLoading=true;
        add_article(new News_Articles());
    }

    public void removeLoadingItem(){
        isLoading=false;
        int position=data.size()-1;
        News_Articles na=data.get(position);

        if(na != null){
            data.remove(position);
            notifyItemRemoved(position);
        }
    }


}
