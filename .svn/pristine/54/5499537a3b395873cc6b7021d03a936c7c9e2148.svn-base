<template>
  <Poptip class="extbtns" placement="left" trigger="hover" >
     <Button type="text" style="width:100%;height:100%; font-size: 18px;">
       <Icon type="more"></Icon>
     </Button>
     <div class="extbtns-items" slot="content">
         <Button v-for="(item, index) in items" @click="click(item)" type="text" :key="'items' + index">{{item}}</Button>
       </tr>
     </div>
  </Poptip>
</template>
<script>

export default {
  name:'DataRowOperateBar',
  props:{
    'commands':{
      type:String,
      default:'详情,删除'
    }
  },
  data() {
    return {
    }
  },
  mounted:function(){
  },
  computed:{
     items:function(){
      return this.commands.split(',');
     }
  },
  methods:{
    click:function(name){
      this.$emit('on-command',name);
    }
  }
}

</script>

<style type="text/css">
  .extbtns{

  }
  .extbtns-items{

  }
</style>

