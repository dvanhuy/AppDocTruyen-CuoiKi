package com.example.appdoctruyen_cuoiki.ChiTietTruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdoctruyen_cuoiki.R;
import com.example.appdoctruyen_cuoiki.ReadBook;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ChiTietTruyen extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyViewPagerAdapter myViewPagerAdapter;
    DatabaseReference databaseReference;
    String idtruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_truyen);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.tabViewPager);
        myViewPagerAdapter = new MyViewPagerAdapter(this);
        viewPager2.setAdapter(myViewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });


        Intent intent = getIntent();
        idtruyen = intent.getStringExtra("idtruyen");

        ImageView imageButtonback = findViewById(R.id.imageButtonback);
        imageButtonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button beginRead = findViewById(R.id.BeginRead);
        beginRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ChiTietTruyen.this, ReadBook.class);
                intent1.putExtra("chuong","chuong1");
                intent1.putExtra("truyen",idtruyen);
                startActivity(intent1);
            }
        });

        ImageView imagebiaanh = findViewById(R.id.imageView5);
        TextView title = findViewById(R.id.title);
        TextView author = findViewById(R.id.author);
        TextView sochuong = findViewById(R.id.sochuongchitiet);

        databaseReference = FirebaseDatabase.getInstance().getReference("Truyen");
        databaseReference.child(idtruyen).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    DataSnapshot dataSnapshot= task.getResult();
                    Picasso.get().load(String.valueOf(dataSnapshot.child("image").getValue())).into(imagebiaanh);
                    title.setText(String.valueOf(dataSnapshot.child("tentruyen").getValue()));
                    author.setText("Tác giả: "+String.valueOf(dataSnapshot.child("tacgia").getValue()));
                    sochuong.setText(String.valueOf(dataSnapshot.child("sochuong").getValue()));
                }
            }
        });

    }
}