<template>
  <Editable @add="add" @remove="remove" :editable="editable" class="contract-editable"> 
    <table cellspacing="0" cellpadding="0" v-if="!editable">
        <thead>
          <th >序号</th>
          <th>图 号</th>
          <th>物料名称</th>
          <th width="60">材质</th>
          <th width="60">硬度</th>
          <th width="80">交货类型</th>
          <th width="80">单位</th>
          <th width="80">数量</th>
          <th width="80">重量</th>
          <th width="80">总重</th>
          <th width="110">交货日期</th> 
        </thead>
        <tbody>
          <template v-for="(item,index) in list" >
          <tr :key="'mater_'+index">
            <td>
              {{index+1}}
            </td>
            <td>
              {{item.drawing}}
            </td>
            <td>
              {{item.materName}}
            </td>
            <td>
              {{item.texture}}
            </td>
            <td>
              {{item.hardness}}
            </td>
            <td>
              {{computedDeliveryTypeName(item.deliveryType)}}
            </td>
            <td>
              {{computedUnitName(item.unit)}}
            </td>
            <td>
              {{item.quantity}}
            </td>
            <td>
              {{item.weight}}
            </td>
            <td>
              {{item.weightTotal}}
            </td>
            <td>
              {{item.deliveryDate}}
            </td> 
          </tr>
          <tr>
            <th>技术要求</th>
            <td colspan="11">
              {{item.remark}}
            </td>
          </tr> 
          <tr>
            <th>附件</th>
            <td colspan="11">
              <UploadBox v-model="item.files" :readonly="true"></UploadBox>
            </td>
          </tr> 
          <tr>
            <th>费用</th>
            <td colspan="11">
              <table class="fee-table">
                <tr>
                  <td>毛坯单价</td>
                  <td >毛坯费</td>
                  <td >加工费</td>
                  <td >包装费</td>
                  <td >运费</td>
                  <td >港口费</td>
                  <td >模型费</td>
                  <td >金额</td>
                </tr>
                <tr class="fee">
                  <td>{{item.workblank}}</td>
                  <td >{{item.workblankFee}}</td>
                  <td >{{item.processFee}}</td>
                  <td >{{item.packingFee}}</td>
                  <td >{{item.freightFee}}</td>
                  <td >{{item.portFee}}</td>
                  <td >{{item.modelFee}}</td>
                  <td >{{item.amount}}</td>
                </tr>
              </table>
            </td>
          </tr>
         </template>
        </tbody>
      </table>

      <template v-for="(item,index) in list" v-else>
        <div class="contract-editable-row" :key="'mater_'+index" @click="curIndex = index">
        <table style="margin: 10px 0px;">
          <tr :class="{'col-xh':true,'cur':index==curIndex}"> 
            <td width="40">序号</td>
            <td width="40">{{index+1}}</td>
          </tr>
        </table>
        <table cellspacing="0" cellpadding="0" style="width:100%" >
          <tr>
            <th width="70">图 号</th>
            <td width="105">
              <Input v-model="item.drawing" @on-blur="drawingOnBlur(item)" />
            </td>
            <th width="40">物料名称</th>
            <td>
              <Input type="text" v-model="item.materName" />
            </td>
            <th width="40">材质</th>
            <td width="80">
              <Input type="text" v-model="item.texture" />
            </td>
            <th width="40">硬度</th>
            <td width="80">
              <Input type="text" v-model="item.hardness" />
            </td>
            <th width="40">单位</th>
            <td width="80">
              <Select v-model="item.unit" placeholder="" style="text-align: center">
                  <Option v-for="item in unitArgs" :value="item.argCode" :key="item.argCode">{{ item.argText }}</Option>
                </Select>
            </td>
          </tr>
        </table> 
        <table cellspacing="0" cellpadding="0" style="width:100%">
          <tr>
            <td width="70">交货类型</td>
            <td width="105">
              <Select v-model="item.deliveryType" placeholder="" style="text-align: center">
                  <Option v-for="item in processRequireArgs" :value="item.argCode" :key="item.argCode">{{ item.argText }}</Option>
                </Select>
            </td>
            <td width="40">数量</td>
            <td width="100">
              <InputNumber :max="999999" :min="0" v-model="item.quantity" @on-change="computedWeightTotal(item)"></InputNumber>
            </td>
            <td width="40">重量</td>
            <td>
              <InputNumber :max="999999" :min="0" v-model="item.weight" @on-change="computedWeightTotal(item)"></InputNumber>
            </td>
            <td width="40">总重</td>
            <td width="80">
              <InputNumber :max="999999" :min="0" v-model="item.weightTotal"></InputNumber>
            </td>
            <td width="40">交货日期</td>
            <td>
              <DatePicker type="date" placeholder="" v-model="item.deliveryDate" @on-change="datePickerChange(item,arguments)"></DatePicker>
            </td>
          </tr>
        </table>
        <table cellspacing="0" cellpadding="0" style="width:100%">
           <tr>
            <th width="70">技术要求</th>
            <td >
              <Input type="text" v-model="item.remark" placeholder="" />
            </td>
          </tr>
        </table>
        <table cellspacing="0" cellpadding="0" style="width:100%">
           <tr>
            <th width="70">附件</th>
            <td style="padding: 10px">
               <UploadBox v-model="item.files"></UploadBox>
            </td>
          </tr>
        </table>
        <table cellspacing="0" cellpadding="0" style="width:100%">
           <tr>
            <th width="70">费用</th>
            <td style="padding: 0px;">
              <table class="fee-table">
                <tr>
                  <td>毛坯单价</td>
                  <td >毛坯费</td>
                  <td >加工费</td>
                  <td >包装费</td>
                  <td >运费</td>
                  <td >港口费</td>
                  <td >模型费</td>
                  <td >金额</td>
                </tr>
                <tr class="fee">
                  <td><InputNumber :max="999999" :min="0" v-model="item.workblank" @on-change="computedAmount(item)"></InputNumber></td>
                  <td ><InputNumber :max="999999" :min="0" v-model="item.workblankFee" @on-change="computedAmount(item)"></InputNumber></td>
                  <td ><InputNumber :max="999999" :min="0" v-model="item.processFee" @on-change="computedAmount(item)"></InputNumber></td>
                  <td ><InputNumber :max="999999" :min="0" v-model="item.packingFee" @on-change="computedAmount(item)"></InputNumber></td>
                  <td ><InputNumber :max="999999" :min="0" v-model="item.freightFee" @on-change="computedAmount(item)"></InputNumber></td>
                  <td ><InputNumber :max="999999" :min="0" v-model="item.portFee" @on-change="computedAmount(item)"></InputNumber></td>
                  <td ><InputNumber :max="999999" :min="0" v-model="item.modelFee" @on-change="computedAmount(item)"></InputNumber></td>
                  <td >{{item.amount}}</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        </div>
      </template>
      
  </Editable>
