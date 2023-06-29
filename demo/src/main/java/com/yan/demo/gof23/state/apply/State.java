package com.yan.demo.gof23.state.apply;

/**
 * 提取公共部分
 */
public abstract class State implements IState{
    protected Context context;

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
