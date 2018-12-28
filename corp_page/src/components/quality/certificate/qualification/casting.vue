<template>
  <Modal v-model="casting_display" :closable="false" class="selcustomer" :mask-closable="true" width="1000" :transfer="false">
  <div class="page inquireview">
    <div class="page-bar">
      <LayoutHor>
        <div slot="left">
          <!--<Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>-->
        </div>
        <div class="page-bar-title">铸件合格证</div>
      </LayoutHor>
    </div>
    <Loading :loading="loading" style="border: solid 1px #797979">
      <div class="subheader" style="text-align: center;font-size: 30px;margin-top: 20px;letter-spacing:45px;color:#797979  ">
        铸件合格证
      </div>
      <Editable v-for="(item) in formItem.detailList" :queryForm="queryForm" :material="item"></Editable>
      <div style="clear: both;"></div>
    </Loading>
    <table class="savebar" cellpadding="0" cellspacing="0">
      <tr>
        <Button type="primary" icon="checkmark" @click="save" v-show="isList==2">保存</Button>
        <Button type="ghost" @click="Submittals" style="margin-left: 8px"  v-show="isList==2">送审</Button>
        <Button type="ghost" @click="reset" style="margin-left: 8px"  v-show="isList==2">重置</Button>
        <Button type="ghost" @click="close" style="margin-left: 8px" v-show="isList==2">取消</Button>

        <Button type="primary" icon="checkmark" @click="save" v-show="isList==3">保存</Button>
        <Button type="primary" icon="checkmark" @click="thisPrint" v-show="isList==4">打印</Button>

        <Button type="ghost" @click="close" style="margin-left: 8px" v-show="isList==1">退出</Button>

      </tr>
    </table>
  </div>
    <div slot="footer"></div>
  </Modal>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './CastingDetailCard';
  import dateFormat from '@/assets/js/dateFormat';
  import UploadBox from '@/components/upload/Index';
  import page from '@/assets/js/page';
  export default {
    components: {
      Loading,
      LayoutHor,
      Editable,
      UploadBox,
      page
    },
    data() {
      return {
        loading: 0,
        formItem:{
          inquireId:'',
          customerId:'',
          customerName:'',
          inDate:'',
          isInquire:0,
          hasProcedure:0,
          detailList:[],
          isForeign:0,
          isCraftDesign:0,
          sales:'',
          salesName:'',
          remark:'',
          designer:'',
          completeTime:'',
          files:'',
        },
        queryForm:{},
        item:"",
        casting_display:false,
        list:[],
        contractId:'',
        unitArgs:[],
        oriItem:{},
        isNew:true,
        isList:2,
      }
    },
    mounted: function () {
      //alert(this.$route.query.id);
      //this.inquireId = this.$route.query.id;
      //this.inquireId = 201810090001;
     /* this.load();*/
    },
    computed: {},
    methods: {

      open(data){
          //this.queryForm.testPeople = this.$user.empInfo.empId;
          this.queryForm.testPeople = this.$user.empInfo.trueName;
          this.queryForm.nowDate = page.formatDate(new Date(),'yyyy-MM-dd');
          this.queryForm.status = 0;
          this.casting_display=true;
          this.formItem.detailList[0]=data;
        //this.load();

      },
      listOpen(data,HGZstatus){
          this.queryForm=data;
          this.formItem.detailList[0]=data.testItemObj;
          this.isList=1;
          this.casting_display=true;
          switch (HGZstatus) {
              case 1:
                  this.queryForm.status = 1;
                  this.queryForm.checkedBy = this.$user.empInfo.trueName;
                  this.isList=3;
                  break;
              case 2:
                  this.queryForm.status = 2;
                  this.queryForm.approvedBy = this.$user.empInfo.trueName;
                  this.isList=3;
                  break;
              case 4:
                  this.isList=4;
                  break;
          }
      },
        close(){
            this.casting_display=false;
        },
      ok(){
        this.goBack();
      },
      save(){

          this.queryForm.testItem=JSON.stringify(this.formItem.detailList[0]);
          this.queryForm.contractId=this.formItem.detailList[0][0].contractId;
            this.$http.post("api/Certificate/addC", this.queryForm).then((res) => {
                this.loading = 0;
                if (res.data.code == 0) {
                    this.$Message.success("操作成功！");
                    this.$emit("certificateId_get",res.data.data.certificateId);
                    this.goBack();
                } else {
                    this.$Message.error(res.data.message);
                }
            }).catch((error) => {
                this.loading = 0;
                this.$Message.error("请求失败，请重新操作")
            });
      },
      reset(){
        this.load();
      },
      goBack(){
        this.casting_display=false;
        //this.$router.go(-1);
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
  .selcustomer .page{
    padding: 0px;
  }
  .selcustomer .page-searchbox{
    margin-top: 0px;
  }
  .selcustomer .status-2{
    color:#ff6600;
  }
  .selcustomer .footer{
    text-align: center;
    padding-left: 10px;
  }
  .selcustomer .row-checked td{
    background-color: #e8f8fd;
    /*color:#20bfee;*/
    font-weight: bold;
  }

  /*load() {
  this.loading = 1;
  this.$http.post("/api/inquire/get?contractId=" + this.contractId,{}).then((res) => {
    this.loading = 0;
    if (res.data.code == 0) {
      if(res.data.data){
        Object.assign(this.formItem,res.data.data);
        formItem.detailList=formItem.detailList[0];console.log(formItem.detailList);
      }else{
        this.$Message.error('询价单不存在！');
        //this.goBack();
      }
    } else {
      this.$Message.error(res.data.message);
    }
  }).catch((error) => {
    this.loading = 0;
    this.$Message.error("操作失败！")
  });
},*/
</style>
