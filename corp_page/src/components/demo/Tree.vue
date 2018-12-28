<template> 
  <div class="page">
    <h2 class="page-title">1. 树组件</h2>
    <table class="layout">
      <tr>
        <td> 
          模板1
        </td>
        <td>模板2</td> 
      </tr>  
      <tr>
        <td class="td"><div class="tv"><TvNode :model="root"></TvNode></div></td>
        <td class="td"><div class="tv"><TvNodeCheck :model="root"></TvNodeCheck></div></td> 
      </tr>  
      <tr>
        <td colspan="2">
          <i-button type="primary" @click="getCheck">获取选择中</i-button> 
          <i-button type="default" @click="setCheck">设置选择中(1,2,3)</i-button>
        </td>
      </tr>
    </table>
  </div>
</template>
<script>
import TvNode from '@/components/tree/TvNode'
import TvNodeCheck from '@/components/tree/TvNodeCheck'
import data from '@/components/demo/data.js'
import treeComponent from '@/components/tree/treeComponent.js'

export default {
  components: {
    TvNode,TvNodeCheck
  },

  data() { 
    return {
      root:{
        title:'根结点',
        children:[]
      },
      tree:null 
    } 
  },

  methods: {
    load:function(){
      var that = this;

      var tree = new treeComponent({
          idProperty:'powerId',
          titleProperty:'powerCaption',
          parentIdProperty:'parentId',
          onLoad:function(){
            tree.options.data = data.tree.data;  
            tree.build();   
            tree.options.isLoad = true;
          },
          onEvent:function(e){
            alert(e.name);
          }
      });
      tree.load(); 

      this.tree = tree;
      this.root = tree.options._root;
  
    },
    getCheck:function(){
      var ids = this.tree.getCheck().join(',');
      alert(ids);
    },
    setCheck:function(){
       this.tree.setCheck([1,2,3]);
    }
  },

  mounted:function () {
    this.load();
  }

}
</script>
<style>
  .page{padding:10px;}
  .page-title{margin-bottom: 10px;}

  .tv{padding: 10px;border:1px solid #dedede;} 
  .layout .td{vertical-align: top;width: 200px;}
  
</style> 