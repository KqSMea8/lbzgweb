<template>
  <div class="page demo">
    <div class="page-title">
      选择部门和人员
    </div>
    <div>
      <table class="demo-table">
        <tr>
          <td>联系人</td>
          <td><Input v-model="empName" readonly="readonly" style="cursor: default;" icon="search" @on-click="selEmp" ></Input></td>
        </tr>
        <tr>
          <td>部门</td>
          <td>{{deptName}}<Button @click="selDept">选择部门</Button></td>
        </tr>
        <tr>
          <td>代码片段</td>
          <td>
<pre>
  import SelContacts from '@/components/selcontacts'; 

  &lt;Button @click=&quot;selDept&quot;&gt;选择部门&lt;/Button&gt;

  selDept(){
    var sel = this.$refs.selContacts;
    sel.show({
      isMulti:false,
      selectPerson:false,
      selectDept:true,
      ok:()=>{ 
        if(sel.select.length>0){
          this.deptName = sel.select[0].title;
          this.deptId = sel.select[0].key;
        }
      }
    });
  }              
</pre>
          </td>
        </tr>
      </table>
      <SelContacts ref="selContacts"></SelContacts>
    </div>
  </div> 
</template>

<script>

import SelContacts from '@/components/selcontacts'; 

export default {
  components: {
    SelContacts
  },
  data() {  
    return {
      empId:'',
      empName:'',
      deptId:'',
      deptName:''
    }
  },
  mounted:function(){ 
     
  },
  computed:{
     
  },
  methods:{
    selEmp(){
      var sel = this.$refs.selContacts;
      sel.show({
        isMulti:false,
        selectPerson:true,
        selectDept:false,
        ok:()=>{ 
          if(sel.select.length>0){
            this.empName = sel.select[0].title;
            this.empId = sel.select[0].key;
          }
        }
      });
    },
    selDept(){
      var sel = this.$refs.selContacts;
      sel.show({
        isMulti:false,
        selectPerson:false,
        selectDept:true,
        ok:()=>{ 
          if(sel.select.length>0){
            this.deptName = sel.select[0].title;
            this.deptId = sel.select[0].key;
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