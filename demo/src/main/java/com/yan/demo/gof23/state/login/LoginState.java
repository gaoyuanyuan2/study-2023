package com.yan.demo.gof23.state.login;

public class LoginState extends UserState {
    @Override
    public void favorite() {
        System.out.println("收藏成功");
    }

    @Override
    public void comment(String comment) {
        System.out.println("评论成功：" + comment);
    }

}
