package com.lyarc.tp.corp.quality.certification.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.quality.certification.bean.CastingCertificate;
import com.lyarc.tp.corp.quality.certification.bean.ContractDetailQueryBean;
import com.lyarc.tp.corp.quality.certification.bean.MagneticPowderInspection;
import com.lyarc.tp.corp.quality.certification.bean.PartSize;
import com.lyarc.tp.corp.quality.certification.bean.QualifiedReport;
import com.lyarc.tp.corp.quality.certification.bean.UltrasonicTesting;
import com.lyarc.tp.corp.quality.certification.service.CertificateService;

@Controller
@ResponseBody
@RequestMapping("/api/Certificate")
public class CertificateController {

	@Autowired
	private CertificateService certificateService;

	/** 
	 * 	添加铸件合格证  
	 *
	 * @param record(id 合格证报告管理id contract_id 合同编号 certificate_id 合格证编号 test_item
	 *        检测项目 chemical_component 化学成分 mechanical_property 力学性能 status 状态 1.合格
	 *        2.不合格 wf_inst_id 流程实例id wf_inst_time 流程发起时间 wf_inst_remark 审核备注 audit
	 *        审核 create_time 创建时间 update_time 更新时间 tmstamp 时间戳 )
	 * @return Integer类型的row 
	 */
	@ResponseBody
	@RequestMapping("/addC")
	public ResultVo<Map<String, Object>> add(@RequestBody CastingCertificate record) {
		Map<String, Object> map = certificateService.addC(record);
		return ResultVo.success(map);
	}

	/** 
	 * 	添加零件加工尺寸报告 
	 * 添加零件加工尺寸报告
	 * 
	 * @param record(id 合格证报告管理id contract_id 合同编号 part_size_id 零件加工尺寸编号
	 *        senal_number 铸造号码 no 序号 dimension 图纸尺寸 area 区域 tolerance_allowance
	 *        公差/余量 actual_dimension 实际尺寸 status 状态 1.合格 2.不合格 wf_inst_id 流程实例id
	 *        wf_inst_time 流程发起时间 wf_inst_remark 审核备注 audit 审核 create_time 创建时间
	 *        update_time 更新时间 tmstamp 时间戳 ) 
	 * @return Integer类型的ID
	 */
	@ResponseBody
	@RequestMapping("/addP")
	public ResultVo<Integer> addP(@RequestBody PartSize record) {
		Integer id = certificateService.addP(record);
		return ResultVo.success(id);

	}

	/** 
	 * 添加超声波探伤检验报告
	 * 
	 * @param record(id 合格证报告管理id contract_id 合同编号 ultrasonic_id 超声波探伤编号 test_item
	 *        检测项目 description 零件名称 report_no 报告编号 order_no 产品工号 part_no 零件编号 status
	 *        状态 1.正火 2.回火 report_date 报告日期 drawing_no 零件图号 commission_unit 委托单位
	 *        quantity 数量 material 材质 required_standard 执行标准 type_of_the 探头型号
	 *        type_of_coupling 耦合方式 statu 表面状态 type_of_machine 设备编号 size 探头尺寸
	 *        frequency 频率 adjusting_field 校 准 区 angle 探头角度 coupling 耦 合 剂
	 *        sketch_part 零件示意图 ultrasonic_test_result 超声波探伤检验结果 wf_inst_id 流程实例id
	 *        wf_inst_time 流程发起时间 wf_inst_remark 审核备注 audit 审核 create_time 创建时间
	 *        update_time 更新时间 tmstamp 时间戳 ) 
	 * @return Integer 类型的 row
	 */
	@ResponseBody
	@RequestMapping("/addU")
	public ResultVo<Integer> addU(@RequestBody UltrasonicTesting record) {
		Integer row = certificateService.addU(record);
		return ResultVo.success(row);
	}

	/** 
	 * 添加磁粉探伤检验报告
	 * 
	 * @param record(id 合格证报告管理id contract_id 合同编号 magnetic_id 磁粉探伤编号 description
	 *        零件名称 report_no 报告编号 order_no 产品工号 part_no 零件编号 status 状态 1.正火 2.回火
	 *        report_date 报告日期 drawing_no 零件图号 commission_unit 委托单位 quantity 数量
	 *        material 材质 test_item 检测项目 sketch_part 零件示意图 magnetic_test_result
	 *        磁粉探伤检验结果 type_of_equipment 仪器型号 area_tested 探伤区域 type_of_machine 设备编号
	 *        surface_condition 表面状态 testing_method 检测方式 method_of_testing 探伤方法
	 *        medium 媒介 prod_distance 电极距离 current_in_ampsr 电流 required_standard
	 *        执行标准 wf_inst_id 流程实例id wf_inst_time 流程发起时间 wf_inst_remark 审核备注 audit
	 *        审核 create_time 创建时间 update_time 更新时间 tmstamp 时间戳 ) 
	 * @return Integer 类型的 id
	 */
	@ResponseBody
	@RequestMapping("/addM")
	public ResultVo<Integer> addM(@RequestBody MagneticPowderInspection record) {
		Integer id = certificateService.addM(record);
		return ResultVo.success(id);
	}

