<template>  
<ul class="dept-tree-node">
  <li>
      <div v-bind:class="{'tv-node':1,'select':model.select}" :style="style" @click="model.doEvent('select',$event)">

          <table class="tv-node-row">
            <tr>
                <td class="tv-node-row-arrow">
                    <span v-bind:class="{'tv-arrow':1,'open':model.open,'close':!model.open,'empty':model.children.length==0}" @click="model.doEvent('fold',$event)">
                    <Icon type="arrow-right-b" class="arrow-close"></Icon>
                    <Icon type="arrow-down-b" class="arrow-open"></Icon>
                    </span>
                </td>
                <td :title="model.tooltip" >
                    {{model.title}} 
                </td>
                <td class="tv-node-row-ext" @click="model.doEvent('contextmenu',$event)">
                    <div class="btns">                        
                        <Icon type="more"></Icon>
                    </div>
                </td>
            </tr>
        </table>
      </div>
      <ul v-bind:class="{'tv-hidden':!model.open}">
          <li v-for="node in model.children">
              <DeptTreeNode :model="node"></DeptTreeNode>
          </li>
      </ul>
  </li>
</ul> 
</template>

<script>

export default {
  name:'DeptTreeNode',
  components: {
  },
  props: {
    model: {
      type:[Object],
      default:{}
    }
  },
  data() { 
    return {};
  },
  mount:function(){ 
  },
  computed:{
    style:function () {
      if(this.model && this.model.level){
        return 'padding-left:' + this.model.level*14 + 'px';
      }
      return "";
    }
  }
}
</script>

<style>
  /*组件*/
  .dept-tree-node .tv-node{padding: 3px 0px;cursor: default;}
  .dept-tree-node .tv-node:hover{background-color: #20bfee;color: #20bfee;border-right:2px solid #20bfee;}
  .dept-tree-node .tv-node.select{background-color: #20bfee;color: #20bfee;border-right:2px solid #20bfee;}
  
  .dept-tree-node .tv-node-row{width: 100%;line-height: 26px; padding: 0 15px;}
  .dept-tree-node .tv-node-row td{margin: 0px;padding: 0px;vertical-align: middle;}
  .dept-tree-node .tv-node-row-arrow{width: 14px;color: #666;}
  .dept-tree-node .tv-node-row-check{width: 16px;text-align: center;}

  .dept-tree-node .tv-children{}
  .dept-tree-node .tv-children li{padding: 2px;}
  .dept-tree-node .tv-title{}

  .dept-tree-node .tv-arrow{cursor: pointer;background-color:transparent;width: 14px;display: inline-block;text-align: center;}
  .dept-tree-node .tv-arrow:hover{color:#20bfee}
  .dept-tree-node .tv-arrow.open .arrow-close,
  .dept-tree-node .tv-arrow.open .arrow-empty {display: none}        
  .dept-tree-node .tv-arrow.close .arrow-open,
  .dept-tree-node .tv-arrow.close .arrow-empty {display: none}
  .dept-tree-node .tv-arrow.empty .arrow-open,
  .dept-tree-node .tv-arrow.empty .arrow-close{display: none}

  .dept-tree-node .tv-checkbox{font-size: 14px;cursor: pointer;}
  .dept-tree-node .tv-checkbox.tv-checkbox-check{color:#20bfee;font-weight: bold;}

  .dept-tree-node .tv-hidden{display: none;}
  .dept-tree-node .tv-node:hover .tv-toolbar{display: inline-block;}
  .dept-tree-node .tv-toolbar .ivu-btn{padding: 0px;} 

  /*扩展菜单*/
  .dept-tree-node .tv-node-row-ext{width: 20px;}
  .dept-tree-node .tv-node-row-ext .btns{visibility:hidden;cursor: pointer;font-size: 14px;} 
  .dept-tree-node .tv-node-row-ext .btns:hover{color:#20bfee;}
  .dept-tree-node .tv-node:hover .tv-node-row-ext .btns,
  .dept-tree-node .tv-node.select .tv-node-row-ext .btns{visibility:inherit;}
</style>

