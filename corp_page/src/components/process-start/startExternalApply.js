import utils from './utils.js'

// 发起合同审核
function begin(data,callback){  
  var conf = {
  	"form_id":31, 
  	"明细Html":"fp_field_detailsHTML", 
  };
  var fd = {
  	processKey:conf.form_id
  };
  fd.businessKey = 'data.external_apply_id'; 
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