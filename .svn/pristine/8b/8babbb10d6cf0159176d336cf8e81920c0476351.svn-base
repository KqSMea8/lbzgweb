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
        </div>
 <!--       <div class="subheader">
          调价单
        </div>-->
        <Form ref="form" class="page-form" :model="formItem" :rules="formRules" :label-width="120">
          <table cellspacing="0" cellpadding="0">
            <colgroup>
              <col width="33%">
              <col width="auto">
              <col width="33%">
            </colgroup>
            <tr>
            <td>
              <FormItem prop="unqualifiedId" label="采购不合格品编号：">
                <Input readonly="readonly" placeholder="系统自动生成" v-model="formItem.unqualifiedId" type="text" style="width:120px"></Input>
              </FormItem></td>
              <td> <FormItem prop="purchaseOrderId" label="采购申请单号：">
                <Input readonly="readonly" v-model="purchaseOrderId" type="text" style="width:120px"></Input>
            </FormItem></td>
              <td> <FormItem prop="inspectionId" label="检验单号：">
                <Input v-model="inspectionId" type="text" style="width:120px"></Input>
            </FormItem></td>
              <td> <FormItem prop="lnventoryStatus" label="入库状态：">
                <Select v-model="formItem.lnventoryStatus" style="width:200px">
                  <Option value=""></Option>
                  <Option value="1">未入库</Option>
                  <Option value="2">已入库</Option>
                </Select>
            </FormItem></td>
            </tr>
            <tr>
              <td>
              <FormItem prop="createTime" label="检验日期：">
                <DatePicker type="date" placeholder="" v-model="formItem.createTime" format="yyyy-MM-dd" style="width:120px"></DatePicker>
              </FormItem> </td>
              <td> <FormItem prop="inspector" label="检验员：">
                <Input readonly="readonly" v-model="inspector" type="text" style="width:120px"></Input>
              </FormItem></td>
              <td> <FormItem prop="lnvoicingStatus" label="发票开具状态：">
                <Select v-model="formItem.lnvoicingStatus" style="width:200px">
                  <Option value=""></Option>
                  <Option value="1">已开</Option>
                  <Option value="2">未开</Option>
                </Select>
              </FormItem></td>
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
                  :creator="formItem.sales"
                  :purchaseOrderId="this.purchaseOrderId"
                  @on-amount-change="onAmountChange"></Editable>
      </div>
      <table class="savebar" cellpadding="0" cellspacing="10px">
        <tr style="padding-top: 20px">
          <td>
            不合格品原因：
            <Input v-model="formItem.remark" type="textarea" :rows="4" placeholder="不合格品原因..." />
          </td>
        </tr>
        <tr>
          <td style="padding-top: 20px">
            <i-button @click="save" type="info">保存并提交</i-button>
            <i-button @click="goBack">退出</i-button></td>
        </tr>
      </table>
    </Loading>
    <SelProvider ref="selProvider"></SelProvider>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './StandardEditable';
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
        param:{
          wuliaobianma:'',
          caigoubianhao:'',
          jianyandanhao:'',
        },
        inspectionApplyId:'',
        formItem:{
          providerName:'',
          pmapId:'',
          materId:'',
          materName:'',
          spec:'',
          model:'',
          subType:'',
          uses:'',
          price:'',
          afterPrice:'',
          remark:'',
          createTime:page.formatDate(new Date(),'yyyy-MM-dd'),
          sales:this.$user.empInfo.trueName,
        },
        formRules:{
          providerName: [
            { required: true, message: '请选择供货商' }
          ],
        },
        detailList:[],
        list:[],
        oriItem:{
          providerName:'',
          pmapId:'',
          materId:'',
          materName:'',
          spec:'',
          model:'',
          subType:'',
          uses:'',
          price:'',
          afterPrice:'',
          remark:'',
          createTime:page.formatDate(new Date(),'yyyy-MM-dd'),
          sales:this.$user.empInfo.trueName,
        },
        storage:[]
      }
    },
    mounted: function () {
      this.purchaseOrderId = this.$route.query.purchaseOrderId;
        this.inspectionId = this.$route.query.inspectionId;
        this.materId = this.$route.query.materId;
        this.inspectionApplyId = this.$route.query.inspectionApplyId;
       this.inspector=this.formItem.sales;

      this.load();
    },
    computed: {
      pageTitle(){
          return '新建采购不合格品通知单';
      }
    },
    methods: {
      load() {
        this.loading = 1;
        this.$http.post("/api/InspectionSheet/list?purchaseOrderId="+this.purchaseOrderId+"&inspectionId="+this.inspectionId+"&materId="+this.materId+"&inspectionApplyId="+this.inspectionApplyId,{
          purchaseOrderId:this.purchaseOrderId,
          inspectionId:this.inspectionId,
          inspectionApplyId:this.inspectionApplyId,
          materId:this.materId,
        }).then((res) => {

          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              this.formItem=res.data.data.rows[0];
              this.list=res.data.data.rows;
              /*this.oriItem = eval('('+JSON.stringify(res.data.data)+')');
              Object.assign(this.formItem,res.data.data);
              this.list = res.data.data;
              for(var i in this.list){
                this.list[i].afterPrice=0;
                this.list[i].createTime=page.formatDate(new Date(),'yyyy-MM-dd');
              }*/
            }else{
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

      save(){

          var form={

          };
          Object.assign(form,this.list[0].detailList);

          /*console.log(this.list[0].detailList)*/

        console.log(form)
/*console.log(form)*/

        // 提交
        //this.loading = 1;
        var uri = '/api/UnqualifiedProductBill/add';

        this.$http.post(uri, form[0]).then((res) => {
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
          Object.assign(this.formItem,this.oriItem);

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
  /* initNew(){
        Object.assign(this.formItem,{
          pmapId:'',
          materId:'',
          materName:'',
          spec:'',
          model:'',
          subType:'',
          use:'',
          price:'',
          afterPrice:'',
          remark:'',
          createTime:page.formatDate(new Date(),'yyyy-MM-dd'),

        });
        this.formItem.signDate = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.sales = this.$user.empInfo.empId;
          this.formItem.sales = this.$user.empInfo.trueName;
        }
        this.list = [];
        this.list.push(this.$refs.editable.listNewRow());
        this.list.push(this.$refs.editable.listNewRow());
      },*/
</style>
