<template>
  <div class="page">
    <h2 class="page-title">2.部门树组件</h2>
    <div class="tv">
       <DeptTreeNode :model="root"></DeptTreeNode>
       <div v-bind:class="{'contextmenu':1,'hidden':!contextmenu.show}" :style="menustyle">
              <ul v-if="currentDept!=null && currentDept.id==0" clas="contextmenu-ul">
                  <li>
                       <i-button type="text" @click="addDept" class="contextmenu-item">添加子部门</i-button>
                  </li>
              </ul>
              <ul v-else clas="contextmenu-ul">
                  <li>
                       <i-button type="text" @click="addDept" class="contextmenu-item">添加子部门</i-button>
                  </li>
                  <li>
                       <i-button type="text" @click="updateDept" class="contextmenu-item">修改部门</i-button>
                  </li>
                  <li>
                       <i-button type="text" @click="deleteDept" class="contextmenu-item">删除</i-button>
                  </li>
              </ul>
         </div>
    </div>
</div>
</template>
<script>
import DeptTreeNode from '@/components/contacts/DeptTreeNode'
import treeComponent from '@/components/tree/treeComponent.js'

export default {
  components: {
    DeptTreeNode
  },
  data() { 
    return {
      contextmenu:{
         top:0,
         left:0,
         show:false
      },
      currentDept:null,
      root:{
        title:'根结点',
        children:[]
      },
      tree:null,
      data: []
    };
  },
  mounted:function(){ 
        var that = this;
        var tree = new treeComponent({
              idProperty:'deptId',
              titleProperty:'deptName',
              parentIdProperty:'parentId',
              root:{title:'部门',data:{deptName:'部门',deptId:0,parentId:null}},
              onLoad:function(){

                  if(!tree.options.isLoad){ 
                        tree.build();
                        tree.openLevel(0);
                    }else{ 
                        tree.buildRestoreStatus(); 
                    }
                    tree.options.isLoad = true;
                 
              },
              onEvent:function(e){
                // 弹出上下文菜单 
                  if(e.name == 'contextmenu'){
                      var offset = $(e.$e.srcElement).offset();

                      var winHeight = $(window).height();

                      var offsetY = offset.top + $('.contextmenu').height() - winHeight;

                      if(offsetY>0){
                          offset.top = offset.top - offsetY;
                      }

                      if(offset.top < 0){
                          offset.top = 0;
                      }

                      that.contextmenu.show=true;
                      that.contextmenu.top = offset.top;
                      that.contextmenu.left = offset.left;
                      that.contextmenu.show = true;
                      
                      that.currentDept = e.sender.data;

                      tree.setSelect([that.currentDept.deptId]);

                      e.handled=true;
                  }
              },
              onSelect:function(e){
                  that.contextmenu.show = false;
                  that.currentDept = e.sender.data;
              }
      });
              this.tree= tree;
              this.root= tree.options._root;  

          // 鼠标离开快捷菜单消失
            var hiddenContextMenuTimer = null;
            $('.contextmenu').hover(function(){
                clearTimeout(hiddenContextMenuTimer);
            },function(){
                hiddenContextMenuTimer = setTimeout(function(){
                    that.contextmenu.show = false;
                },300);
            }); 

            this.load(this.data);  
  },
  computed:{
    style:function () {
      if(this.model && this.model.level){
        return 'padding-left:' + this.model.level*14 + 'px';
      }
      return "";
    },
    menustyle:function() {
       return 'top:'+this.contextmenu.top+'px;left:'+this.contextmenu.left+'px';
    }
  },
  methods:{
    load:function(data){
          this.$http.post('/api/contacts/dept/list', {}).then((res) => {
            if (res.data.code === 0) {
                data = res.data.data;
                this.tree.options.data = data;
                this.tree.load();
            } else {
              this.$Message.error(res.data.message)
            }
          }).catch((error) => {
            this.$Message.error(error.toString())
          });         
      },
        addDept:function(){
          this.contextmenu.show = false;
          alert("添加子部门页面");
        },
        updateDept:function(){
            this.contextmenu.show = false;
            alert("修改部门名称页面");
        },
        deleteDept:function(){
            this.contextmenu.show = false;

            var curDeptId = this.currentDept.deptId;
            var node = this.tree.first(function(node){
                return node.data.deptId == curDeptId;
            })
            if(node){
                console.log(node);
                alert(node.data.deptName);
                /*var parent = node.parent;
                parent.children.remove(node);
                this.tree.options.data.remove(node.data);
                this.tree.buildRestoreStatus(parent);*/
            }
        }

  }
}

</script>

<style>
  .page{padding:10px;}
  .page-title{margin-bottom: 10px;}
  .tv{padding: 10px;border:1px solid #dedede;width:300px;}/*组件*/
  .tv .dept-tree-node .tv-node{padding: 2px 0px;cursor: default;}
  .tv .dept-tree-node .tv-node:hover{background-color: #eaf2ff;border-right: 0;color: #39f;}
  .tv .dept-tree-node .tv-node.select{background-color: #C4DDF9;border-right: 0;color: #39f;}
  
  .tv .tv-node-row{width: 100%;line-height: 16px;}
  .tv .tv-node-row td{margin: 0px;padding: 0px;vertical-align: middle;}
  .tv .tv-node-row-arrow{width: 14px;}
  .tv .tv-node-row-check{width: 16px;text-align: center;}

  .tv .tv-children{}
  .tv .tv-children li{padding: 2px;}
  .tv .tv-title{}

  .tv .tv-arrow{cursor: pointer;background-color:transparent;width: 14px;display: inline-block;text-align: center;}
  .tv .tv-arrow:hover{color:#39f}
  .tv .tv-arrow.open .arrow-close,
  .tv .tv-arrow.open .arrow-empty {display: none}        
  .tv .tv-arrow.close .arrow-open,
  .tv .tv-arrow.close .arrow-empty {display: none}
  .tv .tv-arrow.empty .arrow-open,
  .tv .tv-arrow.empty .arrow-close{display: none}

  .tv .tv-checkbox{font-size: 14px;cursor: pointer;}
  .tv .tv-checkbox.tv-checkbox-check{color:#39f;font-weight: bold;}

  .tv .tv-hidden{display: none;}
  .tv .tv-node:hover .tv-toolbar{display: inline-block;}
  .tv .tv-toolbar .ivu-btn{padding: 0px;} 

  .tv .contextmenu{position: fixed;;background-color: white;border:1px solid #efefef;}
  .tv .contextmenu-item{width: 100%;text-align: left;}
  .tv .contextmenu-item:hover{background-color:#eaf2ff }
  .tv .hidden{display: none;}

</style>

