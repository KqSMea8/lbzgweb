package com.lyarc.tp.corp.quality.revision.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.quality.revision.bean.CheckBrickTube;
import com.lyarc.tp.corp.quality.revision.bean.CheckElectrode;
import com.lyarc.tp.corp.quality.revision.bean.CheckFerroAlloy;
import com.lyarc.tp.corp.quality.revision.bean.CheckLime;
import com.lyarc.tp.corp.quality.revision.bean.CheckOriginalSand;
import com.lyarc.tp.corp.quality.revision.bean.CheckResinChromeOre;
import com.lyarc.tp.corp.quality.revision.bean.CheckVMethodCoating;
import com.lyarc.tp.corp.quality.revision.bean.CheckVisualInspection;
import com.lyarc.tp.corp.quality.revision.bean.CheckWaterGlass;
import com.lyarc.tp.corp.quality.revision.bean.RevisedInspection;
import com.lyarc.tp.corp.quality.revision.bean.RevisedInspectionQueryBean;
import com.lyarc.tp.corp.quality.revision.bean.TcheckRevisionLog;
import com.lyarc.tp.corp.quality.revision.bean.TcheckRevisionLogQueryBean;
import com.lyarc.tp.corp.quality.revision.form.CheckBrickTubeForm;
import com.lyarc.tp.corp.quality.revision.form.CheckFerroAlloyForm;
import com.lyarc.tp.corp.quality.revision.form.CheckVMethodCoatingForm;
import com.lyarc.tp.corp.quality.revision.service.TcheckRevisionLogService;

@ResponseBody
@Controller
@RequestMapping(path = "/api/revision")
public class TcheckRevisionLogController {
	
	@Autowired
    private TcheckRevisionLogService tcheckRevisionLogService;
	
	@RequestMapping("/list")
    @ResponseBody
    public ResultVo<PageResultBean> list(@RequestBody TcheckRevisionLogQueryBean queryBean) {
        Integer page = queryBean.getPage();
        Integer pageSize = queryBean.getPageSize();
        if (page != null && pageSize != null) {
            queryBean.setOffset((page - 1) * pageSize);
        }

        List<TcheckRevisionLog>	 revisionList =tcheckRevisionLogService.list(queryBean);
        Long count = tcheckRevisionLogService.count(queryBean);
        return ResultVo.success(PageResultBean.success(count, revisionList));
    }
	
