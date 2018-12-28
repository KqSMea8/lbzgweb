package com.lyarc.tp.corp.quality.revision.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyarc.tp.corp.quality.revision.bean.CheckBrickTube;
import com.lyarc.tp.corp.quality.revision.bean.CheckBrickTubeDetail;
import com.lyarc.tp.corp.quality.revision.bean.CheckElectrode;
import com.lyarc.tp.corp.quality.revision.bean.CheckFerroAlloy;
import com.lyarc.tp.corp.quality.revision.bean.CheckFerroAlloyDetail;
import com.lyarc.tp.corp.quality.revision.bean.CheckLime;
import com.lyarc.tp.corp.quality.revision.bean.CheckOriginalSand;
import com.lyarc.tp.corp.quality.revision.bean.CheckResinChromeOre;
import com.lyarc.tp.corp.quality.revision.bean.CheckVMethodCoating;
import com.lyarc.tp.corp.quality.revision.bean.CheckVMethodCoatingDetail;
import com.lyarc.tp.corp.quality.revision.bean.CheckVisualInspection;
import com.lyarc.tp.corp.quality.revision.bean.CheckWaterGlass;
import com.lyarc.tp.corp.quality.revision.bean.RevisedInspection;
import com.lyarc.tp.corp.quality.revision.bean.RevisedInspectionQueryBean;
import com.lyarc.tp.corp.quality.revision.bean.TcheckRevisionLog;
import com.lyarc.tp.corp.quality.revision.bean.TcheckRevisionLogQueryBean;
import com.lyarc.tp.corp.quality.revision.dao.RevisedInspectionMapper;
import com.lyarc.tp.corp.quality.revision.dao.TcheckRevisionLogMapper;
import com.lyarc.tp.corp.quality.revision.form.CheckBrickTubeDetailForm;
import com.lyarc.tp.corp.quality.revision.form.CheckBrickTubeForm;
import com.lyarc.tp.corp.quality.revision.form.CheckFerroAlloyDetailForm;
import com.lyarc.tp.corp.quality.revision.form.CheckFerroAlloyForm;
import com.lyarc.tp.corp.quality.revision.form.CheckVMethodCoatingDetailForm;
import com.lyarc.tp.corp.quality.revision.form.CheckVMethodCoatingForm;
import com.lyarc.tp.corp.seq.service.SeqService;



@Service
public class TcheckRevisionLogService {
	@Autowired
	private TcheckRevisionLogMapper tcheckRevisionLogMapper;
	@Autowired
	private RevisedInspectionMapper revisedInspectionMapper;
	@Autowired
	private SeqService seqService;
	
	public List<TcheckRevisionLog> list(TcheckRevisionLogQueryBean queryBean) {
		List<TcheckRevisionLog> revisionList = tcheckRevisionLogMapper.list(queryBean);
		return revisionList;
	}

	public Long count(TcheckRevisionLogQueryBean queryBean) {
		Long count = tcheckRevisionLogMapper.count(queryBean);
		return count;
	}

	public List<CheckOriginalSand> getAllCheckOriginalSand(RevisedInspectionQueryBean queryBean) throws JsonParseException, JsonMappingException, IOException {
		List<CheckOriginalSand> revisedInspection = revisedInspectionMapper.getAllCheckOriginalSand(queryBean);
		for (CheckOriginalSand checkOriginalSand : revisedInspection) {
			ObjectMapper mapper = new ObjectMapper();
	        List<Object> itemOneObj = mapper.readValue(checkOriginalSand.getInspectionResult(),new TypeReference<List<Object>>() { });
	        checkOriginalSand.setItemOneObj(itemOneObj);
		}
		return revisedInspection;
	}

