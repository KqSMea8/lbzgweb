<template>
  <div class="contract-detail editable">
    <div class="editable-table">
      <table cellspacing="0" cellpadding="0">
        <thead>
          <th width="40">序号</th>
          <th width="80">物料代码</th>
          <th width="110">物料名称</th>
          <th width="80">规格</th>
          <th width="80">型号</th>
          <th width="80">产地</th>
          <th width="80">物料类别</th>
          <th width="100">适用工序或产品</th>
          <th width="60" >单位</th>
          <th width="60" >数量</th>
          <th width="60">单价（元）</th>
          <th width="60">金额（元）</th>
          <th width="110">备注</th>
        </thead>
        <tbody>
          <template v-for="(item,index) in list" >
          <tr :key="'mater_'+index">
            <td>
              {{index+1}} <!--序号 -->
            </td>
            <td>
              {{item.materId}}<!--物料代码 {{item.npmId}} -->
            </td>
            <td>
              {{item.materName}}<!--物料名称 -->
            </td>
            <td>
              {{item.spec}}<!-- 规格-->
            </td>
            <td>
              {{item.model}}<!-- 型号-->
            </td>
            <td>
              {{item.origin}}<!--产地 -->
            </td>

            <td>
              {{computedMaterType(item.materType)}}<!--物料类别 -->
            </td>
            <td>
              {{item.use}}<!--适用工序或产品 -->
            </td>
            <td>
              {{computedUnitName(item.unit)}}<!-- 单位-->
            </td>
            <td>
              {{item.quantity}}<!--数量 -->
            </td>

            <td>
              {{item.price}}<!-- 单位（元）-->
            </td>
            <td>
              {{item.amount}}<!--金额（元） -->
            </td>
            <td>
              {{item.remark}}<!-- 备注-->
            </td>
          </tr>

          <tr>
            <td></td>
            <td class="label">附件</td>
            <td colspan="11">
              <UploadBox v-model="item.files" :readonly="!editable"></UploadBox>   <!-- formItem.files 调用上传附件的控件 -->
            </td>
          </tr>

         </template>
        </tbody>
      </table>

    </div>
    <SelectMaterial ref="selmaterial" :transfer="transfer"></SelectMaterial>
  </div>
</template>
<script>
  import floatObj from '@/assets/js/floatObj';
  import SelectMaterial from '@/components/material/selmaterial/SelectMaterial';
  import UploadBox from '@/components/upload/Index';//上传附件


  export default {
    components: {
      SelectMaterial,
      UploadBox
    },
    props:{
      unitArgs:{  //单位参数
        type:Array,
        default:function() {
          return [];
        }
      },
      list:{
        type:Array,
        default:function () {
          var arr = [];
          return arr;
        }
      },

      editable:{
        type:Boolean,
        default:false
      },
      value:{
       type:String,
       default:''
     },
      disabled:{
      type:Boolean,
      default:false
    },
    transfer:{
      type:Boolean,
      default:true
    }
    },
    data() {
      return {
        curIndex:0,
        "files":null, //输入附件的选项
      }
    },
    mounted: function () {

    },
    computed: {
      computedUnitName () {
        return function (unit){
          return this.$args.getArgText("unit",unit)
        }
      },
      computedMaterType () {
        return function (materType){
          if(materType =="1"){
            return "设备配件";
          }else if(materType =="2"){
            return "常规物料";
          }else if(materType =="3"){
            return "办公用品";
          }else{
            return "";
          }
        }
      },
    },
    methods: {
    }
  }

</script>

<style type="text/css">
  .contract-detail.editable {

  }
  .contract-detail .editable-tools{

  }
  .contract-detail .editable-table{
    margin-top: 4px;
    overflow-x: auto;
  }
  .contract-detail .editable-table table{
    border-collapse:collapse;
    border: 1px solid #dedede;
    width: 100%;
  }
  .contract-detail .editable-table table th{
    background: #efefef;
  }
  .contract-detail .editable-table table td,
  .contract-detail .editable-table table th{
    height: 40px;
    border: 1px solid #dedede;
    text-align: center;
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
  .contract-detail table.fee-table{
    border: 0px;
  }
  .contract-detail table.fee-table tr td{
    border: 0px;padding:0px 4px;
  }
  .contract-detail table.fee-table tr th{
    border: 0px;
    width: 70px;
    background-color: white;
    padding:0px 4px;font-weight: normal;
    color:#999;
  }
  .contract-detail table.fee-table .fee td{
    color: red;
  }
</style>
