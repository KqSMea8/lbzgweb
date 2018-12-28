<template>
  <div class="topbar">
    <LayoutHor>
      <div class="topbar-logo" slot="left">
        <img src="@/assets/img/logo1.jpg" style="height:50px;"/>
      </div>
      <div class="topbar-center"> 
        <div class="topbar-menu">
          <div class="topbar-menu-item" @click="open(item)"
            v-for="(item, index) in menus">
            {{item.title}}
          </div>
        </div>
      </div>
      <table class="topbar-userbox" slot="right" cellspacing="0" cellpadding="0">
        <tr>
          <td>
            <div style="width:10px;"></div>
          </td>
          <td>
            <img class="topbar-userbox-logo" :src="userHeader"/></td>
          <td>
            <div class="topbar-userbox-name">{{userName}}</div>
          </td>
          <td>
            <div style="width:10px;"></div>
          </td>
        </tr>
      </table>
    </LayoutHor>
    <div class="topbar-down hiden">
      <table class="topbar-down-layout" cellspacing="0" cellpadding="0">
        <tr>
          <td class="center"><img class="topbar-down-logo" :src="userHeader"/></td>
        </tr>
        <tr>
          <td class="center"><span>{{userName}}</span></td>
        </tr>
        <tr>
          <td>
            <div class="topbar-down-splitline"></div>
          </td>
        </tr> 
        <tr>
          <td>
            <Button @click="loginInfoChg" class="topbar-down-btnmenu" type="text" icon="locked">我的账号</Button>
          </td>
        </tr>
        <tr>
          <td>
            <Button @click="logout" class="topbar-down-btnmenu" type="text" icon="android-exit">退出</Button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script>
  import LayoutHor from '@/components/layout/LayoutHor';

  export default {
    components: {
      LayoutHor
    },
    data() {
      return {
        menus: []
      }
    },
    props: {
      user: {
        type: [Object],
        default: {
          loginName: '',
          nickName: '',
          header: ''
        }
      }
    },
    methods: { 
      open: function (item) { 
        if (name == 'web') {
          window.open("http://www.lyarc.com");
        }
        if (item && item.view) {
          this.$router.push({name: item.view})
        }
      },
      logout: function () {
        window.location.href = "/corp/logout";
      },
      chgpwd: function () {
        this.$router.push({name: 'frm.chgpwd'});
      }, 
      loginInfoChg: function () {
        this.$router.push({name: 'frm.loginInfoChg'});
      }
    },
    mounted: function () {
      this.menus = [];
      if (window.location.href.indexOf('localhost') >= 0) {
        this.menus.push({name: 'demo', title: 'Demo', view: 'demo'});
      }
      this.menus.push({name: 'index', title: '首页', view: 'index'});
      this.menus.push({name: 'contacts', title: '通讯录', view: 'contacts', icon1: 'person'});  

      var userbox = {};
      userbox.showHandle = null;
      userbox.hidenHandle = null;
      userbox.init = function () {
        $('.topbar-userbox,.topbar-down').hover(function () {
          clearTimeout(userbox.showHandle);
          clearTimeout(userbox.hidenHandle);
          if ($('.topbar-down').hasClass('hiden')) {
            userbox.showHandle = setTimeout(function () {
              $('.topbar-down').toggleClass('hiden', 0);
            }, 400);
          }
        }, function () {
          clearTimeout(userbox.hidenHandle);
          clearTimeout(userbox.showHandle);

          if (!$('.topbar-down').hasClass('hiden')) {
            userbox.hidenHandle = setTimeout(function () {
              $('.topbar-down').toggleClass('hiden', 1);
            }, 400);
          }

        });
      }
      userbox.init();

      var menuitem = {};
      menuitem.showHandle = null;
      menuitem.hidenHandle = null;
      menuitem.init = function () {

        $('.menu-item-hover,.topbar-menu-down').hover(function () {
          clearTimeout(menuitem.showHandle);
          clearTimeout(menuitem.hidenHandle);
          if ($('.topbar-menu-down').hasClass('hiden')) {
            menuitem.showHandle = setTimeout(function () {
              $('.topbar-menu-down').toggleClass('hiden', 0);
            }, 400);
          }
        }, function () {
          clearTimeout(menuitem.hidenHandle);
          clearTimeout(menuitem.showHandle);

          if (!$('.topbar-menu-down').hasClass('hiden')) {
            menuitem.hidenHandle = setTimeout(function () {
              $('.topbar-menu-down').toggleClass('hiden', 1);
            }, 400);
          }

        });
      }

      this.$nextTick(function(){
        //渲染完毕 
        menuitem.init();
      }); 
    },
    computed: {
      userHeader: function () {
        return this.user.header || require('@/assets/img/icon_corp.png');
      },
      userName: function () {
        if (this.user.isSuper) {
          return this.user.nickName + ' [超管]';
        } else {
          return this.user.nickName + ' - ' +this.user.loginName;
        }
      }
    },
    created: function () {
      var that = this;
      this.$bus.$on('on-router-after-each', function (para) {
         
      });
    }
  }
