<template>
  <div class="page page-bill">
    <div class="page-bar">
      <LayoutHor>
        <div slot="left">
          <Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>
        </div>
        <div class="page-bar-title">询价单 - 创建</div>
      </LayoutHor>
    </div>
    <Loading :loading="loading">
    <div class="baseinfo">
      <div class="subheader">
        单据表头
      </div>
      <Row>
        <Col span="14">
          <div class="">
            <table cellspacing="0" cellpadding="0">
              <tr>
                <td class="label">询价单编号</td>
                <td><Input v-model="formItem.inquireId" readonly="readonly"></Input></td>
                <td class="label">录入日期</td>
                <td>
                  <DatePicker v-if="false" type="date" placeholder="" v-model="formItem.inDate" format="yyyy-MM-dd" @on-change="datePickerChange"></DatePicker>
                  <Input v-model="formItem.inDate" readonly="readonly"></Input>
                </td>
              </tr>
              <tr>
                <td class="label">客户</td>
                <td>
                  <Input v-model="formItem.customerName" readonly="readonly" icon="android-search" class="selectinput" @on-click="onSelectCustomer">
                  </Input>
                </td>
                <td class="label">业务员</td>
                <td><Input readonly="readonly" v-model="formItem.salesName"></Input></td>
              </tr>
              <tr>
                <td class="label">备注</td>
                <td colspan="3"><Input v-model="formItem.remark"></Input></td>
              </tr>
            </table>
          </div>
        </Col>
        <Col span="10">
          <table >
            <tr><td>
              <Checkbox v-model="formItem.isInquire"
                :true-value="1" :false-value="0">需求询价</Checkbox>
              &nbsp;
              <Checkbox v-model="formItem.hasProcedure"
                :true-value="1" :false-value="0">含加工工序</Checkbox>
              &nbsp;
              <Checkbox v-model="formItem.isForeign"
                :true-value="1" :false-value="0">是否外销</Checkbox>
              &nbsp;
              <Checkbox v-model="formItem.isCraftDesign"
                :true-value="1" :false-value="0">进行工艺设计</Checkbox>
            </td></tr>
          
          </table>
        </Col>
      </Row>
    </div>
    <div>
      <div class="subheader">
        单据明细
      </div>
      <Editable ref="editable"
        :list="list"
        :unitArgs="unitArgs"
        :processRequireArgs="processRequireArgs"
        @on-drawing-input="onDrawingInput"></Editable>
    </div>
    <div class="tooltip">
      * 在录入单据明细之前，请录入单据中的客户信息<br/>
      * 单据明细中的图号是唯一标识，录入图号光标离开后，自动加载物料数据

    </div>
    <table class="savebar" cellpadding="0" cellspacing="0">
      <tr>
        <td class="save" @click="save">
          提交
        </td>
        <td class="reset" @click="reset">
          重置
        </td>
        <td></td>
      </tr>
    </table>
    </Loading>
    <SelectCustomer ref="selectCustomer"></SelectCustomer>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './InquireDetailEditable';
  import SelectCustomer from '@/components/customer/SelectCustomer';
  import dateFormat from '@/assets/js/dateFormat';
  import UploadBox from '@/components/upload/Index';

  export default {
    components: {
      Loading,
      LayoutHor,
      Editable,
      SelectCustomer,
      UploadBox
    },
    data() {
      return {
        loading: 0,
        formItem:{
          inquireId:'',
          customerId:'',
          customerName:'',
          inDate:'',
          isInquire:1,
          hasProcedure:0,
          isForeign:0,
          isCraftDesign:0,
          sales:'',
          salesName:'',
          remark:''
        },
        list:[],
        unitArgs:[],
        processRequireArgs:[],
        oriItem:{},
        isNew:true,
      }
    },
    mounted: function () {
      this.loadUnit();
      this.loadArgs(); //调用交货类型
      this.initNew();
    },
    computed: {},
    methods: {
      load() {

      },
      loadUnit() {
        this.loading = 1;
        //查询角色列表数据
        this.$http.post("/api/arg/list", {argGroup:'unit'}).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.unitArgs = res.data.data;
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新发送")
        });
      },
      loadArgs(){
        this.loadArg('process_require',(err,data)=>{ //调用loadArg(*,*)方法
          if(err){
            this.$Message.error('加载参数出错：'+err);
          }else{
            this.processRequireArgs = data;
          }
        });
      },
       loadArg(group,callback){//关键调用此方法获取绑定参数,获取后台对应的api返回的参数值
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
      initNew(){
        Object.assign(this.formItem,{
          inquireId:'',
          customerId:'',
          customerName:'',
          inDate:'',
          isInquire:1,
          hasProcedure:0,
          isForeign:0,
          isCraftDesign:0,
          sales:'',
          salesName:'',
          remark:''
        });
        this.formItem.inDate = dateFormat(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.sales = this.$user.empInfo.empId;
          this.formItem.salesName = this.$user.empInfo.trueName;
        }
        this.list = [];
        this.list.push(this.$refs.editable.listNewRow());
        this.list.push(this.$refs.editable.listNewRow());
        this.list.push(this.$refs.editable.listNewRow());
        this.list.push(this.$refs.editable.listNewRow());
        this.list.push(this.$refs.editable.listNewRow());
      },
      save(){
        var form = {
          detailList:[]
        };

        Object.assign(form,this.formItem);

        // 表头信息
        if(form.customerId == null || form.customerId == ''){
          this.$Message.error('请选择客户！');
          return;
        }

        // 明细
        for(var i = 0;i<this.list.length;i++){
          var item = this.list[i];
          if(item.drawing != ''){
            if(item.materName =='' || item.materName == null){
              this.$Message.error('图号 ' + item.drawing + ' ,物料名称不能为空！');
              return;
            }
            if(item.unit =='' || item.unit == null){
              this.$Message.error('图号 ' + item.drawing + ' ,单位不能为空！');
              return;
            }
            if(item.deliveryDate == ''){
              item.deliveryDate = null;
            }
            form.detailList.push(item);
          }
        }

        // 提交
        this.loading = 1;
        this.$http.post("/api/inquire/add", form).then((res) => {
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
      onSelectCustomer(){
        this.$refs.selectCustomer.open();
        this.$refs.selectCustomer.$off('on-ok');
        this.$refs.selectCustomer.$on('on-ok',(items)=>{
          if(items.length > 0){
            this.formItem.customerId = items[0].customerId;
            this.formItem.customerName = items[0].customerName;
          }
        });
      },
      onDrawingInput(item){
        if(this.formItem.customerId == null || this.formItem.customerId == ''){
          this.$Message.error('请选择客户！');
          return;
        }
        var materId = this.formItem.customerId + '-' + item.drawing;

        this.$http.post('/api/material/get?materId=' +materId, {}).then((res) => {
          if (res.data.code === 0 && res.data.data !=null) {
            item.materName = res.data.data.materName;
            item.unit = res.data.data.unit;
          }
        }).catch((error) => {
          this.$Message.error(error.message)
        });
      },
      datePickerChange(val){
        this.formItem.inDate = val;
      },
      reset(){
        if(this.isNew){
          this.initNew();
        }else{

        }
      },
      goBack(){
        this.$router.push('/inquire');
      }
    }
  }

</script>

