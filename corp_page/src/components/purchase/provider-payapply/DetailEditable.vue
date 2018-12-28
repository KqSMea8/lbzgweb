<template> 
  <Editable @add="add" @remove="remove" :editable="editable">
    <table cellspacing="0" cellpadding="0" v-if="!editable"> 
      <thead>
        <th class="col-xh">序号</th> 
        <th >申请单编号</th>
        <th >供应商代码</th>
        <th >供应商名称</th>
        <th >申请费用</th>
        <th >采购合同编号</th> 
        <th >合同附件</th>
        <th ><span>备注</span></th>
      </thead>
      <tbody>
        <tr v-for="(item,index) in list"  :key="'mater_'+index" @click="curIndex = index">
          <td :class="{'col-xh':true,'cur':index==curIndex}">
            {{index+1}}
          </td>
          <td>
            {{item.paId}}
          </td>
          <td >
            {{item.providerId}} 
          </td>
          <td >
            {{item.providerName}} 
          </td>
          <td >
            {{item.amount}} 
          </td>
          <td >
            {{item.orderId}} 
          </td> 
          <td >
            {{item.files}}  
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
          <th >申请单编号</th>
          <th >供应商代码</th>
          <th >供应商名称</th>
          <th >申请费用</th>
          <th >采购合同编号</th> 
          <th >合同附件</th>
          <th class="col-remark"><span>备注</span></th>
        </thead> 
        <tbody>
          <tr v-for="(item,index) in list"  :key="'mater_'+index" @click="curIndex = index">
            <td :class="{'col-xh':true,'cur':index==curIndex}">
              {{index+1}}
            </td>
            <td >
              <span>{{item.paId}}</span>
            </td>  
            <td  class="col-select" @click="editable && !isImport &&  selProvider(item)">
              {{item.providerId}} 
            </td>
            <td >
              {{item.providerName}} 
            </td>
            <td class="col-amount">
              <Input  type="text" v-model="item.amount" />
            </td>
            <td class="col-orderId">
              <Input  type="text" v-model="item.orderId" />
            </td> 
            <td >
              {{item.files}}
            </td> 
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>
          </tr>
        </tbody>
      </table>
    <SelProvider ref="selProvider"></SelProvider>
  </Editable>  
</template>
<script>
  import Editable from '@/components/editable-table';
  import floatObj from '@/assets/js/floatObj';
  import SelProvider from '@/components/provider/SelectProvider';

  export default {
    components: {
      Editable,
      SelProvider,
    },
    props:{
      list:{
        type:Array,
        default:function () {
          var arr = [];
        }
      },
      paId:{
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
          paId:'',
          providerId:'',
          providerName:'',
          amount:'',
          orderId:'',
          files:'',
          proposer:'',
          remark:'',
          status:'',
          creator:'',
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
   
</style>
