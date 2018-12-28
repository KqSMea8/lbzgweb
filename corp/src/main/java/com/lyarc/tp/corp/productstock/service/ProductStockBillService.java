package com.lyarc.tp.corp.productstock.service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.common.DateUtil;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.productstock.bean.ProductStock;
import com.lyarc.tp.corp.productstock.bean.ProductStockBillBean;
import com.lyarc.tp.corp.productstock.bean.ProductStockBillDetail;
import com.lyarc.tp.corp.productstock.bean.ProductStockBillQueryBean;
import com.lyarc.tp.corp.productstock.bean.ProductStockKey;
import com.lyarc.tp.corp.productstock.dao.ProductStockBillDetailMapper;
import com.lyarc.tp.corp.productstock.dao.ProductStockBillMapper;
import com.lyarc.tp.corp.productstock.dao.ProductStockMapper;

@Service
public class ProductStockBillService {
	@Autowired
	private ProductStockBillMapper productStockBillMapper;
	@Autowired
	private ProductStockBillDetailMapper productStockBillDetailMapper;
	@Autowired
	private ProductStockMapper productStockMapper;

	public PageResultBean list(ProductStockBillQueryBean productStockBillQueryBean) {
		List<ProductStockBillBean> productStockBillList = productStockBillMapper.list(productStockBillQueryBean);
		Long count = productStockBillMapper.count(productStockBillQueryBean);
		return PageResultBean.success(count, productStockBillList);
	}

	public Integer add(ProductStockBillBean productStockBillBean) {
		String productStockBillId = productStockBillBean.getStockBillId();
		if (productStockBillId == null || "".equals(productStockBillId)) {
			productStockBillId = getProductStockBillId(productStockBillBean.getOperType(),
					productStockBillBean.getBillDate());
			productStockBillBean.setStockBillId(productStockBillId);
		}
		// 插入非明细的部分
		int row = productStockBillMapper.insertSelective(productStockBillBean);
		// 插入明细
		List<ProductStockBillDetail> productStockBillDetails = productStockBillBean.getDetailList();
		productStockBillDetails.forEach(d -> {
			d.setStockBillId(productStockBillBean.getStockBillId());
			productStockBillDetailMapper.insertSelective(d);
		});
		return row;
	}

	public String getProductStockBillId(Integer operType, LocalDate billDate) {
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = billDate.atStartOfDay().atZone(zone).toInstant();
		java.util.Date date = Date.from(instant);
		return getProductStockBillId(operType, date);
	}

	/*
	 * 1.入库 2.出库 3.移库 4.调库
	 */
	public String getProductStockBillId(Integer operType, Date localDate) {// 单号调用存储过程，按一定规则，新生成
		// 获取当前日期
		String currentDate = DateUtil.getDateStr(localDate, "yyyyMMdd");
		Integer sequence = 0;
		synchronized (this) {
			sequence = productStockBillMapper.createSequence(currentDate);
		}
		// 得到一个NumberFormat的实例
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组
		nf.setGroupingUsed(false);
		// 设置最大整数位数
		nf.setMaximumIntegerDigits(4);
		// 设置最小整数位数
		nf.setMinimumIntegerDigits(4);
		String billNo = currentDate + nf.format(sequence);
		if (operType == 1) {
			return 'I' + billNo;
		}
		if (operType == 2) {
			return 'O' + billNo;
		}
		if (operType == 3) {
			return 'M' + billNo;
		}
		if (operType == 4) {
			return 'A' + billNo;
		}
		return billNo;
	}

	// 更新
	public Integer update(ProductStockBillBean productStockBillBean) {
		productStockBillBean.setUpdateTime(new Date());
		int rows = productStockBillMapper.updateByPrimaryKeySelective(productStockBillBean);// 1更新主数据
		String stockBillId = productStockBillBean.getStockBillId();
		// 删除明细
		productStockBillDetailMapper.deleteByInStockId(stockBillId);// 2 删除列表中的旧数据

		// 插入明细
		List<ProductStockBillDetail> productStockBillDetails = productStockBillBean.getDetailList();
		productStockBillDetails.forEach(d -> {
			d.setStockBillId(productStockBillBean.getStockBillId());
			productStockBillDetailMapper.insertSelective(d);
		});
		return rows;
	}

	public ProductStockBillBean selectedObject(String stockBillId) {
		return productStockBillMapper.selectedObject(stockBillId);
	}

	// 获取详情
	public ProductStockBillBean getBill(String stockBillId) {
		ProductStockBillBean bill = productStockBillMapper.getBill(stockBillId);
		if (bill != null) {
			bill.setStockBillId(stockBillId);
		}
		return bill;
	}

	// 删除
	public Integer delete(String stockBillId) {
		Integer id = productStockBillMapper.delete(stockBillId);
		return id;
	}

	// 提交
	public Integer publicStatus(String stockBillId) {
		return productStockBillMapper.publicStatus(stockBillId);
	}

	// 审核通过
	public Integer passStatus(String stockBillId) {
		// 得到表单对象
		ProductStockBillBean bill = getBill(stockBillId);
		if (bill == null) {
			throw new RuntimeException("订单不存在,订单编号：" + stockBillId);
		}
		if (bill.getStatus() != 1) {
			throw new RuntimeException("单据不是待审核状态,订单编号：" + stockBillId);
		}
		//
		for (ProductStockBillDetail detail : bill.getDetailList()) {
			// 获得一个ProductStock
			ProductStock productStock = productStockMapper.selectByPrimaryKey(
					ProductStockKey.create(bill.getStorageId(), detail.getMaterId(), detail.getCasting()));
			// Stock存在
			if (productStock != null) {
				if (bill.getOperType() == 2 || bill.getOperType() == 3) {
					// ProductStock中的数量变少
					productStock.setQuantity(productStock.getQuantity().subtract(detail.getQuantity()));
					productStock.setCheckQuantity(null);
				} else {
					// ProductStock中的数量变多
					productStock.setQuantity(productStock.getQuantity().add(detail.getQuantity()));
				}
				productStockMapper.updateByPrimaryKeySelective(productStock);
			} else {
				productStock = new ProductStock();
				productStock.setStorageId(bill.getStorageId());
				productStock.setMaterId(detail.getMaterId());
				productStock.setMaterName(detail.getMaterName());
				productStock.setUnit(detail.getUnit());
				productStock.setQuantity(detail.getQuantity());
				productStock.setCasting(detail.getCasting());
				if (bill.getOperType() == 2 || bill.getOperType() == 3) {
					productStock.setQuantity(detail.getQuantity().multiply(new BigDecimal(-1)));
				}
				productStockMapper.insertSelective(productStock);
			}
		}
		//
		productStockBillMapper.passStatus(stockBillId);
		return 1;
	}

	// 驳回
	public Integer rejectStatus(String stockBillId, String auditRemark) {

		return productStockBillMapper.rejectStatus(stockBillId, auditRemark);
	}
}