</script>

<style>
  .topbar {
    height: 60px;
    width: 100%;
    position: relative;
    /*
    background-color: #495060;
    */
    /*background-color: #222d32;*/
    background-color: white;
    color: #333;
  }
 
  .topbar-center {
    float: right;
    overflow: hidden;
    height: 100%;
    padding-right: 20px;
  }

  .topbar:after {
    content: '';
    display: block;
    width: 100%;
    height: 2px;
    background: #c5261d;
    position: absolute;
    bottom: 0;
    left: 0;
  }

  .topbar .topbar-logo {
    font-size: 14px;
    padding-left: 15px;
    width: 140px;
  }

  .topbar .layout-hor-left {
    vertical-align: middle;
  }

  .topbar .layout-hor-right {
    vertical-align: middle;
  }

  .topbar .ivu-menu-horizontal.ivu-menu-light:after {
    display: none;
  }

  .topbar-userbox {
    font-size: 14px;
    cursor: default;
    height: 100%;
  }

  .topbar-userbox td {
    padding: 4px;
  }

  .topbar-userbox:hover {
    color:white; 
    background-color: #c5261d;
  }

  .topbar-userbox-logo {
    width: 24px;
    height: 24px;
    background-color: white;
    border-radius: 12px;
    padding: 0px;
    margin: 0px;
    margin-top: 4px;
  }

  .topbar-userbox-name {
    max-width: 120px;
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
  }

  .topbar-down {
    border-radius: 0px;
    position: fixed;
    right: 0px;
    top: 59px;
    width: 200px;
    background-color: white;
    border: 1px solid #dedede;
    color: #333;
    font-size: 14px;
    padding: 4px;
    box-shadow: 0 1px 6px rgba(0, 0, 0, .2);
    z-index: 1000;
  }

  .topbar-down.hiden ,.topbar-menu-down.hiden{
    display: none;
  }

  .topbar-down-layout {
    width: 100%;
  }

  .topbar-down-layout td {
    text-align: left;
  }

  .topbar-down-layout .center {
    text-align: center;
  }

  .topbar-down-logo {
    width: 48px;
    height: 48px;
    border-radius: 24px;
    background-color: white;
  }

  .topbar-down-btnmenu.ivu-btn {
    width: 100%;
    font-size: 14px;
    text-align: left;
  }

  .topbar-down-btnmenu.ivu-btn:hover {
    background-color: #eee;
  }

  .topbar-down-splitline {
    border-bottom: 1px dashed #ccc;
    margin: 4px 0px;
  }

  .topbar-menu-down {
    border-radius: 0px;
    position: fixed;
    right: 100px;
    top: 59px;
    width: 200px;
    background-color: white;
    border: 1px solid #dedede;
    color: #333;
    font-size: 14px;
    padding: 4px;
    box-shadow: 0 1px 6px rgba(0, 0, 0, .2);
    z-index: 1000;
  }
  /*nav*/
  .topbar-menu{
    height: 100%;
    font-size: 14px;
    color:#333; 
    text-align: right;
  }
  .topbar-menu-item{
    height: 60px;
    line-height: 60px;
    padding: 0px 20px;  
    cursor: pointer;
    display: inline-block;
  }
  .topbar-menu-item:hover{
    color:white; 
    background-color: #c5261d;
  }
</style>

