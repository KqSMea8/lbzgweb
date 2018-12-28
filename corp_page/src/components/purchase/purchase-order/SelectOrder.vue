<template>
<Modal v-model="display" title="选择采购单" :closable="false" :mask-closable="false" width="800" class="selectorder" :transfer="transfer">
  <div class="page">
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <Input v-model="queryForm.purchaseOrderId" placeholder="采购单号" ></Input>
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
  <div slot="footer" >
    <div class="footer">
      <Button @click="onOK"  type="primary" icon="checkmark">确 定</Button>
      <Button @click="onCancel"  type="default" >取 消</Button>
    </div>
    <Editable :editable="false">
      <table cellspacing="0" cellpadding="0">
        <thead>
          <th class="col-xh">序号</th> 
          <th >物料编码</th>
          <th>物料名称</th>
          <th >规格</th>
          <th >型号</th>
          <th >单位</th>
          <th >数量</th>
          <th >用途</th> 
          <th >需求日期</th>
          <th >到货数量</th> 
          <th >单价</th> 
          <th >金额</th> 
          <th >备注</th> 
        </thead>
        <tbody>
           <tr v-for="(item,index) in details"  :key="'mater_'+index" >
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
              {{item.spec}}<!--规格 -->
            </td>
            <td>
              {{item.model}}<!--型号 -->
            </td>
            <td>
              {{$args.getArgText('unit',item.unit)}}<!-- 单位-->
            </td>
            <td> 
              {{item.quantity}}<!--申购数量  -->
            </td>
            <td> 
              {{item.use}}<!--用途 -->
            </td> 
            <td>
               {{item.needDate}}<!--需求日期 -->
            </td>
             <td>
               {{item.quantity}}<!-- 到货数量-->
            </td> 
            </td>
             <td>
              {{item.price}}<!--单价 -->
            </td>
            <td>
              {{item.amount}}<!--金额 -->
            </td> 
            <td>
              {{item.remark}}<!-- 备注-->
            </td>
          </tr>
        </tbody>
      </table>
    </Editable>
  </div>
</Modal>
</template>
<script>
  import Loading from '@/components/loading';
  import Editable from '@/components/editable-table';
  import page from '@/assets/js/page';
  export default {
    components: {
      Loading,Editable
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
            title: '采购单',
            key: 'purchaseOrderId',
            align: 'left'
          },
          page.table.initDateColumn({
            title: '采购日期',
            key: 'createTime',
          }),
          page.table.initPersonColumn({
            title: '采购人',
            key: 'proposer',
          }),
          page.table.initDepartmentColumn({
            title: '采购部门',
            key: 'department',
          }),
        ], 
        display:false,
        list: [],
        details:[],
        bill:null,
        total:0,
        queryParam: {},
        queryForm: {
          purchaseOrderId: '',
          industry:'',
          status:2
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
      this.query()
    },
    computed: {},
    methods: {
      load() {
        var pagebar = this.$refs.pagebar;
        this.loading = 1;
        this.queryParam.page = pagebar.currentPage;
        this.queryParam.pageSize = pagebar.currentPageSize;
        this.$http.post("/api/order/list", this.queryParam).then((res) => {
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
      loadDetails(id) { 
        this.loading = 1;
        this.$http.post("/api/order/get?purchaseOrderId=" + id).then((res) => {
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
        this.bill = null;
        for(var i = 0;i<this.list.length;i++){
          var item = this.list[i];
          item._checked = index == i && item.status!=2;
        }
        this.loadDetails(this.list[index].purchaseOrderId);
      },
      reset: function () {
        Object.assign(this.queryForm,{
          purchaseOrderId: '',
          industry:'',
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
      onOK() { 
        var select = this.bill;
        if(select == null){
          this.$Message.error('请选择单据');
          return;
        }
        this.display = false;
        this.options.ok(select);
      },
      onCancel(){
        this.display = false;
      }
    }
  }

</script>

<style type="text/css">
  .selectorder .page{
    padding: 0px;
  }
  .selectorder .page-searchbox{
    margin-top: 0px;
  }
  .selectorder .status-2{
    color:#ff6600;
  }
  .selectorder .footer{
    text-align: center;
    padding-left: 10px;
  }
  .selectorder .row-checked td{
    background-color: #e8f8fd;
    /*color:#20bfee;*/
    font-weight: bold;
  }
</style>
