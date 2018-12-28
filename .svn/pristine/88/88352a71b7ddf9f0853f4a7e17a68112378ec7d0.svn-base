<template> 
  <div class="editable-table"> 
    <table class="editable-table-tools" cellspacing="0" cellpadding="0" v-if="editable">
      <tr>
        <td>
          <Button icon="plus" @click="add" title="新增行"></Button>
        </td>
        <td>&nbsp;</td>
        <td>
          <Button icon="android-remove" @click="remove" title="删除行"></Button>
        </td>
      </tr>
    </table>
    <div :class="{'editable-table-container':true,'editable':editable}"> 
      <slot></slot>
    </div>
  </div> 
</template>
<script>
  export default {
    components: { 
    },
    props:{
      editable:{
        type:Boolean,
        default:false
      }
    },
    data() { 
      return {
        curIndex:0,
      }
    },
    mounted: function () {
      
    },
    computed: {},
    methods: { 
      load() {

      },
      add(){
        this.$emit('add');
      },
      remove(){
        this.$emit('remove');
      }
    }
  }

</script>

<style type="text/css"> 
  .editable-table {
    
  }
  .editable-table-tools{

  }
  .editable-table-container{
    margin-top: 4px;
    overflow-x:auto;
  } 
  .editable-table-container table{ 
    border-collapse:collapse;
    border: 1px solid #dedede;  
  }
  .editable-table-container table th{
    background: #efefef;
    white-space:nowrap;
    padding: 0px 10px;
  }
  .editable-table-container table td,
  .editable-table-container table th{
    height: 40px;
    border: 1px solid #dedede;
    text-align: center;
    padding: 0px 10px;
    white-space: nowrap;
  }
  /*可编辑组件样式重写*/
  .editable-table-container .ivu-input,
  .editable-table-container .ivu-input-number,
  .editable-table-container .ivu-select,
  .editable-table-container .ivu-select-selection{
     
  }
  .editable-table-container .ivu-input-number-input{
    padding-right: 24px;
    text-align: right;
  }
  /*列的样式*/
  .editable-table-container th.col-xh{
    width: 40px;
  }
  .editable-table-container tbody .col-xh{
    text-align: center; cursor: default;
  }
  .editable-table-container .col-xh.cur{
    background: #e8f8fd;
    color:#20bfee; 
    border-right: 2px solid #20bfee;
    cursor: default;font-weight: bold;;
  }
  .editable-table-container th.col-date > span{
    width: 110px;display: inline-block;
  }
  .editable-table-container th.col-remark > span{
    width: 80px;display: inline-block;
  }

  .editable-table-container th.col-price > span{
    width: 40px;display: inline-block; 
  }
  .editable-table-container th.col-quantity > span{
    width: 40px;display: inline-block;
  }
  .editable-table-container th.col-amount > span{
    width: 40px;display: inline-block;
  } 
  .editable-table-container .col-price{
    background-color: #fdc4c1;
  } 
  .editable-table-container .col-quantity{
    background-color: #cfecc4;
  } 
  .editable-table-container .col-amount{
    background-color: #fdc4c1;
  }
  .editable-table-container td.col-select {
    cursor: pointer;
  }
  .editable-table-container td.col-select > span{
    display: block; 
    border:solid #d7dde4 1px;
    border-radius: 4px;
    padding: 4px 6px;
    min-height: 28px;
  } 
  .editable-table-container td.col-select:hover > span{
    border-color: #eb5954;
  }
  .editable-table-container th.col-w1 > span{
    width: 40px;display: inline-block;
  }
  .editable-table-container th.col-w2 > span{
    width: 80px;display: inline-block;
  }
  .editable-table-container th.col-w3 > span{
    width: 120px;display: inline-block;
  }
  .editable-table-container th.col-w4 > span{
    width: 160px;display: inline-block;
  }
  .editable-table-container th.col-w5 > span{
    width: 200px;display: inline-block;
  }
</style>
