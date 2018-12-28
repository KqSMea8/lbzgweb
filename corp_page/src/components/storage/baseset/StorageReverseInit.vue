<template>
  <div class="page customerlist">
    <div class="page-title">
      <a @click="goPage('/storage/baseset')">仓库基础设置</a> -&gt;仓库反初始化
    </div>
    <Loading :loading="loading">
    <div class="page-datatable">
      <i-table :columns="columns" :data="list"></i-table>
      <div style="height:10px;"></div>
      <Page ref="pagebar" :total="total" size="small"
          @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top" :transfer="true"></Page>
    </div>
    </Loading>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';

  export default {
    components: {
      DataRowOperateBar,//数据列表的最后一行的操作列：鼠标触发控件，控件上显示 编辑，删除等
      Loading,
    },
    data() {
      let that = this;
      return {
        columns: [
          {
            title: '仓库代码',
            key: 'storageId',
            width: 120,
          },
          {
            title: '仓库名称',
            key: 'name',
            width: 120,
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
            title: '初始化标志',
            key: 'initStatus',
            width: 100,
            align: 'center',
            render:(h,params)=>{
              var status = params.row.initStatus;
              var setButton = "已完成";
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
              let setButton = "";
              return h(DataRowOperateBar, {
                props: {
                  commands: setButton + '反初始化'//'反初始化,编辑,删除'
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
        if (name === '反初始化') {
          if(!this.$user.hasPower('ckgl.ckfcsh.reinit')){
            this.$Message.error('暂无权限！');
            return;
          }
         this.$Modal.confirm({
          title: '仓库反初始化操作',
          content: '<p>请确认是否对此仓库进行反初始化操作？</p>',
          onOk: () => {
            this.loading = 1;
            var url = '/api/storage/reverseInit?storageId='+params.row.storageId;//更新对应的id的状态
            this.$http.get(url, {}).then((res) => {
              this.loading = 0;
              if (res.data.code === 0) {
                this.$Message.success('操作成功');
                this.load();//操作完成，重新刷新页面并加载。
              } else {
                this.$Message.error(res.data.message)
              }
            }).catch((error) => {
              this.loading = 0;
              this.$Message.error(error.message)
            });
          }
         });
        }
      },

      goBack: function () {
        this.$router.go(-1);
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
