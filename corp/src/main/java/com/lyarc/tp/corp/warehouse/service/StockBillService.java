package com.lyarc.tp.corp.warehouse.service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyarc.tp.corp.common.DateUtil;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.purchase.order.dao.InStockApplyMapper;
import com.lyarc.tp.corp.util.JMapperUtils;
import com.lyarc.tp.corp.warehouse.bean.Stock;
import com.lyarc.tp.corp.warehouse.bean.StockBillBean;
import com.lyarc.tp.corp.warehouse.bean.StockBillDetail;
import com.lyarc.tp.corp.warehouse.bean.StockBillQueryBean;
import com.lyarc.tp.corp.warehouse.bean.StockKey;
import com.lyarc.tp.corp.warehouse.dao.StockBillDetailMapper;
import com.lyarc.tp.corp.warehouse.dao.StockBillMapper;
import com.lyarc.tp.corp.warehouse.dao.StockMapper;
import com.lyarc.tp.corp.warehouse.form.StockBillDetailForm;
import com.lyarc.tp.corp.warehouse.form.StockBillForm;

/**
 * 仓库清单业务实现类
 * 
 * @author shiyong
 *
 */
@Service
public class StockBillService {
	@Autowired
	private StockBillMapper stockBillMapper;
	@Autowired
	private StockBillDetailMapper stockBillDetailMapper;
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private InStockApplyMapper inStockApplyMapper;

	public PageResultBean list(StockBillQueryBean bean) {
		List<StockBillBean> stockBillList = stockBillMapper.list(bean);// 列表
		Long count = stockBillMapper.count(bean);// 计数
		return PageResultBean.success(count, stockBillList);
	}

	public Integer add(StockBillForm stockBillForm) {
		// 转型
		StockBillBean stockBillBean = JMapperUtils.getDestination(stockBillForm, StockBillBean.class,
				StockBillForm.class);
		// 获得清单号
		String stockBillId = getStockBillId(stockBillBean.getOperType(), stockBillBean.getBillDate());// 单号调用存储过程，按一定规则，新生成=====================
		stockBillBean.setStockBillId(stockBillId);
		// 设置清单状态
		stockBillBean.setStatus(0);

		List<StockBillDetail> details = new ArrayList<StockBillDetail>();

		stockBillBean.setDetailList(details);
		// 获取stockBillForm(清单)带过来的明细
		List<StockBillDetailForm> stockBillDetailForms = stockBillForm.getDetailList();
		// 遍历明细
		stockBillDetailForms.forEach(d -> {
			// 转型
			StockBillDetail stockBillDetail = JMapperUtils.getDestination(d, StockBillDetail.class,
					StockBillDetailForm.class);
			// stockBillDetail中添加stockBillDetail
			details.add(stockBillDetail);
			// 设置主键
			stockBillDetail.setStockBillId(stockBillId);
		});
		Integer row = add(stockBillBean);
		return row;
	}

	@Transactional
	public Integer add(StockBillBean stockBillBean) {

		String stockBillId = stockBillBean.getStockBillId();
		if (stockBillId == null || "".equals(stockBillId)) {
			stockBillId = getStockBillId(stockBillBean.getOperType(), stockBillBean.getBillDate());
			stockBillBean.setStockBillId(stockBillId);
		}
		// 插入非明细的部分

		int row = stockBillMapper.insertSelective(stockBillBean);

		// 插入明细
		List<StockBillDetail> stockBillDetails = stockBillBean.getDetailList();
		stockBillDetails.forEach(d -> {

			d.setStockBillId(stockBillBean.getStockBillId());
			stockBillDetailMapper.insertSelective(d);
		});
		if (stockBillBean.getInStockApplyId() != null || "".equals(stockBillBean.getInStockApplyId())) {
			inStockApplyMapper.pass(stockBillBean.getInStockApplyId(), stockBillId);
		}
		return row;
	}

	public String getStockBillId(Integer operType, LocalDate billDate) {
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = billDate.atStartOfDay().atZone(zone).toInstant();
		java.util.Date date = Date.from(instant);
		return getStockBillId(operType, date);
	}

