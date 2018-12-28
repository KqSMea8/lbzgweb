<template>
  <div :class="classes">

    <div class="initproc-left">
      <menuNav ref="menuNav" :curFormId="activeName" @on-open="open"></menuNav>
    </div>
    <div class="initproc-body">
      <mobileForm ref="mobileForm"
        :title="formData.title"
        :description="formData.description"
        :fieldOptions="fields"
        :fieldAuth="curFieldAuth"
        @selectPerson="selectPerson"
        @selectDept="selectDept"
        @selectMultiPerson="selectMultiPerson"
        @selectMultiDept="selectMultiDept"
      ></mobileForm>

      <div class="initproc-footer">
        <div class="initproc-footer-wrap">
          <Button  @click="save" type="primary">立即发送</Button>
           <Button @click="saveDraft">保存待发</Button>
           <Button @click="viewFlow">查看流程</Button>
        </div>
      </div>
    </div>
    <!--<Loading v-if="loading"></Loading>-->
    <flowGraph ref="flowGraph"></flowGraph>
    <selectDeptPerson ref="selectDeptPerson"></selectDeptPerson>
  </div>
</template>
<script>
import menuNav from '@/components/workflow/initProc/menuNavComponent';
import mobileForm from '@/components/workflow/components/form/MobileForm';
import flowGraph from '@/components/workflow/initProc/flowGraph';
import Loading from '@/components/loading';
import selectDeptPerson from '@/components/workflow/components/SelectDeptPerson';

