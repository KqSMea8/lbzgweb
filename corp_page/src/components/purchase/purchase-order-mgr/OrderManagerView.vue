<template>
  <div class="page purchase-order-view">
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
        <tr class="emptytds">
          <td></td>
          <td width="100"></td>
          <td></td>
          <td width="100"></td>
          <td></td>
          <td width="100"></td>
          <td></td>
          <td width="100"></td>
        </tr>
        <tr>
          <td class="label">订单编号</td>
          <td>{{formItem.purchaseOrderId}}</td>
          <td class="label">申购日期</td>
          <td>
            {{formItem.createTime?formItem.createTime.substring(0,10):''}}
          </td> 
          <td class="label">申请部门</td>
          <td>
            {{formItem.departmentName}}
          </td>
          <td class="label">申请人</td>
          <td>
            {{formItem.proposerName}}
          </td>
        </tr>
        <tr>
          <td class="label">备注</td>
          <td colspan="3">
            {{formItem.remark}}
          </td>
           
          <td class="label">质量要求</td>
          <td colspan="3">
            {{formItem.quality}}
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
        :editable="false"
        @on-amount-change="onAmountChange"></Editable>
    </div>
    <div class="subheader">
      流程状态
    </div>
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
    <table class="savebar" cellpadding="0" cellspacing="0">
      <tr>
        <td class="save" @click="save" v-if="pageFlag<=2">
          确定
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

  export default {
    components: {
      Loading,
      LayoutHor,
      Editable, 
      UploadBox, 
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
      this.load(); 
    },
    computed: {
      pageTitle(){
        if(this.pageFlag == 1){
          return '采购订单维护 - 详情';
        } 
      }
    },
    methods: {
      load() {
        this.loading = 1;
        this.$http.post("/api/order/get?purchaseOrderId=" + this.purchaseOrderId,{}).then((res) => { 
          this.loading = 0;
          if (res.data.code == 0) {
            var data = res.data.data;
            if(data){
              data.departmentName = this.$args.getArgText('deptList',data.department);
              data.proposerName = this.$args.getArgText('empList',data.proposer);

              this.oriItem = eval('('+JSON.stringify(data)+')');
              Object.assign(this.formItem,data);

              this.list = res.data.data.detailList;
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
      save(){
        this.goBack();
      },  
      goEdit(){
        this.$router.push({path:'/purchase-order/edit?id='+ this.purchaseOrderId });
      }, 
      onAmountChange(val){
        this.formItem.amount = val;
      },
      datePickerChange(val){
        this.formItem.signDate = val;
      },
      reset(){
        this.load();
      },
      goBack(){
        this.$router.push('/purchase-order-mgr');
      },
      // 提交评审审核
      submitAudit(){
        this.$Modal.confirm({
          title: '确认提交审核',
          content: '<p>确定提交采购单吗？</p>',
          onOk: () => {
            this.loading = 1;
            process.startPurchaseOrderAudit(this.oriItem,(err,instid)=>{
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
        var api = '/api/order/submit';
        var data = {purchaseOrderId:this.purchaseOrderId};

        if(type == ''){
          data.wfInstId = instid;
        }
        /*
        if(type == 'revision'){
          api = '/api/contract/revision/submit';
        }
        if(type == 'undo'){
          api = '/api/contract/undo/submit';
          data.undoWfInstId = instid;
        }*/

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
  .purchase-order-view.page{
    width: 900px;
    margin: 0 auto;
    padding: 10px 0px;
    position: relative;
  }
  .purchase-order-view .subheader{
    height: 34px;line-height: 34px;
    font-size:14px;
    border-bottom: 0px solid #efefef;
    margin-bottom: 10px;
    color:#20bfee;
  }
  .purchase-order-view .baseinfo{
    font-size: 14px;
  }
  .purchase-order-view .baseinfo .label{
    width: 90px;text-align: right;
    padding-right: 8px;color:#999;
  }
  .purchase-order-view .baseinfo table{
    width: 100%;
  }
  .purchase-order-view .baseinfo table td{
    height: 40px;padding-right:4px;
  }

  .purchase-order-view .baseinfo table .emptytds td{
    height: 0px;padding-right:8px;
  }

  .purchase-order-view .savebar{
    margin-top: 10px;
    height: 40px;
    width: 100%;
    border-collapse: collapse;
  }
  .purchase-order-view .savebar td{
    border: 1px solid #fefefe;
    font-size: 14px;
  }
  .purchase-order-view .savebar .save{
    width: 120px;
    border: 1px solid #20bfee;
    background-color: #20bfee;
    color: white;
    text-align: center;
    cursor: pointer;
  }
  .purchase-order-view .savebar .reset{
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

  .purchase-order-view .tooltip{
    padding:10px;
    background-color: #fafafa;
    border:1px solid #efefef;
    border-radius: 3px;
    color:#666;
    margin-top: 10px;
  }

  .purchase-order-view .flowinfo{
    font-size: 14px;
  }
  .purchase-order-view .flowinfo td,.contractview .flowinfo th{
    padding:4px 10px;
  }
</style>
