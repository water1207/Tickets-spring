package cn.edu.hziee.tpp.model;

import java.io.Serializable;

public class HallInfo implements Serializable {
    private Integer hallId;

    private String hallName;

    private Integer cinemaId;

    private String seatInfo;

    private CinemaInfo cinemaInfo;

    private static final long serialVersionUID = 1L;

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName == null ? null : hallName.trim();
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo == null ? null : seatInfo.trim();
    }

    public CinemaInfo getCinemaInfo() {
        return cinemaInfo;
    }

    public void setCinemaInfo(CinemaInfo cinemaInfo) {
        this.cinemaInfo = cinemaInfo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hallId=").append(hallId);
        sb.append(", hallName=").append(hallName);
        sb.append(", cinemaId=").append(cinemaId);
        sb.append(", seatInfo=").append(seatInfo);
        sb.append(", cinemaInfo=").append(cinemaInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}