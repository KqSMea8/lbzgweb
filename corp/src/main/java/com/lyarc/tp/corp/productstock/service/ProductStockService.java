package com.lyarc.tp.corp.productstock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.productstock.bean.ProductStock;
import com.lyarc.tp.corp.productstock.bean.ProductStockKey;
import com.lyarc.tp.corp.productstock.bean.ProductStockQueryBean;
import com.lyarc.tp.corp.productstock.dao.ProductStockMapper;

@Service
public class ProductStockService {
	@Autowired
	private ProductStockMapper productStockMapper;

	// 查询列表
	public PageResultBean list(ProductStockQueryBean productStockQueryBean) {
		List<ProductStock> list = productStockMapper.list(productStockQueryBean);
		Long count = productStockMapper.count(productStockQueryBean);
		return PageResultBean.success(count, list);
	}

	public Integer checkBatchStatus(List<ProductStock> ProductStocks) {// 循环调用接口实现mapper的方法
		for (ProductStock productStock : ProductStocks) {
			productStockMapper.checkBatchStatus(productStock);// 传入的List集合，循环更新每一条的信息（4个主键匹配好，即可更新状态字段）
		}
		return 1;
	}

	// 主键、数量
	public Integer inStock(ProductStock bean) {

		ProductStockKey key = new ProductStockKey();
		key.setCasting(bean.getCasting());
		key.setMaterId(bean.getMaterId());
		key.setMaterName(bean.getMaterName());
		key.setStorageId(bean.getStorageId());

		ProductStock stock = this.get(key);
		if (stock != null) {
			stock.setQuantity(stock.getQuantity().add(bean.getQuantity()));
			productStockMapper.update(stock);
		} else {
			productStockMapper.add(bean);
		}
		return 1;
	}

	public ProductStock get(ProductStockKey key) {
		return productStockMapper.get(key);
	}

	// 盘点查询
	public Integer addOrUpdate(List<ProductStock> productStocks) {
		for (ProductStock productStock : productStocks) {
			ProductStockKey psk = new ProductStockKey();
			psk.setCasting(productStock.getCasting());
			psk.setStorageId(productStock.getStorageId());
			psk.setMaterId(productStock.getMaterId());
			ProductStock existStock = productStockMapper.selectByPrimaryKey(psk);
			if (existStock == null) {
				productStockMapper.insertSelective(productStock);
			} else {
				productStockMapper.updateByPrimaryKeySelective(productStock);
			}
		}

		return 1;
	}

	// (盘点)状态提交

	public Integer checkSubmit(String storageId) {
		return productStockMapper.checkSubmit(storageId);
	}

}