	/** 
	 * 编辑铸件合格证
	 * 
	 * @param record(id 合格证报告管理id contract_id 合同编号 certificate_id 合格证编号 test_item
	 *        检测项目 chemical_component 化学成分 mechanical_property 力学性能 status 状态 1.合格
	 *        2.不合格 wf_inst_id 流程实例id wf_inst_time 流程发起时间 wf_inst_remark 审核备注 audit
	 *        审核 create_time 创建时间 update_time 更新时间 tmstamp 时间戳 ) 
	 * @return Integer 类型的row
	 */
	@ResponseBody
	@RequestMapping("/updateC")
	public ResultVo<Integer> updateC(String certificateId) {
		Integer row = certificateService.deleteC(certificateId);
		return ResultVo.success(row);
	}

	/** 
	 * 编辑零件加工尺寸报告
	 * 
	 * @param record(id 合格证报告管理id contract_id 合同编号 part_size_id 零件加工尺寸编号
	 *        senal_number 铸造号码 no 序号 dimension 图纸尺寸 area 区域 tolerance_allowance
	 *        公差/余量 actual_dimension 实际尺寸 status 状态 1.合格 2.不合格 wf_inst_id 流程实例id
	 *        wf_inst_time 流程发起时间 wf_inst_remark 审核备注 audit 审核 create_time 创建时间
	 *        update_time 更新时间 tmstamp 时间戳 ) 
	 * @return Integer类型的ID
	 */
	@ResponseBody
	@RequestMapping("/updateP")
	public Integer updateP(@RequestBody PartSize record) {
		Integer id = certificateService.updateP(record);
		return id;

	}

	/** 
	 * 编辑超声波探伤检验报告
	 * 
	 * @param record(id 合格证报告管理id contract_id 合同编号 ultrasonic_id 超声波探伤编号 test_item
	 *        检测项目 description 零件名称 report_no 报告编号 order_no 产品工号 part_no 零件编号 status
	 *        状态 1.正火 2.回火 report_date 报告日期 drawing_no 零件图号 commission_unit 委托单位
	 *        quantity 数量 material 材质 required_standard 执行标准 type_of_the 探头型号
	 *        type_of_coupling 耦合方式 statu 表面状态 type_of_machine 设备编号 size 探头尺寸
	 *        frequency 频率 adjusting_field 校 准 区 angle 探头角度 coupling 耦 合 剂
	 *        sketch_part 零件示意图 ultrasonic_test_result 超声波探伤检验结果 wf_inst_id 流程实例id
	 *        wf_inst_time 流程发起时间 wf_inst_remark 审核备注 audit 审核 create_time 创建时间
	 *        update_time 更新时间 tmstamp 时间戳 ) 
	 * @return Integer 类型的row
	 */
	@ResponseBody
	@RequestMapping("/updateU")
	public Integer updateU(@RequestBody UltrasonicTesting record) {
		Integer row = certificateService.updateU(record);
		return row;
	}

	/**
	 * 编辑磁粉探伤检验报告 
	 * 
	 * @param record(id 合格证报告管理id contract_id 合同编号 magnetic_id 磁粉探伤编号 description
	 *        零件名称 report_no 报告编号 order_no 产品工号 part_no 零件编号 status 状态 1.正火 2.回火
	 *        report_date 报告日期 drawing_no 零件图号 commission_unit 委托单位 quantity 数量
	 *        material 材质 test_item 检测项目 sketch_part 零件示意图 magnetic_test_result
	 *        磁粉探伤检验结果 type_of_equipment 仪器型号 area_tested 探伤区域 type_of_machine 设备编号
	 *        surface_condition 表面状态 testing_method 检测方式 method_of_testing 探伤方法
	 *        medium 媒介 prod_distance 电极距离 current_in_ampsr 电流 required_standard
	 *        执行标准 wf_inst_id 流程实例id wf_inst_time 流程发起时间 wf_inst_remark 审核备注 audit
	 *        审核 create_time 创建时间 update_time 更新时间 tmstamp 时间戳 ) 
	 * @return Integer 类型的 id
	 */
	@ResponseBody
	@RequestMapping("/updateM")
	public Integer updateM(@RequestBody MagneticPowderInspection record) {
		Integer id = certificateService.updateM(record);
		return id;
	}

