<template> 
  <div class="wf-propertysetting-publish">
    <div class="wf-propertysetting-publish-head">
      发布设置
      <a @click="goEdit" class="wf-propertysetting-publish-head-edit" v-if="!edit">编辑</a>
    </div>
    <Form v-if="!edit" class="wf-propertysetting-publish-form" :model="data" :label-width="120" label-position="left">
      <FormItem label="启用/停用" >
        <span v-if="data.isOpen">已开启</span>
        <span v-else>已停用</span>
      </FormItem>
      <FormItem label="设置图标样式">
        <span class="icon-size-22 form-iconfont form-icon-lanqiu" style="color: rgb(255, 255, 255);background-color: rgb(208, 208, 208);" title=""></span>
      </FormItem> 
    </Form>
    <Form v-else class="wf-propertysetting-publish-form" :model="form" :label-width="120" label-position="left">
      <FormItem label="启用/停用" >
        <iSwitch v-model="form.isOpen" :true-value="1" :false-value="0"></iSwitch>
      </FormItem>
      <FormItem label="设置图标样式">
        <span class="icon-size-22 form-iconfont form-icon-lanqiu" style="color: rgb(255, 255, 255);background-color: rgb(208, 208, 208);" title=""></span>
      </FormItem>
      <FormItem>
        <Button type="primary" @click="save">保存</Button>
        <Button type="default" @click="cancel">取消</Button>
      </FormItem>
    </Form>
  </div>
</template>

<script> 
	export default {
  components: {  
  },
  data() {
    return { 
      id:0,
      appId:0, 
      edit:false,
      data:{ 
        isOpen:1
      },
      form:{
        isOpen:1
      }
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
            } else {
                this.$Message.error(res.data.message);
            }
      }).catch((error) => {
         this.$Message.error(error.toString())
      }) 
    },
    goEdit:function(){
      this.form.isOpen = this.data.isOpen;
      this.edit = true;
    },
    cancel:function(){
      this.edit = false;
    },
    save:function(){
      var isOpen = this.form.isOpen ? 1 : 0;
      this.$http.post('/api/wf/form/update',{id:this.id,isOpen:isOpen}).then((res) => {
         if (res.data.code === 0) {
              this.data.isOpen = this.form.isOpen;
              this.edit = false;               
            } else {
                this.$Message.error(res.data.message);
            }
      }).catch((error) => {
         this.$Message.error(error.toString())
      }) 
    }
  }
}
</script>

<style type="text/css" scoped>
.wf-propertysetting-publish{ 
  font-size: 14px;
}
.wf-propertysetting-publish-head{
  line-height: 40px;
  border-bottom: 1px solid #eee; 
  padding: 0px 20px;
  height: 40px;
}
.wf-propertysetting-publish-head-edit{
  display: inline-block;
  float: right;
}
.wf-propertysetting-publish-form{
  margin-top: 20px; 
  margin:20px;
}
.wf-propertysetting-publish-form.ivu-form .ivu-form-item-label{
  font-size: 14px;
}
</style>