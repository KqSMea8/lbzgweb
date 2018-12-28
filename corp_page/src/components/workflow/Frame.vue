<template>
  <LayoutHor :fix="true" :left="'frame-layout-left'">
    <!--<div slot="left" class="frame-left">-->
      <!--<Menu theme="light" :open-names="openItems" width="150px" @on-select="open" :active-name.sync="activeName" class="frame-left-menu wt-scroll">-->
          <!--<MenuItem :name="item.key" :key="item.key" v-for="item in items">-->
              <!--{{item.title}}-->
          <!--</MenuItem>-->
      <!--</Menu>-->
    <!--</div>-->
    <div class="frame-layout-body">
       <router-view></router-view>
    </div>
  </LayoutHor>
</template>
<script>

import LayoutVer from '@/components/layout/LayoutVer';
import LayoutHor from '@/components/layout/LayoutHor';

export default {
  name:'workflowFrame',
  components: {
    LayoutVer,
    LayoutHor
  },
  data() {
    return {
      items:[
        {key:'myworkk',title:'我的事项1',uri:'/workflow/mywork'},
        {key:'myapp',title:'我的应用',uri:'/workflow/myapp'},
        {key:'appmgr',title:'应用管理',uri:'/workflow/appmgr'},
        {key:'sysmgr',title:'系统管理',uri:'/workflow/sysmgr'}
      ],
      openItems:[],
      activeName:'',
      lastActiveName:'',
      menuStatus:{
        '/workflow/mywork':'myworkk',
        '/workflow/myapp':'myapp',
        '/workflow/appmgr':'appmgr',
        '/workflow/sysmgr':'sysmgr',
      }
    }
  },
  mounted(){
    this.setActiveName();
    if(this.$route.name == 'workflow'){
      this.$router.replace({path: 'workflow/index'});
    }
  },
  methods:{
    open(key) {
        var menu = this.find(key);
        if (menu) {
          this.$router.push({path: menu.uri});
        }
      },
    find(key, menus) {
      if (!menus) {
        return this.find(key, this.items);
      } else {
        for (var i = 0; i < menus.length; i++) {
          if (menus[i].key == key) {
            return menus[i]
          }
          if (menus[i].submenu) {
            var sub = this.find(key, menus[i].submenu);
            if (sub) {
              return sub;
            }
          }
        }
      }
    },
    setActiveName () {
      let routeName = this.$route.name;
      let path = this.$route.path;
      this.activeName = this.menuStatus[path] || '';
    }
  },
  created() {

    this.init1 = (para)=>{
      var path = para.to.path;
      var key = this.menuStatus[path] || '';

      this.activeName = key;
      this.lastActiveName = key;
    }

    this.$bus.$on('on-router-after-each',this.init1);
  },
  destroyed(){
    this.$bus.$off('on-router-after-each',this.init1);
  }
}

</script>`
<style type="text/css">
  /*布局*/
  .frame-left {
     height:100%;
     width:150px;
     background-color: white;
     border-right: 1px solid #eeeeee;
  }
  .frame-left-menu{
    overflow: auto;
    height: 100%;
  }
  .frame-left-menu:hover{
    overflow: auto;
  }
  .frame-layout-left.layout-hor-left{
    width: 150px;
  }
  .frame-layout-body{
    height: 100%;
  }
  /*菜单*/
  .frame-left-menu.ivu-menu-vertical.ivu-menu-light:after {
    background-color: transparent;
  }

  .frame-left-menu.ivu-menu-vertical.ivu-menu-light {
    background-color: transparent;
  }

  .frame-left-menu.ivu-menu-vertical .ivu-menu-item,
  .frame-left-menu.ivu-menu-vertical .ivu-menu-submenu-title {
    padding: 10px 15px;
    color: #666;
  }

  .frame-left-menu.ivu-menu-light.ivu-menu-vertical .ivu-menu-item-active,
  .frame-left-menu.ivu-menu-light.ivu-menu-vertical .ivu-menu-item-active:not(.ivu-menu-submenu) {
    color: inherit;
    border-right: 0px solid #eee;
    color: #fff;
    background-color: #20bfee;
  }

</style>
