<template>
  <div :class="classes">
    <div class="process-body">
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
      <div class="process-footer">
        <div class="process-footer-wrap">
          <Button  @click="operate" type="primary">处 理</Button>
          <Button @click="viewFlow">查看流程</Button>
        </div>
      </div>
      <processComment v-if="showComment" @do="submit" @close="commentClose"></processComment>
    </div>
    <processBackModal ref="backModal" @ok="backModalOk"></processBackModal>
    <Loading :loading="loading"></Loading>
    <flowGraph ref="flowGraph"></flowGraph>
    <selectDeptPerson ref="selectDeptPerson"></selectDeptPerson>
  </div>
</template>
<script>
import mobileForm from '@/components/workflow/components/form/MobileForm';
import processComment from '@/components/workflow/process/processComment';
import processBackModal from '@/components/workflow/process/processBackModal';
import Loading from '@/components/loading';
import page from '@/assets/js/page';
import flowGraph from '@/components/workflow/initProc/flowGraph';
import selectDeptPerson from '@/components/workflow/components/SelectDeptPerson';

export default {
  name:'process',
  components: {
    mobileForm,
    processComment,
    processBackModal,
    Loading,
    flowGraph,
    selectDeptPerson
  },
  data() {
    return {
      instId:0, // 流程实例
      taskId:0,
      formData:{
        title:'标题',
        description:'描述',
      },
      fieldValue:{},
      fields:[],
      fieldAuth:[],
      flow:[],
      curFieldAuth:{},
      tasks:[],
      showComment:false,
      loading:0,
    }
  },
  computed:{
    classes(){
      return [
        'process'
      ];
    }
  },
  mounted(){
    this.load();
  },
  methods:{
    load(){
      if(this.$route.query.task && this.$route.query.task >0){
        this.taskId = this.$route.query.task;
      }else{
        this.$Message.error('缺少参数 task！');
        return;
      }

      if(this.$route.query.inst && this.$route.query.inst >0){
        this.instId = this.$route.query.inst;
        this.loadProcess();
      }else{
        this.$Message.error('缺少参数 inst');
        return;
      }
    },
    loadForm:function (id) {
      var that = this;
      this.loading = 1;
      this.$http.post('/api/wf/form/get?id=' + id, {}).then((res) => {
        this.loading = 0;
        if (res.data.code == 0) {
          that.formData = res.data.data;
          this.fields = this.evalJson(this.formData.fieldsJson) || [];
          this.flow = this.evalJson(this.formData.flowJson) || [];
          this.fieldAuth = this.evalJson(this.formData.fieldAuthJson) || [];
 
          // 还原值
          that.$nextTick(()=>{
            this.setCurFieldAuth();
            // 设置默认值
            this.setDefault();
            that.$refs['mobileForm'].setValue(this.fieldValue); 
          })
          
        } else {
          that.$Message.error(res.data.message);
        }
      }).catch((error) => {
        this.loading = 0;
        that.$Message.error(error);
      });
    },
    loadProcess:function(){
      this.loading = 1;
      this.$http.get('/api/wf/process/getProcessInst?instId='+this.instId).then((res) => {
        this.loading = 0;
        if (res.data.code === 0) {
          var data = res.data.data;
          if(data){
            this.fieldValue = {};
            // 转换表单字段的值
            for(var i=0;i<data.varList.length;i++){
              var keyVal = data.varList[i];
              this.fieldValue[keyVal.variableName] = keyVal.value;
            }
            // 获取任务
            this.tasks = data.tasks;

            // 加载表单定义
            this.loadForm(data.formId);
          }else{
            this.$Message.info("流程实例不存在！");
          }
        } else {
          this.$Message.error(res.data.message);
        }
      }).catch((error) => {
        this.loading = 0;
        this.$Message.error(error.toString())
      })
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

      var tasksObj = {};
      if(this.tasks){
        this.tasks.map((t)=>{
          tasksObj[t.taskId] = t;
        });
      }

      var flowObj = {};
      if(this.flow){
        this.flow.map((f)=>{
          flowObj[f.id] = f;
        });
      }

      if(tasksObj[this.taskId]){
        var flowId = tasksObj[this.taskId].flowId;
        if(flowObj[flowId]){
          var fieldAuth = flowObj[flowId].fieldAuth;
          if(fas[fieldAuth]){
            this.curFieldAuth = fas[fieldAuth].fields;
          }else{
            if(fieldAuth == 'edit'){
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
            if(fieldAuth == 'browse'){
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
      }
    },
    setDefault(){ 
      var fieldValue = this.fieldValue;
      this.fields.map((field)=>{
        var auth = this.curFieldAuth[field.id];
        if(auth && auth.auth == '1'){
          if(auth.default != '' && auth.default != null){
            if(fieldValue[auth.id] == '' 
              || fieldValue[auth.id] == null 
              || typeof(fieldValue[auth.id]) == 'undefined')
            {
              if(['text','textarea'].indexOf(field.type)>=0){
                fieldValue[auth.id] = auth.default; 
              }
              if(['person','multi_person'].indexOf(field.type)>=0){ 
                if(auth.default == 'org_currentUser'){
                  if(this.$user.empInfo){ 
                    fieldValue[auth.id + '_ids'] = this.$user.empInfo.empId;
                    fieldValue[auth.id] = this.$user.empInfo.trueName;
                  }
                }
              }
              if(['department','multi_department'].indexOf(field.type)>=0){
                if(auth.default == 'org_currentUserDepartment'){
                  if(this.$user.empInfo){ 
                    fieldValue[auth.id + '_ids'] = this.$user.empInfo.deptId;
                    fieldValue[auth.id] = this.$args.getArgText('deptList',this.$user.empInfo.deptId);
                  }
                } 
              }
              if(['date'].indexOf(field.type)>=0){
                //fieldValue[auth.id] = auth.default;
                if(auth.default == 'date_currentDateTime'){
                  if(field.format == 1){
                    fieldValue[auth.id] = page.formatDate(new Date());
                  }
                  if(field.format == 2){
                    fieldValue[auth.id] = page.formatDateTime(new Date());
                  }
                }
              }
            }
          }
        }
      });
    },
    submit(comment,action){
      if(action == 'submit'){
        var mf = this.$refs["mobileForm"];
        if(!mf.validate()){
          this.$Message.error('有验证项未通过！');
          return;
        }

        var fd = this.buildFormData();
        fd.taskId = this.taskId;
        fd.remark = comment.comment;
        this.loading = 1;
        this.$http.post('/api/wf/process/task/complete',fd).then((res) => {
          if (res.data.code === 0) {
            this.$Message.info("操作成功！");
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
        this.showComment = false;
      }
      if(action == 'stop'){
        this.loading = 1;
        var fd = this.buildFormData();
        fd.taskId = this.taskId;
        fd.instanceId = this.instId;
        fd.remark = comment.comment;
        this.$http.post('/api/wf/process/endProcess',fd).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.$Message.info("操作成功！");
            this.close();
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error(error.toString())
        })
      }
      if(action == 'back'){
        var backModal = this.$refs['backModal'];
        backModal.show();
      }
    },
    operate(){
      this.showComment = true;
    },
    backModalOk(){
      var backModal = this.$refs['backModal'];
      var fd = this.buildFormData();
      fd.taskId = this.taskId;
      fd.remark = backModal.remark;
      fd.instanceId = this.instId;
      var action = 'backStartUser';
      if(backModal.back == 2){
        action = 'backToPrev';
      }
      this.loading = 1;
      this.$http.post('/api/wf/process/backToPrev',fd).then((res) => {
        this.loading = 0;
        if (res.data.code === 0) {
          this.$Message.info("操作成功！");
          this.close();
        } else {
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
    commentClose(){
      this.showComment = false;
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
  .process{
    position: relative;
    height: 100%;
    width: 100%;
    overflow: hidden;
  }
  .process-body{
    height: 100%;
    width: 100%;
    height: 100%;
    overflow: auto;
    position: relative;
    padding-bottom: 56px;
  }
  .process-footer{
    height: 56px;
    position: absolute;
    left:0px;
    bottom: 0px;
    right:0px;
    text-align: center;
    background-color: #646464;
  }
  .process-footer-wrap{
    display: inline-block;
    margin-top: 10px;
  }
  .process-footer .ivu-btn{
    margin:0px 10px;
    width: 100px;
  }

  .process-comment{
     z-index: 102
  }
</style>
