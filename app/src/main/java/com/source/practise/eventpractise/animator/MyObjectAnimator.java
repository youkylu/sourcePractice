package com.source.practise.eventpractise.animator;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * <p>Class: com.source.practise.eventpractise.animator.MyObjectAnimator</p>
 * <p>Description: </p>
 * <pre>
 *
 *  </pre>
 *
 * @author lujunjie
 * @date 2019/4/12/9:35.
 */
public class MyObjectAnimator implements VSYNCManager.AnimationFrameCallBack{
    public static final String TAG ="tuch";
    private long mDuration=0;
    private WeakReference<View> target;
    MyFloatPropertyValuesHolder myFloatPropertyValuesHolder;
    long mStartTime = -1;
    private float index = 0;

    public TimeInterpolator interpolator;

    public void setDuration(int duration){
        this.mDuration =duration;
    }

    public void setInterpolator(TimeInterpolator timeInterpolator){
        this.interpolator  =timeInterpolator;
    }

    private MyObjectAnimator(View view, String propertyName, float... values){
        target = new WeakReference<View>(view);
        myFloatPropertyValuesHolder = new MyFloatPropertyValuesHolder(propertyName,values);
    }

    public static MyObjectAnimator ofFloat(View view, String propertyName, float... values){
        MyObjectAnimator animator = new MyObjectAnimator( view,  propertyName,  values);
        return  animator;
    }


    public void start(){
        myFloatPropertyValuesHolder.setupSetter(target );
        mStartTime = System.currentTimeMillis();
        VSYNCManager.getInstance().addAnimationFrameCallBack(this);
    }

    @Override
    public boolean doAnimationFrame(long currentTime) {
        float total = mDuration/16;
        //        执行百分比
        float fraction =  (index++)/total;
        if(interpolator!=null){
            fraction = interpolator.getInterpolator(fraction);
        }
        if(index>=total){
            index = 0;
//            return true;
        }
        myFloatPropertyValuesHolder.setAnimatedValue(target.get(),fraction);

        return false;
    }
}
