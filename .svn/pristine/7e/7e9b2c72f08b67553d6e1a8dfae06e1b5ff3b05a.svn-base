<template>
    <Modal v-model="display" title="导入合同" :closable="false" :mask-closable="false" width="800" class="selcustomer" :transfer="transfer">
        <div class="page">
            <div class="page-searchbox">
                <table cellpadding="0" cellspacing="0">
                    <tr>
                        <td>铸件号：</td>
                        <td>
                            <Input v-model="queryForm.casting" placeholder="铸件号" ></Input>
                        </td>
                        <td>图号：</td>
                        <td>
                            <Input v-model="queryForm.drawing" placeholder="图号" ></Input>
                        </td>
                        <td>
                            <Button @click="query"  type="primary" icon="ios-search">查询</Button>
                        </td>
                        <td>
                            <Button @click="reset" >重置</Button>
                        </td>
                    </tr>
                    <tr>
                        <td>合同编号：</td>
                        <td>
                            <Input v-model="queryForm.contractId" placeholder="合同编号" ></Input>
                        </td>
                        <td>客户名称：：</td>
                        <td>
                            <Input v-model="queryForm.customerName" placeholder="客户名称：" ></Input>
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
            <Button @click="close"  type="default" >取 消</Button>
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
              if(row.status == "1"){
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
            title: '合同编号',
            key: 'contractId',
            width: 120,
          },
          {
            title: '客户名称',
            key: 'customerName',
            align: 'left'
          },
          {
            title: '行号',
            key: 'count',
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
            title: '铸件号',
            key: 'casting',
            width: 80,
            align: 'center',
          },
          {
            title: '材质',
            key: 'texture',
            align: 'center',
            width:60,
          },
          {
            title: '交货日期',
            key: 'deliveryDate',
            align: 'center',
            width:60,
          },
        ],
        display:false,
        list: [],
        total:0,
        queryParam: {},
        queryForm: {
          casting: '',
          drawing: '',
          contractId: '',
          customerName: '',
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
        this.$http.post("/api/Certificate/list", this.queryParam).then((res) => {
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
          //item._checked = index == i;
            if(index == i){

                if(item._checked==true){
                    item._checked = false;
                }else{
                    item._checked=true;
                }
            }

        }
      },
      reset: function () {
        Object.assign(this.queryForm,{
            casting: '',
            drawing: '',
            contractId: '',
            customerName: '',
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
      close(){
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
        this.display = false;
        this.options.ok(select);

        // 以下代码需要遗弃
        this.selected = [];
        this.list.map((item)=>{
          if(item._checked){
              if(this.selected[0]){
                    if(this.selected[0].contractId==item.contractId){
                        this.selected.push(item);
                    }
              }else{
                  this.selected.push(item);
              }

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
    /*loadGroups: function () {
            this.loading = 1;
            //查询角色列表数据
            this.$http.post("/api/arg/list", {argGroup:'industry'}).then((res) => {
              this.loading = 0;
              if (res.data.code === 0) {
                this.industry = res.data.data;

              } else {
                this.$Message.error(res.data.message);
              }
            }).catch((error) => {
              this.loading = 0;
              this.$Message.error("请求失败，请重新发送")
            });
          },*/
</style>
