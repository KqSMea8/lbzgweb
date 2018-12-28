<template> 
  <Editable @add="add" @remove="remove" :editable="editable">
    <table cellspacing="0" cellpadding="0" v-if="!editable"> 
      <thead>
        <th class="col-xh">序号</th> 
        <th >物料名称</th>
        <th >合同号</th>
        <th >图号</th>
        <th >材质</th>
        <th >铸造号</th> 
        <th >条形码</th>
        <th >批号</th>
        <th >箱号</th>
        <th >数量</th>
        <th><span>备注</span></th>
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
            {{item.contractId}} 
          </td>
          <td >
            {{item.drawing}} 
          </td>
          <td >
            {{item.texture}} 
          </td>
          <td >
            {{item.casting}} 
          </td> 
          <td >
            {{item.barcode}}  
          </td>
          <td> 
            {{item.batch}}
          </td>
          <td> 
            {{item.box}}
          </td>
          <td> 
            {{item.quantity}}
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
          <th >物料名称</th>
          <th >合同号</th>
          <th >图号</th>
          <th >材质</th>
          <th >铸造号</th> 
          <th >条形码</th>
          <th >批号</th>
          <th >箱号</th>
          <th >数量</th>
          <th><span>备注</span></th>
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
              <Input  type="text" v-model="item.contractId" />
            </td>
            <td >
              <Input  type="text" v-model="item.drawing" />
            </td>
            <td >
              <Input  type="text" v-model="item.texture" />
            </td>
            <td >
              <Input  type="text" v-model="item.casting" />
            </td> 
            <td>
              <Input  type="text" v-model="item.barcode" />
            </td>
            <td>
              <Input  type="text" v-model="item.batch" />
            </td>
            <td>
              <Input  type="text" v-model="item.box" />
            </td>
            <td>
              <Input  type="text" v-model="item.quantity" />
            </td>
            <td>
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
  import SelectMaterial from '@/components/purchase/provider-material/SelProviderMaterial'
  import SelProvider from '@/components/provider/SelectProvider';
  import SelectPlace from '@/components/storage/selplace/SelectPlace'
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
          materName:'',
          spec:'',
          model:'',
          subType:'',
          use:'',
          price:0,
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
