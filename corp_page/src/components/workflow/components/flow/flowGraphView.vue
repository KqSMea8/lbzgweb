<template>
  <div :class="classes" @click="nodeClick(null)">
    <div class="flowgraphview-body">
     <div class="flowgraphview-svg" :style="flowgraphStyle" >
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
  name:'flowgraphview',
  props:{
    value: {
      type: Array,
      default:()=>{return [];}
    }
  },
  data() {
    var obj = { 
      nodes:[], 
      canvas:{
        layout:'ver',//ver hor
        editable:false,
      }
    }
    return obj;
  },
  mounted:function(){ 
    this.clear();
  },
  computed:{
    classes(){
      return [
          'flowgraphview'
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
      if(flg == 1){
        this.canvas.layout = 'ver';
      }else{
        this.canvas.layout = 'hor';
      }
    },
    nodeClick:function(node){
       
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
  .flowgraphview{
    height: 100%; 
    -webkit-user-select:none;
    -moz-user-select:none;
    -ms-user-select:none;
    user-select:none;
  } 

  .flowgraphview-body{
    height: 100%;
    overflow: hidden;
    overflow: auto;
    background-color: white;
    text-align: center;
    padding: 20px;
  }

  .flowgraphview-svg{
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
  .flowgraphview-svg-node-btns {
    display: inline-block;
    background-color: #e5e5e5;
    height: 58px;border-radius: 39px;
    position: absolute;
    padding: 0px 18px;
    z-index: 100;
  }
  .flowgraphview-svg-node-btns td{
    padding: 4px 10px;white-space:nowrap;
  }
  .flowgraphview-svg-node-btns td:hover{
    cursor: pointer;
  }
  .flowgraphview-svg-node-btns td:hover .ivu-icon{
    color:#4779C5;
  } 
  .flowgraphview-svg-node-btns .ivu-icon{
    font-size: 28px;color:#5990cf;
  }

  .flowgraphview-svg-node-btns-split .flowgraphview-svg-node-btns-addsibling,
  .flowgraphview-svg-node-btns-split .flowgraphview-svg-node-btns-replace,
  .flowgraphview-svg-node-btns-split .flowgraphview-svg-node-btns-edit,
  .flowgraphview-svg-node-btns-split .flowgraphview-svg-node-btns-remove{
     display: none;
  }
  .flowgraphview-svg-node-btns-start .flowgraphview-svg-node-btns-addsibling,
  .flowgraphview-svg-node-btns-start .flowgraphview-svg-node-btns-replace,
  .flowgraphview-svg-node-btns-start .flowgraphview-svg-node-btns-remove{
     display: none;
  }

</style>