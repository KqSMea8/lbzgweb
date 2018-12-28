<template>
  <div class="page page-bill complaintview">
    <div class="page-bar">
      <LayoutHor>
        <div slot="left">
          <Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>
        </div>
        <div class="page-bar-title">投诉单 - 查看</div>
      </LayoutHor>
    </div>
    <Loading :loading="loading">
    <div class="baseinfo"> 
      <div class="subheader">
        基本信息
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
            <FormItem prop="complaintId" label="投诉单号">
              {{formItem.complaintId}}
            </FormItem>
          </td> 
          <td>
            <FormItem prop="contractId" label="合同编号">
              {{formItem.contractId}}
            </FormItem>
          </td>
          <td>
            <FormItem prop="customerId" label="客户">
              {{formItem.customerName}}
            </FormItem>
          </td>
        </tr>
        <tr>
          <td>
            <FormItem prop="sales" label="业务员">
              {{formItem.salesName}}
            </FormItem>
          </td> 
          <td>
            <FormItem prop="address" label="处置地点">
              {{formItem.address}}
            </FormItem>
          </td> 
          <td>
            <FormItem prop="createTime" label="受理时间">
              {{formItem.createTime}}
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
            <th>金额</th>
            <th><span>金额说明</span></th>
          </thead> 
          <tbody>
            <tr >
              <td >
                {{formItem.materId}}
              </td>  
              <td >
                {{formItem.materName}}
              </td>
              <td >
                {{formItem.drawing}}
              </td>
              <td >
                {{formItem.casting}}
              </td>
              <td >
                {{formItem.texture}}
              </td>
              <td > 
                {{formItem.quantity}}
              </td>
              <td >
                {{formItem.remark}}
              </td>
              <td > 
                {{formItem.amount}}
              </td>
              <td >
                {{formItem.amountRemark}}
              </td>
            </tr>
          </tbody>
        </table>
      </Editable>
      <div class="infocolumn">售后问题描述处理时间等要求</div>
      <div class="remarkbox">
        {{formItem.requireRemark}}
      </div> 
      <div class="infocolumn">处理方案</div>
      <div class="remarkbox">
      {{formItem.solution}}
      </div>

      </Form>
      <div class="subheader">
        流程状态
      </div>
      <Row>
        <Col span="8">
           <table class="flowinfo">
            <tr>
              <th>评审</th>
              <td>
                <span v-if="formItem.status==0">待提交</span>
                <span v-if="formItem.status==1">审核中</span>
                <span v-if="formItem.status==2" style="color:green">通过</span>
                <span v-if="formItem.status==3" style="color:red">驳回</span>
              </td>
            </tr>
            <tr>
              <td></td>
              <td>
                <a @click="goEdit" v-if="formItem.status==0">编辑</a>
                &nbsp;
                <a @click="submitAudit" v-if="formItem.status==0 ||　formItem.status==3">提交</a>
                &nbsp;
              </td>
            </tr>
          </table>
        </Col>
      </Row>
       <table class="savebar" cellpadding="0" cellspacing="0">
        <tr>
          <td class="save" @click="save">
            确定
          </td>
          <td>
          </td>
        </tr>
      </table>
    </div>
    </Loading>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import page from '@/assets/js/page';
  import pagejs from '@/assets/js/page';
  import Editable from '@/components/editable-table';
  import process from '@/components/process-start';

  export default {
    components: {
      Loading,
      LayoutHor,
      Editable
    },
    data() {
      return {
        loading: 0,
        stockBillId:'',
        pageFlag:1,//1.新建 2.编辑 3.修订
        formItem:{
          complaintId:'',
          contractId:'',
          customerId:'',
          customerName:'',
          sales:'',
          salesName:'',
          address:'',
          createTime:page.formatDate(new Date(),'yyyy-MM-dd'),
          requireRemark:'',
          materId:'',
          materName:'',
          drawing:'',
          casting:'',
          texture:'',
          quantity:'',
          linkMan:'',
          linkPhone:'',
          remark :'',
          amount:0,
          amountRemark:'',
          //工作流表单数据
          solution:''
        },
        formRules:{

        },
        list:[],  
        oriItem:{},
        storage:[]
      }
    },
    mounted: function () { 
      this.complaintId = this.$route.query.id;
      if(this.complaintId){
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
          return '投诉单 - 创建';
        }
        if(this.pageFlag == 2){
          return '投诉单 - 编辑';
        }
        if(this.pageFlag == 3){
          return '投诉单 - 修订';
        }
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
              this.oriItem.salesName = this.$args.getArgText('empList',this.oriItem.sales);
              Object.assign(this.formItem,this.oriItem);
              this.loadWf();
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
      loadWf(){
        process.getInstValue(this.formItem.wfInstId,(err,data)=>{
          if(err){ 
            return;
          }
          this.formItem.solution = data.solution;
        });
      },
      initNew(){
        Object.assign(this.formItem,{
          complaintId:'',
          contractId:'',
          customerId:'',
          customerName:'',
          sales:'',
          salesName:'',
          address:'',
          createTime:page.formatDate(new Date(),'yyyy-MM-dd'),
          requireRemark:'',
          materId:'',
          materName:'',
          drawing:'',
          casting:'',
          texture:'',
          quantity:'',
          linkMan:'',
          linkPhone:'',
          remark :'',
          amount:0,
          amountRemark:'',
        });
        this.formItem.signDate = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.sales = this.$user.empInfo.empId;
          this.formItem.salesName = this.$user.empInfo.trueName;
        }
      }, 
      save(){
        var form = {};

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

        // 提交
        this.loading = 1;
        var uri = '/api/complaint/add';
        if(this.pageFlag == 2){
          uri = '/api/complaint/update';
        }

        this.$http.post(uri,form).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            this.goBack();
          } else {
            this.goBack();
            /*this.$Message.error(res.data.message);*/
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新操作")
        });
      },
      goBack(){
        /*this.$router.push('/complaint');*/
        this.$router.go(-1);
      },
      goEdit(){
        this.$router.push({path:'/complaint/edit?id='+ this.complaintId });
      },
      submitAudit(){

        this.$Modal.confirm({
          title: '确认提交审核',
          content: '<p>确定要提交投诉单吗？</p>',
          onOk: () => {
            this.loading = 1;
            process.startComplaintAudit(this.oriItem,(err,instid)=>{
              this.loading = 0;
              if(err){
                this.$Message.error(err.msg);
              }else{
                this.submitAuditCallBack('',instid);
              }
            });
          }
        });
      },
      submitAuditCallBack(type,instid){
        var api = '/api/complaint/submit';
        var data = {complaintId:this.complaintId};
        if(type == ''){
          data.wfInstId = instid;
        }
        this.loading = 1;
        this.$http.post(api,data).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            this.$Message.info('提交成功！');
            this.load();
          } else {
            this.$Message.error(res.data.message);
            this.load();
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("操作失败！")
        });
      },
    }
  }
</script>
<style type="text/css">
  .complaintview .remarkbox{
    border: 1px solid #dedede;
    border-radius: 3px;
    padding: 10px;
    min-height: 80px;
  }

  .complaintview .infocolumn{
    line-height: 30px;
  }
</style>
