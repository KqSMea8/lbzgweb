<template>
  <div class="page">
     <div class="page-content">
         <div class="content-title">业务应用</div>          
            <ul class="applist">
               <template v-for="app in appList">
                   <li @click="goDetail(app.id)">
                     <div class="applist-area">
                       <span class="app-list-icon app-img"><img :src="getIcon('')"></span>
                       <span class="app-list-name">{{app.title}}</span>
                     </div>
                   </li>
               </template>
            </ul>
          <div class="content-title">表单应用
              <a @click="showReport" class="content-title-right" style="padding-top:5px;">查看报表</a>
              
          </div>         
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
      </div>
       <div>
        <FormDetail ref="formDetail"></FormDetail>
      </div>
     </div>
</template>
<script>
  import FormDetail from '@/components/workflow/FormDetail';
  export default {
    components: {
      FormDetail
    },
    data() {
      return {
        appList:[],
        formList:[]
      }
    },
    mounted () {
       this.loadApp();
       this.loadForm();
    },
    methods: {
      loadApp(){
           this.$http.post('/api/wf/app/myApps',{}).then((res) => {
              if (res.data.code === 0) {
                  this.appList = res.data.data;
              } else {
                  this.$Message.error(res.data.message);
              }
            }).catch((error) => {
                this.$Message.error(error.toString())
            });
      },
      loadForm(){
          this.$http.post('/api/wf/form/myForms',{}).then((res) => {
            if (res.data.code === 0) {
                this.formList = res.data.data;
            } else {
                this.$Message.error(res.data.message);
            }
          }).catch((error) => {
              this.$Message.error(error.toString())
          });
      },
      getIcon(src){
          return require('../../assets/img/application.png');
      },
      goDetail(id){
        this.$router.push({name:'workflow.myapp.detail',params:{id:id}});
      },
      openForm(id){
        let routeData = this.$router.resolve({ path:'/workflow/initproc?formid=' + id});
        window.open(routeData.href, '_blank');
      },
      showReport(){
        this.$router.push({name:'workflow.report'}); 
      }
       
    }
  }
</script>
<style type="text/css" scoped>
  .content-title{ font-weight: normal; font-size: 14px; margin: 15px 0; padding-bottom: 5px;width: 100%}
  .content-title .content-title-right{float: right;margin-right: 15px;}
  .applist{border-bottom: 1px #eee dashed; padding-bottom: 5px;}
  .applist li{    
    width: 120px;
    height: 150px;
    display: inline-block;
    margin-bottom: 20px;
    margin-right: 28px;
    vertical-align: top;
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
