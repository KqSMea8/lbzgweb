<template>
  <Modal v-model="casting_display" :closable="false" class="selcustomer" :mask-closable="true" width="1000" :transfer="false">
  <div class="page inquireview">
    <div class="page-bar">
      <LayoutHor>
        <div slot="left">
          <!--<Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>-->
        </div>
        <div class="page-bar-title">洛阳洛北重工机械有限公司</div>
        <div class="page-bar-title">Luoyang LuoBei Heavy Industry Machinery Co., Ltd</div>
        <div class="page-bar-title">超声波探伤检验报告</div>
        <div class="page-bar-title"> ULTRASONIC TEST REPORT</div>
      </LayoutHor>
    </div>
    <Loading :loading="loading" style="border: solid 1px #797979">
      <div class="subheader" style="text-align: center;font-size: 22px;margin-top: 20px;color:#797979  ">
        洛阳洛北重工机械有限公司
      </div>
      <div class="subheader" style="text-align: center;font-size: 22px;margin-top: 20px;color:#797979  ">
        Luoyang LuoBei Heavy Industry Machinery Co., Ltd
      </div>
      <div class="subheader" style="text-align: center;font-size: 22px;margin-top: 20px;color:#797979  ">
        超声波探伤检验报告
      </div>
      <div class="subheader" style="text-align: center;font-size: 22px;margin-top: 20px;color:#797979  ">
        ULTRASONIC TEST REPORT
      </div>
      <Editable v-for="(item) in formItem.detailList" :isList="isList" :queryForm="queryForm" :material="item"></Editable>
      <div style="clear: both"></div>
    </Loading>
    <table class="savebar" cellpadding="0" cellspacing="0">
      <tr>
        <!--<Button type="primary" icon="checkmark" v-if="!isList" @click="save">保存</Button>
        <Button type="ghost" @click="Submittals" v-if="!isList" style="margin-left: 8px" >送审</Button>
        <Button type="ghost" @click="reset" v-if="!isList" style="margin-left: 8px" >重置</Button>
        <Button type="ghost" @click="close" v-if="!isList" style="margin-left: 8px">取消</Button>

        <Button type="primary" @click="editList" v-if="isList" style="margin-left: 8px">编辑</Button>
        <Button type="ghost" @click="close" v-if="isList" style="margin-left: 8px">退出</Button>-->
        <Button type="primary" icon="checkmark" @click="save" v-show="isList==2">保存</Button>
        <Button type="ghost" @click="Submittals" style="margin-left: 8px"  v-show="isList==2">送审</Button>
        <Button type="ghost" @click="reset" style="margin-left: 8px"  v-show="isList==2">重置</Button>
        <Button type="ghost" @click="close" style="margin-left: 8px" v-show="isList==2">取消</Button>

        <Button type="primary" icon="checkmark" @click="save" v-show="isList==3">保存</Button>
        <Button type="primary" icon="checkmark" @click="thisPrint" v-show="isList==4">打印</Button>
        <Button type="primary" @click="editList" v-if="isList==1" style="margin-left: 8px">编辑</Button>
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
  import Editable from './ultrasonicDetailCard';
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
        inquireId:'',
        formItem:{
          inquireId:'',
          customerId:'',
          customerName:'',
          inDate:'',
          isInquire:0,
          hasProcedure:0,
          isForeign:0,
          isCraftDesign:0,
          detailList:[],
          sales:'',
          salesName:'',
          remark:'',
          designer:'',
          completeTime:'',
          files:'',
        },
        isList:2,
        queryForm:{
            contractId:'',
            description:'',
            reportNo:'',
            orderNo:'',
            partNo:'',
            status:'',
            reportDate:'',
            drawingNo:'',
            commissionUnit:'',
            quantity:'',
            material:'',
            sketchPart:'',
            inquireId:'',
            typeOf:'',
            sensitivity:'',
            requiredStandard:'',
            typeOfThe:'',
            typeOfCoupling:'',
            statu:'',
            typeOfMachine:'',
            size:'',
            frequency:'',
            ultrasonicTestResult:'',
            adjustingField:'',
            angle:'',
            testPeople:'',
            coupling:'',
            wfInstRemark:'',
            operatorName:'',
            checkedBy:'',
        },
        item:"",
        casting_display:false,
        list:[],
        isNew:true,
      }
    },
    mounted: function () {

    },
    computed: {},
    methods: {
      open(item){
        /*
        * contractId 合同号
        *description 零件名称
        *drawingNo 零件图号
        *commissionUnit 委托单位
        *quantity  数量
        * material 材     质
        * */
        var item2=[];
          item2['contractId']="";
          item2['description']="";
          item2['drawingNo']="";
          item2['commissionUnit']="";
          item2['quantity']="";
          item2['material']="";
        for(var i in item){
            if(item[i]['contractId']){
                item2['contractId']=item[i]['contractId'];
            }
            if(item[i]['materName']){
                item2['description']+=item[i]['materName']+",";
            }
            if(item[i]['drawing']){
                item2['drawingNo']+=item[i]['drawing']+",";
            }
            if(item[i]['drawing']){
                item2['commissionUnit']+=item[i]['drawing']+",";
            }
            if(item[i]['quantity']){
                item2['quantity']+=item[i]['quantity']+",";
            }
            if(item[i]['texture']){
                item2['material']+=item[i]['texture']+",";
            }

        }
          item2['description']=item2['description'].substr(0, item2['description'].length - 1);
          item2['drawingNo']=item2['drawingNo'].substr(0, item2['drawingNo'].length - 1);
          item2['commissionUnit']=item2['commissionUnit'].substr(0, item2['commissionUnit'].length - 1);
          item2['quantity']=item2['quantity'].substr(0, item2['quantity'].length - 1);
          item2['material']=item2['material'].substr(0, item2['material'].length - 1);
          Object.assign(this.queryForm,item2);
          this.formItem.detailList[0]=item2;
          //this.queryForm.proposer = this.$user.empInfo.empId;
          this.queryForm.testPeople = this.$user.empInfo.trueName;
          this.queryForm.nowDate = page.formatDate(new Date(),'yyyy-MM-dd');
          this.queryForm.reportDate = page.formatDate(new Date(),'yyyy-MM-dd');
          this.queryForm.certificateId = item.certificateId;
          this.queryForm.status = 0;
          this.casting_display=true;
      },
        listOpen(item,HGZstatus){
            this.queryForm=item;
            this.formItem.detailList[0]=item;
            this.casting_display=true;
            this.isList=1;
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
      save(){
          let url = '/api/Certificate/addU';
          let msg = '保存成功';
          this.loading = 1;
          this.$http.post(url, this.queryForm).then((res) => {
              this.loading = 0;
              if (res.data.code == 0) {
                  this.$Message.success(msg);

                  this.$emit("on-save");
                  this.close();
              } else {
                  this.$Message.error(res.data.message)
              }
          }).catch((error) => {
              this.loading = 0;
              this.$Message.error(error.message)
          });
      },
        close(){
            this.casting_display=false;
        },
      editList(){
          this.isList=2;
      },
      ok(){
        this.goBack();
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
</style>
