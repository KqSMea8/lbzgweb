<template>
  <div class="page storage-baseset-nav">
    <div class="page-title">
      新建合格证
    </div>
    <div class="page-tools">
      <table cellpadding="10" cellspacing="10">
        <tr>
          <td><Button @click="onSelectCustomer"  icon="plus">导入合同</Button></td>
          <td><Button @click="goBack" icon="edit">退出</Button></td>
        </tr>
      </table>
    </div>


    <Card class="navlist">
      <a @click="casting_open">铸件合格证</a>
      <a @click="ultrasonic_open">超声波探伤检验报告</a>
      <a @click="magnetic_open">磁粉探伤检验报告</a>
      <a @click="size_open">零件加工尺寸报告</a>
    </Card>
    <Card class="navlist">
      <Record ref="record"></Record>
      <Casting ref="casting" @certificateId_get="certificateId_get"></Casting>
      <Ultrasonic ref="ultrasonic"></Ultrasonic>
      <Magnetic ref="magnetic"></Magnetic>
      <Injury ref="injury"></Injury>
      <Size ref="size"></Size>
    </Card>
  </div>

</template>
<script>
  import Record from './record';
  import Casting from './../qualification/casting';
  import Ultrasonic from './../qualification/ultrasonic';
  import Magnetic from './../qualification/magnetic';
  import Injury from './../qualification/injury';
  import Size from './../qualification/size';
  import '@/assets/css/page.css';
  import Loading from '@/components/loading';
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  export default {
    components: {
      Record,
      Casting,
      Loading,
      Ultrasonic,
      Magnetic,
      Size,
      Injury,
      DataRowOperateBar
    },
    data() {
      return {
        dataId:[],
        certificateId:null,
      }
    },
    mounted: function () {
    },
    computed: {},
    methods: {
      casting_open(){
        if(this.dataId==""){
          this.$Message.error("请先导入合同");
        }else{
          this.$refs.casting.open(this.dataId);
        }

      },
      ultrasonic_open(){
        if(this.dataId==""){
          this.$Message.error("请先导入合同");
        }else{
            if(this.certificateId==null){
                this.$Message.error("请先保存铸件");
                return;
            }
          this.$refs.ultrasonic.open(this.dataId);
        }

      },
      magnetic_open(){
        if(this.dataId==""){
          this.$Message.error("请先导入合同");
        }else{
            if(this.certificateId==null){
                this.$Message.error("请先保存铸件");
                return;
            }
          this.$refs.injury.open(this.dataId);
        }

      },
      size_open(){
          if(this.dataId==""){
              this.$Message.error("请先导入合同");
          }else{
              if(this.certificateId==null){
                  this.$Message.error("请先保存铸件");
                  return;
              }
              if(this.dataId.length>1){
                  this.$Message.error("不允许导入多个");
              }else{
                  this.$refs.size.open(this.dataId);
              }

          }

       /*
*/

      },
        /*
        * 保存子集casting传过来的合格证id
        * */
        certificateId_get(certificateId){
            this.certificateId=certificateId;
            this.dataId.certificateId=certificateId;
        },
      onSelectCustomer(){

        this.$refs.record.show();
        this.$refs.record.$off('on-ok');
        this.$refs.record.$on('on-ok',(items)=>{
          if(items.length > 0){
            //this.dataId = items[0].materId;
            this.dataId = items;

            this.casting_open();
          }
        });
      },
      goPage(uri){
        this.$router.push({path:uri});
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
</style>
