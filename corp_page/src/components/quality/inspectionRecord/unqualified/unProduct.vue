<template>
  <Modal v-model="display" title="选择生产不合格品单号" :closable="close" :mask-closable="true" width="1000" class="selcustomer" :transfer="transfer">
    <div class="page">
      <div class="page-searchbox">
        <table cellpadding="0" cellspacing="10px">
          <tr>
            <td>不合格品单号：</td>
            <td>
              <Input v-model="queryForm.materId" placeholder="不合格品单号" ></Input>
            </td>
            <td>开始日期：</td>
            <td>
              <DatePicker v-model="queryForm.startTime" type="daterange" split-panels placeholder="开始日期" style="width: 200px"></DatePicker>
            </td>
            <td>报告部门：</td>
            <td>
              <Input v-model="queryForm.departmentName" readonly="readonly"
                     style="cursor: pointer;" placeholder="报告部门"  @click.native="selDept" icon="search"></Input>
            </td>
          </tr>
          <tr>
            <td>检验员：</td>
            <td>
              <Input v-model="queryForm.proposerName" readonly="readonly"
                     style="cursor: pointer;" placeholder="检验员" @click.native="selEmp" icon="search"></Input>
            </td>
            <td>
              <Button @click="query"  type="primary" icon="ios-search">查询</Button>
            </td>
            <td>
              <Button @click="reset" >重置</Button>
            </td>
          </tr>
          <tr>
            <td>
              <Button @click="goPage('/inspectionRecord/correct?id=')"  type="info" icon="ios-recording" size="large">纠正措施</Button>
            </td>
            <td>
              <Button @click="goPage('/inspectionRecord/concession?id=')"  type="info" icon="ios-filing" size="large">让步就收</Button>
            </td>
            <td>
              <Button @click="goPage('/inspectionRecord/d_report?id=')" type="info" size="large">8D报告</Button>
            </td>
            <td>
              <Button @click="goPage('/inspectionRecord/requisition?id=')"  type="primary">编辑</Button>
            </td>
            <td>
              <Button @click="goPage('/inspectionRecord/requisition?browse=1&id=')"  type="primary">浏览</Button>
            </td>
            <td>
              <Button @click="close"  type="dashed">退出</Button>
            </td>
          </tr>
        </table>
      </div>
      <Loading :loading="loading">
        <div class="page-datatable">
          <i-table :row-class-name="rowClassName"
                   :columns="columns"
                   @on-row-click="innerCheckRow(arguments[1])"
                   :data="list"></i-table>

          <div style="height:10px;"></div>

          <Page ref="pagebar" :total="total" size="small"
                @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top" :transfer="true"></Page>
        </div>
      </Loading>
    </div>
<!--    <div slot="footer" class="footer">
      <Button @click="onOK"  type="primary" icon="checkmark">确 定</Button>
      <Button @click="onCancel"  type="default" >取 消</Button>
    </div>-->
    <SelContacts ref="selContacts"></SelContacts>
    <div slot="footer"></div>
  </Modal>
