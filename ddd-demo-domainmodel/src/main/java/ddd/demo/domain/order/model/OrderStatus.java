package ddd.demo.domain.order.model;

/**
 * Created by lil.yang on 2017/6/4.
 * 订单状态
 */
public enum  OrderStatus {
    ReadyConfirm(1, "待确认"),
    Confirm(2, "确认"),
    Out(3, "出库"),
    Delivery(4, "发货"),
    Complete(5, "完成"),
    Cancel(6, "取消");

    private int code;
    private String name;
    OrderStatus(int code, String name) {
        this.code=code;
        this.name=name;
    }

    public int value(){
        return this.code;
    }

    public static OrderStatus parse(int value){
        switch (value) {
            case 1:
                return ReadyConfirm;
            case 2:
                return Confirm;
            case 3:
                return Out;
            case 4:
                return Delivery;
            case 5:
                return Complete;
            case 6:
                return Cancel;
            default:
                return ReadyConfirm;
        }
    }

}
