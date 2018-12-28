package com.lyarc.tp.corp.common.bean;

/**
 * 
 * 分页查询数据
 * 
 * @version 1.0
 * @since JDK1.8
 * @author zhangjingtao
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年8月14日 下午5:17:27
 */
public class BaseQueryBean {

	/** 起始页码 */
	private Integer page;
	/** 查询条数 */
	private Integer pageSize;
	/** 偏移量 */
	private Integer offset;
	/** 排序 默认降序2 */
	private Integer order;
	/** 排序字段 默认排序字段为createTime */
	private String sort;
	/** 需要总记录数 */
	private Integer requireTotal;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getRequireTotal() {
		return requireTotal;
	}

	public void setRequireTotal(Integer requireTotal) {
		this.requireTotal = requireTotal;
	}

	public Integer getOffset() {
		if (offset != null) {
			return offset;
		}
		if (page != null && pageSize != null) {
			return (page - 1) * pageSize;
		}
		return 0;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "BaseQueryBean{" + "page=" + page + ", pageSize=" + pageSize + ", offset=" + offset + ", order=" + order
				+ ", sort='" + sort + '\'' + ", requireTotal=" + requireTotal + '}';
	}
}
