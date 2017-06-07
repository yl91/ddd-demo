package ddd.demo.domain.order.event;

import easy.domain.event.IDomainEvent;

/**
 * Created by lil.yang on 2017/6/4.
 */
public class OrderDeliveredDomainEvent implements IDomainEvent {
    private long orderId;

    public OrderDeliveredDomainEvent(long orderId) {
        this.orderId = orderId;
    }

    public long getOrderId() {
        return this.orderId;
    }

    public String getBusinessId() {
        return null;
    }
}
