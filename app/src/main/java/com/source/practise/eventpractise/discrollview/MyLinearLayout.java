package com.source.practise.eventpractise.discrollview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.source.practise.eventpractise.R;

import androidx.annotation.Nullable;

/**
 * <p>Class: com.source.practise.eventpractise.discrollview.MyLinearLayout</p>
 * <p>Description: </p>
 * <pre>
 *
 *  </pre>
 *
 * @author lujunjie
 * @date 2019/4/22/17:31.
 */
public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context,  AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        //在child view外面包裹一层容器----偷梁换柱
       MyLayoutParams p  =(MyLayoutParams) params;

       if(!isDiscrollvable(p)){
           super.addView(child, index, params);
       }else{
            MyFrameLayout mf = new MyFrameLayout(getContext(), null);
           mf.setmDiscrollveAlpha(p.mDiscrollveAlpha);
           mf.setmDiscrollveFromBgColor(p.mDiscrollveFromBgColor);
           mf.setmDiscrollveToBgColor(p.mDiscrollveToBgColor);
           mf.setmDiscrollveScaleX(p.mDiscrollveScaleX);
           mf.setmDiscrollveScaleY(p.mDiscrollveScaleY);
           mf.setmDisCrollveTranslation(p.mDisCrollveTranslation);
           mf.addView(child);
           super.addView(mf, index, params);
       }

    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        //采花大盗---childview里面的自定义属性--->MyFrameLayout
        return new MyLayoutParams(getContext(), attrs);
    }

    private boolean isDiscrollvable(MyLayoutParams p) {
        return p.mDiscrollveAlpha||
                p.mDiscrollveScaleX||
                p.mDiscrollveScaleY||
                p.mDisCrollveTranslation!=-1||
                (p.mDiscrollveFromBgColor!=-1&&
                        p.mDiscrollveToBgColor!=-1);
    }

    private class MyLayoutParams extends LinearLayout.LayoutParams{

        public int mDiscrollveFromBgColor;//背景颜色变化开始值
        public int mDiscrollveToBgColor;//背景颜色变化结束值
        public boolean mDiscrollveAlpha;//是否需要透明度动画
        public int mDisCrollveTranslation;//平移值
        public boolean mDiscrollveScaleX;//是否需要x轴方向缩放
        public boolean mDiscrollveScaleY;//是否需要y轴方向缩放

        public MyLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            //获取自定义属性
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.DiscrollView_LayoutParams);
            mDiscrollveAlpha =  a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_alpha,false);
            mDiscrollveScaleX = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleX, false);
            mDiscrollveScaleY = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleY, false);
            mDisCrollveTranslation = a.getInt(R.styleable.DiscrollView_LayoutParams_discrollve_translation, -1);
            mDiscrollveFromBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_fromBgColor, -1);
            mDiscrollveToBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_toBgColor, -1);
            a.recycle();
        }
    }
}
