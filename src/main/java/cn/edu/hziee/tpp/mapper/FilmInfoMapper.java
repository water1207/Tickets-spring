package cn.edu.hziee.tpp.mapper;

import cn.edu.hziee.tpp.model.FilmInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FilmInfoMapper {
    int deleteByPrimaryKey(Integer filmId);

    int insert(FilmInfo record);

    FilmInfo selectByPrimaryKey(Integer filmId);

    List<FilmInfo> selectAll();

    int updateByPrimaryKey(FilmInfo record);
}