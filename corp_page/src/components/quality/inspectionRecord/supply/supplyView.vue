<template>
  <Modal v-model="display" title="采购报检单" :closable="true" :mask-closable="true" width="1000" class="selcustomer" class-name="my_parsent_model">
  <div class="page purchase-order-view">
    <div class="page-bar">
    </div>
    <Loading :loading="loading">
      <div class="baseinfo">

        <table cellspacing="0" cellpadding="0">
          <tr>
            <td>检验单号：</td>
            <td><Input v-model="formRecord.inspectionId" placeholder="检验单号..." style="width: 100px" /></td>
            <td>
              是否审核:
              <i-switch  v-model="formRecord.sfsh"/>
            </td>
            <td class="label" style="width: 100px;!important;">过磅单编号：</td>
            <td>
              <Input v-model="formRecord.gbdh" placeholder="过磅单编号" style="width: 100px" />
              <i-button type="success" @click="gbcx" ghost>查询</i-button>
            </td>
          </tr>
          <tr>
            <td class="label">备注：</td>
            <td>
              <Input v-model="formRecord.remark" type="textarea" placeholder="请输入备注..." />
            </td>
          </tr>
        </table>
      </div>
      <div style="width: 100%;height: 0px;margin-bottom: 10px;margin-top: 20px;border: #666666 solid 2px;"></div><!--/*border: #666666 solid 2px;*/-->

    <div class="baseinfo">

      <table cellspacing="0" cellpadding="0">
        <thead>
          <th class="label">采购申请单编号</th>
          <th>{{formItem.purchaseOrderId}}</th>
          <th class="label">申请日期</th>
          <th>
            {{formItem.createTime}}
          </th>
          <th class="label">申请部门</th>
          <th>
            {{formItem.departmentName}}
          </th>
          <th class="label">需求日期</th>
          <th>
            {{formItem.applyDate}}
          </th>
        </thead>
      </table>
    </div>
    <div>
<!--      <div class="subheader">
        单据明细
      </div>-->
      <Editable ref="editable"
        :list="list"
        :editable="false"
        :formItem="formItem"
        @closeThisParent="closeThisParent"
        @on-amount-change="onAmountChange" @singleSave="singleSave" :purchaseOrderId="formItem.purchaseOrderId" :inspectionId="formRecord.inspectionId"></Editable>
    </div>
