<template>
  <div class="page storage-baseset-nav">
    <div class="page-title">
        不合格品报告管理
    </div>
      <div class="button-pad navlist">
          <Button type="primary" @click="goProduct"  ghost>生产不合格品报告管理</Button>
          <Button type="info" @click="goPurchase" ghost>采购不合格品报告管理</Button>
      </div>

          <UnProduct ref="unProduct"></UnProduct>
          <UnPurchase ref="unPurchase"></UnPurchase>
  </div>
</template>
<script>
  import UnProduct from './unProduct';
  import UnPurchase from './unPurchase';
  export default {
    components: {
      UnProduct,
      UnPurchase,
    },
    data() {
      return {
        purchase_number:'',
      }
    },
    mounted: function () {
    },
    computed: {},
    methods: {
      goProduct(){
        this.$refs.unProduct.show();
      },
      goPurchase(){
        this.$refs.unPurchase.show();
      },
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
  .button-pad{
      margin: 0 auto;
  }
    .button-pad button{
        margin: 20px 10px;
    }
</style>
