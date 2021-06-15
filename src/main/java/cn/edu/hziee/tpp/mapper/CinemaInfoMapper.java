package cn.edu.hziee.tpp.mapper;

import cn.edu.hziee.tpp.model.CinemaInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CinemaInfoMapper {
    int deleteByPrimaryKey(Integer cinemaId);

    int insert(CinemaInfo record);

    CinemaInfo selectByPrimaryKey(Integer cinemaId);

    List<CinemaInfo> selectAll();

    List<CinemaInfo> selectByArea(String area);

    List<String> selectAreas();

    int updateByPrimaryKey(CinemaInfo record);
}