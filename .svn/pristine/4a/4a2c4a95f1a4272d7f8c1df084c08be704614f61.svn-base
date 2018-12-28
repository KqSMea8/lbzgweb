<template>
  <div class="page storage-baseset-nav">
    <div class="page-title">
      检验记录管理
    </div>
    <Card class="navlist">
        <a @click="goPage('/inspectionRecord/supply')">新建采购物料检验单</a>
        <a @click="goPage('/inspectionRecord/unqualified')">不合格品报告管理</a>
        <a @click="goPage('/quality/quality-record/RevisionRecord')">检验记录修订</a>
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
