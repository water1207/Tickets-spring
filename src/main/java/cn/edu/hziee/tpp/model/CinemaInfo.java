package cn.edu.hziee.tpp.model;

import java.io.Serializable;

public class CinemaInfo implements Serializable {
    private Integer cinemaId;

    private String cinemaName;

    private String cinemaAddress;

    private Float grade;

    private String telephone;

    private String cinemaPic;

    private String area;

    private static final long serialVersionUID = 1L;

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName == null ? null : cinemaName.trim();
    }

    public String getCinemaAddress() {
        return cinemaAddress;
    }

    public void setCinemaAddress(String cinemaAddress) {
        this.cinemaAddress = cinemaAddress == null ? null : cinemaAddress.trim();
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getCinemaPic() {
        return cinemaPic;
    }

    public void setCinemaPic(String cinemaPic) {
        this.cinemaPic = cinemaPic == null ? null : cinemaPic.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cinemaId=").append(cinemaId);
        sb.append(", cinemaName=").append(cinemaName);
        sb.append(", cinemaAddress=").append(cinemaAddress);
        sb.append(", grade=").append(grade);
        sb.append(", telephone=").append(telephone);
        sb.append(", cinemaPic=").append(cinemaPic);
        sb.append(", area=").append(area);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}