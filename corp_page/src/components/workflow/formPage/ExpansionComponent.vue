<template>
  <div class="expansion">
    <div class="expansion-head" @click="switchOpen">
      <Icon type="minus" v-if="open" ></Icon>
      <Icon type="plus" v-else></Icon>
      {{title}}
    </div>
    <div class="expansion-body" v-if="open">
      <slot></slot>
    </div>
  </div>
</template>
<script>

export default {
  name:'expansion',
  props:{ 
    open:{type:Boolean,default:true},
    title:{type:String,default:'标题'}
  },
  data() {
    return {
    }
  },
  mounted:function(){

  },
  computed:{
      
  },
  methods:{ 
    switchOpen:function(){
      if(this.open){
        this.open = false;
      }else{
        this.open = true;
      }
    }
  }
}

</script>

<style type="text/css">
  .expansion{}
  .expansion-head{ 
    line-height: 28px;
    height: 28px;
    color: #76767D;
    font-size: 14px;
    padding-left: 12px;
    background-color: #ededee;
    border-bottom: 1px solid #f6f6f7;
    cursor: default;
  }
  .expansion-body{
    padding:8px;
  }

  .expansion-head .ivu-icon{
    color:#5990cf;
  }
</style>