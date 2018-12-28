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
      <div class="subheader">
        单据表头
      </div>
      <Form ref="form" class="page-form" :model="formItem" :rules="formRules" :label-width="120"> 
      <table cellspacing="0" cellpadding="0">
        <colgroup>
          <col width="33%"> 
          <col width="auto"> 
          <col width="33%">
        </colgroup>
        <tr>
          <FormItem prop="contractId" label="合同编号">
            {{formItem.contractNo}}
          </FormItem>
          <td>
            <FormItem prop="customerId" label="客户名称">
              {{formItem.customerName}}
            </FormItem>
          </td>
          <td>
            <FormItem prop="sales" label="业务员">
              <SelPersonInput v-model="formItem.sales" :text="formItem.salesName" bindText="salesName" :model="formItem"></SelPersonInput>
            </FormItem>
          </td> 
        </tr>
        <tr>
          <td>
            <FormItem prop="amount" label="合同金额">
              {{formItem.amount}}
            </FormItem>
          </td>
          <td>
            <FormItem prop="proposer" label="发货申请人">
              <SelPersonInput v-model="formItem.proposer" :text="formItem.proposerName" bindText="proposerName" :model="formItem"></SelPersonInput>
            </FormItem>
          </td> 
          <td>
            <FormItem prop="deliveryAddress" label="交货地点">
              {{formItem.deliveryAddress}}
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
        :unitArgs="unitArgs"
        :processRequireArgs="processRequireArgs"
        :editable="true"
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
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './DetailEditable';
  import page from '@/assets/js/page';
  import floatObj from '@/assets/js/floatObj';
  import process from '@/components/process-start';
  import UploadBox from '@/components/upload/Index';
  import SelPersonInput from '@/components/selcontacts/SelPersonInput';
  import pagejs from '@/assets/js/page'; 

  export default {
    components: {
      Loading,
      LayoutHor,
      Editable,
      UploadBox,
      SelPersonInput
    },
    data() {
      return {
        loading: 0,
        contractId:'',
        shippingId:'',
        pageFlag:2,//1.新建 2.编辑 3.修订
        formItem:{
          contractId:'',
          proposer:'',
          proposerName:'',
          customerId:'',
          customerName:'',
          currency:'',
          currencyName:'',
          amount:'',
          remark:'',
          deliveryAddress:'',
          sales:'',
          salesName:'',
        },
        list:[],
        unitArgs:[],
        processRequireArgs:[],
        currencyArgs:[],
        oriItem:{},
        remark:'',
        formRules:{
          
        },
      }
    },
    computed: {
      pageTitle(){
        if(this.pageFlag == 1){
          return '发货单 - 创建';
        }
        if(this.pageFlag == 2){
          return '发货单 - 编辑';
        }
        if(this.pageFlag == 3){
          return '发货单 - 修订';
        }
      }
    },
    mounted: function () { 
      if(this.$route.query.id){
        this.pageFlag = 2;
        this.shippingId = this.$route.query.id;
        this.load();
      }else{
        this.pageFlag = 1;
        this.initNew();
        this.contractId = this.$route.query.contractId;
        if(this.contractId){
          this.loadContract();
        }
      }
    },
    methods: {
      load() {
        this.loading = 1;
        this.$http.post("/api/shipping/get?shippingId=" + this.shippingId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              Object.assign(this.formItem,res.data.data);
              this.list = res.data.data.detailList;
              this.oriItem = res.data.data;

              this.formItem.proposer = this.$user.empInfo.empId;
              this.formItem.proposerName = this.$user.empInfo.trueName;

              this.formItem.sales = this.$user.empInfo.empId;
              this.formItem.salesName = this.$user.empInfo.trueName;
            }else{
              this.$Message.error('合同不存在！');
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
      loadContract() {
        this.loading = 1;
        this.$http.post("/api/contract/get?contractId=" + this.contractId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              this.oriItem = eval('('+JSON.stringify(res.data.data)+')');
              Object.assign(this.formItem,res.data.data);
              this.list = [];  
              res.data.data.detailList.map((item)=>{
                this.list.push(Object.assign({
                  id:null,
                  shippingId:'', 
                  materId:'',
                  materName:'', 
                  drawing:'',
                  casting:'',
                  texture:'',
                  hardness:'',
                  unit:'',
                  weight:0,
                  weightTotal:0,
                  price:0,
                  amount:0,
                  quantity:0,
                  remark :'',
                },item,{id:null}));
              });
            }else{
              this.$Message.error('合同不存在！');
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
      loadArgs(){
        this.loadArg('process_require',(err,data)=>{
          if(err){
            this.$Message.error('加载参数出错：'+err);
          }else{
            this.processRequireArgs = data;
          }
        });
        this.loadArg('currency',(err,data)=>{
          if(err){
            this.$Message.error('加载参数出错：'+err);
          }else{
            this.currencyArgs = data;
          }
        });
        this.loadArg('unit',(err,data)=>{
          if(err){
            this.$Message.error('加载参数出错：'+err);
          }else{
            this.unitArgs = data;
          }
        });
      },
      loadArg(group,callback){
        this.$http.post("/api/arg/list", {argGroup:group}).then((res) => {
          if (res.data.code === 0) {
            callback(null,res.data.data);
          } else {
            callback(res.data.message);
          }
        }).catch((error) => {
          callback("请求失败!");
        });
      },
      initNew(){
        Object.assign(this.formItem,{
          contractId:'',
          proposer:'',
          proposerName:'',
          customerId:'',
          customerName:'',
          currency:'',
          currencyName:'',
          amount:'',
          remark:'',
          deliveryAddress:'',
          sales:'',
          salesName:'',
        });
        this.formItem.signDate = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.proposer = this.$user.empInfo.empId;
          this.formItem.proposerName = this.$user.empInfo.trueName;

          this.formItem.sales = this.$user.empInfo.empId;
          this.formItem.salesName = this.$user.empInfo.trueName;
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
        var uri = '/api/shipping/add';
        if(this.pageFlag == 2){
          uri = '/api/shipping/update';
        }

        this.$http.post(uri, form).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            this.$Message.info("保存成功！");
            if(this.pageFlag == 1){
              this.$router.replace('/shipping/view?id='+res.data.data);
            }else{
              this.goBack();
            } 
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
        this.$router.go(-1);
      }
    }
  }

</script>

