<template>
  <div class="page">
    <div class="page-title">
      数据字典
    </div> 
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <Select v-model="queryForm.argGroup" style="width:150px" @on-change="query">
                <Option v-for="item in groups" :value="item.argCode" :key="item.argCode">{{ item.argText }}</Option>
            </Select>
          </td>
          <td>
            <Button @click="query"  type="primary" icon="ios-search">查询</Button>
          </td>
          <td>
            <Button  @click="addArg" icon="plus">添加</Button>
          </td>
        </tr>
      </table>
    </div>
    <Loading :loading="loading">
    <div class="page-datatable">
      <i-table :columns="columns" :data="list"></i-table> 
    </div>
    </Loading> 
    <ArgEdit ref="editArg" @on-save="query"></ArgEdit>
  </div>
</template>
<script>
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import Loading from '@/components/loading';
  import ArgEdit from './ArgEdit';

  export default {
    components: {
      DataRowOperateBar,
      Loading,
      ArgEdit
    },
    data() {
      let that = this;
      return {
        columns: [ 
          {
            title: '编码',
            key: 'argCode',
            width: 120,
          },
          {
            title: '标题',
            key: 'argText',
            width: 200,
          },
          {
            title: '备注',
            key: 'argRemark',
          },
          {
            title: '排序',
            key: 'seq',
            width: 100,
            align: 'center',
          },
          {
            title: '操作',
            width: 100,
            align: 'center',
            render: function (h, params) {
              if(params.row.isInit === 1){
                return h('span',{title:'系统初始化数据'},'--')
              }
              return h(DataRowOperateBar, {
                props: {
                  commands: '编辑,删除'
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
          argGroup: ''
        },
        groups:[],
        loading: 0
      }
    },
    mounted: function () {
      this.loadGroups(this.query);
    },
    computed: {},
    methods: {
      loadGroups: function (callback) {  
        this.loading = 1; 
        //查询角色列表数据
        this.$http.post("/api/arg/list", {argGroup:'group'}).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) { 
            this.groups = res.data.data;
            if(this.groups.length>0){
              this.queryForm.argGroup = this.groups[0].argCode;
            }
            return callback && callback();
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新发送")
        });
      }, 
      load: function () {  
        this.loading = 1; 
        //查询角色列表数据
        this.$http.post("/api/arg/list", this.queryParam).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.loading = 0;
            this.list = res.data.data;
            // 同步条件信息到表单
            $.extend(this.queryForm, this.queryParam);
          } else {
            this.list = null;
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新发送")
        });
      }, 
      query: function () {  
        $.extend(this.queryParam, this.queryForm); 
        this.load();
      }, 
      pageChange: function (page) {
        this.load();
      }, 
      rowCommand: function (name, params) {
        if (name === '编辑') {
          this.updateArg(params.row);
          return;
        }
        if (name === '删除') {
          this.$Modal.confirm({
            title: '删除确认',
            content: '<p>删除后不能恢复，确定删除已选择的记录吗？</p>',
            onOk: () => {
              var aid = params.row.id;
              this.loading = 1;
              this.$http.post('/api/arg/delete?id=' + aid, {}).then((res) => {
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
      addArg: function () {
        var lastSeq = 0;
        if(this.list.length>0){
          lastSeq = this.list[this.list.length-1].seq;
        }
        this.$refs.editArg.open({id:0,
          argGroup:this.queryForm.argGroup,
          seq:lastSeq+1
        });
      },
      updateArg: function (arg) {
        this.$refs.editArg.open(arg);
      },
    }
  }

</script>

<style type="text/css">
  
</style>
