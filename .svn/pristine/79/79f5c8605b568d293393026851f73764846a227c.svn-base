package com.lyarc.tp.corp.quality.certification.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyarc.tp.corp.quality.certification.bean.CastingCertificate;
import com.lyarc.tp.corp.quality.certification.bean.ContractDetailQueryBean;
import com.lyarc.tp.corp.quality.certification.bean.MagneticPowderInspection;
import com.lyarc.tp.corp.quality.certification.bean.PartSize;
import com.lyarc.tp.corp.quality.certification.bean.QualifiedReport;
import com.lyarc.tp.corp.quality.certification.bean.UltrasonicTesting;
import com.lyarc.tp.corp.quality.certification.dao.CastingCertificateMapper;
import com.lyarc.tp.corp.quality.certification.dao.MagneticPowderInspectionMapper;
import com.lyarc.tp.corp.quality.certification.dao.PartSizeMapper;
import com.lyarc.tp.corp.quality.certification.dao.QualifiedReportMapper;
import com.lyarc.tp.corp.quality.certification.dao.UltrasonicTestingMapper;
import com.lyarc.tp.corp.seq.service.SeqService;

@Service
public class CertificateService {

	@Autowired
	private CastingCertificateMapper castingCertificateMapper;
	@Autowired
	private PartSizeMapper partSizeMapper;
	@Autowired
	private UltrasonicTestingMapper ultrasonicTestingMapper;
	@Autowired
	private MagneticPowderInspectionMapper magneticPowderInspectionMapper;
	@Autowired
	private SeqService seqService;
	@Autowired
	private QualifiedReportMapper qualifiedReportMapper;

	// 添加铸件合格证
	public Map<String, Object> addC(CastingCertificate record) {
		String certificateId = getEiaId();
		record.setCertificateId(certificateId);
		QualifiedReport qualifiedReport = new QualifiedReport();
		qualifiedReport.setId(getEiaId());
		qualifiedReport.setCertificateId(certificateId);
		qualifiedReport.setContractId(record.getContractId());
		qualifiedReportMapper.insertSelective(qualifiedReport);
		Integer id = castingCertificateMapper.insertSelective(record);
		Map<String, Object> map = new HashMap<>();
		map.put("certificateId", certificateId);
		return map;
	}

	public Integer addP(PartSize record) {
		String partSizeId = record.getPartSizeId();
		if (partSizeId != null && !"".equals(partSizeId)) {
			partSizeMapper.deleteByPrimaryKey(record.getPartSizeId());
		} else {
			partSizeId = getEiaId();
			record.setPartSizeId(partSizeId);
		}
		QualifiedReport qualifiedReport = new QualifiedReport();
		qualifiedReport.setCertificateId(record.getCertificateId());
		qualifiedReport.setPartSizeId(partSizeId);
		qualifiedReportMapper.updByCertificateId(qualifiedReport);
		Integer id = partSizeMapper.insertSelective(record);
		return id;

	}

	public Integer addU(UltrasonicTesting record) {
		String ultrasonicId = record.getUltrasonicId();
		if (ultrasonicId != null && !"".equals(ultrasonicId)) {
			ultrasonicTestingMapper.deleteByPrimaryKey(ultrasonicId);
			record.setReportNo(ultrasonicId);
		} else {
			ultrasonicId = getEiaId();
			record.setUltrasonicId(ultrasonicId);
			record.setReportNo(ultrasonicId);
		}
		QualifiedReport qualifiedReport = new QualifiedReport();
		qualifiedReport.setCertificateId(record.getCertificateId());
		qualifiedReport.setUltrasonicId(ultrasonicId);
		qualifiedReportMapper.updByCertificateId(qualifiedReport);
		Integer id = ultrasonicTestingMapper.insertSelective(record);
		return id;

	}

