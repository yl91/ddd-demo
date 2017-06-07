package ddd.demo.infrastructure.repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import ddd.demo.domain.order.model.DeliveryAddressInfo;
import ddd.demo.domain.order.model.Order;
import ddd.demo.domain.order.model.OrderItem;
import ddd.demo.domain.order.model.VendorInfo;
import ddd.demo.domain.order.repository.IOrderRepository;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lil.yang on 2017/6/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class})
public class OrderRepositoryTest{
    @Autowired
    private IOrderRepository orderRepository;

    @Test
    @Transactional
    @Commit
    public void addOrderTest() {
        Order order = this.create();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        this.orderRepository.add(order);
        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeMillis());
    }

    @Test
    @Transactional
    @Commit
    public void findOneTest() {

        SerializeConfig.getGlobalInstance().put(DateTime.class, new ObjectSerializer() {
            public void write(JSONSerializer jsonSerializer, Object o, Object o1, java.lang.reflect.Type type, int i) throws IOException {

                SerializeWriter out = jsonSerializer.out;
                DateTime value = (DateTime) o;
                if (value == null) {
                    out.writeNull();
                } else {
                    out.writeString(value.toString("yyyy-MM-dd"));
                }
            }
        });

        Order order = this.create();
        this.orderRepository.add(order);
        Order a = this.orderRepository.findBy(order.getId());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        this.orderRepository.findBy(order.getId());
        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeMillis());

        String expected = JSON.toJSONString(order);
        String actual = JSON.toJSONString(a);


        Assert.assertEquals(expected, actual);

    }

    private Order create() {

        long orderId = this.orderRepository.getNexOrderId();

        List<OrderItem> orderItemList = new ArrayList<OrderItem>();

        OrderItem orderItem1 = new OrderItem(100, 1, new BigDecimal("23.5"));
        OrderItem orderItem2 = new OrderItem(101, 1, new BigDecimal("23.5"));
        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);


        VendorInfo vendorInfo = new VendorInfo(100, "a");
        DeliveryAddressInfo deliveryAddressInfo = new DeliveryAddressInfo("张三", "18500191543", "北洋");


        Order order = new Order(orderId, new BigDecimal("23.56"), new BigDecimal("10.23"), new BigDecimal("20.12"), 1, vendorInfo, orderItemList, deliveryAddressInfo);

        return order;
    }
}
