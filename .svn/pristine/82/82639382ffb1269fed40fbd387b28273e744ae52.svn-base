<template>
  <ListPage ref="page" api="/api/stock/bill/list" 
  :model="this" @onCurrentRowChange="curRowChg" :beforeLoad="beforeLoad">
    <div class="page-title" slot="page-title">
      <a @click="goPage('/storage/mgr')">仓库管理</a> -&gt;单据审核
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
              <Radio :label="1">待审核</Radio> 
              <Radio :label="2">通过</Radio>
              <Radio :label="3">驳回</Radio>
            </RadioGroup>
          </td>
          <td class="page-tools">
            <td v-if="queryForm.status==1">
              <Button @click="pass" v-power="'ckgl.djsh.audit'" icon="checkmark">审核</Button>
            </td>
          </td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <Input v-model="queryForm.stockBillId" placeholder="单据号" ></Input>
          </td>
          <td>操作原因</td>
          <td>
            <Select v-model="queryForm.operType" style="width:100px">
              <Option v-for="item in operTypes" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
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
    <SelAudit ref="audit"></SelAudit>
  </ListPage> 
    
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import ListPageDetail from '@/components/page/ListPageDetail';
  import SelStorage from '@/components/storage/input/SelStorage'; 
  import SelStockOperType from './SelStockOperType'; 
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
        operTypes: [
              {
                  value: '1',
                  label: '入库'
              },
              {
                  value: '2',
                  label: '出库'
              },
              {
                  value: '3',
                  label: '移库'
              },
          ],
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
            title: '单据编号',
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
                     
                  }
                }
              },text);
            }
          },
          page.table.initDateColumn({
            title: '单据日期',
            key: 'billDate',
            align:'left',
          }), 
          page.table.initArgColumn({
            title: '操作原因',
            key: 'operType',
            group:'stockOperTypeList',
            align:'left',
            width:120
          }),
          page.table.initArgColumn({
            title: '仓库',
            key: 'storageId',
            group:'storageList',
            align:'left',
            width:150
          }),
          page.table.initArgColumn({
            title: '移入仓库',
            key: 'relatedStorageId',
            group:'storageList',
            align:'left',
            width:150
          }),
          page.table.initPersonColumn({
            title: '申请人',
            key: 'proposer',
            align:'left',
          }),
          page.table.initDepartmentColumn({
            title: '申请部门',
            key: 'department',
            align:'left',
          }),
          page.table.initDateColumn({
            title: '创建日期',
            key: 'createTime',
            align:'left',
          }),
          {
            title: '关联单据',
            key: 'relatedBillId',
            align: 'left',
            width: 120
          }, 
          {
            title: '驳回原因',
            key: 'auditRemark',
            align: 'left',
            width: 120,
          },
          page.table.initDateColumn({
            title: '驳回日期',
            key: 'auditTime',
            align:'left',
          }),
          {
            title: '备注',
            key: 'remark',
            align: 'left',
            width:100,
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
          page.table.initDateColumn({
            title: '生产日期',
            key: 'madeDate',
            align: 'left',
          }),
          {
            title: '货位',
            key: 'placeName',
            align: 'left',
            width:150
          },
          {
            title: '数量',
            key: 'quantity',
            align: 'right',
            width:150
          },
          page.table.initDateColumn({
            title: '到厂日期',
            key: 'arrivalDate',
            align: 'left',
          }), 
          page.table.initDateColumn({
            title: '有效日期',
            key: 'validityDate',
            align: 'left',
          }), 
          page.table.initDateColumn({
            title: '质保期',
            key: 'qualityTime',
            align: 'left',
          }), 
          page.table.initArgColumn({
            title: '单位',
            key: 'unit',
            group:'unit',
            align: 'left',
            width:150
          }),
          {
            title: '件数',
            key: 'packageCount',
            align: 'right',
            width:120
          },
          {
            title: '车号',
            key: 'vehicleNo',
            align: 'left',
            width:120
          },
          {
            title: '毛重',
            key: 'roughWeight',
            align: 'left',
            width:120
          },
          {
            title: '扣杂',
            key: 'takeOutWeight',
            align: 'left',
            width:120
          },
          {
            title: '皮重',
            key: 'packageWeight',
            align: 'left',
            width:120
          },
          {
            title: '单价(元)',
            key: 'price',
            align: 'right',
            width:150
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
            align: 'left',
            width:100,
          },
        ], 
        queryForm: {
          storageId:this.$user.config.data.storageId,
          stockBillId:'',
          status:1,
          operType:'', 
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
          status:1,
          operType:'', 
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
      switStorage(){
        this.$user.config.data.storageId = this.queryForm.storageId;
        this.reset();
      }, 
      auditPass() { 
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
          this.$http.post('/api/stock/bill/audit/pass?stockBillId=' + item.stockBillId, {}).then((res) => {
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
          this.$http.post('/api/stock/bill/audit/reject',{
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
      goPage(page){
        this.$router.push({path:page});
      }
    }
  }

</script>

<style type="text/css">
  
</style>
