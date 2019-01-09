package com.zft.elasticsearch.util;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * @author zft
 * @date 2019/1/9.
 */
public class AopLogUtils {

    private final static Cache<String, String> METHOD_DESC_CACHE = CacheBuilder.newBuilder().
            initialCapacity(80).maximumSize(200).build();

    private final static Cache<String, String> CLASS_DESC_CACHE = CacheBuilder.newBuilder().
            initialCapacity(15).maximumSize(50).build();

    private final static String UNKNOWN = "unknown";

    public static String getMethodDesc(Class<?> targetClz, Method method) {
        String className = targetClz.getName();
        String methodName = method.getName();
        try {
            return METHOD_DESC_CACHE.get(className + "." + methodName, () -> {
                MethodDesc methodAnnotation = method.getAnnotation(MethodDesc.class);
                String value = Optional.ofNullable(methodAnnotation).map(MethodDesc::value).orElse(UNKNOWN);
                return StringUtils.isBlank(value) ? className + "." + methodName : value;
            });
        } catch (ExecutionException e) {
            return className + "." + methodName;
        }
    }

    public static String getClassDesc(Class<?> targetClz) {
        String className = targetClz.getName();
        try {
            return CLASS_DESC_CACHE.get(className, () -> {
                ClassDesc clzAnnotation = targetClz.getAnnotation(ClassDesc.class);
                return Optional.ofNullable(clzAnnotation).map(ClassDesc::value).orElse(UNKNOWN);
            });
        } catch (ExecutionException e) {
            return className;
        }
    }

    public static String toJson(Object... args) {
        if (args == null || args.length == 0) {
            return "";
        }
        return JSON.toJSONString(args);
    }

}
