package com.lyarc.tp.corp.productstock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.productstock.bean.ProductStock;
import com.lyarc.tp.corp.productstock.bean.ProductStockInBean;
import com.lyarc.tp.corp.productstock.bean.ProductStockInQueryBean;
import com.lyarc.tp.corp.productstock.bean.ProductStockKey;
import com.lyarc.tp.corp.productstock.dao.ProductStockInMapper;

@Service
public class ProductStockInService {
	@Autowired
	private ProductStockInMapper productStockInMapper;
	@Autowired
	private ProductStockService productStockService;
	
	public PageResultBean list(ProductStockInQueryBean bean) {
		List<ProductStockInBean> stockBillList = productStockInMapper.list(bean);// 列表
		Long count = productStockInMapper.count(bean);// 计数
		return PageResultBean.success(count, stockBillList);
	}


	 public Integer add(ProductStockInBean bean) {
		 int row =productStockInMapper.insertSelective(bean);
		 // 查库存
		 ProductStockKey key=new ProductStockKey();
		 key.setCasting(bean.getCasting());
		 key.setMaterId(bean.getMaterId());
		 key.setMaterName(bean.getMaterName());
		 key.setStorageId(bean.getStorageId());
		 
		 ProductStock stock = productStockService.get(key);
		 // 加库存、提交
		 if(stock==null) {
			stock = new ProductStock();
			stock.setCasting(bean.getCasting());
			stock.setMaterId(bean.getMaterId());
			stock.setMaterName(bean.getMaterName());
			stock.setStorageId(bean.getStorageId()); 
			stock.setCustomerId(bean.getCustomerId());
			stock.setDrawing(bean.getDrawing());
			stock.setUnit(bean.getUnit());
			stock.setWeight(bean.getWeight());
			stock.setQuantity(bean.getQuantity());
		 }
		 stock.setQuantity(bean.getQuantity());
		 productStockService.inStock(stock);
		 return row;
	 }
}
