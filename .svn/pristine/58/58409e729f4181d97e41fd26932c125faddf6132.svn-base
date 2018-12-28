<template>
  <Editable :editable="editable">
    <div cellspacing="0" cellpadding="0" v-if="!editable" v-for="(item,index) in list"  :key="'mater_'+index" @click="curIndex = index">
      <div style="width: 100%;height: 0px;margin-bottom: 10px;margin-top: 20px"></div><!--/*border: #666666 solid 2px;*/-->
      <table style="border-top: solid #666666 4px;padding-top: 20px;!important;">
        <thead>
        <th class="col-xh">序号</th>
        <th >物料编码</th>
        <th >物料名称</th>
        <th >规格型号</th>
        <th >品种</th>
        <th >级别</th>
        <th >单位</th>
        <th class="col-remark"><span>申购数量</span></th>
        <th class="col-remark"><span>用途</span></th>
        <th class="col-remark"><span>供方或产地</span></th>
        <th class="col-w3"><span>供应商</span></th>
        <th >产地</th>
        <th >采购员</th>
        <th class="col-date"><span>到货日期</span></th>
        <th class="col-remark"><span>单价</span></th>
        <th class="col-remark"><span>金额</span></th>
        <th class="col-remark"><span>原厂批号</span></th>
        <th class="col-remark"><span>厂批号</span></th>
        <th class="col-remark"><span>到货数量</span></th>
        <th class="col-remark"><span>车号</span></th>
        <th class="col-remark"><span>毛重</span></th>
        <th class="col-remark"><span>皮重</span></th>
        <th class="col-remark"><span>净重</span></th>
        <th class="col-remark"><span>生产日期</span></th>
        <th class="col-remark"><span>到厂日期</span></th>
        <th class="col-remark"><span>有效期</span></th>
        <th class="col-remark"><span>质保期</span></th>
        <th class="col-remark"><span>备注</span></th>
        </thead>
        <tbody>
        <tr>
          <td>
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
            {{item.subType}}
          </td>
          <td >
            {{item.level}}
          </td>
          <td >
            {{item.providerName}}
          </td>
          <td >
            {{item.unit}}
          </td>
          <td>
            {{item.quantity}}
          </td>
          <td>
            {{item.use}}
          </td>
          <td>
            {{item.origin}}
          </td>

          <td >
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
            {{item.batch}}
          </td>
          <td>
            {{item.arrivalQuantity}}
          </td>
          <td>
            {{item.vehicleNo}}
          </td>
          <td>
            {{item.roughWeight}}
          </td>
          <td>
            {{item.takeOutWeight}}
          </td>
          <td>
            {{item.packageWeight}}
          </td>
          <td>
            {{item.packageWeight}}
          </td>
          <td>
            {{item.validityDate}}
          </td>
          <td>
            {{item.qualityTime}}
          </td>
          <td>
            {{item.qualityTime}}
          </td>
          <td>
            {{item.remark}}
          </td>
        </tr>
        </tbody>
      </table>
      <table class="savebar" cellpadding="0" cellspacing="0">
        <tr>
          <td> <!--@click="save"-->
            <i-button type="info" @click="parameterOpen(index)">创建检验台账</i-button>
          </td>
          <td> <!--@click="reset"-->
            <i-button type="info" @click="createStandard(index)">创建不合格通知单</i-button>
          </td>
          <td>
            检验员：
          </td>
          <td>
            <Input v-model="list[index].inspector" readonly="readonly"
                   style="cursor: pointer;width:100px;"  placeholder="检验员" @click.native="selEmp1(index)" icon="search"></Input>
          </td>
          <td>
            化验员：
          </td>
          <td>
            <Input v-model="list[index].analyst" readonly="readonly"
                   style="cursor: pointer;width:100px;"  placeholder="化验员" @click.native="selEmp2(index)" icon="search"></Input>
          </td>
          <td>
            检验日期：
          </td>
          <td>
            <!--<Input type="date" placeholder="" v-model="list[index].nowDate" :readonly="isOnclick[index]"></Input>-->
            <div class="dateClick">
            <input type="text" placeholder="" disabled="disabled" v-model="list[index].nowDate" format="yyyy-MM-dd" style="width:120px"></input>
            </div>
          </td>
          <td>
            <i-button type="success" @click="dgbc(index)">保存</i-button>
          </td>
        </tr>
      </table>


    </div>


    <SelectMaterial ref="selmaterial" :transfer="true"></SelectMaterial>
    <SelContacts ref="selContacts"></SelContacts> <!-- 新加部门选择控件 -->
    <Parameter ref="parameter" @close="close"></Parameter> <!-- 新加部门选择控件 -->
  </Editable>
