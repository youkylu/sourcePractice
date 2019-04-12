package com.source.practise.eventpractise.red_book_splash;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nineoldandroids.view.ViewHelper;
import com.source.practise.eventpractise.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * <p>Class: com.source.practise.eventpractise.red_book_splash.ParallaxContainer</p>
 * <p>Description: </p>
 * <pre>
 *
 *  </pre>
 *
 * @author lujunjie
 * @date 2019/4/12/15:42.
 */
public class ParallaxContainer extends FrameLayout implements ViewPager.OnPageChangeListener {

    private List<ParallaxFragment> fragments;
    private ParallaxPagerAdapter adapter;

    private ImageView iv_man;

    public void setIv_man(ImageView iv_man) {
        this.iv_man = iv_man;
    }

    public ParallaxContainer(@NonNull Context context) {
        super(context);
    }

    public ParallaxContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallaxContainer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setUp(int... childIds) {
        //Fragment数组
        fragments = new ArrayList<>();
        for (int i = 0; i < childIds.length; i++) {
            ParallaxFragment parallaxFragment = new ParallaxFragment();

            Bundle args = new Bundle();
            //Fragment中需要加载的布局文件id
            args.putInt("layoutId", childIds[i]);
            parallaxFragment.setArguments(args);
            fragments.add(parallaxFragment);
        }
        ViewPager vp = new ViewPager(getContext());
        vp.setId(R.id.parallax_pager);
        //实例化适配器
        SplashActivity splashActivity = (SplashActivity) getContext();
        adapter = new ParallaxPagerAdapter(splashActivity.getSupportFragmentManager(), fragments);
        vp.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        vp.setAdapter(adapter);
        vp.setOnPageChangeListener(this);
        addView(vp, 0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //动画
        int containerWidth = getWidth();
        ParallaxFragment outFragment =null;

        try {
            outFragment = fragments.get(position-1);
        }catch (Exception e){}


        ParallaxFragment inFragment =null;

        try {
            inFragment = fragments.get(position);
        }catch (Exception e){}
        if(outFragment!=null){
            //获取Fragment上所有的视图，实现动画效果
            List<View> inViews = outFragment.getParallaxViews();
            if (inViews != null) {
                for (View view : inViews) {
//
                    ParallaxViewTag tag = (ParallaxViewTag) view.getTag(R.id.parallax_view_tag);
                    if (tag == null) {
                        continue;
                    }
                    ViewHelper.setTranslationX(view, (containerWidth - positionOffsetPixels) * tag.xIn);
                    ViewHelper.setTranslationY(view, (containerWidth - positionOffsetPixels) * tag.yIn);
                }

            }
        }
        if(inFragment != null){
            List<View> outViews = inFragment.getParallaxViews();
            if (outViews != null) {
                for (View view : outViews) {
                    ParallaxViewTag tag = (ParallaxViewTag) view.getTag(R.id.parallax_view_tag);
                    if (tag == null) {
                        continue;
                    }
                    //仔细观察退出的fragment中view从原始位置开始向上移动，translationY应为负数
                    ViewHelper.setTranslationY(view, 0 - positionOffsetPixels * tag.yOut);
                    ViewHelper.setTranslationX(view, 0 - positionOffsetPixels * tag.xOut);
                }
            }
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (position == adapter.getCount() - 1) {
            iv_man.setVisibility(INVISIBLE);
        } else {
            iv_man.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        AnimationDrawable animationDrawable = (AnimationDrawable) iv_man.getBackground();
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                animationDrawable.start();
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                animationDrawable.stop();
                break;
            default:
                break;
        }
    }
}
