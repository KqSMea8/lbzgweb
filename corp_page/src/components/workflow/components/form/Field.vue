<template>
  <div :class="classes" v-if="access!=4"> 
    <div class="fmc-field-operate" v-if="design && active">
      <div class="fmc-field-operate-bar">
        <Icon type="ios-plus-outline" style="color:#4FACE9" @click.native="add"></Icon> 
        <Icon type="ios-close-outline" style="color:red" @click.native="remove"></Icon>
      </div>
    </div>
    <div v-if="this.invalid" class="fmc-field-invalid-message">{{this.message}}</div>
    <Row type="flex" justify="center" align="middle">
        <Col :span="col[0]" v-if="model.layout==1">
          <div class="fmc-field-label"><span class="fmc-field-label-require"> * </span>{{seqTitle}}</div>
        </Col>
        <Col :span="col[1]">
        <div class="fmc-field-label fmc-field-label-top" 
          v-if="model.layout==2"><span class="fmc-field-label-require"> * </span>{{seqTitle}}</div>
        <div v-if="access==3">******</div>
        <div v-else class="fmc-field-input">
          <Input v-if="model.type=='text'" v-model="stringValue" placeholder="" :disabled="ctrlDisabled"></Input> 

          <Input v-if="model.type=='textarea'" v-model="stringValue" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="" :disabled="ctrlDisabled"></Input>

          <Select v-if="model.type=='select'" v-model="stringValue" :disabled="ctrlDisabled">
              <Option v-for="item in model.enum_data" :value="item.value" :key="item.value">{{ item.text }}</Option>
          </Select>

          <InputNumber v-if="model.type=='number'" v-model="numberValue" placeholder="" :disabled="ctrlDisabled" :precision="model.digit"></InputNumber>

          <CheckboxGroup v-if="model.type=='checkbox'" v-model="checkboxValue" size="large">
            <Checkbox :disabled="ctrlDisabled" :label="item.value" v-for="(item,index) in model.enum_data"> 
                <span>{{item.text}}</span>
            </Checkbox>
            <Checkbox :disabled="ctrlDisabled" label="other" v-if="model.enum_other"> 
                <span>其它</span>
                <Input v-if="checkboxValue && checkboxValue.indexOf('other')>=0" v-model="other" placeholder="" :disabled="ctrlDisabled"></Input>
            </Checkbox>
          </CheckboxGroup>

          <RadioGroup v-if="model.type=='radio'" v-model="stringValue" size="large">
            <Radio :disabled="ctrlDisabled" :label="item.value" v-for="(item,index) in model.enum_data"> 
                <span>{{item.text}}</span>
            </Radio>
            <Radio :disabled="ctrlDisabled" label="other" v-if="model.enum_other"> 
                <span>其它</span>
                <Input v-if="stringValue == 'other'" v-model="other" placeholder="" :disabled="ctrlDisabled"></Input>
            </Radio>
          </RadioGroup>

          <DatePicker v-if="model.type=='date' &&　!ctrlDisabled" 
          :type="model.format==1?'date':'datetime'" :format="dateFormat" v-model="dateValue"></DatePicker>

          <Input v-if="model.type=='date' && ctrlDisabled" v-model="stringValue" placeholder="" :disabled="ctrlDisabled" style="width:187px"></Input> 


          <iSwitch :disabled="ctrlDisabled" v-if="model.type=='open'" size='default' v-model="booleanValue" :true-value="true" :false-value="false"></iSwitch>

          <div class="fmc-field-uploadpic" v-if="model.type=='upload_pic'">
            <div class="fmc-field-uploadpic-upload">
              <Icon type="image" class="fmc-field-uploadpic-icon"></Icon>
              <br/>
              请上传图片
            </div> 
          </div>

          <div class="fmc-field-upload" v-if="model.type=='upload'">
            <div class="fmc-field-upload-upload">
              <Icon type="android-attach" class="fmc-field-upload-icon"></Icon>
              <br/>
              请上传图片
            </div> 
          </div>

          <div class="fmc-field-location" v-if="model.type=='location'">
            <div class="fmc-field-location-get">
              <Icon type="location" class="fmc-field-location-icon"></Icon>
              点击获取位置
            </div> 
          </div>

          <Input v-if="model.type=='serial_number'" v-model="stringValue" placeholder="" :disabled="ctrlDisabled"></Input>

          <div v-if="model.type=='separator'" class="fmc-field-separator"></div>

          <div class="fmc-field-description" v-if="model.type=='description'"> 
            <div class="fmc-field-description-body">
              {{model.description}}
            </div>
          </div>

          <div v-if="model.type=='address'" class="fmc-field-address">
            <Row>
              <Col span="8">
                <Select v-model="address_1" :disabled="ctrlDisabled" >
                  <Option v-for="item in province" :value="item.code" :key="item.code">{{ item.text }}</Option>
                </Select>
              </Col>
              <Col span="8"> 
                  <Select v-model="address_2" :disabled="ctrlDisabled" >
                    <Option v-for="item in city" :value="item.code" :key="item.code">{{ item.text }}</Option>
                  </Select> 
              </Col>
              <Col span="8">
                <Select v-model="address_3" :disabled="ctrlDisabled" >
                    <Option v-for="item in area" :value="item.code" :key="item.code">{{ item.text }}</Option>
                </Select>
              </Col>
            </Row>
            <Input class="fmc-field-address-address" v-model="address_4" placeholder="" :disabled="ctrlDisabled"></Input>
          </div>

          <div class="fmc-field-editor" v-if="model.type=='editor'"> 
            <div ref="editorBody" class="fmc-field-editor-body">
               
            </div>
          </div>

          <table v-if="model.type=='person' || model.type=='department' || model.type=='multi_person' || model.type=='multi_department'" style="width:100%" >
            <tr>
              <td>
                <Input readonly v-model="stringValue" placeholder="" :disabled="ctrlDisabled"></Input>
              </td>
              <td class="fmc-field-more">
                <a @click="selectPerson" :disabled="ctrlDisabled">选择</a>
              </td>
            </tr>
          </table> 
        </div> 
        </Col> 
    </Row> 
  </div>
