package ddd.demo.domain.order.model;

/**
 * Created by lil.yang on 2017/6/4.
 */
public class OrderEntityRule extends easy.domain.rules.EntityRule<Order> {
    public OrderEntityRule(){
        this.numberShouldGreaterThan("id",0L,OrderBrokenRuleMessage.ORDER_ID_ERROR);
        this.numberShouldGreaterThan("vendorInfo.id",0L,OrderBrokenRuleMessage.VENDOR_ID_ERROR);
    }
}
