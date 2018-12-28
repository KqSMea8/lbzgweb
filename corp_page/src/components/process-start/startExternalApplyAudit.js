import utils from './utils'


// 发起合同审核 
function startExternalApplyAudit(data,callback){  
	/* debugger */
  var conf = {
  	"form_id":50,
		"业务对象":"fp_field_businessObj",
		"外检申请单编号":"fp_field_externalApplyId",
  	"申请人":"fp_field_operatorName",
  	"申请日期":"fp_field_applicationDate",
  	"申请部门":"fp_field_departmentName",//has_procedure、is_foreign 
  	"备注":"fp_field_remark", 
  	"明细Html":"fp_field_detailsHTML", 
  };
  var fd = {
  	processKey:conf.form_id
  };
	/* alert(fd.processKey) */
  fd.businessKey = data.instrumnetId;
  fd[conf["外检申请单编号"]] = data.externalApplyId;
  fd[conf["申请人"]] = data.operatorName;
  fd[conf["申请部门"]] = data.departmentName;
  fd[conf["申请日期"]] = data.applicationDate;
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
  		title:'计量器具名称',
  		key:'counterName'
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
  		title:'生产厂家',
  		key:'orgin'
  	},
  	{
  		title:'内部编号',
  		key:'internalId'
  	}, 
  	{
  		title:'安装位置',
  		key:'installationSite'
  	}, 
  	{
  		title:'检定周期',
  		key:'retestingPeriod'
  	}, 
  	{
  		title:'上次鉴定时间',
  		key:'lastTestingTime'
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
 
export default startExternalApplyAudit;