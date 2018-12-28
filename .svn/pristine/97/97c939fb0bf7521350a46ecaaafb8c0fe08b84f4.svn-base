<template>
    <div class="page purchase-order-edit">
        <div class="page-bar">
            <div class="page-bar-title">{{pageTitle}}</div>
        </div>
        <table  cellspacing="20px;" cellpadding="0">
            <tr>
                <td><i-button type="info">保存并提交</i-button></td>
                <td><i-button type="info">取消</i-button></td>
                <td><i-button type="info">打印</i-button></td>
                <td><i-button type="info" @click="goBack">退出</i-button></td>
            </tr>
        </table>
        <Loading :loading="loading">
            <div class="baseinfo">
                <table stripe  cellspacing="10px" cellpadding="0">
                    <tr>
                        <td align="center">文件编号：</td>
                        <td colspan="3"><Input v-model="formItem.inspectionApplyId" placeholder="文件编号"></Input></td>
                    </tr>
                    <tr>
                        <td align="center">产品现行状态：</td>
                        <td colspan="3">
                            <AddDetailEditable ref="editable"
                                               :list="list"
                                               :editable="true"
                                               :isNew="pageFlag==1"
                                               @on-amount-change="onAmountChange"></AddDetailEditable>
                            <Input v-model="formItem.remark" type="textarea" :rows="2" placeholder="产品现行状态.."></Input>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">检验人员：</td>
                        <td><Input v-model="formItem.inspectionApplyId" placeholder="检验人员"></Input></td>
                        <td colspan="2">
                            <DatePicker type="date" placeholder="" v-model="formItem.applyDate" format="yyyy-MM-dd" style="width:120px"></DatePicker>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">申请理由：</td>
                        <td colspan="3"><Input v-model="formItem.remark" type="textarea" :rows="2" placeholder="产品现行状态.."></Input></td>
                    </tr>
                    <tr>
                        <td colspan="1">申请人：</td>
                        <td colspan="1"><Input width="80%" v-model="formItem.inspectionApplyId" placeholder="申请人"></Input></td>
                        <td colspan="1">操作日期：</td>
                        <td><DatePicker type="date" placeholder="" v-model="formItem.applyDate" format="yyyy-MM-dd" style="width:120px"></DatePicker></td>
                    </tr>

                </table>
                <!--<table stripe  cellspacing="10px" cellpadding="0">
                    <tr>
                        <td class="label">纠正措施编号</td>
                        <td><Input v-model="formItem.inspectionApplyId" placeholder="纠正措施编号"></Input></td>
                        <td class="label">操作人</td>
                        <td>
                            <Input v-model="formItem.operatorName"  readonly="readonly" style="cursor: pointer;" @click.native="selEmp" icon="search"></Input>
                        </td>
                        <td class="label">操作日期</td>
                        <td>
                            <DatePicker type="date" placeholder="" v-model="formItem.applyDate" format="yyyy-MM-dd" style="width:120px"></DatePicker>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">不合格品单编号</td>
                        <td><Input v-model="formItem.inspectionApplyId" disabled="disabled" placeholder="不合格品单编号"></Input></td>
                        <td class="label">采购申请单号</td>
                        <td><Input v-model="formItem.inspectionApplyId" disabled="disabled" placeholder="采购申请单号"></Input></td>
                        <td class="label">检验员</td>
                        <td>
                            <Input v-model="formItem.operatorName"  readonly="readonly" style="cursor: pointer;" @click.native="selEmp" icon="search"></Input>
                        </td>
                    </tr>
                    &lt;!&ndash;<tr>

                        <td class="label">申请部门</td>
                        <td>
                            <Input v-model="formItem.departmentName" readonly="readonly" style="cursor: pointer;"  @click.native="selDept"></Input>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">备注</td>
                        <td class="col-remark">
                            <Input v-model="formItem.remark" type="textarea" :rows="3"></Input>
                        </td>
                    </tr>&ndash;&gt;
                </table>-->
            </div>
            <div>
                <!--<div class="subheader">
                    物料明细
                </div>-->
               <!-- <AddDetailEditable ref="editable"
                          :list="list"
                          :editable="true"
                          :isNew="pageFlag==1"
                          @on-amount-change="onAmountChange"></AddDetailEditable>-->
            </div>
           <!-- <div style="height: 20px "></div>
            <table class="savebar" cellpadding="0" cellspacing="0">
                <tr>
                    <p style="font-size: 16px">不合格描述:</p>
                   <Input v-model="formItem.operatorName" style="margin-top: 10px" type="textarea" rows="4" placeholder="不合格描述..." />
                </tr>
            </table>-->
        </Loading>
        <SelContacts ref="selContacts"></SelContacts>
    </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import AddDetailEditable from './concessionEditable';
  import page from '@/assets/js/page';
  import SelContacts from '@/components/selcontacts';

  export default {
    components: {
      Loading,
      LayoutHor,
      AddDetailEditable,
      SelContacts
    },
    data() {
      return {
        loading: 0,
        purchaseOrderId:'',
        pageFlag:1,//1.新建 2.带入
        formItem:{
          "inspectionApplyId":"",
          "operatorName":"",
          "applyDate":"",
          "departmentName":"",
          "remark":"",
        },
        list:[],
        currencyArgs:[],
        oriItem:{},
        remark:'',
        instrumentId:"",
      }
    },
    mounted: function () {
      this.instrumentId = this.$route.query.id;

      this.pageFlag = 1;
      if(this.instrumentId){
        this.pageFlag = 2;
      }
      if(this.pageFlag == 1){
        this.initNew();
        this.load();
      }
      if(this.pageFlag == 2){
        this.load();
      }
    },
    computed: {
      pageTitle(){
        if(this.pageFlag == 1){
          return '计量器具管理- 新建外检申请单';
        }
        if(this.pageFlag == 2){
          return '计量器具管理 - 修改外检申请单';
        }
      }
    },

    methods: {
      load() {
        this.loading = 1;
        var itemDetail= this.$refs.editable;//引用到其他控件，赋给对象
        this.$http.post("/api/instrument/list",{instrumentId:this.$route.query.id}).then((res) => {
          this.loading = 0;
          var def=res.data.data.rows[0];
          this.list.push(def);
          /*if (res.data.code == 0) {
            if(res.data.data){

              this.oriItem = eval('('+JSON.stringify(res.data.data)+')');

              Object.assign(this.formItem,res.data.data);
              this.list =[];
              res.data.data.detailList.map(item=>{
                this.list.push(Object.assign(itemDetail.listNewRow(),item));//该对象引用自己的方法listNewRow();该方法取到的值放入item
              });
              this.formItem.departmentName = this.$args.getArgText('deptList',this.formItem.department);
            }else{
              this.$Message.error('订单不存在！');
              this.goBack();
            }
          } else {
            this.$Message.error(res.data.message);
          }*/
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("操作失败！")
        });
      },

      initNew(){
        Object.assign(this.formItem,{
          "inspectionApplyId":"",
          "operatorName":"",
          "applyDate":"",
          "departmentName":"",
          "remark":"",
        });
        this.formItem.applyDate = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.proposer = this.$user.empInfo.empId;
          this.formItem.operatorName = this.$user.empInfo.trueName;
        }
        //this.list = [];
        //this.list.push(this.$refs.editable.listNewRow());
        //this.list.push(this.$refs.editable.listNewRow());
      },

      selDept(){
        var sel = this.$refs.selContacts;
        sel.show({
          isMulti:false,
          selectPerson:false,
          selectDept:true,
          ok:()=>{
            if(sel.select.length>0){
              this.formItem.departmentName = sel.select[0].title;
              this.formItem.department = sel.select[0].key;
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
              this.formItem.proposer = sel.select[0].key;
              this.formItem.operator = sel.select[0].key;
            }
          }
        });
      },

      //保存并提交
      save(){
        var form = {
          detailList:[]
        };

        Object.assign(form,this.formItem);
        form.applyDate = page.formatDate(form.applyDate);

        form.detailList = [];
        // 明细
        for(var i = 0;i<this.list.length;i++){
          var item = this.list[i];
          if(item.materId != ''){
            if(item.quantity == 0){
              this.$Message.error('物料 ' + item.materId + ' ,请录入数量');
              return;
            }
            item.needDate = page.formatDate(item.needDate);
            form.detailList.push(item);
          }
        }

        // 提交
        this.loading = 1;
        var uri = '/api/ExternalInspectionApply/insert';

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

      onAmountChange(val){
        this.formItem.amount = val;
      },
      reset(){
        if(this.pageFlag == 1){
          this.initNew();
        }else {
          Object.assign(this.formItem,this.oriItem);
        }
      },
      goBack(){
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
</style>
