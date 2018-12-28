import utils from './utils'

//[{"type":"text","id":"field_fZHHtoxaVtimSFeN2DQ97U","title":"合同编号","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_ug5wYQF11BjedbeQ9hscKN","title":"客户","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"checkbox","id":"field_5AhHMFZX8wYM1pczP7jrVJ","title":"标识","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"含加工工序","value":"has_procedure"},{"text":"是否外销","value":"is_foreign"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_uNS5xa7qBQ28vdiB8UVTGw","title":"业务员","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_3bTxaXWS9V1njCkwHdFhq2","title":"签订日期","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_p5Y8TfNEZtMeFiZbCaRJag","title":"签订地点","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_fH6Mivn6n2SqR2CSYrpwvM","title":"合同金额","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_sNGRim3TaNwr8guNMkyqZ2","title":"币种","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_3XewE54mJArNjhbtfAnxdK","title":"交货地点","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_aCXq6C1Pi9roMhDPYmw67W","title":"备注","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"editor","id":"field_6dMJwGbX2kuTXrmq55xp3S","title":"明细","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0}]

// 发起合同审核
function startDispositionAudit(data,callback){  
  var conf = {
  	"form_id":33,
    "业务对象":"fp_field_businessObj",
    "处置单编号":"fp_field_disposeId",
    "投诉单编号":"fp_field_complaintId",
    "合同Id":"fp_field_contractId",
  	"合同编号":"fp_field_contractNo",
  	"客户":"fp_field_customerName",
  	"标识":"fp_field_flag",//has_procedure、is_foreign 
  	"业务员":"fp_field_salesName",
  	"处置地点":"fp_field_address",
  	"明细Html":"fp_field_detailsHTML", 
  };
  var fd = {
  	processKey:conf.form_id
  };
  fd.businessKey = data.disposeId;
  fd[conf["处置单编号"]] = data.disposeId;
  fd[conf["投诉单编号"]] = data.complaintId;
  fd[conf["合同Id"]] = data.contractId;
  fd[conf["合同编号"]] = data.contractNo;
  fd[conf["客户"]] = data.customerName;
  fd[conf["业务员"]] = data.salesName;
  fd[conf["处置地点"]] = data.address;
  fd[conf["初次交付时间"]] = data.firstDeliver;
  fd[conf["联系人"]] = data.linkMan;
  fd[conf["联系电话"]] = data.linkPhone;
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
  		title:'铸件号',
  		key:'casting'
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
      title:'金额',
      key:'amount'
    },
  	{
  		title:'备注',
  		key:'remark'
  	},
    {
      title:'金额说明',
      key:'amountRemark'
    },
    {
      title:'处理要求',
      key:'requireRemark'
    },
  ],[data]);
   
  utils.start(fd,(err,dt)=>{
  	if(err){
  		callback(err);
  	}else{
  		callback(null,dt.procInstId);
  	}
  });
}

export default startDispositionAudit;