</template>
<script>
  import Loading from '@/components/loading';
  import SelContacts from '@/components/selcontacts';
  export default {
    components: {
      Loading,
      SelContacts
    },
    props:{
      transfer:{
        type:Boolean,
        default:true
      }
    },
    data() {
      var that = this;
      return {
        columns: [
          {
            title: '选择',
            key: 'index',
            width: 60,
            render:function(h,params) {
              var row = params.row;
              var index = params.index;
              var props={
                value:row._checked,
              };
              if(row.status == "2"){
                props.disabled =true;
              }
              return h('Checkbox',{
                props:props,
                on:{
                  'on-change':()=>{
                    that.innerCheckRow(index);
                  }
                }
              });
            }
          },
          {
            title: '不合格品单号',
            key: 'unqualifiedId',
            width: 120,
          },
          {
            title: '报告类别',
            key: 'reportType',
            align: 'left'
          },
          {
            title: '合同编号',
            key: 'contractId',
            align: 'left',
          },
          {
            title: '物料名称',
            key: 'materName',
            width: 80,
            align: 'center',
          },
          {
            title: '图号',
            key: 'drawing',
            width: 80,
            align: 'center',
          },
          {
            title: '材质',
            key: 'texture',
            width: 80,
            align: 'center',
          },
          {
            title: '铸件号',
            key: 'casting',
            width: 80,
            align: 'center',
          },
          {
            title: '报告部门',
            key: 'department',
            width: 80,
            align: 'center',
          },
          {
            title: '检验员',
            key: 'inspector',
            width: 80,
            align: 'center',
          },
          {
            title: '报告日期',
            key: 'reportDate',
            width: 80,
            align: 'center',
          },
          {
            title: '不合格原因',
            key: 'remark',
            width: 80,
            align: 'center',
          },
        ],
        display:false,
        list: [],
        total:0,
        queryParam: {},
        queryForm: {
          materId:'',
          startTime:'',
          departmentName:'',
          proposerName:'',
        },
        industry:[],
        selected:[],
        loading: 0,
        options:{
          ok:(data)=>{},
          cancel:()=>{}
        }
      }
    },
    mounted: function () {
      this.loadGroups();
      this.query()
    },
    computed: {},
    methods: {
      loadGroups: function () {
        this.loading = 1;
      },
      load() {
        var pagebar = this.$refs.pagebar;
        this.loading = 1;
        this.queryParam.page = pagebar.currentPage;
        this.queryParam.pageSize = pagebar.currentPageSize;
        this.$http.post("api/", this.queryParam).then((res) => {
          this.loading = 0;
          var list = res.data.data.rows;
          list.map((item)=>{item._checked=false;});
          this.list = list;
          this.total = res.data.data.total;
          this.selection = [];
          // 同步条件信息到表单
          Object.assign(this.queryForm, this.queryParam);
        }).catch((error) => {
          this.loading = 0;
          this.$Message.info("生产不合格,工序系统创建,请输入对应的api") 
        });
      },
      query: function () {
        Object.assign(this.queryParam,this.queryForm)
        this.load();
      },
      pageChange: function (page) {
        this.load();
      },
      pageSizeChange:function(pageSize){
        var pagebar = this.$refs.pagebar;
        if(pagebar.currentPage == 1){
          this.load();
        }
      },
      innerCheckRow(index){
        for(var i = 0;i<this.list.length;i++){
          var item = this.list[i];
          item._checked = index == i && item.status!=2;
        }
      },
      reset: function () {
        /*
        * queryForm: {
          materId:'',
          startTime:'',
          departmentName:'',
          proposerName:'',
        },*/
        Object.assign(this.queryForm,{
          materId:'',
          startTime:'',
          departmentName:'',
          proposerName:'',
        });

        var pagebar = this.$refs.pagebar;
        pagebar.current = 1;
        pagebar.currentPage = 1;
        pagebar.currentPageSize = 10;

        this.query();
      },
      rowClassName(row,index){
        return row._checked?'row-checked':'';
      },
      // 对外方法
      show(options){
        Object.assign(this.options,{
          ok:(data)=>{},
          cancel:()=>{}
        },options);
        this.display = true;
        this.reset();
      },
      close() {
        this.display = false;
      },
      onOK() {
        var select = null;
        this.list.map((item)=>{
          if(item._checked){
            select = item;
          }
        });
        if(select == null){
          this.$Message.error('请选择客户');
          return;
        }
        this.options.ok(select);
        /*// 以下代码需要遗弃
        this.selected = [];
        this.list.map((item)=>{
          if(item._checked){
            this.selected.push(item);
          }
        });*/
        this.display = false;
        this.$emit('weigh_get',this.selected);
      },
      onCancel(){
        this.display = false;
      },
      selDept(){
        var sel = this.$refs.selContacts;
        sel.show({
          isMulti:false,
          selectPerson:false,
          selectDept:true,
          ok:()=>{
            if(sel.select.length>0){
              this.queryForm.departmentName = sel.select[0].title;
              //this.queryForm.department = sel.select[0].key;
            }
          }
        });
      },
      selEmp(){
        var sel = this.$refs.selContacts;
        sel.show({
          isMulti:false,
          selectPerson:true,
          selectDept:false,
          ok:()=>{
            if(sel.select.length>0){
              this.queryForm.proposerName = sel.select[0].title;
              //this.queryForm.operator = sel.select[0].key;
            }
          }
        });
      },
      goPage(uri){
        var select = null;
        this.list.map((item)=>{

          if(item._checked){
            select = item;
          }
        });
        this.$router.push(uri+select.materId+"&department="+ select.department+"&purchaseOrderId="+select.purchaseOrderId+"&unqualifiedId="+select.unqualifiedId);
     },
    }
  }

</script>

<style type="text/css">
  .selcustomer .page{
    padding: 0px;
  }
  .selcustomer .page-searchbox{
    margin-top: 0px;
  }
  .selcustomer .status-2{
    color:#ff6600;
  }
  .selcustomer .footer{
    text-align: center;
    padding-left: 10px;
  }
  .selcustomer .row-checked td{
    background-color: #e8f8fd;
    /*color:#20bfee;*/
    font-weight: bold;
  }
</style>
