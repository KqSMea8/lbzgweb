<template>
  <div class="page inquireview">
    <div class="page-bar">
      <LayoutHor>
        <div slot="left">
          <table class="savebar" cellpadding="0" cellspacing="0">
            <tr>
              <td><Button size="small" @click="goBack" icon="chevron-left"  type="warning">退出</Button></td>
              <!--<td><Button size="small" @click="ok"  type="warning">返回(确定)</Button></td>-->
            </tr>
          </table>
        </div>
        <div class="page-bar-title">询价单 - 查看</div>
      </LayoutHor>
    </div>
    <Loading :loading="loading">
    <div class="baseinfo">
      <div class="subheader">
        单据表头
      </div>
      <Row>
        <Col span="14">
          <div class="">
            <table cellspacing="0" cellpadding="0">
              <tr>
                <td class="label">业务员(操作人员)</td>
                <td>{{formItem.salesName}}</td>
                <td class="label">录入日期（操作日期）</td>
                <td>
                  {{formItem.inDate}}
                </td>
              </tr>
             </table>
          </div>
        </Col>
      </Row>
    </div>
    <div class="subheader">
      单据明细
    </div>
    <Editable v-for="(item,index) in detailList"
        :title="'v'+item.versionId" :ledgerId="ledgerId" :material="item">
    </Editable>
    </Loading>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './QualityRevisionCard';
  import dateFormat from '@/assets/js/dateFormat';
  import UploadBox from '@/components/upload/Index';

  export default {
    components: {
      Loading,
      LayoutHor,
      Editable,
      UploadBox
    },
    data() {
      return {
        loading: 0,
        batchId:'',
        ledgerId:'',
        formItem:{
          batchId:'',
          customerId:'',
          customerName:'',
          inDate:'',
          isInquire:0,
          hasProcedure:0,
          isForeign:0,
          isCraftDesign:0,
          sales:'',
          salesName:'',
          remark:'',
          designer:'',
          completeTime:'',
          files:'',
          ledgerId:'',
        },
        list:[],
        unitArgs:[],
        oriItem:{},
        detailList:[],
        isNew:true,
      }
    },
    mounted: function () {
      this.batchId = this.$route.query.id;
        this.ledgerId=this.$route.query.ledgerId;
        /*console.log("this.ledgerId："+this.ledgerId);*/
      this.load();
    },
    computed: {},
    methods: {
      load() {
        this.loading = 1;
        if (this.ledgerId=='1001'){
            var url="/api/revision/getAllCheckVisualInspection?batchId=" + this.batchId;
        }else if (this.ledgerId=='1002'){
              var url="/api/revision/getAllCheckFerroAlloy?batchId=" + this.batchId;
          }else if (this.ledgerId=='1004'){
              var url="/api/revision/getAllCheckBrickTube?batchId=" + this.batchId;
          }else if (this.ledgerId=='1006'){
              var url="/api/revision/getAllCheckVMethodCoating?batchId=" + this.batchId;
          }else if (this.ledgerId=='1008'){
              var url="/api/revision/getAllCheckResinChromeOre?batchId=" + this.batchId;
          }else if (this.ledgerId=='1009'){
              var url="/api/revision/getAllCheckLime?batchId=" + this.batchId;
          }else if (this.ledgerId=='1010'){
              var url="/api/revision/getAllCheckWaterGlass?batchId=" + this.batchId;
          }else if (this.ledgerId=='1011'){
              var url="/api/revision/getAllCheckOriginalSand?batchId=" + this.batchId;
          }else if (this.ledgerId=='1012'){
              var url="/api/revision/getAllCheckElectrode?batchId=" + this.batchId;
          }


        this.$http.post(url,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {

            if(res.data.data){
              Object.assign(this.detailList,res.data.data);
              console.log(this.detailList);
            }else{
              this.$Message.error('询价单不存在！');
              this.goBack();
            }
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("操作失败！")
        });
      },
      ok(){
        this.goBack();
      },
      reset(){
        this.load();
      },
      goBack(){
        this.$router.go(-1);
      }
    }
  }

</script>

<style type="text/css">
  .inquireview.page{
    width: 900px;margin: 0 auto;
    padding: 10px 0px;
    position: relative;
  }
  .inquireview .subheader{
    height: 34px;line-height: 34px;
    font-size:14px;
    border-bottom: 0px solid #efefef;
    margin-bottom: 10px;
    color:#20bfee;
  }
  .inquireview .baseinfo{
     font-size: 14px;
  }
  .inquireview .baseinfo .label{
    width: 80px;text-align: right;color:#999;
  }
  .inquireview .baseinfo table{
    width: 100%;
  }
  .inquireview .baseinfo table td{
    height: 40px;padding-right:8px;
  }

  .inquireview .savebar{
    margin-top: 10px;
    height: 40px;
    width: 100%;
    border-collapse: collapse;
  }
  .inquireview .savebar td{
    border: 1px solid #fefefe;
    font-size: 14px;
  }
  .inquireview .savebar .save{
    width: 120px;
    border: 1px solid #20bfee;
    background-color: #20bfee;
    color: white;
    text-align: center;
    cursor: pointer;
  }
  .inquireview .savebar .reset{
    width: 60px;
    border: 1px solid #a1e7f8;
    background-color: #a1e7f8;
    color: white;
    text-align: center;
    cursor: pointer;
  }

  .selectinput{
    cursor: pointer;
  }

  .inquireview .tooltip{
    padding:10px;
    background-color: #fafafa;
    border:1px solid #efefef;
    border-radius: 3px;
    color:#666;
    margin-top: 10px;
  }

  .inquireview .billfooter{
    margin-top: 10px;
    font-size: 14px;
    float: right;
  }
  .inquireview .billfooter td{
    line-height: 40px;
    padding-right: 10px;
  }
  .inquireview .billfooter .label{
     width: 80px;
  }
</style>
