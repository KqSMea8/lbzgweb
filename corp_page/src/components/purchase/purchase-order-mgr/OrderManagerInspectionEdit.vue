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
      <table cellspacing="0" cellpadding="0">
        <tr>
          <td class="label">报检申请单号</td>
          <td><Input v-model="formItem.inspectionApplyId" disabled="disabled" placeholder="由系统自动生成"></Input></td>
          <td class="label">操作人</td>
          <td>
            <Input v-model="formItem.operatorName" readonly="readonly"
            style="cursor: pointer;"  @click.native="selEmp"></Input>
          </td>
          <td class="label">过磅单号</td>
          <td>
            <Input v-model="formItem.guobangId"></Input>
          </td>
        </tr>
         <tr>
          <td class="label">采购申请编号</td>
          <td><Input v-model="formItem.purchaseOrderId"></Input></td>
          <td class="label">申请日期</td>
           <td>
            <DatePicker type="date" placeholder="" v-model="formItem.applyDate" format="yyyy-MM-dd" style="width:120px"></DatePicker>
          </td> 
          <td class="label">申请部门</td>
          <td>
            <Input v-model="formItem.departmentName" readonly="readonly"
            style="cursor: pointer;"  @click.native="selDept"></Input>
          </td>
        </tr>
        <tr>
          <td class="label">申请人</td>
          <td>
            <Input v-model="formItem.proposerName" readonly="readonly"
            style="cursor: pointer;"  @click.native="selEmp"></Input>
          </td>
        </tr>
      </table>
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

  export default {
    components: {
      Loading,
      LayoutHor,
      Editable,
      SelContacts
    },
    data() {
      return {
        loading: 0,
        purchaseOrderId:'',
        pageFlag:1,//1.新建 2.带入
        formItem:{
          "inspectionApplyId":"",
          "purchaseOrderId":"",
          "department":"",
          "departmentName":"",
          "proposer":"",
          "proposerName":"",
          "operator":"",
          "operatorName":"",
          "qualityInspectionId":"",
          "applyDate":"",
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
          return '采购订单维护 - 新建报检申请单';
        }
        if(this.pageFlag == 2){
          return '采购订单维护 - 创建报检申请单';
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
                this.list.push(Object.assign(itemDetail.listNewRow(),item));//该对象引用自己的方法listNewRow();该方法取到的值放入item
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
          "inspectionApplyId":"",
          "purchaseOrderId":"",
          "department":"",
          "departmentName":"",
          "proposer":"",
          "proposerName":"",
          "operator":"",
          "operatorName":"",
          "qualityInspectionId":"",
        });
        this.formItem.applyDate = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.proposer = this.$user.empInfo.empId;
          this.formItem.proposerName = this.$user.empInfo.trueName;
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
              this.formItem.operatorName = sel.select[0].title;
              this.formItem.operator = sel.select[0].key;
            }
          }
        }); 
      },

      //保存并提交
      save(){
        var form = {
          detailList:[]
        };

        Object.assign(form,this.formItem);
        form.applyDate = page.formatDate(form.applyDate);

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
        var uri = '/api/inspectionApply/add';

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

