package com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.bean.CorrectiveAction;
import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.bean.UnqualifiedPeoductBillQueryBean;
import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.bean.UnqualifiedProductBill;
import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.bean.UpbEightDReport;
import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.service.UnqualifiedProductBillService;

/**
 * 不合格品通知单
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping("api/UnqualifiedProductBill")
public class UnqualifiedProductBillController {

	@Autowired
	public UnqualifiedProductBillService unqualifiedProductBillService;
	
	/**
	 * 新建不合格品通知单(导入采购报检单)
	 * 
	 * @param                     record(
	 * @param unqualified_id 不合格品单号
	 * @param inspection_id       检验单号
	 * @param inspection_apply_id 报检单号
	 * @param lnventory_status    入库状态 1.已入库 2.未入库
	 * @param inspector           检验员
	 * @param create_time         创建时间
	 * @param update_time         更新时间
	 * @param tmstamp             时间戳
	 * @param wf_inst_remark      不合格品原因)
	 * @param                    
	 * @param mater_id            物料编码
	 * @param mater_name          物料名称
	 * @param contract_id         合同编号
	 * @param spec                规格型号
	 * @param sub_type            品种
	 * @param level               级别
	 * @param unit                单位
	 * @param quantity            数量
	 * @param use                 用途
	 * @param origin              产地
	 * @param buyer               采购员
	 * @param arrival_date        到货日期
	 * @param price               单价（元）
	 * @param amount              金额
	 * @param arrival_quantity    到货数量
	 * @param origin_batch        原厂批号
	 * @param batch               批号
	 * @param package_count       件数
	 * @param vehicle_no          车号
	 * @param rough_weight        毛重
	 * @param take_out_weight     扣杂
	 * @param package_weight      皮重
	 * @param validity_date       有效日期
	 * @param quality_time        质保期
	 * @param drawing             图号
	 * @param texture             材质
	 * @param casting             铸件号
	 * @param report_type         报告类别
	 * @param department          报告部门
	 * @param inspector           检验员
	 * @param report_date         报告日期
	 * @param remark              备注
	 * @return Integer类型的id
	 */
	@RequestMapping("/add")
	@ResponseBody
	public ResultVo<Integer> add(@RequestBody UnqualifiedProductBill record){
		/* LoginUser loginUser = UserHolder.getUser();
		 record.setCreator(loginUser.getUserId());*/
		Integer id = unqualifiedProductBillService.add(record);
		return ResultVo.success(id);
	}
	
	/**
	 * 主页
	 * 
	 * @param queryBean( 
	 * @param "page": 起始页码,
	 * @param "pageSize": 查询条数,
	 * @param "offset":偏移量,
	 * @param "order": 排序 默认降序2,
	 * @param "sort": 排序字段 默认排序字段为createTime,
	 * @param "requireTotal": 需要总记录数,
	 * @param "contractId": "合同编号",
	 * @param "unqualifiedId": "不合格品单号",
	 * @param "inspectionId": "检验单号",
	 * @param "inspectionApplyId": "报检单号",
	 * @param "materId": "物料编码",
	 * @param "materName": "物料名称",
	 * @param "spec": "规格型号",
	 * @param "subType": "品种",
	 * @param "level": "级别",
	 * @param "unit": "单位",
	 * @param "quantity": "数量",
	 * @param "use": "用途",
	 * @param "origin": "产地",
	 * @param "buyer": "采购员",
	 * @param "arrivalDate": "到货日期",
	 * @param "price": 单价（元）,
	 * @param "amount": 金额,
	 * @param "arrivalQuantity": "到货数量",
	 * @param "originBatch": "原厂批号",
	 * @param "batch": "批号",
	 * @param "packageCount": "件数",
	 * @param "vehicleNo": "车号",
	 * @param "roughWeight": 毛重,
	 * @param "takeOutWeight": 扣杂,
	 * @param "packageWeight": 皮重,
	 * @param "validityDate": "有效日期",
	 * @param "qualityTime": "质保期",
	 * @param "drawing": "图号",
	 * @param "texture": "材质",
	 * @param "casting": "铸件号",
	 * @param "department": "报告部门",
	 * @param "inspector": 检验员,
	 * @param "reportType": "报告类别",
	 * @param "reportDate": "报告日期",
	 * @param "remark": 备注,
	 * @param "purchaseOrderId": "采购申请单号",
	 * @param "lnventoryStatus": 入库状态 1.已入库 2.未入库,
	 * @param "lnvoicingStatus": 发票开具状态 1.已开 2.未开,
	 * @param "wfInstId": 流程实例Id,
	 * @param "wfInstTime": 流程发起时间,
	 * @param "wfInstRemark": 不合格品原因,
	 * @param "creator": 创建人,
	 * @param "createTime": "创建时间",
	 * @param "updateTime": "跟新时间",
	 * @param "tmstamp": "时间戳")
	 * @return unqualifiedPeoductBillList 集合 ,count 计数
	 */
	@RequestMapping(path="/list",method=RequestMethod.POST)
    @ResponseBody
    public ResultVo<PageResultBean> list( UnqualifiedPeoductBillQueryBean queryBean) {
        Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
        	queryBean.setOffset((page - 1) * pageSize);
        }

        List<UnqualifiedPeoductBillQueryBean>  unqualifiedPeoductBillList = unqualifiedProductBillService.list(queryBean);
        Long count = unqualifiedProductBillService.count(queryBean);
        return ResultVo.success(PageResultBean.success(count, unqualifiedPeoductBillList));
    }
	
	/**
	 * 不合格品报告管理编辑
	 * @param record
	 * @param "unqualifiedId": "不合格品单号",
	 * @param "inspectionId": "检验单号",
	 * @param "inspectionApplyId": "报检单号",
	 * @param "materId": "物料编码",
	 * @param "materName": "物料名称",
	 * @param "spec": "规格型号",
	 * @param "subType": "品种",
	 * @param "level": "级别",
	 * @param "unit": "单位",
	 * @param "quantity": "数量",
	 * @param "use": "用途",
	 * @param "origin": "产地",
	 * @param "buyer": "采购员",
	 * @param "arrivalDate": "到货日期",
	 * @param "price": 单价（元）,
	 * @param "amount": 金额,
	 * @param "arrivalQuantity": "到货数量",
	 * @param "originBatch": "原厂批号",
	 * @param "batch": "批号",
	 * @param "packageCount": "件数",
	 * @param "vehicleNo": "车号",
	 * @param "roughWeight": 毛重,
	 * @param "takeOutWeight": 扣杂,
	 * @param "packageWeight": 皮重,
	 * @param "validityDate": "有效日期",
	 * @param "qualityTime": "质保期",
	 * @param "drawing": "图号",
	 * @param "texture": "材质",
	 * @param "casting": "铸件号",
	 * @param "department": "报告部门",
	 * @param "inspector": 检验员,
	 * @param "reportType": "报告类别",
	 * @param "reportDate": "报告日期",
	 * @param "remark": 备注,
	 * @param "purchaseOrderId": "采购申请单号",
	 * @param "lnventoryStatus": 入库状态 1.已入库 2.未入库,
	 * @param "lnvoicingStatus": 发票开具状态 1.已开 2.未开,
	 * @param "wfInstId": 流程实例Id,
	 * @param "wfInstTime": 流程发起时间,
	 * @param "wfInstRemark": 不合格品原因,
	 * @param "creator": 创建人,
	 * @param "createTime": "创建时间",
	 * @param "updateTime": "跟新时间",
	 * @param "tmstamp": "时间戳")
	 * @return Integer类型的row
	 */
	@RequestMapping("/update")
    @ResponseBody
	public ResultVo<Integer> update(@RequestBody UnqualifiedProductBill record) {
    	Integer row = unqualifiedProductBillService.update(record);
    	return ResultVo.success(row);
    }
	
	/**
	 * 添加纠正措施
	 * 
	 * @param record(
	 * @param remedial_action_id        纠正措施编号
	 * @param unqualified_id            不合格品单号
	 * @param inspection_id             检验单号
	 * @param mater_id                  物料编码
	 * @param mater_name                物料名称
	 * @param spec                      规格型号
	 * @param drawing                   图号
	 * @param casting                   铸件号
	 * @param provider_id               供货单位
	 * @param quantity                  数量
	 * @param check_quantity            检测数量
	 * @param unit                      单位
	 * @param inspector                 检验员
	 * @param bill_date                 操作日期
	 * @param operator                  操作人
	 * @param nonconforming_description 不合格描述
	 * @param purchase_order_id         采购申请单号
	 * @param status                    状态 0.录入 1.审核 2 通过
	 * @param wf_inst_id                流程实例ID
	 * @param wf_inst_time              流程发起时间
	 * @param wf_inst_remark            审核备注
	 * @param create_time               创建时间
	 * @param update_time               更新时间
	 * @param tmstamp                   时间戳)
	 * @return integer类型的row
	 */
	@RequestMapping("/addC")
	@ResponseBody
	public ResultVo<Integer> add(@RequestBody CorrectiveAction record) {
		Integer row = unqualifiedProductBillService.insertCorrertive(record);
		return ResultVo.success(row);
	}
	
	/**
	 * 添加8D报告
	 * 
	 * @param                    record(
	 * @param file_id            文件编号
	 * @param unqualified_id     不合格品单号
	 * @param not_conform_report 不符合报告类型 1.技术 2.管理 3.包装 4.信息
	 * @param type_name          机型/名称
	 * @param reject_id          不合格品单号
	 * @param one_d              1D团队
	 * @param two_d              2D问题描述
	 * @param three_d            3D防堵措施
	 * @param four_d             4D根本原因分析
	 * @param five_d             5D根本对策
	 * @param six_d              6D效果确认
	 * @param seven_d            7D标准化文件更改
	 * @param eight_d            8D总结
	 * @param create_time        创建时间
	 * @param update_time        更新时间
	 * @param tmstamp            时间戳 )
	 * @return integer类型的row
	 */
	@RequestMapping("/addU")
	@ResponseBody
	public ResultVo<Integer> add(@RequestBody UpbEightDReport record) {
		Integer row = unqualifiedProductBillService.insertUpbEightD(record);
		return ResultVo.success(row);
	}
}
