<template>
  <Modal
    v-model="display"
    :closable="false"
    :footerHide="true"
    title="" 
    width="800">
    <div class="wf-selectperson">
      <div class="wf-selectperson-left">
        <Menu class="wf-selectperson-left-menu" mod="vertical" width="84"  @on-select="open" :active-name="activeName">
          <MenuItem :name="item.key" :key="item.key" v-for="(item,index) in tabs">
              {{item.label}}
          </MenuItem>
        </Menu>
      </div>
      <div class="wf-selectperson-right">
        <Row>
          <Col span="12">
            <div class="wf-sel-dept wt-scroll" v-if="displayDept">
               <SelectDept ref="deptComp" @on-select-dept="selectedDept" @on-select-emp="selectedEmp"></SelectDept>
            </div>
            <div class="wf-sel-groups wt-scroll" v-if="displayGroup">
                <SelectGroup ref="groupsComp" @on-select="addSelect">
                </SelectGroup>
            </div> 
          </Col>
          <Col span="12" class-name="wf-selectperson-right-col">
            <div class="selectedoptions" >
               <RadioGroup v-model="isConcurrence" type="button">
                  <Radio :label="1">并发</Radio>
                  <Radio :label="0">串发</Radio>
              </RadioGroup>
            </div>
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
import SelectGroup from '@/components/workflow/components/SelectGroup';
import SelectDept from '@/components/workflow/components/SelectDept';
export default {
  name:'selectPerson',
  components: {
    SelectGroup,
    SelectDept
  },
  props:{

  },
  data() {
    return {
      display:false,
      displayGroup:false,
      displayDept:true, 
      select:[
        //{type:'person',key:'zhangsan',title:'张三'},
        //{type:'department',key:'1',title:'软件部'},
        //{type:'role',key:'role1',title:'角色'},
        //{type:'relative',key:'admin',title:'相对角色'},
        //{type:'field',key:'sdfsdfsdf',title:'表单字段'},
      ],
      tabs:[
        {label:'部门',key:'department'},
        {label:'角色',key:'role'}
      ],
      activeName:'department',
      isConcurrence:1
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
      this.addSelect({type:'department',key:item.deptId,title:item.deptName});
    },
    selectedEmp(item){
      this.addSelect({type:'person',key:item.empId,title:item.trueName});
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
      this.select.push(item);
    },
    open(key){     
      if(key == 'role'){
        this.displayDept = false;
        this.displayGroup = true; 
        this.activeName = 'role';
      }
      if(key == 'department'){
        this.displayDept = true;
        this.displayGroup = false;
        this.activeName = 'department';
      }
    },
    ok:function(){ 
      this.display = false; 
      this.$emit('ok');
    },
    cancel:function(){
      this.display = false;
      this.select = [];
      this.$emit('cancel');
    },
    show(){ 
      this.select = [];
      this.display = true;
      this.isConcurrence = 1;
      this.open('department');
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
    height: 340px;
    margin:0px 10px;
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
  .wf-selectperson-right .selectedoptions{
    padding: 10px;text-align: center;
  }
  /*左侧标签*/
  .wf-selectperson-left-menu .ivu-menu-item{
    font-size: 14px;
    padding: 8px 24px;
  }
  .wf-selectperson-left-menu .ivu-menu-item.ivu-menu-item-active.ivu-menu-item-selected{
    background-color: #eb5954;
    color:white;
  } 

</style>