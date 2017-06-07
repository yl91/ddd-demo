package ddd.demo.domain.order.event;

import easy.domain.event.IDomainEvent;

/**
 * Created by lil.yang on 2017/6/4.
 */
public class OrderCreatedDomainEvent implements IDomainEvent {

    private long orderId;

    public OrderCreatedDomainEvent(long orderId){
        this.setOrderId(orderId);
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getBusinessId() {
        return null;
    }
}
