import utils from './utils'

//[{"type":"text","id":"field_fZHHtoxaVtimSFeN2DQ97U","title":"合同编号","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_ug5wYQF11BjedbeQ9hscKN","title":"客户","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"checkbox","id":"field_5AhHMFZX8wYM1pczP7jrVJ","title":"标识","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"含加工工序","value":"has_procedure"},{"text":"是否外销","value":"is_foreign"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_uNS5xa7qBQ28vdiB8UVTGw","title":"业务员","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_3bTxaXWS9V1njCkwHdFhq2","title":"签订日期","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_p5Y8TfNEZtMeFiZbCaRJag","title":"签订地点","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_fH6Mivn6n2SqR2CSYrpwvM","title":"合同金额","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_sNGRim3TaNwr8guNMkyqZ2","title":"币种","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_3XewE54mJArNjhbtfAnxdK","title":"交货地点","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_aCXq6C1Pi9roMhDPYmw67W","title":"备注","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"editor","id":"field_6dMJwGbX2kuTXrmq55xp3S","title":"明细","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0}]

// 发起合同审核
function startAdjustpriceAudit(data,callback){  
  var conf = {
  	"form_id":37,
    "业务对象":"fp_field_businessObj",
    "供货商":"fp_field_providerName",
    "操作人":"fp_field_sales",
  	"操作日期":"fp_field_createTime",
  	"备注":"fp_field_remark",
  	"明细Html":"fp_field_detailsHTML", 
  };
  var fd = {
  	processKey:conf.form_id
  };
  fd.businessKey = data.pmapId;
  fd[conf["供货商"]] = data.providerName;
  fd[conf["操作人"]] = data.sales;
  fd[conf["操作日期"]] = data.createTime;
  fd[conf["备注"]] = data.remark;
  fd[conf["业务对象"]] = JSON.stringify(data);
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
  		title:'物料类别',
  		key:'subType'
  	},
  	{
  		title:'适用工序或产品',
  		key:'use'
  	},
  	{
  		title:'修改前价格',
  		key:'price'
  	},
  	{
  		title:'修改后价格',
  		key:'afterPrice'
  	},
  	{
  		title:'备注',
  		key:'remark'
  	}, 
  	{
  		title:'创建人',
  		key:'creator'
  	}
  ],data.detailList);
   
  utils.start(fd,(err,dt)=>{
  	if(err){
  		callback(err);
  	}else{
  		callback(null,dt.procInstId);
  	}
  });
}

export default startAdjustpriceAudit;