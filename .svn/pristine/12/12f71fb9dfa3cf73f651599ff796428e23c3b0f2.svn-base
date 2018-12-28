var short = require('short-uuid');
var translator = short();

//表单元素数据结构
//http://wiki.lyarc.com/pages/viewpage.action?pageId=8028211
let components = {};

function ifundefined(val,newVal){
	if(typeof(val) == 'undefined'){
		return newVal;
	}
	return val;
}   
		   
components.fieldtypes = {
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
};

components.buildField = function(options){
	options = options || {};
	// base
	options.id = ifundefined(options.id,'field_' + translator.new());
	options.type = ifundefined(options.type,'text'); 
	options.title = ifundefined(options.title,components.fieldtypes[options.type] || '表单字段');
	options.description = ifundefined(options.description,'');
	options.layout = options.layout || 2 ; //1.并列 2.换行 3.隐藏标题
	if(options.type == 'separator'){
		options.layout = 3;
	}
	options.width = ifundefined(options.width,'100%'); // 100%、75%、67%、50%、33%、25%
	options.check_unique = ifundefined(options.check_unique,false);
	options.check_unique_tip = ifundefined(options.check_unique_tip,'此字段设置了数据唯一，不能与已有数据重复');
	options.min_length = ifundefined(options.min_length,null);
	options.max_length = ifundefined(options.max_length,null);
	options.min_val = ifundefined(options.min_val,null);
	options.max_val = ifundefined(options.max_val,null);
	// text
	// textarea 
	options.rows = ifundefined(options.rows,2);
	// number 
	options.digit = ifundefined(options.digit,0);
	options.format = ifundefined(options.format,1); // 1.普通 2.百分数 3.千分数
	// select 
	options.enum_source = ifundefined(options.enum_source,1); // 1.自定义 2.基础数据 3.父级
	options.enum_data = ifundefined(options.enum_data,[
			{text:'选项1',value:'1'},
			{text:'选项2',value:'2'}]);
	options.enum_base = ifundefined(options.enum_base,'');
	// checkbox、radio
	options.check_select = ifundefined(options.check_select,1); //1.至少 2.最多 3.恰好
	options.check_select_count = ifundefined(options.check_select_count,null);
	options.enum_other = ifundefined(options.enum_other,0)
	// date
	// open
	// upload_pic
	// ...

	return options;
}

export  default components;