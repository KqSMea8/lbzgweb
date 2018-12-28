package com.lyarc.tp.corp.purchase.provider.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.common.exception.CorpException;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderMaterial;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderMaterialQueryBean;
import com.lyarc.tp.corp.purchase.provider.dto.ProviderMaterialDto;
import com.lyarc.tp.corp.purchase.provider.form.ProviderMaterialForm;
import com.lyarc.tp.corp.purchase.provider.service.ProviderMaterialService;
import com.lyarc.tp.corp.system.material.bean.Material;
import com.lyarc.tp.corp.system.material.service.MaterialService;

@ResponseBody
@Controller
@RequestMapping(path = "/api/providerMaterial")
public class ProviderMaterialController {

	@Autowired
	private ProviderMaterialService providerMaterialService;
	@Autowired
	private MaterialService materialService;

	@RequestMapping("/list")
	@ResponseBody
	public ResultVo<PageResultBean> list(@RequestBody ProviderMaterialQueryBean queryBean) {
		Integer page = queryBean.getPage();
		Integer pageSize = queryBean.getPageSize();
		if (page != null && pageSize != null) {
			queryBean.setOffset((page - 1) * pageSize);
		}

		List<ProviderMaterialDto> providerMaterialList = providerMaterialService.list(queryBean);
		Long count = providerMaterialService.count(queryBean);
		return ResultVo.success(PageResultBean.success(count, providerMaterialList));
	}
	//
	// @RequestMapping("/add")
	// @ResponseBody
	// public ResultVo<Integer> add(@Valid @RequestBody ProviderMaterialForm
	// providerMaterialForm) {
	// ProviderMaterial providerMaterial =
	// JMapperUtils.getDestination(providerMaterialForm, ProviderMaterial.class,
	// ProviderMaterialForm.class);
	// LoginUser loginUser = UserHolder.getUser();
	// providerMaterial.setCreator(loginUser.getUserId());
	// Integer id = providerMaterialService.add(providerMaterial);
	// return ResultVo.success(id);
	// }

	@RequestMapping("/materType/update")
	@ResponseBody
	public ResultVo<Integer> update(@RequestBody ProviderMaterialForm providerMaterialForm) {
		String materType = providerMaterialForm.getMaterType();
		if (StringUtils.isEmpty(materType)) {
			throw new CorpException(ResultVo.failure("请求参数不完整"));
		}
		Material material = new Material();
		material.setMaterId(providerMaterialForm.getMaterId());
		material.setApplyToProductType(materType);
		materialService.update(material);
		return ResultVo.success(1);
	}

	@RequestMapping("/degrade")
	@ResponseBody
	public ResultVo<Integer> degrade(@RequestBody ProviderMaterialForm providerMaterialForm) {
		if (StringUtils.isEmpty(providerMaterialForm.getProviderId())
				|| StringUtils.isEmpty(providerMaterialForm.getMaterId())) {
			throw new CorpException(ResultVo.failure("请求参数不完整"));
		}
		ProviderMaterial providerMaterial = new ProviderMaterial();
		providerMaterial.setProviderId(providerMaterialForm.getProviderId());
		providerMaterial.setMaterId(providerMaterialForm.getMaterId());
		providerMaterial.setStatus(1);
		providerMaterial.setUpdateTime(new Date());
		Integer rows = providerMaterialService.update(providerMaterial);
		return ResultVo.success(rows);
	}

	// @RequestMapping("/updateStatus")
	// @ResponseBody
	// public ResultVo<Integer> updateStatus(@RequestBody ProviderMaterialForm
	// providerMaterialForm) {
	// ProviderMaterial providerMaterial = new ProviderMaterial();
	// providerMaterial.setProviderMaterialId(providerMaterialForm.getProviderMaterialId());
	// providerMaterial.setStatus(providerMaterialForm.getStatus());
	// providerMaterial.setUpdateTime(new Date());
	// Integer rows = providerMaterialService.update(providerMaterial);
	// return ResultVo.success(rows);
	// }
	//
	// @RequestMapping("/delete")
	// @ResponseBody
	// public ResultVo<Integer> delete(@RequestParam("providerMaterialId") String
	// providerMaterialId) {
	// Integer rows = providerMaterialService.delete(providerMaterialId);
	// return ResultVo.success(rows);
	// }
	//
	// @RequestMapping("/get")
	// @ResponseBody
	// public ResultVo<ProviderMaterial> detail(@RequestParam("providerMaterialId")
	// String providerMaterialId) {
	// ProviderMaterial providerMaterial =
	// providerMaterialService.getProviderMaterial(providerMaterialId);
	// return ResultVo.success(providerMaterial);
	// }
	//
	// @RequestMapping("/search")
	// @ResponseBody
	// public ResultVo<List<ProviderMaterialDto>> search(@RequestBody
	// ProviderMaterialQueryBean queryBean) {
	// List<ProviderMaterialDto> providerMaterialList =
	// providerMaterialService.list(queryBean);
	// return ResultVo.success(providerMaterialList);
	// }

}
