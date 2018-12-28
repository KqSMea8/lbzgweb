<template>
  <div class="page instock-edit">
    <div class="page-bar">
      <LayoutHor>
        <div slot="left">
          <Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>
        </div>
        <div class="page-bar-title">{{pageTitle}}</div>
      </LayoutHor>
    </div>
    <Loading :loading="loading">
      <div class="baseinfo">
        <div class="page-tools">
          <table class="savebar" cellpadding="0" cellspacing="0">
            <tr>
              <td>
                <i-button type="info" @click="save">保存</i-button>
              </td>
              <td>
                <i-button type="primary" @click="remove">删除明细</i-button>
              </td>
              <td><i-button @click="reset">清除</i-button></td>
              <td>

                <i-button @click="goBack">退出</i-button>
              </td>
              <td></td>
            </tr>
          </table>
        </div>
        <div class="subheader">
          调价单
        </div>

        <Form ref="form" class="page-form" :model="formItem" :rules="formRules" :label-width="120">
          <table cellspacing="0" cellpadding="0">
            <colgroup>
              <col width="33%">
              <col width="auto">
              <col width="33%">
            </colgroup>
            <tr>
            <td>

             <FormItem prop="createTime" label="操作日期">
               <DatePicker type="date" placeholder="" v-model="formItem.createTime" format="yyyy-MM-dd" style="width:120px"></DatePicker>
             </FormItem></td>
            <td>
              <!--<FormItem prop="providerName" label="供货商">
                <Input v-model="formItem.providerName" readonly="readonly" style="cursor: default;" icon="search" @on-click="selProvider"></Input>
              </FormItem>-->
              <FormItem prop="operator" label="操作人">
               <!-- <Input readonly="readonly" v-model="formItem.sales"  style="width:120px"></Input>-->
                <Input readonly="readonly" v-model="formItem.operator"  style="width:120px"></Input>
              </FormItem>


            </td></tr>
            <tr>
            <td>
              <FormItem prop="sales" label="器具出厂日期">
                <DatePicker type="date" placeholder="" v-model="formItem.manufactureDate" format="yyyy-MM-dd" style="width:120px"></DatePicker>
              </FormItem> </td>
              <td>
              <FormItem prop="sales" label="器具返厂日期">
                <DatePicker type="date" placeholder="" v-model="formItem.applianceReturnDate" format="yyyy-MM-dd" style="width:120px"></DatePicker>
              </FormItem>
              </td>
            <td rowspan="2" style="vertical-align: top;">
              <FormItem prop="testAllCost" label="检定费用 ">
                <Input v-model="formItem.testAllCost" type="text" style="width: 120px;"></Input>
              </FormItem>
            </td>
            </tr>
          </table>
        </Form>
      </div>
      <div>
        <div class="subheader">
          单据明细
        </div>
        <Editable ref="editable"
                  :list="list"
                  :editable="true"
                  :storageId="formItem.storageId"
                  @changeNumber="changeNumber"
                  @on-amount-change="onAmountChange"></Editable>
      </div>

    </Loading>
    <SelProvider ref="selProvider"></SelProvider>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './OutsideEditable';
  import page from '@/assets/js/page';
  import floatObj from '@/assets/js/floatObj';
  import SelStorage from '@/components/storage/input/SelStorage';
  import SelStockOperType from '@/components/storage/input/SelStockOperType';
  import SelDeptInput from '@/components/selcontacts/SelDeptInput';
  import SelProvider from '@/components/provider/SelectProvider'; //导入选供应商的控件
  import SelPersonInput from '@/components/selcontacts/SelPersonInput';
  export default {
    components: {
      Loading,
      LayoutHor,
      Editable,
      SelStorage,
      SelProvider,
      SelStockOperType,
      floatObj,
      SelDeptInput,
      SelPersonInput,
    },
    data() {
      return {
        loading: 0,
        formItem:{
          providerName:'',
          afterPrice:'',
          manufactureDate:'',
          createTime:page.formatDate(new Date(),'yyyy-MM-dd'),
          applianceReturnDate:page.formatDate(new Date(),'yyyy-MM-dd'),
          operator:this.$user.empInfo.trueName,
					instrumentId:'',
					externalApplyId:'',
        },
        formRules:{
          providerName: [
            { required: true, message: '请选择供货商' }
          ],
        },
        list:[],
        oriItem:{},
        storage:[]
      }
    },
    mounted: function () {
        this.load();
    },
    computed: {
      pageTitle(){
          return '外检申请单 - 创建';
      }
    },
    watch: {
      // 如果路由有变化，会再次执行该方法
      '$route': 'load'
    },
    methods: {
      load() {
        this.externalApplyId = this.$route.query.id;
        this.loading = 1;
        this.$http.post("/api/ExternalInspectionApply/get?externalApplyId="+this.externalApplyId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
             /* this.oriItem = eval('('+JSON.stringify(res.data.data)+')');
              Object.assign(this.formItem,res.data.data);*/
              this.list = res.data.data;
              for(var i in this.list){
                this.list[i].afterPrice=0;
                this.list[i].appraisalDate=page.formatDate(new Date(),'yyyy-MM-dd');
                this.list[i].testCost=0;
              }
            }else{
              this.list=[];
              this.list.push();
              this.$Message.error('订单不存在！');
              this.goBack();
            }
          } else {
            this.$Message.error(res.data.message);
            this.list = [];
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("操作失败！")
        });
      },
      remove(){
        this.$refs.editable.remove();
      },
      save(){

        // 明细
/*        for(var i = 0;i<this.list.length;i++){
        }*/

        // 提交
        //this.loading = 1;


        var form = this.formItem;
        form.detailList=this.list;

        var uri = 'api/InstrumentInspectionAccept/insertIIA';

        this.$http.post(uri, form).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            this.$Message.success("操作成功！");
            this.goBack();
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新操作")
        });
      },
      onAmountChange(val){
        this.formItem.amount = val;
      },
      datePickerChange(val){
        this.formItem.signDate = val;
      },
      switStorage(){

      },
      reset(){
        Object.assign(this.formItem,{
            manufactureDate:'',
            testAllCost:'',
        });
          this.load();
      },
      selProvider(){
        var sel = this.$refs.selProvider;//引用该控件，赋值给变量对象
        sel.show({
          ok:(data)=>{
            if(data){
              this.formItem.providerName = data.providerName;
              this.formItem.providerId = data.providerId;
              this.formItem.linkMan = data.linkMan;
              this.formItem.linkPhone = data.linkPhone;
              this.formItem.address = data.address;
            }
          }
        });
      },
      goBack(){
        this.$router.go('-1');
      },
      goImport(){
        var sel = this.$refs.selInStockApply;
        sel.show({
          isMulti:false,
          selectPerson:false,
          selectDept:true,
          ok:(select)=>{
            if(select){
              this.formItem.relatedBillId = select.relatedBillId;
              this.formItem.relatedBillId1 = select.relatedBillId1;

            }
          }
        });
      },
      changeNumber(total){
        this.formItem.testAllCost=total;
      },
    }
  }

