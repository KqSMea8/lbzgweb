<template>
  <div>
    <Input v-model="text" icon="search" @on-click="popWin" 
    readonly="readonly" :placeholder="placeholder"
    :title="value" :disabled="disabled"></Input>
    <SelectMaterial ref="selmaterial" :transfer="transfer"></SelectMaterial>
  </div>
</template>

<script>
import SelectMaterial from './SelectMaterial'

export default { 
  components: { 
    SelectMaterial
  }, 
  props:{ 
    value:{
      type:String,
      default:''
    },
    displayText:{
      type:String,
      default:''
    },
    placeholder:{
      type:String,
      default:'物料'
    },
    disabled:{
      type:Boolean,
      default:false
    },
    type:{
      type:Number,
      default:1
    },
    transfer:{
      type:Boolean,
      default:true
    } 
  },
  data() { 
    return { 
      text:this.materName,
      items:[]
    };
  }, 
  mounted(){
     
  },
  watch:{
    displayText(val,old){
      this.text = val;
    }
  },
  methods:{ 
    // 对外方法
    popWin(options){ 
      var selmaterial = this.$refs.selmaterial;
      selmaterial.show({
        ok:(data)=>{
          this.$emit('input',data.materId); 
          this.$emit('input-item',data);
        },
        type:this.type
      });
    }
  }
}
</script>

<style>
   

</style>