</template>
<script>
  import floatObj from '@/assets/js/floatObj';
  import Editable from '@/components/editable-table';
  import UploadBox from '@/components/upload/Index';

  export default {
    components: {
      Editable,
      UploadBox
    },
    props:{
      unitArgs:{
        type:Array,
        default:function() {
          return [];
        }
      },
      processRequireArgs:{
        type:Array,
        default:function() {
          return [];
        }
      },
      list:{
        type:Array,
        default:function () {
          var arr = [];
        }
      },
      editable:{
        type:Boolean,
        default:false
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
      computedDeliveryTypeName () {
          return function (type){
            return this.$args.getArgText("process_require",type)
          }
      },
      computedUnitName () {
        return function (unit){
          return this.$args.getArgText("unit",unit)
        }
      },
    },
    methods: {
      load() {

      },
      listNewRow(){
        var def = {
          "id":0,
          "contractId":"",
          "materId":"",
          "drawing":"",
          "materName":"",
          "texture":"",
          "hardness":"",
          "deliveryType":"",
          "unit":"kg",
          "quantity":null,
          "weight":null,
          "weightTotal":null,
          "workblank":null,
          "workblankFee":null,
          "processFee":null,
          "packingFee":null,
          "freightFee":null,
          "portFee":null,
          "modelFee":null,
          "amount":null,
          "deliveryDate":null,
          "files":"",
          "remark":"",
        };
        return def;
      },
      add(){
        this.list.push(this.listNewRow());
      },
      remove(){
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
      datePickerChange(item,args){
        item.deliveryDate = args[0];
      },
      drawingOnBlur(item){
        this.$emit('on-drawing-input',item);
      },
      computedWeightTotal(item){
        item.weightTotal = item.quantity*item.weight;
      },
      computedAmount(item){
        var val = 0;
        val = floatObj.add(val,item.workblank);
        val = floatObj.add(val,item.workblankFee);
        val = floatObj.add(val,item.processFee);
        val = floatObj.add(val,item.packingFee);
        val = floatObj.add(val,item.freightFee);
        val = floatObj.add(val,item.modelFee);
        val = floatObj.add(val,item.portFee);
        item.amount = val;
        var total = 0;
        this.list.map((mater)=>{
          total = floatObj.add(total,mater.amount);
        });
        this.$emit('on-amount-change',total);
      }
    }
  }

</script>

<style type="text/css">
  .contract-editable-row table{
    margin-top: -1px; 
  }
  .contract-editable .fee-table{
    width: 100%; 
    margin: -1px;
  }

  .contract-editable .attachfile-body{
    background-color:white;
    border:0px;
  }
</style>
