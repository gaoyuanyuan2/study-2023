package com.yan.demo.gof23.state.apply;

/**
 * 总控
 */
public class Context {
    public static final IState STATE_A = new ConcreteStateA();
    public static final IState STATE_B = new ConcreteStateB();
    //默认状态A
    private IState currentState = STATE_A;

    public void setState(IState currentState) {
        this.currentState = currentState;
        this.currentState.setContext(this);
    }

    public void handle() {
        this.currentState.handle();
    }

    public IState getState() {
        return this.currentState;
    }
}
