<template> 
  <div :class="classes">
    <Menu theme="light" :width="width+'px'" @on-select="open" :active-name.sync="curFormId" class="procnav-menu wt-scroll">
      <MenuItem :name="item.id" v-for="(item,index) in items">
          {{item.title}}
      </MenuItem>
    </Menu>
  </div>
</template>
<script>

export default {
  name:'menuNavComponent',
  components: {
     
  },
  props:{
    width:{
      type:Number,
      default:210
    },
    curFormId:{
      type:Number,
      default:null
    }
  },
  data() {
    return {
      items:[], 
    }
  },
  mounted(){

  }, 
  computed:{
    classes() {
      return ['procnav'];
    }
  },
  methods:{
    load(callback){
      var that = this;
      that.$http.post('/api/wf/form/list',{}).then((res) => {
        if (res.data.code === 0) { 
          var formList = res.data.data;
          var arr = [];
          for(var i=0;i<formList.length;i++){
            var item = formList[i]; 
            arr.push({id:item.id,title:item.title});
          }
          that.items = arr;
          if(callback){
            callback();
          }
        } else {
          that.$Message.error(res.data.message);
        }
      }).catch((error) => {
          that.$Message.error(error)
      })
    },
    open(id) { 
      var item = this.find(id);
      if(item){ 
        this.$emit('on-open',item); 
      }
    },
    find(id) { 
      for (var i = 0; i < this.items.length; i++) {
        if (this.items[i].id == id) {
          return this.items[i]
        }
      }
    } 
  } 
}

</script>
<style type="text/css">
  /*布局*/  
  .procnav{
    height: 100%;
  }
  .procnav-menu {
     height:100%;
     background-color: white;
     border-right: 0px solid #eeeeee;
     overflow-y:hidden;
  } 
  .procnav-menu:hover{
    overflow-y:auto;
  }
    
  /*菜单*/
  .procnav-menu.ivu-menu-vertical.ivu-menu-light:after {
    background-color: transparent; 
  }

  .procnav-menu.ivu-menu-vertical.ivu-menu-light {
    background-color: transparent;
  }

  .procnav-menu.ivu-menu-vertical .ivu-menu-item,
  .procnav-menu.ivu-menu-vertical .ivu-menu-submenu-title {
    padding: 10px 15px;
    color: #666;
  }

  .procnav-menu.ivu-menu-light.ivu-menu-vertical .ivu-menu-item-active,
  .procnav-menu.ivu-menu-light.ivu-menu-vertical .ivu-menu-item-active:not(.ivu-menu-submenu) {
    color: inherit;
    border-right: 0px solid #eee;
    color: #fff;
    background-color: #20bfee;
  }
</style>
