<template>
  <ListPage ref="page" title="调价申请管理" api="/api/pack/query" 
  :model="this" @onCurrentRowChange="curRowChg" :beforeLoad="beforeLoad">  
    <div class="page-title" slot="page-title">
      装箱单管理
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
            <Input v-model="queryForm.packId" placeholder="装箱单号" ></Input>
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
    <ListPageDetail ref="detail" slot="page-datatable-detail"
    api="/api/pack/get?packId="
    :columns="columns1" 
    :objId="curRowId">
    </ListPageDetail> 
  </ListPage> 
    
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import ListPageDetail from '@/components/page/ListPageDetail';
  import SelStockOperType from '@/components/storage/input/SelStockOperType'; 
 
  import page from '@/assets/js/page';
  import process from '@/components/process-start';
    
  export default {
    components: { 
      ListPage,
      ListPageDetail,
      SelStockOperType
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
          }, 
          {
            title:'#', 
            width:50,
            align:'center',
            render:(h,params)=>{
              return h('span', params.index + 1);
            }
          },
          {
            title: '装箱单号',
            key: 'packId',
            width: 140,
          },
          {
            title: '操作人',
            key: 'operator',
            align: 'center',
            width:100,
          },
          {
            title: '创建日期',
            key: 'createTime',
            width: 140,
          },
          {
            title: '驳回原因',
            key: 'remark',
            align: 'center',
          },
          page.table.initDateColumn({
            title: '驳回日期',
            key: 'updateTime',
          }),
          {
            title: '备注',
            key: 'remark',
            align: 'center',
          },
        ], 
        columns1: [
          {
            title:'#', 
            width:50,
            align:'center',
            render:(h,params)=>{
              return h('span', params.index + 1);
            }
          },
          {
            title: '物料名称',
            key: 'materName',
            width: 140,
          },
          {
            title: '合同号',
            key: 'contractId',
            align: 'center',
            width: 120,
          },
          {
            title: '图号',
            key: 'drawing',
            align: 'center',
            width: 120,
          },
          {
            title: '材质',
            key: 'drawing',
            align: 'center',
            width: 120,
          },
          {
            title: '铸造号',
            key: 'casting',
            align: 'center',
            width: 120,
          },
          {
            title: '条码',
            key: 'barcode',
            align: 'center',
            width: 100,
          },
          {
            title: '批号',
            key: 'batch',
            align: 'center',
            width: 160,
          },
          {
            title: '箱号',
            key: 'box',
            align: 'center',
            width: 100,
          },
          {
            title: '数量',
            key: 'quantity',
            align: 'center',
            width: 100,
          },
          {
            title: '备注',
            key: 'remark',
            align: 'center',
          },
        ], 
        queryForm: {
          //packId:'',
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
          operId:'', 
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.query();
      },
      curRowChg(row){
        if(row!=null){
           this.curRow = row;
           this.curRowId = row.packId;
           this.$refs.detail.load(this.curRowId);
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
        this.$router.push({path:'/pack/edit?forward'})
      },
      edit(){
        if(this.curRowId){
          this.$router.push({
            path:'/pack/edit?forward&packId=' + this.curRowId
          })
        }else{
          this.$Message.error('请选择要编辑的调价单');
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
            if(this.curRow){
            this.loading = 1;
            this.$http.post('/api/pack/del',{
              packId:this.curRow.packId,
            }).then((res) => {
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
        }
        });
      },
      goPage(page){
        this.$router.push({path:page});
      },
      submitAudit(order,callback){
        process.startAudit(order,(err,instid)=>{ 
            if(err){
              callback(err);
            }else{
              var data = {wfInstId:instid};
              data.packId = order.packId;
              this.submitAuditCallBack(data,callback);
            }
        });
      },
      submitAuditCallBack(data,callback){ 
        var api = '/api/providerAdjustPrice/submit';  
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
