package com.zft.elasticsearch.common;

import com.zft.elasticsearch.entity.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author zft
 * @date 2018/10/19.
 */
public class HrUtils {
    public static Hr getCurrentHr() {
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
