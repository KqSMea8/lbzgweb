<template>
  <div class="page productstock-baseset-nav"> 
    <div class="page-title">
      产成品库
    </div> 
    <Card class="navlist"> 
        <a @click="goPage('/productstock/stockin')">产成品入库</a>
        <a @click="goPage('/productstock/stockout')">产成品出库</a>
        <a @click="goPage('/productstock/checkstock')">盘点</a>
    </Card>
    <Card class="navlist"> 
        <a @click="goPage('/productstock/query/stock')">库存查询</a>
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
  .productstock-baseset-nav .navlist{
    margin-top: 20px;
  }
  .productstock-baseset-nav .navlist a{
    display: inline-block;
    font-size: 14px; 
    padding: 4px;
  }
</style>
