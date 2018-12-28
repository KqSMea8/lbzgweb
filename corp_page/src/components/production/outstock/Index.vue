<template>
  <ListPage ref="page" api="/api/outStockApply/list" 
  :model="this" @onCurrentRowChange="curRowChg" :beforeLoad="beforeLoad">
    <div class="page-title" slot="page-title">
      领料单
    </div> 
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
      <!--     <td>
            <SelStorage v-model="queryForm.storageId" 
            @on-change="switStorage" style="width:150px;"> 
            </SelStorage> 
          </td> -->
          <td>
            <RadioGroup v-model="queryForm.status" type="button" @on-change="query">
              <Radio :label="null">所有</Radio>
              <Radio :label="0">待提交</Radio>
              <Radio :label="1">审核中</Radio>
              <Radio :label="2">通过</Radio>
              <Radio :label="3">驳回</Radio>
            </RadioGroup>
          </td>
          <td class="page-tools">
            <Button @click="add" v-power="'ckgl.rk.add'" icon="plus">领料单</Button>
          </td>
          <td class="page-tools" v-if="queryForm.status==0"> 
            <Button @click="edit" v-power="'ckgl.rk.edit'" icon="edit">编辑</Button> 
            &nbsp;
            <Button @click="del" v-power="'ckgl.rk.del'" icon="android-delete">删除</Button>
            &nbsp;
            <Button @click="sendAudit" v-power="'ckgl.rk.audit'" icon="checkmark">送审</Button>  
          </td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
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
    api="/api/outStockApply/get?outStockApplyId="
    :columns="columns1">
    </ListPageDetail> 
  </ListPage> 
    
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import ListPageDetail from '@/components/page/ListPageDetail';
  import SelStorage from '@/components/storage/input/SelStorage';
  import page from '@/assets/js/page';
    
  export default {
    components: { 
      ListPage,
      ListPageDetail,
      SelStorage,
      page,
    },
    data() {
      let that = this;
      return {
        curRow:null,  
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
            title: '领料单号',
            key: 'outStockApplyId',
            width: 150,
            fixed: 'left',
            render:(h,params)=>{
              var row = params.row;
              var text = row.outStockApplyId;
              text = text;
              return h('a',{
                props:{

                },
                on:{
                  click:()=>{ 
                  }
                }
              },text);
            }
          },
          page.table.initDateColumn({
            title: '申请日期',
            key: 'createTime',
            align: 'left',
            width:150,
          }),
          page.table.initPersonColumn({
            title: '申请人',
            key: 'proposer',
            align: 'left',
            width:150,
          }),
          {
            title: '金额(元)',
            key: 'amount',
            align: 'right',
            width:150,
          }, 
          {
            title: '驳回原因',
            key: 'auditRemark',
            align: 'center',
            width: 200,
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
          {
            title: '备注',
            key: 'remark',
            align: 'left',
            width:220,
          },
        ],
        columns1: [
         /* {
            title:'#', 
            fixed: 'left',
            width:50,
            align:'center',
            render:(h,params)=>{
              return h('span', params.index + 1);
            }
          },*/
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
            align: 'left',
            width: 120,
          },
          {
            title: '型号',
            key: 'model',
            align: 'left',
            width: 120,
          },
          {
            title: '材质',
            key: 'texture',
            align: 'right',
            width:150
          },
          page.table.initArgColumn({
            title: '单位',
            key: 'unit',
            width: 100,
            group: 'unit',
            align: 'left',
          }), 
          {
            title: '请领数量',
            key: 'quantity',
            align: 'right',
            width:120
          },
          {
            title: '单价(元)',
            key: 'price',
            align: 'right',
            width:120
          },
          {
            title: '金额(元)',
            key: 'amount',
            align: 'right',
            width:100,
          }, 
          {
            title: '备注',
            key: 'remark',
            align: 'right',
            width:100,
          },
        ], 
        queryForm: {
          outStockApplyId:this.$user.config.data.storageId,
          outStockApplyId:'',
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
    /*    if(!this.queryForm.storageId){
          this.$Message.error('请选择仓库');
          return;
        }*/
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
          status:0,
          outStockApplyId:'',
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.query();
      },
      curRowChg(row){
        if(row!=null){
           this.curRow = row;
           this.curRowId = row.outStockApplyId;
           this.$refs.detail.load(this.curRowId);
        }else{
          this.curRowId = null;
          this.$refs.detail.clear();
        }
      },
/*      switStorage(){
        this.$user.config.data.storageId = this.queryForm.storageId;
        this.reset();
      }, */

      edit(){
        if(this.curRow){
            this.$router.push({
              path:'/outstockapply/edit?forward&id=' + this.curRow.outStockApplyId
            })
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
          this.$http.post('/api/outStockApply/submit?outStockApplyId=' + item.outStockApplyId, {}).then((res) => {
              if (res.data.code === 0) {
                this.$Message.info("第 "+ (start+1) +" 条操作成功");
                start ++ ;
                if(start < selection.length){
                  handle();
                }else{
                  this.$refs.page.load();
                }
              } else {
                this.$Message.error(res.data.message)
                this.$refs.page.load();
              }
            }).catch((error) => {
              this.loading = 0;
              this.$Message.error(error.toString())
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
            this.$http.post('/api/outStockApply/delete?outStockApplyId='+this.curRow.outStockApplyId
            ).then((res) => {
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
      add() { 
        this.$router.push({path:'/outstockapply/edit?forward'})
      }
    }
  }

</script>

<style type="text/css">
  
  
</style>
