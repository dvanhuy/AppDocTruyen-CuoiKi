package com.example.appdoctruyen_cuoiki;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class ReadBook extends AppCompatActivity {
    ImageFilterView imageView;
    ScrollView scrollviewcontent;
    LinearLayoutCompat linearLayoutCompat;
    RelativeLayout favouritebutton,bookmarkbutton;
    int favourite = 0 ,bookmark = 0;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        scrollviewcontent = findViewById(R.id.scrollviewcontent);
        linearLayoutCompat = findViewById(R.id.linearLayoutCompat);
        scrollviewcontent.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int xold, int yold) {
                if (y- yold >= 0){
                    linearLayoutCompat.setVisibility(View.GONE);
                }
                else {
                    linearLayoutCompat.setVisibility(View.VISIBLE);
                }
            }
        });
        favouritebutton = findViewById(R.id.favouritebutton);
        ImageView imageViewstar= findViewById(R.id.imgviewstar);
        favouritebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favourite == 0){
                    imageViewstar.setImageResource(R.drawable.readbook_ic_star_yellow);
                    favourite=1;
                }
                else{
                    imageViewstar.setImageResource(R.drawable.readbook_ic_star);
                    favourite=0;
                }
            }
        });

        bookmarkbutton = findViewById(R.id.bookmarkbutton);
        ImageView imageViewbookmark= findViewById(R.id.imgviewbookmark);
        bookmarkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bookmark == 0){
                    imageViewbookmark.setImageResource(R.drawable.readbook_ic_bookmark_yellow);
                    bookmark=1;
                }
                else{
                    imageViewbookmark.setImageResource(R.drawable.readbook_ic_bookmark);
                    bookmark=0;
                }
            }
        });
    }
}