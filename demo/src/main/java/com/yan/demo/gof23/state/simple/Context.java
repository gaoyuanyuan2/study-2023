package com.yan.demo.gof23.state.simple;

/**
 * 总控
 */
public class Context {
    private static final IState STATE_A = new ConcreteStateA();
    private static final IState STATE_B = new ConcreteStateB();
    //默认状态A
    private IState currentState = STATE_A;

    public void setState(IState currentState) {
        this.currentState = currentState;
    }

    public void handle() {
        this.currentState.handle();
    }
}
