<template> 
  <PageComponent :max="true" :title="data.title">
    <div slot="left">
      <Button @click="back" type="warning" size="small"><Icon type="ios-arrow-back" ></Icon>&nbsp;返回</Button>
    </div>
    <div slot="right"></div>
    <div class="wf-flowdesign-page">
      <PageNavComponent class="wf-flowdesign-page-tabs" 
        :tabs="['样式设计','流程设计','属性设置']" 
        @click="pagenav" :cur="1"></PageNavComponent>
      <div class="wf-flowdesign-body">
      	<flowGraph ref="flowGraph" v-model="data.flowNodes" @on-require-selectperson="requireSelectPerson"
        @on-require-edit="requireEdit"></flowGraph>
        <div class="wf-flowdesign-bottom">
          <Button type="primary" @click="save">保 存</Button>
          &nbsp;
          <Button type="default" @click="clear">清 空</Button>
        </div>
      </div> 
      <selectPerson ref="selectPerson"  @ok="selectPerson" ></selectPerson>
      <flowNodeEdit ref="flowNodeEdit"></flowNodeEdit>
    </div> 
  </PageComponent>
</template>

<script>
	
	import PageComponent from '@/components/workflow/components/Page';
  import PageNavComponent from '@/components/workflow/components/PageNav';
  import flowGraph from '@/components/workflow/components/flow/flowGraph';
  import selectPerson from '@/components/workflow/formPage/SelectPersonComponent';
  import flowNodeEdit from '@/components/workflow/formPage/FlowNodeEditComponent';

	export default {
  components: {
    flowGraph,
    PageComponent,
    PageNavComponent,
    selectPerson,
    flowNodeEdit
  },
  data() {
    return {
      loading:0,
      id:0,
      appId:0, 
      data:{ 
        id:0, 
        appId:0,
        title:'表单',
        description:'描述', 
        showFieldSeq:'',
        hidenTitle:'',
        fieldWay:2,
        fieldTitleSize:2,
        fieldValueSize:2,
        fieldRowSpace:2,
        formWidth:2,
        colorBg:'',
        colorHeadBg:'',
        colorPage:'',
        colorTitle:'',
        colorHead:'',
        colorDescribe:'',
        picBg:'',
        picHead:'',
        picHeadLogo:'',
        colorDescribe:'',
        fields :[],
        flowNodes:[],
        fieldAuth:[]
      },
      oriData:{},
    };
  },
  mounted:function(){
    this.id = this.$route.query.id;
    this.appId = this.$route.query.appId;
    this.load();
  },
  computed:{

  },
  watch:{
    '$route':function(){
      this.id = this.$route.query.id;
      this.load();
    }
  },
  methods:{  
    load:function(){ 
      if(this.id){
        this.loading = 1; 
        this.$http.post('/api/wf/form/get?id=' + this.id, {}).then((res) => {
          this.loading = 0; 
          if (res.data.code == 0) {
            if(res.data.data){
              this.oriData = res.data.data;
              this.loadOri();
            }else{
              this.loading = 2;
              this.$Message.error('数据不存在，或已删除！');
            }  
          } else {
            this.loading = 2;
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 2;
          this.$Message.error(error.toString());
        });
      }else{
        this.loading = 3;
        this.$Message.error('缺少参数！id=?');
      }
    },
    loadOri:function(){
      var ori = this.oriData;
      var data =  {
        title:'表单',
        description:'描述', 
        showFieldSeq:'',
        hidenTitle:'',
        fieldWay:2,
        fieldTitleSize:2,
        fieldValueSize:2,
        fieldRowSpace:2,
        formWidth:2,
        colorBg:'',
        colorHeadBg:'',
        colorPage:'',
        colorTitle:'',
        colorHead:'',
        colorDescribe:'',
        picBg:'',
        picHead:'',
        picHeadLogo:'',
        colorDescribe:'',
        fields :[],
        flowNodes:[],
        fieldAuth:[]
      };
      for(var name in data){
        if(name == 'fields'){
          if(ori.fieldsJson && ori.fieldsJson[0] == '['){
            try{
              data.fields = eval('('+ori.fieldsJson+')');
            }catch(ex){
              console.log('eval fieldsJson error');
            }
          }
          continue;
        }
        if(name == 'flowNodes'){
          if(ori.flowJson && ori.flowJson[0] == '['){
            try{
              data.flowNodes = eval('('+ori.flowJson+')');
            }catch(ex){
              console.log('eval flowJson error');
            }
          }
          continue;
        } 
        if(name == 'fieldAuth'){
          if(ori.fieldAuthJson && ori.fieldAuthJson[0] == '['){
            try{
              data.fieldAuth = eval('('+ori.fieldAuthJson+')');
            }catch(ex){
              console.log('eval fieldAuthJson error');
            }
          }
          continue;
        }
        
        data[name] = ori[name] || data[name];
      }
      this.data = data;
    },
    getSubmit:function(){ 
      var data = this.data;
      var retVal = {};
      for(var name in data){

        if(name == 'fields'){ 
          retVal['fieldsJson'] = JSON.stringify(data.fields);
          continue;
        }
        if(name == 'flowNodes'){ 
          retVal['flowJson'] = JSON.stringify(data.flowNodes);
          continue;
        }
        if(name == 'fieldAuth'){ 
          retVal['fieldAuthJson'] = JSON.stringify(data.fieldAuth);
          continue;
        }
        retVal[name] = data[name];
      }

      return retVal;
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
    requireSelectPerson(){ 
      this.$refs.selectPerson.show();
    },
    requireEdit(data){ 
      var comp = this.$refs.flowNodeEdit;
      comp.fields = this.data.fields;
      comp.groups = this.data.fieldAuth;
      comp.show(data,(ok)=>{
        data.branch = comp.data.branch;
        data.execPattern = comp.data.execPattern;
        data.executor = comp.data.executor;
        data.executorId = comp.data.executorId;
        data.fieldAuth = comp.data.fieldAuth;
        data.flowAuth = comp.data.flowAuth;
        data.notify = comp.data.notify; 
        //data.type = comp.data.type;
      }); 
    },
    selectPerson(){
      var comp = this.$refs.selectPerson;
      this.$refs.flowGraph.selectPerson(comp.select,comp.isConcurrence); 
    },
    back:function(){
      this.$router.go(-1);
    },
    save:function(){
      if(this.loading > 0){
        return;
      }
      var data = this.getSubmit();
      // 验证表单项
      var isNew = data.id == 0;
      if(isNew){
        data.appId = this.appId;
      }
      if(!isNew){
        data.id = this.id;
      }
      this.$http.post('/api/wf/form/' + (isNew?'create':'update'), data).then((res) => {
        if (res.data.code == 0) {
          this.$Message.info('保存成功!'); 
          if(isNew){
            this.$router.replace({path:'/workflow/flowdesign?id='+res.data.data});
          }
        } else { 
          this.$Message.error(res.data.message)
        }
      }).catch((error) => { 
        this.$Message.error(error.toString())
      });
    },
    clear:function(){
      this.$refs.flowGraph.clear();
    },
    preview:function(){
      alert('preview');
    }
  }
}
</script>

<style type="text/css" scoped>
  .wf-flowdesign-page{
    padding-top: 41px;
    height: 100%; 
    background-color: #F9FAFB;
  }
  .wf-flowdesign-body{
    height: 100%;
    padding-bottom: 40px;
  }
  .wf-flowdesign-bottom{
    text-align: center;
    padding: 6px;
  }
  .wf-flowdesign-bottom .ivu-btn{
    width: 130px;
  }
  .wf-flowdesign-page-tabs{
    margin-top: -41px;
  }
</style> 
