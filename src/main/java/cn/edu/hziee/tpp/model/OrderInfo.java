package cn.edu.hziee.tpp.model;

import java.io.Serializable;
import java.util.List;

public class OrderInfo implements Serializable {
    private Integer orderId;

    private Integer userId;

    private String total;

    private Integer filmTimesId;

    private Integer orderState;

    private String phone;

    private List<OrderItemInfo> itemInfo;

    private FilmTimes filmTimes;

    private static final long serialVersionUID = 1L;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total == null ? null : total.trim();
    }

    public Integer getFilmTimesId() {
        return filmTimesId;
    }

    public void setFilmTimesId(Integer filmTimesId) {
        this.filmTimesId = filmTimesId;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public List<OrderItemInfo> getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(List<OrderItemInfo> itemInfo) {
        this.itemInfo = itemInfo;
    }

    public FilmTimes getFilmTimes() {
        return filmTimes;
    }

    public void setFilmTimes(FilmTimes filmTimes) {
        this.filmTimes = filmTimes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", userId=").append(userId);
        sb.append(", total=").append(total);
        sb.append(", filmTimesId=").append(filmTimesId);
        sb.append(", orderState=").append(orderState);
        sb.append(", phone=").append(phone);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", itemInfo=").append(itemInfo);
        sb.append(", filmTimes=").append(filmTimes);
        sb.append("]");
        return sb.toString();
    }
}