	public List<CheckOriginalSand> getNewCheckOriginalSand(CheckOriginalSand queryBean) throws JsonParseException, JsonMappingException, IOException {
		List<CheckOriginalSand> revisedInspection = revisedInspectionMapper.selectNewCheckOriginalSand(queryBean);
		for (CheckOriginalSand checkOriginalSand : revisedInspection) {
			ObjectMapper mapper = new ObjectMapper();
	        List<Object> itemOneObj = mapper.readValue(checkOriginalSand.getInspectionResult(),new TypeReference<List<Object>>() { });
	        checkOriginalSand.setItemOneObj(itemOneObj);
		}
		return revisedInspection;
	}

	public Integer addAllCheckOriginalSand(CheckOriginalSand queryBean) {
		String revisionId = getEiaId();//获取流水号
		if(queryBean.getVersionId()==0) {
			queryBean.setBatchId(revisionId);
			queryBean.setRevisionId(revisionId);
			revisedInspectionMapper.insertNewTcheckRevisionLog(queryBean);
		}
		queryBean.setVersionId(queryBean.getVersionId()+1);
		Integer checkOriginalSand=revisedInspectionMapper.insertAllCheckOriginalSand(queryBean);
		revisedInspectionMapper.updCheckRevisionLogVersionId(queryBean.getBatchId(), queryBean.getVersionId(),queryBean.getOperator());
		return checkOriginalSand;
	}
	
	public List<CheckBrickTubeForm> getAllCheckBrickTube(RevisedInspectionQueryBean queryBean) {
		List<CheckBrickTube> checkBrickTubes = revisedInspectionMapper.getAllCheckBrickTube(queryBean.getBatchId());
		List<CheckBrickTubeDetail> checkBrickTubeDetails = revisedInspectionMapper
				.getAllCheckBrickTubeDetail(queryBean.getBatchId());
		List<CheckBrickTubeForm> checkBrickTubesNew = new ArrayList<>();
		CheckBrickTubeForm checkBrickTubeForm = null;
		CheckBrickTubeDetailForm checkBrickTubeDetailForm = null;
		for (CheckBrickTube checkBrickTube : checkBrickTubes) {
			checkBrickTubeForm = new CheckBrickTubeForm();
			BeanUtils.copyProperties(checkBrickTube, checkBrickTubeForm);
			List<CheckBrickTubeDetailForm> checkBrickTubeDetailForms = new ArrayList<>();
			for (CheckBrickTubeDetail checkBrickTubeDetail : checkBrickTubeDetails) {
				checkBrickTubeDetailForm = new CheckBrickTubeDetailForm();
				BeanUtils.copyProperties(checkBrickTubeDetail, checkBrickTubeDetailForm);
				checkBrickTubeDetailForms.add(checkBrickTubeDetailForm);
			}
			checkBrickTubeForm.setDetailList(checkBrickTubeDetailForms);
			checkBrickTubesNew.add(checkBrickTubeForm);
		}
		return checkBrickTubesNew;
	}
	
	public CheckBrickTubeForm getNewCheckBrickTube(RevisedInspectionQueryBean queryBean) {
		CheckBrickTube checkBrickTube = revisedInspectionMapper.selectNewCheckBrickTube(queryBean.getBatchId(),queryBean.getVersionId());
		List<CheckBrickTubeDetail> checkBrickTubeDetails = revisedInspectionMapper
				.selectNewCheckBrickTubeDetail(queryBean.getBatchId(),queryBean.getVersionId());
		CheckBrickTubeForm checkBrickTubeForm = new CheckBrickTubeForm();
		BeanUtils.copyProperties(checkBrickTube, checkBrickTubeForm);
		CheckBrickTubeDetailForm checkBrickTubeDetailForm = null;
		List<CheckBrickTubeDetailForm> checkBrickTubeDetailForms = new ArrayList<>();
		for (CheckBrickTubeDetail checkBrickTubeDetail : checkBrickTubeDetails) {
			checkBrickTubeDetailForm = new CheckBrickTubeDetailForm();
			BeanUtils.copyProperties(checkBrickTubeDetail, checkBrickTubeDetailForm);
			checkBrickTubeDetailForms.add(checkBrickTubeDetailForm);
		}
		checkBrickTubeForm.setDetailList(checkBrickTubeDetailForms);
		return checkBrickTubeForm;
	}
	
