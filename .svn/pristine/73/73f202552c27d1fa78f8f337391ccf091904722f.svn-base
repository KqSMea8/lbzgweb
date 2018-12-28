package com.lyarc.tp.corp.inspection.service;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.common.DateUtil;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.inspection.bean.InspectionBean;
import com.lyarc.tp.corp.inspection.bean.InspectionDetailBean;
import com.lyarc.tp.corp.inspection.dao.InspectionDetailMapper;
import com.lyarc.tp.corp.inspection.dao.InspectionMapper;
import com.lyarc.tp.corp.inspection.dto.InspectionDTO;
import com.lyarc.tp.corp.inspection.form.InspectionDetailForm;
import com.lyarc.tp.corp.inspection.form.InspectionForm;
import com.lyarc.tp.corp.inspection.form.InspectionQueryForm;
import com.lyarc.tp.corp.util.JMapperUtils;

/**
 * 业务处理类
 * 
 * @author shiyong
 *
 */
@Service
public class InspectionService {
	@Autowired
	private InspectionMapper inspectionMapper;

	@Autowired
	private InspectionDetailMapper inspectionDetailMapper;

	/**
	 * 新建
	 * 
	 * @param inspectionForm
	 * @return
	 */
	public Integer add(InspectionForm inspectionForm) {

		InspectionBean inspectionBean = JMapperUtils.getDestination(inspectionForm, InspectionBean.class,
				InspectionForm.class);

		String inspectionApplyId = getInspectionApplyId();// 生成单号
		inspectionBean.setInspectionApplyId(inspectionApplyId);
		inspectionBean.setStatus(0);// 创建
		int row = inspectionMapper.add(inspectionBean);// 新建主数据

		List<InspectionDetailForm> inspectionDetailForms = inspectionForm.getDetailList();// 获取检验单的详情
		inspectionDetailForms.forEach(d -> {
			InspectionDetailBean inspectionDetailBean = JMapperUtils.getDestination(d, InspectionDetailBean.class,
					InspectionDetailForm.class);
			inspectionDetailBean.setInspectionApplyId(inspectionApplyId);// 绑定单号
			inspectionDetailMapper.insertSelective(inspectionDetailBean);
		});

		return row;
	}

	public String getInspectionApplyId() {// 单号调用存储过程，按一定规则，新生成
		// 获取当前日期
		String currentDate = DateUtil.getDateStr(new Date(), "yyyyMMdd");
		Integer sequence = 0;
		synchronized (this) {
			sequence = inspectionMapper.createSequence(currentDate);
		}
		// 得到一个NumberFormat的实例
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组
		nf.setGroupingUsed(false);
		// 设置最大整数位数
		nf.setMaximumIntegerDigits(4);
		// 设置最小整数位数
		nf.setMinimumIntegerDigits(4);
		// 输出测试语句
		return "R" + currentDate + nf.format(sequence);
	}

	/**
	 * id是否存在
	 * 
	 * @param inspectionApplyId
	 * @return
	 */
	public Integer selectedId(String inspectionApplyId) {
		return inspectionMapper.selectedId(inspectionApplyId);
	}

	/**
	 * 获取详情
	 */
	public InspectionDTO get(String inspectionApplyId) {
		InspectionDTO inspectionDTO = inspectionMapper.get(inspectionApplyId);
		return inspectionDTO;
	}

	/**
	 * 删除
	 */
	public Integer delete(String inspectionApplyId) {
		Integer id = inspectionMapper.delete(inspectionApplyId);
		return id;
	}

	/**
	 * 更新报检单
	 */
	public Integer update(InspectionForm inspectionForm) {
		inspectionForm.setUpdateTime(LocalDateTime.now());
		int rows = inspectionMapper.update(inspectionForm);

		String inspectionApplyId = inspectionForm.getInspectionApplyId();
		inspectionDetailMapper.deleteByInspectionApplyId(inspectionApplyId);// 先删除掉之前的旧数据

		// 插入明细
		List<InspectionDetailForm> detailList = inspectionForm.getDetailList();
		detailList.forEach(d -> {
			d.setInspectionApplyId(inspectionApplyId);
			InspectionDetailBean inspectionDetailBean = JMapperUtils.getDestination(d, InspectionDetailBean.class,
					InspectionDetailForm.class);
			inspectionDetailMapper.insertSelective(inspectionDetailBean);// 3 插入列表新数据
		});
		return rows;
	}

	/**
	 * 报检单的列表
	 */
	public PageResultBean list(InspectionQueryForm inspectionQueryForm) {
		List<InspectionDTO> inspectionForms = inspectionMapper.list(inspectionQueryForm);// 列表
		Long count = inspectionMapper.count(inspectionQueryForm);// 计数
		return PageResultBean.success(count, inspectionForms);
	}

}
