package com.yan.demo.gof23.state.login;

public abstract class UserState {

    protected AppContext context;

    public void setContext(AppContext context) {
        this.context = context;
    }

    /**
     * 收藏
     */
    public abstract void favorite();

    /**
     * 评论
     */
    public abstract void comment(String comment);
}
