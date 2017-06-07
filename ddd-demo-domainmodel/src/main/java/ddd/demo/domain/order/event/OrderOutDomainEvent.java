package ddd.demo.domain.order.event;

import easy.domain.event.IDomainEvent;

/**
 * Created by lil.yang on 2017/6/4.
 */
public class OrderOutDomainEvent implements IDomainEvent {

    private Long orderId;

    public OrderOutDomainEvent(Long orderId) {
        this.setOrderId(orderId);
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getBusinessId() {
        return null;
    }
}
