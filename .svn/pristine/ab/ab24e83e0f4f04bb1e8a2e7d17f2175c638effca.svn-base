<template>
  <div class="page storage-baseset-nav"> 
    <div class="page-title">
      仓库管理
    </div> 
    <Card class="navlist"> 
        <a @click="goPage('/storage/instock')">入库</a>
        <a @click="goPage('/storage/outstock')">出库</a>
        <a @click="goPage('/storage/movestock')">移库</a>
        <a @click="goPage('/storage/checkstock')">盘点</a>
        <a @click="goPage('/storage/audit')">单据审核</a>
        <a @click="goPage('/storage/temphumi')">温度记录</a>
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
