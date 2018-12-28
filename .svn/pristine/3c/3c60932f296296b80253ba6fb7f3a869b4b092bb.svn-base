<template>
  <div class="page storage-baseset-nav"> 
    <div class="page-title">
      仓库基础信息设置
    </div> 
    <Card class="navlist"> 
        <a @click="goPage('/storage/storage')">仓库信息</a>
        <a @click="goPage('/storage/storage/place')">货位信息</a>
        <a @click="goPage('/storage/stock/opertype')">库存操作类别</a>
        <a @click="goPage('/storage/storage/auth')">仓库操作权限</a>
        <a @click="goPage('/storage/storage/init')">仓库初始化</a>
        <a @click="goPage('/storage/storage/reinit')">仓库反初始化</a>
        <a @click="goPage('/storage/storage/safe')">安全库存</a> 
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
