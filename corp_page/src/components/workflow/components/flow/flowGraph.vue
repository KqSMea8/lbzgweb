<template>
  <div :class="classes" @click="nodeClick(null)">
    <div class="flowgraph-body">
     <div class="flowgraph-svg" :style="flowgraphStyle" >
      <flowGraphNode v-for="(item,index) in nodes"
        @click="nodeClick"
        :model="item"
        :x="item.x" 
        :y="item.y" 
        :canvas="canvas"
        :line1="item.line1" 
        :line2="item.line2" 
        :line2Depth="item.line2Depth" 
        :type="item.type"
        :contentDepth="item.contentDepth"
        :contentBreadth="item.contentBreadth"
        :title="item.title"></flowGraphNode>
      <div v-if="nodeToolBar.display" :style="nodeToolBarStyle" @click.stop="" :class="{'flowgraph-svg-node-btns':true,
            'flowgraph-svg-node-btns-split':nodeToolBar.nodeType=='split',
            'flowgraph-svg-node-btns-start':nodeToolBar.nodeType=='start'}">
         <table>
           <tr>
             <td @click="nodeToolBarAdd" class="flowgraph-svg-node-btns-add">
              <Icon type="android-add-circle"></Icon>
              <div>添加节点</div>
             </td>
             <td @click="nodeToolBarAddSibling" class="flowgraph-svg-node-btns-addsibling">
              <Icon type="android-add-circle"></Icon>
              <div>添加同级节点</div>
             </td>
             <td @click="nodeToolBarEdit" class="flowgraph-svg-node-btns-edit">
              <Icon type="edit"></Icon>
              <div>编辑</div>
             </td>
             <td @click="nodeToolBarReplace" class="flowgraph-svg-node-btns-replace">
              <Icon type="refresh"></Icon>
              <div>替换</div>
             </td>
             <td @click="nodeToolBarRemove" class="flowgraph-svg-node-btns-remove">
              <Icon type="android-delete"></Icon>
              <div>删除节点</div>
             </td>
           </tr>
         </table>
      </div>
     </div>
    </div>
    <div :class="{'flowgrpah-layout':true,'flowgrpah-layout-ver':canvas.layout=='ver','flowgrpah-layout-hor':canvas.layout=='hor'}">
       <div @click="chgLayout(1)" class="flowgrpah-layout-item flowgrpah-layout-item-ver"><Icon type="android-more-horizontal"></Icon></div>
       <div class="flowgrpah-layout-line"></div>
       <div @click="chgLayout(2)" class="flowgrpah-layout-item  flowgrpah-layout-item-hor"><Icon type="android-more-vertical"></Icon></div>
    </div> 
  </div>
</template>
<script>
import flowGraphNode from '@/components/workflow/components/flow/flowGraphNode'
import flowJs from '@/components/workflow/components/flow/flow'


