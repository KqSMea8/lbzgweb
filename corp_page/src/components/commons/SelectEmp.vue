<template>
     <div class="selected-emp-range" >
        <RadioGroup v-model="range">
            <Radio :label="0">所有人</Radio>
            <Radio :label="1">特定对象</Radio>
        </RadioGroup>     
        <div class="selected-emp-bar" v-if="show">        
            <Button @click="showSelect" style="margin:5px 0;">选择人员</Button>
            <div v-if="emps.length>0">
                <RelatedPerson :persons="emps"></RelatedPerson>
            </div>
        </div>
        <SelContacts ref="selContacts" @on-getcheck="getPersons"></SelContacts>
     </div>
</template>
<script>
import RelatedPerson from '@/components/commons/RelatedPerson';
import SelContacts from '@/components/commons/SelContacts';

export default {
  name:'SelectEmp',
  components: {
    RelatedPerson,
    SelContacts
  },
  props: {
    persons:{
      type:String,
      default:''
    }
  },
  data() { 
    return {
      show:false,
      range:0,
      emps:''
   };
  },
  mounted(){
    this.load(this.persons);
  },
  watch:{
    persons:function(val,old){             
      if(val != old && val.length > 0){
        this.range = 1;
        this.emps = val;
      }else{
        this.range = 0;         
        this.emps = old;
      }   
    },
    range:function(val){     
      if(val === 0){
        this.show = false;
        this.load('');
      }else{
        this.show = true;
        this.load(this.emps);
      }
           
    }
  },
  methods:{
    load(val){
      if(typeof(val) === 'undefined'){
        val = this.persons;
      }
      this.emps = val;
      this.$emit("on-selected",this.emps);
      this.$refs.selContacts.closeSelect();        
    },
     showSelect(){
       this.show = false;
       this.$refs.selContacts.showSelect(this.emps);     
     },
     getPersons(data){
        this.range = 1;
        this.show = true;
        this.load(data);                  
     }
  }
}

</script>

<style> 
  .selected-emp-range .ivu-radio-checked .ivu-radio-inner {
    border-color: #20bfee;
  }
  .selected-emp-range .ivu-radio-inner:after{
    background-color: #20bfee; 
  }
  .selected-emp-bar{padding: 3px;}

  .selected-emp-bar .relatedpersion-list-item {
    width: 40px;
  }
  .selected-emp-bar .relatedpersion-list-item img {
    width: 28px;
    height: 28px
  }
  .select-contacts .relatedpersion-list{
    padding: 3px 3px 3px 0px;
  }
</style>

