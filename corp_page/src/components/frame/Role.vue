<template>
  <ListPage ref="page" title="用户管理 - 角色" api="/api/role/list" 
  :model="this" :beforeLoad="beforeLoad">  
    <div class="page-title" slot="page-title">
      用户管理 - 角色
    </div> 
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td class="page-tools">
            <td>
              <Button  @click="addRole" icon="plus">添加</Button>
            </td>
            <td>
              <Button  @click="batch_del" icon="ios-trash-outline">删除</Button>
            </td>
          </td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <Input v-model="queryForm.roleName"  placeholder="角色名称"/>
          </td>
          <td>创建日期</td>
          <td><DatePicker type="daterange" v-model="queryForm.createTime"
           split-panels placeholder="创建日期"
           style="width: 180px" :clearable="false" ::transfer="true"></DatePicker></td>
          <td>
            <Button @click="query"  type="primary" icon="ios-search">查询</Button>
          </td>
          <td>
            <Button @click="reset"  type="default">重置</Button>
          </td>
        </tr>
      </table>
    </div>
    <RoleEdit ref="editRole" @on-save="query"></RoleEdit>
  </ListPage> 
</template>
<script>
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import Loading from '@/components/loading';
  import RoleEdit from '@/components/frame/RoleEdit';
  import ListPage from '@/components/page/ListPage';

  export default {
    components: {
      DataRowOperateBar,
      Loading,
      RoleEdit,
      ListPage
    },
    data() {
      let that = this;
      return {
        columns: [
          {
            type: 'selection',
            width: 50,
            align: 'center',
            render: function (h,params) {
              if(params.row.roleId === 1){
                params.row._disabled = true
              }
            }
          },
          {
            title: '角色名称',
            key: 'roleName'
          },
          {
            title: '备注',
            key: 'remark',
          },
          {
            title: '创建时间',
            key: 'createTime',
            align: 'center',
            width: 160,
          },
          {
            title: '操作',
            width: 100,
            align: 'center',
            render: function (h, params) {
              if(params.row.roleId === 1){
                return h('span',{},'/')
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
          roleName: '',
          page: '',
          pageSize: ''
        },
        selection: [],
        loading: 0
      }
    },
    mounted: function () {
      this.query();
    },
    computed: {},
    methods: {
      beforeLoad(){

      },
      query() { 
        this.$refs.page.query();
      },
      reset: function () {
        // 清空条件
        this.queryForm.roleName = '';
        this.query();
      },
      select: function (selection) {
        this.selection = selection;
      },
      batch_del: function () {
        var selection = this.selection;
        var roleIds = [];
        for (var i = 0; i < selection.length; i++) {
          roleIds.push(selection[i].roleId);
        }
        if (roleIds.length > 0) {
          this.$Modal.confirm({
            title: '删除确认',
            content: '<p>删除后不能恢复，确定删除已选择的记录吗？</p>',
            onOk: () => {
              this.$http.post('/api/role/delete?roleIds=' + roleIds, {}).then((res) => {
                if (res.data.code === 0) {
                  this.$Message.success("删除成功");
                  $.extend(this.queryForm, this.queryParam);
                  this.query();
                } else {
                  this.$Message.error(res.data.message)
                }
              }).catch((error) => {
                this.$Message.error(error.toString())
              });
            }
          });
        } else {
          this.$Message.error("请选中需删除的数据");
        }
      },
      rowCommand: function (name, params) {
        if (name === '编辑') {
          this.updateRole(params.row.roleId);
          return;
        }
        if (name === '删除') {
          this.$Modal.confirm({
            title: '删除确认',
            content: '<p>删除后不能恢复，确定删除已选择的记录吗？</p>',
            onOk: () => {
              let roleIds = [params.row.roleId];
              this.$http.post('/api/role/delete?roleIds=' + roleIds, {}).then((res) => {
                if (res.data.code === 0) {
                  this.$Message.success("删除成功");
                  $.extend(this.queryForm, this.queryParam);
                  this.query();
                } else {
                  this.$Message.error(res.data.message)
                }
              }).catch((error) => {
                this.$Message.error(error.toString())
              });
            }
          });
        }
      },
      goBack: function () {
        this.$router.go(-1);
      },
      addRole: function () {
        this.$refs.editRole.open(0);
      },
      updateRole: function (roleId) {
        this.$refs.editRole.open(roleId);
      },
    }
  }

</script>

<style type="text/css">

</style>
