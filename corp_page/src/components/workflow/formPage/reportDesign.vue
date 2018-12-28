<template> 
  <PageComponent :max="true" :title="title">
    <div slot="left">
      <Button @click="back" type="warning" size="small"><Icon type="ios-arrow-back" ></Icon>&nbsp;返回</Button>
    </div>
    <div slot="right"></div>

    <div class="wf-reportdesign-page">
      <PageNavComponent class="tabs" 
        :tabs="['样式设计','流程设计','属性设置','报表设计']" 
        @click="pagenav" :cur="3"></PageNavComponent>
      <div class="wf-reportdesign-body">
      	<div class="wf-reportdesign-left">
          <div class="wf-reportdesign-left-top">
             <Button type="ghost" @click="create">新建报表</Button>
          </div>
          <div class="wf-reportdesign-left-list"> 
            <div v-for="(item,index) in reports" @click="open(item)"
             :class="{'wf-reportdesign-left-list-item':1,'cur':item.cur}">
                {{item.title}}
                <span style="float:right;">
                  <Icon type="edit" @click.stop.native="update(item)"></Icon>
                <Icon type="trash-a" @click.native="del(item.id)"></Icon>
            </span> 
            </div>                     
            <div :class="{'wf-reportdesign-left-list-item':1,'cur':1}"
              v-if="isNew">
                新建报表
            </div>
          </div>
        </div>

        <div class="wf-reportdesign-right">
          <div class="wf-reportdesign-right-new" v-if="isNew || isEdit">
            <Row class="wf-reportdesign-right-new-base">
              <Col span="16">
                <Form :model="report" label-position="left" :label-width="100">
                  <FormItem label="报表名称">
                    <Input v-model="report.title"></Input>
                  </FormItem>
                  <FormItem label="报表类型">
                    <RadioGroup v-model="report.type">
                        <Radio :label="1">查询表</Radio>
                        <Radio :label="2">统计表</Radio>
                        <Radio :label="3">统计图</Radio>
                    </RadioGroup>
                  </FormItem>
                  <FormItem label="报表授权">                      
                      <template v-for="emp in authEmps">
                        <Icon type="android-contact" class="icon-type"></Icon>{{emp.empName}}
                      </template>
                      <a @click="selectPersons" v-if="authEmps.length == 0 ">点击选择人员</a>
                      <a @click="selectPersons(authEmps)" v-if="authEmps.length > 0 ">编辑</a>
                  </FormItem>
                  <FormItem label="穿透单据">
                     <iSwitch v-model="report.rift" :true-value="1" :false-value="0"></iSwitch>
                  </FormItem>
                </Form>
              </Col>
              <Col span="8">
                
              </Col> 
            </Row>
            <div class="wf-reportdesign-right-line"></div>
            <Row>
              <Col span="12">
                 <div class="wf-reportdesign-right-line-right">
                    <div class="wf-reportdesign-right-block">
                      <Button type="primary" >设置筛选条件</Button>
                      <div class="wf-reportdesign-page-fieldgroup-header">
                        数据项
                          <Poptip placement="bottom" width="150">
                            <Icon type="plus"></Icon>
                            <div class="api" slot="content">
                                <ul class="wf-reportdesign-page-fieldgroup-options">
                                  <li><Checkbox
                                    :value="checkDatasAll"
                                    @click.prevent.native="handleCheckAll('datas')">全选</Checkbox></li>
                                  <CheckboxGroup v-model="checkDatas" @on-change="checkDatasChange">
                                    <template v-for="item in fields">
                                       <li><Checkbox :label="item.field" :value="item.field">{{item.title}}</Checkbox></li>
                                    </template>
                                  </CheckboxGroup>
                                </ul> 
                            </div>
                          </Poptip>  
                      </div>
                      <div class="wf-reportdesign-page-fieldgroup">
                        <div 
                          v-for="(item,index) in report.datas"
                        class="wf-reportdesign-page-fieldgroup-field">
                          {{item.title}}
                          <span class="wf-reportdesign-page-fieldgroup-field-icon"> 
                              <Icon type="trash-a" @click.native="delField('datas',item)"></Icon>
                          </span>
                        </div> 
                      </div>
                    </div>
                 </div>
              </Col>
              <Col span="12">
                <div class="wf-reportdesign-right-block">
                  <div class="wf-reportdesign-page-fieldgroup-header">
                    自定义查询项
                      <Poptip placement="bottom" width="150">
                            <Icon type="plus"></Icon>
                            <div class="api" slot="content">
                                <ul class="wf-reportdesign-page-fieldgroup-options">
                                  <li><Checkbox
                                    :value="checkQuerysAll"
                                    @click.prevent.native="handleCheckAll('querys')">全选</Checkbox></li>
                                  <CheckboxGroup v-model="checkQuerys" @on-change="checkQuerysChange">
                                    <template v-for="item in fields">
                                       <li><Checkbox :label="item.field" :value="item.field">{{item.title}}</Checkbox></li>
                                    </template>
                                  </CheckboxGroup>
                                </ul> 
                            </div>
                      </Poptip> 
                  </div>
                  <div class="wf-reportdesign-page-fieldgroup">
                        <div 
                          v-for="(item,index) in report.querys"
                        class="wf-reportdesign-page-fieldgroup-field">
                          {{item.title}}
                          <span class="wf-reportdesign-page-fieldgroup-field-icon"> 
                              <Icon type="trash-a" @click.native="delField('querys',item)"></Icon>
                          </span>
                        </div> 
                  </div>
                </div>
              </Col>
            </Row>
            <div class="wf-reportdesign-right-line"></div>
            <div class="wf-reportdesign-right-btn">
                <Button type="primary" @click="save">保存报表</Button>
            </div>
          </div>
          <div v-else>
            <GridReport ref="gridReport" v-if="curReport.type == 1"></GridReport>
          </div>
        </div>
      </div> 
    </div> 
     <SelectDeptPerson ref="selectDeptPerson"></SelectDeptPerson> 
  </PageComponent>
