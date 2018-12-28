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
         <div>5.树脂、铬矿砂检验1008</div>
         <div  class="editable-table">
           <table cellspacing="0" cellpadding="0">
             <thead>
             <th>供应商</th>
             <th>物料名称</th>
             <th width="60">数量</th>
             <th width="60">批次号</th>
             <th width="80">规格</th>
             <th width="380">检验日期</th>
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
                 <Input type="number" v-model="list.quantity" />
               </td>
               <td class="col-cz">
                 {{list.batchId}}
               </td>
               <td>
                 {{list.deliveryType}}
               </td>
               <td class="col-yd">
                 {{this.inspectionDate}}
               </td>
             </tr>
             </tbody>
           </table>
           <br/>
           <!--<Button @click="add" icon="plus"></Button>
           <Button @click="remove" icon="android-remove"></Button>-->
           <table cellspacing="0" cellpadding="0">
             <thead>
             <th colspan="11">检验目的：粒度、水分、灼烧减量、酸耗值、PH、发气量</th>
             <tr><th colspan="11">取样（g）  100</th></tr>
             <th>筛孔尺寸mm</th>
             <template v-for="(item,index) in list.itemOneObj" >
             <th>{{item.aperture_size}}<!--<Input  type="text" v-model="item.aperture_size" />--></th>
             </template>
             <th>底盘</th>
             <tr>
               <th>分析目数</th>
               <th v-for="(item,index) in list.itemOneObj">{{item.analysis_mesh}}<!--<Input  type="text" v-model="item.analysis_mesh" />--></th>
               <th></th>
             </tr>
             </thead>
             <tbody>
             <th>标准</th>
             <td v-for="(item,index) in list.itemOneObj"><Input  type="number" v-model="item.detail.standard_one" /></td>
             <tr>
               <th>含量%</th>
               <td v-for="(item,index) in list.itemOneObj"><Input  type="number" v-model="item.detail.content" /></td>
             </tr>
             </tbody>
             <br/>
             <thead>
             <tr>
               <th></th>
               <th>主要粒度质量和</th>
               <th>含水量%</th>
               <th>PH值</th>
               <th>发气量ml</th>
               <th>细分含量%</th>
               <th>酸耗值ml</th>
               <th>灼碱量%</th>
               <th>平均细度</th>
             </tr>
             </thead>
             <tbody>
             <th>标准</th>
             <td v-for="(item,index) in list.itemTwoObj"><!--{{item.grain_quality.standard_two}}--><Input  type="number" v-model="item.grain_quality.standard_two" /></td>
             <td v-for="(item,index) in list.itemTwoObj"><!--{{item.water_content.standard_two}}--><Input  type="number" v-model="item.water_content.standard_two" /></td>
             <td v-for="(item,index) in list.itemTwoObj"><!--{{item.ph_value.standard_two}}--><Input  type="number" v-model="item.ph_value.standard_two" /></td>
             <td v-for="(item,index) in list.itemTwoObj"><!--{{item.gas_volume.standard_two}}--><Input  type="number" v-model="item.gas_volume.standard_two" /></td>
             <td v-for="(item,index) in list.itemTwoObj"><!--{{item.subdivided_content.standard_two}}--><Input  type="number" v-model="item.subdivided_content.standard_two" /></td>
             <td v-for="(item,index) in list.itemTwoObj"><!--{{item.acid_consumption_value.standard_two}}--><Input  type="number" v-model="item.acid_consumption_value.standard_two" /></td>
             <td v-for="(item,index) in list.itemTwoObj"><!--{{item.burning_alkaline.standard_two}}--><Input  type="number" v-model="item.burning_alkaline.standard_two" /></td>
             <td v-for="(item,index) in list.itemTwoObj"><!--{{item.average_fineness.standard_two}}--><Input  type="number" v-model="item.average_fineness.standard_two" /></td>
             <tr>
               <th>实测</th>
               <td v-for="(item,index) in list.itemTwoObj"><!--{{item.grain_quality.actual}}--><Input  type="number" v-model="item.grain_quality.actual" /></td>
               <td v-for="(item,index) in list.itemTwoObj"><!--{{item.water_content.actual}}--><Input  type="number" v-model="item.water_content.actual" /></td>
               <td v-for="(item,index) in list.itemTwoObj"><!--{{item.ph_value.actual}}--><Input  type="number" v-model="item.ph_value.actual" /></td>
               <td v-for="(item,index) in list.itemTwoObj"><!--{{item.gas_volume.actual}}--><Input  type="number" v-model="item.gas_volume.actual" /></td>
               <td v-for="(item,index) in list.itemTwoObj"><!--{{item.subdivided_content.actual}}--><Input  type="number" v-model="item.subdivided_content.actual" /></td>
               <td v-for="(item,index) in list.itemTwoObj"><!--{{item.acid_consumption_value.actual}}--><Input  type="number" v-model="item.acid_consumption_value.actual" /></td>
               <td v-for="(item,index) in list.itemTwoObj"><!--{{item.burning_alkaline.actual}}--><Input  type="number" v-model="item.burning_alkaline.actual" /></td>
               <td v-for="(item,index) in list.itemTwoObj"><!--{{item.average_fineness.actual}}--><Input  type="number" v-model="item.average_fineness.actual" /></td>
             </tr>
             </tbody>
           </table>
           <br/>
           <table cellspacing="0" cellpadding="0">
             <thead>
             <th width="80">检验结论</th>
             <th width="80">化验员</th>
             <th width="80">检验员</th>
             <th width="80">审核人</th>
             <th width="110">审核日期</th>
             </thead>
             <tbody>
             <tr>
               <td>
                 {{list.conclusion}}
               </td>
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

        this.$http.post("/api/revision/getNewCheckResinChromeOre?batchId=" + this.batchId+"&versionId="+this.versionId,{}).then((res) => {
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
         form.itemOne=JSON.stringify(form.itemOneObj);
          form.itemTwo=JSON.stringify(form.itemTwoObj);
        console.log(form)
        // 表头信息

        form.list = [];
        /*// 明细
        for(var i = 0;i<this.list.length;i++){
          var item = this.list[i];
          if(item.materId != ''){
            if(item.quantity == 0){
              this.$Message.error('物料 ' + item.materId + ' ,请录入数量');
              return;
            }
            item.needDate = page.formatDate(item.needDate);
            form.list.push(item);
          }
        }*/

        // 提交
        this.loading = 1;
        var uri = '/api/revision/addAllCheckResinChromeOre';
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
          var list={
              analysis_mesh:'',
              aperture_size:'',
              detail:''
          };
          this.list.itemOneObj.push(list);
            /*console.log(this.list.itemOneObj.push);
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
