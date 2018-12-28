package com.lyarc.tp.corp.quality.InspectionSheet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.quality.InspectionSheet.bean.InspectionSheet;
import com.lyarc.tp.corp.quality.InspectionSheet.bean.InspectionSheetDetail;
import com.lyarc.tp.corp.quality.InspectionSheet.bean.InspectionSheetQueryBean;
import com.lyarc.tp.corp.quality.InspectionSheet.service.InspectionSheetService;
/**
 * 检验单
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping("api/InspectionSheet")
public class InspectionSheetController {
	
	@Autowired
	public InspectionSheetService inspectionSheetService;

	/**
	 * 
	 * @Desc 小单条物料保存按钮
	 */
	@RequestMapping("/add")
	@ResponseBody
	public ResultVo<Integer> insert(@RequestBody InspectionSheetDetail record){
		Integer id = inspectionSheetService.add(record);
		return ResultVo.success(id);
		
	}
	
	/**
	 * @Desc 添加检验单主表 t_inspection,全部提交保存按钮
	 */
	@RequestMapping("/addMain")
	@ResponseBody
	public ResultVo<Integer> addMain(@RequestBody InspectionSheet record){
		Integer id = inspectionSheetService.addMain(record);
		return ResultVo.success(id);
	}
	
	/**
	 * 
	 * @Desc 获取流水号
	 * @Return
	 */
	@ResponseBody
	@RequestMapping("/getId")
	public ResultVo<String> getIsId(){
		String row = inspectionSheetService.getIsId();
		return ResultVo.success(row);
	}
	 
	/**
	 * 导入采购报检单
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ResultVo<PageResultBean> list(@RequestBody InspectionSheet bean) {
		Integer page = bean.getPage();
		Integer pageSize = bean.getPageSize();
		if (page != null && pageSize != null) {
			bean.setOffset((page - 1) * pageSize);
		}
		List<InspectionSheet> list = inspectionSheetService.list(bean);
		Long count = inspectionSheetService.count(bean);
		return ResultVo.success(PageResultBean.success(count, list));
	}
	
}
