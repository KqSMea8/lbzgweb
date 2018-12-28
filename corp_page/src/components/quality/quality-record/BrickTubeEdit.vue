<template>
  <div class="page purchase-order-edit">
    <div class="page-bar">
      <LayoutHor>
        <div slot="left">
          <Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>
        </div>
      </LayoutHor>
    </div>
      <div class="baseinfo">
      </div>
      <div>
        <div class="subheader">
          单据明细
        </div>
       <div class="inquire-detail-card editable">
          <div>3.砖管检验1004</div>
         <div  class="editable-table">
           <table cellspacing="0" cellpadding="0">
             <thead>
             <th>供应商</th>
             <th>物料名称</th>
             <th width="60">数量</th>
             <th width="60">批次号</th>
             <th >检验日期</th>
             </thead>
             <tbody>
             <tr>
                 <td>
                     {{list.detailList[0].providerName}}
                 </td>
                 <td>
                     {{list.detailList[0].materName}}
                 </td>
                 <td>
                     <Input type="text" v-model="list.detailList[0].quantity" />
                 </td>
                 <td class="col-cz">
                     {{this.batchId}}
                 </td>
                 <td>
                     {{ list.detailList[0].createTime }}
                 </td>
             </tr>
             </tbody>
           </table>
           <br/>
           <table cellspacing="0" cellpadding="0">
            <thead>
             <th>生产厂家</th>
             <th>名称</th>
             <td colspan="2"><Input  type="text" v-model="detailList[0].brickTubeName" /></td>
             <th>型号</th>
             <td colspan="2"><Input  type="text" v-model="detailList[0].brickTubeType" /></td>
             </thead>
             <tbody>
            <tr>
               <td rowspan="3"><Input  type="text" v-model="detailList[0].origin"/></td>
               <td colspan="6"><Input  type="number" v-model="detailList[0].stockQuantity" placeholder="进货数量"/></td>
            </tr>
             <tr>
               <td colspan="6"><Input  type="number" v-model="detailList[0].spotCheckQuantity" placeholder="抽检数量"/></td>
             </tr>
             <th><tr>裂纹 </tr></th>
             <th><tr>毛刺</tr></th>
             <th><tr>损伤</tr></th>
             <th><tr>尺寸不合格</tr></th>
             <th><tr>接口不合格</tr></th>
             <th><tr>端面平面度</tr></th>
             <tr>
               <th>数量</th>
               <td v-for="(item,index) in detailList"><Input  type="number" v-model="item.checkQuantity" /></td>
             </tr>
             <tr>
               <th>抽检合格率</th>
               <td v-for="(item,index) in detailList"><Input  type="number" v-model="item.spotCheckYieid" /></td>
             </tr>
             <tr>
               <th>合格数量</th>
               <td colspan="6" ><Input  type="number" v-model="detailList[0].qualifiedNo" /></td>
             </tr>
             <tr>
               <th>合格率</th>
               <td colspan="6"><Input  type="number" v-model="detailList[0].yieid" /></td>
             </tr>
             <tr>
               <th>结论</th>
               <td colspan="6"><Input  type="text" v-model="detailList[0].conclusion" /></td>
             </tr>
             </tbody>
           </table>
           <br/>
          <!-- <table cellspacing="0" cellpadding="0">
            <thead>
             <th>名称</th>
             <td colspan="2"><Input  type="text" v-model="list.detailList[0].brickTubeName" /></td>
             <th>型号</th>
             <td colspan="2"><Input  type="text" v-model="list.detailList[0].brickTubeType" /></td>
             </thead>
             <tbody>
             <tr>
               <td colspan="6"><Input  type="text" v-model="detailList[1].stockQuantity" placeholder="进货数量"/></td>
             </tr>
             <tr>
               <td colspan="6"><Input  type="text" v-model="list.detailList[0].spotCheckQuantity" placeholder="抽检数量"/></td>
             </tr>
             <th><tr>裂纹 </tr></th>
             <th><tr>毛刺</tr></th>
             <th><tr>损伤</tr></th>
             <th><tr>尺寸不合格</tr></th>
             <th><tr>接口不合格</tr></th>
             <th><tr>端面平面度</tr></th>
             <tr>
               <td v-for="(item,index) in detailList"><Input  type="text" v-model="item.checkQuantity" /></td>
             </tr>
             <tr>
               <td v-for="(item,index) in detailList"><Input  type="text" v-model="item.spotCheckYieid" /></td>
             </tr>
             <tr>
               <td colspan="6"><Input  type="text" v-model="detailList[1].qualifiedNo" /></td>
             </tr>
             <tr>
               <td colspan="6"><Input  type="text" v-model="detailList[1].yieid" /></td>
             </tr>
             <tr>
               <td colspan="6"><Input  type="text" v-model="detailList[1].conclusion" /></td>
             </tr>
             </tbody>
           </table>-->
           <br/>
           <table cellspacing="0" cellpadding="0">
             <thead>
               <th width="80">本批次水份</th>
               <th width="80">本批次结论</th>
             </thead>
             <tbody>
               <td>
                 <Input  type="text" v-model="list.lotMoisture" />
               </td>
               <td>
                 <Input  type="text" v-model="list.conclusion" />
               </td>
             </tbody>
           </table>
           <br/>
           <table cellspacing="0" cellpadding="0">
             <thead>
             <th width="80">化验员</th>
             <th width="80">检验员</th>
             <th width="80">审核人</th>
             <th width="110">审核日期</th>
             </thead>
             <tbody>
             <tr>
                 <td>
                     {{this.analyst}}
                 </td>
                 <td>
                     {{this.inspector}}
                 </td>
                 <td>
                     {{this.auditor}}
                 </td>
                 <td>
                     <!-- {{list.detailList[0].auditTime}}-->
                 </td>
             </tr>
             </tbody>
             <br/>
           </table>
         </div>
        </div>
      </div>
      <table class="savebar" cellpadding="0" cellspacing="0">
        <tr>
          <td class="save" @click="save" v-if="pageFlag<=2">
            保存
          </td>
          <td class="reset" @click="reset">
            重置（取消）
          </td>
          <td></td>
        </tr>
      </table>
    <SelectCustomer ref="selectCustomer"></SelectCustomer>
    <SelContacts ref="selContacts"></SelContacts>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';

  import SelectCustomer from '@/components/customer/SelectCustomer';
  import page from '@/assets/js/page';
  import floatObj from '@/assets/js/floatObj';
  import process from '@/components/process-start';



  export default {
    components: {
      Loading,

    },
    data() {
      return {
        loading: 0,
        purchaseOrderId:'',
        pageFlag:1,//1.新建 2.编辑 3.修订
        detailList:[{
              "brickTubeName":"",
              "brickTubeType":"",
              "origin":"",
              "stockQuantity":"",
              "spotCheckQuantity":"",
              "quantity":"",
              "spotCheckId":"",
              "checkQuantity":"",
              "spotCheckYieid":"",
              "qualifiedNo":"",
              "conclusion":"",
              "lotMoisture":"",

           /* "yieid":"",
            "yieid":"",
            "yieid":"",
            "yieid":"",*/
          }],
        list:[],
        remark:'',
      }
    },
    mounted: function () {
        this.batchId = this.$route.query.batchId;
        this.versionId = this.$route.query.versionId;
        this.ledgerId=this.$route.query.ledgerId;


        this.materName = this.$route.query.materName;
        this.inspectionDate = this.$route.query.inspectionDate;

        this.analyst=this.$user.empInfo.trueName;
        this.inspector=this.analyst;
        this.auditor=this.analyst;

        this.auditTime = this.$route.query.auditTime;
      this.load();

      this.pageFlag = 1;
      if(this.ledgerId){
        this.pageFlag = 2;
      }
      if(this.$route.query.reversion){
        this.pageFlag = 3;
      }
      if(this.pageFlag == 1){
        this.initNew();
      }
      /*if(this.pageFlag == 2){
        this.load();
      }
      if(this.pageFlag == 3){
        this.load();
      }*/
    },
    computed: {
      pageTitle(){
        if(this.pageFlag == 1){
          return '采购订单 - 创建';
        }
        if(this.pageFlag == 2){
          return '采购订单 - 编辑';
        }
        if(this.pageFlag == 3){
          return '采购订单 - 修订';
        }
      }
    },
    methods: {
      load() {
        this.loading = 1;
        this.$http.post("/api/InspectionSheet/StandardList?purchaseOrderId="+this.purchaseOrderId,{})
          .then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
                this.list = res.data.data;
            }else{
              this.$Message.error('订单不存在！');
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
      selDept(){
        var sel = this.$refs.selContacts;
        sel.show({
          isMulti:false,
          selectPerson:false,
          selectDept:true,
          ok:()=>{
            if(sel.select.length>0){
              this.detailList.departmentName = sel.select[0].title;
              this.detailList.department = sel.select[0].key;
            }
          }
        });
      },
      selEmp(){
        var sel = this.$refs.selContacts;
        sel.show({
          isMulti:false,
          selectPerson:true,
          selectDept:false,
          ok:()=>{
            if(sel.select.length>0){
              this.detailList.proposerName = sel.select[0].title;
              this.detailList.proposer = sel.select[0].key;
            }
          }
        });
      },
      save(){
          var form =
              {
                  providerName:this.list.detailList[0].providerName,
                  operator:this.$user.empInfo.empId,
                  batchId:this.batchId,
                  versionId:this.versionId,
                  ledgerId:this.ledgerId,
                  analyst:this.analyst,
                  inspector:this.inspector,
                  auditor:this.auditor,
                  detailList:this.detailList,

                  lotMoisture:this.list.lotMoisture,
                  conclusion:this.list.conclusion,
              };
          console.log(form)
        this.loading = 1;
       /* var uri = '/api/revision/addAllCheckBrickTube';*/
        this.$http.post(uri, form).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            this.$Message.success("操作成功！");
            this.goBack();
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新操作")
        });
      },
      onDrawingInput(item){
        if(this.detailList.customerId == null || this.detailList.customerId == ''){
          this.$Message.error('请选择客户！');
          return;
        }
        var materId = this.detailList.customerId + '-' + item.drawing;

        /*this.$http.post('/api/material/get?materId=' +materId, {}).then((res) => {
          if (res.data.code === 0 && res.data.data !=null) {
            item.materName = res.data.data.materName;
            item.unit = res.data.data.unit;
          }
        }).catch((error) => {
          this.$Message.error(error.message)
        });*/
      },
      onAmountChange(val){
        this.detailList.amount = val;
      },
      datePickerChange(val){
        this.detailList.signDate = val;
      },
      reset(){
        console.log(this.pageFlag);
        if(this.pageFlag == 1){
          this.initNew();
        }else {
          Object.assign(this.detailList,this.oriItem);
        }
      },
      goBack(){
        /*this.$router.push('/quality/quality-record/RevisionRecord');*/
          this.$router.go(-1);
      }
    }
  }

