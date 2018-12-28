<template>
     <div class="select-contacts" v-if="show">
        <div class="search-bar">
            <Select
                v-model="keyword"
                filterable
                remote
                :remote-method="loadEmp"
                :loading="loading"
                placeholder="搜索姓名">
                <Option v-for="item in list" :value="item.empId" :key="item.index" :label="item.trueName" @click.native="selectEmp(item)">
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
        <div class="select-contacts-content">
        <ul >
           <li v-if="curDeptNode!=null">
              <template v-for="item in curDeptNode.children">
                <div class="tv-node" :style="style">
                    <table class="tv-node-row">
                      <tr>
                          <td class="tv-node-row-check" >
                              <Checkbox v-model="item.check" :disabled = "item.disabled" @on-change="checkItem(item)"></Checkbox>
                          </td>
                          <td>
                            <img :src="require('../../assets/img/icon-dept.png')" width="24" height="24" style="vertical-align: middle;"/>
                            {{item.dept.deptName}}
                          </td>
                           <td class="tv-node-row-ext" @click="switchChildren(item)">
                              <div class="btns">
                                  <Icon type="chevron-right"></Icon>
                              </div>
                          </td>
                      </tr>
                  </table>
                </div>
              </template>
           </li>
           <li v-if="curDeptNode!=null">
            <template v-for="emp in curDeptNode.employeeBeans">
              <div v-bind:class="{'tv-node':1}" :style="style">
                <table class="tv-node-row">
                    <tr>
                        <td class="tv-node-row-check" >
                            <Checkbox v-model="emp.check" :disabled = "emp.disabled" @on-change="checkEmp(emp)"></Checkbox>
                        </td>
                        <td>
                          <img v-if="emp.header" :src="emp.header"  width="24" height="24" style="vertical-align: middle;border-radius: 4px;">
                          <Avatar v-else shape="square" icon="person" size="small"  style="vertical-align: middle;"/>
                          {{emp.trueName}}
                        </td>
                        <td></td>
                    </tr>
                </table>
              </div>
            </template>
           </li>
        </ul>
        </div>
        <div v-bind:class="{'hidden':!isHidden,'selected-items':true}">
           <ul class="selected-items-list">
                <template v-for="emp in empList">
                  <li class="selected-items-list-item">
                      <div @click="cancelEmp(emp)">
                         <img v-if="emp.header" :src="emp.header"  width="24" height="24" style="vertical-align: middle;border-radius: 4px;">
                         <Avatar v-else shape="square" icon="person" size="small" style="vertical-align: middle;"/>
                         <div class="selected-list-item-truename">{{emp.trueName}}</div>
                      </div>
                  </li>
                </template>
                <template v-for="item in deptList">
                  <li class="selected-items-list-item">
                      <div @click="cancelItem(item)">
                         <img :src="require('../../assets/img/icon-dept.png')" width="24" height="24" style="vertical-align: middle;"/>
                         <div class="selected-list-item-truename">{{item.dept.deptName}}</div>
                      </div>
                  </li>
                </template>
              </ul>
        </div>
        <div class="selected-btn">
            <Button @click="getCheckEmp" type="text">确定</Button>
        </div>
     </div>
</template>
<script>

