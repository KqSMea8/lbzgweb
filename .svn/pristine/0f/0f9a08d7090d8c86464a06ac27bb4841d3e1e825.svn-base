<template>
    <div class="page member">
      <div class="page-tools">
        <table cellpadding="0" cellspacing="0">
          <tr>
            <td><Button  @click="addEmp" v-power="'xtzs.zzjg.add'">添加员工</Button></td>
            <td><Button  @click="batch" v-power="'xtzs.zzjg.import'" icon="android-exit">批量导入/导出</Button></td>
            <td><Button  @click="sync" v-power="'xtzs.zzjg.sync'" icon="android-sync">同步成员</Button></td>
            <td><Button  @click="openSelectDept" v-power="'xtzs.zzjg.move'" icon="arrow-move">移动成员</Button></td>
          </tr>
        </table>
      </div>
      <div class="page-searchbox">
        <table cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td>
              <span class="member-count">全部成员<span style="color:#999;">（共{{totalCount}}人）</span></span>
            </td>
            <td width="1%">
              <Select v-model="queryForm.status" @on-change="query" >
                <Option v-for="item in status" :value="item.value" :key="item.index">{{ item.label }}</Option>
              </Select>
            </td>
            <td width="1%">
               <Input v-model="queryForm.keyword" placeholder="搜索姓名/手机号" style="width:150px" @on-enter="query" />
            </td>
            <td width="1%">
              <Button icon="ios-search" type="primary" @click="query">查询</Button>
            </td>
          </tr>
        </table>
      </div>
      <Loading :loading="loading">
        <div class="page-datatable">
          <Table @on-selection-change="select" :columns="columns" :data="list" ></Table>
          <div style="height:10px;"></div>
          <Page ref="pagebar" size="small" :total="totalCount" placement="top"
            @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer :transfer="true"></Page>
        </div>
      </Loading>
      <div>
          <EditEmp ref="editEmp" @on-load="query"></EditEmp>
          <SelectDept ref="selectDept" @on-check="moveEmp"></SelectDept>
      </div>
    </div>
</template>
<script>
import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
import Loading from '@/components/loading';
import EditEmp from '@/components/contacts/EditEmp';
import SelectDept from '@/components/commons/SelectDept';


