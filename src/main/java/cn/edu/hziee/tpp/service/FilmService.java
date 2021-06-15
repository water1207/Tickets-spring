package cn.edu.hziee.tpp.service;

import cn.edu.hziee.tpp.mapper.*;
import cn.edu.hziee.tpp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FilmService {
    @Autowired
    private CinemaInfoMapper cinemaInfoMapper;
    @Autowired
    private FilmInfoMapper filmInfoMapper;
    @Autowired
    private FilmTimesMapper filmTimesMapper;

    public List<FilmInfo> selectAll() {
        return filmInfoMapper.selectAll();
    }

    public FilmInfo getFilmInfo(Integer filmId) {
        return filmInfoMapper.selectByPrimaryKey(filmId);
    }

    public List<CinemaInfo> getAllCinema() {
        return cinemaInfoMapper.selectAll();
    }

    public List<CinemaInfo> getCinemaByArea(String area) {
        if (area.equals("all")) {
            return getAllCinema();
        }
        return cinemaInfoMapper.selectByArea(area);
    }

    public List<String> getAreas() {
        return cinemaInfoMapper.selectAreas();
    }

    public FilmTimes getFilmTime(Integer timesId) {
        return filmTimesMapper.selectByPrimaryKey(timesId);
    }

    public List<FilmTimes> queryTimes(Integer filmId,Integer cinemaId,String filmDate) {
        return filmTimesMapper.queryTimes(filmId, cinemaId, filmDate);
    }

}
