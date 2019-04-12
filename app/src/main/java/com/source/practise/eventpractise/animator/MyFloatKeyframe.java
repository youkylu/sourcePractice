package com.source.practise.eventpractise.animator;

/**
 * <p>Class: com.source.practise.eventpractise.animator.MyFloatKeyframe</p>
 * <p>Description: </p>
 * <pre>
 *
 *  </pre>
 *
 * @author lujunjie
 * @date 2019/4/12/10:18.
 */

//关键帧 保存着 某一时刻的具体状态    初始化的时候
public class MyFloatKeyframe {
    float mFraction;
    Class mValueType;
    float mValue;

    public MyFloatKeyframe(float fraction,float value){
        mFraction = fraction;
        mValue =value;
        mValueType = float.class;
    }

    public float getFraction() {
        return mFraction;
    }


    public float getValue() {
        return mValue;
    }

    public void setValue(float mValue) {
        this.mValue = mValue;
    }
}
