package com.lyarc.tp.corp.warehouse.form;

import java.util.Arrays;

import javax.validation.constraints.NotNull;

import com.googlecode.jmapper.annotations.JGlobalMap;

/**
 * 批量更新，删除，该状态的ids；
 * 
 * @author shiyong
 *
 */

@JGlobalMap
public class StockBillStatusForm {
	@NotNull(message = "编号不能为空")
	private String[] ids;

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		return "StockBillStatusForm [ids=" + Arrays.toString(ids) + "]";
	}
}
