import utils from './utils'

//[{"type":"text","id":"field_purchaseOrderId","title":"订单编号","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"date","id":"field_createTime","title":"申请日期","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_proposerName","title":"申请人","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_departmentName","title":"申请部门","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"textarea","id":"field_quality","title":"质量要求","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"text","id":"field_remark","title":"备注","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0},{"type":"editor","id":"field_detailsHTML","title":"明细","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0}]

// 发起合同审核
function start(data,callback){  
  var conf = {
  	"form_id":26,
    "form_id1":26, // 办公类物料
    "form_id2":27, // 常规类物料
    "form_id3":28, // 设备类物料，不大于 2000
    "form_id4":29, // 设备类物料，大于 2000
    "业务对象":"fp_field_businessObj",
    "订单编号":"fp_field_purchaseOrderId",
  	"申请日期":"fp_field_createTime",
  	"申请人":"fp_field_proposerName",
  	"申请部门":"fp_field_departmentName",//has_procedure、is_foreign 
  	"质量要求":"fp_field_quality",
  	"备注":"fp_field_remark",
  	"明细Html":"fp_field_detailsHTML", 
  };
  if(data.detailList){
    var minSubType = 3;
    var amount = 0;

    data.detailList.map((item)=>{
      amount += item.amount * 1;
      if(item.subType < minSubType){
        minSubType = item.subType;
      }
    });
    
    if(minSubType == 1){
      conf.form_id = conf.form_id3;
      if(amount > 2000){
        conf.form_id = conf.form_id4;
      }
    }
    if(minSubType == 2){
      conf.form_id = conf.form_id2;
    }
    if(minSubType == 3){
      conf.form_id = conf.form_id1;
    } 
  }
  var fd = {
  	processKey:conf.form_id
  };
  fd.businessKey = data.purchaseOrderId;
  fd[conf["业务对象"]] = data;

  fd[conf["订单编号"]] = data.purchaseOrderId;
  fd[conf["申请日期"]] = data.createTime;
  fd[conf["申请人"]] = data.proposerName;
  fd[conf["申请部门"]] = data.departmentName;
  fd[conf["质量要求"]] = data.quality;
  fd[conf["备注"]] = data.remark; 
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
  	{
  		title:'数量',
  		key:'quantity'
  	},
  	{
  		title:'用途',
  		key:'use'
  	},
  	{
  		title:'供应商',
  		key:'providerName'
  	}, 
    {
      title:'供方或产地',
      key:'origin'
    }, 
  	{
  		title:'需求日期',
  		key:'needDate'
  	}, 
  	{
  		title:'备注',
  		key:'remark'
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