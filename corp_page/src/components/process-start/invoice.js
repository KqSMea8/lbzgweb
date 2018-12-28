import utils from './utils'

function invoice(data,callback){  
  var conf = {
  	"form_id":32,
    "发货申请人":"fp_field_proposerName",
    "客户":"fp_field_customerName",
  	"合同编号":"fp_field_contractNo",
	  "合同金额":"fp_field_amount",
	  "业务员":"fp_field_salesName",
  	"交货地点":"fp_field_deliveryAddress",
    "备注":"fp_field_remark",
    "单据明细":"fp_field_detailsHTML",
  };
  var fd = {
  	processKey:conf.form_id
  };
  fd.businessKey = data.shippingId;
  fd[conf["发货申请人"]] = data.proposerName;
  fd[conf["客户"]] = data.customerName;
  fd[conf["合同编号"]] = data.contractNo;
  fd[conf["合同金额"]] = data.amount;
  fd[conf["业务员"]] = data.salesName;
  fd[conf["交货地点"]] = data.deliveryAddress;
  fd[conf["备注"]] = data.remark;

  fd[conf["单据明细"]] = utils.buildTableHtml([
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
  		title:'图号',
  		key:'drawing'
    },
    {
  		title:'铸件号',
  		key:'casting'
  	},
  	{
  		title:'材质',
  		key:'texture'
  	},
  	{
  		title:'单位',
      key:'unit',
      //render:(index,row)=>{return $args.getArgText('unit',item.unit)}
  	},
  	{
  		title:'数量',
  		key:'quantity'
  	},
  	{
  		title:'单重（公斤）',
  		key:'weight'
  	},
  	{
  		title:'总重（公斤）',
  		key:'weightTotal'
  	}, 
  	{
  		title:'单价（元）',
  		key:'workblank'
    }, 
    {
  		title:'总价（元）',
  		key:'amount'
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

export default invoice;