<template>
  <div class="page page-bill">
    <div class="page-bar">
      <LayoutHor>
        <div slot="left">
          <Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>
        </div>
        <div class="page-bar-title">新材料 - 详情</div>
      </LayoutHor>
    </div>
    <Loading :loading="loading">
    <div class="baseinfo">
      <table cellspacing="0" cellpadding="0">
        <tr>
          <td class="label">供应商</td>
          <td>{{formItem.providerName}}</td>
          <td class="label">供应商编码</td>
          <td>{{formItem.providerId}}</td>
          <td class="label">操作日期</td>
          <td>{{formItem.submitTime}}</td>
        </tr>
        <tr>
          <td class="label">地址</td>
          <td>{{formItem.address}}</td>
          <td class="label">联系人</td>
          <td>{{formItem.linkMan}}</td>
          <td class="label">联系电话</td>
          <td>{{formItem.linkPhone}}</td>
        </tr>
        <tr>
          <td class="label">采购员</td>
          <td>{{this.$args.getArgText('empList',formItem.buyer)}}</td>
          <td class="label">操作人</td>
          <td>{{this.$args.getArgText('empList',formItem.submiter)}}</td>
        </tr>
        <tr>
          <td class="label">备注</td>
          <td>{{formItem.remark}}</td>
        </tr> 
      </table>
    </div>
    <div class="subheader">
      物料明细
    </div>
    <ProviderNewMatEditable :list="list" :editable="false"></ProviderNewMatEditable> <!-- 物料表单页面 -->
    <br>

    <div class="subheader">
      流程状态
    </div>
    <Row>
      <Col span="8">
         <table class="flowinfo">
          <tr>
            <th>反馈</th>
            <td>
              <span v-if="formItem.reportStatus==1">待反馈</span>
              <span v-if="formItem.reportStatus==2">已反馈</span>
            </td>
          </tr>
          <tr>
            <th>工艺员</th>
            <td>
              <span  v-if="formItem.reportStatus==2">{{this.$args.getArgText('empList',formItem.reporter)}}
               , {{formItem.submitTime}}
              </span>
              &nbsp;
            </td>
          </tr>
        </table>
      </Col>

      <Col span="8">
         <table class="flowinfo">
          <tr>
            <th>评定单</th>
            <td>
              <span v-if="formItem.reportStatus!=2" >--</span>
              <span v-if="formItem.wfStatus==0 && formItem.reportStatus==2" >待创建</span>
              <span v-if="formItem.wfStatus==1" >已提交</span>
              <span v-if="formItem.wfStatus==2" style="color:green" >通过</span>
              <span v-if="formItem.wfStatus==3" style="color:red" >驳回</span>
            </td>
          </tr>
          <tr>
            <td></td>
            <td>
              <a  v-if="formItem.wfStatus==0 && formItem.reportStatus==2" @click="submitAudit">创建</a>
            
              
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
  import ProviderNewMatEditable from './ProviderNewMatEditable';
  import dateFormat from '@/assets/js/dateFormat';
  import process from '@/components/process-start';

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
      ProviderNewMatEditable,//物料明细的组件
    },
    data() {
      return {
        loading: 0,
        npmId:'',
        formItem:{
          providerName:'',
          providerId:'',
          submitTime:'',
          address:'',
          linkMan:'',
          linkPhone:'',
          buyer:'',
          submiter:'',
          remark:'',
          reportStatus:null,
          wfStatus:null,
          reporter:'',
        },
        list:[],
        oriItem:{},
      }
    },
    mounted: function () {
      this.npmId = this.$route.query.id;//路由返回的 row.npmId值
      this.load();
    },
    computed: {},
    methods: {
      load() {
        this.loading = 1;
        this.$http.get('/api/newProviderMaterial/get?newProviderMaterialId=' + this.npmId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              Object.assign(this.formItem,res.data.data);
              this.list = res.data.data.detailList;
              this.oriItem = res.data.data;
            }else{
              this.$Message.error('新材料单不存在！');
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

     //编辑新材料页面
      goEdit(){
        this.$router.push({path:'/provider-newmaterial/edit?id='+ this.npmId });
      },

      ok(){
        this.goBack();
      },
      reset(){
        this.load();
      },
      goBack(){ 
        this.$router.go(-1);
      },

      // 提交评审审核
      submitAudit(){
        this.$Modal.confirm({
          title: '确认提交审核',
          content: '<p>确定提交合格供应商评定单吗？</p>',
          onOk: () => {
            this.loading = 1;
            process.startNewMaterProviderAudit(this.oriItem,(err,instid)=>{
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
        var api = '/api/newProviderMaterial/submit';
        var data = {npmId:this.npmId};

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

