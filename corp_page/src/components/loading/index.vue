<template>
  <div :class="{'tyloading':1,
    'tyloading-loading-1':innerLoading==1,
    'tyloading-loading-2':innerLoading==2,
    'tyloading-mask-1':innerMask==1,
    'tyloading-mask-2':innerMask==2}">

    <Alert type="error" show-icon class="tyloading-error"
      v-if="innerLoading==2">{{innerTitle}}</Alert>

    <div class="tyloading-content">
      <slot></slot>
    </div>
    <div class="tyloading-mask-layer" v-if="innerLoading==1"></div>
    <div class="tyloading-icon" v-if="innerLoading==1">
      <img :src="require('./imgs/loading.gif')" />
    </div>
  </div>
</template>
<script>

export default {
  name:'Loading',
  props:{
    title:{
      type:String,
      default:null
    },
    loading:{
      type:Number,
      default:0
    },
    mask:{// 1.遮罩内容区,2.隐藏内容区
      type:Number,
      default:1
    }
  },
  data() {
    return {
      innerTitle:this.title,
      innerLoading:this.loading,
      innerMask:this.mask
    }
  },
  mounted:function(){

  },
  watch:{
    title(val){
      this.innerTitle = val;
    },
    loading(val){
      this.innerLoading = val;
    },
    mask(val){
      this.innerMask = val;
    }
  },
  computed:{

  },
  methods:{
    start(title){
      this.innerTitle = title || '';
      this.innerLoading = 1;
    },
    end(title){
      this.innerTitle = title || '';
      this.innerLoading = 0;
    },
    error(title){
      this.innerTitle = title || '';
      this.innerLoading = 2;
    },
    contentEvent(e){
      if(this.innerLoading == 1){
        e.preventDefault();
        e.stopPropagation();
      }
    }
  }
}

</script>

<style type="text/css">
  .tyloading{
    width: 100%;
    height: 100%;
    min-height: 100px;
  }

  .tyloading-loading-1{
    position: relative;
  }

  .tyloading-mask-layer{
    position: absolute;
    left:0px;
    top:0px;
    width: 100%;
    height: 100%;
    background-color: black;
    opacity: .01;
  }

  .tyloading-icon{
    position: absolute;
    left: 50%;
    top:50%;
    height: 50px;
    padding: 9px 9px;
    margin-left: -25px;
    margin-top: -25px;
    text-align: center;
    border:1px solid #eee;
    background-color: white;
    border-radius: 6px;
  }
  .tyloading-icon img{
    display: inline-block;
  }
  .tyloading-loading-1 .tyloading-content{
    opacity: .80;
  }
  .tyloading-loading-1.tyloading-mask-2 .tyloading-content{
    display: none;
  }
</style>

