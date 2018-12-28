var short = require('short-uuid');
var translator = short();

//表单元素数据结构
//http://wiki.lyarc.com/pages/viewpage.action?pageId=8028211
let components = {};

function ifundefined(val,newVal){
	if(typeof(val) == 'undefined'){
		return newVal;
	}
	return val;
} 

// 结点
function flowNode(options){
	options = options || {};
	this.options = options;

	// 类型 active,group
	this.type = options.type || 'person';

	// 结点信息
	this.id = options.id || translator.new();
	this.title = options.title || '结点'; 
 	
	// 下一个、上一个
	this.next = null; 
	this.previous = null; 

	this.items = [];

	// 所在的组
	this.split = null;

	// 深度、宽度，大小计算
	this.depth = 1; 
	this.breadth = 1; 

	this.contentDepth = 0.68;
	this.contentBreadth = 1;

	this.x = 0;
	this.y = 0;

	this.maxDepth = 0;
	this.maxBreadth = 0;

	// 线
	this.line1 = 0;
	this.line2 = 0;
	this.line2Depth = 0.32;
}

// 线形遍历
flowNode.prototype.foreachNext = function(func){
	var node = this;
	while(node != null){
		func(node);
		node = node.next;
	}
}

// 遍历
flowNode.prototype.foreach = function(func){
	func(this);
	if(this.type == 'split'){
		for(var i=0;i<this.items.length;i++){
			this.items[i].foreach(func);
		}
	}
	if(this.next){
		this.next.foreach(func);
	}
}

// 头
flowNode.prototype.getStart = function(){
	var node = this.getLineStart();
	if(node.split){
		node = node.split.getStart();
	}
	return node;
}
 

// 链头
flowNode.prototype.getLineStart = function(){
	var node = this;
	var i = 0;
	while(node.previous != null){ 
		node = node.previous;
		i++;
		if(i > 99999){
			throw 'getLineStart 死循环！';
		}
	}
	return node;
}

// 链尾
flowNode.prototype.getLineEnd = function(){
	var node = this;
	var i = 0;
	while(node.next != null){ 
		node = node.next;
		i++;
		if(i > 99999){
			throw 'getLineEnd 死循环！';
		}
	}
	return node;
}

// 定位结点
flowNode.prototype.draw = function(offset){
 	 
	this.depth = this.getDepth();
	this.breadth = this.getBreadth();
	this.contentDepth = this.getContentDepth();
	this.contentBreadth = this.breadth;

	offset = offset || {x:0,y:(this.getLineBreadth()-1)/2};

	this.x = offset.x;
	this.y = offset.y;

  // node
  if(this.type != 'split'){
 
  	// 前面有结点
  	if(this.previous){
  		this.line1 = 1;
  	}  

  	// 后面有结点
  	if(this.next){
  		this.line2 = 1; 
  	}

  	// 是组的结尾
  	if(this.next == null && this.getLineStart().split){
  		this.line2 = this.getLineStart().line1;  
  		this.line2Depth = 0.32 + this.getLineStart().split.getContentDepth() - this.getLineStart().getLineDepth(); 
  	}
  }else{ 

  	if(this.previous && this.previous.type == 'split'){
			this.line1 = 1;
		}

		// 是组的结尾
		if(this.next == null  && this.getLineStart().split){
			this.line2 = this.getLineStart().line1; 
			this.line2Depth = 0.32 + this.getLineStart().split.getContentDepth() - this.getLineStart().getLineDepth(); 
		}

  	var breadth = 0;
  	for(var i = 0;i<this.items.length;i++){
  		var item = this.items[i];

  		item.line1 = 3
			if(i==0){
				item.line1 = 2;
			}
			if(i == this.items.length-1)
			{
				item.line1 = 4;
			} 
  		
  		item.split = this; 

  		item.draw({x:this.x + (this.line1?0.32:0) ,y: this.y + breadth + (item.getLineBreadth()-item.getBreadth())/2});

  		breadth += item.getLineBreadth();
  	} 
  }

  if(this.title == '2g3'){
  }
   
  // 下一个
  if(this.next){
  	this.next.draw({x:this.x + this.depth,y:this.y + (this.breadth - this.next.getBreadth())/2});
  }
}  

// 计算深度
flowNode.prototype.getDepth = function(){

	var depth = 0;

	if(this.type != 'split'){
		// 前面有结点
		if(this.previous){
			depth += 0.32;
		}
		// 组的开头
		if(this.previous == null && this.split){
			depth += 0.32;
		}
		depth += 0.68;
		// 后面有结点
		if(this.next){
			depth += 0.32;
		} 
		// 是组的结尾
		if(this.next == null && this.getLineStart().split){
			depth += 0.32; 
		}
	}else{
		depth = 0;
		// 组的开头
		if(this.previous == null && this.split){
			depth += 0.32;
		}
		if(this.previous && this.previous.type == 'split'){
			depth += 0.32;
		}
		if(this.next == null  && this.getLineStart().split){
			depth += 0.32;
		}

		var max = 0;
		for(var i=0;i<this.items.length;i++){
			var node = this.items[i];
			var itemDepth = node.getLineDepth();
			if(max < itemDepth){
				max = itemDepth;
			}
		}
		depth += max;
	} 
	return depth; 
}

// 计算广度
flowNode.prototype.getBreadth = function(){
	
	if(this.type !='split'){ 
		return 1;
	}

	var breadth = 0;
	for(var i=0;i<this.items.length;i++){ 
		var node = this.items[i];
 		breadth += node.getLineBreadth();
	}

	return breadth;
}

