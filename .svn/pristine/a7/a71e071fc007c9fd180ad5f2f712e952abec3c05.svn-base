<template>
  <div class="page inquireedit">
    <div class="page-bar">
      <LayoutHor>
        <div slot="left">
          <Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>
        </div>
        <div class="page-bar-title">{{ typeName }}</div>
      </LayoutHor>
    </div>
    <Loading :loading="loading">
      <div class="baseinfo">
        <div class="subheader">
        </div>
        <Row>
          <Col span="14">
            <div class="">
              <table cellspacing="0" cellpadding="0">
                <tr>
                    <td class="label">物料编码</td>
                    <td>
                        <Input v-model="formItem.materId" readonly="readonly" icon="android-search" class="selectinput" @on-click="onSelectCustomer"></Input>
                    </td>
                    <td class="label">计量器具名称</td>
                    <td><Input v-model="formItem.counterName" readonly="readonly"></Input></td>
                </tr>
                  <tr>
                      <td class="label">规格等级</td>
                      <td><Input v-model="formItem.spec" readonly="readonly"></Input></td>
                      <td class="label">型号</td>
                      <td><Input v-model="formItem.model" readonly="readonly"></Input></td>
                  </tr>
                  <tr>
                      <td class="label">生产日期</td>
                      <td><Input v-model="formItem.createTime" readonly="readonly"></Input></td>
                      <td class="label">质保期</td>
                      <td><Input v-model="formItem.qualityTime" readonly="readonly"></Input></td>
                  </tr>

                  <tr>
                      <td class="label" v-show="getTime_show">领用日期</td>
                      <DatePicker v-show="getTime_show" type="date" placeholder="" readonly="readonly" v-model="formItem.getTime" format="yyyy-MM-dd" style="width:120px"></DatePicker>
                  </tr>


                  <tr>
                      <td class="label">产地</td>
                      <td><Input v-model="formItem.orgin" placeholder="产地"></Input></td>
                      <td class="label">出厂编号</td>
                      <td><Input v-model="formItem.factoryId" placeholder="出厂编号"></Input></td>
                  </tr>
                  <tr>
                      <td class="label">内部编号</td>
                      <td><Input v-model="formItem.internalId" placeholder="内部编号"></Input></td>
                      <td class="label">安装位置</td>
                      <td><Input v-model="formItem.installationSite" placeholder="安装位置"></Input></td>
                  </tr>
                  <tr>

                      <td class="label">在用情况</td>
                      <td>
                          <Select v-model="formItem.useCase" class="width-1" placeholder="在用情况">
                              <Option value=""></Option>
                              <Option value="1">正常</Option>
                              <Option value="2">报废</Option>
                              <Option value="3">维修</Option>
                              <Option value="4">封存</Option>
                          </Select>
                      </td>
                      <td class="label">计量器具编号</td>
                      <td><Input v-model="formItem.instrumentId"  :readonly="readonlyBool" placeholder="计量器具编号"></Input></td>
                  </tr>
                  <tr>
                      <td class="label">类别</td>
                      <td><Select v-model="formItem.type" style="width:150px" placeholder="类别">
                          <Option value=""></Option>
                          <Option value="1">A类</Option>
                          <Option value="2">B类</Option>
                          <Option value="3">C类</Option>
                      </Select></td>
                      <td class="label">检定周期</td>
                      <td><Input v-model="formItem.retestingPeriod" placeholder="检定周期"></Input></td>
                  </tr>
                  <tr>
                      <td class="label">检定日期</td>
                      <td>
                          <DatePicker type="date" placeholder="检定日期" v-model="formItem.testingTime" format="yyyy-MM-dd" style="width:120px"></DatePicker>
                      </td>
<!--                      <td class="label">报审日期</td>
                      <td>
                          <DatePicker type="date" placeholder="" v-model="formItem.reportTime" format="yyyy-MM-dd" style="width:120px"></DatePicker>
                      </td>-->

                  </tr>
                  <tr>
                  </tr>
                  <tr>
                      <td class="label">备注</td>
                      <td colspan="3"><Input v-model="formItem.remark" placeholder="备注"></Input></td>
                  </tr>
              </table>
            </div>
          </Col>
        </Row>
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
    <Record ref="record"></Record>
  </div>
