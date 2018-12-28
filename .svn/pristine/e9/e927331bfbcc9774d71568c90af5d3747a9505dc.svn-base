<template>
  <div class="page customerlist">
    <div class="page-title">
      <a @click="goPage('/storage/baseset')">仓库基础设置</a> -&gt;库存操作类别
    </div>
    <div class="page-tools">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td><Button @click="add" v-power="'ckgl.kkczlb.add'" icon="plus">新建</Button></td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <Input v-model="queryForm.name" placeholder="库存操作名称"></Input>
          </td>
          <!--<td>-->
            <!--操作类型-->
          <!--</td>-->
          <td>
            <Select v-model="queryForm.type" style="width:150px" placeholder="请选择操作类型">
              <Option :value="''">请选择操作类型</Option>
              <Option :value="1">入</Option>
              <Option :value="2">出</Option>
              <Option :value="3">移</Option>
              <Option :value="4">调</Option>
            </Select>
          </td>
          <!--<td>-->
            <!--操作来源-->
          <!--</td>-->
          <td>
            <Select v-model="queryForm.source" style="width:150px" placeholder="请选择操作来源">
              <Option :value="''">请选择操作来源</Option>
              <Option :value="1">采购</Option>
              <Option :value="2">生产</Option>
              <Option :value="3">库存</Option>
              <Option :value="4">销售</Option>
            </Select>
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
      <i-table :columns="columns" :data="list"></i-table>
      <div style="height:10px;"></div>
    </div>
    </Loading>

    <StorageOperTypeEdit ref="edit" @on-save="query"></StorageOperTypeEdit>
    <StorageOperTypeView ref="view"></StorageOperTypeView>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import StorageOperTypeEdit from './StorageOperTypeEdit'; //导入当前目录下的编辑信息
  import StorageOperTypeView from './StorageOperTypeView';//导入当前目录下的查看信息

  export default {
    components: {
      DataRowOperateBar,
      Loading,
      StorageOperTypeEdit,
      StorageOperTypeView
    },
    data() {
      let that = this;
      return {
        columns: [
          {
            title: '库存操作代码',
            key: 'operId',
            width: 120,
            render:(h,params)=>{
              var row = params.row;
              return h('a',{
                props:{

                },
                on:{
                  click:()=>{
                    this.$refs.view.open(row);
                    this.$refs.view.$off('on-edit');
                    this.$refs.view.$once('on-edit',()=>{
                      this.update(row);
                    });
                  }
                }
              },row.operId);
            }
          },
          {
            title: '库存操作名称',
            key: 'name',
            width: 120,
            align: 'center'
          },
          {
            title: '操作类型',
            key: 'type',
            width: 100,
            align: 'center',
            render:(h,params)=>{
              var status = params.row.type;
              var setButton = "--";
              if (status == 1) {
                setButton = "入";
              }
              if (status == 2) {
                setButton = "出";
              }
              if (status == 3) {
                setButton = "移";
              }
              if (status == 4) {
                setButton = "调";
              }
              return h('span',{class:'type-'+status},setButton);
            }
          },
          {
            title: '操作来源',
            key: 'source',
            align: 'left',
            render:(h,params)=>{
              var status = params.row.source;
              var setButton = "--";
              if (status == 1) {
                setButton = "采购";
              }
              if (status == 2) {
                setButton = "生产";
              }
              if (status == 3) {
                setButton = "库存";
              }
              if (status == 4) {
                setButton = "销售";
              }
              return h('span',{class:'source-'+status},setButton);
            }

          },
          {
            title: '退货',
            key: 'reject',
            width: 120,
            align: 'center',
            render:(h, params)=>{
              if(params.row.reject == 1){//0不退货 1退货
                return h('Icon',{
                    props: {
                      type:'checkmark'
                    }
                });
              }
            }
          },
          {
            title: '返修',
            key: 'repair',
            width: 120,
            align: 'center',
            render:(h, params)=>{
              if(params.row.repair == 1){ //0不返修 1返修
                return h('Icon',{
                    props: {
                      type:'checkmark'
                    }
                });
              }
            }
          },
           {
            title: '操作',
            width: 80,
            align: 'center',
            render: function (h, params) {
              // let status = params.row.status;
              let setButton = "";
              // if (status === 2) {
              //   setButton = "启用";
              // }
              return h(DataRowOperateBar, {
                props: {
                  commands: setButton + '编辑,删除'
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
        list: [],
        total:0,
        queryParam: {},
        queryForm: {
          name: ''
        },
        // industry:[],
        loading: 0
      }
    },
    mounted: function () {//入口方法
      this.query()
    },
    computed: {},
    methods: {
      load() {
        this.loading = 1;
        this.$http.post("/api/storage/stockOpertype/list", this.queryParam).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.loading = 0;
            this.list = res.data.data;//res.data.data.rows;  非list集合数据
            //this.total = res.data.data.total;
            // this.selection = [];
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

      rowCommand: function (name, params) {
        if (name === '编辑') {
          if(!this.$user.hasPower('ckgl.kkczlb.edit')){
            this.$Message.error('暂无权限！');
            return;
          }
          this.update(params.row);
          return;
        }
        if (name === '删除') {
          if(!this.$user.hasPower('ckgl.kkczlb.del')){
            this.$Message.error('暂无权限！');
            return;
          }
           this.$Modal.confirm({
             title: '删除确认',
             content: '<p>删除后不能恢复，确定删除已选择的记录吗？</p>',
             onOk: () => {
               var opId = params.row.operId;
               this.loading = 1;
               this.$http.post('/api/storage/stockOpertype/delete?stockOpertypeId=' + opId, {}).then((res) => {
                 this.loading = 0;
                 if (res.data.code === 0) {
                   this.load();
                 } else {
                   this.$Message.error(res.data.message)
                 }
               }).catch((error) => {
                 this.loading = 0;
                 this.$Message.error(error.toString())
               });
             }
           });
        }
      },

      goBack: function () {
        this.$router.go(-1);
      },

      reset: function () {
        Object.assign(this.queryForm,{
          name: '',
          type:'',
          source:'',
        });
        this.query();
      },

      add: function () {
        this.$refs.edit.open({});
      },

      update: function (item) {
        this.$refs.edit.open(item);
      },
      goPage(url){
        this.$router.push(url);
      }
    }
  }

</script>

<style type="text/css">
  .customerlist .status-2{
    color:#ff6600;
  }
</style>
