<template>
  <div class="page page-bill">
    <div class="page-bar">
      <LayoutHor>
        <div slot="left">
          <Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>
        </div>
        <div class="page-bar-title">合同 - 查看</div>
      </LayoutHor>
    </div>
    <Loading :loading="loading">
    <div class="baseinfo">
      <div class="subheader">
        单据表头
      </div>
      <table cellspacing="0" cellpadding="0">
        <tr>
          <td class="label">合同编号</td>
          <td>{{formItem.contractNo}}</td>
          <td class="label">客户</td>
          <td>{{formItem.customerName}}</td>
          <td></td>
          <td>
            <Tag v-if="formItem.hasProcedure==1" color="green">含加工工序</Tag>
            <Tag v-else>含加工工序</Tag>
            <Tag v-if="formItem.isForeign==1" color="green">是否外销</Tag>
            <Tag v-else>是否外销</Tag>
          </td>
        </tr>
        <tr>
          <td class="label">业务员</td>
          <td>{{formItem.salesName}}</td>
          <td class="label">签订日期</td>
          <td>{{formItem.signDate}}</td>
          <td class="label">签订地点</td>
          <td>{{formItem.signAddress}}</td>
        </tr>
        <tr>
          <td class="label">合同金额</td>
          <td style="color:red">{{formItem.amount}}</td>
          <td class="label">币种</td>
          <td>{{formItem.currencyName}}</td>
          <td class="label">交货地点</td>
          <td>{{formItem.deliveryAddress}}</td>
        </tr>
        <tr> 
          <td class="label">备注</td>
          <td>{{formItem.remark}}</td>
          <td class="label">录入日期</td>
          <td>{{formItem.createTime | formatDate}}</td>
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
      <Col span="8">
         <table class="flowinfo">
          <tr>
            <th>修定</th>
            <td>
              <span v-if="formItem.revisionStatus==0">--</span>
              <span v-if="formItem.revisionStatus==1">审核中</span>
              <span v-if="formItem.revisionStatus==2" style="color:green">通过</span>
              <span v-if="formItem.revisionStatus==3" style="color:red">驳回</span>
            </td>
          </tr>
          <tr>
            <td></td>
            <td>
              <a @click="submitRevisionAudit" v-if="
              formItem.status== 2
              && formItem.revisionStatus!=1">修订</a>
              &nbsp;
            </td>
          </tr>
        </table>
      </Col>
      <Col span="8">
         <table class="flowinfo">
          <tr>
            <th>撤销</th>
            <td>
              <span v-if="formItem.undoStatus==0">--</span>
              <span v-if="formItem.undoStatus==1">审核中</span>
              <span v-if="formItem.undoStatus==2" style="color:green">通过</span>
              <span v-if="formItem.undoStatus==3" style="color:red">驳回</span>
            </td>
          </tr>
          <tr>
            <td></td>
            <td>
              <a @click="submitUndoAudit" v-if="formItem.undoStatus==0 ||　formItem.undoStatus==3">撤销</a>
              &nbsp;
            </td>
          </tr>
        </table>
      </Col>
    </Row>
    <div class="subheader" v-if="false">
      进度
    </div>
    <Card v-if="false">
    <Steps :current="0">
        <Step title="创建" content=""></Step>
        <Step title="审核" content=""></Step>
        <Step title="工艺设计" content=""></Step>
        <Step title="模型制作" content=""></Step>
        <Step title="生产" content=""></Step>
        <Step title="入库" content=""></Step>
        <Step title="发货" content=""></Step>
        <Step title="完成" content=""></Step>
    </Steps>
    </Card>
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
    <RemarkInput ref="remarkInput"></RemarkInput>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import DetailEditable from './DetailEditable'; 
  import dateFormat from '@/assets/js/dateFormat';
  import process from '@/components/process-start';
  import RemarkInput from './RemarkInput';
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
      RemarkInput,
      UploadBox
    },
    data() {
      return {
        loading: 0,
        contractId:'',
        formItem:{
          contractId:'',
          customerId:'',
          customerName:'',
          inDate:'',
          isInquire:0,
          hasProcedure:0,
          isForeign:0,
          isCraftDesign:0,
          sales:'',
          salesName:'',
          remark:'',
          designer:'',
          completeTime:'',
        },
        list:[],
        unitArgs:[],
        processRequireArgs:this.$args.argMap['process_require'],
        oriItem:{},
        isNew:true,
      }
    },
    mounted: function () {
      this.contractId = this.$route.query.id;
      this.load();
    },
    computed: {},
    methods: {
      load() {
        this.loading = 1;
        this.$http.post("/api/contract/get?contractId=" + this.contractId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              Object.assign(this.formItem,res.data.data);
              this.list = res.data.data.detailList;
              this.oriItem = res.data.data;
              //添加交货类型名称，审批流需要交货类型名称
              this.list.forEach(item =>{
                let deliveryTypeName = this.$args.getArgText("process_require", item.deliveryType);
                this.oriItem.deliveryTypeName = deliveryTypeName;
              })
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
      // 提交评审审核
      submitAudit(){
        this.$Modal.confirm({
          title: '确认提交审核',
          content: '<p>确定提交合同审核吗？</p>',
          onOk: () => {
            this.loading = 1; 
            this.$http.post('/api/contract/submit',this.oriItem).then((res) => {
              this.loading = 0;
              if (res.data.code == 0) {
                this.$Message.success('提交成功！');
                this.load();
              } else {
                this.$Message.error(res.data.message);
                this.load();
              }
            }).catch((error) => {
              this.loading = 0;
              this.$Message.error("操作失败！")
            });
          }
        });
      },
      submitAuditCallBack(type,data){
        var api = '/api/contract/submit'; 

        if(type == ''){
          
        }
        if(type == 'revision'){
          api = '/api/contract/revision/submit';
        }
        if(type == 'undo'){
          api = '/api/contract/undo/submit'; 
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
      // 提交撤销审核
      submitUndoAudit(){
        this.$refs.remarkInput.open();
        this.$refs.remarkInput.$off('on-ok');
        this.$refs.remarkInput.$on('on-ok',(text)=>{
          this.loading = 1;
          var data = {contractId:this.oriItem.contractId,remark:text};
          this.$http.post('/api/contract/undo/submit',data).then((res) => {
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
        })
      },
      // 提交修订审核
      submitRevisionAudit(){
        this.$router.push({path:'/contract/edit?reversion=1&id='+ this.contractId });
      },
      goEdit(){
        this.$router.push({path:'/contract/edit?id='+ this.contractId });
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