</script>

<style type="text/css">
  .instock-edit.page{
    width: 900px;
    margin: 0 auto;
    padding: 10px 0px;
    position: relative;
  }
  .instock-edit .subheader{
    height: 34px;line-height: 34px;
    font-size:14px;
    border-bottom: 0px solid #efefef;
    margin-bottom: 10px;
    color:#20bfee;
  }
  .instock-edit .baseinfo{

  }
  .instock-edit .baseinfo .label{
    width: 80px;text-align: right;
    padding-right: 8px;
  }
  .instock-edit .baseinfo table{
    width: 100%;
  }
  .instock-edit .baseinfo table td{
    height: 40px;padding-right:4px;
  }

  .instock-edit .savebar{
    margin-top: 10px;
    height: 40px;
    width: 100%;
    border-collapse: collapse;
  }
  .instock-edit .savebar td{
    border: 1px solid #fefefe;
    font-size: 14px;
  }
  .instock-edit .savebar .save{
    width: 120px;
    border: 1px solid #20bfee;
    background-color: #20bfee;
    color: white;
    text-align: center;
    cursor: pointer;
  }
  .instock-edit .savebar .reset{
    width: 60px;
    border: 1px solid #a1e7f8;
    background-color: #a1e7f8;
    color: white;
    text-align: center;
    cursor: pointer;
  }

  .selectinput{
    cursor: pointer;
  }

  .instock-edit .tooltip{
    padding:10px;
    background-color: #fafafa;
    border:1px solid #efefef;
    border-radius: 3px;
    color:#666;
    margin-top: 10px;
  }

</style>
