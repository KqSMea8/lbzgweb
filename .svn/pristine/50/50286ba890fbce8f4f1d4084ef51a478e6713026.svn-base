<template>
  <ListPage ref="page" title="销售发货单" api="/api/shipping/list" 
  :model="this" @onCurrentRowChange="curRowChg" :beforeLoad="beforeLoad">
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <RadioGroup v-model="queryForm.status" type="button" @on-change="query">
              <Radio :label="null">所有</Radio>
              <Radio :label="0">待提交</Radio>
              <Radio :label="1">审核中</Radio>
              <Radio :label="2">通过</Radio>
              <Radio :label="3">驳回</Radio>
            </RadioGroup>
          </td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <Input v-model="queryForm.shippingId" placeholder="发货单号" ></Input>
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
    api="/api/shipping/get?shippingId="
    :columns="columns1" 
    :objId="curRowId">
    </ListPageDetail> 
    <SelAudit ref="audit"></SelAudit>
  </ListPage> 
    
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import ListPageDetail from '@/components/page/ListPageDetail';
  import SelStorage from '@/components/storage/input/SelStorage'; 
  import SelStockOperType from '@/components/storage/input/SelStockOperType'; 
  import SelAudit from '@/components/storage/selaudit/SelAudit';
 
  import page from '@/assets/js/page';
    
  export default {
    components: { 
      ListPage,
      ListPageDetail,
      SelStorage,
      SelStockOperType,
      SelAudit
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
          }, /*
          {
            title:'#', 
            fixed: 'left',
            width:50,
            align:'center',
            render:(h,params)=>{
              return h('span', params.index + 1);
            }
          },*/
          {
            title: '发货单号',
            key: 'shippingId',
            width: 140,
            fixed: 'left',
            render:(h,params)=>{
              var row = params.row;
              var text = row.shippingId;
              text = text;
              return h('a',{
                props:{

                },
                on:{
                  click:()=>{
                    this.$router.push({path:'/shipping/view?id='+row.shippingId});
                  }
                }
              },text);
            }
          },
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
            title: '收货地址',
            key: 'deliveryAddress',
            align: 'left',
            width: 150,
          },
          page.table.initPersonColumn({
            title: '业务员',
            key: 'sales',
            align: 'left',
            width: 120,
          }),
          page.table.initPersonColumn({
            title: '申请人',
            key: 'proposer',
            align: 'left',
            width: 120,
          }),
          {
            title: '合同金额',
            key: 'amount',
            align: 'left',
            width: 120,
          },
          {
            title: '附件',
            key: 'files',
            align: 'left',
            width: 120,
          },
          {
            title: '备注',
            key: 'remark',
            align: 'left',
            width: 150,
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
          /*{
            title:'#', 
            fixed: 'left',
            width:50,
            align:'center',
            render:(h,params)=>{
              return h('span', params.index + 1);
            }
          },*/
          {
            title: '发货单号',
            key: 'materId',
            fixed: 'left',
            align: 'left',
            width: 120,
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
            title: '图号',
            key: 'drawing',
            align: 'left',
            width: 120,
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
            width: 120,
          },
          {
            title: '合格证',
            key: 'certification',
            align: 'left',
            width:150
          },
          page.table.initArgColumn({
            title: '单位',
            key: 'unit',
            width: 150,
            group: 'unit',
            align: 'left',
          }),
          {
            title: '出库数量',
            key: 'quantity',
            align: 'right',
            width:150
          },
          {
            title: '单重（公斤）',
            key: 'weight',
            align: 'right',
            width:150
          },
          {
            title: '总重（公斤）',
            key: 'weightTotal',
            align: 'right',
            width:150
          },
          {
            title: '单价（元）',
            key: 'price',
            align: 'right',
            width:150
          },
          {
            title: '总价（元）',
            key: 'amount',
            align: 'right',
            width:150
          },
          {
            title: '备注',
            key: 'remark',
            align: 'left',
            width:150
          },
        ], 
        queryForm: {
          shippingId:'',
          status:0,
          createTime:null,
        },
        loading: 0
      }
    },
    mounted: function () {
      this.query();
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
          shippingId:'',
          status:0,
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.query();
      },
      curRowChg(row){
        if(row!=null){
           this.curRowId = row.shippingId;
           this.$refs.detail.load(this.curRowId);
        }else{
          this.curRow = null;
          this.curRowId = null;
          this.$refs.detail.clear();
        }
      },
      del(){
         
      },
      goPage(page){
        this.$router.push({path:page});
      }
    }
  }

</script>

<style type="text/css">
  
</style>