	public Integer addAllCheckBrickTube(CheckBrickTube queryBean) {
		String revisionId = getEiaId();//获取流水号
		if(queryBean.getVersionId()==0) {
			queryBean.setBatchId(revisionId);
			queryBean.setRevisionId(revisionId);
			queryBean.setBrickTubeId(revisionId);
			revisedInspectionMapper.insertNewTcheckRevisionLog(queryBean);
		}
		queryBean.setVersionId(queryBean.getVersionId()+1);
		Integer CheckBrickTube = revisedInspectionMapper.insertAllCheckBrickTube(queryBean);
		List<CheckBrickTubeDetail> detailList=queryBean.getDetailList();
		for (CheckBrickTubeDetail checkBrickTubeDetail : detailList) {
			if(queryBean.getVersionId()==1) {
				checkBrickTubeDetail.setBatchId(revisionId);
				checkBrickTubeDetail.setLedgerId(queryBean.getLedgerId());
			}
			checkBrickTubeDetail.setBrickDetailId(revisionId);
			checkBrickTubeDetail.setVersionId(queryBean.getVersionId());
			revisedInspectionMapper.insertAllCheckBrickTubeDetail(checkBrickTubeDetail);
		}
		revisedInspectionMapper.updCheckRevisionLogVersionId(queryBean.getBatchId(), queryBean.getVersionId(),queryBean.getOperator());
		return CheckBrickTube;
	}

	public List<CheckElectrode> getAllCheckElectrode(RevisedInspectionQueryBean queryBean) throws JsonParseException, JsonMappingException, IOException {
		List<CheckElectrode> revisedInspection = revisedInspectionMapper.getAllCheckElectrode(queryBean);
		for (CheckElectrode checkElectrode : revisedInspection) {
			ObjectMapper mapper = new ObjectMapper();
	        List<Object> itemOneObj = mapper.readValue(checkElectrode.getInspectionItem(),new TypeReference<List<Object>>() { });
	        checkElectrode.setItemOneObj(itemOneObj);
		}
		return revisedInspection;
	}
	
	public List<CheckElectrode> getNewCheckElectrode(CheckElectrode queryBean) throws JsonParseException, JsonMappingException, IOException {
		List<CheckElectrode> revisedInspection = revisedInspectionMapper.selectNewCheckElectrode(queryBean);
		for (CheckElectrode checkElectrode : revisedInspection) {
			ObjectMapper mapper = new ObjectMapper();
	        List<Object> itemOneObj = mapper.readValue(checkElectrode.getInspectionItem(),new TypeReference<List<Object>>() { });
	        checkElectrode.setItemOneObj(itemOneObj);
		}
		return revisedInspection;
	}
	
	public Integer addAllCheckElectrode(CheckElectrode queryBean) {
		String revisionId = getEiaId();//获取流水号
		if(queryBean.getVersionId()==0) {
			queryBean.setBatchId(revisionId);
			queryBean.setRevisionId(revisionId);
			revisedInspectionMapper.insertNewTcheckRevisionLog(queryBean);
		}
		queryBean.setVersionId(queryBean.getVersionId()+1);
		Integer checkElectrode=revisedInspectionMapper.insertAllCheckElectrode(queryBean);
		revisedInspectionMapper.updCheckRevisionLogVersionId(queryBean.getBatchId(), queryBean.getVersionId(),queryBean.getOperator());
		return checkElectrode;
	}

