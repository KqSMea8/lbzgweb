<template>
  <div :class="classes">
  <table :class="tableClasses" cellpadding="0" cellspacing="0">
    <tr :class="topClasses" v-if="showTop">
      <td ><slot name="top"></slot></td>
    </tr>
    <tr :class="centerClasses">
      <td>
        <div :class="centerContainerClasses"><slot></slot></div>
      </td>
    </tr>
    <tr :class="bottomClasses" v-if="showBottom">
      <td><slot name="bottom"></slot></td>
    </tr>
  </table>
  </div>
</template>
<script>

export default {
  name:'LayoutVer',
  props:{
     
  },
  data() {
    return {
      showTop:true,
      showBottom:true
    }
  },
  mounted:function(){
    this.showTop = this.$slots.top !== undefined;
    this.showBottom = this.$slots.bottom !== undefined;
  },
  computed:{
    classes () {
        return [
          'layout-ver'
        ];
    },
    tableClasses () {
        return [
          'layout-ver-table'
        ];
    },
    topClasses () {
        return [
          'layout-ver-top'
        ];
    },
    centerClasses () {
        return [
          'layout-ver-center'
        ];
    },
    centerContainerClasses(){
      return [
        'layout-ver-center-container',
        'wt-scroll'
      ];
    },
    bottomClasses () {
        return [
          'layout-ver-bottom'
        ];
    }
  },
  methods:{ 
     
  }
}

</script>

<style type="text/css">
  .layout-ver{
    height: 100%;
    width: 100%;
    overflow:hidden;
  }

  .layout-ver-table{ 
    height: 100%;
    width: 100%; 
  }

  .layout-ver-top{
    height: auto;
  }
  .layout-ver-center{
    height: 100%;
    overflow: hidden;
  }
  .layout-ver-center-container{
    height: 100%;
    width: 100%;
    overflow-x: auto;
  }
  .layout-ver-bottom{
    height: auto;
  }

  
</style>

