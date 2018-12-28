<template> 
  <div class="wf-fieldauth-publish">
    <div class="wf-fieldauth-publish-head">
      表单授权
      <a @click="goEdit" class="wf-fieldauth-publish-head-edit" v-if="!edit">编辑</a>
    </div>
    <Form class="wf-fieldauth-publish-form" :label-width="120" label-position="left">
      <FormItem label="流程使用者" >
        <template v-for="item in useScope">
          <div class="wf-fieldauth-flow-usescope">
            <Icon type="folder" class="icon-type" v-if="item.type == 'department' "></Icon>
            <Icon type="android-contact" class="icon-type" v-if="item.type == 'person'"></Icon>
            <Icon type="android-contacts" class="icon-type" v-if="item.type == 'role'"></Icon> 
            <span>{{item.title}}</span>          
          </div>
        </template>
        <a @click="select" v-if="edit">编辑</a>
      </FormItem>
      <FormItem v-if="edit">
        <Button type="primary" @click="save">保存</Button>
        <Button type="default" @click="cancel">取消</Button>
      </FormItem>
    </Form>
    <selectPerson ref="selectPerson"  @ok="selectPerson" ></selectPerson>
  </div>
</template>

<script> 
  import selectPerson from '@/components/workflow/formPage/SelectPersonComponent';
  export default {
  components: {  
    selectPerson
  },
  data() {
    return { 
      id:0,
      appId:0, 
      edit:false,
      data:{ 
      },
      useScope:[
        //{type:'person',key:'zhangsan',title:'张三'},
        //{type:'department',key:'1',title:'软件部'},
        //{type:'role',key:'role1',title:'角色'},  
      ]
    };
  },
  mounted:function(){
     
  },
  computed:{

  },
  watch:{
     
  },
  methods:{  
    load:function(id){
       this.id = id; 
       this.$http.post('/api/wf/form/get?id=' + id,{}).then((res) => {
         if (res.data.code === 0) {
                this.data = res.data.data;
                this.build();
            } else {
                this.$Message.error(res.data.message);
            }
      }).catch((error) => {
         this.$Message.error(error.toString())
      }) 
    },
    build(){
      if(this.data.useScope != null && this.data.useScope !=''){
         this.buildPerson();
      } 
      if(this.data.useScopeDept != null && this.data.useScopeDept !=''){
        this.bulidDept();
      }
      if(this.data.useScopeGroup != null && this.data.useScopeGroup !=''){
        this.buildGroup();
      }        
    },
    buildPerson(){
      var empIds = this.data.useScope;
      this.$http.post('/api/contacts/getBaseInfo', {empIds}).then((res) => {
          if (res.data.code === 0) {
            let datas = res.data.data;
            datas.forEach((item) =>{
              this.useScope.push({key:item.empId,title:item.trueName,type:'person'});
            })
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error.toString())
        });
    },
    bulidDept(){
      var deptIds = this.data.useScopeDept;
      this.$http.post('/api/contacts/dept/getDepts?deptIds=' + deptIds, {}).then((res) => {
          if (res.data.code === 0) {
            let datas = res.data.data;
            datas.forEach((item) =>{
              this.useScope.push({key:item.deptId,title:item.deptName,type:'department'});
            })
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error.toString())
        });
    },
    buildGroup(){
      var groupIds = this.data.useScopeGroup;
      this.$http.post('/api/wf/group/getGroups?groupIds=' + groupIds, {}).then((res) => {
          if (res.data.code === 0) {
            let datas = res.data.data;
            datas.forEach((item) =>{
              this.useScope.push({key:item.id,title:item.title,type:'role'});
            })
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error.toString())
        });
    },
    goEdit:function(){     
      this.edit = true;
    },
    cancel:function(){
      this.edit = false;
      this.useScope = [];
      this.load(this.id);
    },
    save:function(){
      var useScopePerson = this.useScope.filter((item) => item.type == 'person'); 
      var useScopeDept = this.useScope.filter((item) => item.type == 'department');
      var useScopeGroup = this.useScope.filter((item) => item.type == 'role');
      var param = {};
      param.id = this.id;
      param.useScope = useScopePerson.map((item) =>{return item.key}).join(',');
      param.useScopeDept = useScopeDept.map((item) =>{return item.key}).join(',');
      param.useScopeGroup = useScopeGroup.map((item) =>{return item.key}).join(',');

      this.$http.post('/api/wf/form/update',param).then((res) => {
         if (res.data.code === 0) {
              this.$Message.info('保存成功');
              this.edit = false;
            } else {
              this.$Message.error(res.data.message);
            }
      }).catch((error) => {
         this.$Message.error(error.toString())
      })
    },
    select:function(){     
      this.$refs.selectPerson.show();
      this.$refs.selectPerson.select = this.useScope;     
    },
    selectPerson:function(){
       var compSelect = this.$refs.selectPerson.select;
       this.useScope = compSelect;
    }
  }
}
</script>

<style type="text/css" scoped>
.wf-fieldauth-publish{ 
  font-size: 14px;
}
.wf-fieldauth-publish-head{
  line-height: 40px;
  border-bottom: 1px solid #eee; 
  padding: 0px 20px;
  height: 40px;
}
.wf-fieldauth-publish-head-edit{
  display: inline-block;
  float: right;
}
.wf-fieldauth-publish-form{
  margin-top: 20px; 
  margin:20px;
}
.wf-fieldauth-publish-form.ivu-form .ivu-form-item-label{
  font-size: 14px;
}
.wf-fieldauth-flow-usescope{
  display: inline-block;
  margin-right: 5px;
}
.wf-fieldauth-flow-usescope .icon-type{
    font-size: 18px;
    color: #5990cf;
    padding-left:2px; 
   }
</style>