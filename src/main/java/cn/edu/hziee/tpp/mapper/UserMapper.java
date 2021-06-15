package cn.edu.hziee.tpp.mapper;

import cn.edu.hziee.tpp.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    User selectByPrimaryKey(Integer userId);

    User selectByUserName(String userName);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}