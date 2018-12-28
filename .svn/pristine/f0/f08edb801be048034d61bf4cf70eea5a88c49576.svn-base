<template>
	<div class="wf-selgroup" >
        <!-- <Tree :data="groupOptions"  @on-select-change="checkedGroup"></Tree> -->  
      <ul>
        <li v-for="item in roles" @click="selectNode(item)">
          {{item.roleName}}
        </li>
      </ul>
  </div>
</template>

<script>

export default {
  name:'selectGroup',
  data() {
    return { 
      roles:[]
    }
  },
  mounted(){
    this.load();
  },
  watch:{  
    
  },
  methods:{
    load(){
      this.$http.post('api/role/list',{pageSize:200}).then((res) => {
        if (res.data.code === 0) {
          this.roles = res.data.data.rows;
        } else {
          this.$Message.error(res.data.message);
        }
      }).catch((error) => {
        this.$Message.error(error.toString())
      })
    },
    selectNode(node){
      this.$emit("on-select",{ 
        type:'role',
        key:node.roleId,
        title:node.roleName
      });
    }
  }
}

</script>
<style type="text/css">
  .wf-selgroup{
    
  }
  .wf-selgroup ul li{
    cursor: pointer;
    padding:10px;
    margin-top: 4px;
  }
  .wf-selgroup ul li:hover{
    background-color: #d5e8fc;
  }

</style>