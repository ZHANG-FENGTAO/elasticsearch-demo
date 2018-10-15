package com.zft.elasticsearch.controller;

import com.zft.elasticsearch.entity.OrderBean;
import com.zft.elasticsearch.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zft
 * @date 2018/10/15.
 */
@Api("订单相关")
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/insert")
    @ApiOperation("添加")
    @ApiImplicitParam(name = "orderBean", value = "订单对象", dataType = "OrderBean",
            required = true, paramType = "body")
    public OrderBean insert(@RequestBody OrderBean orderBean) {
        return orderService.insertOrder(orderBean);
    }

    @GetMapping("/findByName/{name}")
    @ApiOperation("查询")
    public List<OrderBean> findByName(@PathVariable String name) {
        return orderService.findByOrderOwnName(name);
    }


    @GetMapping("/findByLocation")
    @ApiOperation("地理查询")
    public Page<OrderBean> findByLocation(@RequestParam Double topLat,
                                          @RequestParam Double topLng,
                                          @RequestParam Double bottomLat,
                                          @RequestParam Double bottomLng) {
        return orderService.findByLocation(topLat, topLng, bottomLat, bottomLng, new PageRequest(0, 20));
    }


}
