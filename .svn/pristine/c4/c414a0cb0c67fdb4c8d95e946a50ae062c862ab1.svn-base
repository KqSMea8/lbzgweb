<template>
  <ListPage ref="page" api="/api/stock/bill/list" 
  :model="this" @onCurrentRowChange="curRowChg" :beforeLoad="beforeLoad">
    <div class="page-title" slot="page-title">
      <a @click="goPage('/storage/mgr')">仓库管理</a> -&gt;出库单
    </div> 
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <SelStorage v-model="queryForm.storageId" 
            @on-change="switStorage" style="width:150px;"> 
            </SelStorage> 
          </td>
          <td>
            <RadioGroup v-model="queryForm.status" type="button" @on-change="query">
              <Radio :label="null">所有</Radio>
              <Radio :label="0">待提交</Radio>
              <Radio :label="1">审核中</Radio>
              <Radio :label="2">通过</Radio>
              <Radio :label="3">驳回</Radio>
            </RadioGroup>
          </td>
          <td class="page-tools" v-if="queryForm.status==0">
            <Button @click="add" v-power="'ckgl.ck.add'" icon="plus">新建</Button>
            &nbsp;
            <Button @click="edit" v-power="'ckgl.ck.edit'" icon="edit" v-if="curRow!=null">编辑</Button> 
            &nbsp;
            <Button @click="sendAudit" v-power="'ckgl.ck.audit'" icon="checkmark">送审</Button> 
            &nbsp;
            <Button @click="del" v-power="'ckgl.ck.del'" icon="android-delete" v-if="curRow!=null">删除</Button>
          </td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <Input v-model="queryForm.stockBillId" placeholder="出库单号" ></Input>
          </td>
          <td>操作原因</td>
          <td>
            <SelStockOperType v-model="queryForm.operId" style="width:100px" :type="2">
            </SelStockOperType>
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
    api="/api/stock/bill/get?stockBillId="
    :columns="columns1" 
    :objId="curRowId">
    </ListPageDetail> 
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
            title: '出库单号',
            key: 'stockBillId',
            width: 140,
            fixed: 'left',
            render:(h,params)=>{
              var row = params.row;
              var text = row.stockBillId;
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
            title: '生产领用单编号',
            key: 'outStockApplyId',
            align: 'left',
            width: 120
          }, 
          {
            title: '采购退货单编号',
            key: 'purchaseBackOrderId',
            align: 'left',
            width: 120
          }, 
          page.table.initArgColumn({
            title: '出库仓库',
            key: 'storageId',
            group:'storageList',
            align:'left',
            width:150
          }),
          page.table.initArgColumn({
            title: '操作原因',
            key: 'operId',
            group:'stockOperTypeList',
            align:'left',
            width:120
          }),
          page.table.initPersonColumn({
            title: '领用人',
            key: 'proposer',
            width: 120,
            align:'left',
          }),
          page.table.initDepartmentColumn({
            title: '领用部门',
            key: 'department',
            width: 120,
            align:'left',
          }), 
          page.table.initDateColumn({
            title: '操作日期',
            key: 'createTime',
            width: 120,
            align:'left',
          }),
          {
            title: '备注',
            key: 'remark',
            align:'left',
            width:200
          },
          page.table.initMapColumn({
            title: '状态',
            key: 'status',
            data:{
              '0':'待提交',
              '1':'审核中',
              '2':'通过',
              '3':'驳回',
            },
            width: 200
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
            title: '批次号',
            key: 'batch',
            align: 'left',
            width: 120,
          },
          {
            title: '货位',
            key: 'placeName',
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
            title: '数量/重量',
            key: 'quantity',
            align: 'right',
            width:150
          },
          {
            title: '交回数量',
            key: 'returnQuantity',
            align: 'right',
            width:100,
          },
          {
            title: '备注',
            key: 'remark',
            align: 'left',
            width:200
          },
        ], 
        queryForm: {
          stockBillId:'',
          storageId:this.$user.config.data.storageId,
          status:0,
          operId:'', 
          operType:2,
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
        if(!this.queryForm.storageId){
          this.$Message.error('请选择仓库');
          return;
        }
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
          stockBillId:'',
          inStockId:'',
          status:0,
          operId:'', 
          operType:2,
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.query();
      },
      curRowChg(row){
        if(row!=null){
           this.curRow = row;
           this.curRowId = row.stockBillId;
           this.$refs.detail.load(this.curRowId);
        }else{
          this.curRow = null;
          this.curRowId = null;
          this.$refs.detail.clear();
        }
      },
      switStorage(){
        this.$user.config.data.storageId = this.queryForm.storageId;
        this.reset();
      }, 
      add() { 
        this.$router.push({path:'/storage/outstock/edit?forward'})
      },
      edit(){
        if(this.curRowId){
          this.$router.push({
            path:'/storage/outstock/edit?forward&id=' + this.curRowId
          })
        }else{
          this.$Message.error('请选择要编辑的出库单');
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
          this.$http.post('/api/stock/bill/submit?stockBillId=' + item.stockBillId, {}).then((res) => {
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
            this.$http.post('/api/stock/bill/delete',{
              stockBillId:this.curRow.stockBillId,
            }).then((res) => {
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
      goPage(page){
        this.$router.push({path:page});
      }
    }
  }

</script>

<style type="text/css">
  
</style>
