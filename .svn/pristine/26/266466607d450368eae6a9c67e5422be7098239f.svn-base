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
         <div>8.原砂检验1011</div>
         <div class="editable-table">
           <table cellspacing="0" cellpadding="0">
             <thead>
             <th>供应商</th>
             <th>物料名称</th>
             <th width="60">数量</th>
             <th width="60">批次号</th>
             <th width="80">检验日期</th>
             </thead>
             <tbody>
             <tr>
               <td>
                 {{this.provider}}
               </td>
               <td>
                 {{this.materName}}
               </td>
               <td>
                 <Input type="text" v-model="list.quantity" />
               </td>
               <td class="col-cz">
                 {{list.batchId}}
               </td>
               <td class="col-yd">
                 {{this.inspectionDate}}
               </td>
             </tr>
             </tbody>
           </table>
           <br/>
           <table cellspacing="0" cellpadding="0">
             <thead>
             <th colspan="6">检验结果：</th>
             <tr>
               <th>含泥量</th>
               <td><!--{{list.siltContent}}--><Input  type="number" v-model="list.siltContent" /></td>
               <th>含水量%</th>
               <td><!--{{list.waterContent}}--><Input  type="number" v-model="list.waterContent" /></td>
               <th>胶质价</th>
               <td><!--{{list.colloidIndex}}--><Input  type="number" v-model="list.colloidIndex" /></td>
             </tr>
             </thead>
           </table>
           <br/>
           <Button @click="add" icon="plus"></Button>
           <Button @click="remove" icon="android-remove"></Button>
           <table cellspacing="0" cellpadding="0">
             <thead>
             <th rowspan="2">筛孔尺寸（mm）</th>
             <td v-for="(item,index) in list.itemOneObj"><!--{{item.detail.aperture_size}}--><Input  type="number" v-model="item.detail.aperture_size" /></td>
             <!--<td v-for="(item,index) in list.itemOneObj">{{item.detail.aperture_size}}<Input  type="text" v-model="list.colloidIndex" /></td>-->
             <tr>
               <th>3.35</th>
               <th>1.70</th>
               <th>0.850</th>
               <th>0.600</th>
               <th>0.425</th>
               <th>0.300</th>
               <th>0.212</th>
               <th>0.150</th>
               <th>0.100</th>
               <th>0.075</th>
               <th>0.053</th>
               <th>底盘</th>
             </tr>
             <tr>
               <th>残留量</th>
               <td v-for="(item,index) in list.itemOneObj"><!--{{item.detail.residual_quantity}}--><Input  type="text" v-model="item.detail.residual_quantity" /></td>
               <!--<td v-for="(item,index) in list.itemOneObj">{{item.detail.residual_quantity}}</td>-->
             </tr>
             </thead>
           </table>
           <br/>
           <table cellspacing="0" cellpadding="0">
             <thead>
             <th>备注</th>
             <td width="300px"><!--{{list.remark}}--><Input  type="text" v-model="list.remark" /></td>
             <th>检验结论</th>
             <td><!--{{list.conclusion}}--><Input  type="text" v-model="list.conclusion" /></td>
             </thead>
           </table>
           <br/>
           <table cellspacing="0" cellpadding="0">
             <thead>
             <th>主要粒度组成（分组代号）</th>
             <th>平均粒度</th>
             <th>均匀率%</th>
             <th>细率</th>
             </thead>
             <tbody>
             <td><!--{{list.mainGrainComposition}}--><Input  type="number" v-model="list.mainGrainComposition" /></td>
             <td><!--{{list.granularityAvg}}--><Input  type="number" v-model="list.granularityAvg" /></td>
             <td><!--{{list.averageRate}}--><Input  type="number" v-model="list.averageRate" /></td>
             <td><!--{{list.fineRate}}--><Input  type="number" v-model="list.fineRate" /></td>
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
                 {{this.auditTime}}
               </td>
             </tr>
             </tbody>
             <hr/>
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
        curIndex:0,
        loading: 0,
        purchaseOrderId:'',
        pageFlag:1,//1.新建 2.编辑 3.修订
        detailList:{
          "purchaseOrderId":"",
          "department":"",
          "departmentName":"",
          "proposer":"",
          "proposerName":"",
          "remark":"",
          "status":0,
          "wfInstId":"",
          "wfInstTime":"",
          "wfInstRemark":null,
        },
        list:[],
        currencyArgs:[],
        oriItem:{},
        remark:'',
      }
    },
    mounted: function () {
        this.batchId = this.$route.query.batchId;
        this.versionId = this.$route.query.versionId;
        this.provider = this.$route.query.provider;
        this.materName = this.$route.query.materName;
        this.inspectionDate = this.$route.query.inspectionDate;
        this.analyst = this.$route.query.analyst;
        this.inspector = this.$route.query.inspector;
        this.auditor = this.$route.query.auditor;
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
      if(this.pageFlag == 2){
        this.load();
      }
      if(this.pageFlag == 3){
        this.load();
      }
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
        this.$http.post("/api/revision/getNewCheckOriginalSand?batchId=" + this.batchId+"&versionId="+this.versionId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
                this.list = res.data.data[0];
                //this.list.push(this.$refs.editable.listNewRow());
             /* this.oriItem = eval('('+JSON.stringify(res.data.data)+')');
              Object.assign(this.detailList,res.data.data);
              this.list = res.data.data.detailList;
              this.detailList.departmentName = this.$args.getArgText('deptList',this.detailList.department);
              this.detailList.proposerName = this.$args.getArgText('empList',this.detailList.proposer);*/
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
      initNew(){
        Object.assign(this.detailList,{
          "purchaseOrderId":"",
          "department":"",
          "departmentName":"",
          "proposer":"",
          "proposerName":"",
          "remark":"",
          "status":0,
          "wfInstId":"",
          "wfInstTime":"",
          "wfInstRemark":null,
          "createTime":page.formatDate(new Date(),'yyyy-MM-dd'),
          "supplement":page.formatDate(new Date(),'yyyy-MM-dd'),
        });
        this.detailList.signDate = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.detailList.proposer = this.$user.empInfo.empId;
          this.detailList.proposerName = this.$user.empInfo.trueName;
        }
        this.list = [];
        this.list.push(this.$refs.editable.listNewRow());
        this.list.push(this.$refs.editable.listNewRow());
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
        var form = {
          list:[]
        };
        Object.assign(form,this.list);
          form.operator=this.$user.empInfo.empId;
        form.signDate = page.formatDate(form.signDate);
          form.inspectionResult=JSON.stringify(form.itemOneObj);
        console.log(form)
        form.list = [];
        // 明细
        /*for(var i = 0;i<this.list.length;i++){
          var item = this.list[i];
          if(item.materId != ''){
            if(item.quantity == 0){
              this.$Message.error('物料 ' + item.materId + ' ,请录入数量');
              return;
            }
            item.needDate = page.formatDate(item.needDate);
            form.detailList.push(item);
          }
        }*/

        // 提交
        this.loading = 1;
        var uri = '/api/revision/addAllCheckOriginalSand';
        /*if(this.pageFlag == 2){
          uri = '/api/order/update';
        }*/

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
        add(){
            console.log(this.list.itemOneObj.push);
            this.list.itemOneObj.push(this.listNewRow());//增加新行*/
        },
        listNewRow(){
            var def = {
                materId:'',
                materName:'',
                spec:'',
                model:'',
                origin:'',
                materType:'',
                use:'',
                unit:'',
                quantity:null,//数字类型，默认情况
                price:null,
                amount:null,
                remark:'',
            };
            return def;
        },
        remove(){// 删除一行
            if(this.list.itemOneObj.length > this.curIndex){
                this.list.itemOneObj.splice(this.curIndex,1);
            }
            if(this.list.itemOneObj.length <= this.curIndex){
                this.curIndex = this.list.itemOneObj.length - 1;
            }
            if(this.curIndex == -1){
                this.curIndex = 0;
            }
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
        this.$router.push('/quality/quality-record/RevisionRecord');
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
