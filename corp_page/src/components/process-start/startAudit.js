import utils from './utils'

//[{"type":"editor","id":"field_detailsHTML","title":"内容","description":"","layout":2,"width":"100%","check_unique":false,"check_unique_tip":"此字段设置了数据唯一，不能与已有数据重复","min_length":null,"max_length":null,"min_val":null,"max_val":null,"rows":2,"digit":0,"format":1,"enum_source":1,"enum_data":[{"text":"选项1","value":"1"},{"text":"选项2","value":"2"}],"enum_base":"","check_select":1,"check_select_count":null,"enum_other":0}]

// 发起合同审核
function start(data,callback){  
  var conf = {
  	"form_id":30, 
  	"明细Html":"fp_field_detailsHTML", 
  };
  var fd = {
  	processKey:conf.form_id
  };
  fd.businessKey = 'dev-data'; 
  fd[conf["明细Html"]] = JSON.stringify(data);
   
  utils.start(fd,(err,dt)=>{
  	if(err){
  		callback(err);
  	}else{
  		callback(null,dt.procInstId);
  	}
  });
}

export default start;