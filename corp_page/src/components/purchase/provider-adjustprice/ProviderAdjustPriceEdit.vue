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
      </div>
      <div class="subheader">
        调价单
      </div>
      <Form ref="form" class="page-form" :model="formItem" :rules="formRules" :label-width="120"> 
      <table cellspacing="0" cellpadding="0">
        <colgroup>
          <col width="33%"> 
          <col width="auto"> 
          <col width="33%">
        </colgroup>
        <td>
          <FormItem prop="providerName" label="供货商">
            <Input v-model="formItem.providerName" readonly="readonly" style="cursor: default;" icon="search" @on-click="selProvider"></Input>
          </FormItem>
          <FormItem prop="sales" label="操作人">
            <Input readonly="readonly" v-model="formItem.sales"  style="width:120px"></Input>
          </FormItem>
          
            <FormItem prop="createTime" label="操作日期">
              <Input v-model="formItem.createTime" :readonly="true"></Input>
            </FormItem>
          </td>
          <td rowspan="2" style="vertical-align: top;">
            <FormItem prop="remark" label="备注">
              <Input v-model="formItem.remark" type="textarea" :rows="3"></Input>
            </FormItem>
          </td>
        </tr>
      </table>
      </Form>
    </div>
    <div>
      <div class="subheader">
        单据明细
      </div>
      <Editable ref="editable"
        :list="list"
        :editable="true"
        :storageId="formItem.storageId"
        @on-amount-change="onAmountChange"></Editable>
    </div>
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
    </Loading> 
    <SelInStockApply ref="selInStockApply"></SelInStockApply>
    <SelProvider ref="selProvider"></SelProvider>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './DetailEditable'; 
  import page from '@/assets/js/page';
  import floatObj from '@/assets/js/floatObj';  
  import SelStorage from '@/components/storage/input/SelStorage'; 
  import SelStockOperType from '@/components/storage/input/SelStockOperType'; 
  import SelDeptInput from '@/components/selcontacts/SelDeptInput'; 
  import SelProvider from '@/components/provider/SelectProvider'; //导入选供应商的控件
  import SelPersonInput from '@/components/selcontacts/SelPersonInput'; 
  import SelInStockApply from '@/components/purchase/purchase-order-mgr/SelInStockApply'; 
  export default {
    components: {
      Loading,
      LayoutHor,
      Editable, 
      SelStorage,
      SelProvider,
      SelStockOperType,
      SelDeptInput,
      SelPersonInput,
      SelInStockApply
    },
    data() {
      return {
        loading: 0,
        pmapId:'',
        pageFlag:1,//1.新建 2.编辑 3.修订
        formItem:{
          providerName:'',
          pmapId:'',
          materId:'',
          materName:'',
          spec:'',
          model:'',
          subType:'',
          use:'',
          price:'',
          afterPrice:'',
          remark:'',
          createTime:page.formatDate(new Date(),'yyyy-MM-dd'),

        },
        formRules: {
          providerId: [
            {required: true, whitespace: true, message: '请选择供应商', trigger: 'change'},
          ],
        },
        list:[],  
        oriItem:{},
        storage:[]
      }
    },
    mounted: function () { 
      this.pmapId = this.$route.query.id;
      if(this.pmapId){
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
          return '调价单 - 创建';
        }
        if(this.pageFlag == 2){
          return '调价单 - 编辑';
        }
      }
    },
    methods: {
      load() {
        this.loading = 1;
        this.$http.post("/api/providerAdjustPrice/get?pmapId=" + this.pmapId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              this.oriItem = eval('('+JSON.stringify(res.data.data)+')');
              Object.assign(this.formItem,res.data.data);
              this.list = res.data.data.detailList;
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
          pmapId:'',
          materId:'',
          materName:'',
          spec:'',
          model:'',
          subType:'',
          use:'',
          price:'',
          afterPrice:'',
          remark:'',
          createTime:page.formatDate(new Date(),'yyyy-MM-dd'),

        });
        this.formItem.signDate = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
         this.formItem.sales = this.$user.empInfo.empId;
          this.formItem.sales = this.$user.empInfo.trueName;
        }
        this.list = [];
        this.list.push(this.$refs.editable.listNewRow());
        this.list.push(this.$refs.editable.listNewRow());
      }, 
      save(){
        var form = {
          detailList:[]
        };

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

        form.detailList = [];
        // 明细
        for(var i = 0;i<this.list.length;i++){
          var item = this.list[i];
          var msg = '明细第 ' + (i+1) + ' 行，';
          if(item.materId != ''){
            if(item.afterPrice == ''){
              this.$Message.error(msg + '请录入修改后价格');
              return;
            }
            // 日期格式转换
            item.madeDate = page.formatDate(item.madeDate);
            item.arrivalDate = page.formatDate(item.arrivalDate);
            item.validityDate = page.formatDate(item.validityDate);
            form.detailList.push(item);
          }
        }
        // 提交
        this.loading = 1;
        var uri = '/api/providerAdjustPrice/add';
        if(this.pageFlag == 2){
          uri = '/api/providerAdjustPrice/update';
        }

        this.$http.post(uri, form).then((res) => {
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
      datePickerChange(val){
        this.formItem.signDate = val;
      },
      switStorage(){

      },
      reset(){
        if(this.pageFlag == 1){
          this.initNew();
        }else {
          Object.assign(this.formItem,this.oriItem);
        }
      },
      selProvider(){
       var sel = this.$refs.selProvider;//引用该控件，赋值给变量对象
       sel.show({
        ok:(data)=>{
          if(data){
            this.formItem.providerName = data.providerName;
            this.formItem.providerId = data.providerId;
            this.formItem.linkMan = data.linkMan;
            this.formItem.linkPhone = data.linkPhone;
            this.formItem.address = data.address;
          }
        }
       });
      },
      goBack(){
        this.$router.push('/provider-adjustprice');
      },
      goImport(){
        var sel = this.$refs.selInStockApply;
        sel.show({
          isMulti:false,
          selectPerson:false,
          selectDept:true,
          ok:(select)=>{ 
            if(select){
              this.formItem.relatedBillId = select.relatedBillId;
              this.formItem.relatedBillId1 = select.relatedBillId1;
              
            }
          }
        });
      }
    }
  }

</script>

