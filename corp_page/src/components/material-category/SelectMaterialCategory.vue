<template>
  <Modal v-model="show" title="选择物料种类" :closable="false" :mask-closable="false">
    <div class="selectmaterialcatagory">
      <TvNodeCheck v-if="multi" :model="root"></TvNodeCheck>
      <TvNode v-else :model="root"></TvNode>
    </div>
    <div slot="footer">
      <Button  type="text" @click="cancel" style="margin-left: 8px">取消</Button>
      <Button  type="primary" @click="ok">确定</Button>
    </div>
  </Modal>
</template>

<script>
import TvNodeCheck from '@/components/tree/TvNodeCheck'
import TvNode from '@/components/tree/TvNode'
import treeComponent from '@/components/tree/treeComponent.js'
import Loading from '@/components/loading';

export default {
  name: 'SelectDept',
  components: {
    TvNodeCheck,
    TvNode,
    Loading
  },
  data() {
    return {
      root:{
        title:'',
        children:[]
      },
      tree:null,
      show:false,
      cur:null,
      loading: 1
    };
  },
  props:{
    multi:{
      type:Boolean,
      default:false
    }
  },
  mounted(){
    let that = this;
    let tree = new treeComponent({
      idProperty:'id',
      titleProperty:'title',
      parentIdProperty:'parentId',
      root:{title:'物料',data:{title:'物料',id:0,parentId:null}},
      onLoad(){
        if(!tree.options.isLoad){
            tree.build();
            tree.openLevel(0);
        }else{
            tree.buildRestoreStatus();
        }
        tree.options.isLoad = true;
      },
      onSelect(e){
        that.selDept(e.sender.data);
      }
    });
    this.tree= tree;
    this.root= this.tree.options._root;
  },
  computed:{
  },
  methods:{
    open(options){
      this.show = true;
      this.load();
      this.options = options;
    },
    load(){
        this.loading = 1;
        this.$http.post('/api/material/category/list', {}).then((res) => {
          if (res.data.code === 0) {
            this.loading = 0;
            this.tree.options.data = res.data.data; 
            this.tree.load();
          } else {
            this.loading = 0;
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error(error.toString())
        });
    },
    ok(){
      this.show = false;
      if(this.options && this.options.ok){
        this.options.ok();
      }
      this.$emit('ok');
    },
    cancel(){
      this.show = false;
      this.$emit('cancel');
    },  
    selDept(data){
      this.cur = {id:data.id,title:data.title}; 
    },
    // 外部调用 =====>
    getCheck(){
      var items = this.tree.getCheckData().map((item) => {
      var dept = {id:item.id,title:item.title};
        return dept;
      });
      return items;
    },
    setCheck(ids){
      //ids 数组 [1,2,3]
      let deptIds = ids.map((item) => {
        return parseInt(item);
      });
      this.tree.setCheck(deptIds);
    }
  }
}
</script>

<style>
  ul{list-style: none;}
  .selectmaterialcatagory{padding: 10px;height: 100%;}
</style>