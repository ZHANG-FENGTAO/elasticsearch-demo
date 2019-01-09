package com.zft.elasticsearch.service.impl;

import com.zft.elasticsearch.dao.MenuDao;
import com.zft.elasticsearch.entity.Menu;
import com.zft.elasticsearch.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zft
 * @date 2018/10/19.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> getAllMenu() {
        return menuDao.getAllMenu();
    }
}
