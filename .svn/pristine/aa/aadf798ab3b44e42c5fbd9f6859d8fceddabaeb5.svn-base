<template>
  <!--<Poptip class="extbtns" placement="left" trigger="hover" >
&lt;!&ndash;     <Button type="text" style="width:100%;height:100%; font-size: 18px;">
       <Icon type="more"></Icon>
     </Button>&ndash;&gt;
     <div class="extbtns-items" slot="content">

     </div>
  </Poptip>-->
    <div slot="content">
        <Button v-for="(item, index) in items" @click="click(item)"  :class="{sss:item.sss}"  type="text" :key="'items' + index">{{item}}</Button>
        <!--</tr>-->
    </div>

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
  .customerlist .sss{
      color: red;
  }
</style>

