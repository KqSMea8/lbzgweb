package com.lyarc.tp.corp.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.warehouse.bean.Stock;
import com.lyarc.tp.corp.warehouse.bean.StockKey;
import com.lyarc.tp.corp.warehouse.bean.StockQueryBean;
import com.lyarc.tp.corp.warehouse.dao.StockMapper;

@Service
public class StockService {
	@Autowired
	private StockMapper stockMapper;

	public PageResultBean list(StockQueryBean stock) {
		List<Stock> stockList = stockMapper.list(stock);
		Long count = stockMapper.count(stock);
		return PageResultBean.success(count, stockList);
	}
	
	public PageResultBean listMater(StockQueryBean stock) {
		List<Stock> stockList = stockMapper.listMater(stock);
		Long count = stockMapper.countMater(stock);
		return PageResultBean.success(count, stockList);
	}

	public Integer delete(StockKey stockKey) {
		int rows = stockMapper.deleteByPrimaryKey(stockKey);
		return rows;
	}

	public Integer update(Stock stock) {
		int rows = stockMapper.updateByPrimaryKeySelective(stock);
		return rows;
	}

	public Stock getStock(StockKey spkey) {
		Stock stock = stockMapper.selectByPrimaryKey(spkey);
		return stock;
	}

	@Transactional
	public Integer addOrUpdate(List<Stock> stocks) {
		for (Stock stock : stocks) {
			StockKey sk = new StockKey();
			sk.setMaterId(stock.getMaterId());
			sk.setStorageId(stock.getStorageId());
			sk.setPlaceId(stock.getPlaceId());

			Stock existStock = stockMapper.selectByPrimaryKey(sk);
			if (existStock == null) {
				stockMapper.insertSelective(stock);
			} else {
				stockMapper.updateByPrimaryKeySelective(stock);
			}
		}
		return 1;
	}

	/**
	 * 批量仓库盘点
	 */
	public Integer checkBatchStatus(List<Stock> stocks) {// 循环调用接口实现mapper的方法
		for (Stock stock : stocks) {
			stockMapper.checkBatchStatus(stock);// 传入的List集合，循环更新每一条的信息（4个主键匹配好，即可更新状态字段）
		}
		return 1;
	}
	
	/**
	 * 批量仓库盘点
	 */
	@Transactional
	public Integer checkSubmit(String storageId) { 
		return stockMapper.checkSubmit(storageId);
	} 
	
}
