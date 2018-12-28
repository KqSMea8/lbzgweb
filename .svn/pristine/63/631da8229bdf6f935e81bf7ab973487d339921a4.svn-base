import utils from './utils'

function providerPayApply(data,callback){  
  var conf = {
  	"form_id":31,
    "供应商":"fp_field_providerName",
    "供应商编码":"fp_field_providerId",
  	"采购合同编号":"fp_field_orderId",
	  "申请费用":"fp_field_amount",
	  "申请人":"fp_field_proposer",
  	"操作日期":"fp_field_createTime",
  	"附件":"fp_field_files",
  	"备注":"fp_field_remark",
  };
  var fd = {
  	processKey:conf.form_id
  };
  fd.businessKey = data.paId;
  fd[conf["供应商"]] = data.providerName;
  fd[conf["供应商编码"]] = data.providerId;
  fd[conf["采购合同编号"]] = data.orderId;
  fd[conf["申请费用"]] = data.amount;
  fd[conf["申请人"]] = data.proposer;
  fd[conf["操作日期"]] = data.createTime;
  fd[conf["附件"]] = data.files;
  fd[conf["备注"]] = data.remark;
  fd[conf["业务对象"]] = JSON.stringify(data);
   
  utils.start(fd,(err,dt)=>{
  	if(err){
  		callback(err);
  	}else{
  		callback(null,dt.procInstId);
  	}
  });
}

export default providerPayApply;