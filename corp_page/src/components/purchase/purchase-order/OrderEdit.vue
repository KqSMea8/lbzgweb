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
      <table cellspacing="0" cellpadding="0">
        <tr>
          <td class="label">订单编号</td>
          <td><Input v-model="formItem.purchaseOrderId" disabled="disabled"></Input></td>
          <td class="label">申购日期</td>
          <td>
            <DatePicker type="date" placeholder="" v-model="formItem.createTime" format="yyyy-MM-dd" style="width:120px"></DatePicker>
          </td> 
          <td class="label">申请部门</td>
          <td>
            <Input v-model="formItem.departmentName" @click.native="selDept" readonly="readonly" style="cursor: pointer;"></Input>
          </td>
          <td class="label">申请人</td>
          <td>
            <Input v-model="formItem.proposerName" readonly="readonly"
            style="cursor: pointer;"  @click.native="selEmp"></Input>
          </td>
        </tr>
        <tr>
          <td class="label">备注</td>
          <td colspan="3">
            <Input v-model="formItem.remark"></Input>
          </td>
           
          <td class="label">质量要求</td>
          <td colspan="3">
            <Input v-model="formItem.quality"></Input>
          </td>
        </tr>
      </table>
    </div>
    <div>
      <div class="subheader">
        单据明细
      </div>
      <Editable ref="editable"
        :list="list"
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
    <SelectCustomer ref="selectCustomer"></SelectCustomer>
    <SelContacts ref="selContacts"></SelContacts>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './DetailEditable';
  import SelectCustomer from '@/components/customer/SelectCustomer';
  import page from '@/assets/js/page';
  import floatObj from '@/assets/js/floatObj';
  import process from '@/components/process-start';
  import UploadBox from '@/components/upload/Index';
  import SelContacts from '@/components/selcontacts'; 

  export default {
    components: {
      Loading,
      LayoutHor,
      Editable,
      SelectCustomer,
      UploadBox,
      SelContacts
    },
    data() {
      return {
        loading: 0,
        purchaseOrderId:'',
        pageFlag:1,//1.新建 2.编辑 3.修订
        formItem:{
          "purchaseOrderId":"",
          "department":"",
          "departmentName":"",
          "proposer":"",
          "proposerName":"",
          "remark":"",
          "quality":"", 
          "status":0,
          "wfInstId":"",
          "wfInstTime":"",
          "wfInstRemark":null,
        },
        list:[], 
        currencyArgs:[],
        oriItem:{},
        remark:'',
      }
    },
    mounted: function () { 
      this.purchaseOrderId = this.$route.query.id;
      this.pageFlag = 1;
      if(this.purchaseOrderId){
        this.pageFlag = 2;
      }
      if(this.$route.query.reversion){
        this.pageFlag = 3;
      }
      if(this.pageFlag == 1){
        this.initNew();
      }
      if(this.pageFlag == 2){
        this.load();
      }
      if(this.pageFlag == 3){
        this.load();
      }
    },
    computed: {
      pageTitle(){
        if(this.pageFlag == 1){
          return '采购订单 - 创建';
        }
        if(this.pageFlag == 2){
          return '采购订单 - 编辑';
        }
        if(this.pageFlag == 3){
          return '采购订单 - 修订';
        }
      }
    },
    methods: {
      load() {
        this.loading = 1;
        this.$http.post("/api/order/get?purchaseOrderId=" + this.purchaseOrderId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              this.oriItem = eval('('+JSON.stringify(res.data.data)+')');
              Object.assign(this.formItem,res.data.data);
              this.list = res.data.data.detailList;
              this.formItem.departmentName = this.$args.getArgText('deptList',this.formItem.department);
              this.formItem.proposerName = this.$args.getArgText('empList',this.formItem.proposer);
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
          "purchaseOrderId":"",
          "department":"",
          "departmentName":"",
          "proposer":"",
          "proposerName":"",
          "remark":"",
          "quality":"", 
          "status":0,
          "wfInstId":"",
          "wfInstTime":"",
          "wfInstRemark":null,
          "createTime":page.formatDate(new Date(),'yyyy-MM-dd'),
        });
        this.formItem.signDate = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.proposer = this.$user.empInfo.empId;
          this.formItem.proposerName = this.$user.empInfo.trueName;
          this.formItem.department = this.$user.empInfo.deptId;
          this.formItem.departmentName = this.$args.getArgText('deptList',this.$user.empInfo.deptId);
        }
        this.list = [];
        this.list.push(this.$refs.editable.listNewRow());
        this.list.push(this.$refs.editable.listNewRow());
      },
      selDept(){
        var sel = this.$refs.selContacts;
        sel.show({
          isMulti:false,
          selectPerson:false,
          selectDept:true,
          ok:()=>{ 
            if(sel.select.length>0){
              this.formItem.departmentName = sel.select[0].title;
              this.formItem.department = sel.select[0].key;
            }
          }
        }); 
      },
      selEmp(){
        var sel = this.$refs.selContacts;
        sel.show({
          isMulti:false,
          selectPerson:true,
          selectDept:false,
          ok:()=>{ 
            if(sel.select.length>0){
              this.formItem.proposerName = sel.select[0].title;
              this.formItem.proposer = sel.select[0].key;
            }
          }
        }); 
      },
      save(){

        var form = {
          detailList:[]
        };

        Object.assign(form,this.formItem);
        form.signDate = page.formatDate(form.signDate);

        // 表头信息
        if(form.department == null || form.department == ''){
          this.$Message.error('请选择部门');
          return;
        }

        form.detailList = [];
        // 明细
        for(var i = 0;i<this.list.length;i++){
          var item = this.list[i];
          if(item.materId != ''){
            if(item.quantity == 0){
              this.$Message.error('物料 ' + item.materId + ' ,请录入数量');
              return;
            }
            item.needDate = page.formatDate(item.needDate);
            form.detailList.push(item);
          }
        }

        // 提交
        this.loading = 1;
        var uri = '/api/order/add';
        if(this.pageFlag == 2){
          uri = '/api/order/update';
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
      onDrawingInput(item){
        if(this.formItem.customerId == null || this.formItem.customerId == ''){
          this.$Message.error('请选择客户！');
          return;
        }
        var materId = this.formItem.customerId + '-' + item.drawing;

        this.$http.post('/api/material/get?materId=' +materId, {}).then((res) => {
          if (res.data.code === 0 && res.data.data !=null) {
            item.materName = res.data.data.materName;
            item.unit = res.data.data.unit;
          }
        }).catch((error) => {
          this.$Message.error(error.message)
        });
      },
      onAmountChange(val){
        this.formItem.amount = val;
      },
      datePickerChange(val){
        this.formItem.signDate = val;
      },
      reset(){
        if(this.pageFlag == 1){
          this.initNew();
        }else {
          Object.assign(this.formItem,this.oriItem);
        }
      },
      goBack(){
        this.$router.push('/purchase-order');
      }
    }
  }

</script>

