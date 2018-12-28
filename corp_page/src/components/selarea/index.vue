<template>
  <div>
    <Row>
      <Col span="8" style="padding-right:4px;">
        <Select v-model="address_1">
          <Option v-for="item in province" :value="item.code" :key="item.code">{{ item.text }}</Option>
        </Select>
      </Col>
      <Col span="8" style="padding: 0 2px;"> 
        <Select v-model="address_2">
          <Option v-for="item in city" :value="item.code" :key="item.code">{{ item.text }}</Option>
        </Select>
      </Col>
      <Col span="8" style="padding-left:4px;">
        <Select v-model="address_3">
            <Option v-for="item in area" :value="item.code" :key="item.code">{{ item.text }}</Option>
        </Select>
      </Col>
    </Row> 
  </div>
</template>
<script>

import areajs from '@/assets/js/area'
export default {
  name:'selarea',
  props:{
    value:{
      type:String,
      default:''
    }
  },
  data() {
    return { 
      province:[],
      city:[],
      area:[],
      address_1:'',
      address_2:'',
      address_3:'',
    }
  },
  mounted:function(){
    this.province = areajs.getProvinces(); 
    this.address_listen = true;
  },
  watch:{
    value:function(val,old){
      if(val != old){ 
        this.address_listen = false; 
        if(val != null &&　val.length>=6 && val.substring(0,2) != '00'){
          this.address_1 = val.substring(0,2) + '0000'; 
          if(val.substring(2,2) != '00'){
            this.address_2 = val.substring(0,4) + '00';
            this.city = areajs.getCities(this.address_1); 
            if(val.substring(4,2) != '00'){
              this.address_3 = val;
              this.area = areajs.getAreas(this.address_2);
            }else{
              this.address_3 = '';
              this.area = areajs.getAreas(this.address_2);
            }
          }else{
            this.address_2 = '';
            this.address_3 = '';
            this.city = [];
            this.area = [];
          }
　          }else{
          this.address_1 = '';
          this.address_2 = '';
          this.address_3 = '';
          this.city = [];
          this.area = [];
        }
        this.$nextTick(()=>{this.address_listen = true});
      }
    },
    address_1(val,old){
      if(val != old && this.address_listen){
        if(val){
          this.$emit('input',val); 
        } 
      } 
    },
    address_2(val,old){
      if(val != old && this.address_listen){ 
        if(val){
          this.$emit('input',val); 
        } 
      }
    },
    address_3(val,old){
      if(val != old && this.address_listen){
        if(val){
          this.$emit('input',val); 
        } 
      }
    },
  },
  computed:{

  },
  methods:{　

  }
}

</script>

<style type="text/css">
  　
</style>

