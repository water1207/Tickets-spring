package cn.edu.hziee.tpp.mapper;

import cn.edu.hziee.tpp.model.HallInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HallInfoMapper {
    int deleteByPrimaryKey(Integer hallId);

    int insert(HallInfo record);

    HallInfo selectByPrimaryKey(Integer hallId);

    List<HallInfo> selectAll();

    List<HallInfo> selectByCinema(Integer cinemaId);

    int updateByPrimaryKey(HallInfo record);
}