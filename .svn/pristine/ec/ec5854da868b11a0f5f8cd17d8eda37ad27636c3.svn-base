<template>
  <div class= "page">
    <div class="page-bar">
      <table class="page-bar-layout" cellspacing="0" cellpadding="0">
        <tr>
          <td class="page-bar-left" width="1"></td>
          <td class="page-bar-title">应用管理</td>
          <td class="page-bar-right"></td>
        </tr> 
      </table> 
    </div>      
	  <div class="page-content">
          <div class="content-title">业务应用</div>          
          	<ul class="applist">
               <li @click="addApp">
	               <div class="applist-area add-form">
                       <span class="app-list-icon"><Icon type="ios-plus-empty" size="64" color="#747474"></Icon></span>
                       <span class="app-list-name">新建应用</span> 
	               </div>
               </li>
               <template v-for="app in appList">
                   <li @click="editApp(app.id)">
                     <div class="applist-area">
                       <span class="app-list-icon app-img"><img :src="getIcon('')"></span>
                       <span class="app-list-name">{{app.title}}</span>
                     </div>
                   </li>
               </template>
          	</ul>
          <div class="content-title">表单应用</div>
            <ul class="applist">
               <li @click="addForm">  
               	   <div class="applist-area add-form">
                       <span class="app-list-icon"><Icon type="ios-plus-empty" size="64" color="#747474"></Icon></span>
                       <span class="app-list-name">新建应用</span> 
	               </div>
	           </li>
               <template v-for="form in formList">
                   <li @click="formSetting(form.id)">
                      <div class="applist-area">
                        <span class="app-list-icon app-img"><img :src="getIcon('')"></span>
                        <span class="app-list-name">{{form.title}}</span>
                      </div>
                   </li>
               </template>
          	</ul>
      </div>
      <div>
          <AppCreate ref="appCreate" @on-submit-app="formCreate" @on-form-create="getApps"></AppCreate>
          <FormCreate ref="formCreate"></FormCreate>
      </div>
  </div>
</template>
<script>
  import AppCreate from '@/components/workflow/AppCreate';
  import FormCreate from '@/components/workflow/FormCreate';
  export default {
    components: {
    	AppCreate,
      FormCreate
    },
    data() {
      return {
        appList: [],
        formList: []
      }
    },
    mounted () {
      this.getApps();
      this.getForms();
    },
    methods: {
    	getApps(){
          this.$http.post('/api/wf/app/list',{}).then((res) => {
          if (res.data.code === 0) {
               this.appList = res.data.data;
          } else {
                this.$Message.error(res.data.message);
          }
        }).catch((error) => {
            this.$Message.error(error.toString())
        })
    	},
    	getForms(){
          this.$http.post('/api/wf/form/list',{}).then((res) => {
          if (res.data.code === 0) {
               this.formList = res.data.data;
          } else {
                this.$Message.error(res.data.message);
          }
        }).catch((error) => {
            this.$Message.error(error.toString())
        })
    	},
	    getIcon(src){
	        return require('../../assets/img/application.png');
	    },
	    addApp(){
           this.$refs.appCreate.open();
	    },
      editApp(id){
         this.$router.push({name:'workflow.appmgr.appedit',params:{id:id}});
      },
      addForm(){
         this.$refs.formCreate.open();
      },
      formCreate(appId){
        this.$router.push({path:'/workflow/formdesign?appId=' + appId});
      },
      formSetting(id){
         this.$router.push({path:'/workflow/formdesign?id=' + id});
      }
	}
}
</script>

<style type="text/css" scoped>
  .content-title{ font-weight: normal; font-size: 14px; margin: 15px 0; padding-bottom: 5px;}
  .content-title .content-title-right{display: inline-block;margin-right:10px; }
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
<style>
   
</style>