	public Integer addM(MagneticPowderInspection record) {
		String magneticId = record.getMagneticId();
		if (magneticId != null && !"".equals(magneticId)) {
			magneticPowderInspectionMapper.deleteByPrimaryKey(magneticId);
			record.setReportNo(magneticId);
		} else {
			magneticId = getEiaId();
			record.setMagneticId(magneticId);
			record.setReportNo(magneticId);
		}
		QualifiedReport qualifiedReport = new QualifiedReport();
		qualifiedReport.setCertificateId(record.getCertificateId());
		qualifiedReport.setMagneticId(magneticId);
		qualifiedReportMapper.updByCertificateId(qualifiedReport);
		Integer id = magneticPowderInspectionMapper.insertSelective(record);
		return id;
	}

	public Integer addQR(QualifiedReport record) {
		CastingCertificate castingCertificate = new CastingCertificate();
		record.setCertificateId(castingCertificate.getCertificateId());
		Integer row = qualifiedReportMapper.insertSelective(record);
		return row;

	}

	/**
	 * 自动生成流水号
	 * 
	 * @return //
	 */
	public String getEiaId() {
		return seqService.getBillId(new Date(), "t_inquire", "");
	}

	// 修改

	public Integer deleteC(String certificateId) {
		Integer id = castingCertificateMapper.deleteByPrimaryKey(certificateId);
		return id;
	}

	public Integer updateP(PartSize record) {
		Integer id = partSizeMapper.updateByPrimaryKeySelective(record);
		return id;

	}

	public Integer updateU(UltrasonicTesting record) {
		Integer id = ultrasonicTestingMapper.updateByPrimaryKeySelective(record);
		return id;

	}

	public Integer updateM(MagneticPowderInspection record) {
		Integer id = magneticPowderInspectionMapper.updateByPrimaryKeySelective(record);
		return id;
	}

	/*
	 * public Long findLine(ContractDetailQueryBean line) { Long counts =
	 * castingCertificateMapper.findLine(line); return counts; }
	 */
	/*
	 * List<ContractDetailQueryBean> listContractDetail =
	 * castingCertificateMapper.list(bean); for(ContractDetailQueryBean List :
	 * listContractDetail) { List.getCertificateId(); }
	 */
 
	/* public List<> */
 
	/**
	 * 合同分页显示
	 * 
	 * @param bean
	 * @return
	 */
	public List<ContractDetailQueryBean> list(ContractDetailQueryBean bean) {
		List<ContractDetailQueryBean> contractDetailList = castingCertificateMapper.list(bean);// 列表

		return contractDetailList;
	}

	public Long count(ContractDetailQueryBean bean) {
		Long count = castingCertificateMapper.count(bean);// 计数
		return count;
	}

	// 根据合同号查询合格证与报告详细信息
	public List<ContractDetailQueryBean> selcetById(ContractDetailQueryBean bean) {
		List<ContractDetailQueryBean> certificateId = castingCertificateMapper.selcetById(bean);
		return certificateId;
	}

	// 根据合格证id查询
	public CastingCertificate selectByCid(CastingCertificate Bean)
			throws JsonParseException, JsonMappingException, IOException {
		CastingCertificate castingCertificate = castingCertificateMapper.selectByPrimaryKey(Bean);
		ObjectMapper mapper = new ObjectMapper();
		if (castingCertificate.getTestItem() != null) {
			List<Object> testItemObj = mapper.readValue(castingCertificate.getTestItem(),
					new TypeReference<List<Object>>() {
					});
			castingCertificate.setTestItemObj(testItemObj);
		}

		if (castingCertificate.getChemicalComponent() != null) {
			List<Object> testItema = mapper.readValue(castingCertificate.getChemicalComponent(),
					new TypeReference<List<Object>>() {
					});
			castingCertificate.setTestItema(testItema);
		}
		if (castingCertificate.getMechanicalProperty() != null) {

			List<Object> testItemb = mapper.readValue(castingCertificate.getMechanicalProperty(),
					new TypeReference<List<Object>>() {
					});
			castingCertificate.setTestItemb(testItemb);
		}
		return castingCertificate;
	}

