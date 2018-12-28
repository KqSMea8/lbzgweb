<template>
  <div class="page contractview">
    <div class="page-bar">
      <LayoutHor>
        <div slot="left">
          <Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>
        </div>
        <div class="page-bar-title">新材料试用 - 详情</div>
      </LayoutHor>
    </div>
    <Loading :loading="loading">
    <div class="baseinfo">
      <table cellspacing="0" cellpadding="0">
        <tr>
          <td class="label">供应商</td>
          <td>{{formItem.providerName}}</td>
          <td class="label">供应商编码</td>
          <td>{{formItem.providerId}}</td>
          <td class="label">操作日期</td>
          <td>{{formItem.submitTime}}</td>
        </tr>
        <tr>
          <td class="label">地址</td>
          <td>{{formItem.address}}</td>
          <td class="label">联系人</td>
          <td>{{formItem.linkMan}}</td>
          <td class="label">联系电话</td>
          <td>{{formItem.linkPhone}}</td>
        </tr>
        <tr>
          <td class="label">采购员</td>
          <td>{{this.$args.getArgText('empList',formItem.buyer)}}</td>
          <td class="label">操作人</td>
          <td>{{this.$args.getArgText('empList',formItem.submiter)}}</td>
        </tr>
        <tr>
          <td class="label">备注</td>
          <td>{{formItem.remark}}</td>
        </tr> 
      </table>
    </div>
    <div class="subheader">
      物料明细 <!-- 嵌入调用写好的小组件; CraftnewmaterialEditable.vue-->
    </div>
    <CraftnewmaterialEditable :list="list" :editable="true"></CraftnewmaterialEditable> <!-- 物料表单页面 -->
    <br>

    <table class="savebar" cellpadding="0" cellspacing="0">
      <tr>
        <td class="submit" @click="submit">  <!-- 确定 submit-->
          提交
        </td>
        <!-- <td></td> -->
        <!--  <td class="submit" @click="submit">  
          处理
        </td>-->
      </tr>
    </table>
    </Loading>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import CraftnewmaterialEditable from './CraftnewmaterialEditable';
  import dateFormat from '@/assets/js/dateFormat';
  import process from '@/components/process-start';
  import page from '@/assets/js/page';   

  export default {
    filters: {
      formatDate(time) {
        var date = new Date(time);
        return dateFormat(date, 'yyyy-MM-dd');
      }
    },
    components: {
      Loading,
      LayoutHor,
      CraftnewmaterialEditable,//新物料明细的组件
    },
    data() {
      return {
        loading: 0,
        npmId:'',
        status:1,
        formItem:{
          providerName:'',
          providerId:'',
          submitTime:'',
          address:'',
          linkMan:'',
          linkPhone:'',
          buyer:'',
          submiter:'',
          remark:'',
          reportStatus:null,
          wfStatus:null,
          reporter:'',
        },
        list:[],
        oriItem:{},
      }
    },
    mounted: function () {
      this.npmId = this.$route.query.id;//路由返回的 row.npmId值
      if(this.$route.query.status==1 || this.$route.query.status==2){
       this.status = this.$route.query.status;//路由返回的 row.npmId值
       alert("选择的是：  status="+this.status);
      }
      else{
        return;
      }
      this.load();
    },
    computed: {},
    methods: {
      load() {
        this.loading = 1;
        this.$http.get('/api/newProviderMaterial/get?newProviderMaterialId=' + this.npmId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              Object.assign(this.formItem,res.data.data);
              this.list = res.data.data.detailList;
              this.oriItem = res.data.data;
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
      goBack(){ 
        this.$router.go(-1);
      },
   
       //提交按钮 
      submit(){
        var form = {
          detailList:[]//form对象里面的第一个属性detailList数组，第二个属性npmId，一共两个属性
         };

        form.npmId =this.npmId;//1
        alert("提交按钮 this.npmId="+this.npmId);
        Object.assign(form,this.formItem);//2
        form.submitTime = page.formatDate(form.submitTime);

        form.detailList = [];
        // 明细
        for(var i = 0;i<this.list.length;i++){//提交时，进行相关校验
          var item = this.list[i];//3--- 将明细里面的数据压入form 对象中
          form.detailList.push(item);
        }
        // 提交
        this.loading = 1;
        var uri = '/api/newProviderMaterial/report/complete';

        this.$http.post(uri, form).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            this.$Message.info("完成操作成功！");
            this.goBack();
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新操作")
        });
      },

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
  .contractview .baseinfo table .emptytds td{
    height: 0px;padding-right:8px;
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
    width: 60px;
    border: 1px solid #a1e7f8;
    background-color: #a1e7f8;
    color: white;
    text-align: center;
    cursor: pointer;
  }
  .contractview .savebar .submit{
    
    width: 120px;
    border: 1px solid #20bfee;
    background-color: #20bfee;
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
