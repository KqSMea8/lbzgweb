<template> 
  <Editable @add="add" @remove="remove" :editable="editable">
    <table cellspacing="0" cellpadding="0" v-if="!editable"> 
      <thead>
        <th class="col-xh">序号</th> 
        <th >物料编码</th>
        <th >物料名称</th>
        <th >图号</th>
        <th >铸件号</th>
        <th >合格证编号</th> 
        <th >材质</th> 
        <th >交货类型</th>
        <th><span>产地</span></th>
        <th ><span>单位</span></th>
        <th ><span>出库数量</span></th>
        <th >单重（公斤）</th>
        <th >总重（公斤）</th>
        <th><span>备注</span></th>
      </thead>
      <tbody>
        <tr v-for="(item,index) in list"  :key="'mater_'+index" @click="curIndex = index">
          <td :class="{'col-xh':true,'cur':index==curIndex}">
            {{index+1}}
          </td>
          <td>
            {{item.materId}}
          </td>
          <td >
            {{item.materName}} 
          </td>
          <td >
            {{item.drawing}} 
          </td>
          <td >
            {{item.casting}} 
          </td>
          <td >
            {{item.certification}} 
          </td> 
          <td>
            {{item.texture}}
          </td>
          <td> 
            {{item.delivery_type}}
          </td>
          <td> 
            {{item.origin}}
          </td>
          <td >
            {{$args.getArgText('unit',item.unit)}}  
          </td>
          <td> 
            {{item.quantity}}
          </td>
          <td> 
            {{item.weight}}
          </td>
          <td> 
            {{item.totalWeight}}
          </td>
          <td>
            {{item.remark}} 
          </td>
        </tr>
      </tbody>
    </table>
    <table cellspacing="0" cellpadding="0" v-else>
        <thead>
          <th class="col-xh">序号</th> 
          <th >物料编码</th>
          <th >物料名称</th>
          <th >图号</th>
          <th >铸件号</th>
          <th >合格证编号</th> 
          <th >材质</th> 
          <th >交货类型</th>
          <th><span>产地</span></th>
          <th ><span>单位</span></th>
          <th ><span>出库数量</span></th>
          <th >单重（公斤）</th>
          <th >总重（公斤）</th>
          <th><span>备注</span></th>
        </thead> 
        <tbody>
          <tr v-for="(item,index) in list"  :key="'mater_'+index" @click="curIndex = index">
            <td :class="{'col-xh':true,'cur':index==curIndex}">
              {{index+1}}
            </td>
            <td class="col-select" @click="editable && !isImport &&  selMater(item)">
              <span>{{item.materId}}</span>
            </td>  
            <td >
              {{item.materName}} 
            </td>
            <td >
              {{item.drawing}} 
            </td>
            <td >
              {{item.casting}} 
            </td>
            <td>
              <Input  type="text" v-model="item.certification" style="width:150px"/>
            </td>
            <td >
              <Input  type="text" v-model="item.texture" style="width:100px"/>
            </td>
            <td>
              <Input  type="text" v-model="item.deliveryType" style="width:100px"/>
            </td>
            <td>
              <Input  type="text" v-model="item.origin" style="width:100px"/>
            </td>
            <td >
              {{$args.getArgText('unit',item.unit)}}  
            </td>  
            <td class="col-quantity"> 
              <InputNumber  :max="999999" :min="0" v-model="item.quantity" @on-change="computedWeight(item)"></InputNumber> 
            </td>
            <td class="col-weight"> 
              <InputNumber  :max="999999" :min="0" v-model="item.weight" @on-change="computedWeight(item)"></InputNumber> 
            </td>
            <td class="col-totalWeight"> 
              <InputNumber  :max="999999" :min="0" v-model="item.totalWeight" @on-change="computedWeight(item)"></InputNumber> 
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" style="width: 200px;"/>
            </td>
          </tr>
        </tbody>
      </table>
    <SelectMaterial ref="selmaterial" :transfer="true" :customerId="customerId"></SelectMaterial>
  </Editable>  
</template>
<script>
  import Editable from '@/components/editable-table';
  import floatObj from '@/assets/js/floatObj';
 
  import SelectMaterial from '../selmaterial/SelectMaterial';
  export default {
    components: {
      SelectMaterial,
      Editable,
    },
    props:{
      list:{
        type:Array,
        default:function () {
          var arr = [];
        }
      }, 
      customerId:{
        type:String,
        default:''
      },
      editable:{
        type:Boolean,
        default:false
      },
      isImport:{  // 是否为导入的数据，导入的数据不能添加行，部分字段不能编辑
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
    computed: {},
    methods: { 
      load() {
        
      }, 
      listNewRow(){
        var def = {
          stockBillId:'',
          materId:'',
          materName:'',
          customerId:'',
          customerName:'',
          drawing:'',
          casting:'',
          texture:'',
          certification:'',
          deliveryType:'',
          origin:'',
          unit:'',
          weight:0,
          totalWeight:0,
          quantity:0,
          remark :'',
        };
        return def;
      },
      add(){
        if(!this.isImport){
          this.list.push(this.listNewRow()); 
        } 
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
        item.needDate = args[0];
      },
      computedWeight(item){ 
        item.totalWeight = floatObj.multiply(item.quantity,item.weight);  
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
            row.customerName = data.customerName;
            row.customerId = data.customerId;
            row.drawing = data.drawing;
            row.casting = data.casting;
            row.quantity = data.quantity;
            row.unit = data.unit;
            this.computedWeight(row);
          }
        });
      },
    }
  }

</script>

<style type="text/css"> 
   
</style>
