package com.raghava.newz_.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raghava.newz_.R;
import com.raghava.newz_.models.News_Articles;

import java.util.List;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.MyViewHolder>{

    private Context context;
    private List<News_Articles> data;

    public newsAdapter(Context context, List<News_Articles> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newsChildView = LayoutInflater.from(context).inflate(R.layout.news_child,parent,false);

        MyViewHolder vh=new MyViewHolder(newsChildView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        News_Articles article = data.get(position);
        holder.Title.setText(article.getTitle());
        holder.SubTitle.setText(article.getSubTitle());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
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

}
