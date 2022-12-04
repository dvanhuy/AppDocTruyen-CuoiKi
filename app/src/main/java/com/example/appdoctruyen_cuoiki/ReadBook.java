package com.example.appdoctruyen_cuoiki;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReadBook extends AppCompatActivity {
    ImageFilterView imageView;
    ScrollView scrollviewcontent;
    LinearLayoutCompat linearLayoutCompat;
    RelativeLayout favouritebutton,bookmarkbutton,settingbutton,menubutton;
    int favourite = 0 ,bookmark = 0;
    String idchap,idtruyen;
    DatabaseReference databaseReference;
    TextView textcontent, namechap,numberchap;
    int textsizeg=20,giandongg=7;
    ImageView imageViewbookmark;

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
                else {
                    imageViewstar.setImageResource(R.drawable.readbook_ic_star);
                    favourite=0;
                }
            }
        });

        bookmarkbutton = findViewById(R.id.bookmarkbutton);
        imageViewbookmark= findViewById(R.id.imgviewbookmark);
        bookmarkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bookmark == 0){
                    Intent intent = new Intent(ReadBook.this,SaveBookMark.class);
                    intent.putExtra("idtruyen",idtruyen);
                    getResultBookMark.launch(intent);
                }
            }
        });

        settingbutton = findViewById(R.id.settingbutton);
        settingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendiaglog(Gravity.BOTTOM);
            }
        });

        ImageView imageButton = findViewById(R.id.imageButtonback);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        menubutton = findViewById(R.id.menubutton);
        menubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ImageView chapernext = findViewById(R.id.chapernext);
        ImageView chaperpre = findViewById(R.id.chaperpre);
        chapernext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int chapnext = Integer.parseInt(idchap.substring(6,idchap.length()))+1;
                getDataChap("chuong"+String.valueOf(chapnext));
            }
        });
        chaperpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int chappre = Integer.parseInt(idchap.substring(6,idchap.length()))-1;
                getDataChap("chuong"+String.valueOf(chappre));
            }
        });

        Intent intent = getIntent();
        idtruyen =intent.getStringExtra("truyen");
        idchap =intent.getStringExtra("chuong");
        textcontent = findViewById(R.id.textcontent);
        namechap = findViewById(R.id.namechap);
        numberchap = findViewById(R.id.numberchap);

        databaseReference = FirebaseDatabase.getInstance().getReference("Truyen");
        databaseReference.child(idtruyen).child("chuong").child(idchap).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();
                textcontent.setText(String.valueOf(dataSnapshot.child("noidung").getValue()));
                namechap.setText(String.valueOf(dataSnapshot.child("tenchuong").getValue()));
                numberchap.setText("Chương "+String.valueOf(dataSnapshot.child("sothutu").getValue()));
            }
        });

    }

    public void opendiaglog(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.settingtextdialog);
        Window window = dialog.getWindow();
        if (window == null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams winLayoutAttributes = window.getAttributes();
        winLayoutAttributes.gravity = gravity;
        window.setAttributes(winLayoutAttributes);

        if (Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        }
        else {
            dialog.setCancelable(false);
        }

        ImageView cochutru =  dialog.findViewById(R.id.cochutru);
        ImageView cochucong =  dialog.findViewById(R.id.cochucong);
        ImageView giandongtru =  dialog.findViewById(R.id.giandongtru);
        ImageView giandongcong =  dialog.findViewById(R.id.giandongcong);

        EditText editTextcochu = dialog.findViewById(R.id.editTextcochu);
        EditText editTextGiandong = dialog.findViewById(R.id.editTextGiandong);

        cochutru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textsizeg = textsizeg -1;
                editTextcochu.setText(String.valueOf(textsizeg));
                textcontent.setTextSize(TypedValue.COMPLEX_UNIT_SP,textsizeg);
            }
        });

        cochucong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textsizeg = textsizeg +1;
                editTextcochu.setText(String.valueOf(textsizeg));
                textcontent.setTextSize(TypedValue.COMPLEX_UNIT_SP,textsizeg);
            }
        });

        giandongtru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giandongg = giandongg - 1;
                editTextGiandong.setText(String.valueOf(giandongg));
                textcontent.setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, giandongg,  getResources().getDisplayMetrics()), 1.0f);
            }
        });

        giandongcong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giandongg = giandongg +1;
                editTextGiandong.setText(String.valueOf(giandongg));
                textcontent.setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, giandongg,  getResources().getDisplayMetrics()), 1.0f);
            }
        });
        dialog.show();
    }

    public void getDataChap(String idchaper){
        databaseReference.child(idtruyen).child("chuong").child(idchaper).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();
                if (dataSnapshot.exists()){
                    textcontent.setText(String.valueOf(dataSnapshot.child("noidung").getValue()));
                    namechap.setText(String.valueOf(dataSnapshot.child("tenchuong").getValue()));
                    numberchap.setText("Chương "+String.valueOf(dataSnapshot.child("sothutu").getValue()));
                    idchap = idchaper;
                }
                else{
                    Toast.makeText(ReadBook.this, "Không thể thực hiện", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private ActivityResultLauncher<Intent> getResultBookMark =registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        imageViewbookmark.setImageResource(R.drawable.readbook_ic_bookmark_yellow);
                        bookmark=1;
                    }
                    if (result.getResultCode() == Activity.RESULT_CANCELED){
                    }
                }
            }
    );
}