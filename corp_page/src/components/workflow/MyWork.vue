<template>
  <div class="page mywork-page">
    <Tabs :animated="false" v-model="name" @on-click="load">
      <TabPane label="待处理" name="handling" >
        <div class="mywork-handle">
            <div class="page-searchbox">
              <table cellpadding="0" cellspacing="0">
                <tr>
                  <td>
                       <Input v-model="keyword" placeholder="请输入流程名称" style="width:150px" @keyup.enter.native="load('handling')"></Input>
                       <Button type="primary" @click="load('handling')">查询</Button>
                  </td>
                </tr>
              </table>
            </div>
             <div class="page-datatable">
                <i-table  :columns="columns1" :data="list" ></i-table>
                <div style="height:10px;"></div>
                <Page ref="pagebar1" :total="total" size="small" class-name="page"
                      @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top" :transfer="true"></Page>
              </div>
        </div>
      </TabPane>
      <TabPane label="已处理" name="handled" >
        <div class="mywork-handle">
            <div class="page-searchbox">
              <table cellpadding="0" cellspacing="0">
                <tr>
                  <td>
                      <Input v-model="keywordHandled" placeholder="请输入流程名称" style="width:150px" @keyup.enter.native="load('handled')"></Input>
                      <Button type="primary" @click="load('handled')">查询</Button>
                  </td>
                  <td>
                    <!--<Button  >撤回</Button>-->
                  </td>
                  <!--<td>-->
                     <!--<Button >删除</Button>-->
                  <!--</td>-->
                </tr>
              </table>
            </div>
             <div class="page-datatable">
                <i-table  :columns="columns2" :data="list" ></i-table>
                <div style="height:10px;"></div>
                <Page ref="pagebar2" :total="total" size="small" class-name="page"
                      @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top" :transfer="true"></Page>
              </div>
        </div>
      </TabPane>
      <TabPane label="待发送" name="sending" >
        <div class="mywork-start">
          <div class="page-searchbox">
            <table cellpadding="0" cellspacing="0">
              <tr>
                <td>
                  <Input v-model="keywordSending" placeholder="请输入流程名称" style="width:150px" @keyup.enter.native="load('sending')"></Input>
                  <Button type="primary"  @click="load('sending')">查询</Button>
                </td>
                <!--<td>-->
                  <!--<Button >删除</Button>-->
                <!--</td>-->
              </tr>
            </table>
          </div>
           <div class="page-datatable">
              <i-table :columns="columns3" :data="list" ></i-table>
              <div style="height:10px;"></div>
               <Page ref="pagebar3" :total="total" size="small" class-name="page"
                      @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top" :transfer="true"></Page>
            </div>
        </div>
      </TabPane>
      <TabPane label="已发送" name="sended" >
          <div class="mywork-start">
              <div class="page-searchbox">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td>
                      <Input v-model="keywordSended" placeholder="请输入流程名称" style="width:150px" @keyup.enter.native="load('sended')"></Input>
                      <Button type="primary"  @click="load('sended')">查询</Button></td>
                    <td>
                    <td>
                      <!--<Button  >撤销</Button>-->
                    </td>
                    <!--<td>-->
                       <!--<Button >删除</Button>-->
                    <!--</td>-->
                  </tr>
                </table>
              </div>
               <div class="page-datatable">
                  <i-table :columns="columns4" :data="list" ></i-table>
                  <div style="height:10px;"></div>
                  <Page ref="pagebar4" :total="total" size="small" class-name="page"
                      @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top" :transfer="true"></Page>
                </div>
          </div>
      </TabPane>
      <Button type="ghost" @click="startPro" size="small" slot="extra" class="mywork-start-extra">发起流程</Button>
    </Tabs>
    <div>
        <ProcessLog ref="log"></ProcessLog>
        <!--<Loading v-if="loading"></Loading>-->
      <Loading :loading="loading"></Loading>
    </div>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import ProcessLog from '@/components/workflow/ProcessLog';
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';

  export default {
    components: {
      DataRowOperateBar,
      Loading,
      ProcessLog
    },
    data() {
      let that = this;
      return {
        loading:0,
        status:0,
        name:'handling',
        list:[],
        columns1:[
          {
            title: '流程名称',
            key: 'processName',
            render: (h, params) => {
              var row = params.row;
              var text = row.processName;
              return h('a',{
                props:{

                },
                style: {
                    marginRight: '5px'
                  },
                on:{
                  click:()=>{
                    this.handle(params.row.instanceId,params.row.taskId);
                  }
                }
              },text);
            }
          },
          {
            title: '流程来源',
            key: 'source',
            width: 100
          },
           {
            title: '发起时间',
            key: 'processStartTime',
            align: 'center',
            width: 140
          },
          {
            title: '发起人',
            key: 'starter',
            width: 100,
            align: 'center'
          },
          {
            title: '接收时间',
            key: 'receiveTime',
            align: 'center',
            width: 140
          },
          {
            title: '操作',
            width:100,
            align:'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    size: 'small'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.logs(params.row.instanceId);
                    }
                  }
                }, '日志')
              ]);
            }
          }
        ],
        columns2:[ 
          {
            title: '流程名称',
            key: 'processName'
          },
          {
            title: '流程来源',
            key: 'source',
            width: 100
          },
           {
            title: '发起时间',
            key: 'processStartTime',
            align: 'center',
            width: 140
          },
          {
            title: '发起人',
            key: 'starter',
            width: 100,
            align: 'center',
          },
          {
            title: '处理时间',
            key: 'dealTime',
            align: 'center',
            width: 140
          },
          {
            title: '当前处理人',
            key: 'dealName',
            align: 'center',
            width: 120
          },
          {
            title: '流程状态',
            key: 'status',
            align: 'center',
            width: 120
          },
          {
            title: '操作',
            width:100,
            align:'center',
            render: (h, params) => {
              let status = params.row.status;
              if("已结束" == status){
                return h('div', [
                  h('Button', {
                    props: {
                      size: 'small'
                    },
                    style: {
                      marginRight: '5px'
                    },
                    on: {
                      click: () => {
                        this.logs(params.row.instanceId);
                      }
                    }
                  }, '日志')
                ]);
              }else{
                return h(DataRowOperateBar, {
                  props: {
                    commands: '日志,撤回'
                  },
                  on: {
                    'on-command': (name) => {
                      that.rowCommand(name, params)
                    }
                  }
                });
              }
            }
          }
        ],
        columns3:[ 
          {
            title: '流程名称',
            key: 'processName',
            render: (h, params) => {
              return h('div', [
                h('a', { 
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.editDraft(params.row.draftId);
                    }
                  }
                }, params.row.processName)
              ]);
            }
          },
          {
            title: '流程来源',
            key: 'source',
            width: 100,
            align: 'center'
          },
           {
            title: '创建时间',
            key: 'processCreateTime',
            align: 'center',
            width: 140
          }
        ],
        columns4:[ 
          {
            title: '流程名称',
            key: 'processName'
          },
          {
            title: '流程来源',
            key: 'source',
            width: 100
          },
           {
            title: '发起时间',
            key: 'processStartTime',
            align: 'center',
            width: 140
          },
          {
            title: '当前处理人',
            key: 'dealName',
            align: 'center',
            width: 120
          },
          {
            title: '流程状态',
            key: 'status',
            align: 'center',
            width: 100
          },
          {
            title: '操作',
            width:100,
            align:'center',
            render: (h, params) => {
              let status = params.row.status;
              if("已结束" == status){
                return h('div', [
                  h('Button', {
                    props: {
                      size: 'small'
                    },
                    style: {
                      marginRight: '5px'
                    },
                    on: {
                      click: () => {
                        this.logs(params.row.instanceId);
                      }
                    }
                  }, '日志')
                ]);
              }else{
                return h(DataRowOperateBar, {
                  props: {
                    commands: '日志,撤销'
                  },
                  on: {
                    'on-command': (name) => {
                      that.rowCommand(name, params)
                    }
                  }
                });
              }
            }
          }
        ],
        keyword:'',
        keywordHandled:'',
        keywordSending:'',
        keywordSended:'',
        total:0,
        queryParam:{

        },
        queryForm:{
          workflowName:'',
          page:'',
          pageSize:'',
          status:3
        },
        curPagebar:null
      }
    },
    mounted () {
      this.load(this.name);
    },
    methods: {
      load(name){
         let type = 0;
          switch(name){
            case 'handling': type = 3;this.curPagebar = this.$refs.pagebar1;this.queryForm.workflowName = this.keyword;break;
            case 'handled': type = 4;this.curPagebar = this.$refs.pagebar2;this.queryForm.workflowName = this.keywordHandled;break;
            case 'sending': type = 1;this.curPagebar = this.$refs.pagebar3;this.queryForm.workflowName = this.keywordSending;break;
            case 'sended': type = 2;this.curPagebar = this.$refs.pagebar4;this.queryForm.workflowName = this.keywordSended;break;
          }
          this.status = type;
          this.myProcess(this.status);
       },
       myProcess(status){
            this.loading = 1;
            var pagebar = this.curPagebar;
            this.queryForm.page = pagebar.currentPage;
            this.queryForm.pageSize = pagebar.currentPageSize;
            this.queryForm.status = status;
//            this.queryForm.workFlowName = this.keyword;
            this.list = [];
            this.$http.post('/api/wf/process/myProcess',this.queryForm).then((res) => {
              if (res.data.code === 0) {
                  this.loading = 0;
                  this.list = res.data.data.rows;
                  this.total = res.data.data.total;
                  // 同步条件信息到表单
                  $.extend(this.queryForm,this.queryParam);
              } else {
                this.loading = 0;
                this.list = [];
                this.$Message.error(res.data.message);
              }
            }).catch((error) => {
              this.loading = 0;
              this.list = [];
              this.$Message.error(error.toString())
            })
       },
      startPro(){
          let routeData = this.$router.resolve({ name: 'workflow.initproc' });
          window.open(routeData.href, '_blank');
       },
       handle(instId,taskId){
          let routeData = this.$router.resolve({ path:'/workflow/process?inst=' + instId + '&task=' + taskId});
          window.open(routeData.href, '_blank');
       },
       editDraft(draftId){
          let routeData = this.$router.resolve({ path:'/workflow/initproc?draft=' + draftId});
          window.open(routeData.href, '_blank');
       },
       logs(instanceId){
          this.$refs.log.open(instanceId);
          return;
       },
       reload(){
           console.log(this.name)
         this.load(this.name);
       },
      pageChange(page){
        var that = this;
        //将参数对象数据赋值个表单进行查询
        $.extend(that.queryForm,that.queryParam);
        this.load(this.name);
      },
      pageSizeChange(pageSize){
        var pagebar = this.curPagebar;
        if(pagebar.currentPage == 1){
          this.load(this.name);
        }
      },
      rowCommand: function (name, params) {
        if (name === '撤回') {
          let instanceId = params.row.instanceId
          let taskId = params.row.taskId
          this.$http.post('/api/wf/process/recall', {
            instanceId: instanceId,
            taskId: taskId
          }).then((res) => {
            if (res.data.code === 0) {
              this.$Message.success(res.data.message);
              this.load(this.name);
            } else {
              this.$Message.error(res.data.message)
            }
          }).catch((error) => {
            this.$Message.error(error.toString())
          });
        }
        if (name === '撤销') {
          let instanceId = params.row.instanceId
          let taskId = params.row.taskId
          this.$http.post('/api/wf/process/revoke', {
            instanceId: instanceId,
            taskId: taskId
          }).then((res) => {
            if (res.data.code === 0) {
              this.$Message.success(res.data.message);
              this.load(this.name);
            } else {
              this.$Message.error(res.data.message)
            }
          }).catch((error) => {
            this.$Message.error(error.toString())
          });
        }
        if (name === '日志') {
          this.logs(params.row.instanceId)
          return;
        }
      }
    },
    created(){
      this.$bus.$on('on-subwin-change',this.reload);
    },
    destroyed(){
      this.$bus.$off('on-subwin-change',this.reload);
    }
  }
</script>
<style type="text/css">
  .page-datatable .ivu-table td, .mywork-page .page-datatable .ivu-table th {
    height: 39px !important;
   }
  .mywork-start-extra{margin-right: 20px;}
  .mywork-page .page-searchbox .search-right{width: 1%;padding-right: 15px;text-align: right;}
   
</style>
