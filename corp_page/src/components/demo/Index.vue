<template>
<div class="page">
  <h2 class="page-title">Demo 页面</h2>
  <div class="link" @click="go('demo.tree')">1. 树组件</div>
  <div class="link" @click="go('demo.depttree')">2. 部门树（扩展菜单）</div>
  <div class="link" @click="go('demo.small')">4. 小组件</div>
  <div class="link" @click="go('demo.layout')">5. 布局</div>
  <div class="link" @click="go('demo.selcontacts')">6. 选择部门和人员</div>
  <div class="link" @click="go('demo.selother')">6. 选择 【物料、供应商、客户、货位】</div>
</div>
</template>
<script>
export default {
  data() { return {} },
  methods: {
     go:function (view) {
        this.$router.push({name:view});
     }
  }
}
</script>
<style>
  .link{cursor: pointer;color: #2d8cf0;font-weight: normal;font-size: 14px;}
</style> 