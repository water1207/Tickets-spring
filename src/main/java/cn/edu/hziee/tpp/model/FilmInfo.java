package cn.edu.hziee.tpp.model;

import java.io.Serializable;
import java.util.Date;

public class FilmInfo implements Serializable {
    private Integer filmId;

    private String filmName;

    private Date playTime;

    private String director;

    private String actor;

    private String filmType;

    private String makeFilmArea;

    private String makeFilmMan;

    private Integer filmLength;

    private String filmIntroduce;

    private Integer filmPlayStatus;

    private Float filmGrace;

    private FilmBroadcastInfo filmBroadcastInfo;

    private static final long serialVersionUID = 1L;

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName == null ? null : filmName.trim();
    }

    public Date getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Date playTime) {
        this.playTime = playTime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director == null ? null : director.trim();
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor == null ? null : actor.trim();
    }

    public String getFilmType() {
        return filmType;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType == null ? null : filmType.trim();
    }

    public String getMakeFilmArea() {
        return makeFilmArea;
    }

    public void setMakeFilmArea(String makeFilmArea) {
        this.makeFilmArea = makeFilmArea == null ? null : makeFilmArea.trim();
    }

    public String getMakeFilmMan() {
        return makeFilmMan;
    }

    public void setMakeFilmMan(String makeFilmMan) {
        this.makeFilmMan = makeFilmMan == null ? null : makeFilmMan.trim();
    }

    public Integer getFilmLength() {
        return filmLength;
    }

    public void setFilmLength(Integer filmLength) {
        this.filmLength = filmLength;
    }

    public String getFilmIntroduce() {
        return filmIntroduce;
    }

    public void setFilmIntroduce(String filmIntroduce) {
        this.filmIntroduce = filmIntroduce == null ? null : filmIntroduce.trim();
    }

    public Integer getFilmPlayStatus() {
        return filmPlayStatus;
    }

    public void setFilmPlayStatus(Integer filmPlayStatus) {
        this.filmPlayStatus = filmPlayStatus;
    }

    public Float getFilmGrace() {
        return filmGrace;
    }

    public void setFilmGrace(Float filmGrace) {
        this.filmGrace = filmGrace;
    }

    public FilmBroadcastInfo getFilmBroadcastInfo() {
        return filmBroadcastInfo;
    }

    public void setFilmBroadcastInfo(FilmBroadcastInfo filmBroadcastInfo) {
        this.filmBroadcastInfo = filmBroadcastInfo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", filmId=").append(filmId);
        sb.append(", filmName=").append(filmName);
        sb.append(", playTime=").append(playTime);
        sb.append(", director=").append(director);
        sb.append(", actor=").append(actor);
        sb.append(", filmType=").append(filmType);
        sb.append(", makeFilmArea=").append(makeFilmArea);
        sb.append(", makeFilmMan=").append(makeFilmMan);
        sb.append(", filmLength=").append(filmLength);
        sb.append(", filmIntroduce=").append(filmIntroduce);
        sb.append(", filmPlayStatus=").append(filmPlayStatus);
        sb.append(", filmGrace=").append(filmGrace);
        sb.append(", filmBroadcastInfo=").append(filmBroadcastInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}