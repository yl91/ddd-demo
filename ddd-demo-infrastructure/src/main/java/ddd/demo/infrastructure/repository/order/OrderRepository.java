package ddd.demo.infrastructure.repository.order;
import ddd.demo.domain.order.model.Order;
import ddd.demo.domain.order.model.OrderItem;
import ddd.demo.domain.order.repository.IOrderRepository;
import org.joda.time.DateTime;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lil.yang on 2017/6/4.
 */
public class OrderRepository implements IOrderRepository {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;


    public long getNexOrderId() {
        return DateTime.now().getMillis();
    }

    public void add(Order order) {
        this.sqlSessionTemplate.insert("Order.add",order);

        Map<String,Object> map=new HashMap();
        map.put("orderId",order.getId());

        for (OrderItem orderItem:order.getItems()){
            map.remove("orderItem");
            map.put("orderItem",orderItem);
            this.sqlSessionTemplate.insert("Order.addItems", map);
        }

    }

    public void update(Order order) {

    }

    public Order findBy(Long orderId) {
        return this.sqlSessionTemplate.selectOne("Order.findOrderOne", orderId);
    }

    public void throwTest() {

    }
}
