package ddd.demo.domainmodel.order;
import ddd.demo.domain.order.model.Order;
import ddd.demo.domain.order.model.OrderBrokenRuleMessage;
import ddd.demo.domain.order.model.VendorInfo;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by lil.yang on 2017/6/4.
 */
public class OrderEntityRuleTest {
    @Test
    public void orderIdTest(){

        VendorInfo vendorInfo = new VendorInfo(1,"");

        Order o = new Order(0L, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0, vendorInfo, null, null);

        Boolean vResult = o.validate();

        Assert.assertFalse(vResult);

        o.getBrokenRules().get(0).getName().equals(OrderBrokenRuleMessage.ORDER_ID_ERROR);
    }
}
