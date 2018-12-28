<template>
  <div class="page customerlist">
    <div class="page-title">
      <a @click="goPage('/storage/baseset')">仓库基础设置</a> -&gt;仓库信息维护
    </div>
    <div class="page-tools">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td><Button @click="add" v-power="'ckgl.ckxx.add'" icon="plus">新建</Button></td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <Input v-model="queryForm.name" placeholder="仓库名称"></Input>
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
      <Page ref="pagebar" :total="total" size="small"
          @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top" :transfer="true"></Page>
    </div>
    </Loading>
    <StorageEdit ref="edit" @on-save="query"></StorageEdit>
    <StorageView ref="view"></StorageView>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import StorageEdit from './StorageEdit'; //导入当前目录下的编辑仓库信息
  import StorageView from './StorageView';//导入当前目录下的查看仓库信息

  export default {
    components: {
      DataRowOperateBar,//数据列表的最后一行的操作列：鼠标触发控件，控件上显示 编辑，删除等
      Loading,
      StorageEdit,
      StorageView
    },
    data() {
      let that = this;
      return {
        columns: [
          {
            title: '仓库代码',
            key: 'storageId',
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
              },row.storageId);
            }
          },
          {
            title: '仓库名称',
            key: 'name',
            width: 120,
            align: 'center'
          },
          {
            title: '类别',
            key: 'typeName',
            width: 100,
            align: 'center'
          },
          {
            title: '地址',
            key: 'address',
            align: 'left'
          },
          {
            title: '联系人',
            key: 'linkmanName',
            width: 120,
            align: 'center',
          },
          {
            title: '电话',
            key: 'telphone',
            width: 120,
            align: 'center',
          },
          {
            title: '部门',
            key: 'departmentName',
            width: 120,
            align: 'center',
          },
           {
            title: '盘点标志',
            key: 'checkStatus',
            width: 100,
            align: 'center',
            render:(h,params)=>{
              var status = params.row.checkStatus;
              var setButton = "未盘点";
              if (status == 1) {
                setButton = "盘点";
              }
              return h('span',{class:'checkStatus-'+status},setButton);
            }
          },
          {
            title: '初始化标志',
            key: 'initStatus',
            width: 100,
            align: 'center',
            render:(h,params)=>{
              var status = params.row.initStatus;
              var setButton = "已启用";
              if (status == 0) {
                setButton = "未启用";
              }
              return h('span',{class:'initStatus-'+status},setButton);
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
        industry:[],
        loading: 0
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
        this.$http.post("/api/storage/list", this.queryParam).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.loading = 0;
            this.list = res.data.data.rows;
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
      rowCommand: function (name, params) {
        if (name === '编辑') {
          if(!this.$user.hasPower('ckgl.ckxx.edit')){
            this.$Message.error('暂无权限！');
            return;
          }
          this.update(params.row);
          return;
        }
        if (name === '删除') {
          if(!this.$user.hasPower('ckgl.ckxx.del')){
            this.$Message.error('暂无权限！');
            return;
          }
          this.$Modal.confirm({
            title: '删除确认',
            content: '<p>删除后不能恢复，确定删除已选择的记录吗？</p>',
            onOk: () => {
              var aid = params.row.storageId;
              this.loading = 1;
              this.$http.post('/api/storage/delete?storageId=' + aid, {}).then((res) => {
                this.loading = 0;
                if (res.data.code === 0) {
                  this.$Message.success("删除成功");
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
          name: ''
        });
        this.query();
      },

      add: function () {
        this.$refs.edit.open({storageId:''});
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
