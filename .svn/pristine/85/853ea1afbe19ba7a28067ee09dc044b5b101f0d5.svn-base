<template>
    <Editable @add="add" @remove="remove" class="inquire-editable" :editable="true"> 
    <div class="editable-table">
        <template v-for="(item,index) in list">
          <div class="contract-editable-row" :key="'mater_'+index" @click="curIndex = index">
            <!-- 一个 -->
             <table style="margin: 10px 0px;">
          <tr :class="{'col-xh':true,'cur':index==curIndex}"> 
            <td width="40">序号</td>
            <td width="40">{{index+1}}</td>
          </tr>
             </table>
             <!--二个 -->
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
             <!--三个 -->
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
             <!--四个 -->
        <table cellspacing="0" cellpadding="0" style="width:100%">
           <tr>
            <th width="70">备注</th>
            <td >
              <Input type="text" v-model="item.remark" placeholder="" />
            </td>
          </tr>
        </table>
             <!--五个 -->  
        <table cellspacing="0" cellpadding="0" style="width:100%">
           <tr>
            <th width="70">附件</th>
            <td style="padding: 10px">
               <UploadBox v-model="item.files"></UploadBox>
            </td>
          </tr>
        </table>
          </div>
          </template> 
    </div>
  </Editable>
</template>
<script>
  import floatObj from '@/assets/js/floatObj';
  import UploadBox from '@/components/upload/Index';
  import Editable from '@/components/editable-table';

  export default {
    components: {
      UploadBox,
      Editable
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
      }
    },
    data() {
      return {
        curIndex:0,
      }
    },
    mounted: function () {

    },
    computed: {},
    methods: {
      load() {

      },
      computedAmount(item){
        item.weightTotal = floatObj.multiply(item.quantity,item.weight);
      },
      listNewRow(){
        var def = {
          id:0,
          inquireId:'',
          materId:'',
          drawing:'',
          materName:'',
          texture:'',
          hardness:'',
          deliveryType:'',
          unit:'kg',
          files:'',
          quantity:0,
          weight:0,
          weightTotal:0,
          deliveryDate:null,
          remark:''
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
      computedWeightTotal(item){
        item.weightTotal = item.quantity*item.weight;
      },
      datePickerChange(item,args){
        item.deliveryDate = args[0];
      },
      drawingOnBlur(item){
        this.$emit('on-drawing-input',item);
      }
    }
  }

</script>

<style type="text/css">
 .contract-editable-row table{
    margin-top: -1px; 
  }
  
  .inquire-editable .attachfile-body{
    background-color:white;
    border:0px;
  }
</style>
