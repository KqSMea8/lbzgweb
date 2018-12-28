import axios from 'axios'
import htmlUtil from '@/assets/js/htmlUtil'

var utils = {}; 

utils.start = function(fd,callback){
  axios.post('/api/wf/process/start',fd).then((res) => {

	  if (res.data.code == 0) {
	    callback(null,res.data.data);
	  } else {
	    callback({code:res.data.code,msg:res.data.message});
	  }
	}).catch((error) => {
	  callback({code:1,msg:'发起请求出错'});
	})
}

// 生产表格
utils.buildTableHtml =function(columns,data){
	var html = [];
	html.push('<style type="text/css">');
	html.push(`
	  table.html-table {
	    border-collapse: collapse;
	  }
	  table.html-table th,
	  table.html-table td{
	    min-height: 40px;
	    border: 1px solid #dedede;
	    text-align: center;
	    padding: 10px 10px;
	  }
	  table.html-table th{  
	    background-color: #efefef;
	    white-space:nowrap;
	  }
	  table.html-table td{
	     
	  }`);
	html.push('</style>');
	html.push('<table cellspacing="0" cellpadding="0" class="html-table">');
	html.push('<thead>');
	html.push('<tr>');
	columns.map((head)=>{ html.push('<th>'+ head.title +'</th>'); });
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>'); 
	if(data){
		for(var i =0;i<data.length;i++){
			var row = data[i];
			html.push('<tr>');
			columns.map((head)=>{ 
				var text = '';
				if(head.render){
					text = head.render(i,row);
				}else if(head.key){
					if(row[head.key] != undefined 
						&& row[head.key] != null){
						text = utils.encode(row[head.key]);
					}
				}
				html.push('<td>'+ text +'</td>'); 
			});
			html.push('</tr>');
		}
	}	
	html.push('</tbody>');
	html.push('</table>');
	return html.join('\r\n');
}

utils.encode = htmlUtil.htmlEncode;

export default utils;