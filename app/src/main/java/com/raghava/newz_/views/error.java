package com.raghava.newz_.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.raghava.newz_.MainActivity;
import com.raghava.newz_.R;
import com.raghava.newz_.viewModel.newsViewModel;

public class error extends AppCompatActivity {

    Button retryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_page);

        initViews();


        boolean isLoading=getIntent().getBooleanExtra("isLoading",false);

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLoading) return;
                else{
                    Intent i=new Intent(error.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });

    }

    private void initViews() {
        retryBtn=findViewById(R.id.retryBtn);
    }

}
