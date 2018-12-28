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
        <table cellpadding="0" cellspacing="0">
          <tr>
            <td>
              <Button @click="goImport">导入采购单</Button>
            </td>
          </tr>
        </table> 
      </div>
      <div class="subheader">
        入库单
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
            <FormItem prop="storageId" label="入往仓库">
               <SelStorage v-model="formItem.storageId"></SelStorage> 
            </FormItem>
          </td> 
          <td>
            <FormItem prop="department" label="申请部门">
              <SelDeptInput v-model="formItem.department" :text="formItem.departmentName" bindText="departmentName" :model="formItem"></SelDeptInput>
            </FormItem>
          </td>
          <td>
            <FormItem prop="operator" label="操作人">
              <Input v-model="formItem.operatorName" :readonly="true"></Input>
            </FormItem>
          </td>
        </tr>
        <tr>
          <td>
            <FormItem prop="operId" label="操作原因">
              <SelStockOperType v-model="formItem.operId"></SelStockOperType>
            </FormItem>
          </td> 
          <td>
            <FormItem prop="proposer" label="申请人">
              <SelPersonInput v-model="formItem.proposer" :text="formItem.proposerName" bindText="proposerName" :model="formItem"></SelPersonInput>
            </FormItem>
          </td> 
          <td>
            <FormItem prop="operateTime" label="单据日期">
              <DatePicker type="date" placeholder="" 
                v-model="formItem.billDate" format="yyyy-MM-dd"  
                :disabled="pageFlag!=1" :readonly="pageFlag!=1" 
                @on-change="formItem.billDate=arguments[0]"
                style="width:120px"></DatePicker>
            </FormItem>
          </td>
        </tr>
        <tr> 
          <td></td>
          <td>
            <FormItem prop="receiver" label="收货人">
              <SelPersonInput v-model="formItem.receiver" :text="formItem.receiverName" bindText="receiverName" :model="formItem"></SelPersonInput>
            </FormItem>
          </td> 
          <td>
            <FormItem prop="" label="入库单号">
              <span v-if="pageFlag==1">系统生成</span>
              <span v-else>{{formItem.stockBillId}}</span>
            </FormItem>
          </td>
        </tr>
        <tr> 
          <td>
            <FormItem prop="" label="采购单">
              <span v-if="formItem.purchaseOrderId">
                {{formItem.purchaseOrderId}}
              </span>
              <span v-else></span>
            </FormItem>
          </td> 
          <td>
            <FormItem prop="amount" label="总金额(元)">
              {{formItem.amount}}
            </FormItem>
          </td> 
          <td rowspan="2" style="vertical-align: top;">
            <FormItem prop="remark" label="备注">
              <Input v-model="formItem.remark" type="textarea" :rows="3"></Input>
            </FormItem>
          </td>
        </tr>
        <tr> 
          <td>
             
          </td>
          <td>
            
          </td>
        </tr>
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
        @on-amount-change="onAmountChange"
        :style="{display: formItem.storageId?'':'none'}"></Editable>
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
    <SelectOrder ref="selectOrder"></SelectOrder>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './WeightDetailEditable'; 
  import page from '@/assets/js/page';
  import floatObj from '@/assets/js/floatObj';  
  import SelStorage from '@/components/storage/input/SelStorage'; 
  import SelStockOperType from '@/components/storage/input/SelStockOperType'; 
  import SelDeptInput from '@/components/selcontacts/SelDeptInput'; 
  import SelPersonInput from '@/components/selcontacts/SelPersonInput'; 
  import SelectOrder from '@/components/purchase/purchase-order/SelectOrder';
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
      SelectOrder
    },
    data() {
      return {
        loading: 0,
        stockBillId:'',
        pageFlag:1,//1.新建 2.编辑 3.修订
        formItem:{
          stockBillId:'',
          storageId:'',
          operId:'',
          operType:1,
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
        },
        formRules:{
          storageId: [
            { required: true, message: '请选择仓库' }
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
          return '过磅入库单 - 创建';
        }
        if(this.pageFlag == 2){
          return '过磅入库单 - 编辑';
        }
        if(this.pageFlag == 3){
          return '过磅入库单 - 修订';
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
          operId:'',
          operType:1,
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
        });
        this.formItem.signDate = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.operator = this.$user.empInfo.empId;
          this.formItem.operatorName = this.$user.empInfo.trueName;

          this.formItem.receiver = this.$user.empInfo.empId;
          this.formItem.receiverName = this.$user.empInfo.trueName;
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
        this.$router.push('/storage/instock');
      },
      goImport(){
        var sel = this.$refs.selectOrder;
        sel.show({
          isMulti:false,
          selectPerson:false,
          selectDept:true,
          ok:(select)=>{ 
            if(select){
              this.formItem.inStockApplyId = '';
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
