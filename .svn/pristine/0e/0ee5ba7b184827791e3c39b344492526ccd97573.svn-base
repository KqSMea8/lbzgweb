<template>
  <div class="page inquirelist">
    <div class="page-title"><!-- 页面布局样式 -->
      新材料试用记录
    </div>
    <div class="page-searchbox">
      <table>
        <tr>
          <td></td>
          <td>
            <RadioGroup v-model="queryForm.reportStatus" type="button" @on-change="query">
              <Radio :label="1">待试用</Radio>
              <Radio :label="2">已试用</Radio>
            </RadioGroup>
          </td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>供应商</td>
          <td>
            <Input v-model="queryForm.providerName" readonly="readonly" style="cursor: default;" icon="search" @on-click="selProvider" ></Input>
          </td>
          <td>&nbsp;&nbsp;</td>
          <td>新材料编号</td>
          <td>
            <Input v-model="queryForm.npmId" placeholder="新材料编号" ></Input>
          </td>
          <td>&nbsp;&nbsp;</td>
          <td>起止日期</td>
          <td><DatePicker type="daterange" v-model="queryForm.createTime"
           split-panels placeholder="起止日期"
           style="width: 180px" :clearable="false" :transfer="true"></DatePicker></td>
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
          @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top"  :transfer="true"></Page>
    </div>
    </Loading>
    <SelProvider ref="selProvider"></SelProvider>
  </div>
</template>
<script>
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import Loading from '@/components/loading';
  import page from '@/assets/js/page';
  import SelProvider from '@/components/provider/SelectProvider';

  export default {
    components: {
      DataRowOperateBar,
      Loading,
      SelProvider,  //声明新组件
    },
    data() {
      let that = this;
      return {
        columns: [
          {
            title: '新材料编号',
            key: 'npmId',
            width: 120,
             render:(h,params)=>{
              var row = params.row;
              return h('a',{
                props:{
                },
                on:{
                  click:()=>{  //详情页面，由上传附件的操作
                    this.$router.push({path:'/craft-newmaterial/view?id='+row.npmId+'&status='+this.queryForm.reportStatus});//详情页面
                  }
                }
              },row.npmId);
            }
          },

          {
            title: '供应商名称',
            key: 'providerName',
            align: 'left',
          },
          {
            title: '采购员',
            key: 'buyer',
            align: 'center',
            width: 80,
            render:(h,params)=>{
              var row = params.row;
              return h('span', this.$args.getArgText('empList',row.buyer));
            },
          },
          {
            title: '操作员',
            key: 'submiter',
            align: 'center',
            width: 80,
            render:(h,params)=>{
              var row = params.row;
              return h('span', this.$args.getArgText('empList',row.submiter));
            },
          },
           {
            title: '操作日期',
            key: 'submitTime',
            align: 'center',
            width: 120,
            render:(h, params)=>{
              var row = params.row;
              return h('span', row.submitTime && row.submitTime.length>=10?row.submitTime.substring(0,10):row.submitTime);
            }
          },
          {
            title: '技术员',
            key: 'reporter',
            align: 'center',
            width: 80,
            render:(h,params)=>{
              var row = params.row;
              return h('span', this.$args.getArgText('empList',row.reporter));
            },
          },

           {
            title: '备注',
            key: 'remark',
            align: 'center',
            width: 120,
          },
        ],

        list: [],
        total:0,
        queryParam: {},
        queryForm: {
          reportStatus:1,
          wfStatus:0,
          providerId:'',
          providerName:''
        },
        industry:[],
        loading: 0
      }
    },
    mounted: function () {
      this.loadGroups();
      this.reset();
    },
    computed: {},

    methods: {
      loadGroups: function () {
        this.loading = 1;
        //查询角色列表数据
        this.$http.post("/api/arg/list", {argGroup:'industry'}).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.industry = res.data.data;
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新发送")
        });
      },

      load() {
        var pagebar = this.$refs.pagebar;
        this.loading = 1;
        this.queryParam.page = pagebar.currentPage;
        this.queryParam.pageSize = pagebar.currentPageSize;
        this.$http.post("/api/newProviderMaterial/list", this.queryParam).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.loading = 0;
            this.list = res.data.data.rows;
            this.total = res.data.data.total;
            this.selection = [];
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
        if(this.queryForm.reportStatus==1){//待反馈按钮时，评定旁的4个按钮置灰
          this.queryForm.wfStatus=null;//置空状态
        }
        Object.assign(this.queryParam,this.queryForm);
        this.queryParam.createTimeStart = '';
        this.queryParam.createTimeEnd = '';
        if(this.queryForm.createTime.length > 0){
          this.queryParam.createTimeStart = page.formatDate(this.queryForm.createTime[0]);
        }
        if(this.queryForm.createTime.length > 1){
          this.queryParam.createTimeEnd = page.formatDate(this.queryForm.createTime[1]);
        }
        var pagebar = this.$refs.pagebar;
        pagebar.current = 1;
        pagebar.currentPage = 1;
        pagebar.currentPageSize = 10;
        this.load();
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
          reportStatus:1,
          wfStatus:0,
          providerId:'',
          providerName:'',
          npmId:'',
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.resetPage();
        this.query();
      },
      resetPage: function(){
        var pagebar = this.$refs.pagebar;
        pagebar.current = 1;
        pagebar.currentPage = 1;
        pagebar.currentPageSize = 10;
      },
      selProvider(){
       var sel = this.$refs.selProvider;
       sel.show({
        ok:(data)=>{
          if(data){
            this.queryForm.providerId = data.providerId;
            this.queryForm.providerName = data.providerName;
          }
        }
       });
      }//selProvider()

    }//methods
  }//export

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
