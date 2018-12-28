<template>
  <div class="page contractview"> 
    <div class="page-bar">
      <LayoutHor>
        <div slot="left">
          <Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>
        </div>
        <div class="page-bar-title">待转合同转档</div>
      </LayoutHor>
    </div>
    <Loading :loading="loading">
    <div class="baseinfo">
      <div class="subheader">
        单据表头
      </div>
      <table cellspacing="0" cellpadding="0">
        <tr>
          <td class="label">合同编号</td>
          <td>{{formItem.contractNo}}</td>
          <td class="label">客户</td>
          <td>{{formItem.customerName}}</td>
          <td></td>
          <td> 
            <Tag v-if="formItem.hasProcedure==1" color="green">含加工工序</Tag>
            <Tag v-else>含加工工序</Tag>
            <Tag v-if="formItem.isForeign==1" color="green">是否外销</Tag>
            <Tag v-else>是否外销</Tag>
          </td> 
        </tr>
        <tr>
          <td class="label">业务员</td>
          <td>{{formItem.salesName}}</td> 
          <td class="label">签订日期</td>
          <td>{{formItem.signDate}}</td>
          <td class="label">签订地点</td>
          <td>{{formItem.signAddress}}</td> 
        </tr>
        <tr>
          <td class="label">合同金额</td>
          <td style="color:red">{{formItem.amount}}</td>
          <td class="label">币种</td>
          <td>{{formItem.currencyName}}</td> 
          <td class="label">交货地点</td>
          <td>{{formItem.deliveryAddress}}</td>
        </tr>
        <tr>
          <td class="label">附件</td>
          <td></td>
          <td class="label">备注</td>
          <td>{{formItem.remark}}</td> 
        </tr> 
      </table> 
    </div>   
    <div class="subheader">
      单据明细
    </div> 
    
    <Editable ref="editable" v-for="(item,index) in materials"
        :title="'物料 ' + (index+1)" :material="item" :editable="true"></Editable>
    <table class="billfooter" cellpadding="0" cellspacing="0">
      <tr>
        <td class="label">工艺员</td>
        <td width="100">{{formItem.designerName}}</td>
        <td class="label">预算日期</td>
        <td width="100">{{formItem.completeTime}}</td>
      </tr> 
    </table>  
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
   
  </div> 
</template>
<script> 
  import Loading from '@/components/loading'; 
  import LayoutHor from '@/components/layout/LayoutHor';
  import Editable from './ProjectDetailCard'; //直接调用当前目录下的单据明细中的table表格 ProjectDetailCard.vue
  import SelectCustomer from '../customer/SelectCustomer';
  import dateFormat from '@/assets/js/dateFormat';
  import process from '@/components/process-start';


  export default {
    components: { 
      Loading, 
      LayoutHor,
      Editable,
      SelectCustomer,
    },
    data() { 
      return {
        loading: 0,
        contractId:'',
        formItem:{ 
          contractId:'',
          customerId:'',
          customerName:'',
          inDate:'',
          isInquire:0,
          hasProcedure:0,
          isForeign:0,
          isCraftDesign:0,
          sales:'',
          salesName:'',
          remark:'',
          designer:'',
          completeTime:'',
        },
        materials:[], 
        list:[],
        unitArgs:[],
        oriItem:{},
        isNew:true,
      }
    },
    mounted: function () {  
      this.contractId = this.$route.query.id;
      this.load();
    },
    computed: {},
    methods: { 
      load() {
        this.loading = 1;
        this.$http.post("/api/contract/get?contractId=" + this.contractId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) { 
            if(res.data.data){
              Object.assign(this.formItem,res.data.data);
              this.list = res.data.data.detailList;
              this.oriItem = res.data.data;
              this.materials = res.data.data.detailList;
            }else{
              this.$Message.error('询价单不存在！');
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
        //var details = [];
        this.$refs.editable.map((mater)=>{
          mater.material.fieldJson = 
          JSON.stringify(mater.fields);
        });

        this.oriItem.detailList = this.materials;
        this.oriItem.designer = this.formItem.designer;
        this.oriItem.completeTime = this.formItem.completeTime;

        this.loading = 1; 
        this.$http.post("/api/contractProject/update",this.oriItem).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) { 
            this.$Message.success('操作成功!');
            this.goBack();
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("操作失败！")
        });
      },
      goEdit(){
        this.$router.push({path:'/contract/edit?id='+ this.contractId });
      },
      ok(){
        this.goBack();
      },
      reset(){
        this.load();
      }, 
      goBack(){
        this.$router.go(-1);
      }
    }
  }

</script>

<style type="text/css"> 
  .contractview.page{
    width: 900px;margin: 0 auto;
    padding: 10px 0px;
    position: relative;
  } 
  .contractview .subheader{
    height: 34px;line-height: 34px; 
    font-size:14px;
    border-bottom: 0px solid #efefef;
    margin-bottom: 10px;
    color:#20bfee;
  }
  .contractview .baseinfo{
     font-size: 14px;
  }
  .contractview .baseinfo .label{
    width: 80px;text-align: right;
    color:#999;
  }
  .contractview .baseinfo table{
    width: 100%;
  }
  .contractview .baseinfo table td{
    height: 40px;padding-right:8px;
  }

  .contractview .savebar{
    margin-top: 10px;
    height: 40px; 
    width: 100%;
    border-collapse: collapse; 
  }
  .contractview .savebar td{
    border: 1px solid #fefefe;
    font-size: 14px;
  } 
  .contractview .savebar .save{
    width: 120px; 
    border: 1px solid #20bfee;
    background-color: #20bfee;
    color: white;
    text-align: center;
    cursor: pointer; 
  }
  .contractview .savebar .reset{
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

  .contractview .tooltip{
    padding:10px;
    background-color: #fafafa;
    border:1px solid #efefef;
    border-radius: 3px;
    color:#666; 
    margin-top: 10px;
  }

  .contractview .billfooter{
    margin-top: 10px;
    font-size: 14px; 
    float: right;
  }
  .contractview .billfooter td{
    line-height: 40px;
    padding-right: 10px;
  }
  .contractview .billfooter .label{
     width: 80px;
  }

  .contractview .flowinfo{
    font-size: 14px;
  }
  .contractview .flowinfo td,.contractview .flowinfo th{
    padding:4px 10px;
  }
</style>
