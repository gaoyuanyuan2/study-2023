package com.yan.demo.gof23.state.simple;

public class ConcreteStateA implements IState{
    @Override
    public void handle() {
        System.out.println("StateA do action");
    }
}
