<template>
<Modal v-model="display" title="选择物料" :closable="false" :mask-closable="false" width="800" class="selprovidermater" :transfer="transfer">
  <div class="page"> 
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>  
          <td>
             <Input type="text" v-model="queryForm.materName" placeholder="物料"></Input>
          </td>
          <td>&nbsp;&nbsp;</td>
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
      <i-table :columns="columns" :data="list" @on-row-click="innerCheckRow(arguments[1])"></i-table>
      <div style="height:10px;"></div>
      <Page ref="pagebar" :total="total" size="small"
          @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top" :transfer="true"></Page>
    </div>
    </Loading>
    <ProviderEdit ref="edit" @on-save="query"></ProviderEdit> 
  </div>
  <div slot="footer" class="footer">
    <Button @click="onOK"  type="primary" icon="checkmark">确 定</Button>
    <Button @click="onCancel"  type="default" >取 消</Button>
  </div>
</Modal>
</template>

<script>
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import Loading from '@/components/loading';
  import ProviderEdit from './ProviderEdit';//编辑 

  export default {
    components: {
      DataRowOperateBar,
      Loading,
      ProviderEdit, 
    },
    props:{
      transfer:{
        type:Boolean,
        default:true
      } 
    },
    data() {
      let that = this;
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
            title: '物料代码',
            key: 'materId',
            align: 'center',
            width: 120,
          },
          {
            title: '物料名称',
            key: 'materName',
            width: 120,
            align: 'left'
          },
          {
            title: '采购类别',
            key: 'subType',
            width: 100,
            align: 'center',
            render:(h,params)=>{
              var row = params.row;
              return h('span', this.$args.getArgText('material_subtype',row.subType));
            },
          },
          {
            title: '物料类别',
            key: 'materType',
            width: 100,
            render:(h,params)=>{
              var row = params.row;
              return h('span', this.$args.getArgText('material_class',row.materType));
            },
            align: 'center'
          },
          {
            title: '规格',
            key: 'spec', 
            align: 'center',
            width:80
          },
          {
            title: '型号',
            key: 'model', 
            align: 'center',
            width:80
          },
          {
            title: '供应商',
            key: 'providerName', 
            width:120
          },
          {
            title: '合格',  //状态（1.备用 2.合格）
            width: 80,
            align: 'status',
            render: function (h, params) {
              var status = params.row.status;
              let setButton = "备选";
              if (status == 2) {
                setButton = "合格";
              }
              return h('span',setButton);
            }
          }, 
        ],
        display:false,
        list: [],
        total:0,
        queryParam: {},
        queryForm: {
          status:null,
          providerId:'',
          providerName:'',
          materId:'',
          materName:''
        },
        provider:[],//供应商下拉选择
        material:[],//物料下拉选择
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
        this.queryParam.containOtherMater = 1;
        this.$http.post("/api/providerMaterial/list", this.queryParam).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.loading = 0;
            var list = res.data.data.rows;
            list.map((item)=>{item._checked=false;});
            this.list = list;
            this.total = res.data.data.total;
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
        // var pagebar = this.$refs.pagebar;
        // pagebar.current = 1;
        // pagebar.currentPage = 1;
        // pagebar.currentPageSize = 10;
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

      switStorage(){
        this.reset();
      }, 
       
      goBack: function () {
        this.$router.go(-1);
      }, 
      innerCheckRow(index){
        for(var i = 0;i<this.list.length;i++){
          var item = this.list[i];
          item._checked = index == i;
        }
      },
      reset: function () {
        Object.assign(this.queryForm,{  
          providerId: '',
          providerName: '',
          materId: '',
          materName:'',
          materType: ''
        });
        this.resetPage();
        this.query();
      },

      resetPage: function(){
        var pagebar = this.$refs.pagebar;
        pagebar.current = 1;
        pagebar.currentPage = 1;
        pagebar.currentPageSize = 10;
      }, 

      update: function (item) {
        this.$refs.edit.open(item);
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
        var select = null;
        this.list.map((item)=>{
          if(item._checked){
            select = item;
          }
        });
        if(select == null){
          this.$Message.error('请选择物料');
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
  .selprovidermater .page{
    padding: 0px;
  }
  .selprovidermater .page-searchbox{
    margin-top: 0px;
  }
  .selprovidermater .footer{
    text-align: center;
  }
</style>
