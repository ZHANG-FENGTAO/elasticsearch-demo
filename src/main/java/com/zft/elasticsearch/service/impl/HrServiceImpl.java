package com.zft.elasticsearch.service.impl;

import com.zft.elasticsearch.dao.HrDao;
import com.zft.elasticsearch.entity.Hr;
import com.zft.elasticsearch.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author zft
 * @date 2018/10/19.
 */
@Service
public class HrServiceImpl implements HrService {

    @Autowired
    private HrDao hrDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrDao.loadUserByUsername(username);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不对");
        }
        return hr;
    }
}
