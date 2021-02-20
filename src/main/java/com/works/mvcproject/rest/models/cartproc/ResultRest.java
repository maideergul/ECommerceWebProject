package com.works.mvcproject.rest.models.cartproc;

import java.util.HashMap;
import java.util.Map;

public class ResultRest {

    private Integer pid;
    private Integer cid;
    private String detail;
    private String img;
    private String price;
    private String title;
    private Integer pstatu;
    private Integer quantity;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPstatu() {
        return pstatu;
    }

    public void setPstatu(Integer pstatu) {
        this.pstatu = pstatu;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    
}