package com.zft.elasticsearch.controller;

import com.zft.elasticsearch.entity.OrderBean;
import com.zft.elasticsearch.service.OrderService;
import com.zft.elasticsearch.util.ClassDesc;
import com.zft.elasticsearch.util.MethodDesc;
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
@ClassDesc("orderController")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/insert")
    @ApiOperation("添加")
    @MethodDesc("insert")
    @ApiImplicitParam(name = "orderBean", value = "订单对象", dataType = "OrderBean",
            required = true, paramType = "body")
    public OrderBean insert(@RequestBody OrderBean orderBean) {
        return orderService.insertOrder(orderBean);
    }

    @GetMapping("/findByName/{name}")
    @ApiOperation("查询")
    @MethodDesc("findByName")
    public List<OrderBean> findByName(@PathVariable String name) {
        return orderService.findByOrderOwnName(name);
    }


    @GetMapping("/findByLocation")
    @ApiOperation("地理查询")
    @MethodDesc("findByLocation")
    public Page<OrderBean> findByLocation(@RequestParam Double topLat,
                                          @RequestParam Double topLng,
                                          @RequestParam Double bottomLat,
                                          @RequestParam Double bottomLng) {
        return orderService.findByLocation(topLat, topLng, bottomLat, bottomLng, PageRequest.of(0, 20));
    }
}
