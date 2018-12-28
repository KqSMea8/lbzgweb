<template>
  <div class="page">
    <div class="page-bar">
      <table class="page-bar-layout" cellspacing="0" cellpadding="0">
        <tr>
          <td class="page-bar-left">
            <Button size="small" icon="chevron-left" @click="goBack" type="warning">返回</Button>
          </td>
          <td class="page-bar-title">{{app.title}}</td>
          <td class="page-bar-right"></td>
        </tr>
      </table>
    </div>
     <div class="page-content">
         <div class="content-title">应用菜单</div>
            <ul class="applist">
               <template v-for="form in formList">
                   <li @click="openForm(form.id)">
                     <div class="applist-area">
                       <span class="app-list-icon app-img"><img :src="getIcon('')"></span>
                       <span class="app-list-name">{{form.title}}</span>
                     </div>
                   </li>
               </template>
            </ul>
       <div class="content-title">应用数据</div>
          <Loading :loading="loading">
            <Tabs :animated="false" v-model="name" @on-click="myProcess">
              <TabPane label="待处理" name="handling" >
                  <div class="page-datatable">
                        <i-table :columns="columns1" :data="list" ></i-table>
                        <div style="height:10px;"></div>
                        <Page ref="pagebar1" :total="total" size="small" class-name="page"
                          @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top" :transfer="true"></Page>
                  </div>
              </TabPane>
              <TabPane label="已处理" name="handled" >
                    <div class="page-datatable">
                        <i-table :columns="columns2" :data="list" ></i-table>
                        <div style="height:10px;"></div>
                       <Page ref="pagebar2" :total="total" size="small" class-name="page"
                          @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top" :transfer="true"></Page>
                    </div>
              </TabPane>
            </Tabs>
          </Loading>
      </div>
      <!--<div>-->
         <!--<Loading v-if="loading"></Loading>-->
      <!--</div>-->
     </div>
</template>
<script>
  import Loading from '@/components/loading';
  export default {
    components: {
      Loading
    },
    data() {
      return {
        loading:0,
        formList:[],
        id:this.$route.params.id,
        app:{},
        list:[],
        name:'handling',
        columns1:[
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
            width: 120
          },
          {
            title: '发起人',
            key: 'starter',
            width: 80,
            align: 'center'
          },
          {
            title: '接收时间',
            key: 'receiveTime',
            align: 'center',
            width: 120
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
            width: 120
          },
          {
            title: '发起人',
            key: 'starter',
            width: 80,
            align: 'center',
          },
          {
            title: '处理时间',
            key: 'dealTime',
            align: 'center',
            width: 120
          },
          {
            title: '当前处理人',
            key: 'dealName',
            align: 'center',
            width: 80
          },
          {
            title: '流程状态',
            key: 'status',
            align: 'center',
            width: 80
          }
        ],
        total:0,
        queryParam:{},
        queryForm:{
          status:3,
          page:'',
          pageSize:''
        },
        curPagebar:null
      }
    },
    mounted () {
       this.load();
       this.getForms();
       this.myProcess(this.name);
    },
    methods: {
      load(){
        this.$http.post('/api/wf/app/get?id=' + this.id,{}).then((res) => {
            if (res.data.code === 0) {
                this.loading = 0;
                this.app = res.data.data;
            } else {
                this.$Message.error(res.data.message);
            }
          }).catch((error) => {
              this.$Message.error(error.toString())
          });
      },
      getForms(){
          this.$http.post('/api/wf/form/list',{appId:this.id}).then((res) => {
          if (res.data.code === 0) {
            this.loading = 0;
              this.formList = res.data.data;
          } else {
              this.$Message.error(res.data.message);
          }
        }).catch((error) => {
            this.$Message.error(error.toString())
        });
      },
      myProcess(name){
            let status = 0;
            if(name == 'handling'){
              status = 3;
              this.curPagebar = this.$refs.pagebar1;
            }else{
              status = 4;
              this.curPagebar = this.$refs.pagebar2;
            }
            this.loading = 1;
            this.list = [];
            this.queryForm.status = status;
            this.queryForm.page = this.curPagebar.currentPage;
            this.queryForm.pageSize = this.curPagebar.currentPageSize;

            this.$http.post('/api/wf/process/myProcess',this.queryForm).then((res) => {
              if (res.data.code === 0) {
                  this.loading = 0;
                  this.list = res.data.data.rows;
                  this.total = res.data.data.total;
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
      getIcon(src){
          return require('../../assets/img/application.png');
      },
      goBack(){
        this.$router.back(-1);
      },
      openForm(id){
        let routeData = this.$router.resolve({ path:'/workflow/initproc?formid=' + id});
        window.open(routeData.href, '_blank');
      },
      pageChange(page){
        var that = this;
        //将参数对象数据赋值个表单进行查询
        $.extend(that.queryForm,that.queryParam);
        this.myProcess(this.name);
      },
      pageSizeChange(pageSize){
        var pagebar = this.curPagebar;
        if(pagebar.currentPage == 1){
          this.myProcess(this.name);
        }
      }

    }
  }
</script>
<style type="text/css" scoped>
  .content-title{ font-weight: normal; font-size: 14px; margin: 15px 0; padding-bottom: 5px;width: 100%}
  .content-title .content-title-right{margin-right:100px;float: right;}
  .applist{border-bottom: 1px #eee dashed; padding-bottom: 5px;}
  .applist li{
    width: 120px;
    height: 150px;
    display: inline-block;
    margin-bottom: 20px;
    margin-right: 28px;
    vertical-align: top;
    cursor: pointer;
   }
   .applist li:hover{
    box-shadow: 0 0 10px rgba(0,0,0,.1);
    cursor: pointer;
  }
  .applist .add-form{border:1px solid #e1e1e1;}
  .applist .applist-area {
    background: #ffffff;
    border: 1px solid #e6e6e6;
    width: 100%;
    height: 100%;
    display: block;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    border-radius: 3px;
    text-align: center;
    position: relative;
  }
  .applist li .applist-area .app-list-name {
    padding: 0 8px;
    font-size: 14px;
    margin-top: 10px;
    height: 40px;
    color: #515151;
    line-height: 1.3;
    overflow: hidden;
    text-overflow: ellipsis;
    display: block;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
}
.applist li .applist-area .app-list-icon {
  color: #747474;
    margin-top: 28px;
    display: inline-block;
    line-height: normal;
}
.applist li .applist-area .app-img img{
    width: 42px;
    height: 42px;
    margin-bottom: 5px;
    display: inline-block;
    margin-top: 10px !important
}
</style>
