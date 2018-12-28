<template>
  <Select v-model="storageId" placeholder="仓库" @on-change="switStorage">
    <Option v-for="(item,index) in storage" :value="item.storageId" :key="index">{{ item.name }}</Option>
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
    }
  },
  data() {
    return {
      storage:[],
      storageId:this.value
    };
  },
  mounted(){
    this.load();
  },
  computed:{

  },
  watch:{
    value(val,old){
      if(val != this.storageId){
        this.storageId = val;
      }
      if(val != old){
        this.$emit('on-change');
      }
    }
  },
  methods:{
    load(){
      this.$http.post("/api/storage/listAll", {}).then((res) => { 
        if (res.data.code === 0) {
          var data = res.data.data;
          var rows = [];
          data.map((item)=>{
            if(item.initStatus == 0){
              rows.push(item);
            }
          });
          this.storage = rows;
        } else {
          this.$Message.error(res.data.message);
        }
      }).catch((error) => { 
        this.$Message.error("请求失败，请重新发送")
      });
    },
    switStorage(){
      this.$emit('input',this.storageId);
      /*this.reset();*/
    }/*,*/
/*    reset(){
          Object.assign(this.data,{
          materId:'',
          materName:'',
          spec:'',
          model:'',
          quantity:'',
          texture:'',
          unit:'',
          price:'',
          amount:'',
          remark:'',
        });
    }*/
  }  
}
</script>

<style>
   

</style>

