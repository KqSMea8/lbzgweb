<template>
  <div class="page storage-baseset-nav"> 
    <div class="page-title">
      仓库查询
    </div> 
    <Card class="navlist"> 
        <a @click="goPage('/storage/query/stock')">库存查询</a>
        <a @click="goPage('')">日出发存查询</a>
        <a @click="goPage('')">月收发存查询</a>
        <a @click="goPage('')">盘点记录</a>
        <a @click="goPage('')">呆滞物料查询</a>
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
