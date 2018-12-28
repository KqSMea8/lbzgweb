package com.lyarc.tp.corp.util;

import com.googlecode.jmapper.JMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * JMpapper转换工具
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月15日 13:54:15
 */
public class JMapperUtils {

    /**
     * 方法描述 转换集合
     * 
     * @param sources 转换对象
     * @param destination 目标类
     * @param source 源类
     * @return List<D>
     *
     * @author huanghaoqi
     * @date 2017年08月15日 13:54:46
     */
    public static <S, D> List<D> getDestinations(List<S> sources, Class<D> destination, Class<S> source) {
        JMapper<D, S> principalMapper = new JMapper<>(destination, source);
        return sources.stream().map(principalMapper::getDestination).collect(Collectors.toList());
    }

    /**
     * 方法描述 转换单个对象
     * 
     * @param sourceObject 转换对象
     * @param destination 目标类
     * @param source 源类
     * @return D
     *
     * @author huanghaoqi
     * @date 2017年08月15日 13:59:58
     */
    public static <S, D> D getDestination(S sourceObject, Class<D> destination, Class<S> source) {
        JMapper<D, S> principalMapper = new JMapper<>(destination, source);
        return principalMapper.getDestination(sourceObject);
    }
}
