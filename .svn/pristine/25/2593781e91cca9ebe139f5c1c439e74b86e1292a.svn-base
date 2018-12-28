<template>
  <div class="wf-fieldauth">
    <div class="wf-fieldauth-left">
      <div v-for="(item,index) in defaultGroups" 
        @click="switchCur(item)"
        :class="{
          'wf-fieldauth-left-item':1,
          'cur':item == cur
          }">
        {{item.title}}
      </div> 
      <div class="wf-fieldauth-left-split"></div>
      <div v-for="(item,index) in groups" 
        @click="switchCur(item)"
        :class="{
          'wf-fieldauth-left-item':1,
          'cur':item == cur
          }">
        {{item.title}}
      </div>
    </div>
    <div class="wf-fieldauth-right">
      <table class="wf-fieldauth-table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
          <td colspan="4">
            字段权限名称&nbsp;
            <Input v-model="cur.title" placeholder="" style="width: 200px" readonly></Input>
          </td> 
        </tr>
        <tr class="wf-fieldauth-table-head">
          <td>操作权限</td>
          <td width="260">
            <RadioGroup v-model="selall">
              <Radio :label="1" :disabled="true">
                  全选
                </Radio>
                <Radio :label="2" :disabled="true">
                  全选
                </Radio>
                <Radio :label="3" :disabled="true">
                  全选
                </Radio>
                <Radio :label="4" :disabled="true">
                  全选
                </Radio>
            </RadioGroup> 
          </td>
          <td width="70"><Checkbox v-model="selallRequire" :true-value="true" :false-value="false" :disabled="true">必填</Checkbox></td>
          <td width="140">初始值设置</td>
        </tr>
        </thead>
        <tbody>
          <tr v-for="(item,index) in curFields">
            <td>{{item.title}}</td>
            <td>
              <RadioGroup v-model="item.auth">
                <Radio :label="1" :disabled="item.auth!=1">
                  编辑
                </Radio>
                <Radio :label="2" :disabled="item.auth!=2">
                  浏览
                </Radio>
                <Radio :label="3" :disabled="item.auth!=3">
                  隐藏
                </Radio>
                <Radio :label="4" :disabled="item.auth!=4">
                  不可见
                </Radio>
              </RadioGroup> 
            </td>
            <td><Checkbox v-model="item.require" :true-value="true" :false-value="false" :disabled="true">必填</Checkbox></td>
            <td>
              <!--'text','textarea','number','select','checkbox','radio','date','open','upload_pic','upload','serial_number','location','separator','picture','description','comment','detail','address','person','department','multi_person','multi_department'-->
              <Input v-if="['text','textarea'].indexOf(item.field.type)>=0"
                v-model="item.default" :disabled="true"></Input>
              <Select v-if="['person','multi_person'].indexOf(item.field.type)>=0"
                v-model="item.default" :disabled="true">
                <Option value="">请选择</Option>
                <Option value="org_currentUser">
                  当前登录人
                </Option>
              </Select>
              <Select v-if="['department','multi_department'].indexOf(item.field.type)>=0"
                 v-model="item.default" :disabled="true" >
                <Option value="">请选择</Option>
                <Option value="org_currentUserDepartment">
                  当前登录人所在部门
                </Option>
              </Select>
              <Select v-if="['date'].indexOf(item.field.type)>=0"
                 v-model="item.default" :disabled="true">
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
                 v-model="item.default" :disabled="true">
                <Option value="">请选择</Option>
                <Option :value="option.value" v-for="option in item.field.enum_data">option.text</Option>
              </Select>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
export default { 
  components: {
     
  },
  props:{

  },
  data() {
    return { 
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
      curFields:[],
      selall:0,
      selallRequire:0,
      fields:[]
    }
  },
  mounted:function(){
    /*
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
          }];
    */
  },
  computed:{
    
  },
  watch:{  
    
  },
  methods:{  
    switchCur(group){
      this.cur = group;
      var items = [];

      for(var i =0;i<this.fields.length;i++){
        var field = this.fields[i];
        var item ={};
        item.title = field.title;
        item.field = field;

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
      var fields = items;
      this.curFields = fields;
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
    }
  }
}

</script>

<style type="text/css">
  .wf-fieldauth{
    height: 100%;
    padding-left:160px;
    position: relative;
    overflow: hidden;
  }

  .wf-fieldauth-left{
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
  .wf-fieldauth-left-item{
    line-height: 38px;
    height: 38px;
    padding: 0px 10px;
    border-radius: 6px;
    margin:10px;
    cursor: pointer;
  }
  .wf-fieldauth-left-item:hover{
    background-color: #e6e6e6;
  }
  .wf-fieldauth-left-item.cur{
    background-color: #20bfee;
    color: white;
  }
  .wf-fieldauth-left-split{
    height: 1px;
    margin:0px 10px;
    border-bottom: 1px solid #ccc;
  }
  .wf-fieldauth-right{
    height: 100%;
    overflow-y: auto;
    padding: 0px 10px;
  }

  .wf-fieldauth-table{
    width: 100%;
  }
  .wf-fieldauth-table td{
    padding: 10px;
  }
  .wf-fieldauth-table-head td{
    border-bottom: 1px solid #ccc;
  }

  .wf-fieldauth .ivu-checkbox-disabled.ivu-checkbox-checked .ivu-checkbox-inner {
    background-color: #2d8cf0;
    border-color: #2d8cf0;
  }
</style>