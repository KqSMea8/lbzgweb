<template>
  <ListPage ref="page" title="产成品出库" api="/api/productStockBill/list" 
  :model="this" @onCurrentRowChange="curRowChg" :beforeLoad="beforeLoad">
    <div class="page-title" slot="page-title">
      <a @click="goPage('/productstock/mgr')">产成品库</a> -&gt;产成品出库
    </div> 
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
          <td class="page-tools">
            <Button @click="add" v-power="'ckgl.ccpck.add'" icon="plus">新建</Button>
            &nbsp;
          </td>
          <td v-if="queryForm.status==0">
            <Button @click="edit" v-power="'ckgl.ccpck.edit'" icon="edit">编辑</Button> 
            &nbsp;
            <Button @click="del" v-power="'ckgl.ccpck.del'" icon="android-delete">删除</Button>
            &nbsp;
            <Button @click="sendAudit" v-power="'ckgl.ccpck.audit'" icon="checkmark">送审</Button> 
            &nbsp;
          </td>
          <td v-if="queryForm.status==1">
            <Button @click="pass" v-power="'ckgl.ccpck.pass'" icon="checkmark">审核</Button>
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
    api="/api/productStockBill/get?stockBillId="
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
            title: '出库仓库',
            key: 'storageId',
            align:'left',
            width:150
          },
          page.table.initDateColumn({
            title: '操作日期',
            key: 'createTime',
            align:'left',
          }),
          {
            title: '发货单号',
            key: 'shippingOrder',
            align: 'left',
            width: 120
          }, 
          {
            title: '客户',
            key: 'customerName',
            align: 'left',
            width:200,
          },
          {
            title: '合同编号',
            key: 'contractId',
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
            key: 'proposer',
            align: 'left',
            width: 120,
          }),
          page.table.initPersonColumn({
            title: '操作人',
            key: 'operator',
            align: 'left',
            width: 120,
          }),
          page.table.initPersonColumn({
            title: '发货人',
            key: 'shipper',
            align: 'left',
            width: 120,
          }),
          page.table.initPersonColumn({
            title: '检验员',
            key: 'inspector',
            align: 'left',
            width: 120,
          }), 
          {
            title: '备注',
            key: 'remark',
            align: 'left',
            width: 150,
          },
          page.table.initDateColumn({
            title: '审核时间',
            key: 'auditTime',
            align: 'left',
            width: 150,
          }),
          {
            title: '审核备注',
            key: 'auditRemark',
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
          {
            title: '交货类型',
            key: 'deliveryType',
            align: 'left',
            width:150
          },
          {
            title: '产地',
            key: 'origin',
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
            key: 'quantity',
            align: 'right',
            width:150
          },
          {
            title: '总重（公斤）',
            key: 'totalWeight',
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
          stockBillId:'',
          status:0,
          operType:2,
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
          stockBillId:'',
          status:0,
          operType:2,
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.query();
      },
      curRowChg(row){
        if(row!=null){
           this.curRowId = row.stockBillId;
           this.$refs.detail.load(this.curRowId);
        }else{
          this.curRow = null;
          this.curRowId = null;
          this.$refs.detail.clear();
        }
      },
      add() { 
        this.$router.push({path:'/productstock/stockout/edit?forward'})
      },
      edit(){
        if(this.curRowId){
          this.$router.push({
            path:'/productstock/stockout/edit?forward&id=' + this.curRowId
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
          this.$http.post('/api/productStockBill/send?stockBillId=' + item.stockBillId, {}).then((res) => {
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

      auditPass(){
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
          this.$http.post('/api/productStockBill/pass?stockBillId=' + item.stockBillId, {}).then((res) => {
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
      auditReject(remark){
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
          this.$http.post('/api/productStockBill/reject',{
            stockBillId:item.stockBillId,
            auditRemark:remark
          }).then((res) => {
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
      pass(){
        this.$refs.audit.open({
          ok:(opt)=>{
            if(opt.pass){
              this.auditPass();
            }else{
              this.auditReject(opt.remark);
            }
          }
        });
      },
      del(){
          this.$Modal.confirm({
          title: '删除确认',
          content: '<p>删除后不能恢复，确定删除已选择的记录吗？</p>',
          onOk: () => {
            if(this.curRow){
            this.loading = 1;
            this.$http.post('/api/productStockBilldelete',{
              StockBillId:this.curRow.StockBillId,
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
