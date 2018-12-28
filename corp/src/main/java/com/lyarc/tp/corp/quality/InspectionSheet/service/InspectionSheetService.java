package com.lyarc.tp.corp.quality.InspectionSheet.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.inspection.bean.InspectionDetailBean;
import com.lyarc.tp.corp.quality.InspectionSheet.bean.InspectionSheet;
import com.lyarc.tp.corp.quality.InspectionSheet.bean.InspectionSheetDetail;
import com.lyarc.tp.corp.quality.InspectionSheet.bean.InspectionSheetQueryBean;
import com.lyarc.tp.corp.quality.InspectionSheet.dao.InspectionSheetDetailMapper;
import com.lyarc.tp.corp.quality.InspectionSheet.dao.InspectionSheetMapper;
import com.lyarc.tp.corp.seq.service.SeqService;
/**
 * 检验单
 * @author Administrator
 *
 */
@Service
public class InspectionSheetService {
	@Autowired
	private InspectionSheetMapper inspectionSheetMapper;
	@Autowired
	private InspectionSheetDetailMapper inspectionSheetDetailMapper;
	@Autowired
	private SeqService seqService;
	
	/**
	 *	添加明细
	 * @param record
	 * @return
	 */
	public Integer add(InspectionSheetDetail record) {
		Integer row = inspectionSheetDetailMapper.insertSelective(record);
		return row;
	}
	
	/**
	 * 
	 * @Desc添加主表
	 * @Param
	 * @Return
	 */
	public Integer addMain(InspectionSheet record) {
		Integer row =  inspectionSheetMapper.addMain(record);
		return row;
	}
	
	//流水号
	public String getIsId(){
    	return seqService.getBillId(new Date(), "t_inquire", "");
    }

	//分页
	public List<InspectionSheet> list(InspectionSheet bean){
		List<InspectionSheet> inspectionSheets = inspectionSheetMapper.list(bean);
		for (InspectionSheet inspectionSheet : inspectionSheets) {
			List<InspectionSheetDetail> detailList = inspectionSheetDetailMapper
					.selectByPrimaryKey(bean.getInspectionApplyId(), bean.getMaterId());
			inspectionSheet.setDetailList(detailList);
		}
		return inspectionSheets;
	}

	public Long count(InspectionSheet bean) {
 		Long row =  inspectionSheetMapper.count(bean);
		return row;
	}



	
	
	
}
