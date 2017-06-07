package ddd.demo.domain.order.model;

import easy.domain.base.BrokenRuleMessage;
import easy.domain.base.EntityBase;
import easy.domain.base.IAggregateRoot;
import org.joda.time.DateTime;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lil.yang on 2017/6/4.
 * 订单实体类
 */
public class Order extends EntityBase<Long> implements IAggregateRoot {

    /**
     * 订单总金额
     */
    private BigDecimal totalPrice;
    /**
     * 实际支付金额
     */
    private BigDecimal payPrice;
    /**
     * 订单生成时间
     */
    private DateTime createTime;
    /**
     * 发货时间
     */
    private DateTime deliveryTime;
    /**
     * 订单出库时间
     */
    private DateTime outTime;
    /**
     * 订单确认时间
     */
    private DateTime confirmTime;
    /**
     * 订单完成时间
     */
    private DateTime completeTime;
    /**
     * 订单取消时间
     */
    private DateTime cancelTime;
    /**
     * 订单状态
     */
    private OrderStatus orderStatus;
    /**
     * 订单优惠金额
     */
    private BigDecimal discountPrice;
    /**
     * 订单明细
     */
    private List<OrderItem> items;
    /**
     * 下单用户ID
     */
    private int userId;
    /**
     * 商家信息
     */
    private VendorInfo vendorInfo;

    /**
     * 收货地址
     */
    private DeliveryAddressInfo deliveryAddressInfo;

    public Order(long orderId, BigDecimal totalPrice, BigDecimal discountPrice, BigDecimal payPrice, int userId, VendorInfo vendorInfo, List<OrderItem> items, DeliveryAddressInfo deliveryAddressInfo) {
        this.setId(orderId);
        this.setTotalPrice(totalPrice);
        this.setDiscountPrice(discountPrice);
        this.setPayPrice(payPrice);
        this.setUserId(userId);
        this.setVendorInfo(vendorInfo);
        this.setItems(items);
        this.setDeliveryAddressInfo(deliveryAddressInfo);
        this.setCreateTime(DateTime.now());
        this.setOrderStatus(OrderStatus.ReadyConfirm);
    }

    /**
     * 订单确认
     */
    public void confirm(){
        this.setOrderStatus(OrderStatus.Confirm);
        this.setConfirmTime(DateTime.now());
    }

    /**
     * 订单出库
     */
    public void out() {
        this.setOutTime(DateTime.now());
        this.setOrderStatus(OrderStatus.Out);
    }

    /**
     * 订单发货
     */
    public void delivery() {
        this.setDeliveryTime(DateTime.now());
        this.setOrderStatus(OrderStatus.Delivery);
    }

    /**
     * 订单完成
     */
    public void complete() {
        this.setCompleteTime(DateTime.now());
        this.setOrderStatus(OrderStatus.Complete);
    }

    /**
     * 修改收货地址
     *
     * @param deliveryAddressInfo
     */
    public void changeDeliveryAddress(DeliveryAddressInfo deliveryAddressInfo) {
        this.setDeliveryAddressInfo(deliveryAddressInfo);
    }

    /**
     * 订单取消
     */
    public void cancel() {
        this.setOrderStatus(OrderStatus.Cancel);
        this.setCancelTime(DateTime.now());
    }



    protected BrokenRuleMessage getBrokenRuleMessages() {
        return new OrderBrokenRuleMessage();
    }

    public Boolean validate() {
        return new OrderEntityRule().isSatisfy(this);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    private void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice==null?BigDecimal.ZERO.setScale(2):totalPrice.setScale(2);
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public DateTime getCreateTime() {
        return createTime;
    }

    private void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }

    public DateTime getDeliveryTime() {
        return deliveryTime;
    }

    private void setDeliveryTime(DateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public DateTime getOutTime() {
        return outTime;
    }

    private void setOutTime(DateTime outTime) {
        this.outTime = outTime;
    }

    public DateTime getConfirmTime() {
        return confirmTime;
    }

    private void setConfirmTime(DateTime confirmTime) {
        this.confirmTime = confirmTime;
    }

    public DateTime getCompleteTime() {
        return completeTime;
    }

    private void setCompleteTime(DateTime completeTime) {
        this.completeTime = completeTime;
    }

    public DateTime getCancelTime() {
        return cancelTime;
    }

    private void setCancelTime(DateTime cancelTime) {
        this.cancelTime = cancelTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    private void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice == null ? BigDecimal.ZERO.setScale(2) : discountPrice.setScale(2);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    private void setItems(List<OrderItem> items) {
        this.items = items == null ? new ArrayList<OrderItem>(0) : items;
    }

    public int getUserId() {
        return userId;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    public VendorInfo getVendorInfo() {
        return vendorInfo;
    }

    private void setVendorInfo(VendorInfo vendorInfo) {
        this.vendorInfo = vendorInfo;
    }

    public DeliveryAddressInfo getDeliveryAddressInfo() {
        return deliveryAddressInfo;
    }

    private void setDeliveryAddressInfo(DeliveryAddressInfo deliveryAddressInfo) {
        this.deliveryAddressInfo = deliveryAddressInfo;
    }
}
