<template> 
  <Editable @add="add" @remove="remove" :editable="editable">
    <table cellspacing="0" cellpadding="0" v-if="!editable"> 
      <thead>
        <th class="col-xh">序号</th> 
        <th >物料编码</th>
        <th >物料名称</th>
        <th >图号</th>
        <th >铸件号</th>
        <th >材质</th>
        <th >单位</th>
        <th >数量</th>
        <th >单重（公斤）</th>
        <th >总重（公斤）</th>
        <th >单价（元）</th>
        <th >总价（元）</th>
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
          <td>
            {{item.texture}}
          </td>
          <td>
            {{item.unit}}
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
            {{item.price}}
          </td>
          <td> 
            {{item.amount}}
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
          <th >材质</th>
          <th >单位</th>
          <th >数量</th>
          <th >单重（公斤）</th>
          <th >总重（公斤）</th>
          <th >毛坯单价（元）</th>
          <th >总价（元）</th>
          <th><span>备注</span></th>
        </thead> 
        <tbody>
          <tr v-for="(item,index) in list"  :key="'mater_'+index" @click="curIndex = index">
            <td :class="{'col-xh':true,'cur':index==curIndex}">
              {{index+1}}
            </td>
            <td >
              {{item.materId}}
            </td>  
            <td >
              {{item.materName}} 
            </td>
            <td >
              {{item.drawing}} 
            </td>
            <td >
              <Input  type="text" v-model="item.casting" style="width:100px"/>
            </td>
            <td >
              {{item.texture}} 
            </td>
            <td>
              {{$args.getArgText('unit',item.unit)}}
            </td>
            <td class="col-quantity"> 
              <InputNumber  :max="999999" :min="0" v-model="item.quantity" @on-change="computedWeight(item)"></InputNumber> 
            </td>
            <td class="col-weight"> 
              {{item.weight}} 
            </td>
            <td class="col-weightTotal"> 
              {{item.weightTotal}} 
            </td>
            <td class="col-price"> 
              {{item.workblank}} 
            </td>
            <td class="col-amount"> 
              {{item.amount}} 
            </td>
            <td class="col-remark">
              <Input type="text" v-model="item.remark" style="width:150px"/>
            </td>
          </tr>
        </tbody>
      </table>
  </Editable>  
</template>
<script>
  import Editable from '@/components/editable-table';
  import floatObj from '@/assets/js/floatObj';
 
  export default {
    components: {
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
          id:null,
          shippingId:'', 
          materId:'',
          materName:'', 
          drawing:'',
          casting:'',
          texture:'',
          hardness:'',
          unit:'kg',
          weight:0,
          weightTotal:0,
          price:0,
          amount:0,
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
        item.weightTotal = floatObj.multiply(item.quantity,item.weight);  
        var total = 0;
        this.list.map((mater)=>{
          total = floatObj.add(total,mater.weightTotal);
        });
        this.$emit('on-weight-change',total);
      },
    }
  }

</script>

<style type="text/css"> 
   
</style>
