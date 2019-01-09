package com.zft.elasticsearch.entity;

import lombok.Data;

import java.util.List;

/**
 * @author zft
 * @date 2018/10/19.
 */
@Data
public class Menu {
    private Long id;
    private String url;
    private String path;
    private Object component;
    private String name;
    private String iconCls;
    private Long parentId;
    private List<Role> roles;
    private List<Menu> children;
    private MenuMeta meta;

}
