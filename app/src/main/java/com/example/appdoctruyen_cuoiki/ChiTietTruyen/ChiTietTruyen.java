package com.example.appdoctruyen_cuoiki.ChiTietTruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdoctruyen_cuoiki.ChiTietTruyen.ChiTietTruyenFragment.ChapterListFragment;
import com.example.appdoctruyen_cuoiki.ChiTietTruyen.ChiTietTruyenFragment.MoTaFragment;
import com.example.appdoctruyen_cuoiki.R;
import com.example.appdoctruyen_cuoiki.ReadBook;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ChiTietTruyen extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    DatabaseReference databaseReference;
    String idtruyen;
    String gioithieuuu="";
    int sochuonglistview=1;

    MoTaFragment moTaFragment;
    ChapterListFragment chapterListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_truyen);

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
                addDataToDatabase();
                goToReadBook("chuong1");
            }
        });

        ImageView imagebiaanh = findViewById(R.id.imageView5);
        TextView title = findViewById(R.id.title);
        TextView author = findViewById(R.id.author);
        TextView sochuong = findViewById(R.id.sochuongchitiet);
        TextView phathanh = findViewById(R.id.txtPhatHanh);
        TextView anban = findViewById(R.id.txtAnBan);
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
                    phathanh.setText(String.valueOf(dataSnapshot.child("phathanh").getValue()));
                    anban.setText(String.valueOf(dataSnapshot.child("anban").getValue()));
                    gioithieuuu = String.valueOf(dataSnapshot.child("mota").getValue());
                    sochuonglistview = Integer.parseInt(String.valueOf(dataSnapshot.child("sochuong").getValue()));
                    moTaFragment = new MoTaFragment(gioithieuuu);
                    chapterListFragment = new ChapterListFragment(sochuonglistview);
                    createBottomNav();
                }
            }
        });

    }
    public void createBottomNav(){
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.tabViewPager);

        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position){
                    case 0:
                        return moTaFragment;
                    case 1:
                        return chapterListFragment;
                    default:
                        return moTaFragment;
                }
            }

            @Override
            public int getItemCount() {
                return 2;
            }
        });

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
    }

    public void goToReadBook(String idchuong){
        Intent intent1 = new Intent(ChiTietTruyen.this, ReadBook.class);
        intent1.putExtra("chuong",idchuong);
        intent1.putExtra("truyen",idtruyen);
        startActivity(intent1);
    }

    public void addDataToDatabase(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("lichsu");
        Query query = ref.orderByValue().equalTo(idtruyen);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() == null){
                    ref.push().setValue(idtruyen);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}