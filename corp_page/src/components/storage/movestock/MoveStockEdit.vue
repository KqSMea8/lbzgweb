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
      <div class="subheader">
        移库单
      </div>
      <Form ref="form" class="page-form" :model="formItem" :rules="formRules" :label-width="120"> 
      <table cellspacing="0" cellpadding="0">
        <colgroup>
          <col width="33%"> 
          <col width="auto"> 
          <col width="33%">
        </colgroup>
          <td>
            <FormItem prop="" label="移库单号">
              <span v-if="pageFlag==1">系统生成</span>
              <span v-else>{{formItem.stockBillId}}</span>
            </FormItem>
            <FormItem prop="storageId" label="移出仓库">
               <SelStorage v-model="formItem.storageId" :disabled="pageFlag == 2"></SelStorage> 
            </FormItem>
          <td>
            <FormItem prop="billDate" label="单据日期">
              <DatePicker type="date" placeholder="" 
                v-model="formItem.billDate" format="yyyy-MM-dd"  
                :disabled="pageFlag!=1" :readonly="pageFlag!=1" 
                @on-change="formItem.billDate=arguments[0]"
                style="width:120px"></DatePicker>
            </FormItem>
            <FormItem prop="relatedStorageId" label="移入仓库">
               <SelStorage v-model="formItem.relatedStorageId"></SelStorage> 
            </FormItem>
          </td>
          <td>
            <FormItem prop="operId" label="操作类别">
              <SelStockOperType v-model="formItem.operId" :type="3"></SelStockOperType>
            </FormItem>
            <FormItem label="操作人" prop="operator">
            <Input v-model="formItem.operatorName" :readonly="true"></Input>
          </FormItem>
          </td> 
      </table>
      </Form>
    </div>
    <div>
      <div class="subheader">
        单据明细
      </div>
      <Alert v-if="!formItem.storageId">
        请选择仓库
      </Alert>
      <Editable ref="editable"
        :list="list"
        :editable="true"
        :storageId="formItem.storageId"
        :storageId1="formItem.relatedStorageId"
        @on-amount-change="onAmountChange" :style="{display: formItem.storageId?'':'none'}"></Editable>
    </div>
    <table class="savebar" cellpadding="0" cellspacing="0">
      <tr>
        <td class="save" @click="save" v-if="pageFlag<=2">
          保存
        </td> 
        <td class="reset" @click="reset">
          重置
        </td>
        <td></td>
      </tr>
    </table>
    </Loading> 
    <SelInStockApply ref="selInStockApply"></SelInStockApply>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './DetailEditable'; 
  import page from '@/assets/js/page';
  import floatObj from '@/assets/js/floatObj';  
  import SelStorage from '@/components/storage/input/SelStorage'; 
  import SelStockOperType from '@/components/storage/input/SelStockOperType'; 
  import SelDeptInput from '@/components/selcontacts/SelDeptInput'; 
  import SelPersonInput from '@/components/selcontacts/SelPersonInput'; 
  import SelInStockApply from '@/components/purchase/purchase-order-mgr/SelInStockApply'; 
  import pagejs from '@/assets/js/page';

  export default {
    components: {
      Loading,
      LayoutHor,
      Editable, 
      SelStorage,
      SelStockOperType,
      SelDeptInput,
      SelPersonInput,
      SelInStockApply
    },
    data() {
      return {
        loading: 0,
        stockBillId:'',
        pageFlag:1,//1.新建 2.编辑 3.修订
        formItem:{
          stockBillId:'',
          storageId:'',
          relatedStorageId:'',
          operId:'',
          operType:3,
          operSource:1,
          billDate:page.formatDate(new Date(),'yyyy-MM-dd'),
          isWeight:1,
          inStockApplyId:'',
          purchaseOrderId:'',
          outStockApplyId:'',
          purchaseBackOrderId:'',

          department:null,
          departmentName:'',
          proposer:'',
          proposerName:'',
          operator :'',
          operatorName :'',
          receiver:'',
          receiverName:'',
          shipper:'',
          shipperName:'',
          inspector:'',
          inspectorName:'',
          amount:0,
          remark:'',
          status:'', 
          billDate:page.formatDate(new Date(),'yyyy-MM-dd  HH:mm:ss'),
        },
        formRules:{
          storageId: [
            { required: true, message: '请选择移出仓库' }
          ],
          relatedStorageId: [
            { required: true, message: '请选择移入仓库' }
          ],
          operId: [
            { required: true, message: '请选择操作原因' }
          ],
        },
        list:[],  
        oriItem:{},
        storage:[]
      }
    },
    mounted: function () { 
      this.stockBillId = this.$route.query.id;
      if(this.stockBillId){
        this.pageFlag = 2;
        this.load();
      }else{
        this.pageFlag = 1;
        this.initNew();
      }
    },
    computed: {
      pageTitle(){
        if(this.pageFlag == 1){
          return '移库单 - 创建';
        }
        if(this.pageFlag == 2){
          return '移库单 - 编辑';
        }
        if(this.pageFlag == 3){
          return '移库单 - 修订';
        }
      }
    },
    methods: {
      load() {
        this.loading = 1;
        this.$http.post("/api/stock/bill/get?stockBillId=" + this.stockBillId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              this.oriItem = eval('('+JSON.stringify(res.data.data)+')');
              Object.assign(this.formItem,res.data.data);
              this.list = res.data.data.detailList;

              this.formItem.departmentName = this.$args.getArgText('deptList',this.formItem.department);
              this.formItem.proposerName = this.$args.getArgText('empList',this.formItem.proposer);
              this.formItem.receiverName = this.$args.getArgText('empList',this.formItem.receiver);
              this.formItem.operatorName = this.$args.getArgText('empList',this.formItem.operator);

            }else{
              this.$Message.error('订单不存在！');
              this.goBack();
            }
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("操作失败！")
        });
      }, 
      initNew(){
        Object.assign(this.formItem,{
          stockBillId:'',
          storageId:'',
          relatedStorageId:'',
          operId:'',
          operType:3,
          operSource:1,
          billDate:page.formatDate(new Date(),'yyyy-MM-dd HH:mm:ss'),
          isWeight:1,
          inStockApplyId:'',
          purchaseOrderId:'',
          outStockApplyId:'',
          purchaseBackOrderId:'',
          department:null,
          departmentName:'',
          proposer:'',
          proposerName:'',
          operator :'',
          operatorName :'',
          receiver:'',
          receiverName:'',
          shipper:'',
          shipperName:'',
          inspector:'',
          inspectorName:'',
          amount:0,
          remark:'',
          status:'', 
          billDate:page.formatDate(new Date(),'yyyy-MM-dd'),
        });
        this.formItem.signDate = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.operator = this.$user.empInfo.empId;
          this.formItem.operatorName = this.$user.empInfo.trueName;
        }
        this.list = [];
        this.list.push(this.$refs.editable.listNewRow());
        this.list.push(this.$refs.editable.listNewRow());
      }, 
      save(){

        var form = {
          detailList:[]
        };

        Object.assign(form,this.formItem);
        form.signDate = page.formatDate(form.signDate);

        var pass = true;
        this.$refs.form.validate((valid) => {
          pass = valid;
        }) 
        
        if (!pass) {  
          this.$Message.error('验证未通过！');
          return;
        }

        form.detailList = [];
        // 明细
        for(var i = 0;i<this.list.length;i++){
          var item = this.list[i];
          var msg = '明细第 ' + (i+1) + ' 行，';
          if(item.materId != ''){
            if(item.quantity == 0){
              this.$Message.error(msg + '请录入数量');
              return;
            }
            if(item.placeId == ''){
              this.$Message.error(msg + '请录入货位');
              return;
            }
            // 日期格式转换
            item.madeDate = page.formatDate(item.madeDate);
            item.arrivalDate = page.formatDate(item.arrivalDate);
            item.validityDate = page.formatDate(item.validityDate);
            form.detailList.push(item);
          }
        }

        form.billDate = pagejs.formatDate(form.billDate);

        // 提交
        this.loading = 1;
        var uri = '/api/stock/bill/add';
        if(this.pageFlag == 2){
          uri = '/api/stock/bill/update';
        }

        this.$http.post(uri, form).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            this.$Message.info("操作成功！");
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
      reset(){
        if(this.pageFlag == 1){
          this.initNew();
        }else {
          Object.assign(this.formItem,this.oriItem);
        }
      },
      goBack(){
        this.$router.push('/storage/movestock');
      },
      goImport(){
        if(!this.formItem.storageId){
          this.$Message.error("请选择仓库");
          return;
        }
        var sel = this.$refs.selInStockApply;
        sel.show({
          storageId:this.formItem.storageId,
          ok:(select)=>{ 
            if(select){
              this.formItem.inStockApplyId = select.inStockApplyId;
              this.formItem.purchaseOrderId = select.purchaseOrderId;
              this.list = [];
              select.detailList.map((row)=>{ 
                var newRow = this.$refs.editable.listNewRow();
                this.list.push(Object.assign(newRow,row));
              });
            } 
          }
        });
      }
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
