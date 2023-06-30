package com.yan.demo.gof23.state.order;

public enum OrderStatus {
    //待支付，待发货，待收货，订单结束
    WAIT_PAYMENT,
    WAIT_DELIVER,
    WAIT_RECEIVE,
    FINISHED;
}
