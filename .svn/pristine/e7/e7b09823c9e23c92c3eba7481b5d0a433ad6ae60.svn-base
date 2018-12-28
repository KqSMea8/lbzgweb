<template>
    <ListPage ref="page" title="外检申请单" api="/api/ExternalInspectionApply/findEIA"
              :model="this" @onCurrentRowChange="curRowChg" :beforeLoad="beforeLoad">
        <div class="page-title" slot="page-title">外检申请单
        </div>
        <div class="page-searchbox">
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td>外检申请单编号：</td>
                    <td>
                        <Input v-model="queryForm.externalApplyId" placeholder="外检申请单编号" ></Input>
                    </td>
                    <td>申请人：</td>
                    <td>
                        <Input v-model="queryForm.operatorName" placeholder="申请人" ></Input>
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
                </tr>
            </table>
        </div>
        <div class="page-searchbox">
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td class="page-tools">
                        <Button @click="add"  icon="plus">创建外检返厂验收单</Button>
                        &nbsp;
                        <Button @click="edit"  icon="edit">编辑</Button>
                        &nbsp;
                        <Button @click="sendAudit"  icon="checkmark">退出</Button>
                    </td>
                </tr>
            </table>
        </div>

        <ListPageDetail ref="detail" slot="page-datatable-detail"
                        api="/api/ExternalInspectionApply/get?externalApplyId="
                        :columns="columns1"
                        :objId="externalApplyId">
        </ListPageDetail>
        <ProviderEdit ref="edit" @on-save="query"></ProviderEdit>


    </ListPage>

</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import ListPageDetail from './ListPageDetail';
  import SelStorage from '@/components/storage/input/SelStorage';
  import SelStockOperType from '@/components/storage/input/SelStockOperType';
  import ProviderEdit from './InspectionEdit';
  import page from '@/assets/js/page';

  export default {
    components: {
      ListPage,
      ListPageDetail,
      SelStorage,
      ProviderEdit,
      SelStockOperType
    },
    data() {
      let that = this;
      return {
        externalApplyId:'',
        columns: [

          {
            title:'序号',
            fixed: 'left',
            width:50,
            align:'center',
            render:(h,params)=>{
              return h('span', params.index + 1);
            }
          },
          {
            title: '外检申请单编号',
            key: 'externalApplyId',
            width: 140,
            fixed: 'left',

          },
          {
            title: '申请人',
            key: 'operatorName',
            align: 'center',
          },
          {
            title: '申请部门',
            key: 'departmentName',
            align: 'center',
          },
          page.table.initDateColumn({
            title: '申请日期',
            key: 'applicationDate',
          }),
          page.table.initMapColumn({
            title: '状态',
            key: 'status',
            data:{
              '1':'通过',
              '2':'驳回',
            }
          }),
          {
            title: '驳回原因',
            key: 'wfInstRemark',
            align: 'center',
            render:(h,params)=>{
              var row = params.row;
              var text = row.wfInstRemark;
              text = text;
              return h('a',{
                props:{

                },
                on:{
                  click:()=>{
                    this.$refs.edit.open(row);
                    //this.$router.push({path:'/contract/view?id='+row.inStockId});
                  }
                }
              },text);
            }
          },
          page.table.initDateColumn({
            title: '驳回日期',
            key: 'wfInstTime',
          }),
          {
            title: '备注',
            key: 'wfInstRemark',
            align: 'center',
          },
        ],
        columns1: [
          {
            title:'序号',
            fixed: 'left',
            width:50,
            align:'center',
            render:(h,params)=>{
              return h('span', params.index + 1);
            }
          },
          {
            title: '计量器具编号',
            key: 'ins',
            width: 140,
            fixed: 'left',
             key: 'instrumentId',
          },
          {
            title: '计量器具名称',
            key: 'counterName',
            fixed: 'center',
            align: 'center',
            width: 120,
          },
          {
            title: '型号',
            key: 'model',
            fixed: 'center',
            align: 'center',
            width: 120,
          },
          {
            title: '生产厂家',
            key: 'orgin',
            align: 'center',
            width: 120,
          },
          {
            title: '内部编号',
            key: 'model',
            align: 'center',
            width: 120,
          },
          {
            title: '安装位置',
            key: 'installationSite',
            align: 'center',
            width: 100,
          },
          {
            title: '检定周期',
            key: 'retestingPeriod',
            align: 'center',
            width: 160,
          },
          {
            title: '上次检定时间',
            key: 'lastTestingTime',
            align: 'center',
            width: 100,
          },
          {
            title: '备注',
            key: 'remark',
            align: 'left',
            width: 100,

          },

        ],
        queryForm: {
          externalApplyId:'',
          proposer:'',
					operatorName:'',
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
        queryParam.createBeginTime = '';
        queryParam.createEndTime = '';
        delete queryParam.createTime;
        if(this.queryForm.createTime.length > 0){
          queryParam.createBeginTime = page.formatDate(this.queryForm.createTime[0]);
        }
        if(this.queryForm.createTime.length > 1){
          queryParam.createEndTime = page.formatDate(this.queryForm.createTime[1]);
        }
      },
      reset() {
        Object.assign(this.queryForm,{
          externalApplyId:'',
          proposer:'',
					operatorName:'',
          status:0,
          operId:'',
          createTime:[]
					/* [page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())] */
        });
        this.query();
      },
			/* [page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())] */
      curRowChg(row){
        if(row!=null){
           this.curRow = row;
           this.curRowId = row.externalApplyId;
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
        this.$router.push({path:'/device/newOutside?forward&id='+this.externalApplyId})
      },
      edit(){
          this.$router.push({
            path:'/device/AddApp?forward&id=' + this.externalApplyId
          })
      },
      sendAudit(){
        ///var selection = this.$refs.page.getSelection();
        this.$refs.page.load();
        this.$router.go(-1);
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
