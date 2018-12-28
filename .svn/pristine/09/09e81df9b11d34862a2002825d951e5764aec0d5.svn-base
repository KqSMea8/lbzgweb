<template>
     <div class="wf-seldept">
        <div class="search-bar" v-if="showPerson">
            <Select
                v-model="keyword"
                filterable
                remote
                :remote-method="loadEmp"
                :loading="loading"
                placeholder="搜索姓名">
                <Option v-for="item in list" :value="item.empId" :key="item.index" :label="item.trueName" @click.native="checkEmp(item)">
                  <img v-if="item.header" :src="item.header"  width="24" height="24" style="vertical-align: middle;border-radius: 4px;">
                  <Avatar v-else shape="square" icon="person" size="small"  style="vertical-align: middle;"/>
                  {{ item.trueName }}
                  <span style="margin-left: 5px;"/>{{item.moblie}}</span>
                </Option>
            </Select>
        </div>
        <Breadcrumb separator=">">
             <template v-for="item in nodeItems">
                <BreadcrumbItem @click.native="switchNode(item)" :replace="false">
                  {{item.dept.deptName}}
                </BreadcrumbItem>
             </template>
        </Breadcrumb>
        <div class="wf-seldept-content">
        <ul >
           <li v-if="curDeptNode!=null">
              <template v-for="item in curDeptNode.children">
                <div class="tv-node" :style="style">
                    <table class="tv-node-row">
                      <tr>
                          <td class="tv-node-row-title" @click="checkItem(item)">
                            <img :src="require('@/assets/img/icon-dept.png')" width="24" height="24" style="vertical-align: middle;"/>
                            &nbsp;
                            {{item.dept.deptName}}
                          </td>
                          <td class="tv-node-row-space"></td>
                          <td class="tv-node-row-ext" @click="switchChildren(item)">
                            <Icon type="chevron-right"></Icon>
                          </td>
                      </tr>
                  </table>
                </div>
              </template>
           </li>
           <li v-if="curDeptNode!=null && showPerson">
              <div class="tv-node" v-for="emp in curDeptNode.employeeBeans">
                <table class="tv-node-row">
                    <tr>
                      <td class="tv-node-row-title" @click="checkEmp(emp)">
                        <img v-if="emp.header" :src="emp.header"  width="24" height="24" style="vertical-align: middle;border-radius: 4px;">
                        <Avatar v-else shape="square" icon="person" size="small"  style="vertical-align: middle;"/>
                        &nbsp;
                        {{emp.trueName}}
                      </td>
                    </tr>
                </table>
              </div>
           </li>
        </ul>
        </div>
     </div>
</template>
<script>

