package ddd.demo.domain.order.service;

import ddd.demo.domain.order.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lil.yang on 2017/6/4.
 * 计算订单总金额
 */
public class TotalPriceService {

    /**
     * 返回订单总金额
     * @param orderItems
     * @return
     */
    public BigDecimal getTotalPrice(List<OrderItem> orderItems) {
        return orderItems.stream().map(item ->
                item.getPrice().multiply(BigDecimal.valueOf(item.getNumber()))
        ).reduce(BigDecimal::add).get();
    }

}
