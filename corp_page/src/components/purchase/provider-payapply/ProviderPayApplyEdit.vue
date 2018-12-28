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
      <Form ref="form" class="page-form" :model="formItem" :rules="formRules" :label-width="120">
      <table cellspacing="0" cellpadding="0">
        <colgroup>
          <col width="33%"> 
          <col width="auto"> 
          <col width="33%">
        </colgroup>
        <td>
          <FormItem prop="providerName" label="供应商">
            <Input v-model="formItem.providerName" readonly="readonly" style="cursor: default;" icon="search" @on-click="selProvider" :disabled="pageFlag == 2"></Input>
          </FormItem>
          <FormItem prop="providerId" label="供应商编码">
            <Input v-model="formItem.providerId" :disabled="pageFlag == 2"></Input>
          </FormItem>
          <FormItem prop="amount" label="申请费用">
            <InputNumber :max="9999999999" :min="0" v-model="formItem.amount"></InputNumber>
          </FormItem>     
        </td>
        <td >
          <FormItem prop="orderId" label="采购合同编号">
            <Input v-model="formItem.orderId"></Input>
          </FormItem>  
          <FormItem prop="files" label="附件">
            <UploadBox v-model="formItem.files"></UploadBox>
          </FormItem>
        </td>     
        <td>
          <FormItem prop="proposer" label="申请人">
            <Input v-model="formItem.proposer"></Input>
          </FormItem>
          <FormItem prop="createTime" label="操作日期">
            <DatePicker type="date" readonly="readonly" placeholder="" v-model="formItem.createTime" format="yyyy-MM-dd" style="width:120px"></DatePicker>
          </FormItem>

          <FormItem prop="remark" label="备注">
            <Input v-model="formItem.remark"></Input>
          </FormItem>
        </td>
      </table>
    </Form>
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
    <SelProvider ref="selProvider"></SelProvider><!-- 选择供应商控件声明,  加入该控件-->
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import page from '@/assets/js/page';
  import floatObj from '@/assets/js/floatObj';
  import SelProvider from '@/components/provider/SelectProvider'; //导入选供应商的控件
  import UploadBox from '@/components/upload/Index';

  export default {
    components: {
      Loading,
      LayoutHor,
      SelProvider, 
      UploadBox //声明新组件
    },
    data() {
      return {
        loading: 0,
        pageFlag:1,//1.新建 2.编辑
        formItem:{
          paId:'',
          providerId:'',
          providerName:'',
          amount:0,
          orderId:'',
          files:'',
          proposer:'',
          remark:'',
          status:'',
          creator:'',
          wfInstId:"",
          wfInstTime:"",
          wfInstRemark:null,
          createTime:page.formatDate(new Date(),'yyyy-MM-dd'),
        },
        formRules: {
          providerId: [
            {required: true, whitespace: true, message: '请选择供应商', trigger: 'change'},
          ],
        },
        list:[],
        unitArgs:[],
        currencyArgs:[],
        oriItem:{},
        remark:'',
      }
    },
    mounted: function () { 
      this.paId = this.$route.query.id;
      if(this.paId){
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
          return '付款单 - 创建';
        }
        if(this.pageFlag == 2){
          return '付款单 - 编辑';
        }
      }
    },
    methods: {
      load() {
        this.loading = 1;//
        this.$http.post("/api/providerPayApply/get?paId=" + this.paId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              this.oriItem = eval('('+JSON.stringify(res.data.data)+')');
              Object.assign(this.formItem,res.data.data);
              this.list = res.data.data.detailList;
            }else{
              this.$Message.error('新材料单不存在！');
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

      loadArgs(){
        this.loadArg('price',(err,data)=>{
          if(err){
            this.$Message.error('加载参数出错：'+err);
          }else{
            this.currencyArgs = data;
          }
        });
        this.loadArg('unit',(err,data)=>{
          if(err){
            this.$Message.error('加载参数出错：'+err);
          }else{
            this.unitArgs = data;
          }
        });
      },
      loadArg(group,callback){
        this.$http.post("/api/providerPayApply/list", {argGroup:group}).then((res) => {
          if (res.data.code === 0) {
            callback(null,res.data.data);
          } else {
            callback(res.data.message);
          }
        }).catch((error) => {
          callback("请求失败!");
        });
      },

      initNew(){//新材料 - 创建
        Object.assign(this.formItem,{
          providerId:'',
          providerName:'',
          amount:0,
          orderId:'',
          files:'',
          proposer:'',
          remark:'',
          status:'',
          creator:'',
          wfInstId:"",
          wfInstTime:"",
          wfInstRemark:null,
          createTime:page.formatDate(new Date(),'yyyy-MM-dd'),
        });
        this.formItem.createTime = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.proposer = this.$user.empInfo.empId;
          this.formItem.proposer = this.$user.empInfo.trueName;
        }
        this.list = [];

      },

      save(){// 提交
        var form = {
          detailList:[]
        };

        Object.assign(form,this.formItem);
        form.createTime = page.formatDate(form.createTime);

        // 请选择供应商
        if(form.providerId == null || form.providerId == ''){
          this.$Message.error('请选择供应商！');
          return;
        }

        // 提交
        this.loading = 1;
        var uri = '/api/providerPayApply/add';
        if(this.pageFlag == 2){
          uri = '/api/providerPayApply/update';
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

      onDrawingInput(item){//
        //请选择供应商
        if(this.formItem.providerId == null || this.formItem.providerId == ''){
          this.$Message.error('请选择供应商！');
          return;
        }
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
        this.$router.push('/provider-payapply');
      },

      //选择供应商的调用实现方法
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
    }
  }
</script>

