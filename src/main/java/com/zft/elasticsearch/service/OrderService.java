package com.zft.elasticsearch.service;

import com.zft.elasticsearch.entity.OrderBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zft
 * @date 2018/10/15.
 */
public interface OrderService {

    /**
     * 根据订单归属查询
     *
     * @param name 归属名
     * @return 订单列表
     */
    List<OrderBean> findByOrderOwnName(String name);

    OrderBean insertOrder(OrderBean orderBean);

    Page<OrderBean> findByLocation(Double topLat, Double topLng, Double bottomLat, Double bottomLng, Pageable pageable);
}
