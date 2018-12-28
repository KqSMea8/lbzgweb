<template>
<ListPage ref="page" title="新材料供应管理" api="/api/newProviderMaterial/list" :model="this" :beforeLoad="beforeLoad">  
    <div class="page-tools">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <RadioGroup v-model="queryForm.reportStatus" type="button" @on-change="query">
              <Radio :label="1">待反馈</Radio>
              <Radio :label="2">已反馈</Radio>
            </RadioGroup>
          </td>
          <td>
            <RadioGroup v-model="queryForm.wfStatus" type="button" @on-change="query">
              <Radio :label="0" :disabled="queryForm.reportStatus==1">待创建</Radio>
              <Radio :label="1" :disabled="queryForm.reportStatus==1">已提交</Radio>
              <Radio :label="2" :disabled="queryForm.reportStatus==1">通过</Radio>
              <Radio :label="3" :disabled="queryForm.reportStatus==1">驳回</Radio>
            </RadioGroup>
          </td>
          <td><Button @click="add" v-power="'cggl.xcl.add'" icon="plus">新建</Button></td>
        </tr>
      </table>
    </div> 
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <Input v-model="queryForm.providerName" :readonly="true" style="cursor: default;" placeholder="供应商" icon="search" @on-click="selProvider" ></Input>
          </td> 
          <td>
            <Input v-model="queryForm.npmId" placeholder="新材料编号" ></Input>
          </td> 
          <td>
            <Input v-model="queryForm.materName" placeholder="物料名称" ></Input>
          </td> 
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
    <SelProvider ref="selProvider"></SelProvider>
</ListPage>
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import Loading from '@/components/loading';
  import ProviderNewMatEdit from './ProviderNewMatEdit';
  import page from '@/assets/js/page';
  import SelProvider from '@/components/provider/SelectProvider';

  export default {
    components: {
      ListPage,
      DataRowOperateBar,
      Loading,
      ProviderNewMatEdit,
      page,
      SelProvider  //声明新组件
    },
    data() {
      let that = this;
      return {
        columns: [
          {
            title: '新材料编号',
            key: 'npmId',
            width: 120,
            align: 'center',
            render:(h,params)=>{
              var row = params.row;
              return h('a',{
                props:{
                },
                on:{
                  click:()=>{
                    this.$router.push({path:'/provider-newmaterial/view?forward&id='+row.npmId});///新材料的详情页面上添加编辑按钮
                  }
                }
              },row.npmId);
            }
          },

          {
            title: '供应商名称',
            key: 'providerName',
            align: 'left',
            minWidth:200,
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
            title: '反馈',
            key: 'reportStatus',
            align: 'center',
            width: 80,
            render: function (h, params) {
              var status = params.row.reportStatus;
              let setButton = "待反馈";
              if (status == 2) {
                setButton = "已反馈";
              }
              return h('span',setButton);
            }
          },
           {
            title: '评定',
            key: 'wfStatus',
            align: 'center',
            width: 80,
            render: function (h, params) {
              var status = params.row.reportStatus;

              var wfstatus =params.row.wfStatus;

              let setButton = "";
              if (status == 1) {
                setButton = "--";
              }
              if(status > 1){
                if (wfstatus == 0) {
                 setButton = "待创建";
                }
                if (wfstatus == 1) {
                  setButton = "已提交";
                 }
                if (wfstatus == 2) {
                 setButton = "通过";
                }
                if (wfstatus == 3) {
                  setButton = "驳回";
                }
              }
              return h('span',setButton);
            }
          },
           {
            title: '备注',
            key: 'remark',
            align: 'center',
            width: 120,
          },
        ], 
        queryForm: {
          reportStatus:2,
          wfStatus:0,
          providerId:'',
          providerName:'',
          materName:'',
          npmId:'',
        }, 
        statusParam:2
      }
    },
    mounted: function () {
      if(this.$route.query.reportStatus){
        let param = this.$route.query.reportStatus
        this.statusParam = parseInt(param);
      } 
      this.reset(); 
    },
    computed: {},

    methods: {
      query() {
        this.$refs.page.query();
      }, 
      reset() {
        Object.assign(this.queryForm,{
          ireportStatus:this.statusParam,
          wfStatus:0,
          providerId:'',
          providerName:'',
          npmId:'',
          materName:'',
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.query();
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

      add: function () {
        this.$router.push({path:'/provider-newmaterial/edit?forward'}); 
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
      }

    }
  }

</script>

<style type="text/css">
  
</style>