	public List<CheckFerroAlloyForm> getAllCheckFerroAlloy(RevisedInspectionQueryBean queryBean) {
		List<CheckFerroAlloy> checkFerroAlloys = revisedInspectionMapper.getAllCheckFerroAlloy(queryBean.getBatchId());
		List<CheckFerroAlloyDetail> checkFerroAlloyDetails = revisedInspectionMapper
				.getAllCheckFerroAlloyDetail(queryBean.getBatchId());
		List<CheckFerroAlloyForm> checkFerroAlloysNew = new ArrayList<>();
		CheckFerroAlloyForm checkFerroAlloyForm = null;
		CheckFerroAlloyDetailForm checkFerroAlloyDetailForm = null;
		for (CheckFerroAlloy checkFerroAlloy : checkFerroAlloys) {
			checkFerroAlloyForm = new CheckFerroAlloyForm();
			BeanUtils.copyProperties(checkFerroAlloy, checkFerroAlloyForm);
			List<CheckFerroAlloyDetailForm> checkFerroAlloyDetailForms = new ArrayList<>();
			for (CheckFerroAlloyDetail checkFerroAlloyDetail : checkFerroAlloyDetails) {
				checkFerroAlloyDetailForm = new CheckFerroAlloyDetailForm();
				BeanUtils.copyProperties(checkFerroAlloyDetail, checkFerroAlloyDetailForm);
				checkFerroAlloyDetailForms.add(checkFerroAlloyDetailForm);
			}
			checkFerroAlloyForm.setDetailList(checkFerroAlloyDetailForms);
			checkFerroAlloysNew.add(checkFerroAlloyForm);
		}
		return checkFerroAlloysNew;
	}
	
	public CheckFerroAlloyForm getNewCheckFerroAlloy(RevisedInspectionQueryBean queryBean) {
		CheckFerroAlloy checkFerroAlloy = revisedInspectionMapper.selectNewCheckFerroAlloy(queryBean.getBatchId(),queryBean.getVersionId());
		List<CheckFerroAlloyDetail> checkFerroAlloyDetails = revisedInspectionMapper
				.selectNewCheckFerroAlloyDetail(queryBean.getBatchId(),queryBean.getVersionId());
		CheckFerroAlloyForm checkFerroAlloyForm = new CheckFerroAlloyForm();
		BeanUtils.copyProperties(checkFerroAlloy, checkFerroAlloyForm);
		CheckFerroAlloyDetailForm checkFerroAlloyDetailForm = null;
		List<CheckFerroAlloyDetailForm> checkFerroAlloyDetailForms = new ArrayList<>();
		for (CheckFerroAlloyDetail checkFerroAlloyDetail : checkFerroAlloyDetails) {
			checkFerroAlloyDetailForm = new CheckFerroAlloyDetailForm();
			BeanUtils.copyProperties(checkFerroAlloyDetail, checkFerroAlloyDetailForm);
			checkFerroAlloyDetailForms.add(checkFerroAlloyDetailForm);
		}
		checkFerroAlloyForm.setDetailList(checkFerroAlloyDetailForms);
		return checkFerroAlloyForm;
	}
	
	public Integer addAllCheckFerroAlloy(CheckFerroAlloy queryBean) {
		String revisionId = getEiaId();//获取流水号
		if(queryBean.getVersionId()==0) {
			queryBean.setBatchId(revisionId);
			queryBean.setRevisionId(revisionId);
			revisedInspectionMapper.insertNewTcheckRevisionLog(queryBean);
		}
		queryBean.setVersionId(queryBean.getVersionId()+1);
		Integer checkFerroAlloy=revisedInspectionMapper.insertAllCheckFerroAlloy(queryBean);
		List<CheckFerroAlloyDetail> detailList=queryBean.getDetailList();
		for (CheckFerroAlloyDetail checkFerroAlloyDetail : detailList) {
			String ferroAlloyId = getEiaId();
			checkFerroAlloyDetail.setBatchId(queryBean.getBatchId());
			checkFerroAlloyDetail.setLedgerId(queryBean.getLedgerId());
			checkFerroAlloyDetail.setFerroAlloyId(ferroAlloyId);
			checkFerroAlloyDetail.setVersionId(queryBean.getVersionId());
			revisedInspectionMapper.insertAllCheckFerroAlloyDetail(checkFerroAlloyDetail);	
		}
		revisedInspectionMapper.updCheckRevisionLogVersionId(queryBean.getBatchId(), queryBean.getVersionId(),queryBean.getOperator());
		return checkFerroAlloy;
	}

