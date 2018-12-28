<template>
  <ListPage ref="page" api="/api/inspectionApply/list" 
  :model="this" @onCurrentRowChange="curRowChg" :beforeLoad="beforeLoad">
    <div class="page-title" slot="page-title">
    报检申请单驳回记录
    </div> 
   
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
         
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
        </tr>
      </table>
    </div>

    <ListPageDetail ref="detail" slot="page-datatable-detail"
    api="/api/inspectionApply/get?inspectionApplyId="
    :columns="columns1">
    </ListPageDetail> 

  </ListPage> 
    
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import ListPageDetail from '@/components/page/ListPageDetail';
  
  import page from '@/assets/js/page';
    
  export default {
    components: { 
      ListPage,
      ListPageDetail,
      // SelStorage,
      // SelStockOperType
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
            title: '报检单号',
            key: 'inspectionApplyId',
            width: 140,
            fixed: 'left',
            render:(h,params)=>{
              var row = params.row;
              var text = row.inspectionApplyId;
              text = text;
              return h('a',{
                props:{

                },
                on:{
                  click:()=>{
                    //this.$router.push({path:'/contract/view?id='+row.inStockId});
                  }
                }
              },text);
            }
          },
          {
            title: '采购申请单',
            key: 'purchaseOrderId',//purchase_order_id   relatedBillId
            align: 'center',
            width: 120
          }, 
          page.table.initDateColumn({
            title: '申请日期',
            key: 'applyDate',
          }),
         
          page.table.initPersonColumn({
            title: '申请人',
            key: 'proposer',
          }),
          page.table.initDepartmentColumn({
            title: '申请部门',
            key: 'department',
          }), 
         
          page.table.initDateColumn({
            title: '创建日期',
            key: 'createTime',
          }),
          {
            title: '驳回原因',
            key: 'status',
            align: 'left',
            width: 120,
          },
          page.table.initDateColumn({
            title: '驳回日期',
            key: 'status',
          }),
          {
            title: '备注',
            key: 'status',
            align: 'left',
            width:100,
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
        ],
        columns1: [
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
            title: '物料代码',
            key: 'materId',
            fixed: 'left',
            align: 'left',
            width: 120,
          },
          {
            title: '物料名称',
            key: 'materName',
            fixed: 'left',
            align: 'left',
            width: 120,
          },
          {
            title: '规格',
            key: 'spec',
            align: 'center',
            width: 120,
          },
          {
            title: '型号',
            key: 'model',
            align: 'center',
            width: 120,
          },
          {
            title: '批次号',
            key: 'materId',
            align: 'center',
            width: 120,
          },
          page.table.initDateColumn({
            title: '生产日期',
            key: 'madeDate',
          }),
          {
            title: '货位',
            key: 'placeName',
            align: 'left',
            width:150
          },
          {
            title: '数量',
            key: 'quantity',
            align: 'left',
            width:150
          },
          page.table.initDateColumn({
            title: '到厂日期',
            key: 'arrivalDate',
          }), 
          page.table.initDateColumn({
            title: '有效日期',
            key: 'validityDate',
          }), 
          page.table.initDateColumn({
            title: '质保期',
            key: 'qualityTime',
          }), 
          {
            title: '单位',
            key: 'unit',
            align: 'left',
            width:150
          },
          {
            title: '单价',
            key: 'price',
            align: 'left',
            width:150
          },
          {
            title: '金额',
            key: 'amount',
            align: 'right',
            width:100,
          }, 
          {
            title: '备注',
            key: 'amount',
            align: 'left',
            width:100,
          },
        ], 
        queryForm: {
          // storageId:'',
          status:3,
          // operId:'', 
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
          // inStockId:'',
          status:3,
          // operId:'', 
          createTime:""/*[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]*/
        });
        this.query();
      },
      curRowChg(row){
        if(row!=null){
           this.curRow = row;
           this.curRowId = row.inspectionApplyId;
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
     
      goPage(page){
        this.$router.push({path:page});
      }
    }
  }

</script>

<style type="text/css">
  
</style>
