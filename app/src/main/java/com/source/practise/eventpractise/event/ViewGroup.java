package com.source.practise.eventpractise.event;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Class: com.source.practise.eventpractise.event.ViewGroup</p>
 * <p>Description: </p>
 * <pre>
 *
 *  </pre>
 *
 * @author lujunjie
 * @date 2019/4/11/14:36.
 */
public class ViewGroup extends View {

    List<View> mChildList = new ArrayList<>();
    private View[] mChildren = new View[0];
    private TouchTarget mFirstTouchTarget;

    public ViewGroup(int left, int top, int right, int bottom) {
        super(left, top, right, bottom);
    }

    public void addView(View view) {
        if (view == null) {
            return;
        }
        mChildList.add(view);
        mChildren = mChildList.toArray(new View[mChildList.size()]);
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        System.out.println(name+" dispatchTouchEvent ");
        boolean handled = false;
        boolean intercepted = OnInterceptTouchEvent(event);
        //TouchTarget  模式 内存缓存   move up
        TouchTarget newTouchTarget = null;


        int actionMasked = event.getActionMasked();
        if (actionMasked != MotionEvent.ACTION_CANCEL && !intercepted) {

            //down事件
            if (actionMasked == MotionEvent.ACTION_DOWN) {
                final View[] children = mChildren;

                for (int i = children.length - 1; i >= 0; i--) {
                    View child = mChildren[i];
                    //view 能够接受事件
                    if (!isContainer(event.getX(), event.getY())) {
                        continue;
                    }
                    //能够接受事件  child   分发给他
                    if (dispatchTransformedTouchEvent(event, child)) {
//                        View[]  采取了 Message 的方式进行  链表结构
                        handled = true;
                        newTouchTarget = addTouchTarget(child);
                        break;
                    }
                }
            }
// 当前的ViewGroup  dispatchTransformedTouchEvent
            if (mFirstTouchTarget == null) {
                handled = dispatchTransformedTouchEvent(event, null);
            }
        }


        return handled;
    }

    private TouchTarget addTouchTarget(View child) {
        final TouchTarget target = TouchTarget.obtain(child);
        target.next = mFirstTouchTarget;
        mFirstTouchTarget = target;
        return target;
    }

    private static final class TouchTarget {
        public View child; //当前缓存的view
        //回收池(一个对象)
        private static TouchTarget sRecycleBean;
        private static final Object sRecycleLock = new Object[0];
        private TouchTarget next;
        private static int sRecycleCount;

        public static TouchTarget obtain(View child) {
            TouchTarget target;
            synchronized (sRecycleLock) {
                if (sRecycleBean == null) {
                    target = new TouchTarget();
                } else {
                    target = sRecycleBean;
                }
                sRecycleBean = target.next;
                sRecycleCount--;
                target.next = null;
            }
            target.child = child;
            return target;
        }

        public void Recycle() {
            if (child == null) {
                throw new IllegalStateException("已经被回收了");
            }
            synchronized (sRecycleLock) {
                if (sRecycleCount < 32) {
                    next = sRecycleBean;
                    sRecycleBean = this;
                    sRecycleCount += 1;
                }
            }
        }
    }


    //分发处理 子控件  View
    private boolean dispatchTransformedTouchEvent(MotionEvent event, View child) {
        final boolean handled;
        if(child!=null){
            handled = child.dispatchTouchEvent(event);
        }else{
            handled = super.dispatchTouchEvent(event);
        }


        return handled;

    }

    private boolean OnInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    private String name;

    @Override
    public String toString() {
        return ""+name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
