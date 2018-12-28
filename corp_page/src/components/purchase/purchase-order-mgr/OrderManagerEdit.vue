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
      <Form :model="formItem" ref="form" :label-width="100" class="form-item" :rules="formRules">
        <td>
          <FormItem label="入库申请单号" prop="inStockApplyId">
            <Input v-model="formItem.inStockApplyId" disabled="disabled" placeholder="由系统自动生成"/>
          </FormItem>
          <FormItem label="申报人" prop="proposer">
            <SelPersonInput v-model="formItem.proposerName" :text="formItem.proposerName" bindText="proposerName" :model="formItem"></SelPersonInput>
          </FormItem>
        </td>
         <td>
          <FormItem label="采购申请编号" prop="purchaseOrderId">
             <Input v-model="formItem.purchaseOrderId" disabled="disabled"></Input>
          </FormItem>
          <FormItem label="申请日期" prop="createTime">
             <DatePicker type="date" placeholder="" v-model="formItem.createTime" format="yyyy-MM-dd" style="width:120px"></DatePicker>
          </FormItem>
          <FormItem prop="departmentName" label="申请部门">
              <SelDeptInput v-model="formItem.department" :text="formItem.departmentName" bindText="departmentName" :model="formItem"></SelDeptInput>
            </FormItem>
        </td>
        <td>
          <FormItem label="操作人" prop="proposer">
             <Input v-model="formItem.proposerName" readonly="readonly" disabled="disabled"/>
          </FormItem>
          <FormItem label="仓库" prop="storageId">
             <SelStorage v-model="formItem.storageId"></SelStorage> 
          </FormItem>
        </td>
      </Form>
    </div>
    <div>
      <div class="subheader">
        物料明细
      </div>
      <Editable ref="editable"
        :list="list"
        :editable="true"
        :isNew="pageFlag==1"
        @on-amount-change="onAmountChange"></Editable>
    </div>
    <table class="savebar" cellpadding="0" cellspacing="0">
      <tr>
        <td class="save" @click="save" v-if="pageFlag<=2">
          保存并提交
        </td> 
        <td class="reset" @click="reset">
          重置
        </td>
        <td></td>
      </tr>
    </table>
    </Loading>
    <SelContacts ref="selContacts"></SelContacts>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './DetailEditable';
  import page from '@/assets/js/page';
  import floatObj from '@/assets/js/floatObj';
  import process from '@/components/process-start';
  import SelContacts from '@/components/selcontacts'; 
  import SelStorage from '@/components/storage/input/SelStorage';
  import SelDeptInput from '@/components/selcontacts/SelDeptInput'; 
  import SelPersonInput from '@/components/selcontacts/SelPersonInput';

  export default {
    components: {
      Loading,
      LayoutHor,
      Editable,
      SelContacts,
      SelStorage,
      SelDeptInput,
      SelPersonInput
    },
    data() {
      return {
        loading: 0,
        purchaseOrderId:'',
        pageFlag:1,//1.新建 2.带入
        formItem:{
          "inStockApplyId":"",
          "purchaseOrderId":"",
          "department":null,
          "departmentName":"",
          "proposer":null,
          "proposerName":"",
          "remark":"",
          "quality":"", 
          "status":0,
          "wfInstId":"",
          "wfInstTime":"",
          "wfInstRemark":null,
          "createTime":page.formatDate(new Date(),'yyyy-MM-dd'),
          "storageId":"",
        },
        formRules: {
          storageId: [
            {required: true, message: '请选择仓库', trigger: 'change'},
          ],
          departmentName: [
            {required: true, message: '请选择申请部门', trigger: 'change'},
          ],
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
      if(this.pageFlag == 1){
        this.initNew();
      }
      if(this.pageFlag == 2){
        this.load();
      }
    },
    computed: {
      pageTitle(){
        if(this.pageFlag == 1){
          return '采购订单维护 - 新建入库申请单';
        }
        if(this.pageFlag == 2){
          return '采购订单维护 - 创建入库申请单';
        }
      }
    },

    methods: {
      load() {
        this.loading = 1;
        var itemDetail= this.$refs.editable;//引用到其他控件，赋给对象
        this.$http.post("/api/order/get?purchaseOrderId=" + this.purchaseOrderId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              this.oriItem = eval('('+JSON.stringify(res.data.data)+')');
              Object.assign(this.formItem,res.data.data);
              this.list =[];
              res.data.data.detailList.map(item=>{
                var row = Object.assign(itemDetail.listNewRow(),item);
                row.buyer = this.$user.empInfo.empId;
                row.buyerName=this.$user.empInfo.trueName;
                this.list.push(row);//该对象引用自己的方法listNewRow();该方法取到的值放入item
              });
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
          "inStockApplyId":"",
          "purchaseOrderId":"",
          "department":null,
          "departmentName":"",
          "proposer":null,
          "proposerName":"",
          "storageId":"",
          "remark":"",
          "quality":"", 
          "status":0,
          "wfInstId":"",
          "wfInstTime":"",
          "wfInstRemark":null,
          "billDate":page.formatDate(new Date(),'yyyy-MM-dd'),
          "createTime":page.formatDate(new Date(),'yyyy-MM-dd'),
        });
        this.formItem.signDate = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.proposer = this.$user.empInfo.empId;
          this.formItem.proposerName = this.$user.empInfo.trueName;
        }
        this.list = [];
        this.list.push(this.$refs.editable.listNewRow());
        this.list.push(this.$refs.editable.listNewRow());
      }, 

      //保存并提交
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
        var uri = '/api/inStockApply/add';

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
        this.$router.push('/purchase-order-mgr');
      }
    }
  }

</script>

