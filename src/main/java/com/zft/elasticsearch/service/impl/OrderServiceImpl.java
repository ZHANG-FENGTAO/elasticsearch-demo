package com.zft.elasticsearch.service.impl;

import com.zft.elasticsearch.dao.OrderDao;
import com.zft.elasticsearch.entity.OrderBean;
import com.zft.elasticsearch.service.OrderService;
import com.zft.elasticsearch.util.ClassDesc;
import com.zft.elasticsearch.util.MethodDesc;
import org.elasticsearch.index.query.GeoBoundingBoxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zft
 * @date 2018/10/15.
 */
@Service
@ClassDesc("OrderServiceImpl")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<OrderBean> findByOrderOwnName(String name) {
        return orderDao.findByOrderOwnNameIs(name);
    }

    @Override
    public OrderBean insertOrder(OrderBean orderBean) {
        return orderDao.save(orderBean);
    }

    @Override
    @MethodDesc("findByLocation")
    public Page<OrderBean> findByLocation(Double topLat, Double topLng, Double bottomLat, Double bottomLng, Pageable pageable) {
        GeoBoundingBoxQueryBuilder queryBuilder = QueryBuilders.geoBoundingBoxQuery("location");
///        queryBuilder.setCorners(new GeoPoint(50.0, 90),new GeoPoint(30, 119));
//        queryBuilder.setCorners(new GeoPoint(topLat, topLng),new GeoPoint(bottomLat, bottomLng));
        return orderDao.search(queryBuilder,pageable);
    }
}
