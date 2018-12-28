import startContractAudit from './startContractAudit'
import startContractUndoAudit from './startContractUndoAudit'
import startContractReversionAudit from './startContractReversionAudit'
import startPurchaseOrderAudit from './startPurchaseOrderAudit'
import startNewMaterProviderAudit from './startNewMaterProviderAudit'
import startAudit from './startAudit'   
import startExternalApplyAudit from './startExternalApplyAudit'  
import providerPayApply from './providerPayApply'
import invoice from './invoice' 
import startComplaintAudit from './startComplaintAudit'
import startDispositionAudit from './startDispositionAudit' 
import startOutContractAudit from './startOutContractAudit' 
import startAdjustpriceAudit from './startAdjustpriceAudit' 
import startSelfMater from './startSelfMater' 
import startComplaintDisposeAudit from './startComplaintDisposeAudit'

import utils from './utils' 
 
var process = {};

process.getInstValue = function(instId,callback) {
  utils.get(instId,(err,data)=>{
  	if(err){
  		callback(err,null);
  	}else{
  		var fv = {};
  		if(data && data.varList){ 
  			data.varList.map((kv)=>{
  				if(kv.variableName.indexOf('field_')>-1)
  				{
  					fv[kv.variableName.substring(6)] = kv.value;
  				} 
  			})
  		}
  		callback(null,fv);
  	}
  });
}

// 发起合同审核
process.startContractAudit = startContractAudit;
process.startContractUndoAudit = startContractUndoAudit;
process.startContractReversionAudit = startContractReversionAudit;
// 发起采购订单审核
process.startPurchaseOrderAudit = startPurchaseOrderAudit;
// 发起新材料供应商评定审核
process.startNewMaterProviderAudit = startNewMaterProviderAudit;
process.startAudit = startAudit; 
process.startExternalApplyAudit = startExternalApplyAudit; 
process.providerPayApply = providerPayApply;
process.startOutContractAudit = startOutContractAudit;
process.invoice = invoice;
process.startAdjustpriceAudit = startAdjustpriceAudit;
process.startSelfMater = startSelfMater;
process.startComplaintDisposeAudit = startComplaintDisposeAudit;
 
export default process;