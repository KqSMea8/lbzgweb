<template> 
  <Editable @add="add" @remove="remove" :editable="editable">
    <table cellspacing="0" cellpadding="0" v-if="!editable"> 
      <thead>
        <th class="col-xh">序号</th> 
        <th >物料编码</th>
        <th >物料名称</th>
        <th >规格</th>
        <th >型号</th>
        <th >单位</th>
        <th >移出货位</th>
        <th >移入货位</th>
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
            {{$args.getArgText('unit',item.unit)}}  
          </td>
          <td>
            {{item.placeId}}
          </td>
          <td> 
            {{item.relatedPlaceId}}
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
          <th >单位</th>
          <th class="col-quantity"><span>数量</span></th>
          <th >移出货位</th> 
          <th >移入货位</th>  
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
             <td>
              {{$args.getArgText('unit',item.unit)}}  
            </td>
            <td class="col-quantity"> 
              <InputNumber  :max="999999" :min="0" v-model="item.quantity" @on-change="computedAmount(item)"></InputNumber> 
            </td>
            <td >
              <span>{{item.placeName}}</span>
            </td>
              </td><td class="col-select" 
              @click="editable && selPlace1(item)">
              <span>{{item.relatedPlaceName}}</span>
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>
          </tr>
        </tbody>
      </table>
    <SelectMaterial ref="selmaterial" :transfer="true" :storageId="storageId"></SelectMaterial>
    <SelProvider ref="selProvider"></SelProvider>
    <SelectPlace ref="selPlace1" storageId="storageId1"></SelectPlace>
  </Editable>  
</template>
<script>
  import Editable from '@/components/editable-table';
  import floatObj from '@/assets/js/floatObj';
  import SelectMaterial from '@/components/storage/selmaterial/SelectMaterial';
  import SelProvider from '@/components/provider/SelectProvider';
  import SelectPlace from '../selplace/SelectPlace'
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
      storageId1:{
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
          relatedPlaceId:'',
          placeName:'',
          relatedPlaceName:'',
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
            row.placeId = data.placeId;
            row.placeName = data.placeName;
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
     
      selPlace1(row){
        var selplace = this.$refs.selPlace1;
        selplace.show({
          storageId:this.storageId1,
          ok:(data)=>{
            row.relatedPlaceId = data.placeId;
            row.relatedPlaceName = data.placeName;
          }
        });
      }
    }
  }

</script>

<style type="text/css"> 
   
</style>
