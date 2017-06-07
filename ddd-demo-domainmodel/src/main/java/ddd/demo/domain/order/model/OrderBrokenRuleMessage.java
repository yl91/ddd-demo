package ddd.demo.domain.order.model;

import easy.domain.base.BrokenRuleMessage;

/**
 * Created by lil.yang on 2017/6/4.
 */
public class OrderBrokenRuleMessage extends BrokenRuleMessage {
    public static final String ORDER_ID_ERROR = "order_id_error";
    public static final String VENDOR_ID_ERROR="vendor_id_error";

    @Override
    protected void populateMessage() {
        this.getMessages().put(OrderBrokenRuleMessage.ORDER_ID_ERROR, "订单ID必须大于0");
        this.getMessages().put(VENDOR_ID_ERROR,"商家ID必须大于0");
    }
}