	// 根据零件尺寸加工报告id查询
	public PartSize selectByPid(PartSize record) throws JsonParseException, JsonMappingException, IOException {
		PartSize partSize = partSizeMapper.selectByPid(record);
		ObjectMapper mapper = new ObjectMapper();
		if (partSize.getTestItem() != null) {
			List<Object> testItemObj = mapper.readValue(partSize.getTestItem(), new TypeReference<List<Object>>() {
			});
			partSize.setTestItemObj(testItemObj);
		}
		return partSize;
	}

	// 根据超声波探伤检验报告id查询
	public UltrasonicTesting selectByUid(UltrasonicTesting record) {
		UltrasonicTesting ultrasonicTesting = ultrasonicTestingMapper.selectByPrimaryKey(record);
		/*
		 * ObjectMapper mapper = new ObjectMapper(); if (ultrasonicTesting.getTestItem()
		 * != null && ultrasonicTesting.getSketchPart() != null &
		 * ultrasonicTesting.getUltrasonicTestResult() != null) { List<Object>
		 * testItemObj = mapper.readValue(ultrasonicTesting.getTestItem(), new
		 * TypeReference<List<Object>>() { }); List<Object> sketchPartObj =
		 * mapper.readValue(ultrasonicTesting.getSketchPart(), new
		 * TypeReference<List<Object>>() { }); List<Object> ultrasonicTestResultObj =
		 * mapper.readValue(ultrasonicTesting.getUltrasonicTestResult(), new
		 * TypeReference<List<Object>>() { });
		 * ultrasonicTesting.setTestItemObj(testItemObj);
		 * ultrasonicTesting.setSketchPartObj(sketchPartObj);
		 * ultrasonicTesting.setUltrasonicTestResultObj(ultrasonicTestResultObj); }
		 */
		return ultrasonicTesting;
	}

	// 根据磁粉探伤检验报告查询
	public MagneticPowderInspection selectByMid(MagneticPowderInspection record) {

		MagneticPowderInspection magneticPowderInspection = magneticPowderInspectionMapper.selectByMid(record);

		/*
		 * ObjectMapper mapper = new ObjectMapper(); List<Object> testItemObj =
		 * mapper.readValue(magneticPowderInspection.getTestItem(), new
		 * TypeReference<List<Object>>() { }); List<Object> sketchPartObj =
		 * mapper.readValue(magneticPowderInspection.getSketchPart(), new
		 * TypeReference<List<Object>>() { }); List<Object> magneticTestResultObj =
		 * mapper.readValue(magneticPowderInspection.getMagneticTestResult(), new
		 * TypeReference<List<Object>>() { });
		 * magneticPowderInspection.setTestItemObj(testItemObj);
		 * magneticPowderInspection.setSketchPartObj(sketchPartObj);
		 * magneticPowderInspection.setMagneticTestResultObj(magneticTestResultObj);
		 */

		return magneticPowderInspection;
	}

