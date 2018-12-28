<template>
    <Modal v-model="display" title="创建检验文件" :closable="false"  class="" width="800" class-name="ivu-modal-mask my_model">
  <div class="page storage-baseset-nav">
<!--    <div class="page-title">
        创建检验文件
    </div>-->
    <!--<Card class="navlist">
        <a @click="goPage('/inspectionRecord/supply')">新建采购物料检验单</a>
        <a @click="goPage('/storage/storage/place')">不合格品报告管理</a>
        <a @click="goPage('/storage/stock/opertype')">检验记录修订</a>
    </Card>-->
      <table cellspacing="0" cellpadding="0" width="100%" style="text-align: center">
          <thead>
          <tr>
              <td>序号</td>
              <td>检验文件</td>
              <td>操作</td>
          </tr>
          </thead>
          <tr>
              <td>1</td>
              <td> 原辅材料目视检验</td>
              <td><i-button @click="goPage('/quality-record/VisualInspectionEdit?forward&ledgerId=1001')">新建</i-button></td>
          </tr>
          <tr>
              <td>2</td>
              <td> 铁合金检验</td>
              <td><i-button @click="goPage('/quality-record/FerroAlloyEdit?forward&ledgerId=1002')">新建</i-button></td>
          </tr>
          <tr>
              <td>3</td>
              <td> 砖管检验</td>
              <td><i-button @click="goPage('/quality-record/BrickTubeEdit?forward&ledgerId=1004')">新建</i-button></td>
          </tr>
          <tr>
              <td>4</td>
              <td> V法涂料检验</td>
              <td><i-button @click="goPage('/quality-record/VMethodCoatingEdit?forward&ledgerId=1006')">新建</i-button></td>
          </tr>
          <tr>
              <td>5</td>
              <td> 树脂、铬矿砂检验</td>
              <td><i-button @click="goPage('/quality-record/ResinChromeOreEdit?forward&ledgerId=1008')">新建</i-button></td>
          </tr>
          <tr>
              <td>6</td>
              <td>石灰检验</td>
              <td><i-button @click="goPage('/quality-record/CheckLimeEdit?forward&ledgerId=1009')">新建</i-button></td>
          </tr>
          <tr>
              <td>7</td>
              <td>水玻璃检验</td>
              <td><i-button @click="goPage('/quality-record/WaterGlassEdit?forward&ledgerId=1010')">新建</i-button></td>
          </tr>
          <tr>
              <td>8</td>
              <td> 原砂检验</td>
              <td><i-button @click="goPage('/quality-record/OriginalSandEdit?forward&ledgerId=1011')">新建</i-button></td>
          </tr>
          <tr>
              <td>9</td>
              <td> 电极检验</td>
              <td><i-button @click="goPage('/quality-record/ElectrodeEdit?forward&ledgerId=1012')">新建</i-button></td>
          </tr>
        </table>
  </div>
        <div slot="footer"></div>
    </Modal>
</template>
<script> 　
  export default {
    components: {
     　
    },
    data() {
      return {
        display:false,
          versionId:"0",
          parameterData:[],

      }
    },
    mounted: function () {

    },
    hidden(){
      this.display=false;
    },
    computed: {

    },
    methods: {
      open(item){
          this.parameterData=item;
        this.display=true;
      },
      goPage(uri){
         // window.location.reload();
          this.display=false;
         this.$emit("close");
        this.$router.replace({path:uri+"&versionId="+this.versionId});
/*alert(this.ledgerId)*/
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
    .my_model{
        z-index: 1003;
    }
</style>
