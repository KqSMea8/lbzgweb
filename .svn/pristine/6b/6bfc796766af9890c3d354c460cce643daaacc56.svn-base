<template>
  <ListPage ref="page" title="产成品入库" api="/api/productStockIn/list" 
  :model="this" @onCurrentRowChange="curRowChg" :beforeLoad="beforeLoad">  
   <div class="page-title" slot="page-title">
      <a @click="goPage('/productstock/mgr')">产成品库</a> -&gt;产成品入库
    </div> 
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <Input v-model="queryForm.contractId" placeholder="合同编号" ></Input>
          </td>
          <td>
            <Input v-model="queryForm.drawing" placeholder="图号" ></Input>
          </td>
          <td>
            <Input v-model="queryForm.casting" placeholder="铸件号" ></Input>
          </td>
          <td>开始日期</td>
          <td><DatePicker type="daterange" v-model="queryForm.createTime"
           split-panels placeholder="开始日期"
           style="width: 180px" :clearable="false" ::transfer="true"></DatePicker></td>
          <td>
            <Button @click="query"  type="primary" icon="ios-search">查询</Button>
          </td>
          <td>
            <Button @click="reset" >重置</Button>
          </td>
          <td class="page-tools"><div class="split"></div></td>
          <td>
            <Button @click="add" v-power="'ckgl.ccprk.add'" icon="plus">新建</Button>
          </td>
        </tr>
      </table>
    </div>
    <Edit ref="edit" @on-save="load"></Edit>
  </ListPage> 
    
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import page from '@/assets/js/page';
  import process from '@/components/process-start';
  import Edit from './StockInEdit';
    
  export default {
    components: { 
      ListPage,
      Edit,
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
          }, /*
          {
            title:'#', 
            align: 'left',
            width:50,
            align:'center',
            render:(h,params)=>{
              return h('span', params.index + 1);
            }
          },*/
          {
            title: '合同编号',
            key: 'contractId',
            align: 'left',
            width:200,
          },
          {
            title: '客户',
            key: 'customerName',
            align: 'left',
            width:200,
          },
          {
            title: '图号',
            key: 'drawing',
            width: 120,
            align: 'left',
          },
          {
            title: '铸件号',
            key: 'casting',
            align: 'left',
            width: 120,
          },
          {
            title: '材质',
            key: 'texture',
            align: 'left',
            width: 100,
          },
          {
            title: '数量',
            key: 'quantity',
            align: 'right',
            width: 100,
          },
          {
            title: '重量',
            key: 'totalWeight',
            align: 'right',
            width: 100,
          },
          {
            title: '仓库',
            key: 'storageId',
            align: 'left',
            width: 120,
          },
          page.table.initDateColumn({
            title: '操作日期',
            key: 'createTime',
            align:'left',
          }),
          page.table.initPersonColumn({
            title: '业务员',
            key: 'proposer',
            align: 'left',
          }),
        ],  
        queryForm: {
          contractId:'',
          drawing:'',
          casting:'',
          createTime:null,
        },
        loading: 0
      } 
    },
    mounted: function () {
      this.reset();
    },
    methods: {
      load(){
        this.$refs.page.load();
      },
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
          contractId:'',
          drawing:'',
          casting:'',
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.query();
      },
      curRowChg(row){
        if(row!=null){
          this.curRowId = row.stockBillId;
          this.curRowId = row.paId;
        }else{
          this.curRow = null;
          this.curRowId = null;
        }
      },
      add() { 
        this.$refs.edit.open({customerId:''});
        this.reset();
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
      goPage(page){
        this.$router.push({path:page});
      },
    }
  }

</script>

<style type="text/css">
  
</style>