export default {
  components: {
    DataRowOperateBar,
    Loading,
    EditEmp,
    SelectDept
  },
  props: {
  },
  data() {
    var that = this;
    return {
      loading:0,
      totalCount:0,
      timer:{},
      columns:[
        {
            type: 'selection',
            width: 40,
            align: 'center'
        },
        {
            title: '姓名',
            align: 'center',
            width:160,
            render: (h,params)=>{

              return h('Row',{
                      props:{type:'flex',justify:'center',align:'middle'},
                    },
                    [
                     h('Col',{props:{span:'3'}},[
                         h('img',{attrs:{src:that.follow(params.row.status),style:'width:12px;height:12px;margin-top:6px;'}})
                      ]),
                    h('Col',{props:{span:'6'}},[
                        h('Avatar',{
                         props:{src:params.row.header,shape:'circle',icon:'person'}
                        })
                      ]
                    ),
                    h('Col',{props:{span:'1'}},[
                    ]),
                    h('Col',{props:{span:'9','class-name':'truename-col-span'}},[
                      h('span',params.row.trueName)
                    ])
                  ]
              );
            }
        },
        {
            title: '性别',
            key: 'sexName',
            align: 'center',
            width:60
        },
        {
           title: '手机号',
            key: 'mobile',
            align: 'center',
            width:120
        },
        {
            title: '部门',
            key: 'deptName',
            align: 'left'
        },
        {
          title: '职位',
          key: 'duty',
          align: 'left',
          width:140
        },
        {
          title: '员工生日',
          key: 'birthSolar',
          align: 'left',
          width:140
        },
        {
          title: '操作',
          align:'left',
          width:100,
          render: (h, params) => {
            if(params.row.dimission === 1) {
              return h(DataRowOperateBar,{
                        props:{
                          commands:'删除'
                        },
                        on:{
                          'on-command': (name)=>{
                            that.rowCommand(name,params)
                          }
                        }
                });
            }
            return  h(DataRowOperateBar,{
                        props:{
                          commands:'编辑,删除,离职'
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
      list:[],
      status: [
          { value: -1, label: '全部成员'},
          { value: 1, label: '已关注'},
          { value: 0, label: '未关注'}
        ],
      queryParam:{

      },
      queryForm:{
        //1=已激活，2=已禁用，0=未激活
        status:-1,
        keyword:'',
        //离职状态(0在职,1离职)
        dimission: 0,
        //部门ID
        deptId: 1
      },
      selection:[]
    }
  },
  mounted:function(){
    this.query();
  },
  computed:{
  },
  methods:{
    follow: function(status){
        if(status ===1 ) {
          return require('../../assets/img/icon-follow.png');
        }
        return require('../../assets/img/icon-unfollow.png');
    },
    loadEmp: function() {

      var pagebar = this.$refs.pagebar;
      this.loading = 1;
      this.queryParam.page = pagebar.currentPage;
      this.queryParam.pageSize = pagebar.currentPageSize;

      this.$http.post('/api/contacts/emp/list', this.queryParam).then((res) => {
          if (res.data.code === 0 && res.data.data!=null) {
            this.loading = 0;
            this.list = res.data.data.rows;
            this.totalCount = res.data.data.total;
            this.selection = [];
            // 同步条件信息到表单
            $.extend(this.queryForm,this.queryParam);
          } else {
            this.loading = 0;
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error(error.message)
      });
    },
    refresh:function(){
        this.query();
        var pagebar = this.$refs.pagebar;
        pagebar.current = 1;
        pagebar.currentPage = 1;
        pagebar.currentPageSize = 10;

    },
    query:function(){
        $.extend(this.queryParam,this.queryForm);
        this.$refs.pagebar.currentPage = 1;
        this.loadEmp();
    },
    reset:function(){
        this.queryForm = {
            status:-1,
            keyword:'',
            dimission: 0,
            deptId: 1
        }

        var pagebar = this.$refs.pagebar;
        pagebar.current = 1;
        pagebar.currentPage = 1;
        pagebar.currentPageSize = 10;

        this.query();
    },
    pageChange:function(page){
      this.loadEmp();
    },
    pageSizeChange:function(pageSize){
      var pagebar = this.$refs.pagebar;
      if(pagebar.currentPage === 1){
        this.loadEmp();
      }
    },
    select:function(selection){
      this.selection=selection;
    },
    rowCommand:function(name,params){
      if(name == '删除'){
        if(!this.$user.hasPower('xtzs.zzjg.add')){
          this.$Message.error('暂无权限！');
          return;
        }
        this.deleteEmp(params.row.empId);
        return;
      }
      if(name == '离职'){
        this.dimissed(params.row.empId);
        return;
      }
      if(name == '编辑'){
        this.updateEmp(params.row.empId);
        return;
      }
    },
    deleteEmp:function(empIds){
      var that = this;
      this.$Modal.confirm({
          title: '删除确认',
          content: '<p>删除后不能恢复，确定删除该条记录吗？</p>',
          onOk: () => {
              that.loading = 1;
              this.$http.post('/api/contacts/emp/delete?empIds=' + empIds,{}).then((res) => {
                if (res.data.code === 0) {
                  that.loading = 0;
                  this.$Message.success("删除成功");
                  that.query();
                } else {
                  this.loading = 0;
                  this.$Message.error(res.data.message)
                }
              }).catch((error) => {
                this.loading = 0;
                this.$Message.error(error.message)
              });
            }
          });
    },
    dimissed:function(empIds){
       var that = this;
       this.$Modal.confirm({
          title: '设置离职',
          content: '<p>设置离职后，该成员不能访问应用，但历史数据仍保留，</p><p>确定进行离职操作吗？</p>',
          onOk: () => {
              that.loading = 1;
              this.$http.post('/api/contacts/emp/dimissed?empIds=' + empIds + "&dimissionStatus=1",
                             {}).then((res) => {
                if (res.data.code === 0) {
                  that.loading = 0;
                  this.$Message.success("设置成功");
                  that.query();
                } else {
                  this.loading = 0;
                  this.$Message.error(res.data.message)
                }
              }).catch((error) => {
                this.loading = 0;
                this.$Message.error(error.message)
              });
            }
          });
    },
    addEmp:function(){
      this.$refs.editEmp.open('');
    },
    updateEmp:function(empId){
        this.$refs.editEmp.open(empId);
    },
    openSelectDept:function(){
      if(this.selection.length === 0){
        this.$Message.error("请选择成员");
        return;
      }
      this.$refs.selectDept.open();
      this.$refs.selectDept.setCheck([]);
    },
    sync:function(){
      this.$router.push({name:'contacts.sync'});
    },
    batch:function(){
      if(!this.$user.hasPower('txl.pl')){
          this.$Message.error('暂无权限！');
          return;
        }
       this.$router.push({name:'contacts.batch'});
    },
    moveEmp:function(depts){
      let deptIds = depts.map((item) => {return parseInt(item.deptId)});
      let empIds = this.selection.map((item) => { return item.empId });
      let that = this;
      that.loading = 1;
      this.$http.post('/api/contacts/emp/setDept?empIds=' + empIds + "&deptIds=" + deptIds,
                               {}).then((res) => {
          if (res.data.code === 0) {
            that.loading = 0;
            that.$Message.success("设置成功");
            that.$refs.selectDept.close();
            that.query();
          } else {
            that.loading = 0;
            that.$Message.error(res.data.message)
          }
        }).catch((error) => {
          that.loading = 0;
          that.$Message.error(error.message)
      });
    },
    undistributed:function(){
        this.loading = 1;
        let pagebar = this.$refs.pagebar;
        pagebar.currentPage = 1;
        pagebar.currentPageSize = 10;
        this.$http.post('/api/contacts/emp/undistributed',{
         dimission:0,
         status:-1,
         likeContent:this.queryForm.keyword,
         page:pagebar.currentPage,
         pageSize:pagebar.currentPageSize
       }).then((res) => {
          if (res.data.code === 0) {
             this.loading = 0;
             this.list = res.data.data.rows;
             this.totalCount = res.data.data.total;

             this.selection = [];
              // 同步条件信息到表单
              $.extend(this.queryForm,this.queryParam);
          } else {
            this.loading = 0;
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error(error.message);
        });
    }
  }
}

</script>

<style type="text/css">
  /*通用样式*/
  .page.member{
    padding-top: 0px;
  }
  .member-title{
    font-size: 14px;
  }
  .member .member-count{
    color: #666;
    font-size: 14px;
  }
  .member .page-datatable{
    margin-top: 10px;
  }

  .member .page-datatable .ivu-table-cell{
     padding: 0 10px;
  }

  .member .page-datatable .ivu-table td,
  .member .page-datatable .ivu-table th {
    height: 39px;
  }

  .member .ivu-avatar {
    margin-top: 5px;
    width: 28px;
    height: 28px;
    line-height: 28px;
  }

  .member .ivu-avatar>i {
    line-height: 28px;
  }
  .member .truename-col-span {
    text-align: left;
  }

</style>

