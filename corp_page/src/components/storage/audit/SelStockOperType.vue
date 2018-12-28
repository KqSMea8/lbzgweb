<template>
  <Select v-model="operId" placeholder="操作原因" @on-change="switItem">
    <Option v-for="item in operTypes" :value="item.operId" :key="item.operId">{{ item.name }}</Option>
  </Select>
</template>

<script>  

export default { 
  components: { 
     
  },
  props:{
    value:{
      type:String,
      default:''
    },
  },
  data() {
    return {
      operTypes:[],
      operId:this.value
    };
  },
  mounted(){
    this.load();
  },
  computed:{

  },
  watch:{
    value(val,old){
      if(val != old){
        this.operId = val;
      }
    },
    type(val,old){
      if(val != old){
        this.load();
      }
    }
  },
  methods:{
    load(){
      this.operTypes = [];
      this.$http.post("/api/storage/stockOpertype/list", {type:this.type})
      .then((res) => {
        if(res.data.code == 0){
          this.operTypes = res.data.data;
        }
      });
    },
    switItem(){
      this.$emit('input',this.operId);
      this.$emit('on-change');
    }
  }
}
</script>

<style>
   

</style>

