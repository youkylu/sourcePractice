package com.source.practise.eventpractise.animator;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Class: com.source.practise.eventpractise.animator.VSYNCManager</p>
 * <p>Description: </p>
 * <pre>
 *
 *  </pre>
 *
 * @author lujunjie
 * @date 2019/4/12/11:55.
 */
public class VSYNCManager {
    private static final VSYNCManager ourInstance = new VSYNCManager();

    public static VSYNCManager getInstance(){
        return ourInstance;
    }

    private VSYNCManager(){
        new Thread(runnable).start();
    }
    public void addAnimationFrameCallBack(AnimationFrameCallBack animationFrameCallBack){
        list.add(animationFrameCallBack);
    }

    private List<AnimationFrameCallBack> list =new ArrayList<>();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while(true){

                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for(AnimationFrameCallBack callBack:list ){
                    if(callBack.doAnimationFrame(System.currentTimeMillis())){
                        break;
                    }
//                    callBack.doAnimationFrame(System.currentTimeMillis());
                }
            }
        }
    };

    interface AnimationFrameCallBack{
        boolean doAnimationFrame(long currentTime);
    }
}
