package com.lyarc.tp.corp.purchase.provider.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lyarc.tp.corp.common.DateUtil;
import com.lyarc.tp.corp.common.util.CommonUtil;
import com.lyarc.tp.corp.contract.contract.bean.Contract;
import com.lyarc.tp.corp.purchase.provider.bean.*;
import com.lyarc.tp.corp.purchase.provider.constant.ProviderConstant;
import com.lyarc.tp.corp.purchase.provider.dao.NewProviderMaterialDetailMapper;
import com.lyarc.tp.corp.purchase.provider.dao.NewProviderMaterialMapper;
import com.lyarc.tp.corp.purchase.provider.dao.ProviderMaterialMapper;
import com.lyarc.tp.corp.purchase.provider.dto.NewProviderMaterialDetailDto;
import com.lyarc.tp.corp.purchase.provider.dto.NewProviderMaterialDto;
import com.lyarc.tp.corp.purchase.provider.form.NewProviderMaterialDetailForm;
import com.lyarc.tp.corp.purchase.provider.form.NewProviderMaterialForm;
import com.lyarc.tp.corp.system.material.dao.MaterialMapper;
import com.lyarc.tp.corp.util.JMapperUtils;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEndEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowOperLog;
import com.lyarc.tp.corp.workflow.dao.WorkflowOperLogMapper;
import com.lyarc.tp.corp.workflowact.constant.OperLogEnum;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class NewProviderMaterialService {
	@Autowired
	private NewProviderMaterialMapper newProviderMaterialMapper;
	@Autowired
	private NewProviderMaterialDetailMapper newProviderMaterialDetailMapper;
	@Autowired
	private WorkflowOperLogMapper workflowOperLogMapper;

	@Autowired
	private MaterialMapper materialMapper;
	@Autowired
	private ProviderMaterialMapper providerMaterialMapper;

	public List<NewProviderMaterialDto> list(NewProviderMaterialQueryBean queryBean) {
		List<NewProviderMaterialDto> newProviderMaterialList = newProviderMaterialMapper.list(queryBean);
		return newProviderMaterialList;
	}

	public Long count(NewProviderMaterialQueryBean queryBean) {
		Long count =  newProviderMaterialMapper.count(queryBean);
		return count;
	}

	@Transactional
	public Integer delete(String newProviderMaterialId) {
		//删除明细
		newProviderMaterialDetailMapper.deleteByNpmId(newProviderMaterialId);
		int rows = newProviderMaterialMapper.deleteByPrimaryKey(newProviderMaterialId);
		return rows;
	}

	@Transactional
	public Integer update(NewProviderMaterialForm newProviderMaterialForm) {
		NewProviderMaterial newProviderMaterial = JMapperUtils.getDestination(newProviderMaterialForm, NewProviderMaterial.class, NewProviderMaterialForm.class);
		newProviderMaterial.setUpdateTime(LocalDateTime.now());
		int rows = newProviderMaterialMapper.updateByPrimaryKeySelective(newProviderMaterial);
		String newProviderMaterialId = newProviderMaterial.getNpmId();
		//删除明细
		newProviderMaterialDetailMapper.deleteByNpmId(newProviderMaterialId);

		//插入明细
		List<NewProviderMaterialDetailForm> detailList = newProviderMaterialForm.getDetailList();
		detailList.forEach(d->{
			d.setNpmId(newProviderMaterialId);
			NewProviderMaterialDetail newProviderMaterialDetail = JMapperUtils.getDestination(d, NewProviderMaterialDetail.class, NewProviderMaterialDetailForm.class);
			newProviderMaterialDetailMapper.insertSelective(newProviderMaterialDetail);
		});
		return rows;
	}

	@Transactional
	public Integer completeReport(NewProviderMaterialForm newProviderMaterialForm) {

		NewProviderMaterial newProviderMaterial = new NewProviderMaterial();
		newProviderMaterial.setNpmId(newProviderMaterialForm.getNpmId());
		newProviderMaterial.setUpdateTime(LocalDateTime.now());
		newProviderMaterial.setReportStatus(2);//已反馈
		newProviderMaterial.setReporter(newProviderMaterialForm.getReporter());
		newProviderMaterial.setReportSubmitTime(LocalDate.now());
		int rows = newProviderMaterialMapper.updateByPrimaryKeySelective(newProviderMaterial);
		String newProviderMaterialId = newProviderMaterial.getNpmId();

		//修改明细
		List<NewProviderMaterialDetailForm> detailList = newProviderMaterialForm.getDetailList();
		detailList.forEach(d->{
			NewProviderMaterialDetail newProviderMaterialDetail = new NewProviderMaterialDetail();
			newProviderMaterialDetail.setNpmId(newProviderMaterialId);
			newProviderMaterialDetail.setMaterId(d.getMaterId());
			newProviderMaterialDetail.setFiles(d.getFiles()==null ? "" : d.getFiles());
			newProviderMaterialDetailMapper.updateByPrimaryKeySelective(newProviderMaterialDetail);
		});
		return rows;
	}

	@Transactional
	public Integer update(NewProviderMaterialDto newProviderMaterialDto) {
		NewProviderMaterial newProviderMaterial = JMapperUtils.getDestination(newProviderMaterialDto, NewProviderMaterial.class, NewProviderMaterialDto.class);
		newProviderMaterial.setUpdateTime(LocalDateTime.now());
		int rows = newProviderMaterialMapper.updateByPrimaryKeySelective(newProviderMaterial);
		String newProviderMaterialId = newProviderMaterial.getNpmId();
		//删除明细
		newProviderMaterialDetailMapper.deleteByNpmId(newProviderMaterialId);

		//插入明细
		List<NewProviderMaterialDetailDto> detailList = newProviderMaterialDto.getDetailList();
		detailList.forEach(d->{
			d.setNpmId(newProviderMaterialId);
			NewProviderMaterialDetail newProviderMaterialDetail = JMapperUtils.getDestination(d, NewProviderMaterialDetail.class, NewProviderMaterialDetailDto.class);
			newProviderMaterialDetailMapper.insertSelective(newProviderMaterialDetail);
		});
		return rows;
	}

	@Transactional
	public Integer updateNewProviderMaterial(NewProviderMaterial newProviderMaterial) {
		int rows = newProviderMaterialMapper.updateByPrimaryKeySelective(newProviderMaterial);
		return rows;
	}

	/**
	 *
	 * @description 添加供应商新材料 并将物料插入到供应商名录中 该材料状态为备选 ，新材料审核通过后 该材料状态设置为合格
	 * @param
	 * @return
	 * @author wangting
	 * @date 2018/9/3 0003 16:26
	 *
	 */
	@Transactional
	public Integer add(NewProviderMaterialForm newProviderMaterialForm) {
		NewProviderMaterial newProviderMaterial = JMapperUtils.getDestination(newProviderMaterialForm, NewProviderMaterial.class, NewProviderMaterialForm.class);

		String newProviderMaterialId = getNewProviderMaterialId();
		newProviderMaterial.setNpmId(newProviderMaterialId);
		newProviderMaterial.setReportStatus(1);
		int row = newProviderMaterialMapper.insertSelective(newProviderMaterial);

		//插入明细
		List<NewProviderMaterialDetailForm> detailList = newProviderMaterialForm.getDetailList();
		detailList.forEach(d->{
			d.setNpmId(newProviderMaterialId);
			NewProviderMaterialDetail newProviderMaterialDetail =
					JMapperUtils.getDestination(d, NewProviderMaterialDetail.class, NewProviderMaterialDetailForm.class);
			newProviderMaterialDetail.setNpmId(newProviderMaterialId);
			newProviderMaterialDetailMapper.insertSelective(newProviderMaterialDetail);

			//检查是否存在
			ProviderMaterialKey key = new ProviderMaterialKey();
			key.setProviderId(newProviderMaterial.getProviderId());
			key.setMaterId(d.getMaterId());
			ProviderMaterial pd = providerMaterialMapper.selectByPrimaryKey(key);
			if(pd == null || StringUtils.isEmpty(pd.getProviderId())){
				//插入供应商名录
				ProviderMaterial providerMaterial = new ProviderMaterial();
				providerMaterial.setMaterId(d.getMaterId());
				providerMaterial.setOrigin(d.getOrigin());
				providerMaterial.setUse(d.getUse());
				providerMaterial.setRemark(d.getRemark());
				providerMaterial.setPrice(d.getPrice());
				providerMaterial.setProviderId(newProviderMaterial.getProviderId());
				providerMaterial.setStatus(1);//备用
				providerMaterialMapper.insertSelective(providerMaterial);
			}
		});
		return row;
	}

	//    private String getNewProviderMaterialId() {
	//        return CommonUtil.createUUID();
	//    }

	public String getNewProviderMaterialId(){
		//获取当前日期
		String currentDate = DateUtil.getDateStr(new Date(), "yyyyMMdd");
		Integer sequence = 0;
		synchronized (this) {
			sequence = newProviderMaterialMapper.createSequence(currentDate);
		}
		//得到一个NumberFormat的实例
		NumberFormat nf = NumberFormat.getInstance();
		//设置是否使用分组
		nf.setGroupingUsed(false);
		//设置最大整数位数
		nf.setMaximumIntegerDigits(4);
		//设置最小整数位数
		nf.setMinimumIntegerDigits(4);
		//输出测试语句
		return currentDate + nf.format(sequence);
	}

	/**
	 *
	 * @description 获取询价单详情
	 * @param
	 * @return
	 * @author wangting
	 * @date 2018/7/27 0027 16:39
	 *
	 */
	public NewProviderMaterialDto getNewProviderMaterial(String newProviderMaterialId) {
		NewProviderMaterialDto newProviderMaterial = newProviderMaterialMapper.selectAndDetail(newProviderMaterialId);
		return newProviderMaterial;
	}

	/**
	 *
	 * @description 新材料供应通知单审核回调
	 * @param
	 * @return
	 * @author wangting
	 * @date 2018/8/16 0016 16:33
	 *
	 */
	@Transactional
	public void newProviderMaterialAuditCallBack(WorkflowInstEndEvent endEvent){
		//获取业务单据
		NewProviderMaterial newProviderMaterial = newProviderMaterialMapper.selectByPrimaryKey(endEvent.getBusinessKey());

		//判断业务单据和流程实例关系
		if(newProviderMaterial != null && newProviderMaterial.getWfInstId().equals(endEvent.getInstId())) {
			if(newProviderMaterial.getWfStatus() == 1) {
				NewProviderMaterial newProviderMaterialBean = new NewProviderMaterial();
				newProviderMaterialBean.setNpmId(endEvent.getBusinessKey());
				// 通过
				if(endEvent.getCause() == 1) {
					newProviderMaterialBean.setWfStatus(2);
				}
				// 驳回
				if(endEvent.getCause() == 2) {
					newProviderMaterialBean.setWfStatus(3);
					newProviderMaterialBean.setWfInstRemark(endEvent.getRemark());
				}
				// 保存
				newProviderMaterialMapper.updateByPrimaryKeySelective(newProviderMaterialBean);
				
				//将该物料在供应商名录中状态设置为合格
				List<NewProviderMaterialDetail> newProviderMaterialDetails = newProviderMaterialDetailMapper.selectByNpmId(endEvent.getBusinessKey());
				
				newProviderMaterialDetails.forEach(d->{
					ProviderMaterial pm = new ProviderMaterial();
					pm.setProviderId(newProviderMaterial.getProviderId());
					pm.setMaterId(d.getMaterId());
					pm.setStatus(2);//合格
					providerMaterialMapper.updateByPrimaryKeySelective(pm);
				});
			}
		}
	}

}
