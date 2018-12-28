<template>
    <ListPage ref="page" title="检验结果修订记录" api="/api/revision/list"
              :model="this" @onCurrentRowChange="curRowChg" :beforeLoad="beforeLoad">
        <div class="page-title" slot="page-title">
        </div>
        <div class="page-searchbox">
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td>检验物料批次号:</td>
                    <td>
                        <Input v-model="queryForm.revisionId" placeholder="模糊搜索" ></Input>
                    </td>
                    <td>开始日期</td>
                    <td><DatePicker type="daterange" v-model="queryForm.analyst"
                                    split-panels placeholder="创建日期"
                                    style="width: 180px" :clearable="false" ::transfer="true"></DatePicker></td>
                    <td>
                        <Button @click="query"  type="primary" icon="ios-search">查询</Button>
                    </td>
                    <td>
                        <Button @click="reset" >重置</Button>
                    </td>
                    <td class="page-tools">
                         <Button @click="edit"  icon="edit">编辑（预览）</Button>
                    </td>
                    <td class="page-tools">
                        <Button size="small" @click="goBack" type="warning">返回</Button>
                    </td>
                </tr>
            </table>
        </div>

    </ListPage>

</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import ListPageDetail from '@/components/page/ListPageDetail';
  import SelStorage from '@/components/storage/input/SelStorage';
  import SelStockOperType from '@/components/storage/input/SelStockOperType';

  import page from '@/assets/js/page';

  export default {
    components: {
      ListPage,
      ListPageDetail,
      SelStorage,
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
            title: '文件批次号',
            key: 'batchId',
            width: 140,
            fixed: 'left',
            render:(h,params)=>{
              var row = params.row;
              console.log(row);
              var text = row.batchId;
              text = text;
              return h('a',{
                props:{

                },
                on:{
                  click:()=>{
                    this.$router.push({path:'/quality-revision/view?forward&id='+ row.batchId+'&ledgerId='+row.ledgerId});
                  }
                }
              },row.batchId);
            }
          },
          {
            title: '供应商名称(创建日期)',
            key: 'createTime',
            width: 140,
            fixed: 'left',
          },
          page.table.initDateColumn({
            title: '申请日期(化验员)',
            key: 'analyst',
          }),
          {
            title: '供货商代码(检验员)',
            key: 'inspector',
            align: 'center',
            width:100,
          },

          {
            title: '创建人（修订人员）',
            key: 'auditor',
            align: 'center',
          },
          page.table.initDateColumn({
            title: '申请日期(修订日期)',
            key: 'updateTime',
          }),
         /* page.table.initMapColumn({
            title: '状态（修订日期）',
            key: 'updateTime',
            data:{
              '0':'待提交',
              '1':'审核中',
              '2':'通过',
              '3':'驳回',
            }
          }),*/
        ],

        queryForm: {
          revisionId:'',
          updateTime:0,
          analyst:null,
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
        queryParam.analystStart = '';
        queryParam.analystEnd = '';
        delete queryParam.analyst;
        if(this.queryForm.analyst.length > 0){
          queryParam.analystStart = page.formatDate(this.queryForm.analyst[0]);
        }
        if(this.queryForm.analyst.length > 1){
          queryParam.analystEnd = page.formatDate(this.queryForm.analyst[1]);
        }
      },
      reset() {
        Object.assign(this.queryForm,{
          revisionId:'',
          updateTime:null,
          operId:'',
          analyst:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.query();
      },

      switStorage(){
        this.reset();
      },
      curRowChg(row){
        if(row!=null){
           this.curRow = row;
           this.curRowId = row.revisionId;
           this.$refs.detail.load(this.curRowId);
        }else{
          this.curRow = null;
          this.curRowId = null;
          this.$refs.detail.clear();
        }
      },
      /*edit(){
        console.log(this.curRowId);
        if(this.curRowId){
          this.$router.push({
            path:'/quality-revision/edit?forward&id=' + this.curRowId
          })
        }else{
          this.$Message.error('请选择要编辑的入库单');
        }
      },*/
      goBack(){
        /*this.$router.push('/quality/quality-revision/Revision');*/
        this.$router.go(-1);
      },
      goPage(page){
        this.$router.push({path:page});
      }
    }
  }

</script>

<style type="text/css">

</style>
