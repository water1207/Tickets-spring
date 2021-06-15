package cn.edu.hziee.tpp.model;

import java.io.Serializable;

public class FilmBroadcastInfo implements Serializable {
    private Integer filmBroadcastId;

    private Integer filmId;

    private String url;

    private Integer isMainPage;

    private Integer type;

    private static final long serialVersionUID = 1L;

    public Integer getFilmBroadcastId() {
        return filmBroadcastId;
    }

    public void setFilmBroadcastId(Integer filmBroadcastId) {
        this.filmBroadcastId = filmBroadcastId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getIsMainPage() {
        return isMainPage;
    }

    public void setIsMainPage(Integer isMainPage) {
        this.isMainPage = isMainPage;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", filmBroadcastId=").append(filmBroadcastId);
        sb.append(", filmId=").append(filmId);
        sb.append(", url=").append(url);
        sb.append(", isMainPage=").append(isMainPage);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}