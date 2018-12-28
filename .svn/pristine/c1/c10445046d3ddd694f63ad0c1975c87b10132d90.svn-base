<template>
<Modal v-model="display" title="选择销售发货单" :closable="false" :mask-closable="false" width="800" class="selshipping" :transfer="transfer">
  <div class="page">
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <Input v-model="queryForm.shippingId" placeholder="发货单号" ></Input>
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

      <Page ref="pagebar" :total="total" size="small" :page-size-opts="[5,10,20,30]" 
          @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top" :transfer="true" ></Page>
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
          <th >图号</th>
          <th >铸件号</th>
          <th >单位</th>
          <th >数量</th>
          <th >单重</th>
          <th >总重</th>
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
              {{item.drawing}}<!--图号 -->
            </td>
            <td>
              {{item.casting}}<!--铸件号 -->
            </td>
            <td>
              {{item.unit}}<!-- 单位-->
            </td>
            <td> 
              {{item.quantity}}<!--数量  -->
            </td>
            <td> 
              {{item.weight}}<!--重量  -->
            </td>
            <td> 
              {{item.weightTotal}}<!--总重  -->
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
            title: '发货单号',
            key: 'shippingId',
            width: 120,
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
        ], 
        display:false,
        list: [],
        details:[],
        bill:null,
        total:0,
        queryParam: {},
        queryForm: {
          shippingId: '',
          status:2,
          contractId:'',
          contractNo:'',
          deliveryAddress:'',
          customerName:'',
          sales:'',
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
        this.queryParam.shippingId = this.options.shippingId;
        this.$http.post("/api/shipping/list", this.queryParam).then((res) => {
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
        this.$http.post("/api/shipping/get?shippingId=" + id).then((res) => {
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
        this.loadDetails(this.list[index].shippingId);
      },
      reset: function () {
        Object.assign(this.queryForm,{
          shippingId: '',
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
