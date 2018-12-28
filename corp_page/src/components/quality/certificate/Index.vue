<template>
  <div class="page storage-baseset-nav">
    <div class="page-title">
      仓库管理
    </div>
    <Card class="navlist">
      <a @click="goPage('/certificate/management')">合格证管理</a>
      <a @click="goPage('/certificate/examination')">合格证审核</a>
      <a @click="goPage('/certificate/authorize')">合格证批准</a>
      <a @click="goPage('/certificate/print')">合格证打印</a>
      <!--
      合格证管理


合格证审核


合格证批准
合格证打印
-->
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
