import ddd.demo.application.ApplicationConfig;
import ddd.demo.application.order.OrderApplication;
import ddd.demo.domain.order.model.DeliveryAddressInfo;
import ddd.demo.domain.order.model.Order;
import ddd.demo.domain.order.model.OrderItem;
import ddd.demo.domain.order.model.VendorInfo;
import ddd.demo.domain.order.repository.IOrderRepository;
import ddd.demo.infrastructure.repository.RepositoryConfig;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lil.yang on 2017/6/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class,RepositoryConfig.class})
public class OrderApplicationTest {
    @Autowired
    private OrderApplication orderApplication;

    @Test
    public void addOrderTest() throws Exception {
        Order order = this.create();
        VendorInfo vendorInfo=new VendorInfo(200,"bb");

        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        OrderItem orderItem1 = new OrderItem(200, 2, new BigDecimal("24.5"));
        OrderItem orderItem2 = new OrderItem(201, 1, new BigDecimal("28.5"));
        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);

        DeliveryAddressInfo deliveryAddressInfo=new DeliveryAddressInfo("王五","13698883222","北京");

        orderApplication.create(vendorInfo,2,new BigDecimal("2"),orderItemList,deliveryAddressInfo);
    }

    private Order create() {

        long orderId = DateTime.now().getMillis();

        List<OrderItem> orderItemList = new ArrayList<OrderItem>();

        OrderItem orderItem1 = new OrderItem(100, 1, new BigDecimal("23.5"));
        OrderItem orderItem2 = new OrderItem(101, 1, new BigDecimal("23.5"));
        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);


        VendorInfo vendorInfo = new VendorInfo(100, "a");
        DeliveryAddressInfo deliveryAddressInfo = new DeliveryAddressInfo("李四", "18500191222", "北洋");


        Order order = new Order(orderId, new BigDecimal("23.56"), new BigDecimal("10.23"), new BigDecimal("20.12"), 1, vendorInfo, orderItemList, deliveryAddressInfo);

        return order;
    }
}
