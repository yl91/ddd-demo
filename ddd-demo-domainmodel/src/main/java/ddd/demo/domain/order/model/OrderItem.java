package ddd.demo.domain.order.model;

import java.math.BigDecimal;

/**
 * Created by lil.yang on 2017/6/4.
 * 订单明细
 */
public class OrderItem {
    /**
     * SkuId
     */
    private int skuId;
    /**
     * 商品数量
     */
    private int number;
    /**
     * 商品单价
     */
    private BigDecimal price;

    public OrderItem(int skuId,int number,BigDecimal price){
        this.setSkuId(skuId);
        this.setNumber(number);
        this.setPrice(price);
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
