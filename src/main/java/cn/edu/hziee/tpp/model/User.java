package cn.edu.hziee.tpp.model;

import java.io.Serializable;

public class User implements Serializable {
    private Integer userId;

    private String userName;

    private String userPwd;

    private String phone;

    private String sex;

    private String userRank;

    private Integer userExp;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank == null ? null : userRank.trim();
    }

    public Integer getUserExp() {
        return userExp;
    }

    public void setUserExp(Integer userExp) {
        this.userExp = userExp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", userPwd=").append(userPwd);
        sb.append(", phone=").append(phone);
        sb.append(", sex=").append(sex);
        sb.append(", userRank=").append(userRank);
        sb.append(", userExp=").append(userExp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}