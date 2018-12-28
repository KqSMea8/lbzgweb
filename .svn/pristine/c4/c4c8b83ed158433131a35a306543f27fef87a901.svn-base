<template> 
  <div :class="classes">
    <div class="wf-property-fieldsauth-left">
      <Button @click="add" type="default" class="wf-property-fieldsauth-add">新建字段权限</Button>
      <div v-for="(item,index) in defaultGroups" 
        @click="switchCur(item)"
        :class="{
          'wf-property-fieldsauth-left-item':1,
          'cur':item == cur
          }">
        {{item.title}}
      </div> 
      <div class="wf-property-fieldsauth-left-split"></div>
      <div v-for="(item,index) in groups" 
        @click="switchCur(item)"
        :class="{
          'wf-property-fieldsauth-left-item':1,
          'cur':item == cur
          }">
        {{item.title}}
      </div>
    </div>
    <div class="wf-property-fieldsauth-right">
      <table class="wf-property-fieldsauth-table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
          <td colspan="4">
            字段权限名称&nbsp;
            <Input v-model="title" placeholder="" style="width: 200px" :readonly="!this.edit"></Input>
            &nbsp;&nbsp;&nbsp;
            <a v-if="!edit" @click="goEdit">编辑</a>
          </td> 
        </tr>
        <tr class="wf-property-fieldsauth-table-head">
          <td>操作权限</td>
          <td>
            <RadioGroup v-model="selall">
              <Radio :label="1" :disabled="!edit">
                  全选
                </Radio>
                <Radio :label="2" :disabled="!edit">
                  全选
                </Radio>
                <Radio :label="3" :disabled="!edit">
                  全选
                </Radio>
                <Radio :label="4" :disabled="!edit">
                  全选
                </Radio>
            </RadioGroup> 
          </td>
          <td><Checkbox @click.prevent.native="checkRequire" v-model="selallRequire" :true-value="true" :false-value="false" :disabled="!edit">必填</Checkbox></td>
          <td width="140">初始值设置</td>
        </tr>
        </thead>
        <tbody>
          <tr v-for="(item,index) in curFields">
            <td>{{item.title}}</td>
            <td>
              <RadioGroup v-model="item.auth">
                <Radio :label="1" :disabled="item.auth!=1 && !edit">
                  编辑
                </Radio>
                <Radio :label="2" :disabled="item.auth!=2 && !edit">
                  浏览
                </Radio>
                <Radio :label="3" :disabled="item.auth!=3 && !edit">
                  隐藏
                </Radio>
                <Radio :label="4" :disabled="item.auth!=4 && !edit">
                  不可见
                </Radio>
              </RadioGroup> 
            </td>
            <td>
              <Checkbox v-model="item.require" :true-value="true" :false-value="false" :disabled="!edit">必填</Checkbox>
            </td>
            <td>
              <!--'text','textarea','number','select','checkbox','radio','date','open','upload_pic','upload','serial_number','location','separator','picture','description','comment','detail','address','person','department','multi_person','multi_department'-->
              <Input v-if="['text','textarea'].indexOf(item.field.type)>=0"
                v-model="item.default" :disabled="!edit"></Input>
              <Select v-if="['person','multi_person'].indexOf(item.field.type)>=0"
                v-model="item.default" :disabled="!edit">
                <Option value="">请选择</Option>
                <Option value="org_currentUser">
                  当前登录人
                </Option>
              </Select>
              <Select v-if="['department','multi_department'].indexOf(item.field.type)>=0"
                 v-model="item.default" :disabled="!edit" >
                <Option value="">请选择</Option>
                <Option value="org_currentUserDepartment">
                  当前登录人所在部门
                </Option>
              </Select>
              <Select v-if="['date'].indexOf(item.field.type)>=0"
                 v-model="item.default" :disabled="!edit">
                <Option value="">请选择</Option>
                <Option value="date_currentDateTime">
                  系统日期时间
                </Option>
                <!--
                <Option value="date_currentMonthStart">
                  本月初日期
                </Option>
                <Option value="date_currentMonthEnd">
                  本月末日期
                </Option>
                <Option value="date_currentYearStart">
                  本年初日期
                </Option>
                <Option value="date_currentYearEnd">
                  本年末日期
                </Option>-->
              </Select> 
              <Select v-if="['select'].indexOf(item.field.type)>=0"
                 v-model="item.default" :disabled="!edit">
                <Option value="">请选择</Option>
                <Option :value="option.value" v-for="option in item.field.enum_data">option.text</Option>
              </Select>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-if="edit" class="wf-property-fieldsauth-bottom">
      <Button @click="saveas" type="ghost">另存为新权限</Button>
      <Button @click="save" type="primary" v-if="cur.id!='edit' && cur.id!='browse'">保存</Button>
      <Button @click="cancel" type="default">取消</Button>
    </div>
  </div>
