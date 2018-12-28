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
    <div class="page-content wt-scroll">
         <div class="page-content-info app-info">
              <div class="app-info-img">图标</div>
              <div class = "app-info-basic">
                 <p class="app-title"><span v-if="!editInfo">{{app.title}}</span>
                 	<Icon type="edit" size="16" color="#80848f" class="app-title-icon" @click.native="editTitle" v-if="!editInfo"></Icon>
                  <Input  v-model="app.title" v-if="editInfo"/>
                 </p>
                 <p class="app-dec">
                  <span v-if="!editInfo">{{app.description}}</span> 
                  <Input  v-model="app.description" type="textarea" :rows="4" v-if="editInfo"/>
                 </p>
                 <p class="app-btn"><Button  type="primary" @click="save"  v-if="editInfo">保存</Button>
                    <Button  type="ghost" @click="cancel" style="margin-left: 35px"  v-if="editInfo">取消</Button></p>
              </div>
         </div>   
         <div class="content-title">
         	<span>使用范围</span>
         	<span class="content-title-edit">更多设置</span>
         </div> 
         <div class="page-content-info usescope">
            <template v-for="emp in persons">
              <Icon type="android-contact" class="icon-type"></Icon>{{emp.trueName}}
            </template>
            <a @click="selectPersons" v-if="persons.length == 0 ">点击选择人员</a>
            <a @click="selectPersons(persons)" v-if="persons.length > 0 ">编辑</a>
         </div> 
         <div class="content-title">
         	<span>全部表单</span>
         	<span class="content-title-edit" @click="addForm">添加表单</span>
         </div>
         <div class="page-content-info app-forms">
             <ul class="formlist">
               <template v-for="item in formList">
                   <li @click="formSetting(item.id)">
                      <div class="formlist-area">
                        <span class="form-list-icon form-img"><img :src="getIcon('')"/></span>
                        <span class="form-list-name">{{item.title}}</span>
                      </div>
                   </li>
               </template>
          	</ul>
         </div>  
         <div class="content-title">高级设置</div>
         <div class="page-content-info setting">
            <Card class="form-row-card" dis-hover> 
              <table cellpadding="0" cellspacing="0">
                    <tr><td>菜单设置</td></tr> 
                    <tr><td>为应用设置菜单</td></tr> 
                    <tr><td>Web端与应用端同步实现</td></tr>                      
              </table>
            </Card>
         </div>  
    </div>
    <div>
        <FormCreate ref="formCreate" @on-form-create="getForms"></FormCreate>
        <SelectDeptPerson ref="selectDeptPerson"></SelectDeptPerson> 
    </div>
  </div>
</template>

<script>
  import FormCreate from '@/components/workflow/FormCreate';
  import SelectDeptPerson from '@/components/workflow/components/SelectDeptPerson';
  export default {
    components: {
      FormCreate,
      SelectDeptPerson
    },
    data() {
      return {
        formList: [],
        id:this.$route.params.id,
        app:{},
        persons:[],
        editInfo:false
      }
    },
    created() {
      this.load();
      this.getForms();
    },
    methods: {
    	goBack(){
    		this.$router.back(-1);
    	},
      load(){
         this.$http.post('/api/wf/app/get?id=' + this.id,{}).then((res) => {
          if (res.data.code === 0) {
               let data = res.data.data;
               this.app = data;
               this.query();
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
              this.formList = res.data.data;
          } else {
              this.$Message.error(res.data.message);
          }
        }).catch((error) => {
            this.$Message.error(error.toString())
        });
      },
    	editTitle(){
    		this.editInfo = true;
    	},
    	getIcon(src){
	        return require('../../assets/img/application.png');
	    },
      addForm(){
        this.$refs.formCreate.open(this.id);
      },
      save(){
        this.$http.post('/api/wf/app/update',this.app).then((res) => {
          if (res.data.code === 0) {
              this.editInfo = false;
          } else {
              this.$Message.error(res.data.message);
          }
        }).catch((error) => {
            this.$Message.error(error.toString())
        });
      },
      cancel(){
        this.editInfo = false;       
      },
      formSetting(id){
         this.$router.push({path:'/workflow/formdesign?id=' + id});
      },
      query:function(){
        let empIds = this.app.useScope;
        this.$http.post('/api/contacts/getBaseInfo', {empIds}).then((res) => {
          if (res.data.code === 0) {
            let datas = res.data.data
            this.persons = datas
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error.toString())
        });
      },
      setUseScope(emps){       
        let authForm = {
          id:this.app.id,
          useScope:emps
        }
        this.$http.post('/api/wf/app/setAuth',authForm).then((res) => {
          if (res.data.code === 0) {
              this.app.useScope = emps;
          } else {
              this.$Message.error(res.data.message);
          }
        }).catch((error) => {
            this.$Message.error(error.toString())
        });
      },
      selectPersons(persons){
          var pop = this.$refs.selectDeptPerson;

          pop.selectPerson = true;
          pop.selectDept = false;
          pop.isMulti = true;
  
          pop.$off('ok');
          pop.$on('ok',()=>{ 
            pop.$off('ok');
            var empIds =  pop.select.map((person)=>{
                            return person.key;
                          }).join(','); 
            this.setUseScope(empIds);
            this.persons =  pop.select.map((person)=>{
                            let emp = {empId:person.key,trueName:person.title}
                            return emp;
                          });
          });
          pop.show();
          pop.select = this.innerGetDeptPerson(persons);
       },
      innerGetDeptPerson(field){
          var arr = [];
          var vals = field;
          for(var i=0;i<vals.length;i++){
             var item = {type:'',key:'',title:''};
             item.type = 'person';
             item.key = vals[i].empId;
             item.title = vals[i].trueName;
             arr.push(item);
          }
          return arr;
    }
	}
}
</script>

