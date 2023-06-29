package com.yan.demo.gof23.state.apply;

public interface IState {
    void handle();

    void setContext(Context context);
}
