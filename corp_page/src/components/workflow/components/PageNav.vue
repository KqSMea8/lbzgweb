<template>
  <div :class="classes">
    <div v-for="(ele,index) in tabs" :key="index" @click="$emit('click',ele,index)" :class="{'wf-pagenav-item':1,'wf-pagenav-item-active':index == cur}">
      {{ele}}
    </div>
  </div>
</template>
<script>

export default {
  name:'pagetabs',
  props:{ 
    tabs:{
      type:Array,
      default:['tab1','tab1']
    },
    cur:{
      type:Number,
      default:0
    }
  },
  data() {
    return {
    }
  },
  mounted:function(){

  },
  computed:{
    classes(){
      return [
        'wf-pagenav'
      ];
    }
  },
  methods:{
     
  }
}

</script>

<style type="text/css">
  .wf-pagenav{
    background: #F9FAFB;  
    height: 40px;
    line-height: 40px;
    text-align: center;
    cursor: default;
    font-size: 14px;
  }

  .wf-pagenav-item{
    display: inline-block;
    padding: 0px 10px; 
    border-bottom:2px solid transparent; 
    cursor: pointer;
    box-sizing:border-box;
    -moz-box-sizing:border-box; /* Firefox */
    -webkit-box-sizing:border-box; /* Safari */
    height: 100%;
    margin: 0px 2px;
  }

  .wf-pagenav-item:hover{
    color:#5990cf
  }

  .wf-pagenav-item.wf-pagenav-item-active{
    border-bottom:2px solid #5990cf;
  }
</style>