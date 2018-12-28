package com.lyarc.tp.corp.common.bean;

import java.io.Serializable;

/**
 * 分页结果
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月18日 10:19:18
 */
public class PageResultBean<T> implements Serializable {

    private static final long serialVersionUID = 3003678859734693388L;
    /**
     * 总数
     */
    private Long total;
    /**
     * 表格数据
     */
    private T rows;

    private PageResultBean(Long total, T rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }

    public static <T> PageResultBean success(Long total, T rows) {
        return new PageResultBean(total, rows);
    }
}
