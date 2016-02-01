package com.geniusvjr.tclibrary.handlers;

import android.os.Handler;
import android.os.Message;

import com.geniusvjr.tclibrary.debug.Debug;
import com.geniusvjr.tclibrary.strategies.HandlerContext;
import com.geniusvjr.tclibrary.strategies.IStrategy;

import static com.geniusvjr.tclibrary.debug.Debug.Log;

/**
 * 用策略实现的Handler
 * Created by dream on 16/1/30.
 */
public class StrategyHandler extends Handler{

    @Override
    public void handleMessage(Message msg) {
        if(msg.obj instanceof IStrategy)
        {
            IStrategy strategy = null;
            try
            {
                strategy = (IStrategy) msg.obj;
            }catch (ClassCastException e)
            {
                Log(this.getClass().getName(), "强制类型转换时出错。");
            }
            HandlerContext context = new HandlerContext(strategy);
            context.run();
        }
        super.handleMessage(msg);
    }

    /**
     * 执行一个策略
     */
    public void execStrategy(IStrategy strategy)
    {
        if(strategy == null)
        {
            return;
        }
        Message msg = Message.obtain();
        msg.obj = strategy;
        Debug.Log(this.getClass().getName(), "执行");
        sendMessage(msg);
    }
}
