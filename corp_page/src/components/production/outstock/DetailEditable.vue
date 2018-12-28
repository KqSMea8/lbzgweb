<template> 
  <Editable @add="add" @remove="remove" :editable="editable">
    <table cellspacing="0" cellpadding="0" >
        <thead>
          <th class="col-xh">序号</th> 
          <th >物料编码</th>
          <th >物料名称</th>
          <th >规格</th>
          <th >型号</th>
           <th class="col-quantity"><span>请领数量</span></th>
          <th >材质</th>
          <th >单位</th>
          <th class="col-price"><span>单价(元)</span></th> 
          <th class="col-amount"><span>金额(元)</span></th>
          <th class="col-remark"><span>备注</span></th>
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
            {{item.spec}} 
            </td>
            <td >
              {{item.model}} 
            </td>
            <td class="col-quantity"> 
              <InputNumber  :max="999999" :min="0" v-model="item.quantity" @on-change="computedAmount(item)"></InputNumber> 
            </td>
            <td > 
              <Input type="text" v-model="item.texture"  />
            </td>
            <td>
              {{$args.getArgText('unit',item.unit)}}  
            </td>
            <td class="col-price">
              <InputNumber  :max="999999" :min="0" v-model="item.price" @on-change="computedAmount(item)"></InputNumber>
            </td>
            <td class="col-amount"> 
              {{item.amount}}
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>
          </tr>
        </tbody>
      </table>
    <SelectMaterial ref="selmaterial" :transfer="true" :storageId="storageId"></SelectMaterial>
  </Editable>  
</template>
<script>
  import Editable from '@/components/editable-table';
  import floatObj from '@/assets/js/floatObj';
  import SelectMaterial from './SelectMaterial';
  export default {
    components: {
      SelectMaterial,
      Editable
    },
    props:{
      list:{
        type:Array,
        default:function () {
          var arr = [];
        }
      },
      storageId:{
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
          id:0,
          materId:'',
          subType:'',
          materName:'',
          spec:'',
          model:'',
          providerId:'',
          providerName:'',
          placeId:'',
          placeName:'',
          arrivalDate:'',
          origin:'',
          unit:'',
          price:0,
          quantity:0,
          amount:0,
          originBatch:'',
          batch:'',
          madeDate:'',
          validityDate:'',
          qualityTime:'',
          remark :''
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
      computedAmount(item){ 
        item.amount = floatObj.multiply(item.price,item.quantity);  
        var total = 0;
        this.list.map((mater)=>{
          total = floatObj.add(total,mater.amount);
        });
        this.$emit('on-amount-change',total);
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
            this.computedAmount(row);
          }
        });
      },
    }
  }

</script>

<style type="text/css"> 
   
</style>
