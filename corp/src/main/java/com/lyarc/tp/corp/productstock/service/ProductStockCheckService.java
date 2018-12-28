package com.lyarc.tp.corp.productstock.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.productstock.bean.ProductStock;
import com.lyarc.tp.corp.productstock.bean.ProductStockBillBean;
import com.lyarc.tp.corp.productstock.bean.ProductStockBillDetail;
import com.lyarc.tp.corp.productstock.dao.ProductStockMapper;

@Service
public class ProductStockCheckService {
	@Autowired
	private ProductStockMapper productStockMapper;
	@Autowired
	private ProductStockBillService productStockBillService;

	public Integer pass(String storageId) {
		// 查询盘点的记录
		List<ProductStock> productStocks = productStockMapper.loadCheck(storageId, 2);
		ProductStockBillBean productStockBillBean = new ProductStockBillBean();
		List<ProductStockBillDetail> detailList = new ArrayList<ProductStockBillDetail>();
		for (ProductStock productStock : productStocks) {
			if (productStock.getCheckQuantity() != null) {
				BigDecimal quantity = new BigDecimal("0");
				quantity = productStock.getCheckQuantity().subtract(productStock.getQuantity());
				productStock.setQuantity(productStock.getCheckQuantity());
				ProductStockBillDetail detail = new ProductStockBillDetail();
				detail.setMaterId(productStock.getMaterId());
				detail.setMaterName(productStock.getMaterName());
				detail.setDrawing(productStock.getDrawing());
				detail.setCasting(productStock.getCasting());
				detail.setQuantity(quantity);
				detail.setWeight(productStock.getWeight());
				detailList.add(detail);
			}
			productStock.setCheckStatus(0);
			productStock.setCheckQuantity(null);
			productStockMapper.checkUpdate(productStock);
		}
		productStockBillBean.setDetailList(detailList);
		productStockBillBean.setOperType(4);
		productStockBillBean.setOperator("");
		productStockBillBean.setStorageId(storageId);
		productStockBillBean.setBillDate(LocalDate.now());
		// 添加单据
		productStockBillService.add(productStockBillBean);

		return 1;
	}
}
