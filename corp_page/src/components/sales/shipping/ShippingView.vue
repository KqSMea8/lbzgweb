<template>
  <div class="page page-bill">
    <div class="page-bar">
      <LayoutHor>
        <div slot="left">
          <Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>
        </div>
        <div class="page-bar-title">发货单 - 查看</div>
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
          <td style="width:220px;"></td>
          <td></td>
          <td style="width:220px;"></td>
          <td></td>
          <td style="width:220px;"></td>
        </tr>
        <tr>
          <td class="label">合同编号</td>
          <td>{{formItem.contractNo}}</td>
          <td class="label">客户</td>
          <td>{{formItem.customerName}}</td>
          <td class="label">业务员</td>
          <td>{{formItem.salesName}}</td>
        </tr>
        <tr>
          <td class="label">合同金额</td>
          <td style="color:red">{{formItem.amount}}</td>
          <td class="label">发货申请人</td>
          <td>{{formItem.proposerName}}</td>
          <td class="label">交货地点</td>
          <td>{{formItem.deliveryAddress}}</td>
        </tr>
        <tr>
          <td class="label">备注</td>
          <td>{{formItem.remark}}</td>
        </tr>
      </table>
    </div>
    <div class="subheader">
      单据明细
    </div>
    <DetailEditable :list="list" :unitArgs="unitArgs"
                    :processRequireArgs="processRequireArgs"></DetailEditable>
    <div class="subheader">
      流程状态
    </div>
    <Row>
      <Col span="8">
         <table class="flowinfo">
          <tr>
            <th>审核</th>
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
              <a @click="submitAudit" v-if="formItem.status==0">提交</a>
              &nbsp;
            </td>
          </tr>
        </table>
      </Col>
    </Row>
    <table class="savebar" cellpadding="0" cellspacing="0">
      <tr>
        <td class="save" @click="ok">
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
  import DetailEditable from './DetailEditable'; 
  import dateFormat from '@/assets/js/dateFormat';
  import process from '@/components/process-start';
  import UploadBox from '@/components/upload/Index';

  export default {
    filters: {
      formatDate(time) {
        var date = new Date(time);
        return dateFormat(date, 'yyyy-MM-dd');
      }
    },
    components: {
      Loading,
      LayoutHor,
      DetailEditable,
      UploadBox
    },
    data() {
      return {
        loading: 0,
        contractId:'',
        formItem:{
          shippingId:this.$route.query.id,
          contractId:'',
          proposer:'',
          proposerName:'',
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
        processRequireArgs:this.$args.argMap['process_require'],
        oriItem:{},
        isNew:true,
      }
    },
    mounted: function () {
      this.shippingId = this.$route.query.id;
      this.load();
    },
    computed: {},
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
              this.oriItem.proposerName = this.$user.empInfo.trueName;
              this.oriItem.salesName = this.$user.empInfo.trueName;

              this.formItem.proposer = this.$user.empInfo.empId;
              this.formItem.proposerName = this.$user.empInfo.trueName;

              this.formItem.sales = this.$user.empInfo.empId;
              this.formItem.salesName = this.$user.empInfo.trueName;
            }else{
              this.$Message.error('发货单不存在！');
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
      submitAudit(){
        this.$Modal.confirm({
          title: '确认提交审核',
          content: '<p>确定提交发货单审核吗？</p>',
          onOk: () => {
            this.loading = 1;
            process.invoice(this.oriItem,(err,instid)=>{ 
            if(err){
              this.$Message.error(err);
            }else{
              var data = {wfInstId:instid};
              data.shippingId = this.formItem.shippingId;

              this.submitAuditCallBack(data,(err)=>{
                if(err)
                {
                  this.$Message.error(err);
                }else{
                  this.$Message.info('操作成功');
                  this.load();
                }
              });
            }
          });
          }
        });
      },
      submitAuditCallBack(data,callback){ 
        var api = '/api/shipping/submit';   
        this.loading = 1;
        this.$http.post(api,data).then((res) => {
          this.loading = 0;
          if(res.data.code == 0){
            callback(null); 
          }else{
            callback(res.data.message);
          } 
        }).catch((error) => { 
          this.loading = 0;
          callback('提交失败！'); 
        });
      },
      goEdit(){
        this.$router.push({path:'/shipping/edit?forward&id=' + this.shippingId});
      },
      ok(){
        this.goBack();
      },
      reset(){
        this.load();
      },
      goBack(){
        this.$router.go(-1);
      }
    }
  }

</script>

