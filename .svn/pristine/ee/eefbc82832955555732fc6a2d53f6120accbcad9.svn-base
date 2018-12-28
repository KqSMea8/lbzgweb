package com.lyarc.tp.corp.quality.revision.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
import com.lyarc.tp.corp.quality.revision.bean.RevisedInspectionQueryBean;
import com.lyarc.tp.corp.quality.revision.bean.TcheckRevisionLog;
@Mapper
public interface RevisedInspectionMapper {
	//原砂检验项目
	List<CheckOriginalSand> getAllCheckOriginalSand(RevisedInspectionQueryBean queryBean);
	List<CheckOriginalSand> selectNewCheckOriginalSand(CheckOriginalSand queryBean);
	Integer insertAllCheckOriginalSand(CheckOriginalSand queryBean);
	
	//砖管检验
	List<CheckBrickTube> getAllCheckBrickTube(@Param(value="batchId") String batchId);
	CheckBrickTube selectNewCheckBrickTube(@Param(value="batchId") String batchId,@Param(value="versionId") Integer versionId);
	Integer	insertAllCheckBrickTube(CheckBrickTube queryBean);
	List<CheckBrickTubeDetail> getAllCheckBrickTubeDetail(@Param(value="batchId") String batchId);
	List<CheckBrickTubeDetail> selectNewCheckBrickTubeDetail(@Param(value="batchId") String batchId,@Param(value="versionId") Integer versionId);
	Integer	insertAllCheckBrickTubeDetail(CheckBrickTubeDetail queryBean);
	
	//电极检验
    List<CheckElectrode> getAllCheckElectrode(RevisedInspectionQueryBean queryBean);
    List<CheckElectrode> selectNewCheckElectrode(CheckElectrode queryBean);
    Integer insertAllCheckElectrode(CheckElectrode queryBean);
	
    //铁合金检验
    List<CheckFerroAlloy> getAllCheckFerroAlloy(@Param(value="batchId")  String batchId);
	CheckFerroAlloy selectNewCheckFerroAlloy(@Param(value="batchId") String batchId,@Param(value="versionId") Integer versionId);
	Integer	insertAllCheckFerroAlloy(CheckFerroAlloy queryBean);
	List<CheckFerroAlloyDetail> getAllCheckFerroAlloyDetail(@Param(value="batchId") String batchId);
	List<CheckFerroAlloyDetail> selectNewCheckFerroAlloyDetail(@Param(value="batchId") String batchId,@Param(value="versionId") Integer versionId);
	Integer	insertAllCheckFerroAlloyDetail(CheckFerroAlloyDetail queryBean);
	
	//石灰检验
	List<CheckLime> getAllCheckLime(RevisedInspectionQueryBean queryBean);
	CheckLime selectNewCheckLime(@Param(value="batchId") String batchId,@Param(value="versionId") Integer versionId);
	Integer insertAllCheckLime(CheckLime queryBean);
	
	//树脂，铬矿砂检验
	List<CheckResinChromeOre> getAllCheckResinChromeOre(RevisedInspectionQueryBean queryBean);
	List<CheckResinChromeOre> selectNewCheckResinChromeOre(RevisedInspectionQueryBean queryBean);
	Integer insertAllCheckResinChromeOre(CheckResinChromeOre queryBean);
	
	//原辅材料目视检验报告
	List<CheckVisualInspection> getAllCheckVisualInspection(CheckVisualInspection queryBean);
	CheckVisualInspection selectNewCheckVisualInspection(@Param(value="batchId") String batchId,@Param(value="versionId") Integer versionId);
	CheckVisualInspection getInitialCheckVisualInspection(@Param(value="batchId") String batchId,@Param(value="versionId") Integer versionId);
	Integer	insertAllCheckVisualInspection(CheckVisualInspection queryBean);
	
	//V法涂料检验
	List<CheckVMethodCoating> getAllCheckVMethodCoating(@Param(value="batchId")  String batchId);
	CheckVMethodCoating selectNewCheckVMethodCoating(@Param(value="batchId") String batchId,@Param(value="versionId") Integer versionId);
	Integer insertAllCheckVMethodCoating(CheckVMethodCoating queryBean);
	List<CheckVMethodCoatingDetail> getAllCheckVMethodCoatingDetail(@Param(value="batchId")  String batchId);
	List<CheckVMethodCoatingDetail> selectNewCheckVMethodCoatingDetail(@Param(value="batchId") String batchId,@Param(value="versionId") Integer versionId);
	Integer insertAllCheckVMethodCoatingDetail(CheckVMethodCoatingDetail queryBean);
	
	//水玻璃检验
	List<CheckWaterGlass> getAllCheckWaterGlass(@Param(value="batchId")  String batchId);
	CheckWaterGlass selectNewCheckWaterGlass(@Param(value="batchId") String batchId,@Param(value="versionId") Integer versionId);
	Integer insertAllCheckWaterGlass(CheckWaterGlass queryBean);

	//检验结果修订日志
	Integer updCheckRevisionLogVersionId(@Param(value="batchId") String batchId,@Param(value="versionId") Integer versionId,@Param(value="operator") String operator);
	Integer insertNewTcheckRevisionLog(TcheckRevisionLog queryBean);
	

}
