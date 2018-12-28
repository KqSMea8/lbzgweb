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
      <table cellspacing="0" cellpadding="0">
        <tr>
          <td class="label">供应商</td>
          <td>
            <Input v-model="formItem.providerName" readonly="readonly" style="cursor: default;" icon="search" @on-click="selProvider"></Input>
          </td>
          <td class="label">供应商编码</td>
          <td><Input v-model="formItem.providerId"></Input></td>
          <td class="label">操作日期</td>
          <td><DatePicker type="date" readonly="readonly" placeholder="" v-model="formItem.submitTime" format="yyyy-MM-dd" style="width:120px"></DatePicker></td>
        </tr>
        <tr>
          <td class="label">地址</td>
          <td><Input v-model="formItem.address"></Input></td>
          <td class="label">联系人</td>
          <td>
            <Input readonly="readonly" v-model="formItem.linkMan"  placeholder="" class="width-1" />
          </td>
          <td class="label">联系电话</td>
          <td><Input  v-model="formItem.linkPhone"  style="width:120px"></Input></td>
        </tr>
        <tr>
          <td class="label">采购员</td>
          <td>
            <Input readonly="readonly" v-model="formItem.buyerName"  @on-click="selSelectman" placeholder="" class="width-1"   icon="search" />
          </td>
          <td class="label">操作人</td>
          <td>
            <Input readonly="readonly" v-model="formItem.submiter"  style="width:120px"></Input>
          </td>
        </tr>
        <tr>
          <td class="label">备注</td>
          <td colspan="3"><Input v-model="formItem.remark"></Input></td>
        </tr>
      </table>
    </div>
    <div>
      <div class="subheader"><!-- 调用 ProviderNewMatEditable.vue -->
        物料明细
      </div>
      <Editable ref="editable"
        :list="list"
        :unitArgs="unitArgs"
        :editable="true"
        @on-amount-change="onAmountChange"
        @on-npmId-input="onDrawingInput"></Editable>
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
    <SelContacts ref="selContacts"></SelContacts> <!-- 新加部门人员控件 -->
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './ProviderNewMatEditable';//新建页面
  import page from '@/assets/js/page';
  import floatObj from '@/assets/js/floatObj';
  import SelProvider from '@/components/provider/SelectProvider'; //导入选供应商的控件
  import SelContacts from '@/components/selcontacts'; //选择部门和人员----已经写好的控件，供项目公用

  export default {
    components: {
      Loading,
      LayoutHor,
      Editable,
      SelProvider,  //声明新组件
      SelContacts,  //声明新组件
    },
    data() {
      return {
        loading: 0,
        pageFlag:1,//1.新建 2.编辑
        formItem:{
          linkMan:'',
          linkManName:'',
          buyer:'',
          buyerName:'',
          linkPhone:'',
          submiter:'',
          providerId:'',
          submitTime:null,
          address:'',
          remark:'',
        },
        list:[],
        unitArgs:[],
        currencyArgs:[],
        oriItem:{},
        remark:'',
      }
    },
    mounted: function () {
      this.loadArgs();
      this.providerId = this.$route.query.id;
      this.pageFlag = 1;
      if(this.providerId){
        this.pageFlag = 2;
      }
      if(this.pageFlag == 1){
        this.initNew();
      }
      if(this.pageFlag == 2){
        this.load();
      }
    },
    computed: {
      pageTitle(){
        if(this.pageFlag == 1){
          return '新材料 - 创建';
        }
        if(this.pageFlag == 2){
          return '新材料 - 编辑';
        }
      }
    },
    methods: {
      load() {
        this.loading = 1;//
        this.$http.post("/api/newProviderMaterial/get?newProviderMaterialId=" + this.providerId,{}).then((res) => {
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
        this.$http.post("/api/arg/list", {argGroup:group}).then((res) => {
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
          linkMan:'',
          linkManName:'',
          buyer:'',
          buyerName:'',
          linkPhone:'',
          submiter:'',
          providerId:'',
          // submitTime:null,
          address:'',
          remark:'',
          submitTime:page.formatDate(new Date(),'yyyy-MM-dd'),
        });
        this.formItem.submitTime = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.sales = this.$user.empInfo.empId;
          this.formItem.submiter = this.$user.empInfo.trueName;
        }
        this.list = [];
        this.list.push(this.$refs.editable.listNewRow());
      },

      save(){// 提交
        var form = {
          detailList:[]
        };

        Object.assign(form,this.formItem);
        form.submitTime = page.formatDate(form.submitTime);

        // 请选择供应商
        if(form.providerId == null || form.providerId == ''){
          this.$Message.error('请选择供应商！');
          return;
        }

        form.detailList = [];
        // 明细
        for(var i = 0;i<this.list.length;i++){//提交时，进行相关校验
          var item = this.list[i];
          if(item.npmId != ''){
            if(item.materName =='' || item.materName == null){
              this.$Message.error('物料代码 ' + item.materId + ' ,物料名称不能为空！');
              return;
            }
            if(item.unit =='' || item.unit == null){
              this.$Message.error('物料代码 ' + item.materId + ' ,单位不能为空！');
              return;
            }
            item.deliveryDate = page.formatDate(item.deliveryDate);
            form.detailList.push(item);
          }
        }

        // 提交
        this.loading = 1;
        var uri = '/api/newProviderMaterial/add';
        if(this.pageFlag == 2){
          uri = '/api/newProviderMaterial/update';
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

      goBack(){//在 Vue 实例内部，你可以通过 $router 访问路由实例,因此你可以调用 this.$router.push。
        this.$router.push('/provider-newmaterial?reportStatus=1'); // 用来导向特殊页面。返回列表界面
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

       //选择采购员
      selSelectman(){
        var sel = this.$refs.selContacts;//引用该控件
        sel.show({
          isMulti:false,
          selectPerson:true,
          selectDept:false,
        ok:()=>{
         if(sel.select.length>0){
            this.formItem.buyer = sel.select[0].key;//
            this.formItem.buyerName = sel.select[0].title;//
          }
         }
        });
      },

    }
  }
</script>

