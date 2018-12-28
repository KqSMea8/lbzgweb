<template> 
  <Editable @add="add" @remove="remove" :editable="editable">
    <table cellspacing="0" cellpadding="0" v-if="!editable"> 
      <thead>
        <th class="col-xh">序号</th> 
        <th >物料编码</th>
        <th >物料名称</th>
        <th >规格</th>
        <th >型号</th>
        <th >采购类别</th> 
        <th >单位</th>
        <th><span>单价</span></th>
        <th ><span>数量</span></th>
        <th ><span>金额</span></th>
        <th ><span>用途</span></th>
        <th >供应商</th>
        <th >供方或产地</th>
        <th><span>需求日期</span></th>
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
            {{item.spec}} 
          </td>
          <td >
            {{item.model}} 
          </td>
          <td >
            {{$args.getArgText('material_subtype',item.subType)}} 
          </td> 
          <td >
            {{$args.getArgText('unit',item.unit)}}  
          </td>
          <td> 
            {{item.price}}
          </td>
          <td> 
            {{item.quantity}}
          </td>
          <td> 
            {{item.amount}}
          </td>
          <td> 
            {{item.use}} 
          </td>
          <td > 
            {{item.providerName}}
          </td>
          <td>
            {{item.origin}}
          </td>
          <td> 
            {{item.needDate}}
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
          <th >规格</th>
          <th >型号</th>
          <th class="col-w3"><span>批次号</span></th> 
          <th >供应商</th> 
          <th >货位</th> 
          <th class="col-quantity"><span>数量</span></th>
          <th class="col-date"><span>生产日期</span></th>
          <th class="col-date"><span>到厂日期</span></th>
          <th class="col-date"><span>有效期</span></th>
          <th class="col-w2"><span>质保期</span></th>
          <th >单位</th>
          <th class="col-price"><span>单价</span></th> 
          <th class="col-amount"><span>金额</span></th> 
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
            <td >
              <Input type="text" v-model="item.batch" /> 
            </td> 
            <td class="col-select" 
              @click="editable && !isImport && selProvider(item)">
              <span>{{item.providerName}}</span>
            </td>
            <td class="col-select" 
              @click="editable && selPlace(item)">
              <span>{{item.placeName}}</span>
            </td>
            <td class="col-quantity"> 
              <InputNumber  :max="999999" :min="0" v-model="item.quantity" @on-change="computedAmount(item)"></InputNumber> 
            </td>
            <td class="col-date"> 
              <DatePicker  type="date" placeholder="生产日期" v-model="item.madeDate" @on-change=""></DatePicker>  
            </td>
            <td class="col-date"> 
              <DatePicker  type="date" placeholder="到厂日期" v-model="item.arrivalDate" @on-change=""></DatePicker>  
            </td>
            <td class="col-date"> 
              <DatePicker  type="date" placeholder="有效期" v-model="item.validityDate" @on-change=""></DatePicker>  
            </td> 
            <td><Input  type="text" v-model="item.qualityTime" /></td>
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
    <SelectMaterial ref="selmaterial" :transfer="true"></SelectMaterial>
    <SelProvider ref="selProvider"></SelProvider>
    <SelectPlace ref="selPlace"></SelectPlace>
  </Editable>  
</template>
<script>
  import Editable from '@/components/editable-table';
  import floatObj from '@/assets/js/floatObj';
  import SelectMaterial from '@/components/material/selmaterial'
  import SelProvider from '@/components/provider/SelectProvider';
  import SelectPlace from './selplace/SelectPlace'
  export default {
    components: {
      SelectMaterial,
      Editable,
      SelProvider,
      SelectPlace
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
      }
    }
  }

</script>

<style type="text/css"> 
   
</style>
