/* ===================================
    treeComponent 树组件

    更新记录
    v0.3 rx 20170425
   =================================== */

//树组件定义
var treeComponent =
(function(){ 

    // 树结点对象
    function treeNode(options){
        $.extend(this,{
            id:'',
            title:'',
            parent:null,
            level:0,
            open:true,
            check:0,
            select:0,
            children:[],
            data:{},
            onEvent:function(e){
            }
        },options);
    }
    // 遍历结点
    treeNode.prototype.foreach = function(call){
        var arr = [];
        arr.push(this); 

        for(var i = 0;i<this.children.length;i++){
            this.children[i].foreach(function(node){
                arr.push(node);
            });
        }

        for(var i =0;i<arr.length;i++){
            call(arr[i]);
        }
    }
    // 查找结点
    treeNode.prototype.first = function(where){
        if(where(this)){
            return this;
        }else{
            for(var i = 0;i<this.children.length;i++){
                var node = this.children[i].first(where);
                if(node){
                    return node;
                }
            }
        }
    }
    treeNode.prototype.doEvent = function(name,$e){
        var e = {name:name,sender:this,$e:$e,handled:false}; 
        this.onEvent(e);
        var parent = this.parent;
         
        if(e.handled){ 
            if($e){
               $e.cancelBubble = true; 
           } 
        }
        while(!e.handled && parent)
        {
            parent.onEvent(e);
            if(e.handled && $e){ 
                $e.cancelBubble = true;
            }
            parent = parent.parent;
        }
    }
    treeNode.prototype.refresh = function(){
        for(var i = 0;i<this.children.length;i++){
            this.children[i].parent = this;
            this.children[i].level = this.level+1;
            this.children[i].refresh();
        }
    }

    // 树组件初始化
    function init(target, options) {
        options = options || {};
        var state = target.options;
        if(state){
            // refresh();
        }else{ 
            state = $.extend(true,{},constructor.defaults,
                { 
                    initCheck:[],
                    data:[]
                },options);
            state.root.onEvent = function(e){   
                if(e.name == 'checkall'){
                    var node = e.sender;
                    e.handled = true;
                    node.check = !node.check;
                    node.foreach(function(item){ 
                        item.check = node.check; 
                    });
                    return;
                }
                if(e.name == 'select'){
                    var node = e.sender;
                    e.handled = true;
                    state._root.foreach(function(item){
                        item.select = node == item;
                    });
                    state.onSelect.call(target,e);
                    return;
                }
                if(e.name == 'fold'){
                    var node = e.sender;
                    e.handled = true;
                    node.open=!node.open;
                    return;
                }
                state.onEvent.call(target,e);
            };
                 
            options = state;
            state._root = new treeNode(options.root);
            target.options = options;
        }
    }

    // 构造函数
    function constructor(options){
        if(this == window){
            return new constructor(options)
        }else{
            init(this, options);
            return this;
        }
    }

    // 方法 
    constructor.prototype.m1 = function(target,param){ 
    };
    // 获取选中行
    constructor.prototype.getCheck = function(){
        var arr = [];
        var options = this.options;
        options._root.foreach(function(node){
            if(node.check){
                arr.push(node.data[options.idProperty]);
            }
        })
        return arr;
    };
    // 获取选中行
    constructor.prototype.getCheckData = function(){
        var arr = [];
        var options = this.options;
        options._root.foreach(function(node){
            if(node.check){
                arr.push(node.data);
            }
        })
        return arr;
    };
    // 设置选中行
    constructor.prototype.setCheck = function(arr){
        var options = this.options;

        var strIds = ','+arr.join(',')+',';
        options._root.foreach(function(node){
            if(strIds.indexOf(','+node.data[options.idProperty]+',')>=0){
                node.check = true;
            }else{
                node.check = false;
            }
        });
    };
    // 获取选择行
    constructor.prototype.getSelect = function(){
        var arr = [];
        var options = this.options;
        options._root.foreach(function(node){
            if(node.select){
                arr.push(node.data[options.idProperty]);
            }
        })
        return arr;
    };
    // 设置选择行
    constructor.prototype.setSelect = function(arr){
        var options = this.options;

        var strIds = ','+arr.join(',')+',';
        options._root.foreach(function(node){
            if(strIds.indexOf(','+node.data[options.idProperty]+',')>=0){
                node.select = true;
            }else{
                node.select = false;
            }
        });
    };
    // 展开层级
    constructor.prototype.openLevel = function(level){
        var options = this.options;
        options._root.foreach(function(node){
            node.open = node.level<=level;
        });
    };
    // 查找结点
    constructor.prototype.first = function(where){
        var options = this.options;
        return options._root.first(where);
    };
    // 遍历结点
    constructor.prototype.foreach = function(call){
        var options = this.options;
        options._root.foreach(call);
    };
    // 加载数据
    constructor.prototype.load = function(){
        // 加载数据
        var options = this.options; 
        options.onLoad.call(this);
    };
    // 构建树结点
    constructor.prototype.build = function(node){
        // 加载数据
        var options = this.options;

        if(node){

            var parentId = '';
            if(node.data && node.data[options.idProperty]){
                parentId = node.data[options.idProperty];
            }
            var arr = [];
            for(var i = 0;i<options.data.length;i++){
                var item = options.data[i];
                if(item[options.parentIdProperty] == parentId){
                    var subNode = new treeNode({
                        data:item,
                        title:item[options.titleProperty]
                    });
                    arr.push(subNode);
                    this.build(subNode);
                }
            }
            node.children = arr;
            node.refresh();
        }else{
            if(options._root != null){ 
                // 记录展开的状态
                node = options._root; 

                this.build(node);  
            }
        }

        return this;
    };

    // 构建结点并还原状态
    constructor.prototype.buildRestoreStatus = function(node){
        var options = this.options;
        node = node || options._root;
        if(node){
            var openIds = [];
            var checkIds = [];
            var selectIds = [];
            node.foreach(function(item){
                if(item.open){
                    openIds.push(item.data[options.idProperty]);
                }
                if(item.check){
                    checkIds.push(item.data[options.idProperty]);
                }
                if(item.select){
                    selectIds.push(item.data[options.idProperty]);
                }
            });

            this.build(node);

            //还原展开的状态 
            var openIds_str = ','+openIds.join(',')+',';
            var checkIds_str = ','+checkIds.join(',')+',';
            var selectIds_str = ','+selectIds.join(',')+',';
            node.foreach(function(item){
                item.open = openIds_str.indexOf(','+ item.data[options.idProperty] +',')>=0;
                item.check = checkIds_str.indexOf(','+ item.data[options.idProperty] +',')>=0;
                item.select = selectIds_str.indexOf(','+ item.data[options.idProperty] +',')>=0;
            });
        }
    }

    // 默认选项
    constructor.defaults = { 
        root:{title:'根结点',data:{id:'0',title:'root',parent:null}},
        data:null,
        isLoad:false,
        idProperty:'id',                //数据项 Id 标示
        parentIdProperty:'parentId',    //数据项 parentId 标示
        titleProperty:'title',
        onEvent:function(){},
        onSelect:function(){},
        onLoad:function(){
            this.isLoad = true;
            this.data = [{title:'请重写 onLoad 方法!',parentId:''}];
            this.build();
        }
    }
    return constructor;
})();

export default treeComponent;