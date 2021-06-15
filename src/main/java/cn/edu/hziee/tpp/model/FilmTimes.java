package cn.edu.hziee.tpp.model;

import java.io.Serializable;

public class FilmTimes implements Serializable {
    private Integer filmTimesId;

    private Integer filmId;

    private Integer hallId;

    private String filmDate;

    private String filmTime;

    private Float filmPrice;

    private Float filmDiscount;

    private String seatInfo;

    private HallInfo hallInfo;

    private FilmInfo filmInfo;

    private static final long serialVersionUID = 1L;

    public Integer getFilmTimesId() {
        return filmTimesId;
    }

    public void setFilmTimesId(Integer filmTimesId) {
        this.filmTimesId = filmTimesId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public String getFilmDate() {
        return filmDate;
    }

    public void setFilmDate(String filmDate) {
        this.filmDate = filmDate == null ? null : filmDate.trim();
    }

    public String getFilmTime() {
        return filmTime;
    }

    public void setFilmTime(String filmTime) {
        this.filmTime = filmTime == null ? null : filmTime.trim();
    }

    public Float getFilmPrice() {
        return filmPrice;
    }

    public void setFilmPrice(Float filmPrice) {
        this.filmPrice = filmPrice;
    }

    public Float getFilmDiscount() {
        return filmDiscount;
    }

    public void setFilmDiscount(Float filmDiscount) {
        this.filmDiscount = filmDiscount;
    }

    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo == null ? null : seatInfo.trim();
    }

    public FilmInfo getFilmInfo() {
        return filmInfo;
    }

    public void setFilmInfo(FilmInfo filmInfo) {
        this.filmInfo = filmInfo;
    }

    public HallInfo getHallInfo() {
        return hallInfo;
    }

    public void setHallInfo(HallInfo hallInfo) {
        this.hallInfo = hallInfo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", filmTimesId=").append(filmTimesId);
        sb.append(", filmId=").append(filmId);
        sb.append(", hallId=").append(hallId);
        sb.append(", filmDate=").append(filmDate);
        sb.append(", filmTime=").append(filmTime);
        sb.append(", filmPrice=").append(filmPrice);
        sb.append(", filmDiscount=").append(filmDiscount);
        sb.append(", seatInfo=").append(seatInfo);
        sb.append(", hallInfo=").append(hallInfo);
        sb.append(", filmInfo=").append(filmInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}