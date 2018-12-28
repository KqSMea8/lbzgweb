<template>
  <div class="inquire-detail-card editable">
    <div class="title">{{title}}</div>
    <div>1.原辅材料目视检验1001</div>
    <div v-if="material.ledgerId==1001" class="editable-table">
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th>供应商</th>
        <th>物料名称</th>
        <th width="60">数量</th>
        <th width="60">批次号</th>
        <th width="80">检验日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{material.batchId}}
          </td>
          <td>
            {{material.ledgerId}}
          </td>
          <td class="col-cz">
            {{material.texture}}
          </td>
          <td class="col-yd">
            {{material.batchId}}
          </td>
          <td>
            {{deliveryTypeName}}
          </td>
        </tr>
        </tbody>
        <thead>
        <th width="380">检验项目；标准</th>
        <th width="80">化验员</th>
        <th width="80">检验员</th>
        <th width="80">审核人</th>
        <th width="110">审核日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{material.inspectionItem}}
          </td>
          <td>
            {{material.quantity}}
          </td>
          <td>
            {{material.weight}}
          </td>
          <td>
            {{material.weightTotal}}
          </td>
          <td>
            {{material.deliveryDate}}
          </td>
        </tr>
       </tbody>
      </table>
    </div>
    <div>2.铁合金检验1002</div>
    <div v-if="material.ledgerId==1002" class="editable-table">
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th>供应商</th>
        <th>物料名称</th>
        <th width="60">数量</th>
        <th width="60">批次号</th>
        <th>检验日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{material.batchId}}
          </td>
          <td>
            {{material.ledgerId}}
          </td>
          <td class="col-cz">
            {{material.batchId}}
          </td>
          <td class="col-yd">
            {{material.hardness}}
          </td>
          <td>
            {{deliveryTypeName}}
          </td>
        </tr>
        </tbody>
      </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th>分析频次</th>
        <th>C</th>
        <th>Si</th>
        <th>Mn</th>
        <th>S</th>
        <th>P</th>
        <th>Cr</th>
        <th>Mo</th>
        <th>检验结论</th>
        <th>备注</th>
        </thead>
      <tbody>
      <tr v-for="(item,index) in material.detailList">
        <th>{{index+1}}</th>
        <td>{{item.contentC}}</td>
        <td>{{item.contentSi}}</td>
        <td>{{item.contentMn}}</td>
        <td>{{item.contentS}}</td>
        <td>{{item.contentP}}</td>
        <td>{{item.contentCr}}</td>
        <td>{{item.contentMo}}</td>
        <td>{{item.inspectConclusion}}</td>
        <td v-if="index==0" rowspan="111" >{{material.remark}} </td>
      </tr>
      </tbody>
      </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th width="80">化验员</th>
        <th width="80">检验员</th>
        <th width="80">审核人</th>
        <th width="80">审核日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{material.quantity}}
          </td>
          <td>
            {{material.weight}}
          </td>
          <td>
            {{material.weightTotal}}
          </td>
          <td>
            {{material.deliveryDate}}
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div>3.砖管检验1004</div>
    <div v-if="material.ledgerId==1004" class="editable-table">
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th>供应商</th>
        <th>物料名称</th>
        <th width="60">数量</th>
        <th width="60">批次号</th>
        <th width="80">检验日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{material.batchId}}
          </td>
          <td>
            {{material.ledgerId}}
          </td>
          <td class="col-cz">
            {{material.texture}}
          </td>
          <td class="col-yd">
            {{material.batchId}}
          </td>
          <td>
            {{deliveryTypeName}}
          </td>
        </tr>
        </tbody>
      </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th>生产厂家</th>
        <th>名称</th>
        <td colspan="2">名称2</td>
        <th>型号</th>
        <td colspan="2">型号2</td>
        </thead>
        <tbody>
        <tr>
          <td rowspan="3">{{material.origin}}生产厂家</td>
          <td colspan="6">{{material.stockQuantity}}进货数量</td>
        </tr>
        <tr>
          <td colspan="6">{{material.spotCheckQuantity}}抽检数量</td>
        </tr>
        <th><tr>裂纹 </tr></th>
        <th><tr>毛刺</tr></th>
        <th><tr>损伤</tr></th>
        <th><tr>尺寸不合格</tr></th>
        <th><tr>接口不合格</tr></th>
        <th><tr>端面平面度</tr></th>
        <tr>
          <th>数量</th>
          <td v-for="(item,index) in material.detailList">{{item.quantity}}</td>
        </tr>
        <tr>
          <th>抽检合格率</th>
          <td v-for="(item,index) in material.detailList">{{item.spotCheckYieid}}</td>
        </tr>
        <tr>
          <th>合格数量</th>
          <td colspan="6">{{material.qualifiedNo}}</td>
        </tr>
        <tr>
          <th>合格率</th>
          <td colspan="6">{{material.yieid}}</td>
        </tr>
        <tr>
          <th>结论</th>
          <td colspan="6">{{material.conclusion}}</td>
        </tr>
        </tbody>
      </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th>名称</th>
        <td colspan="2">名称2</td>
        <th>型号</th>
        <td colspan="2">型号2</td>
        </thead>
        <tbody>
        <tr>
          <td colspan="6">进货数量</td>
        </tr>
        <tr>
          <td colspan="6">抽检数量</td>
        </tr>
        <th><tr>裂纹 </tr></th>
        <th><tr>毛刺</tr></th>
        <th><tr>损伤</tr></th>
        <th><tr>尺寸不合格</tr></th>
        <th><tr>接口不合格</tr></th>
        <th><tr>端面平面度</tr></th>
        <tr>
          <td v-for="(item,index) in material.detailList">{{item.quantity}}</td>
        </tr>
        <tr>
          <td v-for="(item,index) in material.detailList">{{item.spotCheckYieid}}</td>
        </tr>
        <tr>
          <td colspan="6">{{material.qualifiedNo}}</td>
        </tr>
        <tr>
          <td colspan="6">{{material.yieid}}</td>
        </tr>
        <tr>
          <td colspan="6">{{material.conclusion}}</td>
        </tr>
        </tbody>
      </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th width="80">本批次水份</th>
        <th width="80">本批次结论</th>
        <th width="80">化验员</th>
        <th width="80">检验员</th>
        <th width="80">审核人</th>
        <th width="110">审核日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{material.lotMoisture}}
          </td>
          <td>
            {{unitName}}
          </td>
          <td>
            {{material.quantity}}
          </td>
          <td>
            {{material.weight}}
          </td>
          <td>
            {{material.weightTotal}}
          </td>
          <td>
            {{material.deliveryDate}}
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <br/>
    <div>4.V法涂料检验1006</div>
    <div v-if="material.ledgerId==1006" class="editable-table">
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th>供应商</th>
        <th>物料名称</th>
        <th width="60">数量</th>
        <th width="60">批次号</th>
        <th width="80">检验日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{material.batchId}}
          </td>
          <td>
            {{material.ledgerId}}
          </td>
          <td class="col-cz">
            {{material.texture}}
          </td>
          <td class="col-yd">
            {{material.batchId}}
          </td>
          <td>
            {{deliveryTypeName}}
          </td>
          </tr>
        </tbody>
      </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th rowspan="3">检验项目标准（醇基）</th>
        <th rowspan="2">比重（g/cm ）</th>
        <th rowspan="2">波美度</th>
        <th>悬浮性%</th>
        <th rowspan="3">检验结论</th>
        <th rowspan="3">备注</th>
        <tr><th>4小时沉淀后液层高度</th></tr>
        <th>1.46 —1.6</th>
        <th>≥ 95%</th>
        <th>≥ 95%</th>
        <tr>
          <th>抽样</th>
          <th colspan="3">检测值</th>
          <td></td>
          <td></td>
        </tr>
        <tr v-for="(item,index) in material.detailList">
          <th>{{index+1}}</th>
          <td>{{item.proportion}}</td>
          <td>{{item.baumeDegree}}</td>
          <td>{{item.suspensionProperty}}</td>
          <td>{{item.conclusion}}</td>
          <td rowspan="6">{{material.remark}}</td>
        </tr>
        <tr></tr>
        <tr>
          <th>平均数</th>
          <td>{{material.proportionAvg}}</td>
          <td>{{material.baumeDegreeAvg}}</td>
          <td>{{material.suspensionPropertyAvg}}</td>
          <td>{{material.baumeDegree}}</td>
        </tr>
        </thead>
      </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th width="80">化验员</th>
        <th width="80">检验员</th>
        <th width="80">审核人</th>
        <th width="110">审核日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{material.quantity}}
          </td>
          <td>
            {{material.weight}}
          </td>
          <td>
            {{material.weightTotal}}
          </td>
          <td>
            {{material.deliveryDate}}
          </td>
         </tr>
        </tbody>
      </table>
    </div>
    <br/>
    <div>5.树脂、铬矿砂检验1008</div>
    <div v-if="material.ledgerId==1008" class="editable-table">
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th>供应商</th>
        <th>物料名称</th>
        <th width="60">数量</th>
        <th width="60">批次号</th>
        <th width="80">规格</th>
        <th width="380">检验日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{material.batchId}}
          </td>
          <td>
            {{material.ledgerId}}
          </td>
          <td class="col-cz">
            {{material.texture}}
          </td>
          <td class="col-yd">
            {{material.batchId}}
          </td>
          <td>
            <!--{{material.deliveryType}}-->
            {{deliveryTypeName}}
          </td>
          <td>
            {{unitName}}
          </td>
          </tr>
        </tbody>
      </table>
        <br/>
        <table cellspacing="0" cellpadding="0">
          <thead>
          <th colspan="11">检验目的：粒度、水分、灼烧减量、酸耗值、PH、发气量</th>
          <tr><th colspan="11">取样（g）  100</th></tr>
          <th>筛孔尺寸mm</th>
          <th v-for="(item,index) in material.itemOneObj">{{item.aperture_size}}</th>
          <th>底盘</th>
          <tr>
            <th>分析目数</th>
            <th v-for="(item,index) in material.itemOneObj">{{item.analysis_mesh}}</th>
            <th></th>
          </tr>
          </thead>
          <tbody>
            <th>标准</th>
            <td v-for="(item,index) in material.itemOneObj">{{item.detail.standard_one}}</td>
            <tr>
              <th>含量%</th>
              <td v-for="(item,index) in material.itemOneObj">{{item.detail.content}}</td>
            </tr>
          </tbody>
          <br/>
          <thead>
          <tr>
            <th></th>
            <th>主要粒度质量和</th>
            <th>含水量%</th>
            <th>PH值</th>
            <th>发气量ml</th>
            <th>细分含量%</th>
            <th>酸耗值ml</th>
            <th>灼碱量%</th>
            <th>平均细度</th>
          </tr>
          </thead>
          <tbody>
          <th>标准</th>
          <td v-for="(item,index) in material.itemTwoObj">{{item.grain_quality.standard_two}}</td>
          <td v-for="(item,index) in material.itemTwoObj">{{item.water_content.standard_two}}</td>
          <td v-for="(item,index) in material.itemTwoObj">{{item.ph_value.standard_two}}</td>
          <td v-for="(item,index) in material.itemTwoObj">{{item.gas_volume.standard_two}}</td>
          <td v-for="(item,index) in material.itemTwoObj">{{item.subdivided_content.standard_two}}</td>
          <td v-for="(item,index) in material.itemTwoObj">{{item.acid_consumption_value.standard_two}}</td>
          <td v-for="(item,index) in material.itemTwoObj">{{item.burning_alkaline.standard_two}}</td>
          <td v-for="(item,index) in material.itemTwoObj">{{item.average_fineness.standard_two}}</td>
          <tr>
            <th>实测</th>
            <td v-for="(item,index) in material.itemTwoObj">{{item.grain_quality.actual}}</td>
            <td v-for="(item,index) in material.itemTwoObj">{{item.water_content.actual}}</td>
            <td v-for="(item,index) in material.itemTwoObj">{{item.ph_value.actual}}</td>
            <td v-for="(item,index) in material.itemTwoObj">{{item.gas_volume.actual}}</td>
            <td v-for="(item,index) in material.itemTwoObj">{{item.subdivided_content.actual}}</td>
            <td v-for="(item,index) in material.itemTwoObj">{{item.acid_consumption_value.actual}}</td>
            <td v-for="(item,index) in material.itemTwoObj">{{item.burning_alkaline.actual}}</td>
            <td v-for="(item,index) in material.itemTwoObj">{{item.average_fineness.actual}}</td>
          </tr>
          </tbody>
        </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th width="80">检验结论</th>
        <th width="80">化验员</th>
        <th width="80">检验员</th>
        <th width="80">审核人</th>
        <th width="110">审核日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{unitName}}
          </td>
          <td>
            {{material.quantity}}
          </td>
          <td>
            {{material.weight}}
          </td>
          <td>
            {{material.weightTotal}}
          </td>
          <td>
            {{material.deliveryDate}}
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <br/>
    <div>6.石灰检验1009</div>
    <div v-if="material.ledgerId==1009" class="editable-table">
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th>供应商</th>
        <th>物料名称</th>
        <th width="60">数量</th>
        <th width="60">批次号</th>
        <th width="80">检验日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{material.batchId}}
          </td>
          <td>
            {{material.ledgerId}}
          </td>
          <td class="col-cz">
            {{material.texture}}
          </td>
          <td class="col-yd">
            {{material.batchId}}
          </td>
          <td>
            <!--{{material.deliveryType}}-->
            {{deliveryTypeName}}
          </td>
        </tr>
        </tbody>
      </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th rowspan="3" align="center">检验项目：</th>
        <th></th>
        <th>活性度</th>
        <th>检验结论</th>
        <tr>
          <th>标准</th>
          <td>{{material.standard}}</td>
          <td rowspan="2">检验结论</td>
        </tr>
        <tr>
          <th>实测</th>
          <td>{{material.actual}}</td>
        </tr>
        </thead>
      </table>
      <br/>
        <table cellspacing="0" cellpadding="0">
        <thead>
        <th width="80">化验员</th>
        <th width="80">检验员</th>
        <th width="80">审核人</th>
        <th width="110">审核日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{material.quantity}}
          </td>
          <td>
            {{material.weight}}
          </td>
          <td>
            {{material.weightTotal}}
          </td>
          <td>
            {{material.deliveryDate}}
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <br/>
    <div>7.水玻璃检验1010   ====={{ledgerId}}</div>
    <div v-if="material.ledgerId==1010" class="editable-table">
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th>供应商</th>
        <th>物料名称</th>
        <th width="60">数量</th>
        <th width="60">批次号</th>
        <th width="80">检验日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{material.batchId}}
          </td>
          <td>
            {{material.ledgerId}}
          </td>
          <td class="col-cz">
            {{material.texture}}
          </td>
          <td class="col-yd">
            {{material.batchId}}
          </td>
          <td>
            <!--{{material.deliveryType}}-->
            {{deliveryTypeName}}
          </td>
        </tr>
        </tbody>
      </table>
      <br/>
        <table cellspacing="0" cellpadding="0">
          <thead>
          <th rowspan="3" align="center">检验项目：</th>
          <th>模数</th>
          <th>比重</th>
          <th>检验结论</th>
          </thead>
          <tbody>
          <tr >
            <th>标准</th>
            <td>{{material.modulusStand}}</td>
            <td >{{material.proportionStand}}</td>
            <td rowspan="2">{{material.conclusion}}</td>
          </tr>
          <tr>
            <th>实测</th>
            <td>{{material.modulusMeasure}}</td>
            <td>{{material.proportionMeasure}}</td>
          </tr>
          </tbody>
        </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th width="80">化验员</th>
        <th width="80">检验员</th>
        <th width="80">审核人</th>
        <th width="110">审核日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{material.quantity}}
          </td>
          <td>
            {{material.weight}}
          </td>
          <td>
            {{material.weightTotal}}
          </td>
          <td>
            {{material.deliveryDate}}
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <br/>
    <div>8.原砂检验1011</div>
    <div v-if="material.ledgerId==1011" class="editable-table">
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th>供应商</th>
        <th>物料名称</th>
        <th width="60">数量</th>
        <th width="60">批次号</th>
        <th width="80">检验日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{material.batchId}}
          </td>
          <td>
            {{material.ledgerId}}
          </td>
          <td class="col-cz">
            {{material.texture}}
          </td>
          <td class="col-yd">
            {{material.batchId}}
          </td>
          <td>
            <!--{{material.deliveryType}}-->
            {{deliveryTypeName}}
          </td>
        </tr>
        </tbody>
      </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th colspan="6">检验结果：</th>
        <tr>
          <th>含泥量</th>
          <td>{{material.siltContent}}</td>
          <th>含水量%</th>
          <td>{{material.waterContent}}</td>
          <th>胶质价</th>
          <td>{{material.colloidIndex}}</td>
        </tr>
        </thead>
      </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th rowspan="2">筛孔尺寸（mm）</th>
        <td v-for="(item,index) in material.itemOneObj">{{item.detail.aperture_size}}</td>
        <td v-for="(item,index) in material.itemOneObj">{{item.detail.aperture_size}}</td>
        <tr>
          <th>3.35</th>
          <th>1.70</th>
          <th>0.850</th>
          <th>0.600</th>
          <th>0.425</th>
          <th>0.300</th>
          <th>0.212</th>
          <th>0.150</th>
          <th>0.100</th>
          <th>0.075</th>
          <th>0.053</th>
          <th>底盘</th>
        </tr>
        <tr>
          <th>残留量</th>
            <td v-for="(item,index) in material.itemOneObj">{{item.detail.residual_quantity}}</td>
            <td v-for="(item,index) in material.itemOneObj">{{item.detail.residual_quantity}}</td>
        </tr>
        </thead>
      </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th>备注</th>
        <td width="300px">{{material.remark}}</td>
        <th>检验结论</th>
        <td>{{material.conclusion}}</td>
        </thead>
      </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
          <th>主要粒度组成（分组代号）</th>
          <th>平均粒度</th>
          <th>均匀率%</th>
          <th>细率</th>
        </thead>
        <tbody>
          <td>{{material.mainGrainComposition}}</td>
          <td>{{material.granularityAvg}}</td>
          <td>{{material.averageRate}}</td>
          <td>{{material.fineRate}}</td>
        </tbody>
      </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th width="80">化验员</th>
        <th width="80">检验员</th>
        <th width="80">审核人</th>
        <th width="110">审核日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{material.quantity}}
          </td>
          <td>
            {{material.weight}}
          </td>
          <td>
            {{material.weightTotal}}
          </td>
          <td>
            {{material.deliveryDate}}
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <br/>
    <div>9.电极检验1012</div>
    <div v-if="material.ledgerId==1012" class="editable-table">
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th>供应商</th>
        <th>物料名称</th>
        <th width="60">数量</th>
        <th width="60">批次号</th>
        <th width="80">检验日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{material.batchId}}
          </td>
          <td>
            {{material.ledgerId}}
          </td>
          <td class="col-cz">
            {{material.texture}}
          </td>
          <td class="col-yd">
            {{material.batchId}}
          </td>
          <td>
            <!--{{material.deliveryType}}-->
            {{deliveryTypeName}}
          </td>
        </tr>
        </tbody>
      </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
          <th rowspan="3">检验项目：</th>
          <th></th>
          <th>标准</th>
          <th>1</th>
          <th>2</th>
          <th>3</th>
          <th>4</th>
          <th>5</th>
          <th>6</th>
          <th>7</th>
          <th>8</th>
          <th>9</th>
          <th>10</th>
          <tr>
            <th>长度尺寸</th>
            <td v-for="(item,index) in material.itemOneObj">{{item.standard.length_size}}</td>
          </tr>
          <tr>
            <th>电阻</th>
            <td v-for="(item,index) in material.itemOneObj">{{item.standard.resistance}}</td>
          </tr>
         </thead>
        <tbody>
        </tbody>
      </table>
      <br/>
      <table cellspacing="0" cellpadding="0">
        <thead>
        <th width="80">检验结论</th>
        <th width="80">化验员</th>
        <th width="80">检验员</th>
        <th width="80">审核人</th>
        <th width="110">审核日期</th>
        </thead>
        <tbody>
        <tr>
          <td>
            {{unitName}}
          </td>
          <td>
            {{material.quantity}}
          </td>
          <td>
            {{material.weight}}
          </td>
          <td>
            {{material.weightTotal}}
          </td>
          <td>
            {{material.deliveryDate}}
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
  import Tree from "../../demo/Tree";
  export default {
    components: {
        Tree

    },
    props:{
      title:{
            type:String,
            default:'物料1'
        },
        ledgerId:{
            type:String,
            default:false
        },
      material:{
        type:Object,
        default:function () {
          return {
            id:0,
            inquireId:'',
            materId:'',
            drawing:'',
            materName:'',
            texture:'',
            hardness:'',
            deliveryType:'',
            unit:'',
            quantity:0,
            weight:0,
            weightTotal:0,
            deliveryDate:null,
            remark:'',
            fieldJson:'',

          };
        }
      },
      editable:{
        type:Boolean,
        default:false
      },
     },
    data() {
      return {
        fields:this.loadFields()
      }
    },
    mounted: function () {
        //console.log(this.material);
    },
    watch:{
      material(val,old){
        if(val != old){
          this.fields = this.loadFields();
        }
      }
    },
    computed: {
      deliveryTypeName: function () {
        return this.$args.getArgText("process_require",this.material.deliveryType)
      },
      unitName: function (){
        return this.$args.getArgText("unit",this.material.unit)
      }
    },
    methods: {
      load() {
      },
      loadFields(){
        var fields = {};
        if(this.material.fieldJson
          && this.material.fieldJson.length > 0
          && this.material.fieldJson[0]=='{'){
          try{
            fields = eval("("+ this.material.fieldJson +")");
          }catch(ex){
            console.log('转换 json 数据失败！');
          }
        }
        return fields;
      }
    }
  }

