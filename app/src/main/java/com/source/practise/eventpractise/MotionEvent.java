package com.source.practise.eventpractise;

/**
 * <p>Class: com.source.practise.eventpractise.MotionEvent</p>
 * <p>Description: </p>
 * <pre>
 *
 *  </pre>
 *
 * @author lujunjie
 * @date 2019/4/11/14:39.
 */
public class MotionEvent {
    public static final int ACTION_DOWN  =0;
    public static final int ACTION_MOVE =1;
    public static final int ACTION_UP =2;
    public static final int ACTION_CANCEL  =3;
    private int actionMasked;
    private int x;
    private int y;
    public MotionEvent(){

    }

    public MotionEvent(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getActionMasked() {
        return actionMasked;
    }

    public void setActionMasked(int actionMasked) {
        this.actionMasked = actionMasked;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
