<template>
	<div class="designComponent">
		<Row class="designComponent-row">
			<Col span="6"> 
				<Tabs type="card">
	        <TabPane label="基础控件" >
	        	<draggable v-model="fieldCtrls1" @end="fieldDragEnd" :options="{group:{name:'fields', pull:'clone', put:false },sort:false,chosenClass:'draggable-chosen',ghostClass:'draggable-ghost',dragClass:'draggable-drag'}" :clone="cloneDrag" style="height:100%">
                <FieldCtrl v-bind="element" v-for="(element, index) in fieldCtrls1"  :key="index"></FieldCtrl>
            </draggable>
	        </TabPane>
	        <TabPane label="组织架构控制">
	        	 <draggable v-model="fieldCtrls2" @end="fieldDragEnd" :options="{group:{name:'fields', pull:'clone', put:false },sort:false,chosenClass:'draggable-chosen',ghostClass:'draggable-ghost',dragClass:'draggable-drag'}" :clone="cloneDrag" style="height:100%">
                <FieldCtrl v-bind="element" v-for="(element, index) in fieldCtrls2"  :key="index"></FieldCtrl>
            </draggable>
	        </TabPane>
			   </Tabs>
			</Col>
			<Col span="12">
				<div class="dc-page">
					 <div class="dc-page-page">
					 		<div class="dc-page-page-head" @click="switchFormSet" v-if="!(data.hidenTitle)"> 
					 			<div class="dc-page-page-title">
					 				{{data.title}}
					 			</div>
					 			<div class="dc-page-page-description">
					 				{{data.description}}
					 			</div>
					 		</div>

					 		<div v-if="fields.length==0" class="dc-page-page-nofields">拖拽或双击左侧控件添加在表单里</div>

					 		<draggable v-model="fields" 
					 			@end="fieldDragEnd"
					 			:options="{chosenClass:'draggable-chosen',ghostClass:'draggable-ghost',dragClass:'draggable-drag',group:{name:'fields'}}" style="min-height:50%">
						    	
							    	<div v-for="(item,index) in fields" :key="index" class="dc-page-field">
							    		<div class="dc-page-field-inner">
	             					<Field ref="fields" @mousedown.native="setActiveField(item)" 
                        v-bind="item" :seq="index+1" :showSeq="data.showFieldSeq?true:false"
                        @remove="remove(item)" @add="add(item)"></Field>
	             				</div>
	        					</div>
					    </draggable>
					 </div>
					 <div class="dc-page-foot">
					 	<Row>
					 		<Col span="12">&nbsp;</Col>
					 		<Col span="12" class="dc-page-foot-right">
					 			<Button type="info" @click="save">保 存</Button>
					 			&nbsp;
					 			<Button @click="preview">预 览</Button>
					 		</Col>
					 	</Row>
					 </div>
				</div>
			</Col>
			<Col span="6">
				
				<Tabs type="card" v-model="propertyTabs">
		      <TabPane label="表单设置" name="base" > 
		      	<ExpansionComponent title="基本设置">
		      		<Form :model="data" class="dc-form" label-position="top">
				        <FormItem label="标题">
				          <Input v-model="data.title" ></Input> 
					      </FormItem>
					      <FormItem label="描述">
					        <Input v-model="data.description" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="这里是对表单内容的描述"></Input> 
					      </FormItem>
					      <formItem>
					      	 <Checkbox v-model="data.showFieldSeq" :true-value="1" :false-value="0">自动生成字段编号</Checkbox>
					      </formItem>
					      <formItem>
					      	 <Checkbox v-model="data.hidenTitle" :true-value="1" :false-value="0">隐藏表头标题和描述</Checkbox>
					      </formItem> 
				      </Form>
		      	</ExpansionComponent>
		        <ExpansionComponent title="外观设置">
		        	<Form :model="data" class="dc-form" label-position="top">
		        		<FormItem label="版式">
				            <RadioGroup v-model="data.fieldWay">
				                <Radio :label="1">并列</Radio>
				                <Radio :label="2">换行</Radio>
				            </RadioGroup> 
				        </FormItem>
				        <!--
				        <FormItem label="控件标题大小">
				        	<RadioGroup v-model="fieldTitleSize">
				                <Radio label="1" :true-value="1">小</Radio>
				                <Radio label="2" :true-value="2">中</Radio>
				                <Radio label="3" :true-value="3">大</Radio>
				          </RadioGroup>
					      </FormItem>
					      <FormItem label="控件值大小">
				        	<RadioGroup v-model="fieldValueSize">
				                <Radio label="1" :true-value="1">小</Radio>
				                <Radio label="2" :true-value="2">中</Radio>
				                <Radio label="3" :true-value="3">大</Radio>
				          </RadioGroup>
					      </FormItem>
					      <FormItem label="行间距">
				        	<RadioGroup v-model="fieldRowSpace">
				                <Radio label="1" :true-value="1">窄</Radio>
				                <Radio label="2" :true-value="2">中</Radio>
				                <Radio label="3" :true-value="3">宽</Radio>
				          </RadioGroup>
					      </FormItem>
					      <FormItem label="表单宽度">
				        	<RadioGroup v-model="formWidth">
				                <Radio label="1" :true-value="1">窄</Radio>
				                <Radio label="2" :true-value="2">中</Radio>
				                <Radio label="3" :true-value="3">宽</Radio>
				          </RadioGroup>
					      </FormItem>
					      --> 
					    </Form> 
		      	</ExpansionComponent>
		      </TabPane>
		      <TabPane label="控件属性" name="field" :disabled="activeField.type=='none'"> 
		      	<ExpansionComponent :title="activeFiledTypeText">
		      		<Form :model="activeField" class="dc-form" label-position="top">
				        <FormItem label="标题">
				          <Input v-model="activeField.title" ></Input> 
					      </FormItem>
					      <FormItem label="描述">
					        <Input v-model="activeField.description" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="这里是对表单内容的描述"></Input>
					      </FormItem>
					      <!--textarea-->
					      <FormItem label="行数" v-if="activeField.type=='textarea'">
				          <InputNumber v-model="activeField.rows" ></InputNumber>
					      </FormItem>
					      <!--number-->
					      <FormItem label="精度" v-if="activeField.type=='number'">
				          <InputNumber v-model="activeField.digit" ></InputNumber>
					      </FormItem>
				      </Form>
		      	</ExpansionComponent>
            <ExpansionComponent title="日期时间格式" v-if="['date'].indexOf(activeField.type)>=0"> 
              <Form :model="activeField" class="dc-form" >
                <FormItem label="">
                  <RadioGroup v-model="activeField.format">
                        <Radio :label="1" >年-月-日</Radio>
                        <Radio :label="2" >年-月-日-时-分</Radio> 
                  </RadioGroup>
                </FormItem>
              </Form>
            </ExpansionComponent>
		      	<ExpansionComponent title="版式">
		      		<Form :model="activeField" class="dc-form" label-position="top">
				        <FormItem>
				          <RadioGroup v-model="activeField.layout">
				              <Radio :label="1" >并列</Radio>
				              <Radio :label="2" >换行</Radio>
				              <Radio :label="3" >隐藏标题</Radio>
				          </RadioGroup>
					      </FormItem> 
				      </Form>
		      	</ExpansionComponent>
		      	<ExpansionComponent title="校验"> 
				      <Form :model="activeField" class="dc-form" v-if="['text','textarea'].indexOf(activeField.type)>=0">
					    	<FormItem label="最少填写字符(个)">
				          <InputNumber v-model="activeField.min_length" style="width:60px" ></InputNumber>
					      </FormItem>
					      <FormItem label="最多填写字符(个)">
				          <InputNumber v-model="activeField.max_length" style="width:60px"></InputNumber>
					      </FormItem> 
				      </Form>
				      <Form :model="activeField" class="dc-form"  v-if="activeField.type=='number'">
				      	<FormItem label="最小值">
				          <InputNumber v-model="activeField.min_val" style="width:60px" :precision="activeField.digit"></InputNumber>
					      </FormItem>
					      <FormItem label="最大值">
				          <InputNumber v-model="activeField.max_val" style="width:60px" :precision="activeField.digit"></InputNumber>
					      </FormItem>
				      </Form>
              <Form :model="activeField" class="dc-form"  v-if="activeField.type=='checkbox'">
                <FormItem label="">
                  <Select v-model="activeField.check_select" style="width:80px">
                      <Option :value="1">至少</Option>
                      <Option :value="2">最多</Option>
                      <Option :value="3">恰好</Option>
                  </Select>
                  选择
                  <InputNumber v-model="activeField.check_select_count" style="width:60px" :min="0" :precision="0"></InputNumber>
                  项
                </FormItem> 
              </Form>
				    </ExpansionComponent> 
				    <ExpansionComponent title="选项设置" v-if="['select','checkbox','radio'].indexOf(activeField.type)>=0" label-position="top">
				    	<Form :model="activeField" class="dc-form">
				      	<FormItem label="" v-if="['select','radio'].indexOf(activeField.type)>=0">
				          <Select v-model="activeField.enum_source">
							        <Option :value="1">自定义枚举</Option>
							        <Option :value="2">引用枚举模板</Option>
							        <Option :value="3">引用父级模板</Option>
							    </Select>
					      </FormItem>
					      <FormItem label="选项" v-if="activeField.enum_source==1"></FormItem>
					      <FormItem label="" v-if="activeField.enum_source==1">
					      	<draggable v-model="activeField.enum_data" :options="{handle:'.handle',chosenClass:'draggable-chosen',ghostClass:'draggable-ghost',dragClass:'draggable-drag'}">
						      	<Row v-for="item,index in activeField.enum_data" :key="index" class="dc-form-enumdata-row">
						      		<Col span="12">
						      			<Input v-model="item.text" placeholder="选项"></Input>
						      			<div style="height:2px;"></div>
						      			<Input v-model="item.value" placeholder="值"></Input>
						      		</Col>
						      		<Col span="12">
						      			<Icon @click.native="addEnum(index)" type="plus-circled" class="dc-form-enumdata-add"></Icon>
						      			<Icon @click.native="removeEnum(index)" type="close-circled" class="dc-form-enumdata-remove"></Icon>
						      			<Icon type="arrow-move" class="dc-form-enumdata-move handle"></Icon>
						      		</Col>
						      	</Row>
					      	</draggable>
                  <Row v-if="activeField.enum_other" class="dc-form-enumdata-row" align="middle">
                    <Col span="12"> 
                      其它 ____________
                    </Col>
                    <Col span="12"> 
                      <Icon @click.native="activeField.enum_other=0" type="close-circled" class="dc-form-enumdata-remove"></Icon>
                    </Col>
                  </Row>
                  <div>
                    <a @click="activeField.enum_other=1">添加 "其它"</a> 
                  </div>
					      </FormItem>
				      </Form>
				    </ExpansionComponent>
		      </TabPane>
			  </Tabs>
			</Col>
		</Row> 
	</div>
