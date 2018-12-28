package com.lyarc.tp.corp.system.material.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.DateUtil;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.seq.service.SeqService;
import com.lyarc.tp.corp.system.material.bean.Material;
import com.lyarc.tp.corp.system.material.bean.MaterialCategory;
import com.lyarc.tp.corp.system.material.bean.MaterialQueryBean;
import com.lyarc.tp.corp.system.material.dto.MaterialCategoryNodeDto;
import com.lyarc.tp.corp.system.material.form.MaterialCategoryForm;
import com.lyarc.tp.corp.system.material.form.MaterialForm;
import com.lyarc.tp.corp.system.material.service.MaterialService;
import com.lyarc.tp.corp.util.JMapperUtils;

@ResponseBody
@Controller
@RequestMapping(path = "/api/material")
public class MaterialController {

	@Autowired
	private MaterialService materialService;
    @Autowired
    private SeqService seqService;

	@RequestMapping("/buildCategoryTree")
	@ResponseBody
	public ResultVo<List<MaterialCategoryNodeDto>> buildCategoryTree() {
		List<MaterialCategoryNodeDto> list = materialService.buildCategoryTree();
		return ResultVo.success(list);
	}

	@RequestMapping("/category/list")
	@ResponseBody
	public ResultVo listCategory(@Valid @RequestBody MaterialCategory materialCategory) {
		List<MaterialCategory> list = materialService.listCategory(materialCategory);
		return ResultVo.success(list);
	}

	@RequestMapping("/category/add")
	@ResponseBody
	public ResultVo addCategory(@Valid @RequestBody MaterialCategoryForm from) {
		MaterialCategory materialCategory = JMapperUtils.getDestination(from, MaterialCategory.class,
				MaterialCategoryForm.class);
		LoginUser loginUser = UserHolder.getUser();
		materialCategory.setCreator(loginUser.getUserId());
		materialService.addCategory(materialCategory);
		return ResultVo.success();
	}

	@RequestMapping("/category/update")
	@ResponseBody
	public ResultVo updateCategory(@Valid @RequestBody MaterialCategoryForm from) {
		MaterialCategory materialCategory = JMapperUtils.getDestination(from, MaterialCategory.class,
				MaterialCategoryForm.class);
		materialCategory.setUpdateTime(new Date());
		materialService.updateCategory(materialCategory);
		return ResultVo.success();
	}

	@RequestMapping("/category/delete")
	@ResponseBody
	public ResultVo<Integer> deleteCategory(@RequestParam("id") Integer id) {
		Integer rows = materialService.deleteCategory(id);
		return ResultVo.success(rows);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultVo<PageResultBean> list(@RequestBody MaterialQueryBean queryBean) {
		Integer page = queryBean.getPage();
		Integer pageSize = queryBean.getPageSize();
		if (page != null && pageSize != null) {
			queryBean.setOffset((page - 1) * pageSize);
		}
		if (queryBean.getStatus() != null) {
			if (-1 == queryBean.getStatus()) {
				queryBean.setStatus(null);
			}
		}
		PageResultBean pageResultBean = materialService.list(queryBean);
		return ResultVo.success(pageResultBean);
	}

	@RequestMapping("/add")
	@ResponseBody
	public ResultVo add(@Valid @RequestBody MaterialForm from) throws ParseException {
		Material material = JMapperUtils.getDestination(from, Material.class, MaterialForm.class);
		LoginUser loginUser = UserHolder.getUser();
		material.setCreator(loginUser.getUserId()); 
		String code = materialService.getCode(material.getCateId()) + "." +
				seqService.getSerial("t_material_"+material.getCateId(),3);//日期写死，流水固定
		material.setMaterId(code);
		materialService.add(material);
		return ResultVo.success();
	}

	@RequestMapping("/update")
	@ResponseBody
	public ResultVo update(@Valid @RequestBody MaterialForm from) {
		Material material = JMapperUtils.getDestination(from, Material.class, MaterialForm.class);
		material.setUpdateTime(new Date());
		materialService.update(material);
		return ResultVo.success();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResultVo<Integer> delete(@RequestParam("materId") String materId) {
		Integer rows = materialService.delete(materId);
		return ResultVo.success(rows);
	}

	@RequestMapping("/get")
	@ResponseBody
	public ResultVo<Material> detail(@RequestParam("materId") String materId) {
		Material material = materialService.getMaterial(materId);
		return ResultVo.success(material);
	}
	
	@RequestMapping("/getCode")
	@ResponseBody
	public ResultVo<String> getCode(@RequestParam("cateId") Integer cateId) {
		String code = materialService.getCode(cateId);
		return ResultVo.success(code);
	}

}