	public List<CheckLime> getAllCheckLime(RevisedInspectionQueryBean queryBean) {
		List<CheckLime> revisedInspection = revisedInspectionMapper.getAllCheckLime(queryBean);
		return revisedInspection;
	}
	
	public CheckLime getNewCheckLime(CheckLime queryBean) {
		CheckLime checkLime = revisedInspectionMapper.selectNewCheckLime(queryBean.getBatchId(),queryBean.getVersionId());
		return checkLime;
	}
	
	public Integer addAllCheckLime(CheckLime queryBean) {
		String revisionId = getEiaId();//获取流水号
		if(queryBean.getVersionId()==0) {
			queryBean.setBatchId(revisionId);
			queryBean.setRevisionId(revisionId);
			revisedInspectionMapper.insertNewTcheckRevisionLog(queryBean);
		}
		queryBean.setVersionId(queryBean.getVersionId()+1);
		Integer checkLime=revisedInspectionMapper.insertAllCheckLime(queryBean);
		revisedInspectionMapper.updCheckRevisionLogVersionId(queryBean.getBatchId(), queryBean.getVersionId(),queryBean.getOperator());
		return checkLime;
	}

	public List<CheckResinChromeOre> getAllCheckResinChromeOre(RevisedInspectionQueryBean queryBean) throws JsonParseException, JsonMappingException, IOException {
		List<CheckResinChromeOre> revisedInspection = revisedInspectionMapper.getAllCheckResinChromeOre(queryBean);
		for (CheckResinChromeOre checkResinChromeOre : revisedInspection) {
			ObjectMapper mapper = new ObjectMapper();
	        List<Object> itemOneObj = mapper.readValue(checkResinChromeOre.getItemOne(),new TypeReference<List<Object>>() { });
			checkResinChromeOre.setItemOneObj(itemOneObj);
		}
		for (CheckResinChromeOre checkResinChromeOre : revisedInspection) {
			ObjectMapper mapper = new ObjectMapper();
	        List<Object> itemTwoObj = mapper.readValue(checkResinChromeOre.getItemTwo(),new TypeReference<List<Object>>() { });
			checkResinChromeOre.setItemTwoObj(itemTwoObj);
		}
		return revisedInspection;
	}
	
	public List<CheckResinChromeOre> getNewCheckResinChromeOre(RevisedInspectionQueryBean queryBean) throws JsonParseException, JsonMappingException, IOException {
		List<CheckResinChromeOre> revisedInspection = revisedInspectionMapper.selectNewCheckResinChromeOre(queryBean);
		for (CheckResinChromeOre checkResinChromeOre : revisedInspection) {
			ObjectMapper mapper = new ObjectMapper();
	        List<Object> itemOneObj = mapper.readValue(checkResinChromeOre.getItemOne(),new TypeReference<List<Object>>() { });
			checkResinChromeOre.setItemOneObj(itemOneObj);
		}
		for (CheckResinChromeOre checkResinChromeOre : revisedInspection) {
			ObjectMapper mapper = new ObjectMapper();
	        List<Object> itemTwoObj = mapper.readValue(checkResinChromeOre.getItemTwo(),new TypeReference<List<Object>>() { });
			checkResinChromeOre.setItemTwoObj(itemTwoObj);
		}
		return revisedInspection;
	}
	
	public Integer addAllCheckResinChromeOre(CheckResinChromeOre queryBean) {
		String revisionId = getEiaId();//获取流水号
		if(queryBean.getVersionId()==0) {
			queryBean.setBatchId(revisionId);
			queryBean.setRevisionId(revisionId);
			revisedInspectionMapper.insertNewTcheckRevisionLog(queryBean);
		}
		queryBean.setVersionId(queryBean.getVersionId()+1);
		revisedInspectionMapper.updCheckRevisionLogVersionId(queryBean.getBatchId(), queryBean.getVersionId(),queryBean.getOperator());
		Integer revisedInspection=revisedInspectionMapper.insertAllCheckResinChromeOre(queryBean);
		return revisedInspection;
	}

