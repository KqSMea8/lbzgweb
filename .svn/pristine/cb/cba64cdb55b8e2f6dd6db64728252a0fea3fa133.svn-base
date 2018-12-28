<template> 
  <div class="mobileformpage wt-scroll">
    <div class="mobileformpage-wrap">
      <div class="mobileformpage-head"> 
        <div class="mobileformpage-title">
          {{title}}
        </div>
        <div class="mobileformpage-description">
          {{description}}
        </div>
      </div> 
      <div v-for="(item,index) in fields" :key="index">
        <Field ref="fields" 
        :model="item.options" :design="false" 
        :require="item.require" 
        :access="item.access"
        @selectPerson="selectPerson"
        @selectDept="selectDept"
        @selectMultiPerson="selectMultiPerson"
        @selectMultiDept="selectMultiDept"></Field>
      </div>
    </div>
  </div>
</template>

<script>
  
  import Field from '@/components/workflow/components/form/Field';

  export default {
  components: { 
    Field
  },
  props:{
    title:{
      type:String,
      default:'标题'
    },
    description:{
      type:String,
      default:'描述'
    },
    fieldOptions:{ 
      type:Array,
      default:()=>{ 
        var options = [];
        options = [];
        return options;
      }
    },
    fieldAuth:{
      type:Object,
      default:()=>{
        return {};
      }
    }
  },
  data() {
    var retVal = {}; 
    retVal.fields = [];
    return retVal;
  },
  mounted:function(){
     
  },
  computed:{

  },
  watch:{
    fieldOptions(val){
      this.fields = [];
      if(val){
        val.map((item)=>{
          var field = {
            options:item,
            require:false,
            access:1
          };
          this.fields.push(field); 
        });
      }
      this.innerSetFieldAuth(this.fieldAuth);
    },
    fieldAuth(val){
      this.innerSetFieldAuth(val);
    }
  },
  methods:{  
    load:function(){
    },
    getValue(){
      var retVal = {};
      var fields = this.$refs['fields'];
      if(fields){
        for(var i = 0;i<fields.length;i++){
          fields[i].getValues(retVal);
        }
      } 
      return retVal;
    },
    setValue(obj){ 
      var fields = this.$refs['fields']; 
      for(var i = 0;i<fields.length;i++){
        fields[i].setValues(obj);
      }
    },
    validate(){
      var isPass = true;
      var fields = this.$refs['fields'];
      for(var i = 0;i<fields.length;i++){
        isPass = fields[i].validate() && isPass;
      }
      return isPass;
    },
    innerSetFieldAuth(auths){
      var fd = this.getValue();
      this.fields.map((field)=>{
        var id = field.options.id;
        var auth = auths[id];
        if(auth){
          field.require = auth.require && true;
          field.access = auth.auth + 0;
        }
      });
    },
    selectPerson(field){
      this.$emit('selectPerson',field);
    },
    selectDept(field){
      this.$emit('selectDept',field);
    },
    selectMultiPerson(field){
      this.$emit('selectMultiPerson',field);
    },
    selectMultiDept(field){
      this.$emit('selectMultiDept',field);
    }
  } 
}
</script>

<style type="text/css" scoped>
  .mobileformpage{
    background-color: rgb(195, 231, 255);
    height: 100%;
    padding: 20px 10px;
    overflow-y: auto;
    position: relative;
  } 

  .mobileformpage-wrap{
    max-width: 640px;
    margin:0px auto;
  }

  .mobileformpage-head{
    min-height: 100px;
    text-align: center;
    padding-top: 20px;
    background-color: #4FACE9;
    color:white;
  }
  .mobileformpage-title{
    font-size: 16px;
  }
  .mobileformpage-description{
    font-size: 12px;
  } 
</style> 