<!--    <div class="subheader">
      流程状态
    </div>-->
    <table class="savebar" cellpadding="0" cellspacing="0">
      <tr>
        <td class="save" @click="save">
          确定
        </td>
        <td class="reset" @click="reset">
          重置
        </td>
        <td></td>
      </tr>
    </table>
    </Loading>
  </div>
    <div slot="footer"></div>
  </Modal>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './DetailEditable';
  import page from '@/assets/js/page';
  import floatObj from '@/assets/js/floatObj';
  import process from '@/components/process-start';
  import UploadBox from '@/components/upload/Index';

  export default {
    components: {
      Loading,
      LayoutHor,
      Editable,
      UploadBox,
    },
    data() {
      return {
        loading:0,
        materId:'',
        inspectionApplyId:'',
        pageFlag:1,//1.新建 2.编辑 3.修订
        formItem:{
          "inspectionApplyId":"",
          "department":"",
          "departmentName":"",
          "proposer":"",
          "proposerName":"",
          "remark":"",
          "quality":"",
        },
        formRecord:{
          inspectionId:'',
          sfsh:false,
          gbdh:'',
          remark:'',
        },
        display:false,
        list:[],
        currencyArgs:[],
        oriItem:{},
      }
    },
    mounted: function () {
      /*this.purchaseOrderId="C201810220019";*/
      /*this.purchaseOrderId = this.$route.query.id;
      this.load();*/

    },
    computed: {
      pageTitle(){
      }
    },
    methods: {
      load() {
        this.loading = 1;
         this.$http.post("/api/InspectionSheet/list?inspectionApplyId="+this.inspectionApplyId,{
           inspectionApplyId:this.inspectionApplyId,
           materId:this.materId,
         }).then((res) => {
           this.loading = 0;
           if (res.data.code == 0) {
             var data = res.data.data.rows[0];
             if(data){
               data.departmentName = this.$args.getArgText('deptList',data.department);
               data.proposerName = this.$args.getArgText('empList',data.proposer);
               this.oriItem = eval('('+JSON.stringify(data)+')');
               Object.assign(this.formItem,data);

               if(data.detailList){
                 data.detailList.map((item)=>{
                   item.unitName = this.$args.getArgText('unit',item.unit);
                 })
               }

               this.list = data.detailList;
               var nowDate = page.formatDate(new Date(),'yyyy-MM-dd');
               if(this.$user.empInfo){
                 //this.formItem.proposer = this.$user.empInfo.empId;
                 var operatorName = this.$user.empInfo.trueName;
               }
               for(var ss in this.list){
                     this.list[ss].inspector = operatorName;
                     this.list[ss].analyst = operatorName;
                     this.list[ss].nowDate = nowDate;
               }
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

      save(){
        var item = new Object();
        //item[0] = this.formItem;
        Object.assign(item,this.formItem);
        Object.assign(item,this.formRecord);
        //item[1] = this.formRecord;

        this.$emit("supply_get",item);
        this.goBack();
      },
      onAmountChange(val){
        this.formItem.amount = val;
      },
      datePickerChange(val){
        this.formItem.signDate = val;
      },
      closeThisParent(){
          this.display = false;
      },
      reset(){
          Object.assign(this.formRecord,{
            sfsh:false,
            gbdh:'',
            remark:'',
          });
      },
      singleSave(index){
          var item={detailList:[]};
          Object.assign(item,this.formRecord);
          item.detailList.push(this.formItem);
          item.detailList.push(this.list[index]);
          delete item.detailList[0].detailList;
          this.$http.post("/api/InspectionSheet/add",item).then((res) => {
              this.loading = 0;
              if (res.data.code === 0) {
                  this.$Message.success("保存成功");
              } else {
                  this.$Message.error(res.data.message)
              }
          }).catch((error) => {
              this.loading = 0;
              this.$Message.error(error.message)
          });
      },
      open(item) {
        this.inspectionApplyId=item.inspectionApplyId;
        this.materId=item.materId;
        this.$http.post('/api/InspectionSheet/getId', {
        }).then((res) => {
          if (res.data.code === 0){
            var formDataGetId={
              "inspectionId":res.data.data
            };
            Object.assign(this.formRecord,formDataGetId);
          }else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error.message)
        });
        this.display = true;

        this.reset();
        this.load();
      },
      open_weigh(item){
        this.display = true;
        this.formRecord.gbdh=item.materId;
        this.load();
      },
      close(){

        this.display = false;
        },
      goBack(){
        this.display = false;
      },
      gbcx(){
        alert(this.formRecord.gbdh);
      },
    }
  }

</script>

<style type="text/css">
  .purchase-order-view.page{
    width: 900px;
    margin: 0 auto;
    padding: 10px 0px;
    position: relative;
  }
  .purchase-order-view .subheader{
    height: 34px;line-height: 34px;
    font-size:14px;
    border-bottom: 0px solid #efefef;
    margin-bottom: 10px;
    color:#20bfee;
  }
  .purchase-order-view .baseinfo{
    font-size: 14px;
  }
  .purchase-order-view .baseinfo .label{
    width: 90px;text-align: right;
    padding-right: 8px;color:#999;
  }
  .purchase-order-view .baseinfo table{
    width: 100%;
  }
  .purchase-order-view .baseinfo table td{
    height: 40px;padding-right:4px;
  }

  .purchase-order-view .baseinfo table .emptytds td{
    height: 0px;padding-right:8px;
  }

  .purchase-order-view .savebar{
    margin-top: 10px;
    height: 40px;
    width: 100%;
    border-collapse: collapse;
  }
  .purchase-order-view .savebar td{
    border: 1px solid #fefefe;
    font-size: 14px;
  }
  .purchase-order-view .savebar .save{
    width: 120px;
    border: 1px solid #20bfee;
    background-color: #20bfee;
    color: white;
    text-align: center;
    cursor: pointer;
  }
  .purchase-order-view .savebar .reset{
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

  .purchase-order-view .tooltip{
    padding:10px;
    background-color: #fafafa;
    border:1px solid #efefef;
    border-radius: 3px;
    color:#666;
    margin-top: 10px;
  }

  .purchase-order-view .flowinfo{
    font-size: 14px;
  }
  .purchase-order-view .flowinfo td,.contractview .flowinfo th{
    padding:4px 10px;
  }
  .my_parsent_model{
    z-index: 1001;
  }
</style>
