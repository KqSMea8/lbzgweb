package com.lyarc.tp.corp.inspection.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.inspection.dto.InspectionDTO;
import com.lyarc.tp.corp.inspection.form.InspectionForm;
import com.lyarc.tp.corp.inspection.form.InspectionQueryForm;
import com.lyarc.tp.corp.inspection.service.InspectionService;
import com.lyarc.tp.corp.login.form.LoginUser;

/**
 * 检验单的前端api控制类
 * 
 * @author shiyong
 *
 */
@ResponseBody
@Controller
@RequestMapping(path = "/api/inspectionApply")

public class InspectionController {

	@Autowired
	private InspectionService inspectionService;

	/**
	 * 新建 /add
	 */
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResultVo<Integer> add(@Valid @RequestBody InspectionForm inspectionForm) {
		LoginUser loginUser = UserHolder.getUser();
		inspectionForm.setCreator(loginUser.getUserId());
		inspectionForm.setOperator(loginUser.getEmpId());
		inspectionForm.setApplyDate(LocalDate.now());

		Integer rows = inspectionService.add(inspectionForm);
		return ResultVo.success(rows);
	}

	/**
	 * 报检单详情 /get
	 */
	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public ResultVo<InspectionDTO> get(@RequestParam("inspectionApplyId") String inspectionApplyId) {
		if (inspectionApplyId == null) {
			return ResultVo.build(400, "报检单号不能为空");
		} else {
			Integer id = inspectionService.selectedId(inspectionApplyId);// 查询id是否存在 ；
			if (id == null) {
				return ResultVo.build(400, "报检单号不存在");
			} else {
				InspectionDTO inspectionDTO = inspectionService.get(inspectionApplyId);
				return ResultVo.success(inspectionDTO);
			}
		}
	}

	/**
	 * 删除 delete
	 */
	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResultVo<Integer> delete(@RequestParam("inspectionApplyId") String inspectionApplyId) {
		Integer id = inspectionService.selectedId(inspectionApplyId);
		if (id == null) {
			return ResultVo.build(400, "该报检单不存在");
		} else {
			Integer rows = inspectionService.delete(inspectionApplyId);
			return ResultVo.success(rows);
		}

	}

	/**
	 * 报检单的编辑 /update
	 */
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResultVo<Integer> update(@Valid @RequestBody InspectionForm inspectionForm) {
		Integer id = inspectionService.selectedId(inspectionForm.getInspectionApplyId());
		if (id == null) {
			return ResultVo.build(400, "该报检单不存在");
		} else {
			Integer rows = inspectionService.update(inspectionForm);
			return ResultVo.success(rows);
		}
	}

	/**
	 * 报检单的列表 /list
	 */
	@RequestMapping(path = "/list", method = RequestMethod.POST)
	public ResultVo<PageResultBean> list(@Valid @RequestBody InspectionQueryForm inspectionQueryForm) {
		Integer page = inspectionQueryForm.getPage();
		Integer pageSize = inspectionQueryForm.getPageSize();
		if (page != null && pageSize != null) {
			inspectionQueryForm.setOffset((page - 1) * pageSize);
		}
		PageResultBean list = inspectionService.list(inspectionQueryForm);
		return ResultVo.success(list);
	}

}
