package ddd.demo.domain.order.model;

/**
 * Created by lil.yang on 2017/6/4.
 * 收货信息
 */
public class DeliveryAddressInfo {
    /**
     * 收货人姓名
     */
    private String name;
    /**
     * 收货人电话
     */
    private String phone;
    /**
     * 收货人详细地址
     */
    private String address;

    public DeliveryAddressInfo(String name,String phone,String addressa){
        this.setName(name);
        this.setPhone(phone);
        this.setAddress(addressa);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
