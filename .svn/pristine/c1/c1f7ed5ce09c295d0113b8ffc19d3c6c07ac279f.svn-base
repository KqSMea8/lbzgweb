<template>
  <Modal v-model="show" title="选择部门" :closable="false" :mask-closable="false">
    <div class="select-dept">
       <DeptNodeCheck :model="root"></DeptNodeCheck>
    </div>
    <div slot="footer">
        <Button  type="text" @click="close" style="margin-left: 8px">取消</Button>
        <Button  type="primary" @click="getCheck">确定</Button>
    </div>
  </Modal>
</template>

<script>
import DeptNodeCheck from '@/components/commons/DeptNodeCheck'
import treeComponent from '@/components/tree/treeComponent.js'
import Loading from '@/components/loading';

export default {
  name: 'SelectDept',
  components: {
    DeptNodeCheck,
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
      loading: 1
    };
  },
  mounted(){
    let that = this;
    let tree = new treeComponent({
              idProperty:'deptId',
              titleProperty:'deptName',
              parentIdProperty:'parentId',
              root:{title:'公司',data:{deptName:'公司',deptId:1,parentId:null}},
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
    open(){
      this.show = true;
      this.load();

    },
    load(){
        this.loading = 1;
        this.$http.post('/api/contacts/dept/list', {}).then((res) => {
          if (res.data.code === 0) {
            this.loading = 0;
            this.tree.options.data = res.data.data;
            this.tree.options._root.title = res.data.data[0].deptName;
            this.tree.options._root.data = res.data.data[0];
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
    getCheck(){
        let depts = this.tree.getCheckData().map((item) => {
          let dept = {deptId:item.deptId,deptName:item.deptName};
          return dept;
        });
        this.$emit('on-check',depts);
    },
    setCheck(ids){
        //ids 数组 [1,2,3]
        let deptIds = ids.map((item) => {
          return parseInt(item);
        });
        this.tree.setCheck(deptIds);
    },
    selDept(data){
      this.$emit('on-select',data);
    },
    close(){
      this.show = false;
      this.$emit('on-close');
    }
  }
}
</script>

<style>
   .select-dept{padding: 10px;height: 100%;}
</style>

