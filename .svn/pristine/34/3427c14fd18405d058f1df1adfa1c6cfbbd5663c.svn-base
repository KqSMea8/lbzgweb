<template>
<LayoutVer>
  <div slot="top">
  </div> 
  <LayoutHor :fix="true" :left="'contacts-layout-left'">
    <div slot="left" class="contacts-left">
       <LayoutVer>
          <div slot="top" class="contacts-top-title">组织架构</div> 
          <div class="contacts-dept-tree">
            <Dept ref="dept" 
              @on-select="selectDept" 
              @on-menu-delete="delDept"
              @on-menu-add="addDept" 
              @on-menu-update="updateDept"></Dept>
          </div>
          <div slot="bottom" class="contacts-left-footer">
              <a class="footer-txt" v-bind:class="{'active':actionDept}"  @click="undistributed">未分配部门人员</a>
              <a class="footer-txt" v-bind:class="{'active':actionEmp}" @click="loadEmp(1,1)">离职人员</a>
          </div>
      </LayoutVer> 
    </div> 
    <Members ref="members" class="contacts-member"></Members> 
  </LayoutHor>
</LayoutVer>
</template>

<script>

import LayoutVer from '@/components/layout/LayoutVer';
import LayoutHor from '@/components/layout/LayoutHor';
import Dept from '@/components/contacts/Dept'
import Members from '@/components/contacts/Members'

export default {
  components: {
    LayoutVer,
    LayoutHor,
    Dept,
    Members
  },
  data() { 
    return { 
      actionDept:0,
      actionEmp:0
    }
  },
  mounted(){ 
    this.loadDept();
  },
  computed:{ 
  },
  methods:{
    setup(){
      window.location.href="/corp/suite/auth?appKey=contacts";
    },
    loadDept() {
        this.$http.post('/api/contacts/dept/list', {}).then((res) => {        
          if (res.data.code === 0 && res.data.data !=null) {
            this.$refs.dept.load(res.data.data);
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => { 
          this.$Message.error(error.message)
        });
    },
    selectDept(currentDept){
      this.actionDept = 0;
      this.actionEmp = 0;
      this.$refs.members.queryForm = {
         status:-1,
         keyword:'',
         dimission: 0,
         deptId: currentDept.deptId
      };
      this.$refs.members.refresh();
    },
    addDept(editDept){
        let that = this;
        this.$http.post('/api/contacts/dept/add', 
        {
          deptId:editDept.deptId,
          deptName:editDept.deptName
        }).then((res) => {
          if (res.data.code === 0) {
            that.loadDept();
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
           this.$Message.error(error.message)
        });
    },
    updateDept(editDept){
       let that = this;
        this.$http.post('/api/contacts/dept/edit',
         {
          deptId: editDept.deptId, 
          deptName: editDept.deptName
        }).then((res) => {
          if (res.data.code === 0) {
            that.loadDept();
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error.message)
        });
    },
    delDept(deptId){ 
       this.$Modal.confirm({
          title: '删除确认',
          content: '<p>确定删除该部门吗？</p>',
          closable:true,
          onOk: () => {
             let that = this;
            this.$http.post('/api/contacts/dept/delete?deptId=' + deptId).then((res) => {
              if (res.data.code === 0) {
                that.loadDept();
              } else {
                this.$Message.error(res.data.message)
              }
            }).catch((error) => {
              this.$Message.error(error.message)
            });
          }
        });
    },
    loadEmp(dimission,deptId){
      this.actionDept = 0;
      this.actionEmp = 1;
      this.$refs.dept.setSelect();
      this.$refs.members.queryForm = {
         status:-1,
         keyword:'',
         dimission: dimission,
         deptId: deptId
      };
      this.$refs.members.query();
    },
    undistributed(){
      this.actionDept = 1;
      this.actionEmp = 0;
      this.$refs.dept.setSelect();
      this.$refs.members.undistributed();      
    }
  }
}

</script>
<style type="text/css">
  /*部门树*/
  .contacts-left .dept-tree-node .tv-node.select{
    background-color: #e8f8fd;
    color: #20bfee;
  }
  .contacts-left .dept-tree-node .tv-node:hover {
    background-color: #e8f8fd;
  }

</style>

<style type="text/css">
  .contacts-bar-alert { 
    height: 42px;
    line-height: 24px;
    margin-bottom: 0px;
  }
  .contacts-left {
     height:100%;
     width:210px;
     background-color: white;
     border-right: 1px solid #eeeeee;
  }
  .contacts-top-title {
    line-height: 42px;
    font-size: 12px;
    padding-left:15px;
    border-bottom: 1px solid #eeeeee;
    /*background-color: #F9FAFB;*/
  }
  .contacts-left-footer {
    line-height: 40px;
    text-align: left;
    cursor: pointer;
    background-color: white;
  }

  .contacts-left-footer .footer-txt {
    display: block;
    color: #495060;
    border-top: 1px solid #eeeeee;
    padding-left: 15px;
    /*background-color: #F9FAFB;*/
  }
   .contacts-left-footer .footer-txt:hover {
    color: #20bfee;
    background-color: #e8f8fd;
    border-right:2px solid #20bfee; 
  }

  .contacts-left-footer .active {
    color:#20bfee;
    background-color: #e8f8fd;
    border-right:2px solid #20bfee; 
  }
  
  .contacts-layout-left.layout-hor-left{
    width: 210px;
  }
  
</style>