	/** 
	 * 	导入合同 
	 * 
	 * @param bean("page": 起始页码, "pageSize": 查询条数, "offset": 偏移量, "order": null,
	 *        "sort": null, "requireTotal": null, "id": 50, "contractId": "合同编号",
	 *        "certificateId": 合格证编号, "materId": "物料编码", "customerName": 计量器具名称,
	 *        "drawing": "图号", "casting": 铸件号, "materName": "物料名称", "texture": "材质",
	 *        "hardness": "硬度", "deliveryType": "交货类型", "unit": "单位", "line": 行数,
	 *        "quantity": 数量, "weight": 重量, "weightTotal": 总重量, "workblank": 毛坯单价,
	 *        "workblankFee": 毛坯费, "processFee": 加工费, "packingFee": 包装费,
	 *        "freightFee": 运费, "portFee": 港口费, "modelFee": 模型费, "amount": 金额,
	 *        "deliveryDate": "交货日期", "remark": "备注", "creator":创建人 "createTime":
	 *        "创建时间", "updateTime": "修改时间", "tmstamp:时间戳") 
	 * @return list列表,count计数
	 */
	@ResponseBody
	@RequestMapping("/list")
	public ResultVo<PageResultBean> list(@RequestBody ContractDetailQueryBean bean) {
		Integer page = bean.getPage();
		Integer pageSize = bean.getPageSize();
		if (page != null && pageSize != null) {
			bean.setOffset((page - 1) * pageSize);
		}

		List<ContractDetailQueryBean> contractDetailList = certificateService.list(bean);  
		/*
		 * Long line = certificateService.findLine(bean); bean.setLine(line);
		 */ 

		Long count = certificateService.count(bean);

		return ResultVo.success(PageResultBean.success(count, contractDetailList));
	}

	/**
	 * 
	 * @Desc 根据合同id查询铸件合格证 
	 * @Param
	 * @Return
	 * @author
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @Date 2018年11月26日 上午10:28:56 
	 */
	@ResponseBody
	@RequestMapping(path = "/getC")
	public ResultVo<CastingCertificate> selectByCid(CastingCertificate Bean)
			throws JsonParseException, JsonMappingException, IOException {
		CastingCertificate castingCertificate = certificateService.selectByCid(Bean);
		return ResultVo.success(castingCertificate);
	}

	/**
	 * 
	 * @Desc 根据合同id查询零件尺寸加工报告 
	 * @Param
	 * @Return
	 * @author
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @Date 2018年11月26日 上午10:28:50 
	 */
	@ResponseBody
	@RequestMapping("/getP")
	public ResultVo<PartSize> selectByPid(PartSize record) throws JsonParseException, JsonMappingException, IOException {
		PartSize partSize = certificateService.selectByPid(record);
		return ResultVo.success(partSize);
	}

	/**
	 * 
	 * @Desc 根据合同id查询超声波探伤检验报告 
	 * @Param
	 * @Return
	 * @author
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @Date 2018年11月26日 上午10:28:40 
	 */
	@ResponseBody
	@RequestMapping("/getU")
	public ResultVo<UltrasonicTesting> selectByUid(UltrasonicTesting record) {
		UltrasonicTesting ultrasonicTesting = certificateService.selectByUid(record);
		return ResultVo.success(ultrasonicTesting);
	}

	/**
	 * 
	 * @Desc 根据合同id查询磁粉探伤检验报告 
	 * @Param
	 * @Return
	 * @author
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @Date 2018年11月26日 上午10:28:30 
	 */
	@ResponseBody
	@RequestMapping("/getM")
	public ResultVo<MagneticPowderInspection> selectByMid(MagneticPowderInspection record) {
		MagneticPowderInspection magneticPowderInspection = certificateService.selectByMid(record);
		return ResultVo.success(magneticPowderInspection);
	}

	/** 
	 * 根据合同编号(contractId)查询铸件合格证号(certificateId)
	 * 
	 * @param bean(contractId 合同编号)
	 * @return certificateId 铸件合格证号  
	 */
	@ResponseBody
	@RequestMapping("/get")
	public ResultVo<List<ContractDetailQueryBean>> getId(ContractDetailQueryBean bean) {
		List<ContractDetailQueryBean> certificateId = certificateService.selcetById(bean);
		return ResultVo.success(certificateId);
	}

