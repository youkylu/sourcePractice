package com.source.practise.eventpractise.animator;

import android.view.View;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>Class: com.source.practise.eventpractise.animator.MyFloatPropertyValuesHolder</p>
 * <p>Description: </p>
 * <pre>
 *
 *  </pre>
 *
 * @author lujunjie
 * @date 2019/4/12/9:51.
 */
public class MyFloatPropertyValuesHolder {
    //属性名
    String mPropertyName;
    //float
    Class mValueType;
    MyKeyframeSet myKeyframeSet;
    Method  mSetter = null;

    public MyFloatPropertyValuesHolder(String propertyName, float... values){
        this.mPropertyName = propertyName;
        mValueType = float.class;
        myKeyframeSet = MyKeyframeSet.of(values);

    }

    public void setupSetter(WeakReference<View> target){
        char firstLetter = Character.toUpperCase(mPropertyName.charAt(0));
        String theRest = mPropertyName.substring(1);
        String methodName = "set"+ firstLetter+ theRest;

        try {
            mSetter = View.class.getMethod(methodName, float.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void setAnimatedValue(View target, float fraction){
        Object value = myKeyframeSet.getValue(fraction);

        try {
            mSetter.invoke(target, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
