<template>
  <ListPage ref="page" title="付款申请管理" api="/api/providerPayApply/list" 
  :model="this" @onCurrentRowChange="curRowChg" :beforeLoad="beforeLoad">   
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <RadioGroup v-model="queryForm.status" type="button" @on-change="query">
              <Radio :label="0">待提交</Radio>
              <Radio :label="1">审核中</Radio>
              <Radio :label="2">通过</Radio>
              <Radio :label="3">驳回</Radio>
            </RadioGroup>
          </td>
          <td class="page-tools">
            <Button @click="add" v-power="'cggl.fksq.add'" icon="plus">新建</Button>
            &nbsp;
            <Button @click="edit" v-power="'cggl.fksq.edit'" icon="edit" :disabled="queryForm.status!=0">编辑</Button> 
            &nbsp;
            <Button @click="sendAudit" v-power="'cggl.fksq.audit'" icon="checkmark" :disabled="queryForm.status!=0">送审</Button> 
            &nbsp;
            <Button @click="del" v-power="'cggl.fksq.del'" icon="android-delete" :disabled="queryForm.status!=0">删除</Button>
          </td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <Input v-model="queryForm.providerName" placeholder="供货商名称" ></Input>
          </td>
          <td>创建日期</td>
          <td><DatePicker type="daterange" v-model="queryForm.createTime"
           split-panels placeholder="创建日期"
           style="width: 180px" :clearable="false" ::transfer="true"></DatePicker></td>
          <td>
            <Button @click="query"  type="primary" icon="ios-search">查询</Button>
          </td>
          <td>
            <Button @click="reset" >重置</Button>
          </td>
        </tr>
      </table>
    </div>
  </ListPage> 
    
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import SelStockOperType from '@/components/storage/input/SelStockOperType'; 
  import Editable from '@/components/editable-table';
  import page from '@/assets/js/page';
  import process from '@/components/process-start';
    
  export default {
    components: { 
      ListPage,
      SelStockOperType,
      Editable
    },
    data() {
      let that = this;
      return {
        curRowId:'',
        columns: [
          {
            type: 'selection',
            width: 60,
            align: 'center',
            fixed: 'left',
          }, 
          {
            title:'#', 
            fixed: 'left',
            width:50,
            align:'center',
            render:(h,params)=>{
              return h('span', params.index + 1);
            }
          },
          {
            title: '付款申请编号',
            key: 'paId',
            width: 140,
            fixed: 'left',
            render:(h,params)=>{
              var row = params.row;
              var text = row.paId;
              text = text;
              return h('a',{
                props:{

                },
                on:{
                  click:()=>{
                    this.$router.push({path:'/provider-payapply/view?id='+row.paId});
                  }
                }
              },text);
            }
          },
          {
            title: '供应商名称',
            key: 'providerName',
            width: 200,
            fixed: 'left',
          },
          {
            title: '供货商代码',
            key: 'providerId',
            align: 'left',
            width:120,
          }, 
          {
            title: ' ',
          }, 
          {
            title: '申请费用',
            key: 'amount',
            align: 'right',
            width: 120,
          },
          page.table.initDateColumn({
            title: '申请日期',
            key: 'createTime'
          }),
          {
            title: '合同编号',
            key: 'orderId',
            align: 'center',
            width: 140,
          },
          page.table.initMapColumn({
            title: '状态',
            key: 'status',
            width: 120,
            data:{
              '0':'待提交',
              '1':'审核中',
              '2':'通过',
              '3':'驳回',
            }
          }),
        ],  
        queryForm: {
          providerName:'',
          status:0,
          createTime:null,
        },
        loading: 0
      } 
    },
    mounted: function () {
      this.reset();
    },
    methods: {
      query() { 
        this.$refs.page.query();
      },
      beforeLoad(){
        var queryParam = this.$refs.page.queryParam;
        queryParam.createTimeStart = '';
        queryParam.createTimeEnd = '';
        delete queryParam.createTime;
        if(this.queryForm.createTime.length > 0){
          queryParam.createTimeStart = page.formatDate(this.queryForm.createTime[0]);
        }
        if(this.queryForm.createTime.length > 1){
          queryParam.createTimeEnd = page.formatDate(this.queryForm.createTime[1]);
        }
      }, 
      reset() {
        Object.assign(this.queryForm,{
          providerName:'',
          status:0,
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.query();
      },
      curRowChg(row){
        if(row!=null){
          this.curRowId = row.paId;
        }else{
          this.curRow = null;
          this.curRowId = null;
        }
      },
      add() { 
        this.$router.push({path:'/provider-payapply/edit?forward'})
      },
      edit(){
        if(this.curRowId){
          this.$router.push({
            path:'/provider-payapply/edit?forward&id=' + this.curRowId
          })
        }else{
          this.$Message.error('请选择要编辑的付款单');
        }
      },
      sendAudit(){
        var selection = this.$refs.page.getSelection();
        if(selection.length == 0){
          this.$Message.error('请选择要操作的数据行');
          return;
        }
        var start = 0;
        var handle = ()=>{
          if(start >= selection.length){
            return;
          }
          var item = selection[start];
          this.submitAudit(item,(err)=>{
            if(err){
              this.$refs.page.load();
              this.$Message.error(err);
            }else{
              start ++ ;
              if(start < selection.length){
                handle();
              }else{
                this.$refs.page.load();
              }
            } 
          }); 
        }
        handle();
      },
      del(){
         this.$Modal.confirm({
            title: '删除确认',
            content: '<p>删除后不能恢复，确定删除已选择的记录吗？</p>',
            onOk: () => {
              var aid = this.curRowId;
              this.loading = 1;
              this.$http.post('/api/providerPayApply/delete?paId=' + aid, {}).then((res) => {
                this.loading = 0;
                if (res.data.code === 0) {
                  this.$Message.success("删除成功");
                  this.$refs.page.load();
                } else {
                  this.$Message.error(res.data.message)
                }
              }).catch((error) => {
                this.loading = 0;
                this.$Message.error(error.toString())
              });
              this.query();
            }
          });
      },
      goPage(page){
        this.$router.push({path:page});
      },
      submitAudit(order,callback){
        process.providerPayApply(order,(err,instid)=>{ 
            if(err){
              callback(err);
            }else{
              var data = {wfInstId:instid};
              data.paId = order.paId;
              this.submitAuditCallBack(data,callback);
            }
        });
      },
      submitAuditCallBack(data,callback){ 
        var api = '/api/providerPayApply/submit';  
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
    }
  }

</script>

<style type="text/css">
  
</style>