export default {
  components: {
    flowGraphNode
  },
  name:'flowgraph',
  props:{
    value: {
      type: Array,
      default:()=>{return [];}
    }
  },
  data() {
    var obj = { 
      nodes:[],
      nodeToolBar:{
        left:0,
        top:0,
        display:false,
        node:null,
        nodeType:'',
      },
      canvas:{
        layout:'ver',//ver hor
        editable:true,
      }
    }
    return obj;
  },
  mounted:function(){ 

    var data =  [
      this.initNode({id:'1',type:'start',next:'2g',title:'start'}),
      this.initNode({id:'2g',type:'split',next:'3',items:['2g1','2g2','2g3'],title:'2g'}), 
        this.initNode({id:'2g1',type:'person',next:'',title:'2g1'}),
        this.initNode({id:'2g2',type:'role',next:'2g21',title:'2g2'}),
          this.initNode({id:'2g21',type:'department',next:'2g22',title:'2g21'}),
          this.initNode({id:'2g22',type:'person',next:'2g23',title:'2g22'}),
          this.initNode({id:'2g23',type:'person',next:'',title:'2g23'}),
        this.initNode({id:'2g3',type:'person',next:'2g31g',title:'2g3'}), 
        this.initNode({id:'2g31g',type:'split',next:'2g32g',items:['2g31g1','2g31g2'],title:'2g31g'}),
          this.initNode({id:'2g31g1',type:'person',next:'2g31g11g',title:'2g31g1'}),
            this.initNode({id:'2g31g11g',type:'split',next:'',items:['2g31g11g1','2g31g11g2'],title:'2g31g11g'}),
              this.initNode({id:'2g31g11g1',type:'person',next:'',title:'2g31g11g1'}),
              this.initNode({id:'2g31g11g2',type:'person',next:'',title:'2g31g11g2'}),              
          this.initNode({id:'2g31g2',type:'person',next:'',title:'2g31g2'}),
        this.initNode({id:'2g32g',type:'split',next:'',items:['2g32g1','2g32g2','2g32g3'],title:'2g32g'}),
          this.initNode({id:'2g32g1',type:'person',title:'2g32g1'}),
          this.initNode({id:'2g32g2',type:'person',title:'2g32g2'}),
          this.initNode({id:'2g32g3',type:'person',title:'2g32g3'}),
      this.initNode({id:'3',type:'person',next:'4',title:'3'}),
      this.initNode({id:'4',type:'person',next:'5',title:'4'}),
      this.initNode({id:'5',type:'end',next:'',title:'end'}),
    ];  
    //this.$emit('input',data);
    this.clear();
  },
  computed:{
    classes(){
      return [
          'flowgraph'
        ]; 
    },
    flowgraphStyle(){
      var obj = {};
      if(this.nodes && this.nodes.length>0){
        var node = this.nodes[0];
        if(this.canvas.layout == 'ver'){
          obj.width = node.getLineDepth() * 100 + 'px';
          obj.height = node.getLineBreadth() * 100 + 'px';  
        }else{
          obj.width = node.getLineBreadth() * 100 + 'px';  
          obj.height = node.getLineDepth() * 100 + 'px';
        }
      }
      return obj;
    },
    nodeToolBarStyle(){
      var obj = {};
      obj.left = this.nodeToolBar.left + 'px';
      obj.top = this.nodeToolBar.top + 'px';
      return obj;
    } 
  },
  methods:{
    initNode:function(node){ 
      flowJs.storageNode(node);
      return node;
    },
    draw:function(){ 
      var start = flowJs.buildStart(this.value); 

      this.nodes = null;

      if(start != null){ 
        start.draw();
        var arr = []; 
        start.foreach((item)=>{ 
          arr.push(item); 
        }); 

        this.nodes = arr; 
      }  
    }, 
    chgLayout:function(flg){
      this.nodeToolBar.display = false;
      if(flg == 1){
        this.canvas.layout = 'ver';
      }else{
        this.canvas.layout = 'hor';
      }
    },
    nodeClick:function(node){
      if(node && node.type != 'end'){

        // 定位
        if(node.type != 'split'){
          if(this.canvas.layout == 'ver'){
            this.nodeToolBar.left=node.x*100;
            this.nodeToolBar.top=node.y*100;
          }else{
            this.nodeToolBar.left=node.y*100;
            this.nodeToolBar.top=node.x*100;
          }
        }else{
          var depth = node.model.getContentDepth();
          var breadth = node.model.getBreadth();
          if(node.line1>0){
            depth += 0.32;
          }
          if(this.canvas.layout == 'ver'){ 
            this.nodeToolBar.left=(node.x + depth)*100 -50;
            this.nodeToolBar.top=(node.y+breadth/2)*100 -30;
          }else{
            this.nodeToolBar.left=(node.y+breadth/2)*100 - 50;
            this.nodeToolBar.top=(node.x + depth)*100 - 20;
          }
        } 
        
        this.nodeToolBar.display = true;
        this.nodeToolBar.node = node;
        this.nodeToolBar.nodeType = node.model.type;
      }else{
        this.nodeToolBar.display = false;
      }
    },
    nodeToolBarAdd(){ 
      this.nodeToolBar.display = false; 
      this.nodeToolBar.operate = 'add';
      this.$emit('on-require-selectperson');
    },
    nodeToolBarAddSibling(){  
      this.nodeToolBar.display = false;
      this.nodeToolBar.operate = 'addsibling';
      this.$emit('on-require-selectperson');
    },
    nodeToolBarEdit(){
      this.nodeToolBar.display = false;
      var node = this.nodeToolBar.node; 
      var data = node.model.options.data;
      /*
      branch  
      execPattern 
      executor 
      executorId 
      fieldsAuth 
      flowAuth 
      id 
      items 
      next 
      notify 
      title 
      type 
      */
      this.$emit('on-require-edit',data);
    },
    nodeToolBarReplace(){ 
      this.nodeToolBar.display = false;
      this.nodeToolBar.operate = 'replace';
      this.$emit('on-require-selectperson');
    },
    nodeToolBarRemove(){
      var start = this.nodeToolBar.node.model.getStart();
      this.nodeToolBar.node.model.remove();
      var arr = flowJs.getNodes(start);
      // 更新数据源
      this.$emit('input',arr);  
      this.nodeToolBar.display = false;
    },
    edit(data){
      var node = this.nodeToolBar.node; 
      node.model.options.data = data;
      var arr = flowJs.getNodes(node.model.getStart());
      // 更新数据源
      this.$emit('input',arr); 
    },
    selectPerson(els,isConcurrence){
      var node = this.nodeToolBar.node;
      //var els = this.$refs['selectPerson'].select;
      //var isConcurrence = this.$refs['selectPerson'].isConcurrence;

      var newNodes = [];
      for(var i=0;i<els.length;i++){
        var el = els[i];

        var newNode = this.innerCreateFlowNode({type:el.type,title:el.title,executorId:el.key,executor:el.title});
        newNodes.push(newNode);
      } 

      if(newNodes.length == 0){
        return;
      }
      if(newNodes.length <= 1){
        isConcurrence = false;
      }

      if(this.nodeToolBar.operate == 'add'){ 
        if(isConcurrence){
          var groupNode = this.innerCreateFlowNode({type:'split',title:'split'});
          this.innerFlowNodeAdd(groupNode,newNodes);
          node.model.insertNext(groupNode);
        }else{ 
          for(var i = newNodes.length-1;i>=0;i--){
            node.model.insertNext(newNodes[i]);
          }
        }

        var arr = flowJs.getNodes(node.model.getStart());
        // 更新数据源
        this.$emit('input',arr); 
      }

      if(this.nodeToolBar.operate == 'addsibling'){
        var previous = node.model.previous;
        var split = null; 
        if(previous == null && node.model.split != null){
          split = node.model.split; 
          if(isConcurrence){
            for(var i = newNodes.length-1;i>=0;i--){
              split.add(newNodes[i],split.items.indexOf(node.model)+1);
            }
          }else{ 
            var start = newNodes[0];
            for(var i = newNodes.length-1;i>0;i--){
              start.insertNext(newNodes[i]);
            } 
            split.add(start,split.items.indexOf(node.model)+1);
          }
        }else{

          if(previous){ 
            split = this.innerCreateFlowNode({type:'split',title:'split'}); 

            previous.insertNext(split); 
            node.model.remove();
            split.add(node.model);

            if(isConcurrence){
              this.innerFlowNodeAdd(split,newNodes);
              //var groupNode = this.innerCreateFlowNode({type:'split',title:'split'});
              //this.innerFlowNodeAdd(groupNode,newNodes);
              //split.add(groupNode);              
            }else{ 
              var start = newNodes[0];
              for(var i = newNodes.length-1;i>0;i--){
                start.insertNext(newNodes[i]);
              } 
              split.add(start);
            } 
          }
        }
        var arr = flowJs.getNodes(node.model.getStart());
        // 更新数据源
        this.$emit('input',arr);  
      }

      if(this.nodeToolBar.operate == 'replace'){ 
        if(els.length >0){ 
          var el = els[0];
          node.model.options.type = el.type;
          node.model.options.title = el.title;
          node.model.options.data.executorId = el.key;
          node.model.options.data.executor = el.title;
          node.model.options.data.title = el.title;
          node.model.options.data.type = el.type;
          //node.type = el.type;
          //node.title = el.title;   

          var arr = flowJs.getNodes(node.model.getStart());
          // 更新数据源
          this.$emit('input',arr);
        }
      }
    },
    innerCreateFlowNode(options){
      options = options || {type:'split',title:'split'};
      var data = flowJs.storageNode(options); 
      var flowNode = new flowJs.flowNode({
        id:data.id,
        title:data.title,
        type:data.type,
        data:data
      });
      return flowNode;
    },
    innerFlowNodeAdd(split,items){
      for(var i = 0;i<items.length;i++){
        split.add(items[i]);
      }
    },
    clear:function(){
      var data = [this.initNode({id:'start',type:'start',next:'end',title:'发起人'}),
            this.initNode({id:'end',type:'end',next:'',title:'结束'})];

      this.$emit('input',data);
    }
  },
  watch:{
    value(val){
      if(val == null || val.length == 0){
        this.clear();
      }else{
        this.draw();
      }   
    },
    nodes(val){

    },
  }
}

