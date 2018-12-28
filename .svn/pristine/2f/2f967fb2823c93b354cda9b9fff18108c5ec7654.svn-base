<template>
  <Col class="left-menu">
    <template >
      <router-link to="/workflow/mywork" :class="{'router-link-exact-active': RouteName == 'workflow.mywork'}">我的事项</router-link>
      <router-link to="/workflow" :class="{'router-link-exact-active': RouteName == 'hrjobcorp.zwxx'}">我的应用</router-link>
      <router-link to="/workflow" :class="{'router-link-exact-active': RouteName == 'hrjobcorp.zwxx'}">应用管理</router-link>
      <router-link to="/workflow" :class="{'router-link-exact-active': RouteName == 'hrjobcorp.zwxx'}">系统管理</router-link>
  </template>
  </Col>
</template>
<script>
  export default {
    data() {
      return {
        RouteName: this.$route.name,
        power: 0,
        isSuper: 0
      }
    },
    watch: {
      power(Val){
        this.setPower(Val)
      }
    },
    mounted () {
      let User = Vue.prototype.$user.userId
      ArcBase.getRole(this, User, power => {
        this.power = power
        this.setPower(power)
        Vue.prototype.$user.power = power
      })
    },
    methods: {
      setPower(power) {
        this.isSuper = this.$user.hasPower('gzl');
        if(power == 3 && !this.isSuper){
          this.$router.push('')
        }
      }
    }
  }
</script>
<style type="text/css" scoped>
  .left-menu{ width: 185px; height: 100%; position: fixed; z-index: 2; background: #fff;}
  .left-menu a{color: #333; display: block; padding: 10px 25px;}
  .left-menu a.router-link-exact-active{color: #fff; background-color: #20bfee;}
  .left-menu .ivu-menu-light.ivu-menu-vertical .ivu-menu-item{ border-right-width: 0; padding: 10px 25px; border-bottom: 1px #f0f4f8 solid;}
  .left-menu .ivu-menu-light.ivu-menu-vertical .ivu-menu-item-active:not(.ivu-menu-submenu){background-color: #20bfee; color: #fff;}
  .left-menu:after{content: '';  background: #f0f4f8;  position: absolute;  width: 1px;  height: 100%;  right: 0;  top: 0;}
</style>
