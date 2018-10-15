package com.zft.elasticsearch.dao;

import com.zft.elasticsearch.entity.OrderBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zft
 * @date 2018/10/15.
 */
@Component
public interface OrderDao extends ElasticsearchRepository<OrderBean, String> {

    /**
     * 根据名称查询订单
     * JPA的形式
     * @param name 归属名称
     * @return 订单列表
     */
    List<OrderBean> findByOrderOwnNameIs(String name);
}
