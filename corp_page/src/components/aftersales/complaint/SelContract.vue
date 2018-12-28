<template>
<Modal v-model="display" title="选择合同" :closable="false" :mask-closable="false" width="800" class="selcontract" :transfer="transfer"
>
  <div class="page">
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <Input v-model="queryForm.contractId" placeholder="合同编号" ></Input>
          </td> 
          <td>
            <Input v-model="queryForm.customerName" placeholder="客户" ></Input>
          </td>
          <td >开始日期</td>
          <td><DatePicker type="daterange" v-model="queryForm.createTime"
           split-panels placeholder="开始日期"
           format="yyyy-MM-dd"
           style="width: 180px" :clearable="false" ::transfer="true"></DatePicker></td>
          <td>
            <Button @click="query"  type="primary" icon="ios-search">查询</Button>
          </td>
          <td>
            <Button @click="reset" >重置</Button>
          </td>
          <td>
            <Button @click="onCancel"  type="default" >取 消</Button>
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

      <Page ref="pagebar" :total="total" size="small" :page-size-opts="[5,10,20,30]" 
          @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top" :transfer="true" ></Page>
    </div>
    </Loading>
  </div>
  <div slot="footer" >
    <Editable :editable="false">
      <table cellspacing="0" cellpadding="0">
        <thead>
          <th >选择</th>
          <th class="col-xh">序号</th> 
          <th >物料编码</th>
          <th >物料名称</th>
          <th >图号</th>
          <th >铸件号</th>
          <th >材质</th> 
          <th >件数</th> 
        </thead>
        <tbody>
           <tr v-for="(item,index) in details"  :key="'mater_'+index" >
            <td>
              <Button @click="onOK(item)"  type="primary" icon="checkmark">选 择</Button>
            </td>
            <td>
              {{index+1}}
            </td>
            <td>
              {{item.materId}}<!--物料编码 -->
            </td>
            <td>
              {{item.materName}} <!--物料名称 -->
            </td>
            <td>
              {{item.drawing}}<!--图号 -->
            </td>
            <td>
              {{item.casting}}<!--铸件号 -->
            </td>
            <td>
              {{item.texture}}<!--材质 -->
            </td>
            <td>
              {{item.quantity}}<!--件数 -->
            </td>
          </tr>
        </tbody>
      </table>
    </Editable>
    <!-- <SelAudit ref="audit"></SelAudit> -->
  </div>
</Modal>
</template>
<script>
  import Loading from '@/components/loading';
  import Editable from '@/components/editable-table';
  import page from '@/assets/js/page';
  export default {
    components: {
      Loading,
      Editable,
      page
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
            title: '合同编号',
            key: 'contractId',
            width: 120,
          },
          {
            title: '客户',
            key: 'customerName',
            align: 'left'
          },
          {
            title: '交货地点',
            key: 'deliveryAddress',
            align: 'left',
          },
          page.table.initPersonColumn({
            title: '业务员',
            key: 'sales',
            align: 'left',
          }),
          {
            title: '开始日期',
            key: 'createTime',
            align: 'left',
          },
        ], 
        display:false,
        list: [],
        details:[],
        bill:null,
        total:0,
        queryParam: {},
        queryForm: {
          contractId:'',
          contractNo:'',
          deliveryAddress:'',
          customerName:'',
          sales:'',
          createTime:'',
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
        // this.queryParam.contractId = this.options.contractId;
        this.queryParam.status=2;
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
            // Object.assign(this.queryForm, this.queryParam);
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
      loadDetails(id) { 
        this.loading = 1;
        this.$http.post("/api/contract/get?contractId=" + id).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.loading = 0;
            if(res.data.data != null){
              this.details = res.data.data.detailList;
              this.bill = res.data.data;
            }else{
              this.details = [];
            }
          } else {
            this.loading = 0;
            this.details = null; 
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新发送")
        });
      },
      query: function () {
        Object.assign(this.queryParam,this.queryForm);
        var queryParam = this.queryParam;
        this.queryParam.createTimeStart = '';
        this.queryParam.createTimeEnd = '';
        delete this.queryParam.createTime;
        if(this.queryForm.createTime.length > 0){
          queryParam.createTimeStart = page.formatDate(this.queryForm.createTime[0]);
        }
        if(this.queryForm.createTime.length > 1){
          queryParam.createTimeEnd = page.formatDate(this.queryForm.createTime[1]);
        }
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
        this.bill = null;
        for(var i = 0;i<this.list.length;i++){
          var item = this.list[i];
          item._checked = index == i ;
        }
        this.loadDetails(this.list[index].contractId);
      },
      reset: function () {
        Object.assign(this.queryForm,
        {
        contractId:'',
        customerName:'',
        createTime:'',
      });
        this.list = [];

        this.details = [];
        this.bill = null;

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
      onOK(item) { 
        var select = this.bill;
        if(select == null){
          this.$Message.error('请选择合同');
          return;
        }
        this.display = false;
        this.options.ok(select,item);
      },
      onCancel(){
        this.display = false;
      },
    }
  }

</script>

<style type="text/css">
  .selinstockaply .page{
    padding: 0px;
  }
  .selinstockaply .page-searchbox{
    margin-top: 0px;
  }
  .selinstockaply .status-2{
    color:#ff6600;
  }
  .selinstockaply .footer{
    text-align: center;
    padding-left: 10px;
  }
  .selinstockaply .row-checked td{
    background-color: #e8f8fd;
    /*color:#20bfee;*/
    font-weight: bold;
  }
</style>
