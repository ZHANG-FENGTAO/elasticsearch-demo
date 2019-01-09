package com.zft.elasticsearch.dao;

import com.zft.elasticsearch.entity.Hr;
import com.zft.elasticsearch.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zft
 * @date 2018/10/19.
 */
@Mapper
public interface HrDao {

    Hr loadUserByUsername(String userName);

    Role getRolesByHrId(Long id);
}
