package cn.edu.hziee.tpp.mapper;

import cn.edu.hziee.tpp.model.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderInfoMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer orderId);

    List<OrderInfo> selectByTimesId(Integer timesId);

    List<OrderInfo> selectByTimesIdLocked(Integer timesId);

    List<OrderInfo> selectAll();

    List<OrderInfo> selectByUserId(Integer userId);

    int updateByPrimaryKey(OrderInfo record);
}