<style type="text/css" scoped>
   .page-content{height: 100%;width: 100%;overflow-x: auto;margin-top: 20px;}
   .content-title{ font-weight: normal; font-size: 14px; margin: 15px 0; padding-bottom: 5px;}
   .content-title .content-title-edit { display: inline-block;float: right;margin-right: 10px;cursor: pointer;color: #20bfee;}
   .page-content-info .content-title-edit{cursor: pointer;color: #20bfee;}
   .page-content-info{border-bottom: 1px #eee dashed; padding-bottom: 15px;font-size: 14px;}
   .usescope{width: 100%;max-height: 500px;overflow-x: auto;}
   .usescope .icon-type{
    font-size: 18px;
    color: #5990cf;
    padding-left:2px; 
   }
   .usescope a {
    margin-left: 10px;
    cursor: pointer;
    color: #20bfee;
   }
   .app-info{
     width: 100%;
   }
   .app-info .app-info-img{
   	  display: inline-block;
      vertical-align: top;
      width: 60px;
	    height: 60px;
	    text-align: center;
	    line-height: 58px;
	    border: 1px solid #e6e6e6;
	    -webkit-border-radius: 3px;
	    -moz-border-radius: 3px;
	    border-radius: 3px;
	    cursor: pointer;
	    position: relative;
   }
   .app-info .app-info-basic{
      width: 500px;
   	  display: inline-block;
      vertical-align: top;
      margin-left: 30px;
   }
   .app-info .app-title{
   	  font-size: 14px;
      color: #5c5c5c;
      margin-bottom: 10px;
   }
   .app-info .app-title .app-title-icon{
   	  margin-left: 5px;
      cursor: pointer;
   }
   .app-info .app-dec{
   	 font-size: 12px;
     color: #a8a8a8;
   }
   .app-info .app-btn{
    margin-top: 10px;
   }

  .formlist{padding-bottom: 5px;}
  .formlist li{    
  	width: 120px;
    height: 150px;
    display: inline-block;
    margin-bottom: 20px;
    margin-right: 28px;
    vertical-align: top;
   }
  .formlist li:hover{
    box-shadow: 0 0 10px rgba(0,0,0,.1);
    cursor: pointer;
  }
  .formlist .formlist-area {
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
  .formlist li .formlist-area .form-list-name {
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
.formlist li .formlist-area .form-list-icon {
	color: #747474;
    margin-top: 28px;
    display: inline-block;
    line-height: normal;
}
.formlist li .formlist-area .form-img img{
    width: 42px;
    height: 42px;
    margin-bottom: 5px;
    display: inline-block;
    margin-top: 10px !important
}
.page-content-info .form-row-card{
  width: 300px;
}
.page-content-info .form-row-card table{
  width:100%;
  height: 100%;
}
.page-content-info .form-row-card table td{
  text-align: center;
}


</style>
<style>
   
</style>
