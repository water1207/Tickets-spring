package cn.edu.hziee.tpp.mapper;

import cn.edu.hziee.tpp.model.FilmTimes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FilmTimesMapper {
    int deleteByPrimaryKey(Integer filmTimesId);

    int insert(FilmTimes record);

    FilmTimes selectByPrimaryKeyLocked(Integer filmTimesId);

    FilmTimes selectByPrimaryKey(Integer filmTimesId);

    List<FilmTimes> selectAll();

    int updateByPrimaryKey(FilmTimes record);

    List<FilmTimes> queryTimes(Integer filmId,Integer cinemaId,String filmDate);

}