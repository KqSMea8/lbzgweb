<template>
  <div>
    <table>
      <tr><td> 
        <TvNode :model="root"></TvNode>
      </td></tr>
      <tr><td></td></tr>
      <tr><td></td></tr>
    </table>
  </div>
</template>
<script>
import TvNode from '@/components/tree/TvNode' 
import tc from '@/components/tree/treeComponent.js'

var treeComponent = tc.treeComponent;
var data = [
      {"powerId":1,"powerKey":"","powerType":"2","powerIcon":"","powerCaption":"工作台","parentId":0},
      {"powerId":2,"powerKey":"","powerType":"","powerIcon":"","powerCaption":"客户管理","parentId":0},
      {"powerId":3,"powerKey":"","powerType":"","powerIcon":"","powerCaption":"我的客户","parentId":2},
      {"powerId":4,"powerKey":"","powerType":"","powerIcon":"","powerCaption":"客户转移","parentId":2},
      {"powerId":5,"powerKey":"","powerType":"","powerIcon":"","powerCaption":"产品管理","parentId":0},
      {"powerId":6,"powerKey":"","powerType":"","powerIcon":"","powerCaption":"备选库","parentId":5}
    ];

export default {
  components: {
    TvNode
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
            tree.options.data = data;  
            tree.build();   
            tree.options.isLoad = true;
          },
          onEvent:function(e){
            alert(e.name);
          }
      });
      tree.load();

      alert(tree.options._root); 

      this.tree = tree;
      this.root = tree.options._root; 

    }
  },

  mounted:function () {
    this.load();
  }

}
</script>
<style>
  .tv{padding: 10px;border:1px solid #dedede;}/*组件*/
</style> 