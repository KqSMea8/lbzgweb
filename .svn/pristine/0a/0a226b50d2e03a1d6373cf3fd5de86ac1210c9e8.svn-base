<template>
  <Modal
    v-model="display"
    :closable="false"
    :footerHide="true"
    :transfer ="transfer"
    title=""
    width="800">
    <div class="wf-selectperson">
      <div class="wf-selectperson-left">
        <Menu class="wf-selectperson-left-menu" mod="vertical" width="84" :active-name.sync="activeName">
          <MenuItem :name="item.key" :key="item.key" v-for="(item,index) in tabs">
              {{item.label}}
          </MenuItem>
        </Menu>
      </div>
      <div class="wf-selectperson-right">
        <Row>
          <Col span="12">
            <div class="wf-sel-dept wt-scroll">
               <SelectDept ref="deptComp"
               :showPerson="selectPerson"
               @on-select-dept="selectedDept" @on-select-emp="selectedEmp"></SelectDept>
            </div>
          </Col>
          <Col span="12" class-name="wf-selectperson-right-col">
            <div class="selectedbox  wt-scroll">
              <ul>
                <template v-for="item in select">
                  <li>
                    <Icon type="folder" class="icon-type" v-if="item.type == 'department' "></Icon>
                    <Icon type="android-contact" class="icon-type" v-if="item.type == 'person'"></Icon>
                    <Icon type="android-contacts" class="icon-type" v-if="item.type == 'role'"></Icon>
                    &nbsp;
                    <span>{{item.title}}</span>
                    <span class="icon-close" @click="cancelSelected(item)"><Icon type="ios-close-empty"></Icon></span>
                  </li>
                </template>
              </ul>
            </div>
            <div class="selected-btn">
                <div class="selected-footer">
                      <Button type="primary" @click="ok" style="width:100px;">确定</Button>
                      <Button @click="cancel" style="width:100px;">取消</Button>
                </div>
            </div>
          </Col>
        </Row>
      </div>
    </div>
  </Modal>
</template>
<script>
import SelectDept from '@/components/workflow/components/SelectDept';
export default {
  name:'selectPerson',
  components: {
    SelectDept
  },
  props:{
    transfer:{
      type:Boolean,
      default:false
    }
  },
  data() {
    return {
      display:false,
      select:[
        //{type:'person',key:'zhangsan',title:'张三'},
        //{type:'department',key:'1',title:'软件部'},
        //{type:'role',key:'role1',title:'角色'},
        //{type:'relative',key:'admin',title:'相对角色'},
        //{type:'field',key:'sdfsdfsdf',title:'表单字段'},
      ],
      tabs:[
        {label:'部门',key:'department'},
        //{label:'角色',key:'role'}
      ],
      activeName:'department',
      isMulti:false,
      selectPerson:true,
      selectDept:false,
      options:{}
    }
  },
  mounted:function(){

  },
  computed:{

  },
  watch:{

  },
  methods:{
    selectedDept(item){
      if(!this.selectDept){
        this.$Message.error('不能选择部门！');
        return;
      }
      if(this.select.length>=1 && !this.isMulti){
        this.$Message.error('只能选择一项!');
        return;
      }
      this.addSelect({type:'department',key:item.deptId,title:item.deptName});
    },
    selectedEmp(item){
      if(!this.selectPerson){
        this.$Message.error('不能选择人员！');
        return;
      }
      if(this.select.length>=1 && !this.isMulti){
        this.$Message.error('只能选择一项!');
        return;
      }
      this.addSelect({type:'person',key:item.empId,title:item.trueName,dep:item.deptId});
    },
    cancelSelected(item){
      var index = this.select.indexOf(item);
      this.select.splice(index,1);
    },
    addSelect(item){
      // {type:'person',key:'zhangsan',title:'张三'}
      // person,department,role,relative,field

      for(var i =0;i<this.select.length;i++){
        var select = this.select[i];
        if(select.type == item.type && select.key == item.key){
          return;
        }
      }

      if(this.selectPerson && item.type == 'person'){
        this.select.push(item);
      }
      if(this.selectDept && item.type == 'department'){
        this.select.push(item);
      }
    },
    ok:function(){
      this.display = false;
      if(this.options.ok){
        this.options.ok();
      }
    },
    cancel:function(){
      this.display = false;
      this.select = [];
      if(this.options.cancel){
        this.options.cancel();
      }
    },
    // 对外方法
    show(options){

      Object.assign(this.options,{
        isMulti:false,
        selectPerson:true,
        selectDept:false,
        select:[],
        ok:()=>{},
        cancel:()=>{}
      },options);

      this.select = this.options.select || [];
      this.isMulti = this.options.isMulti;
      this.selectPerson = this.options.selectPerson;
      this.selectDept = this.options.selectDept;

      this.display = true;
    }
  }
}

</script>

<style type="text/css">
  .wf-selectperson{
    background-color: #f5f6f8;
    padding-left: 85px;
    height: 450px;
  }
  .wf-selectperson-left{
    background-color: #e6e6e6;
    width: 84px;
    height: 100%;
    margin-left: -85px;
    float:left;
  }
  .wf-selectperson-left .ivu-menu-light{
    background-color: transparent;
  }
  .wf-selectperson-right .wf-selectperson-right-col{
    border-left: 1px solid #e6e6e6;
  }
  .wf-selectperson-right .wf-sel-dept{
    padding: 5px;
    height:450px;
    overflow:auto;
    overflow-x:hidden;
  }
  .wf-selectperson-right .wf-sel-groups{
    padding: 5px;
    height:450px;
    overflow:auto;
    overflow-x:hidden;
  }
  .wf-selectperson-right .selectedbox{
    height: 380px;
    margin:10px 10px;
    padding: 10px;
    overflow:auto;
    overflow-x:hidden;
    margin-bottom: 10px;
    background-color: white;
    border:1px solid #eee;
  }
  .wf-selectperson-right .selectedbox li{
    line-height: 34px;
    height: 34px;
    vertical-align: middle;
    display: block;
    padding: 0px 10px;
  }
  .wf-selectperson-right .selectedbox li:hover{
    background-color: #d5e8fc;
  }
  .wf-selectperson-right .selectedbox .icon-close{
    display: inline-block;
    float:right;
    cursor: pointer;
    height: 20px;
    width: 20px;
    line-height: 20px;
    border-radius: 3px;
    font-size: 20px;
    margin-top: 7px;
    text-align: center;
    border:1px solid #ccc;
  }
  .wf-selectperson-right .selectedbox .icon-close:hover{
    background-color: #ed3f14;
    border-color: #ed3f14;
    color: white;
  }

  .wf-selectperson-right .selectedbox .icon-type{
    font-size: 18px;
    color: #5990cf;
  }
  .wf-selectperson-right .selected-btn .selected-footer{
    padding: 6px;
    text-align: center;
  }
  /*左侧标签*/
  .wf-selectperson-left-menu .ivu-menu-item{
    font-size: 14px;
    padding: 8px 24px;
  }
  .wf-selectperson-left-menu .ivu-menu-item.ivu-menu-item-active.ivu-menu-item-selected{
    background-color: #df5550;
    color:white;
  }

</style>