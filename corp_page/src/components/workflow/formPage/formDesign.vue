<template>
  <PageComponent :max="true" :title="data.title">
    <div slot="left">
      <Button @click="back" type="warning" size="small"><Icon type="ios-arrow-back" ></Icon>&nbsp;返回</Button>
    </div>
    <div slot="right"></div>
    <div class="wf-formdesign-page">
      <PageNavComponent class="wf-formdesign-page-tabs"
        :tabs="['样式设计','流程设计','属性设置']"
        @click="pagenav"></PageNavComponent>
      <div class="wf-formdesign-body">
      	 <DesignComponent :model="data" style="height:100%" @save="save" @preview="preview"></DesignComponent>
      </div>
      <Loading v-if="loading==1"></Loading>
    </div>
  </PageComponent>
</template>

<script>
	import DesignComponent from '@/components/workflow/formPage/DesignComponent';
	import PageComponent from '@/components/workflow/components/Page';
  import PageNavComponent from '@/components/workflow/components/PageNav';
  import Loading from '@/components/loading';

	export default {
  components: {
    DesignComponent,
    PageComponent,
    PageNavComponent,
    Loading
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
        fields :[]
      },
      oriData:{}
    };
  },
  mounted:function(){
    if(this.$route.query.id){
      this.id = this.$route.query.id;
    }
    if(this.$route.query.appId){
      this.appId = this.$route.query.appId;
    }
    this.load();
  },
  computed:{

  },
  watch:{
    '$route':function(){
      if(this.$route.query.id){
        this.id = this.$route.query.id;
      }
      if(this.$route.query.appId){
        this.appId = this.$route.query.appId;
      }
      this.load();
    }
  },
  methods:{
    load:function(){
      if(this.id){
        this.loading = 1;
        this.$http.post('/api/wf/form/get?id=' + this.id, {}).then((res) => {
          if (res.data.code == 0) {
            this.loading = 0;
            this.oriData = res.data.data;
            this.loadOri();
          } else {
            this.loading = 2;
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 2;
          this.$Message.error(error.toString());
        });
      }else{
        this.loading = 2;
        this.data.appId = this.appId;
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
        fields :[]
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
        retVal[name] = data[name];
      }
      return retVal;
    },
    pagenav:function(ele,index){
      if(index > 0){
        if(this.id){
          if(index == 1){
            this.$router.replace({path:'/workflow/flowdesign?id='+this.id});
          }
          if(index == 2){
            this.$router.replace({path:'/workflow/propertysetting?id='+this.id});
          }
          if(index == 3){
            this.$router.replace({path:'/workflow/reportdesign?id='+this.id});
          }
        }else{
          this.$Message.error('请先保存表单');
        }
      }
    },
    back:function(){
      this.$router.go(-1);
    },
    save:function(){
      var data = this.getSubmit();
      // 验证表单项
      var isNew = data.id == 0;
      if(isNew){
        data.appId = this.appId;
      }
      if(!isNew){
        data.id = this.id;
      }
      this.loading = 1;
      this.$http.post('/api/wf/form/' + (isNew?'create':'update'), data).then((res) => {
        this.loading = 0;
        if (res.data.code == 0) {
          this.$Message.info('保存成功!');
          if(isNew){
            this.$router.replace({path:'/workflow/formdesign?id='+res.data.data});
          }
        } else {
          this.$Message.error(res.data.message)
        }
      }).catch((error) => {
        this.loading = 0;
        this.$Message.error(error.toString())
      });
    },
    preview:function(){
      alert('preview');
    }
  }
}
</script>

<style type="text/css" scoped>
  .wf-formdesign-page{
    padding-top: 50px;
    height: 100%;
  }
  .wf-formdesign-body{
    height: 100%;
  }
  .wf-formdesign-page-tabs{
    margin-top: -50px;
    margin-bottom: 10px;
  }
</style>
