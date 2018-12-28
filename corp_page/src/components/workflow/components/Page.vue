<template>
  <div :class="classes">
    <div class="wf-page-head">
      <Row>
        <Col span="6"> 
          <div class="wf-page-head-left">
            <slot name="left"></slot>
          </div> 
        </Col>
        <Col span="12"><div class="wf-page-head-title">{{title}}</div></Col>
        <Col span="6">
          <div class="wf-page-head-right">
            <slot name="right"></slot>
          </div>
        </Col>
      </Row> 
    </div>
    <div class="wf-page-content">
      <slot></slot>
    </div>
  </div>
</template>
<script>

export default {
  name:'page',
  props:{ 
    title:{
      type:String,
      default:'标题'
    },
    max:{
      type:Boolean,
      default:false
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
          'wf-page',
          {
            'wf-page-max':this.max
          }
        ]; 
    }
  },
  methods:{
     
  }
}

</script>

<style type="text/css">
  .wf-page{
    padding: 0px 10px;
  }

  .wf-page-head{
    /*
    background: #F4F6F7;  
    height: 52px;
    line-height: 52px;
    font-size: 14px; 
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    border-bottom: 1px solid #E6E6E6;
    */
    line-height: 40px;
    height: 40px;
    margin: 0px -10px 0px -10px;
    font-size: 14px;
    border-bottom: 1px solid #eee;
    cursor: default;
    background-color: #F9FAFB;
  }

  .wf-page-head-title{
    color: #515151;
    text-align: center;
  }
  .wf-page-head-left{
    text-align: left;
    padding: 0px 10px;
  }
  .wf-page-head-right{
    text-align: right;
    padding: 0px 10px;
  }

  .wf-page-content{
    background-color: white;
  }

  .wf-page.wf-page-max{
    height: 100%;  
    padding: 0px;
    padding-top:41px;
  } 

  .wf-page.wf-page-max .wf-page-head{
    margin: 0px;
    margin-top: -41px; 
  }

  .wf-page.wf-page-max .wf-page-content{
    height: 100%;
  }
</style>