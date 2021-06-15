package cn.edu.hziee.tpp.mapper;

import cn.edu.hziee.tpp.model.OrderItemInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderItemInfoMapper {
    int deleteByPrimaryKey(Integer orderItemId);

    int insert(OrderItemInfo record);

    OrderItemInfo selectByPrimaryKey(Integer orderItemId);

    List<OrderItemInfo> selectAll();

    int updateByPrimaryKey(OrderItemInfo record);

    List<OrderItemInfo> selectByOrder(Integer orderId);
}