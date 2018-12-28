<template>
  <Modal v-model="display" title="审核" :closable="false" :mask-closable="false" :transfer="transfer" :width="360" class="selaudit" @on-ok="ok"
        @on-cancel="cancel">
	<div class="page">
   	  <div class="page-searchbox">
   	    <table cellpadding="0" cellspacing="0">
      	  <tr >								
		    <RadioGroup v-model="pass">
		      <Radio :label="1">通过</Radio>
		      <Radio :label="0">驳回</Radio>
		    </RadioGroup>
		    <Input v-model="remark" type="textarea" placeholder="" v-if="pass==0"/>
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
      default:true
    } 
  },
  data() {
    return { 
      display:false, 
      pass:1,
      remark:'',
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
      this.remark = '';
      
      this.display = true;
    },
    ok(){
      this.display = false;
      this.options.ok({pass:this.pass,remark:this.remark});
    },
    cancel(){
      this.display = false;
    }
  }
}
</script>

<style> 

</style>