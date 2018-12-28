<template>
  <ListPage ref="page" title="用户管理" api="/api/admin/list" 
  :model="this" :beforeLoad="beforeLoad">  
    <div class="page-title" slot="page-title">
      用户管理
    </div> 
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
 
        <tr>
          <tr> 
          <td class="page-tools">
            <td><Button @click="add" v-power="'xtsz.yhgl.add'" icon="plus">新建</Button></td>
            <td><Button @click="batch_delAdmin" v-power="'xtsz.yhgl.del'" icon="ios-trash-outline">删除</Button></td>
            <td>
              <div class="split"></div>
            </td>
            <td><Button  @click="go_set('frm.role')" type="warning" v-power="'xtsz.yhgl.role'" icon="settings">角色设置</Button></td>
          </td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <Input v-model="queryForm.keyword" class="input-title"   placeholder="子账户名称" @on-enter="query"></Input>
          </td>
          <td>
            <Button @click="query"  type="primary" icon="ios-search">查询</Button>
          </td>
          <td>
            <Button @click="reset"  type="default">重置</Button>
          </td>
        </tr>
      </table>
    </div>
      <AdminEdit ref="adminEdit" @on-submit="requireQuery"></AdminEdit>
      <ModifyPassword ref="modifyPassword" @on-submit-modifyPassword="modifyPassword"></ModifyPassword>
      <AdminAdd ref="addAdmin" @on-submit="requireQuery"></AdminAdd>
  </ListPage>
</template>
<script>
import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
import Loading from '@/components/loading';
import AdminEdit from '@/components/frame/AdminEdit';
import ModifyPassword from'@/components/frame/ModifyPassword';
import AdminAdd from'@/components/frame/AdminAdd';
import ListPage from '@/components/page/ListPage';

export default {
  components: {
    DataRowOperateBar,
    Loading,
    AdminEdit,
    ModifyPassword,
    AdminAdd,
    ListPage
  },
  data() {
    var that = this;
    return {
      columns:[
        {
          type: 'selection',
          width: 50,
          align: 'center',
        },
        {
            title: '登录账号',
            key: 'loginName',
            width:150,
            align:'left',
        },
        {
          title:'名称',
          width:120,
          key:"nickName",
          align:'left'
        },
        {
            title: '角色',
            key: 'roleNames',
        },
        {
            title: '绑定员工',
            key: 'empName',
            width:120,
            align:'center',
        },
        {
          title: '创建时间',
          key: 'createTime',
          align:'center',
          width:160,
        },
        {
          title: '操作',
          width:100,
          align:'center',
          render:function(h, params) {
            let status = params.row.status;
              let setButton = "禁用";
              if (status === 2) {
                setButton = "启用";
              }
            return h(DataRowOperateBar,{
              props:{
                commands: setButton + ',修改密码,编辑,删除'
              },
              on:{
                'on-command': (name)=>{
                  that.rowCommand(name,params)
                }
              }
            });
          }
        }
      ],
      list:[ ],
      total:0,
      queryParam:{

      },
      queryForm:{
        keyword:''
      },
      selection:[],
      loading:0
    }
  },
  mounted:function(){
    this.query();
  },
  methods:{
    beforeLoad(){

    },
    requireQuery:function(){
      this.query();
    },
    modifyPassword:function(){
      this.query();
    },
    add:function(){
      this.$refs.addAdmin.open();
    },
    query:function(){
      this.$refs.page.query();
    },
    reset:function(){
      this.queryForm.keyword='';
      this.query();
    },
    select:function(selection){
      this.selection=selection;
    },
    batch_delAdmin:function(){
      var selection = this.selection;
      var ids=[];
      for(var i=0;i<selection.length;i++){
        ids.push(selection[i].id);
      }
      if(ids.length > 0){
        this.$Modal.confirm({
          title: '删除确认',
          content: '<p>删除后不能恢复,确定要删除子账号？</p>',
          onOk: () => {
            this.$http.post('/api/admin/deleteList?ids='+ids, {}).then((res) => {
              if (res.data.code === 0) {
                this.$Message.success("删除成功");
                $.extend(this.queryForm,this.queryParam);
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
    rowCommand:function(name,params){
      if(name === '启用' || name === '禁用'){
        this.rowParams = params;
        var id = this.rowParams.row.userId;
        var status = this.rowParams.row.status;
        this.$http.post('/api/admin/updateStatus', {
          id:id,
          status:status
          }).then((res) => {
            if (res.data.code === 0) {
              this.query();
            } else {
              this.$Message.error(res.data.message)
            }
          }).catch((error) => {
              this.$Message.error(error.toString())
          });
      }
      if(name == '修改密码'){
        this.rowParams = params;
        var id = this.rowParams.row.userId;
        this.$refs.modifyPassword.open(id);
        return;
      }
      if(name == '编辑'){
        this.rowParams = params;
        var id = this.rowParams.row.userId;
        this.$refs.adminEdit.open(id);
        return;
      }
      if(name == '删除'){
        var id = params.row.userId;
        this.$Modal.confirm({
        title: '删除确认',
        content: '<p>删除后不能恢复,确定要删除该子账号？</p>',
        onOk: () => {
          this.$http.post('/api/admin/delete?id=' + id, {}).then((res) => {
            if (res.data.code === 0) {
              this.$Message.success("删除成功");
              this.query();
            } else {
              this.$Message.error(res.data.message)
            }
          }).catch((error) => {
              this.$Message.error(error.toString())
          });
       }
      });
        return ;
      }
    },
    go_set:function (view) {
        this.$router.push({name:view});
    }
  }
}

</script>

<style type="text/css">
  .admin-status-2{
    color:#ff6600;
  }
</style>
