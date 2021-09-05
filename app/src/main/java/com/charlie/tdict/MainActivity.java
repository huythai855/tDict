package com.charlie.tdict;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imgLogo;
    ImageView btn_add, btn_addtogr, btn_gt, btn_show, btn_cam;
    TextView txtAuthor;

    void init(){
        btn_add = findViewById(R.id.btn_add);
        btn_addtogr = findViewById(R.id.btn_addtogr);
        btn_gt = findViewById(R.id.btn_gt);
        btn_cam = findViewById(R.id.btn_cam);
        btn_show = findViewById(R.id.btn_show);
        txtAuthor = findViewById(R.id.txtAuthor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btn_gt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GoogleTranslate.class);
                intent.putExtra("url", "https://translate.google.com/?hl=vi");
                startActivity(intent);
            }
        });

        btn_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GoogleTranslate.class);
                intent.putExtra("url", "https://dictionary.cambridge.org/vi/");
                startActivity(intent);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add.class);
                startActivity(intent);
            }
        });

        btn_addtogr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddToGroup.class);
                startActivity(intent);
            }
        });

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowGroup.class);
                startActivity(intent);
            }
        });

        txtAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GoogleTranslate.class);
                intent.putExtra("url", "https://huythai855.carrd.co/");
                startActivity(intent);
            }
        });


    }
}