</template>
<script>
import formjs from '@/components/workflow/components/form/form'
import areajs from '@/assets/js/area'

export default {
  name:'fmcfield',
  props:{
    model:{
      type:Object,
      default:formjs.buildField({type:'text'})
    },
    seq:{
      type:Number,
      default:1
    },
    showSeq:{
      type:Boolean,
      default:false
    },
    design:{
      type:Boolean,
      default:true
    },
    active:{
      type:Boolean,
      default:false
    },
    access:{ // 访问权限 1.编辑 2.浏览 3.隐藏 4.不可见
      type:Number,
      default:1
    },
    require:{ // 非空
      type:Boolean,
      default:false
    }
  },
  data() { 
    return {
      value:'',
      numberValue:0,
      stringValue:null,
      booleanValue:null,
      dateValue:null,
      checkboxValue:[],
      province:[],
      city:[],
      area:[],
      address_1:'',
      address_2:'',
      address_3:'',
      address_4:'',
      address_listen:true,
      other:'',
      ids:'',

      invalid:false,
      message:'此项必填'
    }
  },
  mounted:function(){
    if(this.model && this.model.type == 'address'){ 
      this.province = areajs.getProvinces(); 
    }
  },
  watch:{
    model(val,old){
    },
    numberValue:function(val,old){
      if(val != old){
        //this.$emit('input',val);
        this.value = val;
      }
    },
    value:function(val,old){  
      if(val != old){
        //'text','textarea','number','select','checkbox','radio','date','open','upload_pic','upload','serial_number','location','separator','picture','description','comment','detail','address','person','department','multi_person','multi_department'

        // string
        if(['text','textarea','select','radio','serial_number','location','picture','detail','person','department','multi_person','multi_department','editor','date'].indexOf(this.model.type)>=0){
          this.stringValue = val;
        }
        // number
        try{
          if(['number'].indexOf(this.model.type)>=0){
            this.numberValue = val * 1;
          }
        }catch(ex){
          console.log("can not to trans string to number!");
        }
        // boolean
        try{ 
          if(['open'].indexOf(this.model.type)>=0){
            if(val != null && val != '' && val != '0'){
              this.booleanValue = true;
            }else{
               this.booleanValue = false;
            }
          } 
        }catch(ex){
          console.log("can not to trans string to boolean!");
        }
        // date
        try{
          if(['date'].indexOf(this.model.type)>=0){
            if(val != null && val.length >= 4){ 
              this.dateValue = new Date(Date.parse(val.replace(/-/g,   "/")));
            }else{
              this.dateValue = null;
            }
          } 
        }catch(ex){
          console.log("can not to trans string to date!");
        }
        // array
        try{
          if(['checkbox'].indexOf(this.model.type)>=0){
            if(val != null){
              this.checkboxValue = val.split(',');
            }else{
              this.checkboxValue = [];
            } 
          } 
        }catch(ex){
          console.log("can not to trans string to array!");
        }
        // address
        try{
          if(['address'].indexOf(this.model.type)>=0){ 
            this.address_listen = false; 
            if(val != null &&　val.length>=6 && val.substring(0,2) != '00'){
              this.address_1 = val.substring(0,2) + '0000'; 
              if(val.substring(2,2) != '00'){
                this.address_2 = val.substring(0,4) + '00';
                this.city = areajs.getCities(this.address_1); 
                if(val.substring(4,2) != '00'){
                  this.address_3 = val;
                  this.area = areajs.getAreas(this.address_2);
                }else{
                  this.address_3 = '';
                  this.area = areajs.getAreas(this.address_2);
                }
              }else{
                this.address_2 = '';
                this.address_3 = '';
                this.city = [];
                this.area = [];
              }
　          }else{
              this.address_1 = '';
              this.address_2 = '';
              this.address_3 = '';
              this.city = [];
              this.area = [];
            }
            this.$nextTick(()=>{this.address_listen = true});
          }
        }catch(ex){
          console.log("can not to trans string to array!");
        }
        this.validate();
      }
    },
    numberValue:function(val,old){
      if(val != old){
        //this.$emit('input',val + '');
        this.value = val + '';
      }
    },
    stringValue:function(val,old){ 
      if(val != old){ 
        //this.$emit('input',val + '');
        if(this.model.type == 'editor'){
          this.$refs.editorBody.innerHTML = val;
        }
        this.value = val + '';
      }
    },
    booleanValue:function(val,old){ 
      if(val != old){
        //this.$emit('input',val?'1':'0');
        this.value = val?'1':'0';
      }
    },
    dateValue:function(val,old){
      if(val != old){
        if(val != null && val.getDate){
          //this.$emit('input',this.formatDate(val,this.dateFormat));
          this.value = this.formatDate(val,this.dateFormat);
        }else{
          //this.$emit('input','');
          this.value = '';
        }
      }
    },
    checkboxValue:function(val,old){
      if(val != old){
        //this.$emit('input',val.join(','));
        this.value = val.join(',');
      }
    },
    address_1(val,old){ 
      if(val != old && this.address_listen){
        if(val){
          //this.$emit('input',val);
          this.value = val;
        } 
      } 
    },
    address_2(val,old){
      if(val != old && this.address_listen){ 
        if(val){
          //this.$emit('input',val);
          this.value = val;
        } 
      }
    },
    address_3(val,old){
      if(val != old && this.address_listen){
        if(val){
          //this.$emit('input',val);
          this.value = val;
        } 
      }
    },
    address_4(val,old){
      
    } 
  },
  computed:{
    classes(){
      return [
          'fmc-field',
          {
            'fmc-field-design':this.design,
            'fmc-field-active':this.active,
            'fmc-field-hidden':this.access == 4,
            'fmc-field-require':this.require,
            'fmc-field-invalid':this.invalid,
          }
        ]; 
    },
    col(){
      if(this.model.layout == 1){
        return [6,18];
      }
      if(this.model.layout == 2){
        return [0,24];
      }
      if(this.model.layout == 3){
        return [0,24];
      }
    },
    dateFormat(){
      if(this.model.format == 1){
        return 'yyyy-MM-dd';
      }else{
        return 'yyyy-MM-dd HH:mm';
      }
    },
    seqTitle(){ 
      if(this.showSeq){
        return this.seq + '. ' + this.model.title;
      }
      return this.model.title;
    },
    ctrlDisabled(){
      return this.design || this.access == 2;
    }
  },
  methods:{
    add:function(){
      this.$emit('add');
    },
    remove:function(){
      this.$emit('remove');
    },
    formatDate(dt,format){  
      var date = {
         "M+": dt.getMonth() + 1,
         "d+": dt.getDate(),
         "h+": dt.getHours(),
         "H+": dt.getHours(),
         "m+": dt.getMinutes(),
         "s+": dt.getSeconds(),
         "q+": Math.floor((dt.getMonth() + 3) / 3),
         "S+": dt.getMilliseconds()
      };
      if (/(y+)/i.test(format)) {
         format = format.replace(RegExp.$1, (dt.getFullYear() + '').substr(4 - RegExp.$1.length));
      }
      for (var k in date) {
         if (new RegExp("(" + k + ")").test(format)) {
             format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
         }
      }
      return format;
    },
    // 给组件赋值
    setValues(obj){ 
      obj = obj || {};
      if(this.model){
        if(obj[this.model.id] != null){
          this.value = obj[this.model.id];
        }else{
          this.value = null;
        }
        if(this.model.type == 'address'){
          this.address_4 = obj[this.model.id + '_address'] || null;
        }
        if(this.model.type == 'checkbox' || this.model.type == 'radio'){
          this.other = obj[this.model.id + '_other'] || '';
        }
        if(this.model.type == 'person'
          || this.model.type == 'department'
          || this.model.type == 'multi_person'
          || this.model.type == 'multi_department'){
          this.ids = obj[this.model.id + '_ids'] || '';
        }
      }
    },
    // 获取组件的值
    getValues(obj){
      obj = obj || {};
      if(this.model){
        obj[this.model.id] = this.value;
        if(this.model.type == 'address'){
          obj[this.model.id + '_address'] = this.address_4;
        }
        if(this.model.type == 'checkbox' || this.model.type == 'radio'){
          obj[this.model.id + '_other'] = this.other;
        }
        if(this.model.type == 'person'
          || this.model.type == 'department'
          || this.model.type == 'multi_person'
          || this.model.type == 'multi_department'){
          obj[this.model.id + '_ids'] = this.ids; 
        }
      }
      return obj;
    },
    selectPerson(){  
      if(this.model.type == 'person'){
        this.$emit('selectPerson',this);
      }
      if(this.model.type == 'department'){
        this.$emit('selectDept',this);
      }
      if(this.model.type == 'multi_person'){
        this.$emit('selectMultiPerson',this);
      }
      if(this.model.type == 'multi_department'){
        this.$emit('selectMultiDept',this);
      }
    },
    validate(){
      if(this.design || this.access != 1){
        this.invalid = false;
        return !this.invalid;
      }

      if(this.value == '' || this.value == null){
        if(this.require){
          this.invalid = true;
          this.message = '此项必填';
          return !this.invalid;
        }
      }else{
        // 数据验证
      }
      
      this.invalid = false;
      return !this.invalid;
    }
  }
}

