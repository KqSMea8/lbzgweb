<template>
  <Modal
    v-model="display"
    :closable="true"
    :footerHide="true"
    :mask-closable="false"
    class="flowgraphview"
    title="" 
    width="95%">
    <flowGraphView ref="flowGraph" v-model="flowNodes"></flowGraphView>
  </Modal>
</template>
<script>
import flowGraphView from '@/components/workflow/components/flow/flowGraphView';
export default {
  name:'initprocflowgraph',
  components: {
    flowGraphView
  },
  props:{
    
  },
  data() {
    return {
      display:false,
      flowNodes:[]
    }
  },
  mounted:function(){ 

  },
  computed:{
      
  },
  methods:{
    ok:function(){ 
      this.display = false; 
      this.$emit('ok');
    },
    cancel:function(){
      this.display = false; 
      this.$emit('cancel');
    },
    show(){
      this.display = true;
    },
    close(){
      this.display = false;
    }
  }
}

</script>

<style type="text/css">
  .flowgraphview .flowgraphview{
    min-height: 260px;
  }

  .flowgraphview .flowgraphview .flowgraphview-body{
    overflow-y: hidden;
  }
</style>