<template>
  <div :class="{'tyleftbar':1,'tyleftbar-fold':fold,'tyleftbar-unfold':!fold,'noselect':1}"
   @mouseover="mouseover"  @mouseout="mouseout">
    <LeftSubMenu ref="leftSubMenu" :fixed="subFixed"
    @on-open="subOpen" @on-fixed-change="subFixedChange"
    :class="{'tyleftbar-ext':1,'tyleftbar-ext-fold':!showSubMenu}"></LeftSubMenu>
    <div class="tyleftbar-head">
      <span class="tyleftbar-head-title">我的应用</span>
      <Icon v-if="fold" @click.native="switchFold"
        type="chevron-right tyleftbar-head-icon"></Icon>
      <Icon v-else @click.native="switchFold"
        type="chevron-left tyleftbar-head-icon"></Icon>
    </div>
    <div class="tyleftbar-menu wt-scroll">
      <Tooltip :disabled="true" :content="item.title" placement="right-start" transfer v-for="(item,index) in navs" >
        <div class="tyleftbar-menu-item" @click="open(item)" :title="item.title">
          <Icon :type="item.iconfont" class="tyleftbar-menu-item-icon" v-if="item.iconfont"></Icon>
          <img :src="item.icon" v-else class="tyleftbar-menu-item-icon"/>
          <span class="tyleftbar-menu-item-title">
            {{item.title}}
          </span>
        </div>
      </Tooltip>
    </div>
  </div>
</template>

<script>

import storage from '@/assets/js/storage'
import LeftSubMenu from './LeftSubMenu'
import power from './power'

export default {
  components: {
    LeftSubMenu
  },
  data() {
    return {
      fold:false,
      navs:[],
      curNav:{},
      showSubMenu:false,
      subFixed:false,
      timer:null
    }
  },
  mounted: function () {
    this.load();
  },
  methods: {
    load(){
      this.fold = true;//storage.getLocal('leftMenuFold');
      this.subFixed = true;
      this.navs = power.navs;
      this.$emit('on-size-change',this);
    },
    open(item){
      this.curNav = item;

      if(item.view){
        this.$router.push({name:item.view});
      }else if(item.uri){
        this.$router.push({path:item.uri});
      }

      this.$refs.leftSubMenu.nav = item;
      this.showSubMenu = item.subItems.length > 0;
    },
    subOpen(item){
      if(item.view){
        this.$router.push({name:item.view});
      }else if(item.uri){
        this.$router.push({path:item.uri});
      }
    },
    subFixedChange(){
      this.subFixed = !this.subFixed;
      if(!this.subFixed){
        this.$emit('on-size-change',this);
      }
    },
    switchFold(){
      this.fold = !this.fold;
      this.$emit('on-size-change',this);
    },
    mouseover(){
      clearTimeout(this.timer);
      var menu = this.$refs.leftSubMenu.nav;
      if(menu && menu.subItems && menu.subItems.length > 0){
        this.showSubMenu = true;
      }
    },
    mouseout(){
      this.timer = setTimeout(()=>{
        if(!this.subFixed){
          this.showSubMenu = false;
        }
      },1000);
    },
    getWidth(){
      var width = 0;
      if(this.fold){
        width += 50;
      }else{
        width += 200;
      }
      if(this.showSubMenu && this.curNav.subItems.length > 0){
        width += 150;
      }
      return width;
    }
  },
  computed:{
  },
  watch:{
    showSubMenu(val,old){
      this.$emit('on-size-change',this);
    }
  },
  created: function() {
    var that = this;
    this.$bus.$on('on-router-after-each', function (para) {
      var params = para.to.params.aid;
      var path = para.to.path;
    })
  }
}
</script>

<style type="text/css">
  .tyleftbar {
    height: 100%;
    padding-top: 42px;
    background-color: #414141;
  }
  .tyleftbar:after {
    content: '';
    display: block;
    width: 1px;
    height: 100%;
    background: #eeeeee;
    position: absolute;
    right: 0;
    top: 0;
  }
  /*头部*/
  .tyleftbar-head {
    height: 42px;
    line-height: 42px;
    padding: 0 0 0 15px;
    font-size: 14px;
    color: white;
    border-bottom: 1px solid #535353;
    cursor: default;
    margin-top: -42px;
  }

  .tyleftbar-head-icon{
    float: right;
    color: white;
    cursor: pointer;
    line-height: 42px;
    width: 50px;
    display: block;
    text-align: center;
  }
  .tyleftbar-head-icon:hover{
    background-color: #c5261d;
    color:white;
  }

  /*菜单*/
  .tyleftbar-menu{
    height: 100%;
    overflow-y: auto;
    color:#fff;
  }
  .tyleftbar-menu-item{
    line-height: 42px;
    height: 42px;
    overflow: hidden;
    font-size: 14px;
    padding: 0px 15px;
    padding-left: 42px;
    cursor: pointer;
    position: relative;
  }
  .tyleftbar-menu-item:hover{
    background-color: #c5261d;
    color:white;
  }
  .tyleftbar-menu-item:hover .tyleftbar-menu-item-icon{
    color:white;
  }
  .tyleftbar-menu-item-icon{
    position: absolute;
    display: block;
    left:17px;
    top:13px;
    width: 16px;
    height: 16px;
    font-size: 16px;
    color:#ccc;
    text-align: center;
  }
  /*fold 状态*/
  .tyleftbar-unfold{
    width: 200px;
  }
  .tyleftbar-fold{
    width: 50px;
  }
  .tyleftbar-fold .tyleftbar-menu-item{
    padding: 0px 15px;
  }
  .tyleftbar-fold .tyleftbar-head-title,
  .tyleftbar-fold .tyleftbar-menu-item-title
  {
    display: none;
  }
  /*提示*/
  .tyleftbar .ivu-tooltip {
    display:initial;
    position: initial;
  }
  .tyleftbar .ivu-tooltip-rel {
    display:initial;
    position: initial;
  }
  /**/
  .tyleftbar-ext{
    position: absolute;
    right:-150px;
    z-index: 999;
    top:0px;
  }
  .tyleftbar-ext-fold{
    display: none;
  }
</style>
