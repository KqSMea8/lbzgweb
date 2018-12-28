import dateFormat from '@/assets/js/dateFormat';

var page = {};
page.formatDate = function(val) {
  if(val != null &&　typeof(val) == 'object'){
    return dateFormat(val,'yyyy-MM-dd');
  } 
  return val;
}
page.formatDateTime = function(val) {
  if(val != null &&　typeof(val) == 'object'){
    return dateFormat(val,'yyyy-MM-dd hh:mm:ss');
  } 
  return val;
}


page.table = {};
page.$args = Vue.prototype.$args;
// 人员
page.table.initPersonColumn = function(col){
	return Object.assign({
    title: '申请人',
    key: 'proposer',
    align: 'center',
    render:(h,params)=>{  
      var row = params.row;
      var column = params.column;
      return h('span', page.$args.getArgText('empList',row[column.key]));
    },
    width:150
  },col);
}
// 部门
page.table.initDepartmentColumn = function(col){
	return Object.assign({
    title: '部门',
    key: 'department',
    align: 'left',
    render:(h,params)=>{  
      var row = params.row;
      var column = params.column;
      return h('span', page.$args.getArgText('deptList',row[column.key]));
    },
    width:150
  },col);
}
// 日期
page.table.initDateColumn = function(col){
  return Object.assign({
    title: '创建日期',
    key: 'createTime',
    align: 'center',
    render:(h,params)=>{  
      var row = params.row;
      var column = params.column;
      var date = params.row[column.key]; 
      return h('span', date && date.length>=10?date.substring(0,10):date);
    },
    width:120
  },col);
}
// 参数 col 中需要 group
page.table.initArgColumn = function(col){
	return Object.assign({
    title: '参数',
    key: 'proposer',
    align: 'center',
    group:'empList',
    render:(h,params)=>{
      var row = params.row;
      var column = params.column; 
      return h('span', page.$args.getArgText(col.group,row[column.key]));
    },
    width:100
  },col);
}
// 映射
page.table.initMapColumn = function(col){
  return Object.assign({
    title: '文本',
    key: 'status',
    align: 'center',
    data:{
      '1':'正常',
      '2':'禁用',
    },
    render:(h,params)=>{
      var row = params.row;
      var column = params.column;
      return h('span', column.data[row[column.key] + '']);
    },
    width:100
  },col);
}
page.table.customerNameColumn = function(col){
  return Object.assign({
    title: '客户名称',
    key: 'customerName',
    align: 'left',
    minWidth:140
  },col);
}
page.table.outProviderName = function(col){
  return Object.assign({
    title: '名称',
    key: 'providerName',
    align: 'left',
    minWidth:150
  },col);
}
export default page;