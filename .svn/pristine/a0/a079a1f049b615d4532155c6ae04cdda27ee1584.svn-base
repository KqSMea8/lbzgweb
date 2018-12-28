<template>
  <Modal style="text-align: left" v-model="display" title="驳回原因" :closable="false" :mask-closable="false" :transfer="transfer"  class="selaudit" @on-ok="ok"
        @on-cancel="cancel">
	<div class="page">
   	  <div class="page-searchbox">
   	    <table cellpadding="0" cellspacing="0">
      	  <tr >								
<!-- 		    <RadioGroup v-model="pass">
		      <Radio :label="0" checked>驳回</Radio>
		    </RadioGroup> -->
		    <Input v-model="auditRemark" type="textarea" placeholder=""/>
      	  </tr>
      	</table>
      </div>
    </div>
  </Modal>
</template>

<script type="text/javascript">
import Loading from '@/components/loading';

export default { 
  components: { 
    Loading
  },
  props:{
    transfer:{
      type:Boolean,
      default:false
    } 
  },
  data() {
    return { 
      display:false, 
      pass:0,
      auditRemark:'',
      options:{}
    };
  },
  mounted(){
     
  },
  computed:{
  },
  methods:{
    
    // 对外方法
    open(options){

      Object.assign(this.options,{
        ok:(data)=>{},
        cancel:()=>{}
      },options); 

      this.pass = 1;
      this.auditRemark = '';
      
      this.display = true;
    },
    ok(){
      this.display = false;
      this.options.ok({pass:this.pass,auditRemark:this.auditRemark});
    },
    cancel(){
      this.display = false;
    }
  }
}
</script>

<style>
</style>