</template>
<script>
var short = require('short-uuid');
var translator = short();

export default { 
  components: {
     
  },
  props:{

  },
  data() {
    return {
      id:0,
      defaultGroups:[ 
        {
          "id":"edit",
          "title":"编辑",
          "fields":{}
        },
        {
          "id":"browse",
          "title":"浏览",
          "fields":{}
        }
      ],
      groups:[],
      cur:{},
      title:'',
      curFields:[],
      selall:0,
      selallRequire:0,
      fields:[],
      edit:false,
      loading:0,
      data:{}
    }
  },
  mounted:function(){
    /* debug
    this.fields = [{"type":"text","id":"field_qnRzzupZB693t4PhtwbR3","title":"姓名","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0}];

    this.groups = [{
      "id":"001",
      "title":"审核人员",
      "fields":{
        "field_qnRzzupZB693t4PhtwbR3":{
            "auth":1,
            "require":true,
            "default":"本月初始日期"
        },
        "field_7DTTznkvuFxaMd4ARFC5pY":{
            "auth":2,
            "require":false,
            "default":"本月初始日期"
        }
      }
    }];*/
  },
  computed:{
    classes(){
      return {
        'wf-property-fieldsauth':true,
        'wf-property-fieldsauth-edit':this.edit
      };
    }
  },
  watch:{  
    selall(val){
      if(val >= 1){
        for(var i = 0;i<this.curFields.length;i++){
          this.curFields[i].auth = val;
        }
      }
    }
  },
  methods:{
    load(id){
      this.id = id;
      this.loading = 1; 
        this.$http.post('/api/wf/form/get?id=' + id, {}).then((res) => {
          this.loading = 0; 
          if (res.data.code == 0) {
            if(res.data.data){
              this.data = res.data.data;
              if(this.data.fieldsJson && this.data.fieldsJson[0] == '['){
                try{
                  this.fields = eval('('+this.data.fieldsJson+')');
                }catch(ex){
                  console.log('eval fieldsJson error');
                }
              }
              if(this.data.fieldAuthJson && this.data.fieldAuthJson[0] == '['){
                try{
                  this.groups = eval('('+this.data.fieldAuthJson+')');
                }catch(ex){
                  console.log('eval fieldsJson error');
                }
              }
              this.switchCur(this.defaultGroups[0]);
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
    },
    switchCur(group){
      this.cur = group;
      var items = [];

      for(var i =0;i<this.fields.length;i++){
        var field = this.fields[i];
        var item ={};
        item.field = field;
        item.id = field.id;
        item.title = field.title;

        var fieldAuth = this.cur.fields[field.id]
        if(this.cur.id=='edit'){
          fieldAuth = {
            auth:1,
            require:false,
            default:'',
            defaultText:'',
          };
        }

        if(this.cur.id=='browse'){
          fieldAuth = {
            auth:2,
            require:false,
            default:'',
            defaultText:'',
          };
        }

        fieldAuth = fieldAuth || {
          auth:1,
          require:false,
          default:'',
          defaultText:'',
        };


        item.auth = fieldAuth.auth;
        item.require = fieldAuth.require;
        item.default = fieldAuth.default;
        item.defaultText = fieldAuth.defaultText;

        items.push(item);
      }
      this.curFields = items;
      this.title = group.title;

      this.edit = false;
      this.selall = 0;
      this.selallRequire = false;
    },
    setCurId(id){ 
      var find = null;
      this.groups.map((item)=>{
        if(item.id == id){
          find = item;
        }
      });

      if(id == 'edit'){
        find = this.defaultGroups[0];
      }
      if(id == 'browse'){
        find = this.defaultGroups[1];
      }

      if(find){
        this.switchCur(find);
      }else{
        this.switchCur(this.defaultGroups[0]);
      }

      this.edit = false;
      this.selall = 0;
      this.selallRequire = false;
    },
    add(){
      this.switchCur({id:translator.new(),fields:{}});
      this.edit = true;
    },
    goEdit(){
      this.edit = true;
    },
    saveas(){
      /*[
          {
              "id":"001",
              "title":"编辑",
              "fields":{
                  "field_qeLDm3tn3AHigXGqqa6oNa":{
                      "auth":1,
                      "require":true,
                      "default":"本月初始日期"
                  }
              }
          }
      ]*/
      var group = this.getForm();
      if(group){
        group.id = translator.new();
        this.inner_save_group(group);
      }
    },
    save(){
      var group = this.getForm();
      if(group){
        group.id = this.cur.id; 
        this.inner_save_group(group);
      }
    },
    inner_save_group(group){ 
      if(group){  
        var groups = {};
        for(var i = 0;i<this.groups.length;i++){
          groups[this.groups[i].id] = this.groups[i];
        } 
        groups[group.id] = group;

        var gs = [];
        for(var name in groups){  
          var trans = Object.assign({},groups[name]); 
          for(var n1 in trans.fields){
            delete trans.fields[n1].field;
          }
          gs.push(trans);
        }

        this.inner_save(gs,group.id);
      }
    },
    inner_save(groups,id){
      this.data.fieldAuthJson = JSON.stringify(groups);

      this.$http.post('/api/wf/form/update', this.data).then((res) => {
        if (res.data.code == 0) {
          this.$Message.info('保存成功!'); 
          this.groups = groups;
          this.setCurId(id);
        } else { 
          this.$Message.error(res.data.message)
        }
      }).catch((error) => { 
        this.$Message.error(error.toString())
      });
    },    
    getForm(){
      var group = {};
      group.id = '';
      group.title = this.title;
      group.fields = {};
      for(var i =0;i<this.curFields.length;i++){
        var field = this.curFields[i];
        group.fields[field.id] = field;
      }
      if(this.title == ''){ 
        this.$Message.error('请录入字段权限名称')
        return null;
      }
      return group;
    },
    cancel(){
      this.switchCur(this.cur);
    },
    checkRequire(){
      if(this.edit){
        this.selallRequire = !this.selallRequire;
        for(var i = 0;i<this.curFields.length;i++){
          this.curFields[i].require = this.selallRequire;
        }
      } 
    }
  }
}

</script>

<style type="text/css">
  .wf-property-fieldsauth{
    height: 100%; 
    padding-left:160px;
    position: relative;
    overflow: hidden; 
  } 

  .wf-property-fieldsauth-left{
    position: absolute;
    left:0px;
    top:0px;
    bottom:0px;
    width:160px;
    background-color: #f5f6f8;
    border-radius: 3px;
    overflow-y: auto;
    overflow-x: hidden;
  }
  .wf-property-fieldsauth-left-item{
    line-height: 38px;
    height: 38px;
    padding: 0px 10px;
    border-radius: 6px;
    margin:10px;
    cursor: pointer;
  }
  .wf-property-fieldsauth-left-item:hover{
    background-color: #e6e6e6;
  }
  .wf-property-fieldsauth-left-item.cur{
    background-color: #20bfee;
    color: white;
  }
  .wf-property-fieldsauth-left-split{
    height: 1px;
    margin:0px 10px;
    border-bottom: 1px solid #ccc;
  }
  .wf-property-fieldsauth-add{
    width: 140px;
    margin:10px 10px 0px 10px;
  }
  .wf-property-fieldsauth-right{
    height: 100%;
    overflow-y: auto;
    padding: 0px 10px;
  }

  .wf-property-fieldsauth-table{
    width: 100%;
  }
  .wf-property-fieldsauth-table td{
    padding: 10px;
  }
  .wf-property-fieldsauth-table-head td{
    border-bottom: 1px solid #ccc;
  }

  .wf-property-fieldsauth .ivu-checkbox-disabled.ivu-checkbox-checked .ivu-checkbox-inner {
    background-color: #2d8cf0;
    border-color: #2d8cf0;
  }

  .wf-property-fieldsauth-edit{
    padding-bottom: 52px;
  } 

  .wf-property-fieldsauth-bottom{
    padding: 10px; 
    border-top:1px solid #eee;
  }
  .wf-property-fieldsauth-bottom .ivu-btn{
    margin:0px 10px;
  }
</style>