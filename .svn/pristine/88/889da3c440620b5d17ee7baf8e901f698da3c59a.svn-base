<template>
  <div class="select-input">
    <Input v-model="innerText" @click.native="selPerson" readonly="readonly" style="cursor: pointer;" :placeholder="placeholder"></Input>
    <SelContacts ref="selContacts"></SelContacts>
  </div>
</template>

<script>  
import SelContacts from '@/components/selcontacts'; 
export default { 
  components: { 
    SelContacts
  },
  props:{
    value:{
      type:String,
      default:''
    },
    text:{
      type:String,
      default:''
    },
    placeholder:{
      type:String,
      default:'人员'
    },
    model:{
      type:Object,
      default:null
    },
    bindText:{
      type:String,
      default:''
    }
  },
  data() {
    return {
      innerText:this.text
    };
  },
  mounted(){ 
  },
  computed:{

  },
  watch:{
    value(val,old){
       
    },
    text(val,old){
      if(val != old){
        this.innerText = val;
      }
    }
  },
  methods:{
    selPerson(){
      var sel = this.$refs.selContacts;
      sel.show({
        isMulti:false,
        selectPerson:true,
        selectDept:false,
        ok:()=>{ 
          if(sel.select.length>0){
            this.$emit('input',sel.select[0].key);
            if(this.bindText && this.model){
              this.model[this.bindText] = sel.select[0].title;
            }
          }
        }
      }); 
    }
  }
}
</script>

<style>
  .select-input .ivu-input{
    cursor:pointer
  }

</style>

