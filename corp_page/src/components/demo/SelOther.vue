<template>
  <div class="page demo">
    <div class="page-title">
      选择【物料、供应商、客户、货位】
    </div>
    <div>
      <table class="demo-table">
        <tr>
          <td>客户</td>
          <td><Input v-model="customerName" readonly="readonly" style="cursor: default;" icon="search" @on-click="selCustomer" ></Input></td>
        </tr> 
        <tr>
          <td>供应商</td>
          <td><Input v-model="providerName" readonly="readonly" style="cursor: default;" icon="search" @on-click="selProvider" ></Input></td>
        </tr> 
        <tr>
          <td>货位</td>
          <td>
            <SelPlaceInput ref="selplace" 
              v-model="placeId"
              :storageId="storageId" 
              :displayText="placeName"
              @input-item="placeName=arguments[0].placeName"
            ></SelPlaceInput>
          </td>
        </tr> 
        <tr>
          <td>物料编码</td>
          <td>
            <SelMaterialInput class="width-2" 
              v-model="materId"
              @input-item="materName=arguments[0].materName"
              :displayText="materId"
              :placeholder="'物料编码'"
              :transfer="false"></SelMaterialInput>
          </td>
        </tr> 
        <tr>
          <td>物料名称</td>
          <td>
            <SelMaterialInput class="width-2" 
              v-model="materId"
              @input-item="materName=arguments[0].materName"
              :displayText="materName"
              :transfer="false"></SelMaterialInput>
          </td>
        </tr> 
      </table>
      <SelCustomer ref="selCustomer"></SelCustomer>
      <SelProvider ref="selProvider"></SelProvider>
    </div>
  </div> 
</template>

<script>

import SelCustomer from '@/components/customer/SelectCustomer'; 
import SelProvider from '@/components/provider/SelectProvider'; 
import SelPlaceInput from '@/components/storage/selplace/SelPlaceInput';
import SelMaterialInput from '@/components/material/selmaterial/SelMaterialInput';

export default {
  components: {
    SelCustomer,
    SelProvider,
    SelPlaceInput,
    SelMaterialInput
  },
  data() {  
    return {
      storageId:'s0002',
      placeId:'',
      placeName:'',

      customerId:'',
      customerName:'',

      providerId:'',
      providerName:'',

      materId:'',
      materName:'',

    }
  },
  mounted:function(){ 
     
  },
  computed:{
     
  },
  methods:{
    selCustomer(){
      var sel = this.$refs.selCustomer;
      sel.show({
        ok:(data)=>{ 
          if(data){
            this.customerId = data.customerId;
            this.customerName = data.customerName;
          }
        }
      });
    },
    selProvider(){
      var sel = this.$refs.selProvider;
      sel.show({
        ok:(data)=>{ 
          if(data){
            this.providerId = data.providerId;
            this.providerName = data.providerName;
          }
        }
      });
    }
  }
}

</script>

<style type="text/css">
  .demo-table td{
    padding: 4px;
  }
</style>