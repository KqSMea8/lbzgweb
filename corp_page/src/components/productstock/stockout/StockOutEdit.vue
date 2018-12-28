<template>
  <div class="page page-bill">
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
              <Button @click="goImport">导入销售发货单</Button>
              </td>
          </tr>
        </table> 
      </div>
      <div class="subheader">
        产成品出库
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
            <FormItem prop="contractId" label="合同编号">
                <Input v-model="formItem.contractId" icon="android-search" @on-click="onSelectContract"></Input>
            </FormItem>
          </td>
          <td>
            <FormItem prop="billDate" label="单据日期">
              <DatePicker type="date" placeholder="" 
                v-model="formItem.billDate" format="yyyy-MM-dd"  
                :disabled="pageFlag!=1" :readonly="pageFlag!=1" 
                @on-change="formItem.billDate=arguments[0]"
                style="width:120px"></DatePicker>
            </FormItem>
          </td>
          <td>
            <FormItem prop="inspector" label="检验员">
              <SelPersonInput v-model="formItem.inspector" :text="formItem.inspectorName" bindText="inspectorName" :model="formItem"></SelPersonInput>
            </FormItem>
          </td>
        </tr>
        <tr>
          <td>
            <FormItem label="客户" prop="customerId">
              {{formItem.customerName}}
            </FormItem>
          </td>
          <td>
            <FormItem prop="operator" label="操作人">
              <SelPersonInput v-model="formItem.operator" :text="formItem.operatorName" bindText="operatorName" :model="formItem"></SelPersonInput>
            </FormItem>
          </td>
          <td>
            <FormItem prop="shipper" label="发货人">
              <SelPersonInput v-model="formItem.shipper" :text="formItem.shipperName" bindText="shipperName" :model="formItem"></SelPersonInput>
            </FormItem>
          </td> 
        </tr>
        <tr> 
          <td>
            <FormItem prop="stockBillId" label="出库单号">
              <span v-if="pageFlag==1">系统生成</span>
              <span v-else>{{formItem.stockBillId}}</span>
            </FormItem>
          </td>
          <td>
            <FormItem prop="deliveryAddress" label="交货地点">
              {{formItem.deliveryAddress}}
            </FormItem>
          </td>          
          <td>
            <FormItem prop="proposer" label="业务员">
              <SelPersonInput v-model="formItem.proposer" :text="formItem.proposerName" bindText="proposerName" :model="formItem"></SelPersonInput>
            </FormItem>
          </td>
        </tr>
        <tr>
          <td rowspan="2" style="vertical-align: top;">
            <FormItem prop="remark" label="备注">
              <Input v-model="formItem.remark" type="textarea" :rows="3"></Input>
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
        :customerId="formItem.customerId"
        @on-amount-change="onAmountChange"></Editable> 
      
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
    <SelShipping ref="selShipping"></SelShipping>
    <SelectContract ref="selcontact" ></SelectContract>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './DetailEditable'; 
  import page from '@/assets/js/page';
  import floatObj from '@/assets/js/floatObj';
  import SelStockOperType from '@/components/storage/input/SelStockOperType'; 
  import SelDeptInput from '@/components/selcontacts/SelDeptInput'; 
  import SelPersonInput from '@/components/selcontacts/SelPersonInput'; 
  import SelShipping from '@/components/sales/shipping/SelShipping'; 
  import pagejs from '@/assets/js/page';
  import SelectContract from '@/components/sales/contract/SelectContract';

  export default {
    components: {
      Loading,
      LayoutHor,
      Editable,
      SelStockOperType,
      SelDeptInput,
      SelPersonInput,
      SelShipping,
      SelectContract
    },
    data() {
      return {
        loading: 0,
        stockBillId:'',
        pageFlag:1,//1.新建 2.编辑 3.修订
        formItem:{
          stockBillId:'',
          storageId:100,
          customerId:'',
          customerName:'',
          contractId:'',
          contractNo:'',
          operId:'',
          operType:2,
          operSource:1,
          billDate:page.formatDate(new Date(),'yyyy-MM-dd'),
          proposer:'',
          proposerName:'',
          sales:'',
          salesName:'',
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
          weight:'',
          deliveryAddress:'',
        },
        formRules:{
          operId: [
            { required: true, message: '请选择操作原因' }
          ],
        },
        list:[],  
        oriItem:{},
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
          return '产成品出库 - 创建';
        }
        if(this.pageFlag == 2){
          return '产成品出库 - 编辑';
        }
        if(this.pageFlag == 3){
          return '产成品出库 - 修订';
        }
      }
    },
    methods: {
      load() {
        this.loading = 1;
        this.$http.post("/api/productStockBill/get?stockBillId=" + this.stockBillId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              this.oriItem = eval('('+JSON.stringify(res.data.data)+')');
              Object.assign(this.formItem,res.data.data);
              this.list = res.data.data.detailList;

              this.formItem.departmentName = this.$args.getArgText('deptList',this.formItem.department);
              this.formItem.proposerName = this.$args.getArgText('empList',this.formItem.proposer);
              this.formItem.shipperName = this.$args.getArgText('empList',this.formItem.shipper);
              this.formItem.operatorName = this.$args.getArgText('empList',this.formItem.operator);
              this.formItem.inspectorName = this.$args.getArgText('empList',this.formItem.inspector);

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
          storageId:100,
          customerId:'',
          customerName:'',
          contractId:'',
          contractNo:'',
          operType:2,
          operSource:1,
          billDate:page.formatDate(new Date(),'yyyy-MM-dd'),
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
          weight:'',
          deliveryAddress:'',
        });

        this.formItem.signDate = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.operator = this.$user.empInfo.empId;
          this.formItem.operatorName = this.$user.empInfo.trueName;
          this.formItem.shipper = this.$user.empInfo.empId;
          this.formItem.shipperName = this.$user.empInfo.trueName;
        }
        if(this.$refs.editable){
          this.list = [];
          this.list.push(this.$refs.editable.listNewRow());
          this.list.push(this.$refs.editable.listNewRow());
        }
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
        var uri = '/api/productStockBill/add';
        if(this.pageFlag == 2){
          uri = '/api/productStockBill/update';
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
      onSelectContract(){
        this.$refs.selcontact.open();
        this.$refs.selcontact.$off('on-ok');
        this.$refs.selcontact.$on('on-ok',(items)=>{
          if(items.length > 0){
            this.formItem.contractId = items[0].contractId;
            this.formItem.contractNo = items[0].contractNo;
            this.formItem.customerId = items[0].customerId;
            this.formItem.customerName = items[0].customerName;
            this.formItem.deliveryAddress = items[0].deliveryAddress;
            this.formItem.proposerName = items[0].salesName;
          }
        });
      },
      goBack(){
        this.$router.push('/productstock/stockout');
      },
      goImport(){
        var sel = this.$refs.selShipping;
        sel.show({
          ok:(select)=>{ 
            if(select){
              this.formItem.shippingOrder = select.shippingId;
              this.formItem.contractNo = select.contractNo;
              this.formItem.contractId = select.contractId;
              this.formItem.deliveryAddress = select.deliveryAddress;
              this.formItem.customerId = select.customerId;
              this.formItem.customerName = select.customerName;
              this.formItem.proposer = select.sales;
              this.formItem.proposerName = this.$args.getArgText('empList',select.sales);
              this.list = [];
              select.detailList.map((row)=>{ 
                var newRow = this.$refs.editable.listNewRow();
                this.list.push(Object.assign(newRow,row,{id:null}));
              });
            } 
          }
        });
      }
    }
  }

</script>

