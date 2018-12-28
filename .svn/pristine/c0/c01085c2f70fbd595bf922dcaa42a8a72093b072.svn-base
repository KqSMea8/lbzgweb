<template>
  <Modal
    v-model="display"
    :closable="false"
    :footerHide="true"
    title="" 
    width="900">
    <div class="wf-flownodeedit">
        <table class="wf-flownodeedit-form">
          <tr>
            <td class="wf-flownodeedit-form-label">执行人</td>
            <td>{{data.executor}}</td>
            <td></td>
            <td></td>
          </tr>
          <tr>
            <td class="wf-flownodeedit-form-label">执行模式</td>
            <td>
              <RadioGroup v-model="data.execPattern">
                <Radio :label="1" :disabled="execPatternDisabled">
                    单人执行
                </Radio>
                <Radio :label="2" :disabled="execPatternDisabled">
                    多人执行
                </Radio>
                <Radio :label="3" :disabled="execPatternDisabled">
                    全体执行
                </Radio>
                <Radio :label="4" :disabled="execPatternDisabled">
                    竞争执行
                </Radio>
              </RadioGroup> 
            </td>
            <td>是否为知会节点</td>
            <td>
              <iSwitch v-model="data.notify" :true-value="true" :false-value="false"></iSwitch> 
            </td>
          </tr>
          <tr>
            <td class="wf-flownodeedit-form-label">处理权限</td>
            <td>
              <CheckboxGroup v-model="data.flowAuth">
                <!--<Checkbox :label="1" v-if="!data.notify">加签</Checkbox>-->
                <Checkbox :label="2" v-if="!data.notify">回退</Checkbox>
                <Checkbox :label="3" v-if="!data.notify">终止</Checkbox>
                <Checkbox :label="4">知会</Checkbox>
              </CheckboxGroup>
            </td> 
          </tr>
        </table>
        <FieldAuth ref="fieldAuth" style="height:290px"></FieldAuth>
        <div class="wf-flownodeedit-btns">
          <Button type="primary" @click="ok">确定</Button>
          <Button type="default" @click="cancel">取消</Button>
        </div>
    </div>
  </Modal>
</template>
<script>
import FieldAuth from '@/components/workflow/formPage/FieldAuthComponent';

export default { 
  components: {
    FieldAuth
  },
  props:{

  },
  data() {
    return {
      display:false, 
      data:{
        branch:'',
        execPattern :1,
        executor:'', 
        executorId :'',
        fieldAuth :'edit',
        flowAuth :[],
        notify:false,
        type:''
      },
      fields:[],
      groups:[],
    }
  },
  mounted:function(){
     
  },
  computed:{
    execPatternDisabled(){
      return this.data.notify || this.data.type == 'person';
    }
  },
  watch:{
    'data.notify':function(val){
      if(val){
        if(this.data.type == 'person'){
          this.data.execPattern = 1;
        }else{
          this.data.execPattern = 3;
        }
      }
    }
  },
  methods:{  
    ok:function(){ 
      this.display = false; 
     
      if(this.$refs.fieldAuth.cur.id){
        this.data.fieldAuth = this.$refs.fieldAuth.cur.id;
      }

      this.$emit('ok');
      if(this.callback){
        this.callback(this.data);
      }
    },
    cancel:function(){
      this.display = false; 
      this.$emit('cancel');
    },
    show(data,callback){ 
      this.display = true;
      this.data.branch = data.branch;
      this.data.execPattern = data.execPattern;
      this.data.executor = data.executor;
      this.data.executorId = data.executorId; 
      this.data.fieldAuth = data.fieldAuth;
      this.data.notify = data.notify;
      this.data.type = data.type;

      if(data.type == 'person'){
        this.data.execPattern = 1;
      }

      if(typeof(data.flowAuth) == 'object'){
        this.data.flowAuth = data.flowAuth;
      }else{
        this.data.flowAuth = [];
      }
      this.data.type = data.type;

      this.$refs.fieldAuth.fields = this.fields;
      this.$refs.fieldAuth.groups = this.groups;

      this.$refs.fieldAuth.setCurId(this.data.fieldAuth);

      this.callback = callback;
    }
  }
}

</script>

<style type="text/css">
  .wf-flownodeedit{ 
    height: 450px;
    font-size: 14px;
  }

  .wf-flownodeedit-form{
    width: 100%; 
  }
  .wf-flownodeedit-form td{
    line-height: 34px;height: 34px;
    padding: 0px 10px; 
  }

  .wf-flownodeedit-form-label{
    text-align: left;
    width: 80px;
  }
  .wf-flownodeedit-btns{
    text-align: center;
    padding: 10px;
  }
  .wf-flownodeedit-btns .ivu-btn{
    width: 90px;margin:0px 10px;
  }
</style>