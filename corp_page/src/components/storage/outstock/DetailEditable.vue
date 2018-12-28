<template> 
  <Editable @add="add" @remove="remove" :editable="editable">
    <table cellspacing="0" cellpadding="0" v-if="!editable"> 
      <thead>
        <th class="col-xh">序号</th> 
        <th >物料编码</th>
        <th >物料名称</th>
        <th >规格</th>
        <th >型号</th>
        <th >批号</th> 
        <th >货位</th> 
        <th >单位</th>
        <th><span>数量/重量</span></th>
        <th ><span>交回数量</span></th>
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
          <td class="col-batch">
            <Input  type="text" v-model="item.batch"/>
          </td> 
          <td>
            {{item.placeId}}
          </td>
          <td >
            {{$args.getArgText('unit',item.unit)}}  
          </td>
          <td> 
            {{item.quantity}}
          </td>
          <td> 
            {{item.returnQuantity}}
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
          <th >批号</th> 
          <th >货位</th> 
          <th >单位</th>
          <th><span>数量/重量</span></th>
          <th ><span>交回数量</span></th>
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
              {{item.spec}} 
            </td>
            <td >
              {{item.model}} 
            </td>
            <td class="col-batch">
              <Input  type="text" v-model="item.batch" style="width:50px"/>
            </td> 
            <td class="col-placeId">
              <span>{{item.placeName}}</span>
            </td>
            <td >
              {{$args.getArgText('unit',item.unit)}}  
            </td>  
            <td class="col-quantity"> 
              <InputNumber  :max="999999" :min="0" v-model="item.quantity"></InputNumber> 
            </td>
            <td class="col-returnQuantity"> 
              <InputNumber  :max="999999" :min="0" v-model="item.returnQuantity"></InputNumber> 
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" style="width:250px"/>
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
 
  import SelectMaterial from '@/components/storage/selmaterial/SelectMaterial';
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
          quantity:0,
          returnQuantity:0,
          amount:0,
          originBatch:'',
          batch:'',
          madeDate:'',
          validityDate:'',
          qualityTime:'',
          remark :'',
          batch:'',
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
            row.batch=data.batch;
            row.placeName=data.placeName;
            row.placeId=data.placeId;
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
    }
  }

</script>

<style type="text/css"> 
   .editable-table-container td.col-placeId > span{
    width: 100px;display: inline-block;
  }
</style>
