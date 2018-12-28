<template>
  <ListPage :editable="!editable">
    <table cellspacing="0" cellpadding="0">
      <thead>
      <th >铸件名称</th>
      <th >图号</th>
      <th >申请数量</th>
      <th >顾客单位</th>
      <th ></th>
      <th ></th>
      </thead>
      <tbody>
      <tr v-for="(item,index) in list"  :key="'mater_'+index" @click="curIndex = index">
        <td>
          {{item.materId}}
        </td>
        <td >
          {{item.counterName}}
        </td>
        <td >
          {{item.model}}
        </td>
        <td >
          {{item.orgin}}
        </td>
        <td >
          {{item.internalId}}
        </td>
        <td >
          {{item.installationSite}}
        </td>
      </tr>
      </tbody>
    </table>
    <!--<SelectMaterial ref="selmaterial" :transfer="true"></SelectMaterial>
    <SelProvider ref="selProvider"></SelProvider>
    <SelectPlace ref="selPlace"></SelectPlace>-->
  </ListPage>
</template>
<script>
  import ListPage from '@/components/editable-table';
/*  import floatObj from '@/assets/js/floatObj';
  import SelectMaterial from '@/components/purchase/provider-material/SelProviderMaterial'
  import SelProvider from '@/components/provider/SelectProvider';
  import SelectPlace from '@/components/storage/selplace/SelectPlace'*/
  export default {
    components: {
      //SelectMaterial,
      ListPage,
      //SelProvider,
      //SelectPlace
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
    this.listNewRow();
    },
    computed: {},
    methods: {
      load() {


      },
      listNewRow(){
        var def = {
          materId:"",
          materName:'',
          model:'',
          orgin:'',
          internalId:'',
          installationSite:'',
          retestingPeriod:'',
          testingTime:"",
          remark:'',
        };
        return def;
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
      selProvider(row){
        var sel = this.$refs.selProvider;//引用该控件，赋值给变量对象
        sel.show({
          ok:(data)=>{
            if(data){
              row.materId = data.materId;
              row.materName = data.materName;
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
