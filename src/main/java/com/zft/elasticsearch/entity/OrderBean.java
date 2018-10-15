package com.zft.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

/**
 * @author zft
 * @date 2018/10/15.
 */
@Data
@Document(indexName = "shop",type = "order",refreshInterval = "0s")
public class OrderBean {

    @Id
    private String orderId;

    private String orderOwnName;

    private String address;

    @GeoPointField
    private GeoPoint location;

}