</template>
<script>

import ExpansionComponent from '@/components/workflow/formPage/ExpansionComponent';
import Field from '@/components/workflow/components/form/Field';
import FieldCtrl from '@/components/workflow/components/form/FieldCtrl';
import draggable from 'vuedraggable'
import formjs from '@/components/workflow/components/form/form'

export default { 
	components: {
    ExpansionComponent,
    Field,
    FieldCtrl,
    draggable
  },
  name:'DesignComponent', 
  props:{ 
  	model:{
  		type:Object,
  		default:null
  	}
  },
  data() {
    var obj = {
    	data:this.model,
    	activeField:{type:'none'},
    	propertyTabs:'base', 
    	fieldCtrls1:[ 
    		formjs.buildField({type:'text'}),
      	formjs.buildField({type:'textarea'}),
      	formjs.buildField({type:'number'}), 
      	formjs.buildField({type:'select'}), 
        formjs.buildField({type:'checkbox'}), 
        formjs.buildField({type:'radio'}), 
        formjs.buildField({type:'date'}), 
        formjs.buildField({type:'open'}), 
        formjs.buildField({type:'upload_pic'}), 
        formjs.buildField({type:'upload'}), 
        formjs.buildField({type:'serial_number'}), 
        formjs.buildField({type:'location'}), 
        formjs.buildField({type:'separator'}), 
        formjs.buildField({type:'picture'}), 
        formjs.buildField({type:'description'}),  
        formjs.buildField({type:'detail'}), 
        formjs.buildField({type:'address'}),  
        formjs.buildField({type:'editor'}),  
    	],
    	fieldCtrls2:[
    		formjs.buildField({type:'person'}), 
        formjs.buildField({type:'department'}),  
        formjs.buildField({type:'multi_person'}), 
        formjs.buildField({type:'multi_department'}), 
    	],
    	fields:[]
    };

    this.initField(obj.data);
    for(var i = 0;i<obj.data.fields.length;i++){
    	obj.fields.push({model:obj.data.fields[i]});
    }

    return obj;
  },
  mounted:function(){

  },
  computed:{
    activeFiledTypeText(){
    	if(this.activeField.title){
    		return this.activeField.title;
    	}
    	return "表单元素";
    }
  },
  watch:{
  	fields(val){
  		var arr = [];
  		for(var i = 0;i<val.length;i++){
  			arr.push(val[i].model);
  		}
  		this.model.fields = arr;
  	},
  	model(val){
  		if(this.data != val){
  			this.data = val;

  			this.fields = [];
  			this.initField(this.data); 
		    for(var i = 0;i<this.data.fields.length;i++){
		    	this.fields.push({model:this.data.fields[i]});
		    }
  		}
  	},
    'data.fieldWay':function(val){
      for(var i = 0;i<this.fields.length;i++){
        this.fields[i].model.layout = val;
      }
    }
  },
  methods:{ 
  	initField(obj){
  		obj.title = obj.title || '表单'; 
	  	obj.description = obj.description || '描述'; 
	  	obj.showFieldSeq = obj.showFieldSeq || '';
	  	obj.hidenTitle = obj.hidenTitle || '';
	  	obj.fieldWay = obj.fieldWay || 2;
	  	obj.fieldTitleSize = obj.fieldTitleSize || 2;
	  	obj.fieldValueSize = obj.fieldValueSize || 2;
	  	obj.fieldRowSpace = obj.fieldRowSpace || 2;
	  	obj.formWidth = obj.formWidth ||2;
	  	obj.colorBg = obj.colorBg || '';
	  	obj.colorHeadBg = obj.colorHeadBg || '';
	  	obj.colorPage = obj.colorPage || '';
	  	obj.colorTitle = obj.colorTitle || '';
	  	obj.colorHead = obj.colorHead || '';
	  	obj.colorDescribe = obj.colorDescribe || '';
	  	obj.picBg = obj.picBg || '';
	  	obj.picHead = obj.picHead || '';
	  	obj.picHeadLogo = obj.picHeadLogo || '';
	  	obj.colorDescribe = obj.colorDescribe || '';
	    obj.fields = obj.fields || [];

			for(var i = 0;i<obj.fields.length;i++){
				formjs.buildField(obj.fields[i]);
			}
  	},
    insertForm:function(){

    },
    fieldDragEnd:function(evt){
    	this.setActiveField(this.fields[evt.newIndex]);
    },
    setActiveField(item){ 
    	var fields = this.fields;
    	for(var i=0;i<fields.length;i++){
    		fields[i].active = false;
    	}

    	if(item != null){
    		item.active = true; 
	    	this.activeField = item.model;
	    	this.propertyTabs = 'field';
    	}else{
				this.activeField = {type:'none'};
    		this.propertyTabs = 'base';
    	} 
    },
    switchFormSet(){
    	this.propertyTabs = 'base';
    },
    cloneDrag(ele){ 
    	var mo = formjs.buildField({type:ele.type});
    	return {model:mo};
    },
    remove(item){ 
    	var fields = this.fields;
    	var index = fields.indexOf(item);
    	fields.splice(index,1);
    	var nextIndex = index; 
    	if(fields.length > nextIndex){
    		nextIndex = nextIndex;
    	}else{
    		nextIndex = nextIndex - 1;
    	}
    	
    	if(nextIndex >= 0 ){
    		this.setActiveField(fields[nextIndex]);
    	}else{
    		this.setActiveField(null);
    	} 
    },
    add(item){
    	var index = this.fields.indexOf(item);
    	var newEle = formjs.buildField({type:item.model.type});
    	newEle = {model:newEle};
    	this.fields.splice(index+1,0,newEle);    	
    },
    addEnum(index){
      this.activeField.enum_data.splice(index,0,{text:'选项',value:'选项'});
    },
    removeEnum(index){
      if(this.activeField.enum_data.length > 2){
        this.activeField.enum_data.splice(index,1);
      }else{
        // ...
      }
    },
    preview(){
    	this.$emit('preview');
    },
    save(){
    	this.$emit('save');
    } 
  }
}

