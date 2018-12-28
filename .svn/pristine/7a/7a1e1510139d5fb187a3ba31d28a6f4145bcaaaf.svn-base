<template>
  <div class="page inquirelist">
    <div class="page-title">
      项目档案
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>录入日期</td>
          <td><DatePicker type="daterange" split-panels placeholder="录入日期" style="width: 180px"></DatePicker></td>
          <td>
            <Input v-model="queryForm.inquireId" placeholder="合同编号" ></Input>
          </td>
          <td>
            <Button @click="query"  type="primary" icon="ios-search">查询</Button>
          </td>
          <td>
            <Button @click="reset" >重置</Button>
          </td>
        </tr>
      </table>
    </div>
    <Loading :loading="loading">
    <div class="page-datatable">
      <i-table :columns="columns" :data="list"></i-table>
      <div style="height:10px;"></div>
      <Page ref="pagebar" :total="total" size="small"
          @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top" :transfer="true"></Page>
    </div>
    </Loading>
    <CustomerEdit ref="edit" @on-save="query"></CustomerEdit>
    <CustomerView ref="view"></CustomerView>
  </div>
</template>

<script>
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import Loading from '@/components/loading';
  import CustomerEdit from '../customer/CustomerEdit';
  import CustomerView from '../customer/CustomerView';

  export default {
    components: {
      DataRowOperateBar,
      Loading,
      CustomerEdit,
      CustomerView
    },
    data() {
      let that = this;
      return {
        columns: [
            {
            title: '项目类型',
            key: 'projectTypeName',
            align: 'center',
            width:100,
          },
          {
            title: '合同编号',
            key: 'contractNo',
            align: 'center',
            width: 120,

          },
          {
            title: '订货单位',
            key: 'customerName',
            align: 'left',
            width:200,
          },
         {
            title: '合同件数',
            key: '合同件数',
            align: 'center',
            width:100,
          },
          {
            title: '预算日期',
            key: '预算日期',
            align: 'center',
            width: 120,
          },
          {
            title: '合同签订日期',
            key: 'signDate',
            align: 'center',
            width: 120,
            render:(h, params)=>{
              var row = params.row;
              return h('span', row.createTime && row.createTime.length>=10?row.createTime.substring(0,10):row.createTime);
            }
          },
           {
            title: '业务员',
            key: 'salesName',
            align: 'center',
            width:100,
          },
          {
            title: '工艺员',
            key: 'designerName',
            align: 'center',
            width:100,
          },
          {
            title: '备注',
            key: 'remark',
            align: 'center'
          },
          {
            title: '评审',
            key: 'proStatus',
            align: 'center',
            width: 80,
            render:(h, params)=>{
              var row = params.row;
              var statusInfo = {
                '0':'创建',
                '1':'审核中',
                '2':'通过',
                '3':'驳回',
              };
              return h('span', statusInfo[row.proStatus+'']);
            }
          },
          {
            title: '修订',
            key: 'proRevisionStatus',
            align: 'center',
            width: 80,
            render:(h, params)=>{
              var row = params.row;
              var statusInfo = {
                '0':'--',
                '1':'审核中',
                '2':'通过',
                '3':'驳回',
              };
              return h('span', statusInfo[row.proRevisionStatus+'']);
            }
          },
         ],
        list: [],
        total:0,
        queryParam: {},
        queryForm: {
          contractId: null,
        },
        industry:[],
        loading: 0
      }
    },
    mounted: function () {
     this.query()//查询按钮
    },
    computed: {},
    methods: {
      load() {  //默认导出列表数据
        var pagebar = this.$refs.pagebar;
        this.loading = 1;
        this.queryParam.page = pagebar.currentPage;
        this.queryParam.pageSize = pagebar.currentPageSize;
        this.$http.post("api/contractProject/list", this.queryParam).then((res) => {//  /api/contractProject/waitChange/list
          this.loading = 0;
          if (res.data.code === 0) {
            this.loading = 0;
            this.list = res.data.data.rows;
            this.total = res.data.data.total;
            this.selection = [];
            // 同步条件信息到表单
            Object.assign(this.queryForm, this.queryParam);
          } else {
            this.loading = 0;
            this.list = null;
            this.total = 0;
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新发送")
        });
      },
      query: function () {
        Object.assign(this.queryParam,this.queryForm)
        this.load();//查询后，再次重新刷新列表
      },
      pageChange: function (page) {
        this.load();
      },
      pageSizeChange:function(pageSize){
        var pagebar = this.$refs.pagebar;
        if(pagebar.currentPage == 1){
          this.load();
        }
      },
      goBack: function () {
        this.$router.go(-1);
      },
      reset: function () {
        Object.assign(this.queryForm,{
          inquireId: '',
          industry:'',
          status:1,
        });
        this.query();
      },
    }
  }

</script>

<style type="text/css">
  .inquirelist .status-2{
    color:#ff6600;
  }

  .inquirelist .ivu-radio-group-button .ivu-radio-wrapper {
    height: 28px;
    line-height: 26px;
    padding: 0 12px;
    font-size: 12px;
}
</style>
