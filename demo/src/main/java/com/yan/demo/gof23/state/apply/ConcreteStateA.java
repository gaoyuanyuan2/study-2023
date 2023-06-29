package com.yan.demo.gof23.state.apply;

public class ConcreteStateA extends State {
    @Override
    public void handle() {
        System.out.println("StateA do action");
        this.context.setState(Context.STATE_B);
        this.context.getState().handle();
    }
}