</script>

<style type="text/css">
  .inquire-detail-card.editable {

  }
  .inquire-detail-card .title{
    height: 40px;
    line-height: 38px;
    text-align: center;
    min-width: 89px;
    border: 1px solid #dedede;
    display: inline-block;
    border-bottom: 0px;font-weight: bold;
  }
  .inquire-detail-card .editable-table{
    overflow-x: auto;
  }
  .editable-table table{
    border-collapse:collapse;
    border: 1px solid #dedede;
    width: 100%;
  }
  .editable-table table th{
    background: #efefef;
  }
  .inquire-detail-card .editable-table table td,
  .inquire-detail-card .editable-table table th{
    height: 40px;
    border: 1px solid #dedede;
    padding: 0 10px;text-align: center;
  }
  .editable-table .ivu-input,
  .editable-table .ivu-input-number,
  .editable-table .ivu-select,
  .editable-table .ivu-select-selection{
    border: 0px;
  }
  .editable-table .ivu-input-number-input{
    padding-right: 24px;
    text-align: right;
  }
  .editable-table .col-xh{
    text-align: center; cursor: default;
  }
  .editable-table .col-xh.cur{
    background: #e8f8fd;
    color:#20bfee;
    border-right: 2px solid #20bfee;
    cursor: default;font-weight: bold;;
  }
  /*行扩展操作*/
  .editable-table .col-cz input{
    text-align: center;;
  }
  .editable-table .col-yd input{
    text-align: center;;
  }
  .inquire-detail-card .editable-card{
    margin-top: 10px;
    margin-bottom: 10px;
  }
  
</style>
