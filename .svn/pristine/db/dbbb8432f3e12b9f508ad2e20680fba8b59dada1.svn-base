<template> 
  <div :class="classes">
    <div class="process-comment-head-tool">
      <Icon type="close-round" @click.native="close" class="process-comment-close"></Icon>
    </div> 
    <Input v-model="comment" type="textarea" :rows="4" placeholder=""></Input>
    <div class="process-comment-btns"> 
      <Button type="primary" @click="submit">提 交</Button>
      <Poptip trigger="click" title="" width="100">
        <Button type="ghost" class="process-comment-btns-more" >...</Button>
        <div slot="content" class="process-comment-pop" >
          <Button type="dashed" @click="stop">终 止</Button> 
          <Button type="dashed" @click="back">回 退</Button>
          <Button type="dashed" @click="sign" v-if="false">加 签</Button>
          <Button type="dashed" @click="notice" v-if="false">知 会</Button>
        </div>
      </Poptip>
    </div>
  </div>
</template>
<script> 
　
export default {
  name:'processComment',
  components: { 
     
  },
  data() {
    return { 
      comment:'',
      fiels:[]
    }
  },
  computed:{
    classes(){
      return [
        'process-comment' 
      ];
    }
  },
  mounted(){
    this.load();
  }, 
  methods:{
    load(){

    },
    stop(){
      this.$emit('do',this,'stop');
    },
    back(){
      this.$emit('do',this,'back');
    },
    sign(){
      this.$emit('do',this,'sign');
    },
    submit(){
      this.$emit('do',this,'submit');
    },
    notice(){
      this.$emit('do',this,'notice');
    },
    notice(){
      this.$emit('do',this,'reject');
    },
    close(){ 
      this.$emit('close');
    }
  } 
}

</script>
<style type="text/css">
  /*布局*/
  .process-comment{
    width: 640px;
    position: absolute;
    bottom:0px;
    padding: 30px;
    padding-top: 40px; 
    left: 50%;
    margin-left: -320px;
    bottom: 0;
    box-shadow: 0 0 10px rgba(0,0,0,.15);
    background-color: white;  
  }
  .process-comment-head-tool{
    text-align: right;
    margin-top: -30px;
    margin-right: -20px;
  }
  .process-comment-close{
    font-size: 18px;
    color:#999;
    cursor: pointer;
  }
  .process-comment-close:hover{
    color:#333;
  }
  .process-comment-btns{
    margin-top:10px;
    text-align: right;
  }
  .process-comment-btns .ivu-btn{
    margin-left:20px;
  }
  .process-comment-pop{ 
    width: 100%;
  }
  .process-comment-pop .ivu-btn{
    float: left;
    margin:0px;
    width: 100%;
  }
  .process-comment .ivu-poptip-body{
    padding: 0px;overflow:hidden;
  }
</style>