	/*
	 * 1.入库 2.出库 3.移库 4.调库
	 */
	public String getStockBillId(Integer operType, Date billDate) {// 单号调用存储过程，按一定规则，新生成
		// 获取当前日期
		String currentDate = DateUtil.getDateStr(billDate, "yyyyMMdd");
		Integer sequence = 0;
		synchronized (this) {
			sequence = stockBillMapper.createSequence(currentDate);
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

	// 提交
	public Integer publicStatus(String stockBillId) {
		return stockBillMapper.publicStatus(stockBillId);
	}

	// 驳回
	public Integer rejectStatus(String stockBillId, String auditRemark) {

		return stockBillMapper.rejectStatus(stockBillId, auditRemark);
	}

	// 获取详情
	public StockBillBean getInStock(String stockBillId) {
		StockBillBean stockBillBean = stockBillMapper.getBill(stockBillId);
		return stockBillBean;
	}

	// 更新
	@Transactional
	public Integer update(StockBillBean stockBillBean) {

		stockBillBean.setUpdateTime(LocalDateTime.now());
		int rows = stockBillMapper.updateByPrimaryKeySelective(stockBillBean);// 1更新主数据
		String stockBillId = stockBillBean.getStockBillId();
		// 删除明细
		stockBillDetailMapper.deleteByInStockId(stockBillId);// 2 删除列表中的旧数据

		// 插入明细
		List<StockBillDetail> stockBillDetails = stockBillBean.getDetailList();
		stockBillDetails.forEach(d -> {
			d.setStockBillId(stockBillBean.getStockBillId());
			stockBillDetailMapper.insertSelective(d);
		});
		return rows;
	}

	// 删除
	public Integer delete(String stockBillId) {
		Integer id = stockBillMapper.delete(stockBillId);
		return id;
	}

	// 删除明细
		public Integer deleteByInStockId(String stockBillId) {
			Integer id = stockBillDetailMapper.deleteByInStockId(stockBillId);
			return id;
		}

	// 审核通过
	@Transactional
	public Integer passStatus(String stockBillId) {
		// 得到表单对象
		StockBillBean bill = getBill(stockBillId);
		if (bill == null) {
			throw new RuntimeException("订单不存在,订单编号：" + stockBillId);
		}
		if (bill.getStatus() != 1) {
			throw new RuntimeException("单据不是待审核状态,订单编号：" + stockBillId);
		}
		//
		for (StockBillDetail detail : bill.getDetailList()) {
			// 获得一个Stock
			Stock stock = stockMapper.selectByPrimaryKey(
					StockKey.create(bill.getStorageId(), detail.getMaterId(), detail.getPlaceId(), detail.getBatch()));
			// Stock存在
			if (stock != null) {
				if (bill.getOperType() == 2 || bill.getOperType() == 3) {
					// stock中的数量变少
					stock.setQuantity(stock.getQuantity().subtract(detail.getQuantity()));
				} else {
					// 变多
					stock.setQuantity(stock.getQuantity().add(detail.getQuantity()));
				}
				// 修改Stock的Quantity，需要做(修改stock数据)
				stockMapper.updateByPrimaryKeySelective(stock);
				// -----------------------------------------------------------------------
			} else {
				stock = new Stock();
				stock.setStorageId(bill.getStorageId());
				stock.setMaterId(detail.getMaterId());
				stock.setPlaceId(detail.getPlaceId());
				stock.setBatch(detail.getBatch());
				stock.setMaterName(detail.getMaterName());
				stock.setSpec(detail.getSpec());
				stock.setModel(detail.getModel());
				stock.setUnit(detail.getUnit());
				stock.setPrice(detail.getPrice());
				stock.setQuantity(detail.getQuantity());
				if (bill.getOperType() == 2 || bill.getOperType() == 3) {
					stock.setQuantity(detail.getQuantity().multiply(new BigDecimal(-1)));
				}
				stock.setAmount(detail.getAmount());
				// 添加一条记录
				stockMapper.insertSelective(stock);

			}
		}
		// 送审
		stockBillMapper.passStatus(stockBillId);

		if (bill.getOperType() == 3) {
			// 单据基本信息
			bill.setOperType(5);
			String storageId = bill.getStorageId();
			bill.setStorageId(bill.getRelatedStorageId());
			bill.setRelatedStorageId(storageId);
			bill.setStockBillId(bill.getStockBillId() + "-1");
			bill.setStatus(1);
			// 单据明细
			for (StockBillDetail detail : bill.getDetailList()) {
				String placeId = detail.getPlaceId();
				detail.setPlaceId(detail.getRelatedPlaceId());
				detail.setRelatedPlaceId(placeId);
			}
			// 保存
			this.add(bill);
			// 审核
			this.passStatus(bill.getStockBillId());
		}

		return 1;
	}

	//
	public StockBillBean selectedObject(String stockBillId) {
		return stockBillMapper.selectedObject(stockBillId);
	}

	public StockBillBean getBill(String stockBillId) {
		StockBillBean bill = stockBillMapper.getBill(stockBillId);
		if (bill != null) {
			bill.setStockBillId(stockBillId);
		}
		return bill;
	}
	
	

}