flowNode.prototype.getLineDepth = function(){
	var node = this;
	var depth = 0;
	while(node != null){
		depth += node.getDepth();
		node = node.next;
	}
	return depth;
}
flowNode.prototype.getLineBreadth = function(){
	
	var node = this;
	var breadth = 0;
	while(node != null){
		if(breadth < node.getBreadth()){
			breadth = node.getBreadth();
		}
		node = node.next;
	}
	return breadth;
}




// 获取组内结点深度
flowNode.prototype.getContentDepth = function(){
	if(this.type == 'split'){
		var max = 0;
		for(var i = 0;i<this.items.length;i++){
			var item = this.items[i];
			if(max < item.getLineDepth()){
				max = item.getLineDepth();
			}
		}
		return max;
	}
	return 0.68;
}

flowNode.prototype.insertNext = function(node){
	var end = node.getLineEnd();
  var next = this.next;
  this.next = node;
  node.previous = this;
  if(next){
  	end.next =  next;
  	next.previous = end;
  }
	return this; 
}

flowNode.prototype.add = function(node,index){ 
	index = index || this.items.length;
	node.split = this;
	this.items.splice(index,0,node);
	return this;
}

flowNode.prototype.remove = function(node){ 
	if(node){
		if(this.type == 'split'){
			var index = this.items.indexOf(node);
			if(index >= 0){
				this.items.splice(index,1);
				node.split = null;

				if(this.items.length == 1){
					var item = this.items[0];

					this.items.splice(0,1);
					item.split = null;

					var previous = this.previous; 
					if(previous){
						previous.insertNext(item);
						this.remove();
					}else{
						if(this.split){
							this.split.add(item,this.split.items.indexOf(this));
							this.split.remove(this);
						}
					}
				}else{
					if(this.items.length == 0){
						this.remove();
					}
				} 
			} 
		}
	}else{
		if(this.previous){
			this.previous.next = this.next; 
			if(this.next){
				this.next.previous = this.previous;
			} 
			this.previous = null;
			this.next = null;
		}else{ 
			if(this.split){
				var next = this.next;
				if(next){
					this.split.add(next,this.split.items.indexOf(this));
					next.previous = null;
					this.next == null; 
				}

				this.split.remove(this); 
				this.split = null;
			}
		}
	}
	return this;
}

components.storageNode = function(options){
	options = options || {};

	options.id = options.id || translator.new();
	options.type = options.type || 'creator';// start、end、split、join、creator、person、department、field、role、relative
	options.title = options.title || '发起人'; 

	options.executor = options.executor || '执行人';
	options.executorId = options.executorId || ''; 
	options.execPattern = options.execPattern || 1; //执行模式，1.单人执行 2.多人执行 3.全体执行 4.竞争执行
	options.notify = options.notify || false; //是否为知会结点
	options.flowAuth = options.flowAuth || []; //处理权限
	options.fieldAuth = options.fieldAuth || 'edit';
	options.branch = options.branch || {};
	options.branch.type = options.branch.type || 0; //0.无 1.自动 2.手动
	options.branch.remark = options.branch.remark || '';
	options.branch.condition = options.branch.condition || []; 

	options.next = options.next || null;
	options.items = options.items || null;

	return options;
}

components.buildStart = function(storageNodes){ 
	for(var i = 0;i<storageNodes.length;i++){
		if(storageNodes[i].type == 'start')
		{
			var node = storageNodes[i];
			var start = new flowNode({
				id:node.id,
				title:node.title,
				type:node.type,
				data:node});
			buildNext(start,storageNodes);
			start.draw();
			return start;
		}
	}
	return null;
}

function buildNext(start,storageNodes){
	if(start.options.data.next){
		var next = arrFind(storageNodes,(node)=>{return node.id == start.options.data.next});
		if(next && next.type != 'join'){
			var last = new flowNode({
				id:next.id,
				title:next.title,
				type:next.type,
				data:next});
			start.insertNext(last);
			buildNext(last,storageNodes);
		}
	}
	if(start.options.data.type == 'split'){ 
		var nodes = start.options.data.items;
		for(var i =0;i<nodes.length;i++){
			var node = arrFind(storageNodes,(node)=>{return node.id == nodes[i]});
			if(node){ 
				var last = new flowNode({
					id:node.id,
					title:node.title,
					type:node.type,
					data:node});
				start.add(last);
				buildNext(last,storageNodes);
			}
		}
	}
}

function arrFind(arr,func){
	for(var i = 0;i<arr.length;i++){
		if(func(arr[i]) == true){
			return arr[i];
		}
	}
	return null;
}

function arrWhere(arr,func){
	var ret = [];
	for(var i = 0;i<arr.length;i++){
		if(func(arr[i]) == true){
			ret.push(arr[i]);
		}
	}
	return ret;
}


components.getNodes = function(start,arr){
  arr = arr || [];

	start.foreachNext((node)=>{

		var data = node.options.data; 

		if(node.next){
			data.next = node.next.id
		}else{

			// 如果没有下一个结点，就查找当前线的头所在的 split 对应 join
			var lineSplit = node.getLineStart().split;
			if(lineSplit)
			{
				data.next = lineSplit.id + '_join';
			}
		}

		arr.push(data);

		if(node.type == 'split'){
			var itemIds = [];
			var lineLastIds = [];
			var join = components.storageNode({
				id:data.id + '_join',
				title:'join',
				type:'join',
				items:[],
				next:data.next
			});
			for(var i = 0;i< node.items.length;i++){
				var lineEnd = node.items[i].getLineEnd(); 
				lineLastIds.push(lineEnd.id); 
				itemIds.push(node.items[i].id);

				components.getNodes(node.items[i],arr);
			}
			data.items = itemIds;
			join.items = lineLastIds;
			arr.push(join);
		} 
	});
	return arr;
}

components.flowNode = flowNode; 

export  default components;