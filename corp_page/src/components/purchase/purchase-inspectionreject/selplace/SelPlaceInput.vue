<template>
  <div>
    <Input v-model="text" icon="search" @on-click="popWin" 
    readonly="readonly"
    :title="value" :disabled="disabled" :placeholder="placeholder"></Input>
    <SelectPlace ref="selplace" :transfer="transfer"></SelectPlace>
  </div>
</template>

<script>
import SelectPlace from './SelectPlace'

export default { 
  components: { 
    SelectPlace
  }, 
  props:{
    storageId:{
      type:String,
      default:'',
    },
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
      default:'货位'
    },
    disabled:{
      type:Boolean,
      default:false
    },
    transfer:{
      type:Boolean,
      default:true
    } 
  },
  data() { 
    return { 
      text:this.placeName,
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
      var selplace = this.$refs.selplace;
      selplace.show({
        storageId:this.storageId,
        ok:(data)=>{
          this.$emit('input',data.placeId); 
          this.$emit('input-item',data);
        }
      }); 
    }
  }
}
</script>

<style>
   

</style>

