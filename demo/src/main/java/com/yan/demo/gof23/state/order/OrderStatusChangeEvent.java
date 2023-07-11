package com.yan.demo.gof23.state.order;

public enum OrderStatusChangeEvent {
    // 支付/发货/确认收货
    PAYED,
    DELIVERY,
    RECEIVED;
}
