package com.source.practise.eventpractise;

import com.source.practise.eventpractise.listener.OnClickListener;
import com.source.practise.eventpractise.listener.OnTouchListener;

/**
 * <p>Class: com.source.practise.eventpractise.View</p>
 * <p>Description: </p>
 * <pre>
 *
 *  </pre>
 *
 * @author lujunjie
 * @date 2019/4/11/14:37.
 */
public class View {

    private int left;
    private int top;
    private int right;
    private int bottom;

    private OnTouchListener mOnTouchListener;
    private OnClickListener mOnClickListener;

    public void setOnTouchListener(OnTouchListener onTouchListener){
        mOnTouchListener = onTouchListener;
    }

    public void setOnClickListener(OnClickListener onClickListener){
        mOnClickListener = onClickListener;
    }

    public View(){

    }

    public View(int left, int top, int right, int bottom ){
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public boolean isContainer(int x, int y){
        if(x>=left && x<=right && y>=top&& y<=bottom){
            return true;
        }
        return false;
    }

   public boolean dispatchTouchEvent(MotionEvent event){
     //消费
       System.out.println(" view  dispatchTouchEvent ");
        boolean result =false;
        if(mOnTouchListener!=null&& mOnTouchListener.onTouch(this,event)){
            result = true;
        }
        if(!result &&onTouchEvent(event)  ){
            result = true;
        }

        return result;
    }

    private boolean onTouchEvent(MotionEvent event){
        if(mOnClickListener!=null){
            mOnClickListener.OnClick(this);
            return true;
        }
        return  false;
    }

}
