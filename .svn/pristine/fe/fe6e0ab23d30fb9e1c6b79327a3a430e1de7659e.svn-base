<template>
  <div class="inquire-detail-card editable">

    <div class="editable-table">
      <table cellspacing="0" cellpadding="0">
        <tr>
          <td>合同号<br/> Contract No.</td>
          <td>{{ queryForm.contractId }}</td>
          <td>零件名称<br/>Description</td>
          <td>{{ queryForm.description }}</td>
          <td>报告编号<br/>Report No.</td>
          <td>{{ queryForm.reportNo }}</td>
        </tr>
        <tr>
          <td>产品工号<br/> Order No.</td><!--产品工号Order No.手工录入 -->
          <td><Input v-model="queryForm.orderNo" :readonly="isList!=2" placeholder="产品工号" ></Input></td>
          <td>零件编号<br/> Part No.</td><!--工序监控系统获取，依据合同号、图号、铸件名称进行判断获取-->
          <td>{{ queryForm.partNo }}</td>
          <td>状   态<br/> Status</td><!--下拉选择两项：调质、正火+回火-->
          <td><Select v-model="queryForm.status" :readonly="isList!=2" class="width-1" placeholder="状   态">
            <Option value=""></Option>
            <Option value="1">调制</Option>
            <Option value="2">正火加回火</Option>
          </Select></td>

        </tr>
        <tr>
          <td>报告日期 <br/>Report Date</td><!--系统当前日期-->
          <td>{{ queryForm.reportDate }}</td>
          <td>零件图号<br/> Drawing No.</td><!--导入的合同带入-->
          <td>{{ queryForm.drawingNo }}</td>
          <td>委托单位 <br/>Commission Unit</td><!--导入的合同带入-->
          <td>{{ queryForm.commissionUnit }}</td>
        </tr>
        <tr>
          <td>数量<br/> Quantity</td><!--导入的合同带入-->
          <td>{{ queryForm.quantity }}</td>
          <td>材     质<br/> Material</td><!--导入的合同带入-->
          <td>{{ queryForm.material }}</td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td colspan="6" style="text-align: left; ">
            零件示意图：<br/><!--sketch_part-->
            Sketch of Parts:
            <br/>
            <textarea v-model=" queryForm.sketchPart" :readonly="isList!=2" rows="5" style="width: 100%;"></textarea>
          </td>
        </tr>
        <tr>
          <td colspan="6" style="text-align: left; ">
            磁粉探伤结果：<br/>
            Result of Magnetic Test:
            <Select v-model="queryForm.magneticTestResult" :disabled="isList!=2" style="width:100%;text-align: center" placeholder="类别">
              <Option value=""></Option>
              <Option value="经磁粉检测，未发现表面有超标缺陷，符合标准要求，合格。">经磁粉检测，未发现表面有超标缺陷，符合标准要求，合格。</Option>
              <Option value="不合格">不合格</Option>
            </Select>
          </td>
        </tr>
        <tr>
          <td>仪器型号 <br/> Type of Equipment</td><!--数据库没有字段-->
          <td colspan="2"><Input v-model="queryForm.typeOfEquipment" :readonly="isList!=2" placeholder="仪器型号" ></Input></td>
          <td>探伤区域   <br/> Area Tested</td>
          <td colspan="2"><Input v-model="queryForm.areaTested" :readonly="isList!=2" placeholder="探伤区域" ></Input></td>
        </tr>
        <tr>
          <td>设备编号 <br/> Type of Machine</td>
          <td colspan="2"><Input v-model="queryForm.typeOfMachine" :readonly="isList!=2" placeholder="设备编号" ></Input></td>
          <td>表面状态   <br/>Surface Condition</td>
          <td colspan="2"><Input v-model="queryForm.surfaceCondition" :readonly="isList!=2" placeholder="表面状态" ></Input></td>
        </tr>
        <tr>
          <td>检测方式 <br/>Testing Method</td>
          <td colspan="2"><Input v-model="queryForm.testingMethod" :readonly="isList!=2" placeholder="检测方式" ></Input></td>
          <td>探伤方法    <br/>Method of Testing</td>
          <td colspan="2"><Input v-model="queryForm.methodOfTesting" :readonly="isList!=2" placeholder="探伤方法" ></Input></td>
        </tr>
        <tr>
          <td>媒  介  <br/>Medium</td>
          <td colspan="2"><Input v-model="queryForm.medium" :readonly="isList!=2" placeholder="媒  介" ></Input></td>
          <td>电极距离    <br/>Prod/Pole Distance</td>
          <td colspan="2"><Input v-model="queryForm.prodDistance" :readonly="isList!=2" placeholder="电极距离" ></Input></td>
        </tr>
        <tr>
          <td>电  流 <br/>Current in Amps</td>
          <td colspan="2"><Input v-model="queryForm.currentInAmpsr" :readonly="isList!=2" placeholder="电  流" ></Input></td>
          <td>执行标准    <br/>Required Standard</td>
          <td colspan="2"><Input v-model="queryForm.requiredStandard" :readonly="isList!=2" placeholder="执行标准" ></Input></td>
        </tr>
        <tr>
          <td>备     注 <br/> Remark</td>
          <td colspan="5"><Input v-model="queryForm.wfInstRemark" :readonly="isList!=2" placeholder="备     注" ></Input></td>
        </tr>
        <tr>
          <td colspan="2">检  验:   Remark:<br/><br/>{{queryForm.testPeople}}</td>
          <td colspan="2">审核:    checked by：<br/><br/>{{queryForm.checkedBy}}</td>
          <td colspan="2">批准：  Approved by：<br/><br/>{{queryForm.approvedBy}}</td>
        </tr>
      </table>
      <div style="height:10px"></div>
    </div>
  </div>
</template>
<script>
    import page from'@/assets/js/page';
    export default {
        components: {
            page,
        },
        props:{
            title:{
                type:String,
                default:'铸件合格证'
            },
            queryForm: {
                type: Object,
                default: function () {
                    return {
                        contractId:'',
                        description:'',
                        reportNo:'',
                        orderNo:'',
                        partNo:'',
                        status:'',
                        reportDate:'',
                        drawingNo:'',
                        commissionUnit:'',
                        quantity:'',
                        material:'',
                        sketchPart:'',
                        inquireId:'',
                        typeOf:'',
                        magneticTestResult:'',
                        areaTested:'',
                        requiredStandard:'',
                        surfaceCondition:'',
                        statu:'',
                        testingMethod:'',
                        methodOfTesting:'',
                        frequency:'',
                        medium:'',
                        prodDistance:'',
                        coupling:'',
                        wfInstRemark:'',
                        testPeople:'',
                        audit:'',
                    };
                }
            },
            isList:{
                type:Number,
                default:2
            },
            editable:{
                type:Boolean,
                default:false
            }
        },
        data() {
            return {
                isList:2,
            }
        },
        mounted: function () {
        },
        watch:{
        },
        computed: {
        },
        methods: {
            load() {
            },
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
  /*
  */
  .d1{border-top:80px threedlightshadow solid;border-left:100px windowframe solid;width:0;height:0;position:relative;color:#FFF}

  .ss{display:block;width:40px}

  .s1{position:absolute;top:-70px;left:-44px;color:black;}

  .s2{position:absolute;bottom:8px;right:55px}


</style>