</template>

<script>
	
	import PageComponent from '@/components/workflow/components/Page';
  import PageNavComponent from '@/components/workflow/components/PageNav';
  import GridReport from '@/components/workflow/report/GridReportComponent'; 
  import SelectDeptPerson from '@/components/workflow/components/SelectDeptPerson';

	export default {
  components: { 
    PageComponent,
    PageNavComponent,
    GridReport,
    SelectDeptPerson 
  },
  data() {
    return {
      loading:0,
      id:0,
      title:'表单',
      reports:[
 /*       {title:'查询',cur:0},
        {title:'统计表',cur:0},
        {title:'统计图',cur:1}*/
      ],
      report:{
        title:'',
        type:1,
        //authEmpIds
        rift:1,
        //filter_json
        datas:[],
        querys:[],
        //groups
        //crosses
        //summation_json
        //row_summation_json
        chart:1,
        allowSwitch:1,
        /*datas:[
          {field:'sdfsdfs',title:'姓名'},
          {field:'sdfsdfs1',title:'手机'},
        ]*/
      },
      authEmps:[],//authEmp:{ empId:'',empName:''}
      fields:[],
      isNew:false,
      isEdit:false,
      checkQuerysAll:false,
      checkDatasAll:false,
      checkDatas:[],
      checkQuerys:[],
      curReport:{}
    };
  },
  mounted:function(){
    this.id = this.$route.query.id;
    this.appId = this.$route.query.appId;
    this.load();
    this.loadForm();
  },
  computed:{

  },
  watch:{
     
  },
  methods:{ 
    loadForm(){
       this.$http.post('/api/wf/form/get?id=' + this.id,{}).then((res) =>{
            if (res.data.code === 0) { 
                let data = res.data.data;
                let rult = []; 
              if(data.fieldsJson&&data.fieldsJson[0] == '[')
                {
                  let arr = eval('('+data.fieldsJson+')'); 
                  for(var i = 0;i<arr.length;i++){
                     rult.push({field:arr[i].id,title:arr[i].title});
                  }
                  this.fields = rult;
                }                
              } else {
                  this.$Message.error(res.data.message);
              }
            }).catch((error) => {
                this.$Message.error(error.toString())
            }) 
    }, 
    load:function(id){ 
       this.$http.post('/api/wf/report/query?formId=' + this.id,{}).then((res) =>{
            if (res.data.code === 0) {
                let rult = res.data.data;                 
                for(var i = 0;i<rult.length;i++){                                  
                    rult[i].cur = rult[i].id == id;
                }
                if(typeof id == 'undefined' && rult.length > 0){
                    rult[0].cur = 1;
                }
                this.reports = rult;
                
                let rp = this.reports.filter(report => report.cur == 1);
                this.open(rp[0]);

              } else {
                  this.$Message.error(res.data.message);
              }
            }).catch((error) => {
                this.$Message.error(error.toString())
            }) 
      
    }, 
    pagenav:function(ele,index){
      if(index == 0){
        this.$router.replace({path:'/workflow/formdesign?id='+this.id});
      }
      if(index == 1){
        this.$router.replace({path:'/workflow/flowdesign?id='+this.id});
      }
      if(index == 2){
        this.$router.replace({path:'/workflow/propertysetting?id='+this.id});
      }
      if(index == 3){
        this.$router.replace({path:'/workflow/reportdesign?id='+this.id});
      }
    },
    back:function(){
      this.$router.go(-1);
    },
    create(){
      this.isNew = true;
      this.isEdit = false;
      for(var i=0;i<this.reports.length;i++){
        this.reports[i].cur = false;
      }
      this.reset();
    },
    open(item){  
      this.isNew = false;
      this.isEdit = false;
      for(var i=0;i<this.reports.length;i++){
        this.reports[i].cur = this.reports[i] == item;
      } 

      this.curReport = item;
      this.$nextTick(()=>{
        if(item.type == 1){ 
          this.$refs.gridReport.show(item.id);
        }
      })   
    },
    reset(){
      this.report = {
        title:'',
        type:1,
        authEmpIds:'',
        rift:1,
        //filter_json
        //datas
        querys:[],
        //groups
        //crosses
        //summation_json
        //row_summation_json
        chart:1,
        allowSwitch:1,
        datas:[]
      }
      this.isEdit = false;
      this.checkQuerys = [];
      this.checkDatas = [];
      this.checkQuerysAll = false;
      this.checkDatasAll = false;

    },
    update(item){
      this.get(item.id);
      this.isEdit = true;
      this.isNew = false;
      for(var i=0;i<this.reports.length;i++){
        this.reports[i].cur = this.reports[i] == item;
      }

    },
    del(id){
       this.$Modal.confirm({
        title: '删除确认',
        content: '<p>删除后不能恢复,确定要删除该报表？</p>',
        onOk: () => {
           this.$http.post('/api/wf/report/delete?id=' + id,{}).then((res) =>{
            if (res.data.code === 0) {                 
                 this.load();          
              } else {
                this.$Message.error(res.data.message);
              }
            }).catch((error) => {
                this.$Message.error(error.toString())
            })
       }
      });
    },
    get(id){ 
      this.$http.post('/api/wf/report/get?id=' + id,{}).then((res) =>{
        if (res.data.code === 0) {              
            this.report = res.data.data;
            this.checkDatas = this.report.datas ? this.report.datas.split(',') :[];
            this.checkQuerys = this.report.querys ? this.report.querys.split(',') :[];
            this.report.datas = this.report.datas ? this.fields.filter(item => this.report.datas.indexOf(item.field)>-1) : []; 
            this.report.querys = this.report.querys ? this.fields.filter(item => this.report.querys.indexOf(item.field)>-1) : [];
            this.authEmps = [];
            for(var i = 0;i<this.report.empList.length;i++){
              this.authEmps.push({empId:this.report.empList[i].empId,empName:this.report.empList[i].trueName});
            }           
          } else {
              this.$Message.error(res.data.message);
          }
        }).catch((error) => {
            this.$Message.error(error.toString())
      })
    },
    save(){
      let param = {};
      param.id = this.report.id;
      param.title = this.report.title;
      param.type = this.report.type;
      param.formId = this.id;
      param.rift = this.report.rift ? 1:0;
      param.datas = this.checkDatas.join(',') || '';
      param.querys = this.checkQuerys.join(',') || '';
      param.chart = this.report.chart;
      param.allowSwitch = this.report.allowSwitch;
      param.authEmpIds = this.authEmps.map(emp => {return emp.empId}).join(',') || '';
      this.$http.post('/api/wf/report/edit',param).then((res) =>{
        if (res.data.code === 0) {               
            this.$Message.info('报表保存成功');
            if(this.isNew){         
              this.load(res.data.data);
              this.isNew = false;
            }else{
              this.load(this.report.id);
              this.isEdit = false;
            }

          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
            this.$Message.error(error.toString())
      }) 
    },
    handleCheckAll(field){
        if(field == 'datas'){
          this.checkDatasAll = !this.checkDatasAll;
          if (this.checkDatasAll) {         
              this.checkDatas = this.fields.map(item =>{ return item.field});
              this.report.datas = this.fields;          
          } else {
              this.checkDatas = [];
              this.report.datas = [];
          }
        }
        if(field == 'querys'){
          this.checkQuerysAll = !this.checkQuerysAll;
          if (this.checkQuerysAll) {  
            this.checkQuerys = this.fields.map(item =>{ return item.field});
            this.report.querys = this.fields;
          } else {
              this.checkQuerys = [];
              this.report.querys = [];
          }
        }
    },
    checkDatasChange(data){
        if (data.length === this.fields.length) {
            this.checkDatasAll = true;
        } else if (data.length > 0) {
            this.checkDatasAll = false;           
        } else {
            this.checkDatasAll = false;
        }
        this.report.datas = this.fields.filter(item => data.indexOf(item.field)>-1);
    },
    checkQuerysChange(data){
        if (data.length === this.fields.length) {
            this.checkQuerysAll = true;
        } else if (data.length > 0) {
            this.checkQuerysAll = false;           
        } else {
            this.checkQuerysAll = false;
        }
        this.report.querys= this.fields.filter(item => data.indexOf(item.field)>-1);
    },
    delField(field,item){
      if(field == 'datas'){
        let index = this.report.datas.indexOf(item);
        this.report.datas.splice(index,1);
        let i = this.checkDatas.indexOf(item.id);
        this.checkDatas.splice(i,1);
      }
      if(field == 'querys'){
        let index = this.report.querys.indexOf(item);
        this.report.querys.splice(index,1);
        let i = this.checkQuerys.indexOf(item.id);
        this.checkQuerys.splice(i,1);
      }     
    },
    selectPersons(authEmps){ //authEmp:{ empId:'',empName:''}
      var pop = this.$refs.selectDeptPerson;
      pop.selectPerson = true;
      pop.selectDept = false;
      pop.isMulti = true;

      pop.$off('ok');
      pop.$on('ok',()=>{ 
        pop.$off('ok');
        var persons = pop.select;
        var selected = [];
        for(var i = 0;i<persons.length;i++){
          selected.push({empId:persons[i].key,empName:persons[i].title});
        } 
        this.authEmps = selected;      
      });
      pop.show();
      pop.select = this.innerGetDeptPerson(authEmps);
    },
    innerGetDeptPerson(field){
      var arr = [];
      var vals = field;
      for(var i=0;i<vals.length;i++){
         var item = {type:'',key:'',title:''};
         item.type = 'person';
         item.key = vals[i].empId;
         item.title = vals[i].empName;
         arr.push(item);
      }
      return arr;
    }
  }
}
</script>

<style type="text/css" scoped>
  .wf-reportdesign-page{
    padding-top: 40px;
    height: 100%; 
    background-color: white;
    overflow:hidden;
    position: relative;
  } 
  .wf-reportdesign-page .tabs{
    margin-top: -40px; 
  }
  .wf-reportdesign-body{
    padding-left:160px;
    height: 100%; 
  }
  .wf-reportdesign-left{
    position: absolute;
    left:0px;
    top:40px;
    bottom: 0px;
    border-right: 1px solid #eeeeee;
    width: 160px; 
  }
  .wf-reportdesign-left-top{
    padding: 6px 10px;
  }
  .wf-reportdesign-left-list-item{
    height: 40px;
    line-height: 40px;
    font-size: 14px;
    padding: 0px 10px;
    background-color: #ededed;
    cursor: pointer;
  }
  .wf-reportdesign-left-list-item:hover{
    background: rgba(89,144,207,.5);
    color: #ffffff;
  }
  .wf-reportdesign-left-list-item.cur{
    color: white;
    background-color: #5990cf;
  }
  .wf-reportdesign-right{
    overflow-y: auto;
    height: 100%;
  }
  .wf-reportdesign-right-new{
     
  }
  .wf-reportdesign-right .ivu-form .ivu-form-item-label{
    font-size: 14px; 
  }
  .wf-reportdesign-right-line{
    height: 1px;
    border-bottom: 1px solid #eeeeee;
  }
  .wf-reportdesign-right-new-base{
    margin:20px;
  }
  .wf-reportdesign-right-line-right{
    border-right: 1px solid #eeeeee;
    height: auto;
  }
  .wf-reportdesign-right-block{
    padding: 10px 20px;
  }
  .wf-reportdesign-page-fieldgroup-header{
    height: 40px;
    line-height:40px;
    font-size: 14px; 
  }
  .wf-reportdesign-page-fieldgroup-header .ivu-icon{
    color:#5990cf;
    display: inline-block;
    margin-left: 100px;
    font-weight: bold;
    font-size: 18px;
    cursor: pointer;
  }
  .wf-reportdesign-page-fieldgroup{
    margin-left: 20px;
  }
  .wf-reportdesign-page-fieldgroup-field{
    line-height: 32px;
    height: 32px;
    border:1px dashed #eee;
    padding: 0px 10px;
    margin-top: 4px;
  }
  .wf-reportdesign-page-fieldgroup-field:hover{
    border: 1px dashed #4FACE9;
  }

  .wf-reportdesign-right-btn{
    width: 100%;
    margin: 0 auto;
    text-align: center;
    padding-top:20px; 
  }
  .wf-reportdesign-page-fieldgroup-options li{
    font-size: 16px;
    padding: 2px;
  }
  .wf-reportdesign-page-fieldgroup-field-icon{
    font-size: 16px;
    float: right;
    cursor: pointer;
    padding-left: 5px;
  }
  .wf-reportdesign-page-fieldgroup-field>span,
  .wf-reportdesign-left-list-item>span{display: none;}
  .wf-reportdesign-page-fieldgroup-field:hover >span,
  .wf-reportdesign-left-list-item:hover >span{display: block;}
  .wf-reportdesign-right .icon-type{
    font-size: 18px;
    color: #5990cf;
    padding-left:2px; 
  }
  .wf-reportdesign-right a {
    margin-left: 10px;
    cursor: pointer;
    color: #20bfee;
   }
</style> 
