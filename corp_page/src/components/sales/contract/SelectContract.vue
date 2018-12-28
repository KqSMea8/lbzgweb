<template>
<Modal v-model="display" title="选择合同" :closable="false" :mask-closable="false" width="800" class="selcontract" :transfer="transfer">
  <div class="page">
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <Input v-model="queryForm.contractId" placeholder="合同编号" ></Input>
          </td>
          <td>
            <Button @click="query"  type="primary" icon="ios-search">查询</Button>
          </td>
          <td>
            <Button @click="reset" >重置</Button>
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
  <div slot="footer" class="footer">
    <Button @click="onOK"  type="primary" icon="checkmark">确 定</Button>
    <Button @click="onCancel"  type="default" >取 消</Button>
  </div>
</Modal>
</template>
<script>
  import Loading from '@/components/loading';
  export default {
    components: {
      Loading,
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
            key: '_checked',
            width: 60,
            render:function(h,params) {
              var row = params.row;
              var index = params.index;
              var props={
                value:row._checked,
              }; 
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
            title: '合同Id',
            key: 'contractId',
            width: 120,
          },
          {
            title: '合同编号',
            key: 'contractNo',
            width: 120,
          },
          {
            title: '客户名称',
            key: 'customerName',
            align: 'left',
            width: 120,
          },
          {
            title: '交货地点',
            key: 'deliveryAddress',
            align: 'left',
            width: 120,
          },
          {
            title: '业务员',
            key: 'salesName',
            align: 'left',
            width: 120,
          },
        ],
        display:false,
        list: [],
        total:0,
        queryParam: {},
        queryForm: {
          contractId: '',
          //status:1
        },
        selected:[],
        loading: 0,
        options:{
          ok:(data)=>{},
          cancel:()=>{}
        }
      }
    },
    mounted: function () {
      this.query()
    },
    computed: {},
    methods: {
      load() {
        var pagebar = this.$refs.pagebar;
        this.loading = 1;
        this.queryParam.page = pagebar.currentPage;
        this.queryParam.pageSize = pagebar.currentPageSize;
        this.$http.post("/api/contract/list", this.queryParam).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.loading = 0;
            var list = res.data.data.rows;
            list.map((item)=>{item._checked=false;});
            this.list = list;
            this.total = res.data.data.total;
            this.selection = [];
            // 同步条件信息到表单
            Object.assign(this.queryForm, this.queryParam);
          } else {
            this.loading = 0;
            this.list = null;
            this.total = 0;
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新发送")
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
          item._checked = index == i;
        }
      },
      reset: function () {
        Object.assign(this.queryForm,{
          contractId:'',
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
      // 需要遗弃，请调用 show
      open() {
        this.display = true;
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
          this.$Message.error('请选择合同');
          return;
        }
        this.display = false;
        this.options.ok(select);

        // 以下代码需要遗弃
        this.selected = [];
        this.list.map((item)=>{
          if(item._checked){
            this.selected.push(item);
          }
        });
        this.display = false;
        
        this.$emit('on-ok',this.selected); 
      },
      onCancel(){
        this.display = false;
      }
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
