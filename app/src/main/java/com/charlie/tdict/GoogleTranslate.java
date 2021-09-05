package com.charlie.tdict;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GoogleTranslate extends AppCompatActivity {

    WebView gt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_translate);

        Intent intent = getIntent();

        String url = intent.getStringExtra("url");

        gt = findViewById(R.id.webview);
        gt.loadUrl(url);
        gt.setWebViewClient(new WebViewClient());
        gt.getSettings().setJavaScriptEnabled(true);
    }
}