</script>

<style type="text/css">
  .fmc-field{
    cursor:default;
    font-size: 14px;
    padding: 10px; 
    border: 1px solid transparent;
    background-color: white;
  } 
  .fmc-field-hidden{
    display: none;
  }
  .fmc-field-label{
    font-size:14px;font-weight: bold;color:#515151;
  }
  .fmc-field-label-top{
    margin-bottom: 8px;
  }
  .fmc-field-label-require{
    color:red;font-weight: bold;
    display: none;
  } 
  .fmc-field-require .fmc-field-label-require{
    display:inline;
  }
  .fmc-field-invalid .fmc-field-input{
    border:1px dashed #ED3F12;
  }
  .fmc-field-invalid-message {
    display: inline-block;
    padding: 4px;
    background-color: #ED3F12;
    color:white;
    border-radius: 4px;
  }
  .fmc-field .ivu-input{width: 100%;}

  .fmc-field-design{
    cursor: move;
  }
  .fmc-field-design:hover{
    border: 1px dashed #4FACE9 !important;
  }
  .fmc-field-design.fmc-field-active{
    border: 1px dashed #4FACE9 !important;
    background-color: #EDF6FC !important;
  } 

  .fmc-field-design .ivu-input[disabled],
  .fmc-field-design fieldset[disabled] .ivu-input,
  .fmc-field-design .ivu-input-number-disabled .ivu-input-number-input,
  .fmc-field-design .ivu-select-disabled .ivu-select-selection,
  .fmc-field-design .ivu-checkbox-disabled .ivu-checkbox-inner,
  .fmc-field-design .ivu-radio-disabled .ivu-radio-inner {
    background-color:white;
    opacity: 1;
    cursor:inherit;
  }
  .fmc-field-design .ivu-checkbox-disabled+span {
    color: #495060;
    cursor: default;
  }
  .fmc-field-design .ivu-radio-wrapper-disabled {
    cursor:  default;
  }

  /*禁用时样式*/

  .fmc-field .ivu-input[disabled],
  .fmc-field fieldset[disabled] .ivu-input,
  .fmc-field .ivu-input-number-disabled .ivu-input-number-input,
  .fmc-field .ivu-select-disabled .ivu-select-selection,
  .fmc-field .ivu-checkbox-disabled .ivu-checkbox-inner,
  .fmc-field .ivu-radio-disabled .ivu-radio-inner,
  .fmc-field .ivu-checkbox-disabled+span,
  .fmc-field .ivu-input-number-disabled
  {
    color:#495060;
  }
  
  .fmc-field-operate {
    position: relative;
  }
  .fmc-field-operate-bar{
    position: absolute;
    right: 0px;
    top:-28px;
    font-size: 24px; 
  }

  .fmc-field-operate-bar .ivu-icon{
    cursor: pointer;background-color: white;
    font-weight: bold;
    border-radius: 10px;
    line-height: 24px;
  }
  .fmc-field-operate-bar .ivu-icon-ios-plus-outline:hover:before { 
    font-weight: normal; 
    content: "\F48B";
  } 
  .fmc-field-operate-bar .ivu-icon-ios-close-outline:hover:before { 
    font-weight: normal; 
    content: "\F406";
  } 
  .fmc-field-uploadpic-upload{
    padding: 4px;
    text-align: center; 
    border: 1px solid #ddd;
    background-color: #ededee;
    width: 100px;
  }
  .fmc-field-uploadpic-upload:hover{
    color:#5990CF;
  }
  .fmc-field-uploadpic-icon{
    font-size: 60px;
    color: #ccc;
  }

  .fmc-field-upload-upload{
    padding: 4px;
    text-align: center; 
    border: 1px solid #ddd;
    background-color: #ededee;
    width: 200px;
  }
  .fmc-field-upload-upload:hover{
    color:#5990CF;
  }
  .fmc-field-upload-icon{
    font-size: 20px;
    color: #ccc;
  }

  .fmc-field-location-get{
    padding: 4px;
    text-align: center; 
    border: 1px solid #ddd;
    background-color: #ededee;
  }
  .fmc-field-location-get:hover{
    color:#5990CF;
  }
  .fmc-field-location-icon{
    font-size: 30px;
    color: #ccc;
  }

  .fmc-field-separator{
    border-top: 1px solid #ccc;
    margin:8px 0px;
  }

  .fmc-field-description-body{
    border-top: 1px dashed #ccc;
    padding: 6px 0px;
  }

  .fmc-field-editor{

  }

  .fmc-field-editor-body{ 
    padding: 6px 0px;
    overflow-x: auto;
  }

  .fmc-field-address{

  }

  .fmc-field-address .ivu-select-selection{
    margin: 0px 10px 10px 0px;
  }

  .fmc-field-address .ivu-row .ivu-col:last .ivu-select-selection{
     margin-right: 0px;
  }

  .fmc-field-address-address{
  }

  .fmc-field-more{
    width: 30px;
  }

  .fmc-field-more a{
     
  } 

</style>
 