	public List<CheckVisualInspection> getAllCheckVisualInspection(CheckVisualInspection queryBean) {
		List<CheckVisualInspection> revisedInspection = revisedInspectionMapper.getAllCheckVisualInspection(queryBean);
		return revisedInspection;
	}
	
	public CheckVisualInspection getNewCheckVisualInspection(CheckVisualInspection queryBean){
		CheckVisualInspection checkVisualInspection = revisedInspectionMapper.selectNewCheckVisualInspection(queryBean.getBatchId(),queryBean.getVersionId());
		return checkVisualInspection;
	}
	
	public CheckVisualInspection getInitialCheckVisualInspection(CheckVisualInspection queryBean) {
		CheckVisualInspection checkVisualInspection = revisedInspectionMapper.getInitialCheckVisualInspection(queryBean.getBatchId(),queryBean.getVersionId());
		return checkVisualInspection;
	}
	
	public Integer addAllCheckVisualInspection(CheckVisualInspection queryBean) {
		String revisionId = getEiaId();//获取流水号
		if(queryBean.getVersionId()==0) {
			queryBean.setBatchId(revisionId);
			queryBean.setRevisionId(revisionId);
			revisedInspectionMapper.insertNewTcheckRevisionLog(queryBean);
		}
		queryBean.setVersionId(queryBean.getVersionId()+1);
		Integer addAllCheckVisualInspection=revisedInspectionMapper.insertAllCheckVisualInspection(queryBean);
		revisedInspectionMapper.updCheckRevisionLogVersionId(queryBean.getBatchId(), queryBean.getVersionId(),queryBean.getOperator());
		return addAllCheckVisualInspection;
	}
	
	public List<CheckVMethodCoatingForm> getAllCheckVMethodCoating(RevisedInspectionQueryBean queryBean) {
		List<CheckVMethodCoating> checkVMethodCoatings = revisedInspectionMapper
				.getAllCheckVMethodCoating(queryBean.getBatchId());
		List<CheckVMethodCoatingDetail> checkVMethodCoatingDetails = revisedInspectionMapper
				.getAllCheckVMethodCoatingDetail(queryBean.getBatchId());
		List<CheckVMethodCoatingForm> checkVMethodCoatingsNew = new ArrayList<>();
		CheckVMethodCoatingForm checkVMethodCoatingForm = null;
		CheckVMethodCoatingDetailForm checkVMethodCoatingDetailForm = null;
		for (CheckVMethodCoating checkVMethodCoating : checkVMethodCoatings) {
			checkVMethodCoatingForm = new CheckVMethodCoatingForm();
			BeanUtils.copyProperties(checkVMethodCoating, checkVMethodCoatingForm);
			List<CheckVMethodCoatingDetailForm> checkVMethodCoatingDetailForms = new ArrayList<>();
			for (CheckVMethodCoatingDetail checkVMethodCoatingDetail : checkVMethodCoatingDetails) {
				checkVMethodCoatingDetailForm = new CheckVMethodCoatingDetailForm();
				BeanUtils.copyProperties(checkVMethodCoatingDetail, checkVMethodCoatingDetailForm);
				checkVMethodCoatingDetailForms.add(checkVMethodCoatingDetailForm);
			}
			checkVMethodCoatingForm.setDetailList(checkVMethodCoatingDetailForms);
			checkVMethodCoatingsNew.add(checkVMethodCoatingForm);
		}
		return checkVMethodCoatingsNew;
	}
	
