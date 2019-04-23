package com.source.practise.eventpractise.discrollview;

/**
 * <p>Class: com.source.practise.eventpractise.discrollview.DiscrollInterface</p>
 * <p>Description: </p>
 * <pre>
 *
 *  </pre>
 *
 * @author lujunjie
 * @date 2019/4/22/18:06.
 */
public interface DiscrollInterface {
    /**
     * 当滑动的时候调用该方法，用来控制里面的控件执行动画
     * @param ratio
     */
    public void onDiscroll(float ratio);

    /**
     * 重置动画
     */
    public void onResetDiscroll();
}
