package ddd.demo.application.order;

import ddd.demo.domain.order.event.OrderCreatedDomainEvent;
import ddd.demo.domain.order.event.OrderDeliveredDomainEvent;
import ddd.demo.domain.order.event.OrderOutDomainEvent;
import ddd.demo.domain.order.model.DeliveryAddressInfo;
import ddd.demo.domain.order.model.Order;
import ddd.demo.domain.order.model.OrderItem;
import ddd.demo.domain.order.model.VendorInfo;
import ddd.demo.domain.order.repository.IOrderRepository;
import ddd.demo.domain.order.service.PayPriceService;
import ddd.demo.domain.order.service.TotalPriceService;
import easy.domain.application.BaseApplication;
import easy.domain.application.result.IBaseResult;
import easy.domain.application.subscriber.IDomainEventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lil.yang on 2017/6/4.
 */
@Component
public class OrderApplication extends BaseApplication {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    public OrderApplication(){
        this.registerSubscriber(new IDomainEventSubscriber<OrderCreatedDomainEvent>() {
            @Override
            public Class<?> subscribedToEventType() {
                return OrderCreatedDomainEvent.class;
            }

            @Override
            public void handleEvent(OrderCreatedDomainEvent orderCreatedDomainEvent) {
                System.out.println("event");
            }
        });
    }

    /**
     * 生成订单
     *
     * @param vendorInfo          商家信息
     * @param userId              下单用户ID
     * @param discountPrice       折扣金额
     * @param orderItemList       订单明细
     * @param deliveryAddressInfo 收货地址
     */
    public void create(VendorInfo vendorInfo, int userId, BigDecimal discountPrice, List<OrderItem> orderItemList, DeliveryAddressInfo deliveryAddressInfo) throws Exception {
        Long orderId = this.orderRepository.getNexOrderId();
        BigDecimal totalPrice = new TotalPriceService().getTotalPrice(orderItemList);
        BigDecimal payPrice = new PayPriceService().getPayPrice(totalPrice, discountPrice);
        Order order = new Order(orderId, totalPrice, discountPrice, payPrice, userId, vendorInfo, orderItemList, deliveryAddressInfo);

        if (!order.validate()) {
            return;
        }

        TransactionTemplate transactionTemplate = new TransactionTemplate(this.platformTransactionManager);
        transactionTemplate.setReadOnly(false);
        transactionTemplate.execute(s -> {
            this.orderRepository.add(order);
            return true;
        });

        this.publishEvent(new OrderCreatedDomainEvent(order.getId()));
    }


    /**
     * 根据订单ID查询详情
     *
     * @param orderId 订单ID
     * @return
     */
    public IBaseResult<Order> findById(long orderId) {
        Order o = this.orderRepository.findBy(orderId);
        return this.write(o);
    }

    /**
     * 订单确认
     *
     * @param orderId 订单ID
     */
    public void confirm(long orderId) {
        Order order = this.orderRepository.findBy(orderId);
        order.confirm();
        if (order.validate()) {
            this.orderRepository.update(order);
        }
    }

    /**
     * 订单出库
     *
     * @param orderId 订单ID
     */
    public void out(long orderId) throws Exception {
        Order order = this.orderRepository.findBy(orderId);
        order.out();
        if (order.validate()) {
            orderRepository.update(order);
        }

        this.publishEvent(new OrderOutDomainEvent(order.getId()));
    }

    /**
     * 订单发货
     *
     * @param orderId 订单ID
     */
    public void delivery(long orderId) throws Exception {
        Order order = this.orderRepository.findBy(orderId);
        order.delivery();
        if (order.validate()) {
            orderRepository.update(order);
        }

        this.publishEvent(new OrderDeliveredDomainEvent(orderId));
    }

    /**
     * 订单完成 订单ID
     *
     * @param orderId 订单ID
     */
    public void complete(long orderId) {
        Order order = this.orderRepository.findBy(orderId);
        order.complete();
        if (order.validate()) {
            orderRepository.update(order);
        }
    }

    /**
     * 订单取消
     *
     * @param orderId 订单ID
     */
    public void cancel(long orderId) {
        Order order = this.orderRepository.findBy(orderId);
        order.cancel();
        if (order.validate()) {
            orderRepository.update(order);
        }
    }

    /**
     * 修改订单收货地址
     *
     * @param orderId             订单ID
     * @param deliveryAddressInfo 新收货地址
     */
    public void changeDeliveryAddress(long orderId, DeliveryAddressInfo deliveryAddressInfo) {
        Order order = this.orderRepository.findBy(orderId);

        order.changeDeliveryAddress(deliveryAddressInfo);
        if (order.validate()) {
            this.orderRepository.update(order);
        }
    }


}
