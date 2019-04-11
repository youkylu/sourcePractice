package com.source.practise.eventpractise;

import com.source.practise.eventpractise.listener.OnClickListener;
import com.source.practise.eventpractise.listener.OnTouchListener;

/**
 * <p>Class: com.source.practise.eventpractise.Test</p>
 * <p>Description: </p>
 * <pre>
 *
 *  </pre>
 *
 * @author lujunjie
 * @date 2019/4/11/17:50.
 */
public class Test {
    public static void main(String[] arg) {
        ViewGroup viewGroup = new ViewGroup(0, 0, 1080, 1920);
        viewGroup.setName("顶级容器");

        ViewGroup viewGroup1 = new ViewGroup(0, 0, 500, 500);
        viewGroup1.setName("第二级容器");


        View view = new View(0, 0, 200, 200);
        viewGroup1.addView(view);
        viewGroup.addView(viewGroup1);
        viewGroup.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("顶级的OnTouch事件");
                return false;
            }
        });
        viewGroup1.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("第二级容器的OnTouch事件");
                return false;
            }
        });

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void OnClick(View v) {
                System.out.println("view的onClick事件");
            }
        });
        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("view的OnTouch事件");
                return true;
            }
        });

        MotionEvent motionEvent = new MotionEvent(100, 100);
        motionEvent.setActionMasked(MotionEvent.ACTION_DOWN);

//        顶级容器分发
        viewGroup.dispatchTouchEvent(motionEvent);
    }
}