</script>

<style type="text/css">
  .designComponent{
  	width: 100%; 
  	height: 100%;
  }
  .designComponent-left{
  	width: 280px;
  }
  .designComponent-content{

  }
  .designComponent-right{
  	width: 280px;
  } 

  .designComponent-row{
  	height: 100%;
  }
  .designComponent-row .ivu-col{height: 100%;}

  .designComponent-col{
  	height: 100%;
  }

  .designComponent .ivu-tabs.ivu-tabs-card>.ivu-tabs-bar .ivu-tabs-tab{
  	border-radius: 0px;
  	border-left:0px;
  	border-left:0px;
  	margin-right: 0px;
  }

  .dc-page{
  	background-color: rgb(195, 231, 255);
  	height: 100%;padding: 10px;padding-bottom: 45px;
  }
  .dc-page-page{ 
  	background: white;
  	height: 100%;
  	overflow-y: auto;
  }

  .dc-page-page-head{
  	min-height: 100px;
  	text-align: center;
  	padding-top: 20px;
  	background-color: #4FACE9;
  	color:white;
  }
  .dc-page-page-title{
  	font-size: 16px;
  }
  .dc-page-page-description{
  	font-size: 12px;
  }
  .dc-page-page-nofields{
  	background: url(../imgs/design-no-icon.png) top center no-repeat;
    width: 100%;
    padding-top: 86px;
    font-size: 14px;
    text-align: center;
    color: #515151;
    position: absolute;
    top: 50%;
    margin-top: -34px;
    left: 0;
  }

  .dc-form,
  .dc-form .ivu-checkbox-wrapper,
  .dc-form .ivu-radio-wrapper{
  	font-size: 14px;
  }

  .dc-form.ivu-form .ivu-form-item {
    margin-bottom: 8px;
  }
  .dc-form.ivu-form-label-top .ivu-form-item-label{
  	padding: 0 0 8px 0;
  	font-size: 14px;
  }

  .dc-form .ivu-form-item-content{
  	line-height: inherit;
  }
  .designComponent .ivu-tabs{
  	height: 100%;
  	padding-top: 31px;
  	background-color: white; 
  }
  .designComponent .ivu-tabs-bar{ 
  	margin-bottom: 0px;
  	margin-top:-31px;
  }
  
  .designComponent .ivu-tabs-content{  
  	height: 100%; 
  }
  .designComponent .ivu-tabs-tabpane{  
  	height: 100%;
  	overflow-y: auto;  
  	padding: 4px 0px;
  } 

  .dc-page-field{ 
  }

  .dc-page-page .draggable-ghost.fieldctrl{
  	border: 2px solid #4FACE9 !important;
  	padding: 2px;
  	height: auto;
  	width: 100%;
  }
  .dc-page-page .draggable-ghost.fieldctrl .fieldctrl-inner{
  	display: none;
  }

  .dc-page-foot{
  	margin-top: 6px;
  }
  .dc-page-foot-right{
  	text-align: right;
  }
  
  .draggable-chosen{} 
  .draggable-drag{}

  .dc-page-field.draggable-ghost{
  	border: 2px solid #4FACE9 !important;
  	padding: 2px;
  }
  .dc-page-field.draggable-ghost .dc-page-field-inner{
  	display: none;
  }

  .draggable-chosen{ 
  }

  .draggable-drag{
  }

  .dc-form-enumdata-row{
  	padding: 4px;  
  	border: 1px dashed transparent;
  	cursor: default;
  }
  .dc-form-enumdata-row:hover{
  	border: 1px dashed #4FACE9 !important;
  	background-color: white;
  }
  .dc-form-enumdata-row .ivu-icon{
  	font-size: 18px;
  	line-height: 18px;
  	margin-top: 4px;
  	margin-left: 10px;
  	color:#ccc;
  }

  .dc-form-enumdata-row.draggable-ghost{
  	border: 2px solid #4FACE9 !important;
  	padding: 2px;
  }

  .dc-form-enumdata-row.draggable-ghost .ivu-col{
  	display: none;
  }

  .dc-form-enumdata-row:hover .ivu-icon{
  	color:#5990cf;
  	cursor: pointer;
  }

  .dc-form-enumdata-row:hover .dc-form-enumdata-remove{
 		color:red;
 	}

 	.dc-form-enumdata-move{
 		cursor: pointer;
 	}
   
</style>

