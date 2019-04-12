package com.source.practise.eventpractise.animator;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Class: com.source.practise.eventpractise.animator.MyKeyframeSet</p>
 * <p>Description: </p>
 * <pre>
 *
 *  </pre>
 *
 * @author lujunjie
 * @date 2019/4/12/10:16.
 */
public class MyKeyframeSet {
    //类型估值器
    TypeEvaluator mEvaluator;
    MyFloatKeyframe mFirstKeyframe;
    List<MyFloatKeyframe> mKeyframes;


    public MyKeyframeSet(MyFloatKeyframe... myFloatKeyframes) {
        mKeyframes = Arrays.asList(myFloatKeyframes);
        mFirstKeyframe = myFloatKeyframes[0];
        mEvaluator = new FloatEvaluator();
    }

    public static MyKeyframeSet of(float[] values) {
        int numKeyframes = values.length;

        MyFloatKeyframe keyFrames[] = new MyFloatKeyframe[numKeyframes];
        keyFrames[0] = new MyFloatKeyframe(0, values[0]);
        for (int i = 1; i < numKeyframes; i++) {
            keyFrames[i] = new MyFloatKeyframe((float) i / (numKeyframes - 1), values[i]);

        }
        return new MyKeyframeSet(keyFrames);
    }

    public Object getValue(float fraction) {
        MyFloatKeyframe preKeyframe = mFirstKeyframe;
        for (int i = 0; i < mKeyframes.size(); i++) {
            MyFloatKeyframe nextKeyframe = mKeyframes.get(i);
            nextKeyframe = mKeyframes.get(i);
            if (fraction < nextKeyframe.getFraction()) {
                return mEvaluator.evaluate(fraction, preKeyframe.getValue(), nextKeyframe.getValue());
            }
            preKeyframe = nextKeyframe;
        }
        return null;
    }
}
