<template>
  <div class="page page-bill">
    <div class="page-bar">
      <LayoutHor>
        <div slot="left">
          <Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>
        </div>
        <div class="page-bar-title">{{pageTitle}}</div>
      </LayoutHor>
    </div>
    <Loading :loading="loading">
    <div class="baseinfo"> 
      <div class="page-tools">
        <table cellpadding="0" cellspacing="0">
          <tr>
            <td>
              <Button @click="goImport">导入合同</Button>
            </td>
          </tr>
        </table> 
      </div>
      <Form ref="form" class="page-form" :model="formItem" :rules="formRules" :label-width="120"> 
      <table cellspacing="0" cellpadding="0">
        <colgroup>
          <col width="33%"> 
          <col width="auto"> 
          <col width="33%">
        </colgroup>
        <tr>
          <td>
            <FormItem prop="complaintId" label="投诉单号">
              <Input v-model="formItem.complaintId" :readonly="true"></Input>
            </FormItem>
          </td> 
          <td>
            <FormItem prop="contractId" label="合同编号">
              <Input v-model="formItem.contractId" :readonly="true"></Input>
            </FormItem>
          </td>
          <td>
            <FormItem prop="customerId" label="客户">
              <Input v-model="formItem.customerName" :readonly="true"></Input>
            </FormItem>
          </td>
        </tr>
        <tr>
          <td>
            <FormItem prop="sales" label="业务员">
              <Input v-model="formItem.salesName" :readonly="true"></Input>
            </FormItem>
          </td> 
          <td>
            <FormItem prop="address" label="处置地点">
              <Input v-model="formItem.address"></Input>
            </FormItem>
          </td> 
          <td>
            <FormItem prop="createTime" label="受理时间">
              <DatePicker type="date" placeholder="" 
                v-model="formItem.createTime" format="yyyy-MM-dd"  
                :disabled="pageFlag!=1" :readonly="pageFlag!=1" 
                @on-change="formItem.createTime=arguments[0]"
                style="width:120px"></DatePicker>
            </FormItem>
          </td>
        </tr>
      </table>
      <Editable>
      <table cellpadding="0" cellspacing="0">
        <thead>
          <th >物料编码</th>
          <th >物料名称</th>
          <th >图号</th>
          <th >铸件号</th>
          <th >材质</th> 
          <th >件数</th>
          <th><span>备注</span></th>
          <th>金额</th>
          <th><span>金额说明</span></th>
        </thead> 
        <tbody>
          <tr >
            <td >
              <Input type="text" v-model="formItem.materId" :readonly="true" disabled/>
            </td>  
            <td >
              <Input type="text" v-model="formItem.materName" :readonly="true" disabled/>
            </td>
            <td >
              <Input type="text" v-model="formItem.drawing" :readonly="true" disabled/>
            </td>
            <td >
              <Input type="text" v-model="formItem.casting" />
            </td>
            <td >
              <Input type="text" v-model="formItem.texture" :readonly="true" disabled/>
            </td>
            <td class="col-quantity" > 
              <Input  type="text" v-model="formItem.quantity" :readonly="true" disabled/>
            </td>
            <td class="col-remark">
              <Input type="text" v-model="formItem.remark" />
            </td>
            <td class="col-amount"> 
              <InputNumber :max="999999" :min="0" v-model="formItem.amount"></InputNumber> 
            </td>
            <td class="col-amountRemark">
              <Input type="text" v-model="formItem.amountRemark" />
            </td>
          </tr>
        </tbody>
      </table>
    </Editable>
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>售后问题描述处理时间等要求</td>
        </tr>
        <tr>
          <td>
            <Input v-model="formItem.requireRemark" type="textarea" style="width:100%" :rows="4"></Input>
          </td>
        </tr>
      </table>
      <table class="savebar" cellpadding="0" cellspacing="0">
        <tr>
          <td class="save" @click="save" v-if="pageFlag<=2">
            保存
          </td> 
          <td class="reset" @click="reset">
            重置
          </td>
          <td></td>
        </tr>
      </table>
      </Form>
    </div>
    
    </Loading> 
    <SelContract ref="selContract"></SelContract>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import page from '@/assets/js/page';
  import pagejs from '@/assets/js/page';
  import SelContract from './SelContract';
  import Editable from '@/components/editable-table';

  export default {
    components: {
      Loading,
      LayoutHor,
      SelContract,
      Editable
    },
    data() {
      return {
        loading: 0,
        stockBillId:'',
        pageFlag:1,//1.新建 2.编辑 3.修订
        formItem:{
          complaintId:'',
          contractId:'',
          customerId:'',
          customerName:'',
          sales:'',
          salesName:'',
          address:'',
          createTime:page.formatDate(new Date(),'yyyy-MM-dd'),
          requireRemark:'',
          materId:'',
          materName:'',
          drawing:'',
          casting:'',
          texture:'',
          quantity:'',
          linkMan:'',
          linkPhone:'',
          remark :'',
          amount:0,
          amountRemark:'',
          customerName:'',
        },
        formRules:{

        },
        list:[],  
        oriItem:{},
        storage:[]
      }
    },
    mounted: function () { 
      this.complaintId = this.$route.query.id;
      if(this.complaintId){
        this.pageFlag = 2;
        this.load();
      }else{
        this.pageFlag = 1;
        this.initNew();
      }
    },
    computed: {
      pageTitle(){
        if(this.pageFlag == 1){
          return '投诉单 - 创建';
        }
        if(this.pageFlag == 2){
          return '投诉单 - 编辑';
        }
        if(this.pageFlag == 3){
          return '投诉单 - 修订';
        }
      }
    },
    methods: {
      load() {
        this.loading = 1;
        
        this.$http.post("/api/complaint/get?complaintId=" + this.complaintId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              this.oriItem = eval('('+JSON.stringify(res.data.data)+')');
              this.oriItem.salesName = this.$args.getArgText('empList',this.oriItem.sales);
              Object.assign(this.formItem,this.oriItem);
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
        Object.assign(this.formItem,{
          complaintId:'',
          contractId:'',
          customerId:'',
          customerName:'',
          sales:'',
          salesName:'',
          address:'',
          createTime:page.formatDate(new Date(),'yyyy-MM-dd'),
          requireRemark:'',
          materId:'',
          materName:'',
          drawing:'',
          casting:'',
          texture:'',
          quantity:'',
          linkMan:'',
          linkPhone:'',
          remark :'',
          amount:0,
          amountRemark:'',
        });
        this.formItem.signDate = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.sales = this.$user.empInfo.empId;
          /*this.formItem.salesName = this.$user.empInfo.trueName;*/
        }
      }, 
      save(){
        var form = {};

        Object.assign(form,this.formItem);
        form.signDate = page.formatDate(form.signDate);

        var pass = true;
        this.$refs.form.validate((valid) => {
          pass = valid;
        }) 
        
        if (!pass) {  
          this.$Message.error('验证未通过！');
          return;
        }

        // 提交
        this.loading = 1;
        var uri = '/api/complaint/add';
        if(this.pageFlag == 2){
          uri = '/api/complaint/update';
        }

        this.$http.post(uri,form).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            this.$Message.info("操作成功！");
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
      },
      goImport(){
        var sel = this.$refs.selContract;
        sel.show({
          ok:(select,mater)=>{ 
            if(select){
              this.formItem.contractId = select.contractId;
              this.formItem.salesName = select.salesName;
              this.formItem.contractNo = select.contractNo;
              this.formItem.deliveryAddress = select.deliveryAddress;
              this.formItem.customerId = select.customerId;
              this.formItem.customerName = select.customerName;
              this.formItem.materId = mater.materId;
              this.formItem.materName = mater.materName;
              this.formItem.drawing = mater.drawing;
              this.formItem.texture = mater.texture;
              this.formItem.quantity = mater.quantity;
            } 
          }
        });
      }
    }
  }

</script>

<style type="text/css">
  .editable-table-container .col-quantity{
    background-color: #cfecc4;
    width: 80px;
  } 

    .editable-table-container .col-amountRemark{
    background-color: #fdc4c1;
    width: 120px;
  }
</style>