export default {
  name:'SelContacts',
  components: {
  },
  props: {
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
    selectEmp(item){
      item.check = true;
      let empIds = this.empList.map((emp) => {
         return emp.empId;
      });
      if(empIds.indexOf(item.empId) === -1){
         this.empList.push(item);
      }
    },
    getCheckEmp(){
      let depts = this.deptList.map((deptNode) => {
         return deptNode.dept.deptId;
      });
      this.$http.post('/api/contacts/emp/selectEmp?deptIds=' + depts, {
          }).then((res) => {
            if(res.data.code === 0){
              let personList = res.data.data.map((item) => {
                              return item.empId;
                });
              let persons = personList.join(',');
              if(this.empList.length > 0){
                  this.empList.map((emp)=>{
                     if(persons.indexOf(emp.empId) === -1){
                           personList.push(emp.empId);
                        }
                  });
              }
              this.$emit('on-getcheck',personList.join(','));
              this.show = false;
            }
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
       if(item.check){
         this.deptList.push(item);
       }else{
        this.deptList.map((deptNode) => {
           if(deptNode.dept.deptId === item.dept.deptId){
              let index = this.deptList.indexOf(deptNode);
              this.deptList.splice(index,1);
           }

        });
      }
    },
    cancelItem(item){
      item.check = false;
      this.deptList.map((deptNode) => {
           if(deptNode.dept.deptId === item.dept.deptId){
              let index = this.deptList.indexOf(deptNode);
              this.deptList.splice(index,1);
           }
      });
    },
    checkEmp(item){
       let empIds = this.empList.map((emp) => {
         return emp.empId;
      });
      if(item.check && empIds.indexOf(item.empId) === -1){
          this.empList.push(item);
      }else{
        this.empList.map((emp) => {
           if(emp.empId === item.empId){
              let index = this.empList.indexOf(emp);
              this.empList.splice(index,1);
           }
        });
      }
    },
    cancelEmp(item){
       item.check = false;
        this.empList.map((emp) => {
           if(emp.empId === item.empId){
              let index = this.empList.indexOf(emp);
              this.empList.splice(index,1);
           }
        });
        this.curDeptNode.employeeBeans.map((emp) =>{
          if(emp.empId === item.empId){
             emp.check = false;
          }
        })
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
    },
    closeSelect(){
       this.show = false;
    }
  }
}

</script>

<style>
  .select-contacts{padding: 10px;}
  .select-contacts .hidden{display: none;}
  .select-contacts .ivu-breadcrumb {cursor: pointer;}
  .select-contacts .search-bar{width:100%;margin-bottom: 10px;}
  .select-contacts .select-contacts-content{border-top: 1px solid #ddd;margin-top: 4px;}

  .select-contacts .selected-items{
    width: 100%;
    background-color: #F9FAFB;
    min-height: 40px;
  }
  .select-contacts .selected-items-list{
    padding: 4px 4px 4px 0px;
  }
  .select-contacts .selected-items-list-item{
    text-align: center;
    display: inline-block;
    overflow: hidden;
    cursor: default;
    margin:0px;
    margin-left: 4px;
    margin-top:4px;
  }
  .select-contacts .selected-list-item-truename {
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
    cursor: default;
  }
  .select-contacts .selected-btn{text-align: center;}
  .select-contacts .selected-btn .ivu-btn-text{color:#20bfee;}

  .select-contacts .tv-node{padding: 3px 0px;cursor: default;}
  .select-contacts .tv-node-row{width: 100%;line-height: 26px;}
  .select-contacts .tv-node-row td{margin: 0px;padding: 0px;vertical-align: middle;}
  .select-contacts .tv-node-row-check{width: 14px;text-align: center;}
  .select-contacts .tv-node-row-check .ivu-checkbox-checked .ivu-checkbox-inner {
    border-color: #20bfee;
    background-color: #20bfee;
   }
  .select-contacts .tv-children li{padding: 2px;}
  .select-contacts .tv-checkbox{font-size: 14px;cursor: pointer;}
  .select-contacts .tv-checkbox.tv-checkbox-check{color:#20bfee;font-weight: bold;}
  .select-contacts .tv-node-row-ext{width: 20px;}
  .select-contacts .tv-node-row-ext .btns{cursor: pointer;font-size: 14px;}
  .select-contacts .tv-node-row-ext .btns:hover{color:#20bfee;}
  .select-contacts .tv-node:hover .tv-node-row-ext .btns,
  .select-contacts .tv-node.select .tv-node-row-ext .btns{visibility:inherit;}
</style>

