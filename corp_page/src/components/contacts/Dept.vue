<template>
  <div class="tv-div">
     <DeptTreeNode :model="root"></DeptTreeNode>
     <div v-bind:class="{'contextmenu':1,'hidden':!contextmenu.show}" :style="menustyle">
            <ul v-if="currentDept!=null && currentDept.id==0" clas="contextmenu-ul">
                <li>
                     <Button type="text" @click="add" class="contextmenu-item" :disabled="!installed">添加子部门</Button>
                </li>
            </ul>
            <ul v-else clas="contextmenu-ul">
                <li>
                     <Button type="text" @click="add" class="contextmenu-item" :disabled="!installed">添加子部门</Button>
                </li>
                <li>
                     <Button type="text" @click="update" class="contextmenu-item" :disabled="!installed">修改部门</Button>
                </li>
                <li>
                     <Button type="text" @click="deleteDept" class="contextmenu-item" :disabled="!installed">删除</Button>
                </li>
            </ul>
            <EditDept ref="editDept" @on-submit="addDept" @on-submit-edit="updateDept"></EditDept>
      </div>
  </div>
</template>
<script>
import DeptTreeNode from '@/components/contacts/DeptTreeNode'
import treeComponent from '@/components/tree/treeComponent.js'
import EditDept from '@/components/contacts/EditDept'

export default {
  components: {
    DeptTreeNode,
    EditDept
  },
  props: {
    installed:{
      type:Boolean,
      default:true
     }
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
        title:'公司',
        children:[]
      },
      tree:null,
      editDept:null,
      data:[]
    };
  },
  mounted(){
    var that = this;
    var tree = new treeComponent({
              idProperty:'deptId',
              titleProperty:'deptName',
              parentIdProperty:'parentId',
              root:{title:'公司',data:{deptName:'公司',deptId:1,parentId:null}},
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
                 that.selDept(e.sender.data);
              }
          });
              this.tree= tree;
              this.root= this.tree.options._root;

            // 鼠标离开快捷菜单消失
            var hiddenContextMenuTimer = null;
            $('.contextmenu').hover(function(){
                clearTimeout(hiddenContextMenuTimer);
            },function(){
                hiddenContextMenuTimer = setTimeout(function(){
                    that.contextmenu.show = false;
                },300);
            });
  },
  computed:{
    style() {
      if(this.model && this.model.level){
        return 'padding-left:' + this.model.level*14 + 'px';
      }
      return "";
    },
    menustyle() {
       return 'top:'+this.contextmenu.top+'px;left:'+this.contextmenu.left+'px;z-index:10;';
    }
  },
  methods:{
    load(data){
      if( typeof(data) != 'undefined' && data.length > 0){
          this.tree.options.data = data;
          this.tree.options._root.title = data[0].deptName;
          this.tree.load();
      }
    },
    selDept(currentDept){
      this.contextmenu.show = false;
      this.currentDept = currentDept;
      this.$emit("on-select",currentDept);
    },
    setSelect(){
      this.tree.setSelect([]);
    },
    add(){
       this.contextmenu.show = false;
       this.$refs.editDept.open(0,this.currentDept.deptName);
    },
    update(){
       this.contextmenu.show = false;
       this.$refs.editDept.open(1,this.currentDept.deptName);
    },
    addDept(deptName){
      this.editDept = {
         deptId:this.currentDept.deptId,
         deptName:deptName
       }
       this.$emit("on-menu-add",this.editDept);
    },
    updateDept(deptName){
       this.editDept = {
         deptId:this.currentDept.deptId,
         deptName:deptName
       }
       this.$emit("on-menu-update",this.editDept);
    },
    deleteDept(){
        this.contextmenu.show = false;
        this.$emit("on-menu-delete",this.currentDept.deptId);
    }
  }
}
</script>

<style>
  .tv-div{}
  .contextmenu{
    position: fixed;
    background-color: white;
    border:1px solid #efefef;
  }
  .contextmenu-item{width: 100%;text-align: left;}
  .contextmenu-item:hover{background-color:#eaf2ff }
  .hidden{display: none;}

</style>

