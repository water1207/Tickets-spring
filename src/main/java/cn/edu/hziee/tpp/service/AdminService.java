package cn.edu.hziee.tpp.service;

import cn.edu.hziee.tpp.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private CinemaInfoMapper cinemaInfoMapper;
    @Autowired
    private FilmInfoMapper filmInfoMapper;
    @Autowired
    private FilmTimesMapper filmTimesMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderItemInfoMapper orderItemMapper;
    @Autowired
    private UserMapper userMapper;

}
