<template>
  <div class="page storage-baseset-nav">
    <div class="page-title">
        供应物料检验记录
    </div>
      <div class="button-pad navlist">
          <Button type="primary" @click="goPurchase"  ghost>导入采购报检单</Button>
          <Button type="primary" @click="goWeigh" ghost>导入过磅单</Button>
          <Button type="info" @click="saveTotal" ghost>保存并提交</Button>
          <Button type="dashed" @click="goBack" ghost>取消</Button>
          <Button type="primary" ghost>打印</Button>
          <Button type="dashed" @click="goBack" ghost>退出</Button>
      </div>
      <Purchase ref="purchase"></Purchase>
      <SupplyView ref="supplyView" @supply_get="supply_get"></SupplyView>
      <WeighRecord ref="weigh" @weigh_get="weigh_get"></WeighRecord>
  </div>
</template>
<script>
  import Purchase from './procurement';
  import SupplyView from './supplyView';
  import WeighRecord from './weighRecord';
  export default {
    components: {
      Purchase,SupplyView,WeighRecord
    },
    data() {
      return {
        purchase_number:'',
        totalData:{},
        saveTotalStatus:0,
      }
    },
    mounted: function () {

    },
    computed: {},
    methods: {
      goPage(uri){
        this.$router.push({path:uri});
      },
      supply_get(item){
          this.totalData=item;
        //console.log(item);
      },
      goPurchase(){
        this.$refs.purchase.show();
        this.$refs.purchase.$off('on-ok');
        this.$refs.purchase.$on('on-ok',(items)=>{
          if(items.length > 0){
            this.saveTotalStatus = 2;
            this.purchase_number=items[0];
            this.$refs.supplyView.open(this.purchase_number);
            //this.dataId = items[0].materId;
            /*  alert(items[0].materName);*/
          }
        });
      },
      saveTotal(){
        if(this.saveTotalStatus == 0){
          this.$Message.info("请先导入报检单");
          return;
        }
        if(this.saveTotalStatus == 1){
          this.$Message.info("已经保存过了");
          return;
        }
            this.$http.post("/api/InspectionSheet/addMain",this.totalData).then((res) => {
                this.loading = 0;
                if (res.data.code === 0) {
                  this.saveTotalStatus=1;
                    this.$Message.success("保存成功");
                } else {
                    this.$Message.error(res.data.message)
                }
            }).catch((error) => {
                this.loading = 0;
                this.$Message.error(error.message)
            });
      },
      goWeigh(){
        this.$refs.supplyView.close();
        this.$refs.weigh.open(this.purchase_number);
      },
      weigh_get(data){
        this.$refs.supplyView.open_weigh(data[0]);
      },
      goBack(){
        this.$router.go(-1);
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
