<template> 
  <PageComponent :max="true" :title="title">
    <div slot="left">
      <Button @click="back" type="warning" size="small"><Icon type="ios-arrow-back" ></Icon>&nbsp;返回</Button>
    </div>
    <div slot="right"></div> 
    <div class="wf-report-page"> 
      <div class="wf-report-body">
      	<div class="wf-report-left"> 
          <div class="wf-report-left-list">
            <div class="wf-report-left-list-group" v-for="(group,index) in forms">
              <div :class="{'wf-report-left-list-group-head':1,'wf-report-left-list-group-head-open':group.open}" @click="group.open = !group.open">
                {{group.title}}
                <Icon type="chevron-down" v-if="group.open"></Icon>
                <Icon type="chevron-right" v-else></Icon>
              </div>
              <div class="wf-report-left-list-group-list" v-if="group.open">
                 <div v-for="(item,index) in group.reports" @click="open(item)"
             :class="{'wf-report-left-list-item':1,'cur':item.cur}">
                {{item.title}}
                </div>
              </div>              
            </div> 
          </div>
        </div>

        <div class="wf-report-right">
          <GridReport ref="gridReport" v-if="curReport.type == 1"></GridReport>
        </div>
      </div> 
    </div> 
  </PageComponent>
</template>

<script>
	
	import PageComponent from '@/components/workflow/components/Page'; 
  import GridReport from '@/components/workflow/report/GridReportComponent'; 

	export default {
  components: { 
    PageComponent, 
    GridReport
  },
  data() {
    return {
      loading:0,
      id:0,
      title:'报表',
      forms:[ 
      ],
      curReport:{},
      report:{
        title:'',
        type:1,
        //authEmpIds
        rift:1,
        //filter_json
        //datas
        //querys
        //groups
        //crosses
        //summation_json
        //row_summation_json
        chart:1,
        allowSwitch:1,
        datas:[
          {field:'sdfsdfs',title:'姓名'},
          {field:'sdfsdfs1',title:'手机'},
        ]
      },
      isNew:false,
    };
  },
  mounted:function(){
    this.load();
  },
  computed:{

  },
  watch:{
     
  },
  methods:{  
    load:function(){ 
      // 加载表单和报表
      /*this.forms = 
      [{title:'表单',open:true,reports:[
          {id:1,title:'查询',type:1,cur:0},
          {id:2,title:'统计表',type:1,cur:0},
          {id:3,title:'统计图',type:1,cur:0}
        ]},
        {
          title:'表单1',open:false,reports:[]
        }
      ];
      this.open(this.forms[0].reports[0]);*/
      this.$http.post('/api/wf/report/auth',{}).then((res) =>{
            if (res.data.code === 0) {                 
                 this.forms = this.build(res.data.data);
                 this.open(this.forms[0].reports[0]);         
              } else {
                this.$Message.error(res.data.message);
              }
            }).catch((error) => {
                this.$Message.error(error.toString())
        })
    },
    build(forms){
       for(var i=0;i<forms.length;i++){        
          forms[i].open = i == 0 ? true : false;
          for(var j = 0;j<forms[i].reports;j++){
             forms[i].reports[j].type = 1;
             forms[i].reports[j].cur = 0;
          }
       }

       return forms;
    },
    back:function(){
      this.$router.go(-1);
    }, 
    open(item){
      for(var i=0;i<this.forms.length;i++){
        var form = this.forms[i];
        for(var j = 0;j<form.reports.length;j++){
          var report = form.reports[j];
          report.cur = report == item;
        }
      }
      this.curReport = item;
        this.$nextTick(()=>{
          if(item.type == 1){
            this.$refs.gridReport.show(item.id);
          }
        }) 
    }
  }
}
</script>

<style type="text/css" scoped>
  .wf-report-page{
    height: 100%; 
    background-color: white;
    overflow:hidden;
    position: relative;
  } 
  .wf-report-body{
    padding-left:150px;
    height: 100%; 
  }
  .wf-report-left{
    position: absolute;
    left:0px;
    top:0px;
    bottom: 0px;
    border-right: 1px solid #eeeeee;
    width: 150px; 
  }
  .wf-report-left-list-group-head{
    font-size: 14px;
    line-height: 40px;
    height: 40px;
    padding: 0px 10px;
    cursor: pointer;
    border-bottom: 1px solid #eee;
  }
  .wf-report-left-list-group-head:hover{
    color: #666;
  }
  .wf-report-left-list-group-head-open{
    border-bottom: 0px;
  }
  .wf-report-left-list-group-head .ivu-icon{
    float: right;display: inline-block;margin-top: 15px;
  }
  .wf-report-left-list-group-list{
    background-color: #ededed;
    min-height: 20px;
  }

  .wf-report-left-list-item{
    height: 34px;
    line-height: 34px;
    font-size: 14px;
    padding: 0px 10px;
    background-color: #ededed;
    cursor: pointer; 
  }
  .wf-report-left-list-item:hover{
    background: rgba(89,144,207,.5);
    color: #ffffff;
  }
  .wf-report-left-list-item.cur{
    color: white;
    background-color: #5990cf;
  }
  .wf-report-right{
    overflow-y: auto;
    height: 100%;
  }
  .wf-report-right-new{
     
  }
  .wf-report-right .ivu-form .ivu-form-item-label{
    font-size: 14px; 
  }
  .wf-report-right-line{
    height: 1px;
    border-bottom: 1px solid #eeeeee;
  }
  .wf-report-right-new-base{
    margin:20px;
  }
  .wf-report-right-line-right{
    border-right: 1px solid #eeeeee;
    height: auto;
  }
  .wf-report-right-block{
    padding: 10px 20px;
  }
  .wf-report-page-fieldgroup-header{
    height: 40px;
    line-height:40px;
    font-size: 14px; 
  }
  .wf-report-page-fieldgroup-header .ivu-icon{
    color:#5990cf;
    display: inline-block;
    margin-left: 100px;
    font-weight: bold;
    font-size: 18px;
    cursor: pointer;
  }
  .wf-report-page-fieldgroup{
    margin-left: 20px;
  }
  .wf-report-page-fieldgroup-field{
    line-height: 32px;
    height: 32px;
    border:1px dashed #eee;
    padding: 0px 10px;
    margin-top: 4px;
  }
  .wf-report-page-fieldgroup-field:hover{
    border: 1px dashed #4FACE9;
  }
</style> 
