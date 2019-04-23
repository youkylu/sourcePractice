package com.source.practise.eventpractise.discrollview;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <p>Class: com.source.practise.eventpractise.discrollview.MyFrameLayout</p>
 * <p>Description: </p>
 * <pre>
 *
 *  </pre>
 *
 * @author lujunjie
 * @date 2019/4/22/17:46.
 */
public class MyFrameLayout extends FrameLayout implements DiscrollInterface {

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

//定义很多的自定义属性
    /**
     * <attr name="discrollve_translation">
     * <flag name="fromTop" value="0x01" />
     * <flag name="fromBottom" value="0x02" />
     * <flag name="fromLeft" value="0x04" />
     * <flag name="fromRight" value="0x08" />
     * </attr>
     * 0000000001
     * 0000000010
     * 0000000100
     * 0000001000
     * top|left
     * 0000000001 top
     * 0000000100 left 或运算 |
     * 0000000101
     * 反过来就使用& 与运算 (判断出了是否用了那个动画)
     */
    private static final int TRANSLATION_FROM_TOP = 0x01;
    private static final int TRANSLATION_FROM_BOTTOM = 0x02;
    private static final int TRANSLATION_FROM_LEFT = 0x04;
    private static final int TRANSLATION_FROM_RIGHT = 0x08;

    //颜色估值器
    private static ArgbEvaluator sArgbEvaluator = new ArgbEvaluator();
    /**
     * 自定义属性的一些接收的变量
     */
    private int mDiscrollveFromBgColor;//背景颜色变化开始值
    private int mDiscrollveToBgColor;//背景颜色变化结束值
    private boolean mDiscrollveAlpha;//是否需要透明度动画
    private int mDisCrollveTranslation;//平移值
    private boolean mDiscrollveScaleX;//是否需要x轴方向缩放
    private boolean mDiscrollveScaleY;//是否需要y轴方向缩放
    private int mHeight;//本view的高度
    private int mWidth;//宽度


    public void setmDiscrollveFromBgColor(int mDiscrollveFromBgColor) {
        this.mDiscrollveFromBgColor = mDiscrollveFromBgColor;

    }

    public void setmDiscrollveToBgColor(int mDiscrollveToBgColor) {
        this.mDiscrollveToBgColor = mDiscrollveToBgColor;
    }

    public void setmDiscrollveAlpha(boolean mDiscrollveAlpha) {
        this.mDiscrollveAlpha = mDiscrollveAlpha;
    }

    public void setmDisCrollveTranslation(int mDisCrollveTranslation) {
        this.mDisCrollveTranslation = mDisCrollveTranslation;
    }

    public void setmDiscrollveScaleX(boolean mDiscrollveScaleX) {
        this.mDiscrollveScaleX = mDiscrollveScaleX;
    }

    public void setmDiscrollveScaleY(boolean mDiscrollveScaleY) {
        this.mDiscrollveScaleY = mDiscrollveScaleY;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
//		onResetDiscroll();
    }

    @Override
    public void onDiscroll(float ratio) {
        //判断是否有动画的属性，开启动画
        //ratio:0~1
        if (mDiscrollveAlpha) {
            setAlpha(ratio);
        }
        if (mDiscrollveScaleX) {
            setScaleX(ratio);
        }
        if (mDiscrollveScaleY) {
            setScaleY(ratio);
        }
        //平移---int值： left，right，top，bottom，   left|bottom
        if (isTranslationFrom(TRANSLATION_FROM_BOTTOM)) { //是否是fromBottom
            setTranslationY(mHeight * (1 - ratio)); //height-->0 (0代表原来的位置)
        }

        if (isTranslationFrom(TRANSLATION_FROM_TOP)) {//从顶部平移进来
            setTranslationY(-mHeight * (1 - ratio));//-height--->0
        }

        if (isTranslationFrom(TRANSLATION_FROM_LEFT)) {
            setTranslationX(-mWidth * (1 - ratio)); //mWidth--->0(0代表恢复到本来原来的位置)
        }
        if (isTranslationFrom(TRANSLATION_FROM_RIGHT)) {
            setTranslationX(mWidth * (1 - ratio));//-mWidth--->0(0代表恢复到本来原来的位置)
        }
        //判断从什么颜色到什么颜色
        if (mDiscrollveFromBgColor != -1 && mDiscrollveToBgColor != -1) {
            setBackgroundColor((int) sArgbEvaluator.evaluate(ratio, mDiscrollveFromBgColor, mDiscrollveToBgColor));
        }
    }

    private boolean isTranslationFrom(int translationMask) {
        if (mDisCrollveTranslation == -1) {
            return false;
        }
        return (mDisCrollveTranslation & translationMask) == translationMask;
    }

    @Override
    public void onResetDiscroll() {
        int ratio = 0;
        //ratio:0~1
        if (mDiscrollveAlpha) {
            setAlpha(ratio);
        }
        if (mDiscrollveScaleX) {
            setScaleX(ratio);
        }
        if (mDiscrollveScaleY) {
            setScaleY(ratio);
        }
        //平移---int值： left，right，top，bottom，   left|bottom
        if(isTranslationFrom(TRANSLATION_FROM_BOTTOM)){//是否是fromBottom
            setTranslationY(mHeight*(1-ratio));//height-->0 (0代表原来的位置)
        }
        if(isTranslationFrom(TRANSLATION_FROM_TOP)){//从顶部平移进来
            setTranslationY(-mHeight*(1-ratio));//-height--->0
        }
        if(isTranslationFrom(TRANSLATION_FROM_LEFT)){
            setTranslationX(-mWidth*(1-ratio));//mWidth--->0(0代表恢复到本来原来的位置)
        }
        if(isTranslationFrom(TRANSLATION_FROM_RIGHT)){
            setTranslationX(mWidth*(1-ratio));//-mWidth--->0(0代表恢复到本来原来的位置)
        }
        //判断从什么颜色到什么颜色
        if(mDiscrollveFromBgColor!=-1&&mDiscrollveToBgColor!=-1){
            setBackgroundColor((int) sArgbEvaluator.evaluate(ratio, mDiscrollveFromBgColor, mDiscrollveToBgColor));
        }
    }
}