</template>
<script>
  import Editable from '@/components/editable-table';
  import floatObj from '@/assets/js/floatObj';
  import SelectMaterial from '@/components/purchase/provider-material/SelProviderMaterial';
  import SelContacts from '@/components/selcontacts'; //引用选择部门控件
  import Parameter from './parameter'; //引用选择部门控件

  export default {
    components: {
      SelectMaterial,
      Editable,
      SelContacts,
      Parameter
    },
    props:{
      list:{
        type:Array,
        default:function () {
          var arr = {
          }
          return arr;
        }
      },
      formItem:{
            type:Array,
            default:function () {
                var arr = {
                }
                return arr;
        }
      },
      formRecord:{
          type:Array,
          default:function () {
              var arr = {
              }
              return arr;
          }
      },
      editable:{
        type:Boolean,
        default:false
      },
      purchaseOrderId:{
        type:String,
        default:''
      },
        inspectionId:{
            type:String,
            default:''
        },
    },

    data() {
      return {
        curIndex:0,
        isOnclick:[],
      }

    },
    mounted: function () {
        /*this.purchaseOrderId = this.$route.query.purchaseOrderId;*/

    },
    computed: {

    },
      watch:{
        "list":"reset"
      },
    methods: {

      load() {

      },
      parameterOpen(index){
          var formParameterData=[
          ];
          formParameterData.purchaseOrderId=this.purchaseOrderId;
          formParameterData.inspectionId=this.inspectionId;
          formParameterData.inspectionApplyId=this.inspectionApplyId;
        this.$refs.parameter.open(formParameterData);
      },
      selEmp1(index){
          if(this.isOnclick[index]){
              return;
          }
        var sel = this.$refs.selContacts;

        sel.show({
          isMulti:false,
          selectPerson:true,
          selectDept:false,
          ok:()=>{
            if(sel.select.length>0){
              this.list[index].inspector=sel.select[0].title;
              this.list.push();
            }
          }

        });

      },
      reset(){
            this.curIndex=0;
            this.isOnclick=[];
            $(".dateClick").css("pointer-events","");
      },
      selEmp2(index){
          if(this.isOnclick[index]){
              return;
          }
        var sel = this.$refs.selContacts;
        sel.show({
          isMulti:false,
          selectPerson:true,
          selectDept:false,
          ok:()=>{
            if(sel.select.length>0){
              this.list[index].analyst=sel.select[0].title;
             // Object.assign(this.list, this.list);
              this.list.push();
            }
          }
        });
      },
        close(){
          this.$emit("closeThisParent");
        },
      dgbc(index){
        if(this.isOnclick[index]==1){
          this.$Message.info("已经保存过了");
          return;
        }
       this.isOnclick[index]=1;
       $(".dateClick").eq(index).css("pointer-events","none");
       this.$emit("singleSave",index);
      },
      createStandard(index){
          if(this.isOnclick[index]==1){
              return;
          }
        this.$router.push("/inspectionRecord/Standard?purchaseOrderId="+this.purchaseOrderId+"&inspectionId="+this.inspectionId+"&materId="+this.list[index].materId+"&inspectionApplyId="+this.formItem.inspectionApplyId);
      },
    }
  }

</script>

<style type="text/css">

</style>
<!--
    <table cellspacing="0" cellpadding="0" v-else>
              <thead>
              <th class="col-xh">序号</th>
              <th >物料编码</th>
              <th >物料名称</th>
              <th >规格型号</th>
              <th >品种</th>
              <th >级别</th>
              <th >单位</th>
              <th class="col-price"><span>申购数量</span></th>
              <th class="col-quantity"><span>用途</span></th>
              <th class="col-amount"><span>供方或产地</span></th>
              <th class="col-w3"><span>供应商</span></th>
              <th >产地</th>
              <th >采购员</th> =
              <th class="col-date"><span>到货日期</span></th>
              <th class="col-remark"><span>单价</span></th>
              <th class="col-remark"><span>金额</span></th>
              <th class="col-remark"><span>原厂批号</span></th>
              <th class="col-remark"><span>厂批号</span></th>
              <th class="col-remark"><span>到货数量</span></th>
              <th class="col-remark"><span>车号</span></th>
              <th class="col-remark"><span>毛重</span></th>
              <th class="col-remark"><span>皮重</span></th>
              <th class="col-remark"><span>净重</span></th>
              <th class="col-remark"><span>生产日期</span></th>
              <th class="col-remark"><span>到厂日期</span></th>
              <th class="col-remark"><span>有效期</span></th>
              <th class="col-remark"><span>质保期</span></th>
              <th class="col-remark"><span>备注</span></th>
              </thead>
              <tbody>
              <tr v-for="(item,index) in list"  :key="'mater_'+index" @click="curIndex = index">
                <td :class="{'col-xh':true,'cur':index==curIndex}">
                  {{index+1}}
                </td>
                <td class="col-select" @click="editable && selMater(item)">
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
                  {{$args.getArgText('material_subtype',item.subType)}}
                </td>
                <td >
                  {{$args.getArgText('unit',item.unit)}}
                </td>
                <td class="col-price">
                  {{item.price}}
                </td>
                <td class="col-quantity">
                  <InputNumber  :max="999999" :min="0" v-model="item.quantity" @on-change="computedAmount(item)"></InputNumber>
                </td>
                <td class="col-amount">
                  {{item.amount}}
                </td>
                <td>
               <Input type="text" v-model="item.use" />
            </td>
            <td >
              {{item.providerName}}
            </td>
            <td>
              {{item.origin}}
            </td>
            <td class="col-date">
              <DatePicker  type="date" placeholder="需求日期" v-model="item.needDate" @on-change="datePickerChange(item,arguments)"></DatePicker>
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>
            <td class="col-remark">
              <Input  type="text" v-model="item.remark" />
            </td>
          </tr>
        </tbody>
      </table>-->