	public CheckVMethodCoatingForm getNewCheckVMethodCoating(RevisedInspectionQueryBean queryBean) {
		CheckVMethodCoating checkVMethodCoating = revisedInspectionMapper.selectNewCheckVMethodCoating(queryBean.getBatchId(),queryBean.getVersionId());
		List<CheckVMethodCoatingDetail> checkVMethodCoatingDetails = revisedInspectionMapper
				.selectNewCheckVMethodCoatingDetail(queryBean.getBatchId(),queryBean.getVersionId());
		CheckVMethodCoatingForm checkVMethodCoatingForm = new CheckVMethodCoatingForm();
		BeanUtils.copyProperties(checkVMethodCoating, checkVMethodCoatingForm);
		CheckVMethodCoatingDetailForm checkVMethodCoatingDetailForm = null;
		List<CheckVMethodCoatingDetailForm> checkVMethodCoatingDetailForms = new ArrayList<>();
		for (CheckVMethodCoatingDetail checkVMethodCoatingDetail : checkVMethodCoatingDetails) {
			checkVMethodCoatingDetailForm = new CheckVMethodCoatingDetailForm();
			BeanUtils.copyProperties(checkVMethodCoatingDetail, checkVMethodCoatingDetailForm);
			checkVMethodCoatingDetailForms.add(checkVMethodCoatingDetailForm);
		}
		checkVMethodCoatingForm.setDetailList(checkVMethodCoatingDetailForms);
		return checkVMethodCoatingForm;
	}

	public Integer addAllCheckVMethodCoating(CheckVMethodCoating queryBean) {
		String revisionId = getEiaId();//获取流水号
		if(queryBean.getVersionId()==0) {
			queryBean.setBatchId(revisionId);
			queryBean.setRevisionId(revisionId);
			revisedInspectionMapper.insertNewTcheckRevisionLog(queryBean);
		}
		queryBean.setVersionId(queryBean.getVersionId()+1);
		Integer vMethodCoating=revisedInspectionMapper.insertAllCheckVMethodCoating(queryBean);
		List<CheckVMethodCoatingDetail>detailList=queryBean.getDetailList();
		for (CheckVMethodCoatingDetail checkVMethodCoatingDetail : detailList) {
			if(queryBean.getVersionId()==1) {
				checkVMethodCoatingDetail.setBatchId(revisionId);
				checkVMethodCoatingDetail.setLedgerId(queryBean.getLedgerId());
			}
			checkVMethodCoatingDetail.setvMethodId(revisionId);
			checkVMethodCoatingDetail.setVersionId(queryBean.getVersionId());
			revisedInspectionMapper.insertAllCheckVMethodCoatingDetail(checkVMethodCoatingDetail);
		}
		revisedInspectionMapper.updCheckRevisionLogVersionId(queryBean.getBatchId(), queryBean.getVersionId(),queryBean.getOperator());
		return vMethodCoating;
	}
	
	public List<CheckWaterGlass> getAllCheckWaterGlass(RevisedInspection queryBean) {
		List<CheckWaterGlass> checkWaterGlasss = revisedInspectionMapper.getAllCheckWaterGlass(queryBean.getBatchId());
		return checkWaterGlasss;
	}
	
	public CheckWaterGlass getNewCheckWaterGlass(CheckWaterGlass queryBean) {
		CheckWaterGlass checkWaterGlass = revisedInspectionMapper.selectNewCheckWaterGlass(queryBean.getBatchId(),queryBean.getVersionId());
		return checkWaterGlass;
	}
	
	public Integer addAllCheckWaterGlass(CheckWaterGlass queryBean) {
		String revisionId = getEiaId();//获取流水号
		if(queryBean.getVersionId()==0) {
			queryBean.setBatchId(revisionId);
			queryBean.setRevisionId(revisionId);
			queryBean.setWaterGlassId(revisionId);
			revisedInspectionMapper.insertNewTcheckRevisionLog(queryBean);
		}
		queryBean.setVersionId(queryBean.getVersionId()+1);
		Integer id=revisedInspectionMapper.insertAllCheckWaterGlass(queryBean);
		revisedInspectionMapper.updCheckRevisionLogVersionId(queryBean.getBatchId(), queryBean.getVersionId(),queryBean.getOperator());
		return id;
	}
	
	 /**
     * 自动生成流水号
     * @return
     */
    public String getEiaId(){
    	return seqService.getBillId(new Date(), "t_inquire", "");
    }

	

}
