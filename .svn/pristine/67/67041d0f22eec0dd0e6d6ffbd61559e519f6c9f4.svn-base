<template> 
  <Editable @add="add" @remove="remove" :editable="editable">
    <table cellspacing="0" cellpadding="0" v-if="!editable">
      <thead>
        <th class="col-xh">序号</th> 
        <th class="col-w2"><span>名称</span></th>
        <th class="col-w2"><span>图号</span></th>
        <th class="col-w2"><span>材质</span></th>
        <th >数量（件）</th>
        <th >计量单位</th> 
        <th >重量（KG）</th> 
        <th >总重量（KG）</th> 
        <th >加工费</th>
        <th >总金额（元）</th>
        <th >交货期</th>
        <th class="col-w3"><span>备注</span></th>
      </thead> 
      <tbody>
        <tr v-for="(item,index) in list"  :key="'mater_'+index" @click="curIndex = index">
          <td :class="{'col-xh':true,'cur':index==curIndex}">
            {{index+1}}
          </td>
          <td>
            {{item.materName}}
          </td>
          <td >
            {{item.drawing}}
          </td>
          <td >
            {{item.texture}}
          </td>
          <td>
            {{item.quantity}}
          </td>
          <td >
            {{item.unit}}
          </td> 
          <td>
            {{item.weight}}
          </td>
          <td>
            {{item.weightTotal}}
          </td>
          <td>
            {{item.processFee}} 
          </td>
          <td>
            {{item.amount}} 
          </td>
          <td>
            {{item.deliveryDate}} 
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
        <th class="col-w2"><span>名称</span></th>
        <th class="col-w2"><span>图号</span></th>
        <th class="col-w2"><span>材质</span></th>
        <th class="col-quantity">数量（件）</th>
        <th >计量单位</th> 
        <th >重量（KG）</th> 
        <th >总重量（KG）</th> 
        <th class="col-price">加工费</th>
        <th class="col-amount">总金额（元）</th>
        <th >交货期</th>
        <th class="col-w3"><span>备注</span></th>
      </thead> 
      <tbody>
        <tr v-for="(item,index) in list"  :key="'mater_'+index" @click="curIndex = index">
          <td :class="{'col-xh':true,'cur':index==curIndex}">
            {{index+1}}
          </td>
          <td>
            <Input  type="text" v-model="item.materName" />
          </td>
          <td >
            <Input  type="text" v-model="item.drawing" />
          </td>
          <td >
            <Input  type="text" v-model="item.texture" />
          </td>
          <td class="col-quantity">
            <InputNumber  :max="999999" :min="0" v-model="item.quantity" @on-change="summation(item)"> </InputNumber>
          </td>
          <td >
            <Input  type="text" v-model="item.unit" />
          </td> 
          <td>
            <InputNumber  :max="999999" :min="0" v-model="item.weight"> </InputNumber>
          </td>
          <td>
           <InputNumber  :max="999999" :min="0" v-model="item.weightTotal"> </InputNumber>
         </td>
         <td class="col-price">
          <InputNumber  :max="999999" :min="0" :step="0.01" v-model="item.processFee" @on-change="summation(item)"> </InputNumber>
        </td>
        <td class="col-amount">
          {{item.amount}} 
        </td>
        <td>
          <DatePicker type="date"
          v-model="item.deliveryDate" format="yyyy-MM-dd"
          style="width:120px"></DatePicker>
        </td>
        <td>
          <Input  type="text" v-model="item.remark" />
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
        total:0, 
        totalBig:'零'
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
          materName:'',
          spec:'',
          model:'',
          subType:'',
          use:'',
          price:0,
          processFee:0,
          quantity:0,
          amount:0,
          afterPrice:'',
          remark :'',
          wfInstId:"",
          wfInstTime:"",
          wfInstRemark:null,
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
            row.price = data.price;
            row.subType = data.subType;
            this.computedAmount(row);
          }
        });
      },
      summation(row){
        row.amount = floatObj.multiply(row.processFee,row.quantity);
        var tota=0;
        this.list.map((mater)=>{
          tota = floatObj.add(tota,mater.amount);
          this.total=tota;
        });
        this.$emit('on-amount-change',tota); 
      },
      selProvider(row){
          var sel = this.$refs.selProvider;//引用该控件，赋值给变量对象
          sel.show({
            ok:(data)=>{
              if(data){
                row.providerName = data.providerName;
                row.providerId = data.providerId; 
              }
            }
          });
        },
        selPlace(row){
          var selplace = this.$refs.selPlace;
          selplace.show({
            storageId:this.storageId,
            ok:(data)=>{
              row.placeId = data.placeId;
              row.placeName = data.placeName;
            }
          });
        },
    }
  }

</script>

<style type="text/css"> 

</style>
