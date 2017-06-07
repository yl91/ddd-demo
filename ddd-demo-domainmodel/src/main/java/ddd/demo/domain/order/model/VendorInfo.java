package ddd.demo.domain.order.model;

/**
 * Created by lil.yang on 2017/6/4.
 * 商家信息
 */
public class VendorInfo {
    /**
     * 商家名称
     */
    private String name;
    /**
     * 商家ID
     */
    private long id;

    public VendorInfo(long id,String name){
        this.setId(id);
        this.setName(name);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
