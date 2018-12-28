package com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.bean.CorrectiveAction;
import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.bean.UnqualifiedPeoductBillQueryBean;
import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.bean.UnqualifiedProductBill;
import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.bean.UpbEightDReport;
import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.dao.CorrectiveActionMapper;
import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.dao.UnqualifiedProductBillMapper;
import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.dao.UpbEightDReportMapper;
import com.lyarc.tp.corp.quality.InspectionSheet.bean.InspectionSheet;
import com.lyarc.tp.corp.quality.InspectionSheet.bean.InspectionSheetDetail;
import com.lyarc.tp.corp.quality.InspectionSheet.dao.InspectionSheetDetailMapper;
import com.lyarc.tp.corp.seq.service.SeqService;
/**
 * 不合格品通知单
 * @author Administrator
 *
 */
@Service
public class UnqualifiedProductBillService{

	@Autowired
	private UnqualifiedProductBillMapper unqualifiedProductBillMapper;
	@Autowired
	private SeqService seqService;
	@Autowired
	private CorrectiveActionMapper correctiveActionMapper;
	@Autowired
	private UpbEightDReportMapper upbEightDReportMapper;
	
	/**
	 * 新建不合格品通知单(导入采购报检单)
	 * 
	 * @param record
	 * @return
	 */
	public Integer add( UnqualifiedProductBill record) {
		String unqualifiedId = getEiaId();
		record.setUnqualifiedId(unqualifiedId);
		record.setInspectionId(unqualifiedId);
		record.setInspectionApplyId(unqualifiedId);
		Integer row = unqualifiedProductBillMapper.insertSelective(record);	
		return row;
	}
	
	
	
	/**
	 * 主页
	 * @param queryBean
	 * @return
	 */
	public List<UnqualifiedPeoductBillQueryBean> list(UnqualifiedPeoductBillQueryBean queryBean) {
        List<UnqualifiedPeoductBillQueryBean> unqualifiedProductBillList = unqualifiedProductBillMapper.list(queryBean);
        return unqualifiedProductBillList;
    }

    public Long count(UnqualifiedPeoductBillQueryBean queryBean) {
        Long count =  unqualifiedProductBillMapper.count(queryBean);
        return count;
    }
	
    /**
     * 编辑
     */
    public Integer update(UnqualifiedProductBill record) {
    	Integer row = unqualifiedProductBillMapper.updateByPrimaryKeySelective(record);
    	return row;
    }

    /**
     * 自动生成流水号
     * @return
     */
    public String getEiaId(){
    	return seqService.getBillId(new Date(), "t_inquire", "");
    }

    /**
     * 纠正措施添加
     * @param record
     * @return
     */
	public Integer insertCorrertive(CorrectiveAction record) {
		String remedialActionId = getEiaId();
		record.setRemedialActionId(remedialActionId);
		Integer id = correctiveActionMapper.insertCorrertive(record);
		return id;
	}

	/**
	 * 8D报告添加
	 * @param record
	 * @return
	 */
	public Integer insertUpbEightD(UpbEightDReport record) {
		String fileId = getEiaId();
		record.setFileId(fileId);
		Integer id = upbEightDReportMapper.insertUpbEightD(record);
		return id;
	}
	
}
