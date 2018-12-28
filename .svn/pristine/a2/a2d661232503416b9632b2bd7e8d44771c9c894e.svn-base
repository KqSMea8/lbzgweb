<template>
  <div class="wf-flowproperty-publish">
    <div class="wf-flowproperty-publish-head">
      流程属性
      <a @click="goEdit" class="wf-flowproperty-publish-head-edit" v-if="!edit">编辑</a>
    </div>
    <div class="wf-flowproperty-property-title">
      流程标题
      <span class="property-flow-name">{{flowTitle}}</span>
    </div>

    <div>
      <!--<Loading v-if="loading"></Loading>-->
      <template v-if="editShow">
        <Edit ref="editCom" v-model="editShow" @callBack="setTitle" />
      </template>
    </div>
  </div>
</template>

<script>
  const Edit = () => import('./EditProperty')
  export default {
  components: {
    Edit
  },
  data() {
    return {
      editShow: false,
      id:0,
      appId:0,
      edit:false,
      data:{
        start:1
      },
      form:{
        start:1
      },
      flowTitle:'',
      formFields:{},
      fieldMap:{}
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
      this.getForm(id);
    },
    getForm(id){
      var that = this;
//      that.reportId = report;
      this.$http.post("/api/wf/form/get?id="+id, {}).then((res) => {
        if (res.data.code === 0) {
          that.loading = 0;
          that.formObj = res.data.data;
          that.formFields = JSON.parse(res.data.data.fieldsJson);
          this.buildFieldMap();
          this.setFormTitle();

        } else {
          that.loading = 0;
          that.list = null;
          this.$Message.error(res.data.message);
        }
      }).catch((error) => {
        console.log(error)
        this.$Message.error("请求失败，请重新发送")
      });
    },

    setFormTitle(){
      var that = this;
      var regex = /\{\{(.+?)\}\}/g;
      var title = that.formObj.flowTitle;
      var titleVar = title.match(regex);
      if(titleVar){
        var map = that.fieldMap;
        titleVar.forEach(function(val,key) {

          val = val.replace('{{','').replace('}}','');
          var field = map.get(val);
          if(field){
            var fieldTitle = field.title;
            title = title.replace(val, fieldTitle);
          }
        })
      }
      this.flowTitle = title;
    },
    buildFieldMap(){
      //将formFields 构建成map
      var that = this;
      var formFields = that.formFields;

      //将datafields 转换成json 格式
      var fieldMap = new Map();
      formFields.forEach(function(field,key){
        let id = field.id;
        fieldMap.set(id,field);
      })
      that.fieldMap = fieldMap;
    },
    goEdit:function(){
      this.form.start = this.data.start;
      //this.edit = true;
      this.editShow = true;

      setTimeout(() => {
        let VM = this;
        let edit = VM.$refs.editCom;
        edit.open(this.id);
      }, 100)

    },
    cancel:function(){
      this.edit = false;
    },
    setTitle(flowTitle){
      this.flowTitle = flowTitle;
    }
  }
}
</script>

<style type="text/css" scoped>
.wf-flowproperty-publish{
  font-size: 14px;
}
.wf-flowproperty-publish-head{
  line-height: 40px;
  border-bottom: 1px solid #eee;
  padding: 0px 20px;
  height: 40px;
}
.wf-flowproperty-property-title{
  line-height: 60px;
  border-bottom: 1px solid #eee;
  padding: 0px 20px;
  height: 60px;
}
.wf-flowproperty-publish-head-edit{
  display: inline-block;
  float: right;
}
.wf-flowproperty-publish-form{
  margin-top: 20px;
  margin:20px;
}
.wf-flowproperty-publish-form.ivu-form .ivu-form-item-label{
  font-size: 14px;
}
  .property-flow-name{
    margin-left: 20px;
  }
</style>
