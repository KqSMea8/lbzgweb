<template>
  <div class="page inquirelist">
    <div class="page-title"><!-- 页面布局样式 -->
      合同铸件号维护
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td>合同编号</td>
          <td>
            <Input v-model="queryForm.npmId" placeholder="合同编码" ></Input>
          </td>
          <td>&nbsp;&nbsp;</td>
          <td>图号</td>
          <td>
            <Input v-model="queryForm.npmId" placeholder="图号" ></Input>
          </td>
          <td>订货单位</td>
          <td>
            <Input v-model="queryForm.providerName" readonly="readonly" style="cursor: default;" icon="search" @on-click="selProvider" ></Input>
          </td>
          <td>
            业务员:
          </td>
          <td>
            <Input v-model="queryForm.proposerName" readonly="readonly"
                   style="cursor: pointer;" placeholder="" @click.native="selEmp" icon="search"></Input>
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
      <div class="page-tools">
        <table cellpadding="10" cellspacing="10">
          <tr>
            <td><Button @click="update" icon="edit">编辑</Button></td>
            <td><Button @click="submit"  icon="paper-airplane">保存</Button></td>
            <td><Button @click="goBack" icon="paper-airplane">退出</Button></td>
          </tr>
        </table>
      </div>
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
  import QualityAdd from '@/components/quality/device/QualityAdd';

  export default {
    components: {
      DataRowOperateBar,
      Loading,
      SelProvider,  //声明新组件
      QualityAdd,
    },
    data() {
      return {
        columns: [
          {
            title: '选择',
            key: 'index',
            width: 60,
            render:function(h,params) {
              var row = params.row;
              var index = params.index;
              var props={
                value:row._checked,
              };
              if(row.status == "2"){
                props.disabled =true;
              }
              return h('Checkbox',{
                props:props,
                on:{
                  'on-change':()=>{
                    that.innerCheckRow(index);
                  }
                }
              });
            }
          },
          {
            title: '合同编码',
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
            title: '订货单位',
            key: 'providerName',
            align: 'center',
          },
          {
            title: '业务员',
            key: 'buyer',
            align: 'center',
            width: 80,
            render:(h,params)=>{
              var row = params.row;
              return h('span', this.$args.getArgText('empList',row.buyer));
            },
          },
          {
            title: '合同总件数',
            key: 'submiter',
            align: 'center',
            width: 80,
            render:(h,params)=>{
              var row = params.row;
              return h('span', this.$args.getArgText('empList',row.submiter));
            },
          },
           {
            title: '行号',
            key: 'submitTime',
            align: 'center',
            width: 120,
            render:(h, params)=>{
              var row = params.row;
              return h('span', row.submitTime && row.submitTime.length>=10?row.submitTime.substring(0,10):row.submitTime);
            }
          },
          {
            title: '铸件名称',
            key: 'reporter',
            align: 'center',
            width: 80,
            render:(h,params)=>{
              var row = params.row;
              return h('span', this.$args.getArgText('empList',row.reporter));
            },
          },
          {
            title: '图号',
            key: 'remark',
            align: 'center',
            width: 120,
          },
          {
            title: '材质',
            key: 'remark',
            align: 'center',
            width: 120,
          },
          {
            title: '交货类型',
            key: 'remark',
            align: 'center',
            width: 120,
          },
          {
            title: '件数',
            key: 'remark',
            align: 'center',
            width: 120,
          },
          {
            title: '合同单重(kg)',
            key: 'remark',
            align: 'center',
            width: 120,
          },
          {
            title: '合同总重(kg)',
            key: 'remark',
            align: 'center',
            width: 120,
          },
          {
            title: '交货日期',
            key: 'remark',
            align: 'center',
            width: 120,
          },
          {
            title: '附件',
            key: 'remark',
            align: 'center',
            width: 120,
          },{
            title: '铸件号',
            key: 'remark',
            align: 'center',
            width: 120,
          },
          {
            title: '铸件ID',
            key: 'remark',
            align: 'center',
            width: 120,
          },
          {
            title: '条码',
            key: 'remark',
            align: 'center',
            width: 120,
          },
          {
            title: '特殊要求',
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
      add: function() {
        /*this.goPage("/new-material/AddNewMaterial");*/

        this.$router.push("/new-material/AddNewMaterial?forward");
      },
      update: function() {
        /*this.goPage("/new-material/AddNewMaterial");*/
        /*if(selection.length>1){
          this.$Message.info("只能选择一个");
        }else if (selection.length<1) {
          this.$Message.info("请选择计量器具");
        }else {
          if(item.instrumentId!=null){
            this.goPage("/device/add?forward&id="+item.instrumentId);
          }else{
            var selection = this.$refs.page.getSelection();
            if (!$.isEmptyObject(selection)){
              this.goPage("/device/add?forward&id="+selection[0]['instrumentId']);
            }else{
              this.$Message.error("请选择");
            }
          }
        }*/
        this.$router.push("/new-material/EditNewMaterial?forward");
      },
      goBack: function () {
        /*this.$router.go(-1);*/
        this.$router.push('/design');
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
