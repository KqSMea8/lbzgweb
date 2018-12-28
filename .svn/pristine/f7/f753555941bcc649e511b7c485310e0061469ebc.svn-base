<template>
    <Editable @add="add" @remove="remove" :editable="editable">
      <table cellspacing="0" cellpadding="0" v-if="!editable">
        <thead>
          <th width="40">序号</th>
          <th width="80">物料代码</th>
          <th width="110">物料名称</th>
          <th width="80">规格</th>
          <th width="80">型号</th>
          <th width="80">产地</th>
          <th width="80">物料类别</th>
          <th width="100">适用工序或产品</th>
          <th width="60">单位</th>
          <th width="60">数量</th>
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

          
          <!--新增加 附件 -->
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

      <table cellspacing="0" cellpadding="0" v-else>
        <thead>
          <th class="col-xh">序号</th>
          <th >物料编码</th>  
          <th >物料名称</th>
          <th >规格</th>
          <th >型号</th>
          <th class="col-origin" ><span>产地</span></th>
          <th >采购类别</th>
          <th >适用工序或产品</th>
          <th >单位</th>
          <th class="col-quantity">数量</th><!--class="col-quantity" -->
          <th class="col-price">单价（元）</th>
          <th class="col-amount">金额（元）</th>
          <th class="col-remark"><span>备注</span></th>
        </thead>
        <tbody>
          <template v-for="(item,index) in list" >
          <tr :key="'mater_'+index" @click="curIndex = index">
            <td :class="{'col-xh':true,'cur':index==curIndex}">
              {{index+1}}
            </td>

             <td class="col-select" @click="selMater(item)">
              <span>{{item.materId}}</span>
            </td>
            <td>
              {{item.materName}}
            </td>

            <td> <!-- 规格-->
              {{item.spec}}
            </td>
             <td> <!-- 型号-->
              {{item.model}}
            </td>

            <td ><!-- 产地-->
               <Input type="text" v-model="item.origin"  />
            </td>
            <td><!-- 采购类别-->
              <Select type="text"  v-model="item.materType">
                <Option :value="'1'">设备配件</Option>
                <Option :value="'2'">常规物料</Option>
                <Option :value="'3'">办公用品</Option>
              </Select>
            </td>
            <td><!-- 使用工序或产地-->
              <Input type="text" v-model="item.use" />
            </td>
            <td>
               {{$args.getArgText('unit',item.unit)}}  
            </td>
            <td class="col-quantity" >
              <InputNumber :max="999999" :min="0" v-model="item.quantity"  @on-change="computedWeightTotal(item)"></InputNumber>
            </td>
            <td class="col-price">
              <InputNumber :max="999999" :min="0" v-model="item.price"  @on-change="computedWeightTotal(item)"></InputNumber>
            </td>
            <td class="col-amount">
              <InputNumber :max="999999" :min="0" v-model="item.amount" ></InputNumber>
            </td>
            <td>
              <Input type="text" v-model="item.remark" />
            </td>
          </tr>
          

         </template>
        </tbody>
      </table>
    <SelectMaterial ref="selmaterial" :transfer="transfer"></SelectMaterial>
  </Editable> 
</template>
<script>
  import floatObj from '@/assets/js/floatObj';
  import SelectMaterial from '@/components/material/selmaterial/SelectMaterial';
  import Editable from '@/components/editable-table';
  import UploadBox from '@/components/upload/Index';//上传附件

  export default {
    components: {
      SelectMaterial,Editable,UploadBox
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

      // 对外方法
    popWin(item){
      var selmaterial = this.$refs.selmaterial;
      selmaterial.show({
        ok:(data)=>{
          item.materId = data.materId;
          item.materName = data.materName;
          item.spec = data.spec;
          item.model = data.model;
          item.materType = data.subType;
          item.unit = data.unit;
        }
      });
    },
      load() {

      },
      listNewRow(){
        var def = {
          materId:'',
          materName:'',
          spec:'',
          model:'',
          origin:'',
          materType:'',
          use:'',
          unit:'',
          quantity:null,//数字类型，默认情况
          price:null,
          amount:null,
          remark:'',
        };
        return def;
      },

      add(){
        this.list.push(this.listNewRow());//增加新行
      },

      remove(){// 删除一行
        if(this.list.length > this.curIndex){
          this.list.splice(this.curIndex,1);
        }
        if(this.list.length <= this.curIndex){
          this.curIndex = this.list.length - 1;
        }
        if(this.curIndex == -1){
          this.curIndex = 0;
        }
      },

      //三个参数的关系
      computedWeightTotal(item){
        item.amount = item.quantity*item.price;//单价乘以数量等于总的金额
      },
      //计算总金额
      computedAmount(item){
        var val = 0;
        val = floatObj.add(val,item.quantity);
        val = floatObj.add(val,item.price);

        item.amount = val;
        var total = 0;
        this.list.map((mater)=>{
          total = floatObj.add(total,mater.amount);
        });
        this.$emit('on-amount-change',total);//绑定赋值给amount字段
      },
      
      selMater(row){
        var selmaterial = this.$refs.selmaterial;
        selmaterial.show({
          ok:(data)=>{ 
            if(_.findIndex(this.list, { 'materId': data.materId })>=0){
              this.$Message.error('物料已存在!');
              return ;
            }
            row.materId = data.materId;
            row.materName = data.materName;
            row.spec = data.spec;
            row.model = data.model;
            row.unit = data.unit;
            row.subType = data.subType;
          }
        });
      },
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
   .editable-table-container th.col-origin > span{
    width: 80px;display: inline-block;
  }
</style>
