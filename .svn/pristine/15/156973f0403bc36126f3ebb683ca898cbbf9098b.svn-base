import utils from './utils'

//[{"type":"text","id":"field_fZHHtoxaVtimSFeN2DQ97U","title":"合同编号","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_ug5wYQF11BjedbeQ9hscKN","title":"客户","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"checkbox","id":"field_5AhHMFZX8wYM1pczP7jrVJ","title":"标识","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"含加工工序","value":"has_procedure"},{"text":"是否外销","value":"is_foreign"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_uNS5xa7qBQ28vdiB8UVTGw","title":"业务员","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_3bTxaXWS9V1njCkwHdFhq2","title":"签订日期","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_p5Y8TfNEZtMeFiZbCaRJag","title":"签订地点","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_fH6Mivn6n2SqR2CSYrpwvM","title":"合同金额","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_sNGRim3TaNwr8guNMkyqZ2","title":"币种","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_3XewE54mJArNjhbtfAnxdK","title":"交货地点","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_aCXq6C1Pi9roMhDPYmw67W","title":"备注","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"editor","id":"field_6dMJwGbX2kuTXrmq55xp3S","title":"明细","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0}]

// 发起合同审核
function startOutContractAudit(data,callback){  
  var conf = {
  	"form_id":35,
    "定做人":"fp_field_partA",
    "承揽人":"fp_field_partB",
  	"合同编号":"fp_field_contractNo",
  	"签订日期":"fp_field_signDate",
  	"签订地点":"fp_field_signAddress",
  	"明细Html":"fp_field_detailsHTML", 
  };
  var fd = {
  	processKey:conf.form_id
  };
  fd.businessKey = data.contractId;
  fd[conf["定做人"]] = data.partA;
  fd[conf["承揽人"]] = data.partB;
  fd[conf["合同编号"]] = data.contractNo;
  fd[conf["签订日期"]] = data.signDate;
  fd[conf["签订地点"]] = data.signAddress;
  fd[conf["明细Html"]] = utils.buildTableHtml([
  	{
  		title:'序号',
  		render:(index,row)=>{
  			return index + 1;
  		}
  	},
  	{
  		title:'名称',
  		key:'materName'
  	},
  	{
  		title:'图号',
  		key:'drawing'
  	},
  	{
  		title:'材质',
  		key:'texture'
  	},
  	{
  		title:'数量（件）',
  		key:'quantity'
  	},
  	{
  		title:'计量单位',
  		key:'unit'
  	},
  	{
  		title:'重量（KG）',
  		key:'weight'
  	},
  	{
  		title:'总重量（KG）',
  		key:'weightTotal'
  	},
  	{
  		title:'加工费',
  		key:'processFee'
  	},
  	{
  		title:'总金额（元）',
  		key:'amount'
  	}, 
  	{
  		title:'交货期',
  		key:'deliveryDate'
  	}, 
  	{
  		title:'备注',
  		key:'remark'
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

export default startOutContractAudit;