export default {
  name:'initproc',
  components: {
    menuNav,
    mobileForm,
    Loading,
    flowGraph,
    selectDeptPerson
  },
  data() {
    return {
      formid:0, // 流程表单
      draft:0, // 草稿
      draftData:{},
      activeName:0,
      formData:{
        title:'标题',
        description:'描述',
      },
      fields:[],
      fieldAuth:[],
      flow:[],
      curFieldAuth:{},
      loading:0,
    }
  },
  computed:{
    classes(){
      return [
        'initproc',
        (this.formid>0 || this.draft>0)?'initproc-left-hiden':''
      ];
    }
  },
  mounted(){
    this.load();
  },
  methods:{
    load(){

      var menuNav = this.$refs.menuNav;
      var that =this;
      // 新建指定的流程
      if(this.$route.query.formid){
        this.formid = this.$route.query.formid;
      }
      if(this.$route.query.draft){
        this.draft = this.$route.query.draft;
      }
      if(this.formid>0){
        this.formid = this.$route.query.formid;
        this.open({id:this.formid});
      }else
      if(this.draft > 0){
        this.loadLoadDraft();
      }else{
        menuNav.load(()=>{
        if(menuNav.items.length>0){
            that.open(menuNav.items[0]);
            this.$nextTick(()=>{
              that.activeName = menuNav.items[0].id;
            });
          }
        });
      }
    },
    loadForm:function (id) {
      this.loading = 1;
      this.$http.post('/api/wf/form/get?id=' + id, {}).then((res) => {
        this.loading = 0;
        if (res.data.code == 0) {
          this.formData = res.data.data;
          this.fields = this.evalJson(this.formData.fieldsJson) || [];
          this.flow = this.evalJson(this.formData.flowJson) || [];
          this.fieldAuth = this.evalJson(this.formData.fieldAuthJson) || [];
          this.setCurFieldAuth();
          // 还原值
          if(this.draft>0 && this.draftData){
            var fieldsJson = this.evalJson(this.draftData.fieldsJson);
            this.$nextTick(()=>{
              this.$refs['mobileForm'].setValue(fieldsJson);
            })
          }
        } else {
          this.$Message.error(res.data.message);
        }
      }).catch((error) => {
        this.loading = 0;
        this.$Message.error(error.toString());
      });
    },
    evalJson(json){
      if(json && json != null && (json[0] == '{' || json[0] == '[')){
        return eval('(' + json +')');
      }
      return null;
    },
    setCurFieldAuth(){
      var fas = {};
      this.fieldAuth.map((fa)=>{
          fas[fa.id] = fa;
        });

      if(this.flow.length>0){
        var id = this.flow[0].fieldAuth;
        if(fas[id]){
          this.curFieldAuth = fas[id].fields;
        }else{
          if(id == 'edit'){
            var fields = {};
            this.fields.map((f)=>{
              fields[f.id] = {
                "id":f.id,
                "title":f.title,
                "auth":1,
                "require":false,
                "default":"",
                "defaultText":""
              };
            });
            this.curFieldAuth = fields;
          }
          if(id == 'browse'){
            var fields = {};
            this.fields.map((f)=>{
              fields[f.id] = {
                "id":f.id,
                "title":f.title,
                "auth":2,
                "require":false,
                "default":"",
                "defaultText":""
              };
            });
            this.curFieldAuth = fields;
          }
        }
      }
    },
    loadLoadDraft:function(){
      this.loading = 1;
      this.$http.get('/api/wf/draft/get?id='+this.draft).then((res) => {
        this.loading = 0;
        if (res.data.code === 0) {
          var data = res.data.data;
          if(data){
            this.draftData = data;
            this.loadForm(data.formId);
          }else{
            this.$Message.info("草稿不存！");
          }
        } else {
          this.$Message.error(res.data.message);
        }
      }).catch((error) => {
        this.loading = 0;
        this.$Message.error(error.toString())
      })
    },
    open(item) {
      this.loadForm(item.id);
    },
    save(){
      var mf = this.$refs["mobileForm"];
      if(!mf.validate()){
        this.$Message.error('有验证项未通过！');
        return;
      }

      var fd = this.buildFormData();
      fd.processKey = this.formData.id;
      fd.draftId = this.draft;
      this.loading = 1;
      this.$http.post('/api/wf/process/start',fd).then((res) => {

        if (res.data.code === 0) {
          this.$Message.info("流程发起成功");
          setTimeout(()=>{
            this.loading = 0;
            this.close();
          },1000);
        } else {
          this.loading = 0;
          this.$Message.error(res.data.message);
        }
      }).catch((error) => {
        this.loading = 0;
        this.$Message.error(error.toString())
      })
    },
    saveDraft(){
      var fd = this.buildFormData();
      fd.formId = this.formData.id;
      if(this.draft > 0){
        fd.id = this.draft;
      }
      this.loading = 1;
      this.$http.post('/api/wf/draft/create',fd).then((res) => {

        if (res.data.code === 0) {
          this.$Message.info("流程保存成功");
          setTimeout(()=>{
            this.loading = 0;
            this.close();
          },1000);
        } else {
          this.loading = 0;
          this.$Message.error(res.data.message);
        }
      }).catch((error) => {
        this.loading = 0;
        this.$Message.error(error.toString())
      })
    },
    viewFlow(){
      if(this.formData && this.formData.flowJson){
        try{
          var json = eval('('+ this.formData.flowJson +')');
          this.$refs.flowGraph.flowNodes = json;
          this.$refs.flowGraph.show();
        }catch(ex){
          this.$Message.error('转换数据出错！')
        }
      }
    },
    buildFormData(){
      var obj = this.$refs["mobileForm"].getValue();
      var retVal = {};
      for(var name in obj){
        retVal['fp_'+name] = obj[name];
      }
      return retVal;
    },
    close(refresh){
      if(window.opener &&window.opener.$app){
        window.opener.$app.$bus.$emit('on-subwin-change');
      }
      window.close();
    },
    selectPerson(field){
      var pop = this.$refs.selectDeptPerson;
      var mobileForm = this.$refs.mobileForm;

      pop.selectPerson = true;
      pop.selectDept = false;
      pop.isMulti = false;

      pop.$off('ok');
      pop.$on('ok',()=>{
        pop.$off('ok');

        var vals = mobileForm.getValue();
        vals[field.model.id] =
        pop.select.map((person)=>{
          return person.title;
        }).join(',');

        vals[field.model.id+'_ids'] =
        pop.select.map((person)=>{
          return person.key;
        }).join(',');

        mobileForm.setValue(vals);
      });
      pop.show();
      pop.select = this.innerGetDeptPerson(field);
    },
    selectDept(field){
      var pop = this.$refs.selectDeptPerson;
      var mobileForm = this.$refs.mobileForm;

      pop.selectPerson = false;
      pop.selectDept = true;
      pop.isMulti = false;

      pop.$off('ok');
      pop.$on('ok',()=>{
        pop.$off('ok');

        var vals = mobileForm.getValue();
        vals[field.model.id] =
        pop.select.map((person)=>{
          return person.title;
        }).join(',');

        vals[field.model.id+'_ids'] =
        pop.select.map((person)=>{
          return person.key;
        }).join(',');

        mobileForm.setValue(vals);
      });
      pop.show();
      pop.select = this.innerGetDeptPerson(field);
    },
    selectMultiPerson(field){
      var pop = this.$refs.selectDeptPerson;
      var mobileForm = this.$refs.mobileForm;

      pop.selectPerson = true;
      pop.selectDept = false;
      pop.isMulti = true;

      pop.$off('ok');
      pop.$on('ok',()=>{
        pop.$off('ok');

        var vals = mobileForm.getValue();
        vals[field.model.id] =
        pop.select.map((person)=>{
          return person.title;
        }).join(',');

        vals[field.model.id+'_ids'] =
        pop.select.map((person)=>{
          return person.key;
        }).join(',');

        mobileForm.setValue(vals);
      });
      pop.show();
      pop.select = this.innerGetDeptPerson(field);
    },
    selectMultiDept(field){
      var pop = this.$refs.selectDeptPerson;
      var mobileForm = this.$refs.mobileForm;

      pop.selectPerson = false;
      pop.selectDept = true;
      pop.isMulti = true;

      pop.$off('ok');
      pop.$on('ok',()=>{
        pop.$off('ok');

        var vals = mobileForm.getValue();
        vals[field.model.id] =
        pop.select.map((person)=>{
          return person.title;
        }).join(',');

        vals[field.model.id+'_ids'] =
        pop.select.map((person)=>{
          return person.key;
        }).join(',');

        mobileForm.setValue(vals);
      });
      pop.show();
      pop.select = this.innerGetDeptPerson(field);
    },
    innerGetDeptPerson(field){
      var arr = [];
      //{type:'person',key:'zhangsan',title:'张三'}
      var vals = this.$refs.mobileForm.getValue();
      var ids = (vals[field.model.id+'_ids'] || '').split(',');
      var titles = (vals[field.model.id] || '').split(',');

      for(var i=0;i<ids.length;i++){
        var item = {type:'',key:'',title:''};
        item.key = ids[i];
        if(item.key != ''){
          if(titles.length>i){
            item.title = titles[i];
          }
          if(field.model.type == 'person'
            || field.model.type == 'multi_person'){
            item.type = 'person';
          }
          if(field.model.type == 'department'
            || field.model.type == 'multi_department'){
            item.type = 'department';
          }
          arr.push(item);
        }
      }
      return arr;
    }
  }
}

</script>
<style type="text/css">
  /*布局*/
  .initproc{
    position: relative;
    height: 100%;
    width: 100%;
    padding-left:210px;
    overflow: hidden;
  }
  .initproc-left {
    height:100%;
    width:auto;
    position: absolute;
    left:0px;
    top:0px;
    bottom:0px;
    background-color: white;
    /*border-right: 1px solid #eeeeee;*/
  }
  .initproc-left:after {
    content: '';
    display: block;
    width: 1px;
    height: 100%;
    background: #eeeeee;
    position: absolute;
    right: 0;
    top: 0;
  }
  .initproc-left-hiden{
    padding-left: 0px;
  }
  .initproc-left-hiden .initproc-left{
    display: none;
  }
  .initproc-body{
    height: 100%;
    width: 100%;
    height: 100%;
    overflow: auto;
    position: relative;
    padding-bottom: 56px;
  }
  .initproc-footer{
    height: 56px;
    position: absolute;
    left:0px;
    bottom: 0px;
    right:0px;
    text-align: center;
    background-color: #646464;
  }
  .initproc-footer-wrap{
    display: inline-block;
    margin-top: 10px;
  }
  .initproc-footer .ivu-btn{
    margin:0px 10px;
  }
</style>
