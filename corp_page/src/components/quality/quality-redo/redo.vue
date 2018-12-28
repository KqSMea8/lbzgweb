<template>
    <ListPage title="补做通知单管理" ref="page" api="/api/order/list" @onCurrentRowChange="curRowChg"
              :model="this" :beforeLoad="beforeLoad">

        <div class="page-searchbox">
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        <Input v-model="queryForm.purchaseOrderId" placeholder="补做单编号" ></Input>
                    </td>

                    <td>
                        <Input v-model="queryForm.providerName" :readonly="true" style="cursor: default;" placeholder="订货单位" icon="search" @on-click="selProvider" ></Input>
                    </td>
                    <td>
                        <Input v-model="queryForm.purchaseOrderId" placeholder="图号" ></Input>
                    </td>
                    <td>采购日期</td>
                    <td><DatePicker type="daterange" v-model="queryForm.createTime"
                                    split-panels placeholder="采购日期"
                                    style="width: 180px" :clearable="false" ::transfer="true"></DatePicker></td>
                    <td>
                        <Button @click="query"  type="primary" icon="ios-search">查询</Button>
                    </td>
                    <td>
                        <Button @click="reset" >重置</Button>
                    </td>
                    <hr>

                </tr>
            </table>
        </div>
        <div class="page-tools">
            <table cellpadding="0" cellspacing="0">
                <tr>

                    <td><Button @click="add"  icon="plus">新建</Button></td>
                    <td><Button @click="sendAudit"  icon="checkmark">送审</Button></td>
                    <td><Button @click="edit"  icon="edit">编辑</Button></td>
                    <td><Button @click="delDept"  icon="android-delete">删除</Button></td>
                    <td><Button @click="out"  >退出</Button></td>
                </tr>
            </table>
        </div>
        <ListPageDetail ref="detail" slot="page-datatable-detail"
                        api="/api/providerAdjustPrice/get?pmapId="
                        :columns="columns1"
                        :objId="curRowId">
        </ListPageDetail>
        <SelProvider ref="selProvider"></SelProvider>
    </ListPage>
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import page from '@/assets/js/page';
  import ListPageDetail from '@/components/page/ListPageDetail';
  import SelStorage from '@/components/storage/input/SelStorage';
  import SelStockOperType from '@/components/storage/input/SelStockOperType';

  import SelProvider from '@/components/provider/SelectProvider';

  export default {
    components: {
      ListPage,
      ListPageDetail,
      DataRowOperateBar,
      SelProvider  //声明新组件
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
            title: '补做单编号',
            key: 'purchaseOrderId',
            width: 140,
            align: 'center',
            render:(h,params)=>{
              var row = params.row;
              var text = row.purchaseOrderId;
              text = text;
              return h('a',{
                props:{

                },
                on:{
                  click:()=>{
                    this.$router.push({path:'/quality-redo/view?forword&id='+row.purchaseOrderId});
                  }
                }
              },text);
            }
          },
          page.table.initPersonColumn({
            title: '操作人',
            key: 'proposer',
          }),
          page.table.initDepartmentColumn({
            title: '订货单位',
            key: 'department',
          }),
          {
            title: '质量要求',
            key: 'quality',
            align: 'left',
          },
          page.table.initDateColumn({
            title: '操作日期',
            key: 'createTime',
          }),

          {
            title: '驳回原因',
            key: 'quality',
            align: 'left',
          },
          {
            title: '驳回日期',
            key: 'quality',
            align: 'left',
          },
          {
            title: '备注',
            key: 'quality',
            align: 'left',
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
            title: '物料代码',
            key: 'materId',
            fixed: 'center',
            align: 'center',
            width: 120,
          },
          {
            title: '物料名称',
            key: 'materName',
            fixed: 'center',
            align: 'center',
            width: 120,
          },
          {
            title: '图号',
            key: 'spec',
            align: 'center',
            width: 120,
          },
          {
            title: '材质',
            key: 'model',
            align: 'center',
            width: 120,
          },
          {
            title: '毛重',
            key: 'subType',
            align: 'center',
            width: 100,
          },
          {
            title: '件数',
            key: 'use',
            align: 'center',
            width: 160,
          },
          {
            title: '顺序号',
            key: 'afterPrice',
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

        list: [],
        total:0,
        queryParam: {},

        queryForm: {
          subType:'',
          status:null,
          purchaseOrderId:'',
          createTime:null
        },
        loading: 0,
        types:[
          {argCode:'1',argText:'设备配件'},
          {argCode:'2',argText:'常规物料'},
          {argCode:'3',argText:'办公用品'}
        ],
      }
    },
    mounted: function () {
      this.reset();
    },
    computed: {},
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
          subType:'',
          status:null,
          purchaseOrderId:'',
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.query();
      },
      curRowChg(row){
        if(row!=null){
           this.curRow = row;
           this.curRowId = row.purchaseOrderId;
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
      add: function () {
        this.$router.push({path:'/quality-redo/edit?forward'});
      },
      edit(){
        console.log(this.curRowId);
        if(this.curRowId){
          this.$router.push({
            path:'/quality-redo/edit?forward&id=' + this.curRowId
          })
        }else{
          this.$Message.error('请选择要编辑的入库单');
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
          this.$http.post('/api/providerAdjustPrice/submit?pampId=' + item.stockBillId, {}).then((res) => {
            if (res.data.code === 0) {
              this.$Message.success("第 "+ (start+1) +" 条操作成功");
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
            this.$Message.error(error)
          });
        }
        handle();
      },
      delDept(deptId){
        this.$Modal.confirm({
          title: '删除确认',
          content: '<p>确定删除该部门吗？</p>',
          closable:true,
          onOk: () => {
            let that = this;
            this.$http.post('/api/contacts/dept/delete?deptId=' + deptId).then((res) => {
              if (res.data.code === 0) {
                that.loadDept();
              } else {
                this.$Message.error(res.data.message)
              }
            }).catch((error) => {
              this.$Message.error(error.message)
            });
          }
        });
      },
      out: function () {
        this.$router.push({path:'/quality-redo'});
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
      },

      update: function (item) {
        this.$refs.edit.open(item);
      }
    }
  }

</script>

<style type="text/css">

</style>
