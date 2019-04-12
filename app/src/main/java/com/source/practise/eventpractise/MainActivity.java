package com.source.practise.eventpractise;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.source.practise.eventpractise.animator.LinearInterpolator;
import com.source.practise.eventpractise.animator.MyObjectAnimator;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
     Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.main_btn);

    }

    public void scale(View view) {

        MyObjectAnimator animator = MyObjectAnimator.ofFloat(button, "scaleX", 1f, 2f);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(3000);
        animator.start();
    }
}
