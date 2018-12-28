package com.lyarc.tp.corp.quality.Instrument.InstrumentInspectionAccept.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.quality.Instrument.InstrumentInspectionAccept.bean.InstrumentInspectionAccept;
import com.lyarc.tp.corp.quality.Instrument.InstrumentInspectionAccept.bean.InstrumentInspectionAcceptDetail;
import com.lyarc.tp.corp.quality.Instrument.InstrumentInspectionAccept.dao.InstrumentInspectionAcceptDetailMapper;
import com.lyarc.tp.corp.quality.Instrument.InstrumentInspectionAccept.dao.InstrumentInspectionAcceptMapper;
import com.lyarc.tp.corp.seq.service.SeqService;

@Service
public class InstrumentInspectionAcceptService {
	
	@Autowired
	private InstrumentInspectionAcceptMapper instrumentInspectionAcceptMapper;
	@Autowired
	private InstrumentInspectionAcceptDetailMapper instrumentInspectionAcceptDetailMapper;
	@Autowired
	private SeqService seqService;
	
	public Integer insert(InstrumentInspectionAccept record) {
		String inspectionAcceptId = getIiaId();
		record.setInspectionAcceptId(inspectionAcceptId);
		int row = instrumentInspectionAcceptMapper.insertSelective(record);
		List<InstrumentInspectionAcceptDetail> IIADetailList = record.getDetailList();
//		List<InstrumentInspectionAcceptDetail> IIADetailList = new ArrayList<>();
		for(InstrumentInspectionAcceptDetail IiaDetaillist : IIADetailList) {
			IiaDetaillist.setInspectionAcceptId(inspectionAcceptId);
			//IiaDetaillist.setInstrumentId(record.getInstrumentId());
			
			instrumentInspectionAcceptDetailMapper.insertSelective(IiaDetaillist);
		}
		
		return row;
	} 

	public Integer delete(String inspectionAcceptId) {
		int rows = instrumentInspectionAcceptDetailMapper.deleteByPrimaryKey(inspectionAcceptId);
		return rows;
	}
	
	public String getIiaId(){
    	return seqService.getBillId(new Date(), "t_inquire", "");
    }
}
