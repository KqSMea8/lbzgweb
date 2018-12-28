<template>
  <div class="page storage-baseset-nav"> 
    <div class="page-title">
      仓库基础信息设置
    </div> 
    <Card class="navlist"> 
        <a @click="goPage('/storage/storage')">探伤结果查询</a>
        <a @click="goPage('/storage/storage/place')">合格率报表</a>
        <a @click="goPage('/storage/stock/opertype')">金相分析记录</a>
        <a @click="goPage('/storage/storage/auth')">产品客户投诉台帐</a>
        <a @click="goPage('/storage/storage/init')">售后报务台账</a>
        <a @click="goPage('/storage/storage/reinit')">质量损失明细台账</a>
        <a @click="goPage('/quality/quality-revision/Revision')">检验结果修订记录</a>
        <a @click="goPage('/storage/storage/safe')">采购物料检验查询</a>
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
