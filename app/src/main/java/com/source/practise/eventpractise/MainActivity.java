package com.source.practise.eventpractise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.source.practise.eventpractise.animator.MyAnimatorMainActivity;
import com.source.practise.eventpractise.red_book_splash.ReadBookSplashActivity;
import com.source.practise.eventpractise.red_book_splash.SplashActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button , redBookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.main_btn);
        redBookBtn = findViewById(R.id.red_book_splash);
        initListener();
    }

    private void initListener() {
        button.setOnClickListener(this);
        redBookBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn:
                startActivity(new Intent(this, MyAnimatorMainActivity.class));
                break;
            case R.id.red_book_splash:
                startActivity(new Intent(this, SplashActivity.class));
                break;
            default:
                break;
        }
    }
}
