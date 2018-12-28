import utils from './utils'

//[{"type":"description","id":"field_221UJ1ycWjdqsbnFooDm51","title":"公司各部门：","description":"以下供应商所供物料经样品试用（小批量生产）验证，结果良好，符合公司质量技术要求。经公司评定为合格供应商，即日起正常供货。请知悉！\n","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_providerName","title":"供应商","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_buyerName","title":"采购员","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"editor","id":"field_aWqu38sg2hmbEtmoUnS6UH","title":"明细","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0}]

// 发起合同审核
function start(data,callback){
  var conf = {
  	"form_id":25,
    "业务对象":"fp_field_businessObj",
    "订单编号":"fp_field_npmId",
  	"供应商":"fp_field_providerName",
  	"采购员":"fp_field_buyerName",
  	"明细Html":"fp_field_detailsHTML",
  };
  var fd = {
  	processKey:conf.form_id
  };
  fd.businessKey = data.npmId;
  fd[conf["业务对象"]] = data;

  fd[conf["订单编号"]] = data.npmId;
  fd[conf["供应商"]] = data.providerName;
  fd[conf["采购员"]] = data.buyerName;
  fd[conf["明细Html"]] = utils.buildTableHtml([
  	{
  		title:'序号',
  		render:(index,row)=>{
  			return index + 1;
  		}
  	},
  	{
  		title:'物料编码',
  		key:'materId'
  	},
  	{
  		title:'物料名称',
  		key:'materName'
  	},
  	{
  		title:'规格',
  		key:'spec'
  	},
  	{
  		title:'型号',
  		key:'model'
  	},
  	{
  		title:'单位',
  		key:'unitName'
  	},
  ],data.detailList);

  utils.start(fd,(err,dt)=>{
  	if(err){
  		callback(err);
  	}else{
  		callback(null,dt.procInstId);
  	}
  });
}

export default start;
