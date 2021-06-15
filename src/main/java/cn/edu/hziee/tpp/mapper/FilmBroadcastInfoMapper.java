package cn.edu.hziee.tpp.mapper;

import cn.edu.hziee.tpp.model.FilmBroadcastInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FilmBroadcastInfoMapper {
    int deleteByPrimaryKey(Integer filmBroadcastId);

    int insert(FilmBroadcastInfo record);

    FilmBroadcastInfo selectByPrimaryKey(Integer filmBroadcastId);

    List<FilmBroadcastInfo> selectAll();

    int updateByPrimaryKey(FilmBroadcastInfo record);
}