<template>
  <Modal v-model="display" title="选择货位" :closable="false" :mask-closable="false" :transfer="transfer">
    <div class="selplace">
      <div class="treeview">
        <Tree :data="[root]" :load-data="loadData" :render="renderContent"></Tree>
      </div>
    </div>
    <div slot="footer" class="footer">
      <Button type="primary" @click="ok" style="width:100px;">确定</Button>
      <Button @click="cancel" style="width:100px;">取消</Button>
    </div>
  </Modal>
</template>

<script> 
import Loading from '@/components/loading';

export default { 
  components: { 
    Loading
  },
  props:{
    transfer:{
      type:Boolean,
      default:true
    } 
  },
  data() {
    return { 
      display:false,
      loading: 1,
      storageId:'', 
      root:{
        name:'货位',
        expand:false,
        loading:false,
        children:[],
        placeId:'',
      }, 
      curData:{
        placeId:'',
        placeName:''
      },
      options:{}
    };
  },
  mounted(){
     
  },
  computed:{
  },
  methods:{
    loadData(item,callback){
      var condition = {
        storageId:this.storageId,
        parentPlaceId:item.placeId
      };
      if(condition.storageId == '' ||　condition.storageId　== null){
        condition.storageId = '--';
      }
      if(condition.parentPlaceId == ''){
        condition.parentPlaceId = '-1';
      }
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
      var checked = data.placeId == this.curData.placeId;

      return  h('div', { 
          class:'treeview-row ' + (checked?'checked':''),
          on:{
            click:()=>{
              this.curData.placeId = data.placeId; 
              this.curData.placeName = data.placeId == ''?'':data.name;
            }
          }
        },[
          h('span',{class:'tvnode-placeid'},'[' + data.placeId + '] '),
          h('span',data.name),
          h('div',{class:'treview-row-btns'},[
            h('Icon',{
              props:{type:'android-checkbox'},
              class:'icon-check',
            }),
          ])
        ]); 
    },
    switchStorage(storageId){
      this.storageId = storageId;
      this.root.expand = false;
      this.root.loading = false;
      this.root.children = [];
    },
    // 对外方法
    show(options){

      Object.assign(this.options,{
        storageId:'',
        ok:(data)=>{},
        cancel:()=>{}
      },options);

      if(this.storageId != this.options.storageId){ 
        this.switchStorage(this.options.storageId);
      }

      this.display = true;
    },
    ok(){
      this.display = false;
      this.options.ok(this.curData);
    },
    cancel(){
      this.display = false;
    }
  }
}
</script>

<style>
  .selplace{

  }

  .selplace .treeview .ivu-tree-title{
    font-size: 14px;
  }

  .selplace .treeview .treeview-row{
    display: inline-block;
    height: 30px;line-height: 30px;
    width: 100%; cursor: default;
    padding: 0px 10px;
    padding-right: 80px;
    position: relative;font-size: 14px;
  }
  .selplace .treeview .treeview-row:hover{
    background-color: #e8f8fd;
  }
  .selplace .treeview .treview-row-btns{
    position: absolute;
    right: 0px;font-size: 14px;
    top:0px;bottom: 0px;
  }
  
  .selplace .treeview-row .icon-check{
    color: white;
    font-size: 16px;
    margin-right: 10px;
  }
  .selplace .treeview-row.checked{
    background-color: #e8f8fd;
  }
  .selplace .treeview-row.checked .icon-check{
    color: #eb5954;
  }

  .selplace .tvnode-placeid{
    color:#ccc;
  }

  .selplace .footer{
    text-align: center;
  }

</style>