	// 铸件合格证查询
	public List<Map<String, Object>> listAllC(QualifiedReport queryBean)
			throws JsonParseException, JsonMappingException, IOException {
		List<QualifiedReport> qualifiedReports = qualifiedReportMapper.selectByPrimaryKeys();
		List<CastingCertificate> castingCertificates = castingCertificateMapper.selectByPrimaryKeys();
		List<PartSize> partSizes = partSizeMapper.selectByPrimaryKeys();
		List<UltrasonicTesting> ultrasonicTestings = ultrasonicTestingMapper.selectByPrimaryKeys();
		List<MagneticPowderInspection> magneticPowderInspections = magneticPowderInspectionMapper.selectByPrimaryKeys();
		List<String> strList = qualifiedReportMapper.listAllC(queryBean);
		System.out.println(strList.size());
		List<Map<String, Object>> maplsit = new ArrayList<>();
		for (String str : strList) {
			Map<String, Object> map = new HashMap<>();
			int rowIndex = 0;
			List<Map<String, Object>> maps = new ArrayList<>();
			for (QualifiedReport qualifiedReport : qualifiedReports) {
				if (str.equals(qualifiedReport.getContractId())) {
					Map<String, Object> map2 = new HashMap<>();
					ObjectMapper mapper = new ObjectMapper();
					CastingCertificate castingCertificateMap = new CastingCertificate();
					for (CastingCertificate castingCertificate : castingCertificates) {
						if (castingCertificate.getCertificateId().equals(qualifiedReport.getCertificateId())) {
							List<Object> testItemObj = mapper.readValue(castingCertificate.getTestItem(),
									new TypeReference<List<Object>>() {
									});

							castingCertificate.setTestItemObj(testItemObj);
							castingCertificateMap = castingCertificate;
							rowIndex += testItemObj.size();
							break;
						}
					}
					map2.put("castingCertificate", castingCertificateMap);
					PartSize partSizeMap = new PartSize();
					for (PartSize partSize : partSizes) {
						if (partSize.getPartSizeId().equals(qualifiedReport.getPartSizeId())) {
							partSizeMap = partSize;
							break;
						}
					}
					map2.put("partSize", partSizeMap);
					UltrasonicTesting ultrasonicTestingMap = new UltrasonicTesting();
					for (UltrasonicTesting ultrasonicTesting : ultrasonicTestings) {
						if (ultrasonicTesting.getUltrasonicId().equals(qualifiedReport.getUltrasonicId())) {
							ultrasonicTestingMap = ultrasonicTesting;

							break;
						}
					}
					map2.put("ultrasonicTesting", ultrasonicTestingMap);
					MagneticPowderInspection magneticPowderInspectionMap = new MagneticPowderInspection();
					for (MagneticPowderInspection magneticPowderInspection : magneticPowderInspections) {
						if (magneticPowderInspection.getMagneticId().equals(qualifiedReport.getMagneticId())) {
							magneticPowderInspectionMap = magneticPowderInspection;
							break;
						}
					}
					map2.put("magneticPowderInspection", magneticPowderInspectionMap);
					maps.add(map2);
					map.put("isForeign", qualifiedReport.getIsForeign());
				}
			}
			map.put("contactId", str);
			map.put("rowIndex", rowIndex);
			map.put("maps", maps);
			maplsit.add(map);
		}

		/*
		 * for (CastingCertificate castingCertificate : listCastingCertificate) {
		 * ObjectMapper mapper = new ObjectMapper(); List<Object> testItem =
		 * mapper.readValue(castingCertificate.getTestItem(),new
		 * TypeReference<List<Object>>() {});
		 * castingCertificate.gettestItemOne()(testItem); }
		 */
		return maplsit;
	}

	public Long counts(QualifiedReport bean) {
		Long count = qualifiedReportMapper.counts(bean);// 计数
		return count;
	}

	/*
	 * public UltrasonicTesting getNew(String reocrd) { UltrasonicTesting row =
	 * ultrasonicTestingMapper.getUltrasonicTesting(reocrd); return row; }
	 */
	/*
	 * public ContractDetailQueryBean findLine(ContractDetailQueryBean bean) {
	 * ContractDetailQueryBean findLine = castingCertificateMapper.findLine(bean);
	 * return findLine; }
	 */
	public Integer updStatusC(CastingCertificate record) {
		Integer result = castingCertificateMapper.updStatusC(record);
		return result;
	}

	public Integer updStatusP(PartSize record) {
		Integer result = partSizeMapper.updStatusP(record);
		return result;
	}

	public Integer updStatusU(UltrasonicTesting record) {
		Integer result = ultrasonicTestingMapper.updStatusU(record);
		return result;
	}

	public Integer updStatusM(MagneticPowderInspection record) {
		Integer result = magneticPowderInspectionMapper.updStatusM(record);
		return result;
	}
}
