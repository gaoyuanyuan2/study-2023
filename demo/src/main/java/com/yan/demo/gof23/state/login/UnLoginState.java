package com.yan.demo.gof23.state.login;

import static com.yan.demo.gof23.state.login.AppContext.STATE_LOGIN;

public class UnLoginState extends UserState {
    @Override
    public void favorite() {
        this.switch2Login();
        this.context.getState().favorite();
    }

    private void switch2Login() {
        System.out.println("跳转导登陆页面");
        this.context.setState(STATE_LOGIN);
    }

    @Override
    public void comment(String comment) {
        this.switch2Login();
        this.context.getState().comment(comment);
    }
}