	/**
	 * @Desc 一个合同 vs 多个合格证 vs 多个物料 
	 * @Param ("contractId":'合同号'
	 * @Param "rowIndex":'行号'
	 * @Param "customerName":客户名称
	 * @Param "materName":物料名称
	 * @Param "drawing":图号
	 * @Param "casting":铸件号
	 * @Param "texture":材质
	 * @Param "unit":单位
	 * @Param "quantity":数量
	 * @Param "deliveryDate":交货日期
	 * @Param "证明报告":(certificateId(铸件合格证),ultrasonicId(超声波探伤报告),magneticId(磁粉探伤报告),partSizeId(零件加工尺寸报告))
	 * @Param "wfInstRemark":驳回原因
	 * @Param "wfInstTime":驳回日期)
	 * @Return contractId,materId.....
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @Date 2018年11月26日 下午8:32:14 
	 */
	@ResponseBody
	@RequestMapping("/listAllC")

	public ResultVo<PageResultBean> listAllC(@RequestBody QualifiedReport queryBean)
			throws JsonParseException, JsonMappingException, IOException {
		Integer page = queryBean.getPage();
		Integer pageSize = queryBean.getPageSize();
		if (page != null && pageSize != null) {
			queryBean.setOffset((page - 1) * pageSize);
		}
		List<Map<String, Object>> listCastingCertificate = certificateService.listAllC(queryBean);
		Long count = certificateService.counts(queryBean);
		return ResultVo.success(PageResultBean.success(count, listCastingCertificate));
	}

	/*
	 * public ResultVo<ContractDetailQueryBean> findLine(ContractDetailQueryBean
	 * bean){ ContractDetailQueryBean line = certificateService.findLine(bean);
	 * return ResultVo.success(line); }
	 */

	/**
	 * 重新添加超声波探伤报告,获取上次添加的记录
	 * 
	 * @param reocrd
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/getNew") public UltrasonicTesting getNew(String reocrd) {
	 * UltrasonicTesting row = certificateService.getNew(reocrd); return row; }
	 */
	/** 
	 * 根据合格证编号修改合格证状态
	 * @param certificateId：合格证编号 ;
	 * @param testPeople：质检员 ; 
	 * @param checkedBy：审核人 ; 
	 * @param approvedBy：批准人 ; 
	 * @param wfInstRemark：驳回原因 ; 
	 * @param status：状态 0.录入 1.审核  2.批准 3.驳回 4. 打印 ;   
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updStatusC")
	public ResultVo<Integer> updStatusC(@RequestBody CastingCertificate record) {
		Integer result = certificateService.updStatusC(record);
		return ResultVo.success(result);
	}
	/** 
	 * 根据零件尺寸加工报告编号修改零件尺寸加工报告状态
	 * @param partSizeId：零件尺寸加工报告编号 ;
	 * @param inspector：质检员 ; 
	 * @param reviewed：审核人 ; 
	 * @param approvalDate：审核确认时间; 
	 * @param wfInstRemark：驳回原因 ; 
	 * @param status：状态 0.录入 1.审核  2.批准 3.驳回 4. 打印 ;   
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updStatusP")
	public ResultVo<Integer> updStatusP(@RequestBody PartSize record) {
		Integer result = certificateService.updStatusP(record);
		return ResultVo.success(result);
	}
	/** 
	 * 根据超声波探伤检验报告编号修改超声波探伤检验报告状态
	 * @param ultrasonicId：超声波探伤检验报告编号 ;
	 * @param inspector：质检员 ; 
	 * @param reviewed：审核人 ; 
	 * @param approvalDate：审核确认时间; 
	 * @param wfInstRemark：驳回原因 ; 
	 * @param status：状态 0.录入 1.审核  2.批准 3.驳回 4. 打印 ;  
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updStatusU")
	public ResultVo<Integer> updStatusU(@RequestBody UltrasonicTesting record) {
		Integer result = certificateService.updStatusU(record);
		return ResultVo.success(result);
	}
	/**  
	 * 根据磁粉探伤检验报告编号修改磁粉探伤检验报告状态
	 * @param magneticId：磁粉探伤检验报告编号;
	 * @param testPeople：质检员 ; 
	 * @param checkedBy：审核人 ; 
	 * @param approvedBy：批准人 ; 
	 * @param wfInstRemark：驳回原因 ; 
	 * @param status：状态 0.录入 1.审核  2.批准 3.驳回 4. 打印 ;  
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updStatusM")
	public ResultVo<Integer> updStatusM(@RequestBody MagneticPowderInspection record) {
		Integer result = certificateService.updStatusM(record);
		return ResultVo.success(result);
	}
}
