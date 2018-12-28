<template>
  <ListPage ref="page" title="外委合同加工" api="/api/outcontract/query" 
  :model="this" @onCurrentRowChange="curRowChg" :beforeLoad="beforeLoad">  
    <div class="page-title" slot="page-title">
      外委合同加工
    </div> 
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
            <Button @click="add" v-power="'cggl.tjsq.add'" icon="plus">新建</Button>
            &nbsp;
            <Button @click="edit" v-power="'cggl.tjsq.edit'" icon="edit" :disabled="queryForm.status!=0">编辑</Button> 
            &nbsp;
            <Button @click="sendAudit" v-power="'cggl.tjsq.audit'" icon="checkmark" :disabled="queryForm.status!=0">送审</Button> 
            &nbsp;
            <Button @click="del" v-power="'cggl.tjsq.del'" icon="android-delete" :disabled="queryForm.status!=0">删除</Button>
          </td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <Input v-model="queryForm.contractId" placeholder="外委合同编号" ></Input>
          </td>
          <td>
            <Input v-model="queryForm.partB" placeholder="承揽人" ></Input>
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
  import page from '@/assets/js/page';
  import process from '@/components/process-start';
    
  export default {
    components: {
      ListPage,
      SelStockOperType
    },
    data() {
      let that = this;
      return {
        curRowId:'',
        columns: [
          {
            title:'序号', 
            width:60,
            align:'center',
            render:(h,params)=>{
              return h('span', params.index + 1);
            }
          },
          {
            title: '外委加工合同编号',
            key: 'contractId',
            width: 140,
            render:(h,params)=>{
              var row = params.row;
              var text = row.contractId;
              text = text;
              return h('a',{
                props:{

                },
                on:{
                  click:()=>{
                    this.$router.push({path:'/outcontract/view?forward&id='+row.contractId});
                  }
                }
              },text);
            }
          },
          {
            title: '承揽人',
            key: 'partB',
            align: 'center',
            width:200,
          },
          {
            title: '签订时间',
            key: 'signDate',
            width: 140,
          },
          {
            title: '签订地点',
            key: 'signAddress',
            align: 'center',
            width: 140,
          },
          {
            title: '总金额',
            key: 'amount',
            align: 'center',
            width: 140,
          },
          page.table.initMapColumn({
            title: '状态',
            key: 'status',
            data:{
              '0':'待提交',
              '1':'审核中',
              '2':'通过',
              '3':'驳回',
            }
          }),
          page.table.initDateColumn({
            title: '驳回原因',
            key: 'wfInstRemark',
            render:(h,params)=>{
              return h(null);
            }
          }),
          page.table.initDateColumn({
            title: '审核时间',
            key: 'wfInstTime',
            render:(h,params)=>{
              return h(null);
            }
          }),
        ],
        queryForm: {
          status:0,
          createTime:null,
          contractId:'',
          partB:''
        },
        loading: 0,
        curRow: {},
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
          contractId:'',
          partB:'',
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.query();
      },
      curRowChg(row){
        if(row!=null){
           this.curRow = row;
           this.curRowId = row.contractId;
           //this.$refs.detail.load(this.curRowId);
        }else{
          this.curRow = null;
          this.curRowId = null;
          this.$refs.detail.clear();
        }
      },
      switStorage(){
        this.reset();
      }, 
      add() { 
        this.$router.push({path:'/outcontract/edit'})
      },
      edit(){
        if(this.curRowId){
          this.$router.push({
            path:'/outcontract/edit?forward&id=' + this.curRowId
          })
        }else{
          this.$Message.error('请选择要编辑的调价单');
        }
      },
      sendAudit(){
        var that = this;
        var handle = ()=>{
          this.submitAudit(that.curRow,(err)=>{
            if(err){
              this.$refs.page.load();
              this.$Message.error(err);
            }else{
              this.$refs.page.load();
            } 
          }); 
        }
        if(that.curRow){
          handle();
        }
      },
      del(){
        var that = this;
        this.$Modal.confirm({
          title: '删除确认',
          content: '<p>删除后不能恢复，确定删除已选择的记录吗？</p>',
          onOk: () => {
            if(that.curRow){
              that.loading = 1;
              this.$http.post('/api/outcontract/del',{
                contractId:that.curRow.contractId,
              }).then((res) => {
                that.loading = 0;
                if (res.data.code === 0) {
                  that.$Message.success("删除成功");
                  that.$refs.page.load();
                } else {
                  that.$Message.error(res.data.message)
                }
              }).catch((error) => {
                that.loading = 0;
                that.$Message.error(error.toString())
              });
              that.query();
          }
        }
        });
      },
      goPage(page){
        this.$router.push({path:page});
      },
      submitAudit(order,callback){
        this.$http.post("/api/outcontract/get?contractId=" + order.contractId,{}).then((res) => {
          if (res.data.code == 0) {
            if(res.data.data){
              order = res.data.data;
              order.detailList = res.data.data.outContractDetailList;
              process.startOutContractAudit(order,(err,instid)=>{
                if(err){
                  callback(err);
                }else{
                  var data = {wfInstId:instid};
                  data.contractId = order.contractId;
                  this.submitAuditCallBack(data,callback);
                }
              });
            }else{
              this.$Message.error('数据不存在！');
              this.goBack();
            }
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.$Message.error("操作失败！")
        });
      },
      submitAuditCallBack(data,callback){
        var api = '/api/outcontract/submit';
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
