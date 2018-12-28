<template>
  <div :class="classes" :style="styles" ref="container">
    <div :class="topClasses" :style="topStyles" ref="topContainer">
      <slot name="top"></slot>
    </div>
    <div :class="centerClasses" :style="centerStyles">
      <slot></slot>
    </div>
    <div :class="bottomClasses" :style="bottomStyles" ref="bottomContainer">
      <slot name="bottom"></slot>
    </div>
  </div>
</template>
<script>

export default {
  name:'Layout',
  props:{
     
  },
  data() {
    return {
      height:0,
      top:{
        height:0
      },
      center:{
        height:0
      },
      bottom:{
        height:0
      },
    }
  },
  mounted:function(){
    this.$nextTick(this.startTimeout);
  },
  computed:{
    classes () {
      return [
        'layout'
      ];
    },
    topClasses () {
      return [
        'layout-top'
      ];
    },
    centerClasses () {
      return [
        'layout-center'
      ];
    },
    bottomClasses () {
      return [
        'layout-bottom'
      ];
    },
    styles(){
      return {
        height: '100%',
        width: '100%',
        overflow:'hidden'
      };
    },
    topStyles(){
      return {
        height:this.top.height + 'px',
        width: '100%',
      };
    },
    centerStyles(){
      return {
        height:this.center.height + 'px',
        width: '100%',
      };
    },
    bottomStyles(){
      return {
        height:this.bottom.height + 'px',
        width: '100%',
      };
    },
  },
  methods:{ 
    startTimeout(){
      window.clearTimeout(this.timeout);
        
      if(this.$refs.container){
        var topHeight =0;
        var botttomHeight = 0; 

        if(this.$slots.top){
          topHeight = this.$slots.top[0].elm.offsetHeight;
        }
        if(this.$slots.bottom){
          botttomHeight = this.$slots.bottom[0].elm.offsetHeight;
        }

        this.height = this.$refs.container.offsetHeight;

        this.top.height = topHeight;
        this.bottom.height = botttomHeight;

        var height = this.height - this.top.height - this.bottom.height;
        this.center.height = height > 0?height:0;

        this.$emit('on-size-change');
      } 

      this.timeout = window.setTimeout(()=>{
        this.startTimeout();
      },1000); 
    }
  },
  destroyed(){ 
    window.clearTimeout(this.timeout);
  },
  deactivated(){
    window.clearTimeout(this.timeout);
  },
  activated(){
    this.$nextTick(this.startTimeout);
  }
}

</script>

<style type="text/css">
  .layout-ver{ 
    
  } 
  .layout-ver-top{
     
  }
  .layout-ver-center{
    
  }
  .layout-ver-center-container{ 

  }
  .layout-ver-bottom{ 

  } 
</style>

