package com.geniusvjr.tclibrary.strategies;

import com.geniusvjr.tclibrary.debug.Debug;

/**
 * 上下文，用于策略模式
 * Created by dream on 16/1/30.
 */
public class HandlerContext {

    IStrategy strategy;

    /**
     * 构造方法
     */
    public HandlerContext(IStrategy strategy) {
        this.strategy = strategy;
    }

    //执行策略
    public void run()
    {
        if(this.strategy == null)
        {
            Debug.Log(this.getClass().getName(), "this.strategy == null");
            return;
        }
        this.strategy.run();
    }
}
