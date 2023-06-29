package com.yan.demo.gof23.state.apply;

public class ConcreteStateB extends State {
    @Override
    public void handle() {
        System.out.println("StateB  do action");
        this.context.setState(Context.STATE_A);
        this.context.getState().handle();
    }
}
