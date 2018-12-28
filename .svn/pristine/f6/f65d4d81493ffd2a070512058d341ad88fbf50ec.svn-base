<template>
    <Editable @remove="remove" :editable="!editable">
        <table cellspacing="0" cellpadding="0">
            <thead>
            <th>序号</th>
            <th>物料代码</th>
            <th >物料名称</th>
            <th >规格型号</th>
            <th >品种</th>
            <th >级别</th>
            <th >供应商</th>
            <th >产地</th>
            <th >采购员</th>
            <th >到货日期</th>
            <th >单价</th>
            <th >金额</th>
            <th >原厂批号</th>
            <th >厂批号</th>
            <th >到货数量</th>
            <th >车号</th>
            <th >毛重</th>
            <th >皮重</th>
            <th >净重</th>
            <th >生产日期</th>
            <th >到厂日期</th>
            <th >有效期</th>
            <th >质保期</th>
            <th >检验数量</th>
            <th >不合格数量</th>
            <th >单位</th>
            <th >备注</th>
            </thead>
            <tbody>
            <tr v-for="(item,index) in list[0].detailList"  :key="'mater_'+index" @click="curIndex = index">
                <td :class="{'col-xh':true,'cur':index==curIndex}">
                    {{index+1}}
                </td>
                <td ><!-- @click="editable && !isImport &&  selMater(item)"-->
                    {{item.materId}}
                </td>
                <td >
                    {{item.materName}}
                </td>
                <td >
                    {{item.spec}}
                </td>
                <td >
                    {{item.subType}}
                </td>
                <td >
                    {{item.level}}
                </td>
                <td>

                    {{item.providerName}}
                </td>
                <td>
                    {{item.origin}}
                </td>
                <td>

                    {{item.buyer}}
                </td>
                <td>

                    {{item.arrivalDate}}
                </td>
                <td>

                    {{item.price}}
                </td>
                <td>

                    {{item.amount}}
                </td>
                <td>

                    {{item.originBatch}}
                </td>
                <td>

                    {{item.remark}}
                </td>
                <td>

                    {{item.quantity}}
                </td>
                <td>

                    {{item.vehicleNo}}
                </td>
                <td>

                    {{item.roughWeight}}
                </td>
                <td>

                    {{item.packageWeight}}
                </td>
                <td>

                    {{item.takeOutWeight}}
                </td>
                <td>

                    {{item.madeDate}}
                </td>
                <td>

                    {{item.arrivalDate}}
                </td>
                <td>

                    {{item.quality}}
                </td>
                <td>

                    {{item.quality}}
                </td>
                <td>

                    {{item.unit}}
                </td>
                <td>
                    {{item.remark}}
                </td>
                <td>
                    {{item.wfInstRemark}}
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
    mounted: {
      formItem: function(){
        return  formItem.remark=1;
      }
    },
    computed: {

    },
    methods: {
      load() {

      },
      add(){
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
      },
    }
  }

</script>

<style type="text/css">

</style>