	@RequestMapping("/getAllCheckOriginalSand")
    @ResponseBody
    public ResultVo<List<CheckOriginalSand>> getAllCheckOriginalSand(RevisedInspectionQueryBean queryBean) throws JsonParseException, JsonMappingException, IOException {
		List<CheckOriginalSand> revisedInspection = tcheckRevisionLogService.getAllCheckOriginalSand(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/getNewCheckOriginalSand")
    @ResponseBody
    public ResultVo<List<CheckOriginalSand>> getNewCheckOriginalSand(CheckOriginalSand queryBean) throws JsonParseException, JsonMappingException, IOException {
		List<CheckOriginalSand> revisedInspection = tcheckRevisionLogService.getNewCheckOriginalSand(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/addAllCheckOriginalSand")
    @ResponseBody
    public ResultVo<Integer> addAllCheckOriginalSand(@RequestBody CheckOriginalSand queryBean){
		Integer id=tcheckRevisionLogService.addAllCheckOriginalSand(queryBean);
		return ResultVo.success(id);
	}
	
	@RequestMapping("/getAllCheckBrickTube")
    @ResponseBody
    public ResultVo<List<CheckBrickTubeForm>> getAllCheckBrickTube(RevisedInspectionQueryBean queryBean) {
		List<CheckBrickTubeForm> revisedInspection = tcheckRevisionLogService.getAllCheckBrickTube(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/getNewCheckBrickTube")
    @ResponseBody
    public ResultVo<CheckBrickTubeForm> getNewCheckBrickTube(RevisedInspectionQueryBean queryBean) {
		CheckBrickTubeForm revisedInspection = tcheckRevisionLogService.getNewCheckBrickTube(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/addAllCheckBrickTube")
    @ResponseBody
    public ResultVo<Integer> addAllCheckBrickTube(@RequestBody CheckBrickTube queryBean){
		Integer id=tcheckRevisionLogService.addAllCheckBrickTube(queryBean);
		return ResultVo.success(id);
	}
	
	@RequestMapping("/getAllCheckElectrode")
    @ResponseBody
    public ResultVo<List<CheckElectrode>> getAllCheckElectrode(RevisedInspectionQueryBean queryBean) throws JsonParseException, JsonMappingException, IOException {
		List<CheckElectrode> revisedInspection = tcheckRevisionLogService.getAllCheckElectrode(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/getNewCheckElectrode")
    @ResponseBody
    public ResultVo<List<CheckElectrode>> getNewCheckElectrode(CheckElectrode queryBean) throws JsonParseException, JsonMappingException, IOException {
		List<CheckElectrode> revisedInspection = tcheckRevisionLogService.getNewCheckElectrode(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/addAllCheckElectrode")
    @ResponseBody
    public ResultVo<Integer> addAllCheckElectrode(@RequestBody CheckElectrode queryBean){
		Integer id=tcheckRevisionLogService.addAllCheckElectrode(queryBean);
		return ResultVo.success(id);
	}
	
	@RequestMapping("/getAllCheckFerroAlloy")
    @ResponseBody
    public ResultVo<List<CheckFerroAlloyForm>> getAllCheckFerroAlloy(RevisedInspectionQueryBean queryBean) {
		List<CheckFerroAlloyForm> revisedInspection = tcheckRevisionLogService.getAllCheckFerroAlloy(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/getNewCheckFerroAlloy")
    @ResponseBody
    public ResultVo<CheckFerroAlloyForm> getNewCheckFerroAlloy(RevisedInspectionQueryBean queryBean) {
		CheckFerroAlloyForm revisedInspection = tcheckRevisionLogService.getNewCheckFerroAlloy(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/addAllCheckFerroAlloy")
    @ResponseBody
    public ResultVo<Integer> addAllCheckFerroAlloy(@RequestBody CheckFerroAlloy queryBean) {
		Integer id=tcheckRevisionLogService.addAllCheckFerroAlloy(queryBean);
		return ResultVo.success(id);
	}
	
	@RequestMapping("/getAllCheckLime")
    @ResponseBody
    public ResultVo<List<CheckLime>> getAllCheckLime(RevisedInspectionQueryBean queryBean) {
		List<CheckLime> revisedInspection = tcheckRevisionLogService.getAllCheckLime(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/getNewCheckLime")
    @ResponseBody
    public ResultVo<CheckLime> getNewCheckLime(CheckLime queryBean) {
		CheckLime revisedInspection = tcheckRevisionLogService.getNewCheckLime(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/addAllCheckLime")
    @ResponseBody
    public ResultVo<Integer> addAllCheckLime(@RequestBody CheckLime queryBean){
		Integer id=tcheckRevisionLogService.addAllCheckLime(queryBean);
		return ResultVo.success(id);
	}
	
	@RequestMapping("/getAllCheckResinChromeOre")
    @ResponseBody
    public ResultVo<List<CheckResinChromeOre>> getAllCheckResinChromeOre(RevisedInspectionQueryBean queryBean) throws JsonParseException, JsonMappingException, IOException {
		List<CheckResinChromeOre> revisedInspection = tcheckRevisionLogService.getAllCheckResinChromeOre(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/getNewCheckResinChromeOre")
    @ResponseBody
    public ResultVo<List<CheckResinChromeOre>> getNewCheckResinChromeOre(RevisedInspectionQueryBean queryBean) throws JsonParseException, JsonMappingException, IOException {
		List<CheckResinChromeOre> revisedInspection = tcheckRevisionLogService.getNewCheckResinChromeOre(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/addAllCheckResinChromeOre")
    @ResponseBody
    public ResultVo<Integer> addAllCheckResinChromeOre(@RequestBody CheckResinChromeOre queryBean){
		Integer id=tcheckRevisionLogService.addAllCheckResinChromeOre(queryBean);
		return ResultVo.success(id);
	}
	
	@RequestMapping("/getAllCheckVisualInspection")
    @ResponseBody
    public ResultVo<List<CheckVisualInspection>> getAllCheckVisualInspection(CheckVisualInspection queryBean) {
		List<CheckVisualInspection> revisedInspection = tcheckRevisionLogService.getAllCheckVisualInspection(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/getNewCheckVisualInspection")
    @ResponseBody
    public ResultVo<CheckVisualInspection> getNewCheckVisualInspection(CheckVisualInspection queryBean) {
		CheckVisualInspection revisedInspection = tcheckRevisionLogService.getNewCheckVisualInspection(queryBean);
		return ResultVo.success(revisedInspection);
	}
	/*
	 * 获取初始数据
	 */
	@RequestMapping("/getInitialCheckVisualInspection")
    @ResponseBody
    public ResultVo<CheckVisualInspection> getInitialCheckVisualInspection(CheckVisualInspection queryBean) {
		CheckVisualInspection revisedInspection = tcheckRevisionLogService.getInitialCheckVisualInspection(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/addAllCheckVisualInspection")
    @ResponseBody
    public ResultVo<Integer> addAllCheckVisualInspection(@RequestBody CheckVisualInspection queryBean) {
		Integer id=tcheckRevisionLogService.addAllCheckVisualInspection(queryBean);
		return ResultVo.success(id);
	}
	
	@RequestMapping("/getAllCheckVMethodCoating")
    @ResponseBody
    public ResultVo<List<CheckVMethodCoatingForm>> getAllCheckVMethodCoating(RevisedInspectionQueryBean queryBean) {
		List<CheckVMethodCoatingForm> revisedInspection = tcheckRevisionLogService.getAllCheckVMethodCoating(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/getNewCheckVMethodCoating")
    @ResponseBody
    public ResultVo<CheckVMethodCoatingForm> getNewCheckVMethodCoating(RevisedInspectionQueryBean queryBean) {
		CheckVMethodCoatingForm revisedInspection = tcheckRevisionLogService.getNewCheckVMethodCoating(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/addAllCheckVMethodCoating")
    @ResponseBody
    public ResultVo<Integer> addAllCheckVMethodCoating(@RequestBody CheckVMethodCoating  queryBean) {
		Integer id=tcheckRevisionLogService.addAllCheckVMethodCoating(queryBean);
		return ResultVo.success(id);
	}
	
	@RequestMapping("/getAllCheckWaterGlass")
    @ResponseBody
    public ResultVo<List<CheckWaterGlass>> getAllCheckWaterGlass(RevisedInspection queryBean) {
		List<CheckWaterGlass> revisedInspection = tcheckRevisionLogService.getAllCheckWaterGlass(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/getNewCheckWaterGlass")
    @ResponseBody
    public ResultVo<CheckWaterGlass> getNewCheckWaterGlass(CheckWaterGlass queryBean) {
		CheckWaterGlass revisedInspection = tcheckRevisionLogService.getNewCheckWaterGlass(queryBean);
		return ResultVo.success(revisedInspection);
	}
	
	@RequestMapping("/addAllCheckWaterGlass")
    @ResponseBody
    public ResultVo<Integer> addAllCheckWaterGlass(@RequestBody CheckWaterGlass queryBean){
		Integer id=tcheckRevisionLogService.addAllCheckWaterGlass(queryBean);
		return ResultVo.success(id);
	}
	
}
