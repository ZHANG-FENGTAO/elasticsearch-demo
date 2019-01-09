package com.zft.elasticsearch.dao;

import com.zft.elasticsearch.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zft
 * @date 2018/10/19.
 */
@Mapper
public interface MenuDao {
    List<Menu> getAllMenu();
}
