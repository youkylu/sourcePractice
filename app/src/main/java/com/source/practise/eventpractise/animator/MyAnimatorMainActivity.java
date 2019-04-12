package com.source.practise.eventpractise.animator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.source.practise.eventpractise.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * <p>Class: com.source.practise.eventpractise.animator.MyAnimatorMainActivity</p>
 * <p>Description: </p>
 * <pre>
 *
 *  </pre>
 *
 * @author lujunjie
 * @date 2019/4/12/15:04.
 */
public class MyAnimatorMainActivity extends AppCompatActivity {
    Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_main);
        button = findViewById(R.id.main_btn);

    }

    public void scale(View view) {
        MyObjectAnimator animator = MyObjectAnimator.ofFloat(button, "scaleX", 1f, 2f);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(3000);
        animator.start();
    }
}
