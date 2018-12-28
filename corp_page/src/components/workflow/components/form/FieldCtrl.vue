<template>
  <div :class="classes"> 
    <div class="fieldctrl-inner">
      <div :class="iconClasses"></div>
      {{title}}
    </div> 
  </div>
</template>
<script>

export default {
  name:'fieldctrl',
  props:{
    type:{ //类型 text,textarea,select
      type:String,
      default:'text'
    },
    title:{
      type:String,
      default:'标题'
    }
  },
  data() {
    return {

    }
  },
  mounted:function(){

  },
  computed:{
    classes(){
      return [
          'fieldctrl'
        ]; 
    },
    iconClasses(){
      return [
          'fieldctrl-icon',
          'fieldctrl-icon-' + this.type
        ];
    }
  },
  methods:{

  }
}

</script>

<style type="text/css">
  .fieldctrl{
    width: 50%;
    padding:4px;
    float: left;
    background-color: white;
  }

  .fieldctrl-inner{
    height: 45px;
    border: 1px dashed #b4b4b4;
    line-height: 45px;
    overflow: hidden; 
    border-radius: 3px;
    text-align: left;
    padding-left: 38px;
    white-space: nowrap;
    position: relative;
    cursor: default;
    -webkit-user-select:none;
    -moz-user-select:none;
    -ms-user-select:none;
    user-select:none;
  }

  .fieldctrl-inner:hover{
    color:#5990CF;
    border:1px solid #5990CF; 
    position: relative;
    box-shadow: 2px 2px 6px rgba(0, 0, 0, .13), -2px -2px 6px rgba(0, 0, 0, .13); 
    cursor: pointer;
  }

  .fieldctrl-icon{
    width: 32px;
    height: 32px;
    position: absolute;
    left: 3px;
    top: 50%;
    margin-top: -16px;
  }
  .fieldctrl-icon-text{
    background: url(../imgs/design-icons-32.png) -10px -10px no-repeat;
  }
  .fieldctrl-icon-textarea{
    background: url(../imgs/design-icons-32.png) -52px -10px no-repeat;
  }
  .fieldctrl-icon-number{
    background: url(../imgs/design-icons-32.png) -94px -10px no-repeat;
  }
  .fieldctrl-icon-select{
    background: url(../imgs/design-icons-32.png) -218px -10px no-repeat;
  }
  .fieldctrl-icon-checkbox{
    background: url(../imgs/design-icons-32.png) -178px -10px no-repeat;
  }
  .fieldctrl-icon-radio{
    background: url(../imgs/design-icons-32.png) -136px -10px no-repeat;
  }
  .fieldctrl-icon-date{
    background: url(../imgs/design-icons-32.png) -52px -52px no-repeat;
  }
  .fieldctrl-icon-open{
    background: url(../imgs/design-icons-32.png) -260px -52px no-repeat;
  }
  .fieldctrl-icon-upload_pic{
    background: url(../imgs/design-icons-32.png) -136px -136px no-repeat;
  }
  .fieldctrl-icon-upload{
    background: url(../imgs/design-icons-32.png) -136px -52px no-repeat;
  }
  .fieldctrl-icon-serial_number{
    background: url(../imgs/design-icons-32.png) -94px -186px no-repeat;
  }
  .fieldctrl-icon-location{
    background: url(../imgs/design-icons-32.png) -218px -52px no-repeat;
  }
  .fieldctrl-icon-separator{
    background: url(../imgs/design-icons-32.png) -52px -94px no-repeat;
  }
  .fieldctrl-icon-picture{
    background: url(../imgs/design-icons-32.png) -178px -94px no-repeat;
  }
  .fieldctrl-icon-description{
    background: url(../imgs/design-icons-32.png) -94px -94px no-repeat;
  }
  .fieldctrl-icon-detail{
    background: url(../imgs/design-icons-32.png) -136px -94px no-repeat;
  }
  .fieldctrl-icon-address{
    background: url(../imgs/design-icons-32.png) -260px -184px no-repeat;
  }
  .fieldctrl-icon-person{
    background: url(../imgs/design-icons-32.png) -136px -184px no-repeat;
  }
  .fieldctrl-icon-department{
    background: url(../imgs/design-icons-32.png) -178px -184px no-repeat;
  }
  .fieldctrl-icon-multi_person{
    background: url(../imgs/design-icons-32.png) -136px -264px no-repeat;
  }
  .fieldctrl-icon-multi_department{
    background: url(../imgs/design-icons-32.png) -178px -264px no-repeat;
  }
  .fieldctrl-icon-editor{
    background: url(../imgs/design-icons-32.png) -52px -10px no-repeat;
  }
  /*
  'text':'单行文本',
  'textarea':'多行文本',
  'number':'数字',
  'select':'下拉框',
  'checkbox':'多选框',
  'radio':'单选框',
  'date':'日期时间',
  'open':'开关',
  'upload_pic':'上传图片',
  'upload':'上传附件',
  'serial_number':'流水号',
  'location':'地理位置',
  'separator':'分隔线',
  'picture':'图片组',
  'description':'文字说明',
  'comment':'处理意见',
  'detail':'明细表',
  'address':'省市区',
  'editor':'编辑器',
  'person':'选人',
  'department':'选部门',
  'multi_person':'选多人',
  'multi_department':'选多部门',

  */
</style>