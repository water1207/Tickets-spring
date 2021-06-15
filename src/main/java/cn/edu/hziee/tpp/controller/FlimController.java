package cn.edu.hziee.tpp.controller;

import cn.edu.hziee.tpp.model.CinemaInfo;
import cn.edu.hziee.tpp.model.FilmInfo;
import cn.edu.hziee.tpp.model.FilmTimes;
import cn.edu.hziee.tpp.model.OrderInfo;
import cn.edu.hziee.tpp.service.FilmService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller

@CrossOrigin(origins = "*", maxAge = 3600)
public class FlimController {
    @Autowired
    private FilmService filmService;

    @RequestMapping("/user/index")
    public String index(Model model) {
        List<FilmInfo> filmInfos = filmService.selectAll();
        model.addAttribute("filmInfos", filmInfos);
        System.out.println(filmInfos);
        return "views/user/index";
    }

    @RequestMapping("/user/film/time")
    public String TimeSelection(@RequestParam(value = "film_id", defaultValue = "1") Integer filmId,
                                @RequestParam(value = "cinema_id", defaultValue = "1") Integer cinemaId,
                                @RequestParam(value = "date", defaultValue = "05-28") String date,
                                Model model) {
        FilmInfo filmInfo = filmService.getFilmInfo(filmId);
        List<CinemaInfo> cinemaList = filmService.getAllCinema();
        List<FilmTimes> times = filmService.queryTimes(filmId, cinemaId, date);

        model.addAttribute("filmInfo", filmInfo);
        model.addAttribute("cinemaList", cinemaList);
        model.addAttribute("times", times);

        return "views/user/time_selection";
    }

    @RequestMapping(value = "/user/query")
    public String TimeQuery(@RequestParam(value = "film_id", defaultValue = "1") Integer filmId,
                            @RequestParam(value = "cinema_id", defaultValue = "1") Integer cinemaId,
                            @RequestParam(value = "date", defaultValue = "05-28") String date,
                            Model model) {
        FilmInfo filmInfo = filmService.getFilmInfo(filmId);
        List<CinemaInfo> cinemaList = filmService.getAllCinema();
        List<FilmTimes> times = filmService.queryTimes(filmId, cinemaId, date);

        model.addAttribute("filmInfo", filmInfo);
        model.addAttribute("cinemaList", cinemaList);
        model.addAttribute("times", times);
        model.addAttribute("cinemaId", cinemaId);

        return "views/user/query";
    }

    @RequestMapping("/user/cinema")
    public String cinemaList(@RequestParam(value = "area",defaultValue = "all") String area,
                             Model model) {
        List<CinemaInfo> cinemaList = filmService.getCinemaByArea(area);
        List<String> areas = filmService.getAreas();

        model.addAttribute("cinemas", cinemaList);
        model.addAttribute("areas", areas);
        return "views/user/cinema";
    }
//    @RequestMapping("/test")
//    public String test(Model model) {
//        List<FilmTimes> times = filmService.queryTimes(1, 1, "05-28");
//        System.out.println(times);
//        return "views/user/time_selection";
//    }
}
