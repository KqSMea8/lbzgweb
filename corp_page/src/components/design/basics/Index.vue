<template>
  <div class="page storage-baseset-nav">
    <div class="page-title">
      基础设置
    </div>
    <Card class="navlist">
        <a @click="goPage('/basics/ProjectTypeManagement/Index')">项目类型管理</a>
        <a @click="goPage('/basics/make/Index')">技术参数信息维护</a>
        <a @click="goPage('/basics/inspection/Index')">创建物料检验项目</a>
        <a @click="goPage('/contract-design/classify/Index')">编辑物料检验项目</a>
        <a @click="goPage('/contract-design/make/Index')">工序管理</a>
    </Card>
  </div>
</template>
<script> 　
  export default {
    components: {
     　
    },
    data() {
      return {

      }
    },
    mounted: function () {

    },
    computed: {},
    methods: {
      goPage(uri){
        this.$router.push({path:uri});
      }
    }
  }

</script>

<style type="text/css">
  .storage-baseset-nav .navlist{
    margin-top: 20px;
  }
  .storage-baseset-nav .navlist a{
    display: inline-block;
    font-size: 14px;
    padding: 4px;
  }
</style>