</script>

<style type="text/css">
  .purchase-order-edit.page{
    width: 900px;
    margin: 0 auto;
    padding: 10px 0px;
    position: relative;
  }
  .purchase-order-edit .subheader{
    height: 34px;line-height: 34px;
    font-size:14px;
    border-bottom: 0px solid #efefef;
    margin-bottom: 10px;
    color:#20bfee;
  }
  .purchase-order-edit .baseinfo{

  }
  .purchase-order-edit .baseinfo .label{
    width: 80px;text-align: right;
    padding-right: 8px;
  }
  .purchase-order-edit .baseinfo table{
    width: 100%;
  }
  .purchase-order-edit .baseinfo table td{
    height: 40px;padding-right:4px;
  }

  .purchase-order-edit .savebar{
    margin-top: 10px;
    height: 40px;
    width: 100%;
    border-collapse: collapse;
  }
  .purchase-order-edit .savebar td{
    border: 1px solid #fefefe;
    font-size: 14px;
  }
  .purchase-order-edit .savebar .save{
    width: 120px;
    border: 1px solid #20bfee;
    background-color: #20bfee;
    color: white;
    text-align: center;
    cursor: pointer;
  }
  .purchase-order-edit .savebar .reset{
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

  .purchase-order-edit .tooltip{
    padding:10px;
    background-color: #fafafa;
    border:1px solid #efefef;
    border-radius: 3px;
    color:#666;
    margin-top: 10px;
  }

  .inquire-detail-card.editable {

  }
  .inquire-detail-card .title{
    height: 40px;
    line-height: 38px;
    text-align: center;
    min-width: 89px;
    border: 1px solid #dedede;
    display: inline-block;
    border-bottom: 0px;font-weight: bold;
  }
  .inquire-detail-card .editable-table{
    overflow-x: auto;
  }
  .editable-table table{
    border-collapse:collapse;
    border:  px solid #dedede;
    width: 100%;
  }
  .editable-table table th{
    background: #efefef;
  }
  .inquire-detail-card .editable-table table td,
  .inquire-detail-card .editable-table table th{
    height: 40px;
    border: 1px solid #dedede;
    padding: 0 10px;text-align: center;
  }
  .editable-table .ivu-input,
  .editable-table .ivu-input-number,
  .editable-table .ivu-select,
  .editable-table .ivu-select-selection{
    border: 0px;
  }
  .editable-table .ivu-input-number-input{
    padding-right: 24px;
    text-align: right;
  }
  .editable-table .col-xh{
    text-align: center; cursor: default;
  }
  .editable-table .col-xh.cur{
    background: #e8f8fd;
    color:#20bfee;
    border-right: 2px solid #20bfee;
    cursor: default;font-weight: bold;;
  }
  /*行扩展操作*/
  .editable-table .col-cz input{
    text-align: center;;
  }
  .editable-table .col-yd input{
    text-align: center;;
  }
  .inquire-detail-card .editable-card{
    margin-top: 10px;
    margin-bottom: 10px;
  }

</style>
