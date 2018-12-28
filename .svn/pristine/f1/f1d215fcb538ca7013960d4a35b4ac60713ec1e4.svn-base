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
      <Form ref="form" class="page-form" :model="formItem" :rules="formRules" :label-width="120"> 
      <table cellspacing="0" cellpadding="0">
        <colgroup>
          <col width="25%"> 
          <col width="25%">
          <col width="25%">
          <col width="25%">
        </colgroup>
        <tr>
          <td>
            <FormItem prop="disposeId" label="处置单号">
              <Input v-model="formItem.disposeId" :readonly="true"></Input>
            </FormItem>
          </td> 
          <td>
            <FormItem prop="contractId" label="合同编号">
              <Input v-model="formItem.contractId" :readonly="true"></Input>
            </FormItem>
          </td>
          <td>
            <FormItem prop="customerId" label="客户">
              <Input v-model="formItem.customerName" :readonly="true"></Input>
            </FormItem>
          </td>
          <td>
            <FormItem prop="sales" label="业务员">
              <Input v-model="formItem.salesName" :readonly="true"></Input>
            </FormItem>
          </td>
        </tr>
        <tr>
          <td>
            <FormItem prop="address" label="处置地点">
              <Input v-model="formItem.address"></Input>
            </FormItem>
          </td> 
          <td>
            <FormItem prop="firstDeliver" label="初次交付时间">
              <DatePicker type="date" placeholder="" 
                v-model="formItem.firstDeliver" format="yyyy-MM-dd"
                @on-change="formItem.firstDeliver=arguments[0]"
                style="width:120px"></DatePicker>
            </FormItem>
          </td>
          <td>
            <FormItem prop="linkMan" label="联系人">
              <Input v-model="formItem.linkMan"></Input>
            </FormItem>
          </td>
          <td>
            <FormItem prop="telphone" label="联系电话">
              <Input v-model="formItem.linkPhone"></Input>
            </FormItem>
          </td>
        </tr>
      </table>
      <Editable>
      <table cellpadding="0" cellspacing="0">
        <thead>
          <th >物料编码</th>
          <th >物料名称</th>
          <th >图号</th>
          <th >铸件号</th>
          <th >材质</th> 
          <th >件数</th>
          <th><span>备注</span></th>
        </thead> 
        <tbody>
          <tr >
            <td >
              <Input type="text" v-model="formItem.materId" :readonly="true" disabled/>
            </td>  
            <td >
              <Input type="text" v-model="formItem.materName" :readonly="true" disabled/>
            </td>
            <td >
              <Input type="text" v-model="formItem.drawing" :readonly="true" disabled/>
            </td>
            <td >
              <Input type="text" v-model="formItem.casting" />
            </td>
            <td >
              <Input type="text" v-model="formItem.texture" :readonly="true" disabled/>
            </td>
            <td class="col-quantity"> 
              <Input type="text" v-model="formItem.quantity" :readonly="true" disabled/>
            </td>
            <td class="col-remark">
              <Input type="text" v-model="formItem.remark" />
            </td>
          </tr>
        </tbody>
      </table>
    </Editable>
      <table  cellpadding="0" cellspacing="0">
        <tr>
          <td>售后问题描述处理时间等要求</td>
        </tr>
        <tr border="1">
          <td  >
                   <Input   type="text"  v-model="formItem.requireRemark" :readonly="true" disabled/>
          </td>
        </tr>
      </table>
      </Form>
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
  import page from '@/assets/js/page';
  import floatObj from '@/assets/js/floatObj';
  import process from '@/components/process-start';
  import UploadBox from '@/components/upload/Index';
  import pagejs from '@/assets/js/page'; 
  import Editable from '@/components/editable-table';

  export default {
    components: {
      Loading,
      LayoutHor,
      Editable
    },
    data() {
      return {
        loading: 0,
        complaintId:'',
        customerId:'',
        pageFlag:2,//1.新建 2.编辑 3.修订
        formItem:{
          disposeId:'',
          contractId:'',
          customerId:'',
          customerName:'',
          sales:'',
          salesName:'',
          address:'',
          firstDeliver:page.formatDate(new Date(),'yyyy-MM-dd'),
          linkMan:'',
          linkPhone:'',
        },
        list:[],
        oriItem:{},
        formRules:{
          
        },
      }
    },
    computed: {
      pageTitle(){
        if(this.pageFlag == 1){
          return '处置单 - 创建';
        }
        if(this.pageFlag == 2){
          return '处置单 - 编辑';
        }
        if(this.pageFlag == 3){
          return '处置单 - 修订';
        }
      }
    },
    mounted: function () {
        this.pageFlag = 1;
        this.initNew();
        this.complaintId = this.$route.query.complaintId;
        if(this.complaintId){
          this.load();
        }

    },
    methods: {
      load() {
        this.loading = 1;
        this.$http.post("/api/complaint/get?complaintId=" + this.complaintId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              this.oriItem = eval('('+JSON.stringify(res.data.data)+')');
              Object.assign(this.formItem,res.data.data);
              this.list = [];
              if(this.formItem.customerId){
                this.loadCustomer();
              }  
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
      loadCustomer() {
        this.loading = 1;
        this.$http.post("/api/customer/get?customerId=" + this.formItem.customerId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              this.formItem.linkMan = res.data.data.linkMan;
              this.formItem.linkPhone = res.data.data.linkPhone;
            }else{
              this.$Message.error('客户不存在！');
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
          complaintId:'',
          disposeId:'',
          contractId:'',
          customerId:'',
          customerName:'',
          sales:'',
          salesName:'',
          address:'',
          firstDeliver:page.formatDate(new Date(),'yyyy-MM-dd'),
          linkMan:'',
          linkPhone:'',
        });
        this.formItem.signDate = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.proposer = this.$user.empInfo.empId;
          this.formItem.proposerName = this.$user.empInfo.trueName;

          this.formItem.sales = this.$user.empInfo.empId;
          this.formItem.salesName = this.$user.empInfo.trueName;
        }
      },
      save(){
        var form = {};

        Object.assign(form,this.formItem);
        form.firstDeliver = page.formatDate(form.firstDeliver);

        var pass = true;
        this.$refs.form.validate((valid) => {
          pass = valid;
        }) 
        
        if (!pass) {  
          this.$Message.error('验证未通过！');
          return;
        }

        // 提交
        this.loading = 1;
        var uri = '/api/complaintDispose/add';
        if(this.pageFlag == 2){
          uri = '/api/complaintDispose/update';
        }
        this.$http.post(uri,form).then((res) => {
          if (res.data.code == 0) {
            this.$Message.info("操作成功！");
            this.$router.replace('/disposition/view?id='+res.data.data);
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
        this.load();
      },
      goBack(){
        this.$router.go(-1);
      },
    }
  }

</script>

<style type="text/css">
   .remarkbox{
    border: 1px solid #dedede;
    border-radius: 3px;
    padding: 10px;
    min-height: 80px;
  }
</style>

