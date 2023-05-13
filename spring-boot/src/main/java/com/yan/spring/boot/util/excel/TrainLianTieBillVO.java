package com.yan.spring.boot.util.excel;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

/**
 * 连铁账单数据
 *
 * @author : Y
 * @since 2022/7/13 11:42
 */
public class TrainLianTieBillVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 交易时间
     */
    @ExcelProperty("交易时间")
    private String tradeTime;
    /**
     * 订单号
     */
    @ExcelProperty("订单号")
    private String orderNo;
    /**
     * 我司订单号
     */
    @ExcelProperty("我司订单号")
    private String supplierNo;
    /**
     * 充值金额
     */
    @ExcelProperty("充值金额")
    private String rechargeAmount;
    /**
     * 出票金额
     */
    @ExcelProperty("出票金额")
    private String ticketAmount;
    /**
     * 出票张数
     */
    @ExcelProperty("出票张数")
    private String ticketCount;
    /**
     * 退票金额
     */
    @ExcelProperty("退票金额")
    private String refundAmount;
    /**
     * 退票张数
     */
    @ExcelProperty("退票张数")
    private String refundCount;
    /**
     * 改签差额
     */
    @ExcelProperty("改签差额")
    private String changeAmount;
    /**
     * 改签张数
     */
    @ExcelProperty("改签张数")
    private String changeCount;
    /**
     * 交易类型
     */
    @ExcelProperty("交易类型")
    private String tradeType;
    /**
     * 交易日期
     */
    @ExcelProperty("交易日期")
    private String tradeDate;
    /**
     * 合作方
     */
    @ExcelProperty("合作方")
    private String partner;
    /**
     * 取票号
     */
    @ExcelProperty("取票号")
    private String ticketNo;
    /**
     * 服务费
     */
    @ExcelProperty("服务费")
    private String serviceFee;

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSupplierNo() {
        return supplierNo;
    }

    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo;
    }

    public String getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(String rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public String getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(String ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public String getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(String ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(String refundCount) {
        this.refundCount = refundCount;
    }

    public String getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(String changeAmount) {
        this.changeAmount = changeAmount;
    }

    public String getChangeCount() {
        return changeCount;
    }

    public void setChangeCount(String changeCount) {
        this.changeCount = changeCount;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }
}