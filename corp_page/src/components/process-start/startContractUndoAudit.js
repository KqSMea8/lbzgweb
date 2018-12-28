import utils from './utils'

// [{"type":"text","id":"field_fZHHtoxaVtimSFeN2DQ97U","title":"合同编号","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_ug5wYQF11BjedbeQ9hscKN","title":"客户","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"checkbox","id":"field_5AhHMFZX8wYM1pczP7jrVJ","title":"标识","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"含加工工序","value":"has_procedure"},{"text":"是否外销","value":"is_foreign"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_uNS5xa7qBQ28vdiB8UVTGw","title":"业务员","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_3bTxaXWS9V1njCkwHdFhq2","title":"签订日期","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_p5Y8TfNEZtMeFiZbCaRJag","title":"签订地点","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_fH6Mivn6n2SqR2CSYrpwvM","title":"合同金额","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_sNGRim3TaNwr8guNMkyqZ2","title":"币种","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_3XewE54mJArNjhbtfAnxdK","title":"交货地点","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_aCXq6C1Pi9roMhDPYmw67W","title":"备注","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"editor","id":"field_6dMJwGbX2kuTXrmq55xp3S","title":"明细","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"textarea","id":"field_qua6noemtJfx9imwACXhjF","title":"撤销原因","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0}]

// 发起合同审核
function startContractUndoAudit(data,other,callback){  
  var conf = {
  	"form_id":23,
    "业务对象":"fp_field_businessObj",
    "合同Id":"fp_field_contractId",
    "合同编号":"fp_field_contractNo",
    "客户":"fp_field_customerName",
    "标识":"fp_field_flag",//has_procedure、is_foreign 
    "业务员":"fp_field_salesName",
    "签订日期":"fp_field_signDate",
    "签订地点":"fp_field_signAddress",
    "合同金额":"fp_field_amount",
    "币种":"fp_field_currencyName",
    "交货地点":"fp_field_deliveryAddress",
    "备注":"fp_field_remark", 
    "明细Html":"fp_field_detailsHTML", 
    "撤销原因":"fp_field_reason", 
  };
  var fd = {
  	processKey:conf.form_id
  };
  fd.businessKey = data.contractId;
  fd[conf["合同Id"]] = data.contractId;
  fd[conf["合同编号"]] = data.contractNo;
  fd[conf["客户"]] = data.customerName;
  fd[conf["标识"]] = (()=>{
  	var retVal = [];
  	if(data.hasProcedure){
  		retVal.push('has_procedure');
  	}
  	if(data.isForeign){
  		retVal.push('is_foreign');
  	}
  	return retVal.join(',');
  })();;
  fd[conf["业务员"]] = data.salesName;
  fd[conf["签订日期"]] = data.signDate;
  fd[conf["签订地点"]] = data.signAddress;
  fd[conf["合同金额"]] = data.amount;
  fd[conf["币种"]] = data.currencyName;
  fd[conf["交货地点"]] = data.deliveryAddress;
  fd[conf["备注"]] = data.remark;
  fd[conf["撤销原因"]] = other.remark;
  fd[conf["业务对象"]] = JSON.stringify(data);
  fd[conf["明细Html"]] = utils.buildTableHtml([
  	{
  		title:'序号',
  		render:(index,row)=>{
  			return index + 1;
  		}
  	},
  	{
  		title:'图号',
  		key:'drawing'
  	},
  	{
  		title:'物料名称',
  		key:'materName'
  	},
  	{
  		title:'材料',
  		key:'texture'
  	},
  	{
  		title:'硬度',
  		key:'hardness'
  	},
  	{
  		title:'交货类型',
  		key:'deliveryTypeName'
  	},
  	{
  		title:'单位',
  		key:'unitName'
  	},
  	{
  		title:'数量',
  		key:'quantity'
  	},
  	{
  		title:'重量',
  		key:'weight'
  	},
  	{
  		title:'总重',
  		key:'weightTotal'
  	}, 
  	{
  		title:'交货日期',
  		key:'deliveryDate'
  	}, 
  	{
  		title:'备注',
  		key:'remark'
  	}, 
  	{
  		title:'毛坯单价',
  		key:'workblank'
  	}, 
  	{
  		title:'毛坯费',
  		key:'workblankFee'
  	}, 
  	{
  		title:'加工费',
  		key:'processFee'
  	}, 
  	{
  		title:'包装费',
  		key:'packingFee'
  	},
  	{
  		title:'运费',
  		key:'freightFee'
  	},
  	{
  		title:'港口费',
  		key:'portFee'
  	},
  	{
  		title:'模型费',
  		key:'modelFee'
  	}, 
  	{
  		title:'金额',
  		key:'amount'
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

export default startContractUndoAudit;