</template>
<script>
  import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import Record from './record';
  import page from '@/assets/js/page';
  export default {
    components: {
      Loading,
      LayoutHor,
      Record,
    },
    data() {
      return {
        typeName:'',
        loading: 0,
        materId:"",
        formItem:{
          instrumentId:"",
          materId:'',
          counterName:'',
          spec:'',
          model:'',
          orgin:'',
          factoryId:'',
          internalId:'',
          installationSite:'',
          getTime:'',
          useCase:'',
          type:'',
          retestingPeriod:'',
          testingTime:'',
          createTime:'',
          qualityTime:'',
          remark:'',
        },
				ruleValidate:{},
        readonlyBool:false,
        getTime_show:false,
        list:[],
        unitArgs:[],
        processRequireArgs:[],
        oriItem:{},
        isNew:true,
        isUpdate:false,
          res:""
      }

    },
    mounted: function () {
      this.load();
    },
    computed: {},
    methods: {
      load() {
        if(this.$route.query.id){
            this.typeName="编辑计量工具";
          this.getTime_show=true;
          this.readonlyBool=true;
          this.isUpdate=true;
          this.isNew=true;
          var id=this.$route.query.id;
          this.$http.post("/api/instrument/list",{instrumentId:id}).then((res) => {
            this.loading = 0;
            if (res.data.code==0) {
              if(res.data.data!=null){
                  Object.assign(this.formItem,res.data.data.rows[0]);
              }else{
                  this.formItem=[];
                this.$Message.error('该计量器具不存在！');
                this.goBack();
              }
            } else {
              this.$Message.error(res.data.message);
            }
          }).catch((error) => {
              this.formItem=[];
            this.loading = 0;
            this.$Message.error("操作失败！")
          });
        }else{
          this.typeName="添加计量工具";
        }
      },
      save(){

        if(this.formItem.materId==""){
            this.$Message.info("物料编码不能为空");
            return;
        }
        if(this.formItem.instrumentId==""){
          this.$Message.info("计量器具编号不能为空");
          return;
        }
        // 提交
        this.loading = 1;
        this.formItem.testingTime=page.formatDate(this.formItem.testingTime);

        if(this.$route.query.id){
          this.formItem.getTime=page.formatDate(this.formItem.getTime);
          this.$http.post("/api/instrument/update", this.formItem).then((res) => {
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
        }else{
                this.formItem.getTime=page.formatDate(new Date(),'yyyy-MM-dd');
                this.$http.post("/api/instrument/add",this.formItem).then((res) => {
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

        }

      },
      onSelectCustomer(){
        this.$refs.record.open();
        this.$refs.record.$off('on-ok');
        this.$refs.record.$on('on-ok',(items)=>{
          if(items.length > 0){
            this.formItem.materId = items[0].materId;
            this.formItem.counterName = items[0].materName;
            this.formItem.spec = items[0].spec;
            this.formItem.model = items[0].model;
            this.formItem.qualityTime = items[0].qualityTime;
            this.formItem.createTime = items[0].createTime;
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
        }
        if(this.isUpdate){
           this.initNew();
          this.load();
        }
      },
      initNew() {
        Object.assign(this.formItem, {
          instrumentId:"",
          materId:'',
          counterName:'',
          spec:'',
          model:'',
          orgin:'',
          factoryId:'',
          internalId:'',
          installationSite:'',
          getTime:'',
          useCase:'',
          type:'',
          retestingPeriod:'',
          testingTime:'',
          createTime:'',
          qualityTime:'',
          remark:'',
        });
      },
      goBack(){
        this.$router.push('/device');
      }
    }
  }

</script>

<style type="text/css">
  .inquireedit.page{
    width: 900px;margin: 0 auto;
    padding: 10px 0px;
    position: relative;
  }
  .inquireedit .subheader{
    height: 34px;line-height: 34px;
    font-size:14px;
    border-bottom: 0px solid #efefef;
    margin-bottom: 10px;
    color:#20bfee;
  }
  .inquireedit .baseinfo{

  }
  .inquireedit .baseinfo .label{
    width: 80px;text-align: right;
  }
  .inquireedit .baseinfo table{
    width: 100%;
  }
  .inquireedit .baseinfo table td{
    height: 40px;padding-right:4px;
  }

  .inquireedit .savebar{
    margin-top: 10px;
    height: 40px;
    width: 100%;
    border-collapse: collapse;
  }
  .inquireedit .savebar td{
    border: 1px solid #fefefe;
    font-size: 14px;
  }
  .inquireedit .savebar .save{
    width: 120px;
    border: 1px solid #20bfee;
    background-color: #20bfee;
    color: white;
    text-align: center;
    cursor: pointer;
  }
  .inquireedit .savebar .reset{
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

  .inquireedit .tooltip{
    padding:10px;
    background-color: #fafafa;
    border:1px solid #efefef;
    border-radius: 3px;
    color:#666;
    margin-top: 10px;
  }
</style>
