<template>
  <PageComponent :max="true" :title="data.title">
    <div slot="left">
      <Button @click="back" type="warning" size="small"><Icon type="ios-arrow-back" ></Icon>&nbsp;返回</Button>
    </div>
    <div slot="right"></div>
    <div class="wf-propertysetting-page">
      <PageNavComponent class="wf-propertysetting-page-tabs"
        :tabs="['样式设计','流程设计','属性设置']"
        @click="pagenav" :cur="2"></PageNavComponent>
      <div class="wf-propertysetting-body">
        <div class="wf-propertysetting-left">
          <div class="wf-propertysetting-left-list">
            <div v-for="(item,index) in leftnav" @click="open(item)"
             :class="{'wf-propertysetting-left-list-item':1,'cur':item.key == cur}">
                {{item.title}}
            </div>
          </div>
        </div>

        <div class="wf-propertysetting-right">
      　　<Publish ref="publish" v-if="cur=='publish'"></Publish>
          <FieldAuth ref="fieldauth" v-if="cur=='fieldauth'"></FieldAuth>
          <FormAuth ref="formauth" v-if="cur=='formauth'"></FormAuth>
          <FormProperty ref="formproperty" v-if="cur=='formproperty'"></FormProperty>
        </div>
      </div>
    </div>
  </PageComponent>
</template>

<script>

	import PageComponent from '@/components/workflow/components/Page';
  import PageNavComponent from '@/components/workflow/components/PageNav';
  import Publish from '@/components/workflow/formPage/property/Publish';
  import FieldAuth from '@/components/workflow/formPage/property/FieldAuth';
  import FormAuth from '@/components/workflow/formPage/property/FormAuth';
  import FormProperty from '@/components/workflow/formPage/property/FormProperty';

	export default {
  components: {
    PageComponent,
    PageNavComponent,
    Publish,
    FieldAuth,
    FormAuth,
    FormProperty
  },
  data() {
    return {
      loading:0,
      id:0,
      appId:0,
      data:{
        title:'表单'
      },
      leftnav:[
        {title:'发布设置',key:'publish'},
        {title:'字段权限设置',key:'fieldauth'},
        {title:'表单授权',key:'formauth'},
        {title:'流程属性',key:'formproperty'},
        //{title:'计算设置',key:'4'},
        //{title:'表单填写规则设置',key:'5'},
        //{title:'消息推送',key:'6'},
      ],
      cur:'publish'
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
      this.$http.post('/api/wf/form/get?id=' + this.id,{}).then((res) => {
         if (res.data.code === 0) {
                this.data = res.data.data;
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
        //alert('属性设置');
      }
      if(index == 3){
        this.$router.replace({path:'/workflow/reportdesign?id='+this.id});
      }
    },
    open(item){
      if(this.cur == item.key){
        return;
      }
      this.cur = item.key;
      this.$nextTick(() =>{
      setTimeout(() => {
        var page = this.$refs[this.cur];
        if(page){
          page.load(this.id);
        }
      }, 50)
      })
    },
    back:function(){
      this.$router.go(-1);
    },
  }
}
</script>

<style type="text/css" scoped>
  .wf-propertysetting-page{
    padding-top: 41px;
    height: 100%;
    background-color: #F9FAFB;
    position: relative;
    overflow:hidden;
  }
  .wf-propertysetting-body{
    height: 100%;
    background-color: white;
    padding-left: 160px;
  }
  .wf-propertysetting-bottom{
    text-align: center;
    padding: 6px;
  }
  .wf-propertysetting-bottom .ivu-btn{
    width: 130px;
  }
  .wf-propertysetting-page-tabs{
    margin-top: -41px;
  }
  .wf-propertysetting-left{
    position: absolute;
    left:0px;
    top:40px;
    bottom: 0px;
    border-right: 1px solid #eeeeee;
    width: 160px;
  }
  .wf-propertysetting-left-list-item{
    height: 40px;
    line-height: 40px;
    font-size: 14px;
    padding: 0px 10px;
    cursor: pointer;
  }
  .wf-propertysetting-left-list-item:hover{
    color: #5990cf;
  }
  .wf-propertysetting-left-list-item.cur{
    color: #5990cf;
  }
  .wf-propertysetting-right{
    overflow-y: auto;
    height: 100%;
  }
  .wf-propertysetting-right-new{

  }
</style>
