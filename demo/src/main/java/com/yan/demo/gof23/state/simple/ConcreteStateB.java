package com.yan.demo.gof23.state.simple;

public class ConcreteStateB implements IState{
    @Override
    public void handle() {
        System.out.println("StateB  do action");
    }
}
