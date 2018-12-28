<template>
  <Modal v-model="casting_display" :closable="false" class="selcustomer" :mask-closable="true" width="1000" :transfer="false">
  <div class="page inquireview">
    <div class="page-bar">
      <LayoutHor>
        <div slot="left">
          <!--<Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>-->
        </div>
        <div class="page-bar-title">零件加工尺寸报告</div>
      </LayoutHor>
    </div>
    <Loading :loading="loading" style="border: solid 1px #797979">
      <div class="subheader" style="text-align: center;font-size: 30px;margin-top: 20px;letter-spacing:45px;color:#797979  ">
        铸件合格证
      </div>
      <Editable v-for="(item) in list" :isList="isList" :queryForm="queryForm" :material="item"></Editable>
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
  import Editable from './sizeDetailCard';
  import page from '@/assets/js/page';
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
        inquireId:'',
        queryForm:{/*this.formItem.operator = this.$user.empInfo.empId;
          this.formItem.operatorName = this.$user.empInfo.trueName;*/
            materName:'',
            senalNumber:'',
            material:'',
            drawingNumber:'',
            partCondition:'',
            remark:'',
            conclusion:'',
            reviewed:'',
            inspector:'',
            status:0,
            operator:'',
            createTime:'',
            wfInstTime:'',
            contractId:'',
        },
        isList:2,
        casting_display:false,
        list:[0],
        unitArgs:[],
        oriItem:{},
        isNew:true,
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
      open(item){
        this.casting_display=true;
          this.queryForm.material=item[0].texture;
          this.queryForm.drawingNumber=item[0].drawing;
          this.queryForm.partCondition=item[0].deliveryType;
          this.queryForm.contractId=item[0].contractId;
          this.queryForm.materName=item[0].materName;
          this.queryForm.inspector=this.$user.empInfo.trueName;
          this.queryForm.createTime=page.formatDate(new Date(),'yyyy-MM-dd');
          this.queryForm.certificateId = item.certificateId;
          //Object.assign(this.queryForm,item[0]);
        this.load();
      },
      listOpen(item,HGZstatus){
          this.queryForm=item;
         /* this.queryForm=item;*/
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
          this.queryForm.partName=this.queryForm.materName;
          let url = '/api/Certificate/addP';
            let msg = '保存成功';
            this.loading = 1;
            this.$http.post(url, this.queryForm).then((res) => {
                this.loading = 0;
                if (res.data.code === 0) {
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
        editList(){
          this.isList=2;
        },
      close(){
         this.casting_display=false;
      },
      reset(){
        this.load();
      },
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
