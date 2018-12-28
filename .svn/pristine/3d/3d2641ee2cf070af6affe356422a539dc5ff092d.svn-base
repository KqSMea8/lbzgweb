package com.lyarc.tp.corp.warehouse.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.warehouse.bean.Stock;
import com.lyarc.tp.corp.warehouse.bean.StockBillBean;
import com.lyarc.tp.corp.warehouse.bean.StockBillDetail;
import com.lyarc.tp.corp.warehouse.dao.StockMapper;

@Service
public class StockCheckService {
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private StockBillService stockBillService;

	public Integer pass(String storageId) {
		// (查询)要盘点的记录
		List<Stock> stocks = stockMapper.loadCheck(storageId, 2);
		// 实例化清单
		StockBillBean stockBillBean = new StockBillBean();
		// 实例化明细集合
		List<StockBillDetail> detailList = new ArrayList<StockBillDetail>();
		for (Stock stock : stocks) {
			if (stock.getCheckQuantity() != null) {
				BigDecimal quantity = new BigDecimal("0");
				quantity = stock.getCheckQuantity().subtract(stock.getQuantity());
				stock.setQuantity(stock.getCheckQuantity());

				StockBillDetail detail = new StockBillDetail();
				// 构建detail
				detail.setMaterId(stock.getMaterId());
				detail.setMaterId(stock.getMaterId());
				detail.setMaterName(stock.getMaterName());
				detail.setSpec(stock.getSpec());
				detail.setModel(stock.getModel());
				detail.setUnit(stock.getUnit());
				detail.setBatch(stock.getBatch());
				detail.setPrice(stock.getPrice());
				detail.setAmount(stock.getAmount());
				detail.setQuantity(quantity);

				detailList.add(detail);
			}
			// 改状态
			stock.setCheckStatus(0);
			stock.setCheckQuantity(null);
			stockMapper.checkUpdate(stock);
		}
		// 清单中添加明细
		stockBillBean.setDetailList(detailList);
		stockBillBean.setStorageId(storageId);
		stockBillBean.setOperId("pdtk");
		stockBillBean.setOperType(4);
		stockBillBean.setOperSource(3);
		stockBillBean.setOperator("");
		stockBillBean.setBillDate(LocalDate.now());

		stockBillService.add(stockBillBean);
		// 插入清单

		return 1;

	}

}
