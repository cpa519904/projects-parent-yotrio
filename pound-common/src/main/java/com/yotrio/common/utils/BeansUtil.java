package com.yotrio.common.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * 模块名称：caimaomall com.caimao.mall.common.utils
 * 功能说明：封装springframework 的BeanUtils方法<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-07-30 16:39
 * 系统版本：1.0.0
 **/

public class BeansUtil {
    /**
     * copy参数
     *
     * @param src
     * @param target
     */
    public static void copyProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target);
    }

    /**
     * copy参数值忽略为空的参数
     *
     * @param src
     * @param target
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    /**
     * copy参数值忽略为空的参数
     *
     * @param src
     * @param target
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target, String[] ignoreProperties) {
        String[] nullProperties = getNullPropertyNames(src);
        String[] properties = contactProperties(nullProperties, ignoreProperties);
        BeanUtils.copyProperties(src, target, properties);
    }

    /**
     * 获取为空的参数名
     *
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 合并两个参数数组
     *
     * @param arr1
     * @param arr2
     * @return
     */
    private static String[] contactProperties(String[] arr1, String[] arr2) {
        Set<String> names = new HashSet<String>();
        for (int i = 0; i < arr1.length; i++) {
            names.add(arr1[i]);
        }
        for (int i = 0; i < arr2.length; i++) {
            if (!names.contains(arr2[i])) {
                names.add(arr2[i]);
            }
        }
        String[] result = new String[names.size()];
        return names.toArray(result);
    }
}