export default {
  name:'SelectDept',
  components: {
  },
  props: {
    showPerson:{
      type:Boolean,
      default:true
    }
  },
  data() {
    return {
      empList:[],
      deptList:[],
      curDeptNode:null,
      nodeItems:[],
      show:false,
      keyword:'',
      list:[],
      loading:false
   };
  },
  mounted(){
    this.load();
  },
  watch:{
  },
  computed:{
    isHidden(){
      return this.empList.length > 0 || this.deptList.length > 0;
    },
    style() {
      if(this.model && this.model.level){
        return 'padding-left:' + this.model.level*14 + 'px';
      }
      return '';
    }
  },
  methods:{
    load(){
        this.$http.post('/api/contacts/emp/buildContacts', {
          }).then((res) => {
            if (res.data.code === 0){
                this.curDeptNode = res.data.data;
                this.nodeItems[0] = this.curDeptNode;
            }else {
              this.$Message.error(res.data.message)
            }
      }).catch((error) => {
            this.$Message.error(error.message)
      });
    },
    loadEmp(value){
      let that = this;
      if(value === ''){
        this.list = [];
        return;
      }
       this.loading = true;
       this.$http.post('/api/contacts/emp/list', {
            deptId: 1,
            dimission: 0,
            status: -1,
            keyword: value,
            page: 1,
            pageSize: 10
          }).then((res) => {
            if (res.data.code === 0 && res.data.data != null) {
               this.loading = false;
               that.list = res.data.data.rows;
            } else {
              this.$Message.error(res.data.message)
            }
          }).catch((error) => {
            this.$Message.error(error.message)
        });
    },
    switchChildren(item){
      this.curDeptNode = item;
      this.nodeItems.push(item);
      var depts = this.deptList.map((dept) => {
         return dept.dept.deptId;
      });
      item.children.map((dept) => {
         dept.check = false;
         var deptItem = dept.dept;
          if(depts.length > 0 && depts.join(',').indexOf(deptItem.deptId) > -1){
              dept.check = true;
            }
          dept.disabled = false;
      });

        var emps = this.empList.map((emp) =>{
          return emp.empId;
        })
        item.employeeBeans.map((emp) => {
            emp.check = false;
            if(emps.length > 0 && emps.join(',').indexOf(emp.empId) > -1){
              emp.check = true;
            }
            emp.disabled = false;
        });
      if(item.check){
          item.children.map((dept) => {
                dept.check = true;
                dept.disabled = true;
          });
          item.employeeBeans.map((emp) => {
            emp.check = true;
            emp.disabled = true;
          });
      }
    },
    switchNode(item){
      this.curDeptNode = item;
      let index = this.nodeItems.indexOf(item);
      this.nodeItems.splice(index+1,this.nodeItems.length);

      var emps = this.empList.map((emp) =>{
          return emp.empId;
        })
        item.employeeBeans.map((emp) => {
            emp.check = false;
            if(emps.length > 0 && emps.join(',').indexOf(emp.empId) > -1){
              emp.check = true;
            }
            emp.disabled = false;
        });
    },
    checkItem(item){
      this.$emit('on-select-dept',item.dept);
    },

    checkEmp(item){
      this.$emit('on-select-emp',item);
    },
    showSelect(emps){
      this.empList = [];
      if(emps.length >0){
        this.$http.post('/api/contacts/getBaseInfo', {empIds:emps}).then((res) => {
          if (res.data.code === 0) {
            var rult = res.data.data;
            for(var i=0;i<rult.length;i++){
                rult[i].check = true;
              }
            this.empList = rult;
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
            this.$Message.error(error.toString());
        });
      }
      this.keyword = '';
      this.deptList = [];
      this.nodeItems.splice(1,this.nodeItems.length-1);
      this.curDeptNode = this.nodeItems[0];
      this.curDeptNode.children.map((dept) => {
         dept.check = false;
      });
      var employees = this.curDeptNode.employeeBeans;
      employees.map((bean) => {
        bean.check = false;
        if(emps.indexOf(bean.empId) > -1){
          bean.check = true;
        }
      });
      this.show = true;
    }
  }
}

</script>

<style>
  .wf-seldept{padding: 10px;}
  .wf-seldept .hidden{display: none;}
  .wf-seldept .ivu-breadcrumb {cursor: pointer;}
  .wf-seldept .search-bar{width:100%;margin-bottom: 10px;}
  .wf-seldept .wf-seldept-content{border-top: 1px solid #ddd;margin-top: 4px;}

  .wf-seldept .selected-items{
    width: 100%;
    background-color: #F9FAFB;
    min-height: 40px;
  }
  .wf-seldept .selected-items-list{
    padding: 4px 4px 4px 0px;
  }
  .wf-seldept .selected-items-list-item{
    text-align: center;
    display: inline-block;
    overflow: hidden;
    cursor: default;
    margin:0px;
    margin-left: 4px;
    margin-top:4px;
  }
  .wf-seldept .selected-list-item-truename {
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
    cursor: default;
  }
  .wf-seldept .selected-btn{text-align: center;}
  .wf-seldept .selected-btn .ivu-btn-text{color:#20bfee;}

  .wf-seldept .tv-node{cursor: default;}
  .wf-seldept .tv-node-row{width: 100%;line-height: 26px;cursor: pointer;}
  .wf-seldept .tv-node-row td{vertical-align: middle;height: 34px;}
  .wf-seldept .tv-node-row-space{
    width: 2px;
  }
  .wf-seldept .tv-node-row-title{
    padding: 0px 10px;
  }
  .wf-seldept .tv-node:hover .tv-node-row-title,
  .wf-seldept .tv-node:hover .tv-node-row-ext{
    background-color: #d5e8fc;
  }

  .wf-seldept .tv-node-row-ext{width: 30px;cursor: pointer;font-size: 14px;text-align: center;}
  .wf-seldept .tv-node .tv-node-row .tv-node-row-ext:hover{
    background-color: #2d8cf0;
    color: white;
  }
</style>

