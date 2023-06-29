package com.yan.demo.gof23.state.login;

public class AppContext {
    public static final UserState STATE_LOGIN = new LoginState();
    public static final UserState STATE_UNLOGIN = new UnLoginState();
    private  UserState userState = STATE_UNLOGIN;
    {
        STATE_LOGIN.setContext(this);
        STATE_UNLOGIN.setContext(this);
    }
    public void setState(UserState state) {
        this.userState = state;
    }

    public UserState getState() {
        return userState;
    }

    public void favorite() {
        this.userState.favorite();
    }

    public void comment(String comment) {
        this.userState.comment(comment);
    }
}
