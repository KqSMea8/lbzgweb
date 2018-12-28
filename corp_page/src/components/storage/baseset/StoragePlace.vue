<template>
  <div class="page storageplace"> 
    <div class="page-title">
      <a @click="goPage('/storage/baseset')">仓库基础设置</a> -&gt;货位信息
    </div>
    <Card class="searchbox">
      <table>
        <tr>
          <td>仓库&nbsp;</td>
          <td>
            <Select v-model="storageId" style="width:150px" placeholder="仓库" @on-change="switchStorage"> 
              <Option :value="''">请选择仓库</Option>
              <Option v-for="item in storages" :value="item.storageId" :key="item.storageId">{{ item.name }}</Option>
            </Select>
          </td>
        </tr>
      </table>
    </Card>
    <div class="treeview" v-if="storageId">
      <Tree :data="[root]" :load-data="loadData" :render="renderContent"></Tree>
    </div>
    <StoragePlaceEdit ref="placeEdit"></StoragePlaceEdit>
  </div>
</template>
<script> 　
  import StoragePlaceEdit from './StoragePlaceEdit';
  export default {
    components: { 
     　StoragePlaceEdit
    },
    data() { 
      return { 
        storageId:'', 
        root:{
          name:'货位',
          expand:false,
          loading:false,
          children:[],
          placeId:'-1',
        },
        storages:[{
          storageId:'s0001',
          name:'仓库001'
        }],
      }
    },
    mounted: function () {  
      this.loadStorage();
    },
    computed: {},
    methods: { 
      loadStorage(){
        this.loading = 1;
        this.$http.post("/api/storage/list", {pageSize:100}).then((res) => { 
          this.loading = 0;
          if (res.data.code === 0) {
            this.storages = res.data.data.rows;
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error('加载出错！');
        });
      },
      loadData(item,callback){
        var condition = {
          storageId:this.storageId,
          parentPlaceId:item.placeId
        };
        this.$http.post("/api/storage/place/list", condition).then((res) => { 
          if (res.data.code === 0) {
            var data = res.data.data;
            data.map((da)=>{
              da.loading = false;
              da.children = [];
            });
            callback(data);
          } else {
             
          }
        }).catch((error) => {
           
        });
      },
      renderContent(h, { root, node, data }){
        return  h('div', { class:'treeview-row'},[
            h('span',{class:'tvnode-placeid'},'[' + data.placeId + '] '),
            h('span',data.name),
            h('div',{class:'treview-row-btns'},[
              h('Icon',{
                props:{type:'plus'},
                class:'btn btn-add',
                title:'添加',
                on:{
                  click:()=>{
                    this.$refs.placeEdit.open({
                      ok:()=>{this.reloadChildren(data);},
                      item:{
                          storageId:this.storageId,
                          name:data.name,
                          parentPlaceId:data.placeId
                      }
                    }); 
                  }
                }
              }),
              h('Icon',{
                props:{type:'edit'},
                class:'btn btn-edit ' + (data == this.root?'btn-hidden':''),
                title:'编辑',
                on:{
                  click:()=>{
                    this.$refs.placeEdit.open({
                      ok:()=>{  
                        var parent = this.getParentData(data,this.root);
                        this.reloadChildren(parent);
                      },
                      item:data
                    });
                  }
                }
              }),
              h('Icon',{
                props:{type:'close'},
                class:'btn btn-remove '+(data == this.root?'btn-hidden':''),
                title:'删除',
                on:{
                  click:()=>{
                    if(data.children &&　data.children.length){
                      this.$Message.error('该货位下有子货位，不能删除')
                      return;
                    }
                    this.$Modal.confirm({
                      title: '确认删除',
                      content: '<p>确定删除该条数据码？</p>',
                      onOk: () => { 
                        this.deleteNode(data);
                      }
                    });
                  }
                }
              }),
            ])
          ]); 
      },
      deleteNode(data){
        this.loading = 1;
        this.$http.post('/api/storage/place/delete?storageId='+data.storageId+'&placeId='+data.placeId, {}).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.$Message.success("删除成功"); 
            var parent = this.getParentData(data,this.root);
            this.reloadChildren(parent);
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error(error.toString())
        });
      },
      reloadChildren(node){
        node.loading = true; 
        this.loadData(node,(data)=>{
          node.loading = false;
          node.children = data;
        })
      }, 
      getParentData(data,node){ 
        if(node.children.indexOf(data) >=0){
          return node;
        }else{
          for(var i = 0;i<node.children.length;i++){
            var ret = this.getParentData(data,node.children[i]);
            if(ret){
              return ret;
            }
          }
          return null;
        } 
      },
      switchStorage(){
        this.root.expand = false;
        this.root.loading = false;
        this.root.children = [];
      },
      goPage(url){
        this.$router.push(url);
      }
    }
  }

</script>

<style type="text/css"> 
  .storageplace{

  }

  .storageplace .searchbox{
    margin-top: 10px;
  }

  .storageplace .treeview {
    width: 50%;
  }
  .storageplace .treeview .ivu-tree-title{
    font-size: 14px;
  }

  .storageplace .treeview .treeview-row{
    display: inline-block;
    height: 30px;line-height: 30px;
    width: 100%; cursor: default;
    padding: 0px 10px;
    padding-right: 80px;
    position: relative;font-size: 14px;
  }
  .storageplace .treeview .treeview-row:hover{
    background-color: #e8f8fd;
  }
  .storageplace .treeview .treview-row-btns{
    position: absolute;
    right: 0px;font-size: 14px;
    top:0px;bottom: 0px;
  }
  .storageplace .treeview .treview-row-btns .btn{
    margin-right: 8px;display: inline-block;
    border: 1px solid #2db7f5;border-radius: 3px;padding: 2px;
    background-color: #2db7f5;color: white;cursor: pointer;
  }
  .storageplace .treeview .treview-row-btns .btn-add{
    background-color: #2db7f5;
    border-color:#2db7f5;
  }
  .storageplace .treeview .treview-row-btns .btn-remove{
    background-color: #ed3f14;
    border-color:#ed3f14;
  } 
  .storageplace .treeview .treview-row-btns .btn-hidden{
    display: none;
  }

  .storageplace .tvnode-placeid{
    color:#ccc;
  }

</style>
