<template>
    <Modal
      :width="500"
      v-model="show"
      :title="title">
        <div class="content-title">角色名称</div>
        <Input v-model="group.title" style="width: 300px"/>
        <div slot="footer">
           <Button type="primary" @click="saveRole">保存</Button> 
           <Button type="text" @click="cancel">取消</Button> 
        </div>
    </Modal>
</template>
<script>
  export default {
    name:'GroupCreate',
    data() {
      return {
        show:false,
        title:'新建角色',
        isEdit:false,
        group:{}
      }
    },
    mounted () {
    },
    methods: {
      open(id){  
         if( typeof id == 'undefined'){
            this.createRole();
         }else{
            this.get(id);
            this.updateRole();
         }
      },
       createRole(){
        this.isEdit = false;
        this.title = '新建角色';
        this.show = true; 
        this.group.empIds = '';       
       },
       updateRole(){
        this.isEdit = true;
        this.title = '编辑角色';
        this.show = true;
       },
       get(id){ 
         this.$http.post('/api/wf/group/get?id=' + id,{}).then((res) => {
              if (res.data.code === 0) {
                  this.group = res.data.data; 
              } else {
                  this.$Message.error(res.data.message);
              }
            }).catch((error) => {
                this.$Message.error(error.toString())
            }) 
       },
       saveRole(){
         if(this.isEdit){
           this.$http.post('/api/wf/group/update',this.group).then((res) => {
              if (res.data.code === 0) {
                  this.cancel();
                  this.$emit("on-submit-group");
              } else {
                  this.$Message.error(res.data.message);
              }
            }).catch((error) => {
                this.$Message.error(error.toString())
            })

         }else{
          this.$http.post('/api/wf/group/create',{title:this.group.title,empIds:this.group.empIds}).then((res) => {
              if (res.data.code === 0) {
                  this.cancel();
                  this.$emit("on-submit-group");
              } else {
                  this.$Message.error(res.data.message);
              }
            }).catch((error) => {
                this.$Message.error(error.toString())
            })
          }        
       },
       cancel(){
          this.group = {};
          this.show = false;
       }
    }
  }
</script>
<style type="text/css" scoped>
  .content-title{font-weight: normal; font-size: 14px; margin: 15px 0; padding-bottom: 5px;}
</style>
