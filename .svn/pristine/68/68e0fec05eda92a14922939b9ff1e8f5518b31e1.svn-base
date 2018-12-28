<template>
  <div :class="classes">
  <table :class="tableClasses" cellpadding="0" cellspacing="0">
    <tr>
      <td :class="leftClasses" v-if="showLeft">
        <slot name="left"></slot>
      </td>
      <td :class="centerClasses">
        <div :class="centerContainerClasses"><slot></slot></div>
      </td>
      <td :class="rightClasses" v-if="showRight">
        <slot name="right"></slot>
      </td>
    </tr>
  </table>
  </div>
</template>
<script>

export default {
  name:'LayoutHor',
  props:{
     fix:{
        type: Boolean,
        default: false
     },
     left:{
        type:String,
        default: null
     },
     center:{
        type:String,
        default: null
     },
     right:{
        type:String,
        default: null
     }
  },
  data() {
    return {
      showLeft:true,
      showRight:true
    }
  },
  mounted:function(){
    this.showLeft = this.$slots.left !== undefined;
    this.showRight = this.$slots.right !== undefined;
  },
  computed:{
    classes () {
        return [
          'layout-hor',
          {
            'layout-hor-fix':this.fix
          }
        ];
    },
    tableClasses () {
        return [
          'layout-hor-table',
          {
            'layout-hor-table-fix':this.fix
          }
        ];
    },
    leftClasses () {
        return [
          'layout-hor-left',
          {
            'layout-hor-left-fix':this.fix
          },
          this.left
        ];
    },
    centerClasses () {
        return [
          'layout-hor-center',
          {
            'layout-hor-center-fix':this.fix
          },
          this.center
        ];
    },
    centerContainerClasses(){
      return [
        'layout-hor-center-container',
        'wt-scroll',
        {
          'layout-hor-center-container-fix':this.fix
        },
      ];
    },
    rightClasses () {
        return [
          'layout-hor-right',
          {
            'layout-hor-right-fix':this.fix
          },
          this.center
        ];
    }
  },
  methods:{ 
     
  }
}

</script>

<style type="text/css">
  .layout-hor{
    height: 100%;
    width: 100%;
    overflow:hidden;
  }

  .layout-hor-table{ 
    height: 100%;
    width: 100%;  
    table-layout:inherit;
  }

  .layout-hor-left{
    width:auto;
    vertical-align: top;
  }
  .layout-hor-center{
    width: 100%;
    vertical-align: top;
    overflow: hidden;
  }
  .layout-hor-center-container{
    height: 100%;
    width: 100%;
    overflow-x: auto;
  }
  .layout-hor-right{
    width: auto;
    vertical-align: top;
  }

  .layout-hor-table-fix{
    table-layout: fixed;
  }

  .layout-hor-left-fix{
    width: auto;
  }

  .layout-hor-center-fix{
    width: auto;
  }

  .layout-hor-right-fix{
    width: auto;
  }
  
</style>

