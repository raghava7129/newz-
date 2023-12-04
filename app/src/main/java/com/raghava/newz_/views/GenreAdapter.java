package com.raghava.newz_.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raghava.newz_.MainActivity;
import com.raghava.newz_.R;
import com.raghava.newz_.models.GenreModel;
import com.raghava.newz_.viewModel.newsViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.MyViewHolder> {

    private Context context;
    private List<GenreModel> GenreModels;
    private newsViewModel newsVM;

    public GenreAdapter(Context context, List<GenreModel> genreModels) {
        this.context = context;
        this.GenreModels = genreModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v=inflater.inflate(R.layout.genre_child,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        GenreModel model = GenreModels.get(position);
        String imageURL= model.getGenrePicUrl();
        myViewHolder.GenreName.setText(model.getGenreName());

        Picasso.get().load(imageURL).placeholder(R.drawable.ic_launcher_background)
                .into(myViewHolder.GenrePic);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fromGenreAdapter = new Intent(context, MainActivity.class);
                fromGenreAdapter.putExtra("GenreType",model.getGenreName());
                context.startActivity(fromGenreAdapter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return GenreModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView GenrePic;
        private TextView GenreName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            GenrePic=itemView.findViewById(R.id.GenreImage);
            GenreName=itemView.findViewById(R.id.GenreName);
        }
    }
}
