<template>
  <div class="page storage-baseset-nav"> 
    <div class="page-title">
      合同工艺制作
    </div> 
    <Card class="navlist"> 
        <a @click="goPage('/contract-design/classify/Index')">合同项目分类</a>
        <a @click="goPage('/contract-design/make/Index')">合同签订后工艺制作</a>
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
