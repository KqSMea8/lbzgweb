<template>
  <ListPage ref="page" api="/api/inStockApply/list" 
  :model="this" @onCurrentRowChange="curRowChg" :beforeLoad="beforeLoad">
    <div class="page-title" slot="page-title">
      入库申请单驳回记录
    </div> 
   
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <!-- <td>
            <Input v-model="queryForm.inStockId" placeholder="入库单号" ></Input>
          </td>
          <td>操作原因</td>
          <td>
            <SelStockOperType v-model="queryForm.operId" style="width:100px">
            </SelStockOperType>
          </td>  -->
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
     <!-- <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>  -->
          <!-- <td>
            <SelStorage v-model="queryForm.storageId" 
            @on-change="switStorage" style="width:150px;"> 
            </SelStorage> 
          </td> -->
          <!-- <td>
            <RadioGroup v-model="queryForm.status" type="button" @on-change="query">
              <Radio :label="null">所有</Radio>
              <Radio :label="0">待提交</Radio>
              <Radio :label="1">审核中</Radio>
              <Radio :label="2">通过</Radio>
              <Radio :label="3">驳回</Radio>
            </RadioGroup>
          </td> -->
          <!-- <td class="page-tools"> -->
            <!-- <Button @click="add"  icon="plus">新建</Button>
            &nbsp; -->
            <!-- <Button @click="sendAudit"  icon="checkmark">提交</Button> 
            &nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;
            <Button @click="edit"  icon="edit">编辑</Button> 
            &nbsp;
          
            <Button @click="del"  icon="android-delete">删除</Button>
          </td>
        </tr>
      </table>
    </div> -->
    <ListPageDetail ref="detail" slot="page-datatable-detail"
    api="/api/inStockApply/get?inStockApplyId="
    :columns="columns1" 
    :objId="curRowId">
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
            title: '入库单号',
            key: 'inStockApplyId',
            width: 140,
            fixed: 'left',
            render:(h,params)=>{
              var row = params.row;
              var text = row.inStockApplyId;
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
            title: '采购申请单编号',
            key: 'purchaseOrderId',//purchase_order_id   relatedBillId
            fixed: 'left',
            width: 140
          },

          page.table.initDepartmentColumn({
            title: '申请部门',
            key: 'department',
          }), 

          page.table.initPersonColumn({
            title: '采购员',
            key: 'buyer',
          }),
          page.table.initArgColumn({
            title: '入往仓库',
            key: 'storageId',
            group:'storageList',
            align:'left',
            width:150
          }),
          {
            title: '驳回原因',
            key: 'auditRemark',
            align: 'left',
            width: 120,
          },
          page.table.initDateColumn({
            title: '驳回日期',
            key: 'updateTime',
          }),
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
            title: '原厂批号',
            key: 'originBatch',
            align: 'center',
            width: 120,
          },
          {
            title: '到厂批号',
            key: 'batch',
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
            title: '到货数量',
            key: 'quantity',
            align: 'left',
            width:150
          },
          {
            title: '用途',
            key: 'use',
            align: 'left',
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
          status:3,
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
          status:3,
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.query();
      },
      curRowChg(row){
        if(row!=null){
           this.curRow = row;
           this.curRowId = row.inStockApplyId;
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
      // add() { 
      //   this.$router.push({path:'/storage/instock/edit?forward'})
      // },
      // edit(){
      //   alert("编辑页面。。。");
        // if(this.curRowId){
        //   this.$router.push({
        //     path:'/storage/instock/edit?forward&id=' + this.curRowId
        //   })
        // }else{
        //   this.$Message.error('请选择要编辑的入库单');
        // }
      // },
      // sendAudit(){
      //   alert("提交页面。。。");

        // var selection = this.$refs.page.getSelection();
        // if(selection.length == 0){
        //   this.$Message.error('请选择要操作的数据行');
        //   return;
        // }
        // var start = 0;
        // var handle = ()=>{
        //   if(start >= selection.length){
        //     return;
        //   }
        //   var item = selection[start];
        //   this.$http.post('/api/stock/bill/submit?stockBillId=' + item.stockBillId, {}).then((res) => {
        //       if (res.data.code === 0) {
        //         this.$Message.info("第 "+ (start+1) +" 条操作成功");
        //         start ++ ;
        //         if(start < selection.length){
        //           handle();
        //         }else{
        //           this.$refs.page.load();
        //         }
        //       } else {
        //         this.$Message.error(res.data.message)
        //         this.$refs.page.load();
        //       }
        //     }).catch((error) => {
        //       this.loading = 0;
        //       this.$Message.error(error.toString())
        //     });
        // }
        // handle();
      // },
      // del(){
      //    alert("删除页面。。。");
      // },
      goPage(page){
        this.$router.push({path:page});
      }
    }
  }

</script>

<style type="text/css">
  
</style>
