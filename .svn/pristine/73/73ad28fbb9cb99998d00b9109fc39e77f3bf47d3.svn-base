package com.lyarc.tp.corp.util;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * 显示页数 
 * 
 * @version 1.0 
 * @since JDK1.8 
 * @author zhangjingtao 
 * @company 洛阳艾克科技有限公司 
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved. 
 * @date 2017年8月14日 下午5:55:12
 */
public class PageUtil {

	/*
	TODO 2017-08-15 缺少方法注释
	 */
	public static List<Integer> getShowPages(int currentPage, int maxPage) {
		List<Integer> pages = new ArrayList<Integer>();
		pages.add(currentPage - 3);
		pages.add(currentPage - 2);
		pages.add(currentPage - 1);
		pages.add(currentPage);
		pages.add(currentPage + 1);
		pages.add(currentPage + 2);
		pages.add(currentPage + 3);
		Iterator<Integer> it = pages.iterator();
		while (it.hasNext()) {
			int p =it.next();
			if (p <= 0 || p > maxPage) {
				it.remove();
			}
		}
		return pages;
	}

	/*
	TODO 2017-08-15 缺少方法注释
	 */
	public static <T> JSONObject pagination(List<T> T, Integer pageIndex, Integer pageSize, int total) {
		JSONObject page = new JSONObject();
		page.put("pageIndex", pageIndex);
		page.put("pageSize", pageSize);
		page.put("totalRecords", total);
		if (total % pageSize == 0) {
			page.put("totalPage", (total / pageSize));
		} else {
			page.put("totalPage", (total / pageSize + 1));
		}
		JSONObject data = new JSONObject();
		data.put("list", T);
		data.put("page", page);
		return data;
	}
	
}