</script>

<style type="text/css">
  .flowgraph{
    height: 100%; 
    -webkit-user-select:none;
    -moz-user-select:none;
    -ms-user-select:none;
    user-select:none;
  } 

  .flowgraph-body{
    height: 100%;
    overflow: hidden;
    overflow: auto;
    background-color: white;
    text-align: center;
    padding: 20px;
  }

  .flowgraph-svg{
    background-color: white;
    display: inline-block;
    margin: auto auto;
    position: relative;
  }

  .flowgrpah-layout{
    position: absolute;
    right: 58px;
    bottom: 100px;
    width: 50px;
    border: 1px solid #e5e5e5;
    box-shadow: 0px 3px 3px #eeeeee;
    padding: 2px;
    background-color: white;
  }
  .flowgrpah-layout-item{
    height: 50px;
    margin:2px;
    font-size: 40px;
    text-align: center;
    line-height: 50px;
    color:#ccc;
    cursor: pointer;
  }
  .flowgrpah-layout-item:hover{
    color:#5990cf
  }
  .flowgrpah-layout-ver .flowgrpah-layout-item-ver{
    color:#5990cf
  }
  .flowgrpah-layout-hor .flowgrpah-layout-item-hor{
    color:#5990cf
  }
  .flowgrpah-layout-line{
    height: 1px;
    border-bottom: 1px solid #e5e5e5;
  }

  /**/
  .flowgraph-svg-node-btns {
    display: inline-block;
    background-color: #e5e5e5;
    height: 58px;border-radius: 39px;
    position: absolute;
    padding: 0px 18px;
    z-index: 100;
  }
  .flowgraph-svg-node-btns td{
    padding: 4px 10px;white-space:nowrap;
  }
  .flowgraph-svg-node-btns td:hover{
    cursor: pointer;
  }
  .flowgraph-svg-node-btns td:hover .ivu-icon{
    color:#4779C5;
  } 
  .flowgraph-svg-node-btns .ivu-icon{
    font-size: 28px;color:#5990cf;
  }

  .flowgraph-svg-node-btns-split .flowgraph-svg-node-btns-addsibling,
  .flowgraph-svg-node-btns-split .flowgraph-svg-node-btns-replace,
  .flowgraph-svg-node-btns-split .flowgraph-svg-node-btns-edit,
  .flowgraph-svg-node-btns-split .flowgraph-svg-node-btns-remove{
     display: none;
  }
  .flowgraph-svg-node-btns-start .flowgraph-svg-node-btns-addsibling,
  .flowgraph-svg-node-btns-start .flowgraph-svg-node-btns-replace,
  .flowgraph-svg-node-btns-start .flowgraph-svg-node-btns-remove{
     display: none;
  }
</style>