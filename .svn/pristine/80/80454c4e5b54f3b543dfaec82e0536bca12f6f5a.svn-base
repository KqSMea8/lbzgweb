package com.lyarc.tp.corp.warehouse.form;

import java.util.Arrays;

import javax.validation.constraints.NotNull;

import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class StockCheckBatchStatusForm {
	@NotNull(message = "仓库代码不能为空")
	private String[] ids;

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		return "StockCheckBatchStatusForm [ids=" + Arrays.toString(ids) + "]";
	}

}
