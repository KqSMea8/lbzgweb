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
              <Button @click="goImport">导入销售发货单</Button>
            </td>
            <td>
              <Input placeholder="条码"></Input>
            </td>
          </tr>
        </table> 
      </div>
      <div class="subheader">
        装箱单
      </div>
      <Form ref="form" class="page-form" :model="formItem" :rules="formRules" :label-width="120"> 
      <table cellspacing="0" cellpadding="0">
        <colgroup>
          <col width="50%">
          <col width="50%">
        </colgroup>
        <tr>
          <td>
            <FormItem prop="customerId" label="装箱单号">
              <Input v-model="formItem.packId" ></Input>
            </FormItem>
          </td>
          <td>
            <FormItem prop="createTime" label="单据日期">
              <DatePicker type="date" placeholder="" 
                v-model="formItem.createTime" format="yyyy-MM-dd"  
                :disabled="pageFlag!=1" :readonly="pageFlag!=1" 
                @on-change="formItem.createTimes=arguments[0]"
                style="width:120px"></DatePicker>
            </FormItem>
          </td>
        </tr>
        <tr>
          <td>
            <FormItem prop="operator" label="操作人">
              <SelPersonInput v-model="formItem.operator" :text="formItem.operatorName" bindText="operatorName" :model="formItem"></SelPersonInput>
            </FormItem>
          </td>
          <td>
            <FormItem prop="deptName" label="操作车间">
              <SelDeptInput v-model="formItem.department" :text="formItem.departmentName" bindText="departmentName" :model="formItem"></SelDeptInput>
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
        :customerId="formItem.id"
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
    <SelShipping ref="selShipping"></SelShipping>
    <SelectContract ref="selcontact" ></SelectContract>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './DetailEditable'; 
  import page from '@/assets/js/page';
  import floatObj from '@/assets/js/floatObj';
  import SelStockOperType from '@/components/storage/input/SelStockOperType'; 
  import SelDeptInput from '@/components/selcontacts/SelDeptInput'; 
  import SelPersonInput from '@/components/selcontacts/SelPersonInput'; 
  import SelShipping from '@/components/sales/shipping/SelShipping'; 
  import pagejs from '@/assets/js/page';
  import SelectContract from '@/components/sales/contract/SelectContract';

  export default {
    components: {
      Loading,
      LayoutHor,
      Editable,
      SelStockOperType,
      SelDeptInput,
      SelPersonInput,
      SelShipping,
      SelectContract
    },
    data() {
      return {
        loading: 0,
        packId:'',
        pageFlag:1,//1.新建 2.编辑 3.修订
        formItem:{
          packId:'',
          storageId:100,
          customerId:'',
          customerName:'',
          contractId:'',
          operId:'',
          operType:2,
          operSource:1,
          createTime:page.formatDate(new Date(),'yyyy-MM-dd'),
          proposer:'',
          proposerName:'',
          operator :'',
          operatorName :'',
          department:'',
          receiver:'',
          receiverName:'',
          shipper:'',
          shipperName:'',
          inspector:'',
          inspectorName:'',
          amount:0,
          remark:'',
          status:'', 
          weight:'',
          deliveryAddress:'',
          department:'',
          departmentName:'',
        },
        formRules:{
          operId: [
            { required: true, message: '请选择操作原因' }
          ],
        },
        list:[],  
        oriItem:{},
      }
    },
    mounted: function () {
      this.packId = this.$route.query.packId;
      if(this.packId){
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
          return '装箱单 - 创建';
        }
        if(this.pageFlag == 2){
          return '装箱单 - 编辑';
        }
        if(this.pageFlag == 3){
          return '装箱单 - 修订';
        }
      }
    },
    methods: {
      load() {
        this.loading = 1;
        this.$http.post("/api/pack/get?packId=" + this.packId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              this.oriItem = eval('('+JSON.stringify(res.data.data)+')');
              Object.assign(this.formItem,res.data.data);
              this.list = res.data.data.detailList;

              this.formItem.departmentName = this.$args.getArgText('deptList',res.data.data.department);
              this.formItem.operatorName = this.$args.getArgText('empList',res.data.data.operator);

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
          packId:'',
          storageId:100,
          customerId:'',
          customerName:'',
          contractId:'',
          contractNo:'',
          operType:2,
          operSource:1,
          createTime:page.formatDate(new Date(),'yyyy-MM-dd'),
          proposer:'',
          proposerName:'',
          operator :'',
          operatorName :'',
          department:'',
          receiver:'',
          receiverName:'',
          shipper:'',
          shipperName:'',
          inspector:'',
          inspectorName:'',
          amount:0,
          remark:'',
          status:'', 
          weight:'',
          deliveryAddress:'',
          department:'',
          departmentName:'',
        });

        this.formItem.signDate = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.operator = this.$user.empInfo.empId;
          this.formItem.operatorName = this.$user.empInfo.trueName;
          this.formItem.department = this.$user.empInfo.deptId;
          this.formItem.departmentName = this.$user.empInfo.deptName;
          this.formItem.receiver = this.$user.empInfo.empId;
          this.formItem.receiverName = this.$user.empInfo.trueName;
        }
        if(this.$refs.editable){
          this.list = [];
          this.list.push(this.$refs.editable.listNewRow());
          this.list.push(this.$refs.editable.listNewRow());
        }
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
          /*if(item.materId != ''){
            if(item.quantity == 0){
              this.$Message.error(msg + '请录入数量');
              return;
            }
            if(item.placeId == ''){
              this.$Message.error(msg + '请录入货位');
              return;
            }
            // 日期格式转换
            item.madeDate = page.formatDate(item.madeDate);
            item.arrivalDate = page.formatDate(item.arrivalDate);
            item.validityDate = page.formatDate(item.validityDate);
            form.detailList.push(item);
          }*/
          item.madeDate = page.formatDate(item.madeDate);
          item.arrivalDate = page.formatDate(item.arrivalDate);
          item.validityDate = page.formatDate(item.validityDate);
          form.detailList.push(item);
        }

        form.createTime = pagejs.formatDate(form.createTime);

        // 提交
        this.loading = 1;
        var uri = '/api/pack/add';
        if(this.pageFlag == 2){
          uri = '/api/pack/update';
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
      reset(){
        if(this.pageFlag == 1){
          this.initNew();
        }else {
          Object.assign(this.formItem,this.oriItem);
        }
      },
      onSelectContract(){
        this.$refs.selcontact.open();
        this.$refs.selcontact.$off('on-ok');
        this.$refs.selcontact.$on('on-ok',(items)=>{
          if(items.length > 0){
            this.formItem.contractId = items[0].contractId;
            this.formItem.contractNo = items[0].contractNo;
            this.formItem.customerId = items[0].customerId;
            this.formItem.customerName = items[0].customerName;
            this.formItem.deliveryAddress = items[0].deliveryAddress;
            this.formItem.proposerName = items[0].salesName;
          }
        });
      },
      goBack(){
        this.$router.push('/pack');
      },
      goImport(){
        var newRow = this.$refs.editable.listNewRow();
        this.list.push(Object.assign(newRow,{
          materId:'QQ',
          materName:'球球',
          contractId:'HT-031',
          drawing:'TH-092',
          casting:'ZZH-052',
          barcode:'TH-902',
          quantity:'13',
        },{id:null}));
        // var sel = this.$refs.selShipping;
        // sel.show({
        //   ok:(select)=>{ 
        //     if(select){
        //       this.formItem.shippingId = select.shippingId;
        //       this.formItem.contractNo = select.contractNo;
        //       this.formItem.deliveryAddress = select.deliveryAddress;
        //       this.formItem.customerName = select.customerName;
        //       this.list = [];
        //       select.detailList.map((row)=>{ 
        //         var newRow = this.$refs.editable.listNewRow();
        //         this.list.push(Object.assign(newRow,row,{id:null}));
        //       });
        //     } 
        //   }
        // });
      }
    }
  }

</script>