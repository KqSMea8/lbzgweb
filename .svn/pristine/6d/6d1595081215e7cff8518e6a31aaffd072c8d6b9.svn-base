<template>
  <ListPage title="采购订单维护" ref="page" api="/api/order/list" 
  :model="this" :beforeLoad="beforeLoad">  
    <div class="page-tools">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td><Button @click="addInspection" v-power="'cggl.cgddwh.add-bj'" icon="plus">新建报检申请单</Button></td>
          <td><Button @click="add" v-power="'cggl.cgddwh.add-rk'" icon="plus">新建入库申请单</Button></td>
        </tr>
      </table>
    </div> 
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <Input v-model="queryForm.departmentName"  readonly="readonly" @on-click="selDept" placeholder="申购部门" class="width-1"  icon="search" />
          </td>  
          <td>
            <Select v-model="queryForm.subType" style="width:150px" placeholder="物料类别">
              <Option v-for="item in types" :value="item.argCode" :key="item.argCode">{{ item.argText }}</Option>
            </Select>
          </td>  
          <td>
            <Input v-model="queryForm.purchaseOrderId" placeholder="物料名称" ></Input>
          </td>
          <td>起止日期</td>
          <td><DatePicker type="daterange" v-model="queryForm.createTime"
           split-panels placeholder="起止日期"
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
    <SelContacts ref="selContacts"></SelContacts> <!-- 新加部门选择控件 -->
  </ListPage>
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar'; 
  import page from '@/assets/js/page';
  import SelContacts from '@/components/selcontacts'; //引用选择部门控件

  export default {
    components: {
      ListPage,
      DataRowOperateBar, 
      SelContacts
    },
    data() {
      let that = this;
      return {
        columns: [
          {
            title: '单号',
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
                    this.$router.push({path:'/purchase-order-mgr/view?forword&id='+row.purchaseOrderId});
                  }
                }
              },text);
            }
          },
          {
            title: '申购日期',
            key: 'createTime',
            align: 'center',
            width: 120,
            render:(h, params)=>{
              var row = params.row;
              var dt = row.createTime;
              return h('span', dt && dt.length>=10?dt.substring(0,10):dt);
            }
          },
          {
            title: '申购人',
            key: 'proposer',
            align: 'center',
            render:(h,params)=>{
              var row = params.row;
              return h('span', this.$args.getArgText('empList',row.proposer));
            },
            width:100,
          },
          {
            title: '申购部门',
            key: 'department',
            align: 'center',
            render:(h,params)=>{
              var row = params.row;
              return h('span', this.$args.getArgText('deptList',row.department));
            },
            width:100,
          },
          {
            title: '质量要求',
            key: 'quality',
            align: 'left', 
          }, 

          {
            title: '备注',
            key: 'remark',
            align: 'center',
            width: 80,
          },
          {
            title: '操作',//操作按钮列表
            width: 80,
            align: 'center',
            render: function (h, params) {
              return h(DataRowOperateBar, {
                props: {
                  commands: '创建报检单,创建入库单'
                },
                on: {
                  'on-command': (name) => {
                    that.rowCommand(name, params)
                  }
                }
              });
            }
          }
        ], 
        queryForm: {
          department:'',
          departmentName:'',//选择部门默认
          subType:'',//物料类别
          status:'2',
          purchaseOrderId:'',//物料名称
          createTime:null//起止日期
        },
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
          department:'',
          departmentName:'',//选择部门默认
          subType:'',
          status:'2',
          purchaseOrderId:'', 
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.query();
      },  

      rowCommand: function (name, params) {
        if (name === '创建报检单') {
          if(!this.$user.hasPower('cggl.cgddwh.create-bj')){
            this.$Message.error('暂无权限！');
            return;
          }
          this.updateInspection(params.row);
          return;
        }
        if (name === '创建入库单') {
          if(!this.$user.hasPower('cggl.cgddwh.create-rk')){
            this.$Message.error('暂无权限！');
            return;
          }
          this.update(params.row);
          return;
        }
       
      }, 
      add: function () {//新建入库单---空白单
        this.$router.push({path:'/purchase-order-mgr/edit?forwoard'})
      },
    
      update: function (item) {//创建入库单
        this.$router.push({path:'/purchase-order-mgr/edit?forwoard&id='+ item.purchaseOrderId})// 采购单号
      },

      addInspection: function () {//新建报检单---空白单
       this.$router.push({path:'/purchase-order-mgr/editInspection?forwoard'})
      },

      updateInspection: function (item) {//创建报检单---更新操作
        this.$router.push({path:'/purchase-order-mgr/editInspection?forwoard&id='+ item.purchaseOrderId})// 采购单号
      },
      
      //选择部门
      selDept(){
        var sel = this.$refs.selContacts;
        sel.show({
           isMulti:false,
           selectPerson:false,
           selectDept:true,
           ok:()=>{ 
            if(sel.select.length>0){
              this.queryForm.departmentName = sel.select[0].title;
              this.queryForm.department = sel.select[0].key;
            }
          }
        });
      },  
    }
  }

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
